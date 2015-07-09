package me.phx.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author phoenix
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class House extends AbstractEntity {
    private Integer id;

    private Integer ownerId;

    private HouseSize houseSize;

    private String location;

    private String ipAddress;


    public enum HouseSize {
        SMALL,
        MEDIUM,
        LARGE
    }
}
