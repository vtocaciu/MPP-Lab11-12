package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity<Long> {
    private String studentName;
    private int studentGroup;

    @Override
    public String toString() {
        return "Student{" +
                ", studentName='" + studentName + '\'' +
                ", studentGroup=" + studentGroup +
                "} " + super.toString();
    }
}
