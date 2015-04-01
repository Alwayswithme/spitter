package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author phoenix
 */
@Data
public class Person {
    @JsonIgnore
    private int id;      // when return person as json this field will be ignoring

    private String name;
    private int age;
}
