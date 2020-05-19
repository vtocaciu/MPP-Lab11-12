package ro.ubb.catalog.core.model;

import lombok.*;
import org.springframework.lang.NonNull;


import javax.persistence.Entity;
import javax.validation.constraints.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity<Long> {

    @NotNull
    @Size(min=2, max=30)
    private String studentName;

    @NotNull
    @Min(900)
    @Max(1000)
    private int studentGroup;

    @Override
    public String toString() {
        return "Student{" +
                ", studentName='" + studentName + '\'' +
                ", studentGroup=" + studentGroup +
                "} " + super.toString();
    }
}
