package com.anderscore.samples.data.service;

import com.anderscore.samples.data.entity.SampleBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import javax.persistence.Lob;
import java.time.LocalDate;

@Service
public class SampleBookService extends CrudService<SampleBook, Integer> {

    private SampleBookRepository repository;

    public SampleBookService(@Autowired SampleBookRepository repository) {
        this.repository = repository;
    }

    @Override
    protected SampleBookRepository getRepository() {
        return repository;
    }

}
