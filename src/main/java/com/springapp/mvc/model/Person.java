package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by phoenix on 3/31/15.
 * @author phoenix
 */
@Data
public class Person {
    @JsonIgnore
    private int id;      // ignoring this field

    private String name;
    private int age;
}
