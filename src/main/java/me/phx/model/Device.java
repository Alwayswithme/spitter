package me.phx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author phoenix
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("device")
public class Device extends AbstractEntity {
    private Integer id;
    private Person owner;
    private String name;
    private DeviceType type;

    /**
     * @author phoenix
     */
    public enum DeviceType {
        PC,
        LAPTOP,
        CELLPHONE
    }
}
