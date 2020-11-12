package com.javaaws.springbootdynamodbexample.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@DynamoDBDocument
@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "messages")
public class Message implements Serializable {
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "id_send")
    private String id_send;

    @DynamoDBAttribute(attributeName = "id_recived")
    private String id_recived;

    @DynamoDBAttribute
    private String message;

    @DynamoDBAttribute
    private String created_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_send() {
        return id_send;
    }

    public void setId_send(String id_send) {
        this.id_send = id_send;
    }

    public String getId_recived() {
        return id_recived;
    }

    public void setId_recived(String id_recived) {
        this.id_recived = id_recived;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
