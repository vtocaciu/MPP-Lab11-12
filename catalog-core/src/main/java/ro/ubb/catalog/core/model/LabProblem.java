package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class LabProblem extends BaseEntity<Long> {
    @NotNull
    @Size(min=2, max = 25)
    private String problemStatement;

    @NotNull
    @Min(1)
    @Max(14)
    private int labNumber;

    @Override
    public String toString() {
        return "LabProblem{" +
                ", problemStatement='" + problemStatement + '\'' +
                ", labNumber=" + labNumber +
                "} " + super.toString();
    }
}
