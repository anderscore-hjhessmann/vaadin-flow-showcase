package com.anderscore.samples.data.service;

import com.anderscore.samples.data.entity.SampleBook;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.Lob;
import java.time.LocalDate;

public interface SampleBookRepository extends JpaRepository<SampleBook, Integer> {

}