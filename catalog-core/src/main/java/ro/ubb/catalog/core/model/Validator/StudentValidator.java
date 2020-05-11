package ro.ubb.catalog.core.model.Validator;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Student;


@Component
public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student object) throws ValidatorException {
        StringBuilder s = new StringBuilder();
        if(object.getStudentGroup() < 0)
            s.append("Invalid group number");
        if(object.getStudentName().equals(""))
            s.append("Invalid student name");
        if(s.length()!=0)
            throw new ValidatorException(s.toString());

    }
}
