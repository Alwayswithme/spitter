package me.phx.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author phoenix
 */
@NoArgsConstructor
@Data
public class House implements Serializable {
    private int id;
    private Person owner;
    private HouseSize size;
    private String location;

}
