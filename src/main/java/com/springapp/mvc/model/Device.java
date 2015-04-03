package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author phoenix
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {
    enum Type {
        PC,
        LAPTOP,
        CELLPHONE
    }
    private int id;
    private Person owner;
    private String name;
    private Type type;
}
