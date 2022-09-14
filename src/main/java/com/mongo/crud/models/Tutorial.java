package com.mongo.crud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tutorials")
public class Tutorial {

    @MongoId
    private String id;

    private String title;
    private String description;
    private boolean published;

}
