package com.ute.farmhome.repository;

import com.ute.farmhome.entity.NotificationHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationHistoryRepository extends CrudRepository<NotificationHistory, Integer> {
    @Query ("SELECT nh FROM NotificationHistory nh WHERE nh.user.id = :uid")
    List<NotificationHistory> findAllByUserId(int uid);
}
