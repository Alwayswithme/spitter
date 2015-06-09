package me.phx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author phoenix
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("device")
public class Device implements Serializable {
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
