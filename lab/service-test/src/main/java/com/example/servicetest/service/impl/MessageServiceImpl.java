package com.example.servicetest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.servicetest.model.Message;
import com.example.servicetest.service.MessageService;

public class MessageServiceImpl implements MessageService {

    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    public List<Message> getMessages() {
        return this.messages.subList(0, this.messages.size());
    }

    public void generateDemo(int size) {
        for (int i = 0; i < size; i++) {
            Message message = new Message(String.format("Demo %d", i + 1));
            this.messages.add(message);
        }
    }

}
