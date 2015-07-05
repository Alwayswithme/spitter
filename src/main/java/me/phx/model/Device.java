package me.phx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author phoenix
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device implements Serializable {
    private int id;
    private Person owner;
    private String name;
    private DeviceType type;

}
