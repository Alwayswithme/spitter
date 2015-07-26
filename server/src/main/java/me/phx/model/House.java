package me.phx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author phoenix
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class House extends AbstractEntity {
    private Integer id;

    private Integer ownerId;

    private Size size;

    private String location;

    private String ipAddress;

    private Person owner;

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }
}
