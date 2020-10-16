package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.Producer;
import com.example.demo.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    @Override
    public void saveProducer(Producer producer) {
            producerRepository.save(producer);
    }

    @Override
    public Producer getProducerByName(String name) {
        return producerRepository.findByName(name);
    }

    @Override
    public Producer getProducerById(Long id) {
        return producerRepository.findById(id).get();
    }

    @Override
    public void deleteProducerById(Long id) {
        Producer producer = producerRepository.findById(id).get();
        Set<Film> films = producer.getFilms();
        for (Film film: films
        ) {
            film.getProducers().remove(producer);
        }
        producerRepository.deleteById(id);
    }
}
