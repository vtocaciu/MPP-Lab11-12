package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Grade;
import ro.ubb.catalog.web.dto.GradeDto;

@Component
public class GradesConverter extends BaseConverter<Grade, GradeDto> {
    @Override
    public Grade convertDtoToModel(GradeDto dto) {
        Grade g = Grade.builder()
                .idLabProblem(dto.getIdLabProblem())
                .idStudent(dto.getIdStudent())
                .grade(dto.getGrade())
                .build();
        g.setId(dto.getId());
        return g;
    }

    @Override
    public GradeDto convertModelToDto(Grade grade) {
        GradeDto g = GradeDto.builder()
                .idLabProblem(grade.getIdLabProblem())
                .idStudent(grade.getIdStudent())
                .grade(grade.getGrade())
                .build();
        g.setId(grade.getId());
        return g;

    }
}
