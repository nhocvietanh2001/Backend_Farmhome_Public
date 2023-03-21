package com.ute.farmhome.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ute.farmhome.dto.NotificationNote;

public interface FirebaseMessagingService {
    public String sendNotification(NotificationNote note, String token) throws FirebaseMessagingException;
}
