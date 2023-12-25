package com.example.demo.app.service.model;

import com.example.demo.app.controller.request.AddUserApiRequest;
import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Clock;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.app.repository.DynamoDbUserRepository.*;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private String birthday;
    private Boolean isDeleted;
    private String deletedAt;

    public static User createFromAddUserApiRequest(AddUserApiRequest addUserApiRequest) {
        Long id = Instant.now(Clock.systemUTC()).toEpochMilli(); // simple id generation

        return User.builder()
                .id(id)
                .name(addUserApiRequest.getUserName())
                .birthday(addUserApiRequest.getBirthday())
                .isDeleted(Boolean.FALSE)
                .build();
    }

    public static User createFromDdbResponse(Map<String, AttributeValue> attributeValueMap) {
        return User.builder()
                .id(Long.parseLong(attributeValueMap.get(DDB_USER_TABLE_USER_ID_KEY).n()))
                .name(attributeValueMap.get(DDB_USER_TABLE_NAME_KEY).s())
                .birthday(attributeValueMap.get(DDB_USER_TABLE_BIRTHDAY_KEY).s())
                .isDeleted(Boolean.TRUE.toString()
                        .equals(attributeValueMap.get(DDB_USER_TABLE_IS_DELETED_KEY).s()))
                .deletedAt(Optional.ofNullable(attributeValueMap.get(DDB_USER_TABLE_DELETED_AT_KEY)).stream()
                        .map(attributeValue -> attributeValue.s())
                        .findFirst()
                        .orElse(null))
                .build();
    }
}
