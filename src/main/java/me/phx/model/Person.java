package me.phx.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author phoenix
 */
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@Alias("person")
public class Person extends AbstractEntity {
    private Integer id;      // when return person as json this field will be ignoring

    @NonNull private String name;
    @NonNull private Integer age;

    private LocalDateTime createdDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Device> devices;  // ignoring this field if it is null
}
