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
public class House extends AbstractEntity {
    private Integer id;
    private Person owner;
    private String location;
    private HouseSize size;
    private String ipAddress;

    /**
     * @author phoenix
     */
    public enum HouseSize {
        SMALL,
        MEDIUM,
        LARGE
    }
}
