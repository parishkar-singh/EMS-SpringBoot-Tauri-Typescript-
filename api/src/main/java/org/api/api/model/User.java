package org.api.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
