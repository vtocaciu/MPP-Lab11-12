package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class GradeDto extends BaseDto {
    private Long idStudent;
    private Long idLabProblem;
    private int grade;
}
