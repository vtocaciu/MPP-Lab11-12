package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.LabProblem;
import ro.ubb.catalog.web.dto.LabProblemDto;

@Component
public class LabProblemsConverter extends BaseConverter<LabProblem, LabProblemDto> {
    @Override
    public LabProblem convertDtoToModel(LabProblemDto dto) {
        LabProblem l = new LabProblem(dto.getProblemStatement(), dto.getLabNumber());
        l.setId(dto.getId());
        return l;
    }

    @Override
    public LabProblemDto convertModelToDto(LabProblem labProblem) {
        LabProblemDto l = LabProblemDto.builder()
                .labNumber(labProblem.getLabNumber())
                .problemStatement(labProblem.getProblemStatement())
                .build();
        l.setId(labProblem.getId());
        return l;
    }
}
