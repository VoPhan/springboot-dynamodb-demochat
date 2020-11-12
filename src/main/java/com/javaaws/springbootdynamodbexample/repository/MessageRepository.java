package com.javaaws.springbootdynamodbexample.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.TransactGetItem;
import com.javaaws.springbootdynamodbexample.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MessageRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public Message sendMessage(Message message){
        message.setCreated_time(LocalDateTime.now().toString());
        mapper.save(message);
        return message;
    }

    public List<Message> getMessage(String id_recived){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val2", new AttributeValue().withS(id_recived));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("id_recived = :val2").withLimit(25).withExpressionAttributeValues(eav);
        ScanResultPage<Message> latestReplies = mapper.scanPage(Message.class, scanExpression);

        return latestReplies.getResults();
    }

    private DynamoDBSaveExpression buildExpression(Message message){
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("id", new ExpectedAttributeValue(new AttributeValue().withS(message.getId())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
