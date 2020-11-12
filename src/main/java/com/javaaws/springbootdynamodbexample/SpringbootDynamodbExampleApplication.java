package com.javaaws.springbootdynamodbexample;

import com.javaaws.springbootdynamodbexample.entity.Message;
import com.javaaws.springbootdynamodbexample.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringbootDynamodbExampleApplication {

    @Autowired
    private MessageRepository repository;

    @PostMapping("/sendMessage")
    public Message sendMessage(@RequestBody Message message){
        return repository.sendMessage(message);
    }

    @PostMapping("/findMessage")
    public List<Message> getMessage(@RequestBody String id_send, @RequestBody String id_recived){
        return repository.getMessage(id_send,id_recived);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamodbExampleApplication.class, args);
    }

}
