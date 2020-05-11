package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class LabProblem extends BaseEntity<Long> {
    private String problemStatement;
    private int labNumber;

    @Override
    public String toString() {
        return "LabProblem{" +
                ", problemStatement='" + problemStatement + '\'' +
                ", labNumber=" + labNumber +
                "} " + super.toString();
    }
}
