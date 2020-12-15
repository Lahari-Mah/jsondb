package com.thebrgtest.jsondb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thebrgtest.jsondb.domain.Input;
import com.thebrgtest.jsondb.service.InputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JsondbApplication {

    private static final Logger log = LoggerFactory.getLogger(JsondbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JsondbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(InputService inputService){
        return args -> {
            // read JSON and load json
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Input>> typeReference = new TypeReference<List<Input>>(){};
            InputStream inputStream = TypeReference.class
                    .getResourceAsStream("/json/input.json");
            try {
                List<Input> input = mapper.readValue(inputStream,typeReference);
                inputService.save(input);
                System.out.println("Data Saved!");
                log.info("Dataset imported into relational database");
            } catch (IOException e){
                System.out.println("Unable to save details: " + e.getMessage());
                log.error("Unable to save details.." + e.getMessage());
            }
        };
    }
}
