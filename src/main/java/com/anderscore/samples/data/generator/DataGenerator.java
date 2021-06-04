package com.anderscore.samples.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import com.anderscore.samples.data.service.SamplePersonRepository;
import com.anderscore.samples.data.entity.SamplePerson;
import com.anderscore.samples.data.service.SampleBookRepository;
import com.anderscore.samples.data.entity.SampleBook;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(SamplePersonRepository samplePersonRepository,
            SampleBookRepository sampleBookRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (samplePersonRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Sample Person entities...");
            ExampleDataGenerator<SamplePerson> samplePersonRepositoryGenerator = new ExampleDataGenerator<>(
                    SamplePerson.class, LocalDateTime.of(2021, 5, 30, 0, 0, 0));
            samplePersonRepositoryGenerator.setData(SamplePerson::setId, DataType.ID);
            samplePersonRepositoryGenerator.setData(SamplePerson::setFirstName, DataType.FIRST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setLastName, DataType.LAST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setEmail, DataType.EMAIL);
            samplePersonRepositoryGenerator.setData(SamplePerson::setPhone, DataType.PHONE_NUMBER);
            samplePersonRepositoryGenerator.setData(SamplePerson::setDateOfBirth, DataType.DATE_OF_BIRTH);
            samplePersonRepositoryGenerator.setData(SamplePerson::setOccupation, DataType.OCCUPATION);
            samplePersonRepositoryGenerator.setData(SamplePerson::setImportant, DataType.BOOLEAN_10_90);
            samplePersonRepository.saveAll(samplePersonRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Sample Book entities...");
            ExampleDataGenerator<SampleBook> sampleBookRepositoryGenerator = new ExampleDataGenerator<>(
                    SampleBook.class, LocalDateTime.of(2021, 5, 30, 0, 0, 0));
            sampleBookRepositoryGenerator.setData(SampleBook::setId, DataType.ID);
            sampleBookRepositoryGenerator.setData(SampleBook::setImage, DataType.BOOK_IMAGE_URL);
            sampleBookRepositoryGenerator.setData(SampleBook::setName, DataType.BOOK_TITLE);
            sampleBookRepositoryGenerator.setData(SampleBook::setAuthor, DataType.FULL_NAME);
            sampleBookRepositoryGenerator.setData(SampleBook::setPublicationDate, DataType.DATE_OF_BIRTH);
            sampleBookRepositoryGenerator.setData(SampleBook::setPages, DataType.NUMBER_UP_TO_1000);
            sampleBookRepositoryGenerator.setData(SampleBook::setIsbn, DataType.EAN13);
            sampleBookRepository.saveAll(sampleBookRepositoryGenerator.create(100, seed));

            logger.info("Generated demo data");
        };
    }

}