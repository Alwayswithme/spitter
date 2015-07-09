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
public class Device extends AbstractEntity {
    private Integer id;

    private Integer ownerId;

    private String name;

    private DeviceType deviceType;

    /**
     * @author phoenix
     */
    public enum DeviceType {
        PC,
        LAPTOP,
        CELLPHONE
    }
}
