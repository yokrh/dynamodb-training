package com.example.demo.app.service;

import com.example.demo.app.controller.request.AddUserApiRequest;
import com.example.demo.app.repository.DynamoDbUserRepository;
import com.example.demo.app.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final DynamoDbUserRepository dynamoDbUserRepository;

    public User getUser(Long userId) {
        User user = dynamoDbUserRepository.queryByUserId(userId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("failed to get a user.");
        }

        return user;
    }

    public void addUser(AddUserApiRequest addUserApiRequest) {
        dynamoDbUserRepository.addUser(User.createFromAddUserApiRequest(addUserApiRequest));
    }

    public void deleteUser(Long userId, String userName) {
        dynamoDbUserRepository.deleteUser(userId, userName);
    }
}
