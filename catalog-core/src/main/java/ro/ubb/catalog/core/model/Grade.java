package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Grade extends BaseEntity<Long> {
    private Long idStudent;
    private Long idLabProblem;
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
