package com.springapp.mvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author phoenix
 */
@NoArgsConstructor
@Data
public class House {
    enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }
    private int id;
    private Person owner;
    private Size size;
    private String location;
}
