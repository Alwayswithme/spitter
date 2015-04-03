package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Device> devices;  // ignoring this field if it is null
}
