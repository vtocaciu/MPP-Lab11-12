package ro.ubb.catalog.core.model;

import lombok.*;

import javax.enterprise.context.NormalScope;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Grade extends BaseEntity<Long> {
    @NotNull
    private Long idStudent;
    @NotNull
    private Long idLabProblem;
    @NotNull
    @Min(0)
    @Max(10)
    private int grade;

    @Override
    public String toString() {
        return "LabProblem{" +
                ", idStudent='" + idStudent + '\'' +
                ", idLabProblem=" + idLabProblem + '\'' +
                ", grade=" + grade + '\''+
                "} " + super.toString();
    }
}
