package ru.diasoft.message.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class NotificationConfig {

    private static final Logger logger = LogManager.getLogger(NotificationConfig.class);

    @Value("${notification.service-account-file}")
    private Resource serviceAccountFile;

    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        try (InputStream inputStream = serviceAccountFile.getInputStream()) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build();
            FirebaseApp.initializeApp(options);
            return FirebaseMessaging.getInstance();
        }
    }

}
