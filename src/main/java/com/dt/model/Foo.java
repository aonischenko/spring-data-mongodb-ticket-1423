package com.dt.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "foo")
public class Foo implements Serializable {

    @Id
    private ObjectId id;

    private Bar bar;

}
