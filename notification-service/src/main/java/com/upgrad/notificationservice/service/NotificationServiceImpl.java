package com.upgrad.notificationservice.service;

import com.upgrad.notificationservice.model.NotificationException;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public final class NotificationServiceImpl implements NotificationService {

  @Override
  public String sendNotification(final String recipient, final String subject, final String body)
      throws NotificationException {
    if (recipient.length() <= 20) {
      return UUID.randomUUID().toString();
    }
    throw new NotificationException("Invalid recipient");
  }
}
