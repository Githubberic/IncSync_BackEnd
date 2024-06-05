package com.user.listener;

import com.user.config.MQConfig;
import com.user.dto.response.UserMessage;
import com.user.interfaces.UserServiceInterface;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private UserServiceInterface userService;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(UserMessage userMessage) {

        Long authId = userMessage.getAuthId();
        String action = userMessage.getAction();

        System.out.println("Received authId: " + authId + "with action: " + action);

        switch (action)
        {
            case "onCreateAccount":
                userService.createUserWithAuthId(authId);
                break;
            case "onDeleteAccount":
                userService.deleteUserWithAuthId(authId);
        }
    }
}
