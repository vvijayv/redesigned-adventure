package com.fourkites.r2dbc;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageTemplateRepo messageRepoTemplate;

    @GetMapping(value = "/{message}")
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<Message> newMessage(@PathVariable String message) {
        return messageRepository.save(Message.builder().value(message).ts(LocalDateTime.now()).build());
    }

    @GetMapping(value = "/messages/{messageId}")
    public Flux<Message> getMethodName(@PathVariable String messageId) {
        return messageRepository.findByValue(messageId);
    }

    @PostMapping(value = "/v2/messages")
    public Flux<Message> getMethodName2(@RequestBody RequestQuery query) {
        return messageRepoTemplate.findByMessageValue(query);
    }

}
