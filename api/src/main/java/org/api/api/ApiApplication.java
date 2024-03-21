package org.api.api;

import org.api.api.model.User;
import org.api.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            String email = "parishkar.singh@esko.com";
            User user = new User("parishkar", email);
//			repository.findAllById();
            usingMongoTemplateAndQuery(repository, mongoTemplate, email, user);
            repository.findUserByEmail(email).ifPresentOrElse(s->{
                System.out.println(user+ "Already Exists");
            },()->{
                System.out.println("Inserting user"+ user);
                repository.insert(user);
            });
        };
    }

    private static void usingMongoTemplateAndQuery(UserRepository repository, MongoTemplate mongoTemplate, String email, User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email"));
        List<User> users = mongoTemplate.find(query, User.class);

        if (users.size() > 1) {
            throw new IllegalStateException("Found many students with email " + email);
        }

        if (users.isEmpty()) {

        } else{
            System.out.println(user +"already exists");
        }
    }
}
