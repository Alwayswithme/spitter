package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author phoenix
 */
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @JsonIgnore
    private int id;      // when return person as json this field will be ignoring

    @NonNull private String name;
    @NonNull private int age;
}
