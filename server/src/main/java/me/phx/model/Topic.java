package me.phx.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Alias("Topic")
public class Topic extends AbstractEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topics.title
     *
     * @mbggenerated
     */
    private String title;

}