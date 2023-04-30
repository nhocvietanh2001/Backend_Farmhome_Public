package com.ute.farmhome.service;

import com.ute.farmhome.dto.NotificationNote;
import com.ute.farmhome.entity.NotificationHistory;
import com.ute.farmhome.entity.User;

import java.util.List;

public interface NotificationHistoryService {
    NotificationHistory save(NotificationNote notificationNote, User user);
    NotificationHistory getById(int id);
    List<NotificationHistory> getByUserId(int id);
    NotificationHistory updateIsRead(int id);
}
