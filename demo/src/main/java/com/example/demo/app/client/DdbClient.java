package com.example.demo.app.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DdbClient {
    /**
     * https://docs.aws.amazon.com/ja_jp/sdk-for-java/latest/developer-guide/examples-dynamodb-items.html
     */
    private final DynamoDbClient dynamoDbClient;

    public QueryResponse query(
            String tableName,
            String keyConditionExpression,
            Map<String, AttributeValue> expressionAttributeValues
    ) {
        QueryRequest request = QueryRequest.builder()
                    .tableName(tableName)
                    .keyConditionExpression(keyConditionExpression)
                    .expressionAttributeValues(expressionAttributeValues)
                    .build();

        QueryResponse response = dynamoDbClient.query(request);

        System.out.println("query result size: " + response.items().size());
        return response;
    }

    public void insert(
            String tableName,
            Map<String, AttributeValue> keyMap
    ) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(keyMap)
                .build();

        dynamoDbClient.putItem(request);

        System.out.println("insert done");
    }

    public void update(
            String tableName,
            Map<String, AttributeValue> keyMap,
            String updateExpression,
            Map<String, AttributeValue> expressionAttributeValues
    ) {
        String conditionExpression = "";
        for (final String key : keyMap.keySet()) {
            conditionExpression += "attribute_exists(" + key + ") and ";
        }
        conditionExpression = conditionExpression
                .substring(0, conditionExpression.length() - " and ".length());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(keyMap)
                .updateExpression(updateExpression)
                .expressionAttributeValues(expressionAttributeValues)
                .conditionExpression(conditionExpression)
                .build();

        dynamoDbClient.updateItem(request);

        System.out.println("update done");
    }

    /**
     * DynamoDbClient.updateItem() is upsert by default,
     * without UpdateItemRequest.conditionExpression().
     */
    public void upsert(
            String tableName,
            Map<String, AttributeValue> keyMap,
            String updateExpression,
            Map<String, AttributeValue>  expressionAttributeValues
    ) {
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(keyMap)
                .updateExpression(updateExpression)
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        dynamoDbClient.updateItem(request);

        System.out.println("upsert done");
    }
}
