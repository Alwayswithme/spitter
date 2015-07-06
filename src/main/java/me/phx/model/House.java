package me.phx.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author phoenix
 */
@NoArgsConstructor
@Data
@Alias("house")
public class House implements Serializable {
    private int id;
    private Person owner;
    private String location;
    private HouseSize size;
    private String ipAddress;

}
