package com.example.demo.service;

import com.example.demo.model.Producer;

import java.util.List;

public interface ProducerService {
    List<Producer> getAllProducers();
    void saveProducer(Producer producer);
    Producer getProducerByName(String name);
}
