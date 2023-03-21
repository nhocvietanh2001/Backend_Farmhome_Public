package com.ute.farmhome.service.implement;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ute.farmhome.dto.NotificationNote;
import com.ute.farmhome.service.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingServiceImplement implements FirebaseMessagingService {
    @Autowired
    FirebaseMessaging firebaseMessaging;
    @Override
    public String sendNotification(NotificationNote note, String token) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(note.getTitle()).setBody(note.getContent()).setImage(note.getImgUrl()).build();
        Message message = Message.builder().setToken(token).setNotification(notification).build();
        return firebaseMessaging.send(message);
    }
}
