package me.phx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * @author phoenix
 */
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Alias("person")
public class Person extends AbstractEntity {
    private Integer id;      // when return person as json this field will be ignoring

    @NonNull private String name;
    @NonNull private Integer age;

    private Date createdDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Device> devices;  // ignoring this field if it is null
}
