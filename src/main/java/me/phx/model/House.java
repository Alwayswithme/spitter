package me.phx.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author phoenix
 */
@NoArgsConstructor
@Data
@Alias("house")
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
