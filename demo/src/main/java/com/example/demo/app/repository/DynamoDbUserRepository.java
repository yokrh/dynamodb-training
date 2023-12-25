package com.example.demo.app.repository;

import com.example.demo.app.client.DdbClient;
import com.example.demo.app.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.time.Clock;
import java.time.Instant;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DynamoDbUserRepository {
    public static final String DDB_USER_TABLE_NAME = "User";
    public static final String DDB_USER_TABLE_USER_ID_KEY = "userId";
    public static final String DDB_USER_TABLE_NAME_KEY = "userName";
    public static final String DDB_USER_TABLE_BIRTHDAY_KEY = "birthday";
    public static final String DDB_USER_TABLE_IS_DELETED_KEY = "isDeleted";
    public static final String DDB_USER_TABLE_DELETED_AT_KEY = "deletedAt";

    private final DdbClient ddbClient;

    public User queryByUserId(Long userId) {
        QueryResponse response;
        try {
            String keyConditionExpression = DDB_USER_TABLE_USER_ID_KEY + " = :" + DDB_USER_TABLE_USER_ID_KEY;
            Map<String, AttributeValue> expressionAttributeValues = Map.of(
                    ":" + DDB_USER_TABLE_USER_ID_KEY, AttributeValue.fromN(userId.toString())
            );

            response = ddbClient.query(
                    DDB_USER_TABLE_NAME,
                    keyConditionExpression,
                    expressionAttributeValues
            );
        } catch(Exception ex) {
            throw new RuntimeException("ddb query by user id error", ex);
        }

        if (!response.hasItems() || response.count() > 1) {
            return null;
        }

        return response.items().stream()
                .map(User::createFromDdbResponse)
                .findFirst()
                .orElse(null);
    }

    public void addUser(User user) {
        try {
            Map<String, AttributeValue> keyMap = Map.of(
                    DDB_USER_TABLE_USER_ID_KEY, AttributeValue.fromN(user.getId().toString()),
                    DDB_USER_TABLE_NAME_KEY, AttributeValue.fromS(user.getName()),
                    DDB_USER_TABLE_BIRTHDAY_KEY, AttributeValue.fromS(user.getBirthday()),
                    DDB_USER_TABLE_IS_DELETED_KEY, AttributeValue.fromS(user.getIsDeleted().toString())
            );

            ddbClient.insert(
                    DDB_USER_TABLE_NAME,
                    keyMap
            );
        } catch(Exception ex) {
            throw new RuntimeException("ddb add user error", ex);
        }
    }

    /**
     * Logical delete
     */
    public void deleteUser(Long userId, String userName) {
        try {
            Map<String, AttributeValue> keyMap = Map.of(
                    DDB_USER_TABLE_USER_ID_KEY, AttributeValue.fromN(userId.toString()),
                    DDB_USER_TABLE_NAME_KEY, AttributeValue.fromS(userName)
            );
            String updateExpression = "SET isDeleted = :isDeleted, deletedAt = :deletedAt";
            Map<String, AttributeValue> expressionAttributeValues = Map.of(
                    ":" + DDB_USER_TABLE_IS_DELETED_KEY, AttributeValue.fromS(Boolean.TRUE.toString()),
                    ":" + DDB_USER_TABLE_DELETED_AT_KEY, AttributeValue.fromS(
                            String.valueOf(Instant.now(Clock.systemUTC()).toEpochMilli())
                    )
            );

            ddbClient.update(
                    DDB_USER_TABLE_NAME,
                    keyMap,
                    updateExpression,
                    expressionAttributeValues
            );
        } catch(Exception ex) {
            throw new RuntimeException("ddb delete user error", ex);
        }
    }
}
