package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class LabProblem extends BaseEntity<Long> {
    private String problemStatement;
    private int labNumber;

}
