package ro.ubb.catalog.core.service;


import org.springframework.data.domain.Page;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.Grade;

import java.util.List;
import java.util.Set;

public interface GradeServiceInterface {
    void assign(Grade g) throws MyException;

    void grade(Long id, int grade) throws MyException;

    void updateGrade(Long id, int grade) throws MyException;

    Grade getGradeById(Long id) throws MyException;

    Set<Grade> getAll();

    void removeGrade(Long id) throws MyException;

    void deleteGradesWithStudent(Long studentId);

    void deleteGradesWithLabProblem(Long labProblemId);

    List<Grade> getAllGradesSorted(String dir, String fields);

    public Page<Grade> pageGrades(int pageSize, int pageNumber);

}
