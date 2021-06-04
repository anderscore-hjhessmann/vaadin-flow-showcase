package com.anderscore.samples.data.service;

import com.anderscore.samples.data.entity.SampleAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class SampleAddressService extends CrudService<SampleAddress, Integer> {

    private SampleAddressRepository repository;

    public SampleAddressService(@Autowired SampleAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    protected SampleAddressRepository getRepository() {
        return repository;
    }

}
