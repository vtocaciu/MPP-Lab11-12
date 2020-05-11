package ro.ubb.catalog.core.model.Validator;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.LabProblem;

@Component
public class LabProblemsValidator implements Validator<LabProblem> {
    @Override
    public void validate(LabProblem object) throws ValidatorException {
        StringBuilder s = new StringBuilder();
        if(object.getLabNumber() < 0)
            s.append("Invalid problem number");
        if(object.getProblemStatement().equals(""))
            s.append("Invalid problem statement");
        if(s.length()>0)
            throw new ValidatorException(s.toString());
    }
}
