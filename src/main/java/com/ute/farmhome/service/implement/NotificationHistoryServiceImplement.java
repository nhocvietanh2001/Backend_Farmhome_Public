package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.NotificationNote;
import com.ute.farmhome.entity.NotificationHistory;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.repository.NotificationHistoryRepository;
import com.ute.farmhome.service.NotificationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationHistoryServiceImplement implements NotificationHistoryService {
    @Autowired
    NotificationHistoryRepository notificationHistoryRepository;

    @Override
    public NotificationHistory save(NotificationNote notificationNote, User user) {
        NotificationHistory history = new NotificationHistory(0,
                user,
                notificationNote.getTitle(),
                notificationNote.getContent(),
                notificationNote.getImgUrl(),
                notificationNote.getType(),
                LocalDate.now(),
                notificationNote.getId(),
                false);
        return notificationHistoryRepository.save(history);
    }

    @Override
    public NotificationHistory getById(int id) {
        return notificationHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("NotificationHistory", "id", String.valueOf(id)));
    }

    @Override
    public List<NotificationHistory> getByUserId(int id) {
        return notificationHistoryRepository.findAllByUserId(id);
    }

    @Override
    public NotificationHistory updateIsRead(int id) {
        NotificationHistory notificationHistory = getById(id);
        notificationHistory.setIsRead(true);
        return notificationHistoryRepository.save(notificationHistory);
    }
}
