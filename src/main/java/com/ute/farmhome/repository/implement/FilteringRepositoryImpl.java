package com.ute.farmhome.repository.implement;

import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.repository.FilteringRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilteringRepositoryImpl implements FilteringRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private boolean stringNotNullAndNotEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    private boolean listNotNullAndNotEmpty(List<String> list) {
        return list != null && !list.isEmpty();
    }

    @Override
    public Page<Fruit> filterFruit(String name, Float amount, List<String> seasonList, Boolean popular, String order, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fruit> criteria = criteriaBuilder.createQuery(Fruit.class);
        CriteriaQuery<Long> criteriaCount = criteriaBuilder.createQuery(Long.class);

        Root<Fruit> fruit = criteria.from(Fruit.class);
        Root<Fruit> fruitCount = criteriaCount.from(Fruit.class);

        List<Predicate> finalPredicates = new ArrayList<>();
        List<Predicate> finalPredicatesCount = new ArrayList<>();

        List<Predicate> singlePredicates = new ArrayList<>();
        List<Predicate> singlePredicatesCount = new ArrayList<>();

        if (stringNotNullAndNotEmpty(name)) {
            // (fruit.name like %name%)
            singlePredicates.add(criteriaBuilder.like(fruit.get("name"), "%" + name + "%"));
            singlePredicatesCount.add(criteriaBuilder.like(fruitCount.get("name"), "%" + name + "%"));
        }

        if (popular != null && !popular.describeConstable().isEmpty()) {
            // (fruit.popular = popular)
            singlePredicates.add(criteriaBuilder.equal(fruit.get("popular"), popular));
            singlePredicatesCount.add(criteriaBuilder.equal(fruitCount.get("popular"), popular));
        }

        finalPredicates.add(criteriaBuilder.and(singlePredicates.toArray(new Predicate[singlePredicates.size()])));
        finalPredicatesCount.add(criteriaBuilder.and(singlePredicatesCount.toArray(new Predicate[singlePredicatesCount.size()])));

        List<Predicate> seasonPredicates = new ArrayList<>();
        List<Predicate> seasonPredicatesCount = new ArrayList<>();

        if (listNotNullAndNotEmpty(seasonList)) {
            for (String season : seasonList) {
                seasonPredicates.add(criteriaBuilder.equal(fruit.get("season"), season));
                seasonPredicatesCount.add(criteriaBuilder.equal(fruitCount.get("season"), season));
            }
            finalPredicates.add(criteriaBuilder.and(seasonPredicates.toArray(new Predicate[seasonPredicates.size()])));
            finalPredicatesCount.add(criteriaBuilder.and(seasonPredicatesCount.toArray(new Predicate[seasonPredicatesCount.size()])));
        }

        Order orderCriteria = criteriaBuilder.desc(fruit.get("date"));
        if (order.equals("oldest"))
            orderCriteria = criteriaBuilder.asc(fruit.get("date"));

        criteria.where(criteriaBuilder.and(finalPredicates.toArray(new Predicate[finalPredicates.size()])));
        criteria.orderBy(orderCriteria);

        List<Fruit> fruitList = entityManager.createQuery(criteria)
                .setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        criteriaCount.select(criteriaBuilder.count(fruitCount)).where(criteriaBuilder.and(finalPredicatesCount.toArray(new Predicate[finalPredicatesCount.size()])));
        Long count = entityManager.createQuery(criteriaCount).getSingleResult();

        return new PageImpl<>(fruitList, pageable, count);
    }
}
