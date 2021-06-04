package com.anderscore.samples.data.service;

import com.anderscore.samples.data.entity.SampleAddress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleAddressRepository extends JpaRepository<SampleAddress, Integer> {

}