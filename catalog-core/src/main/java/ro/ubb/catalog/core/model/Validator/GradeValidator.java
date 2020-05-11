package ro.ubb.catalog.core.model.Validator;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Grade;

@Component
public class GradeValidator implements Validator<Grade> {

    @Override
    public void validate(Grade object) throws ValidatorException {
        StringBuilder s = new StringBuilder();
        if(object.getGrade() < 0 || object.getGrade() > 10)
            s.append("Invalid grade");
        if(s.length()>0)
            throw new ValidatorException(s.toString());
    }
}
