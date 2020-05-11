package ro.ubb.catalog.core.service;



import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.LabProblem;

import java.util.List;
import java.util.Set;

public interface LabProblemsServiceInterface {
    void add(LabProblem l) throws MyException;

    void update(LabProblem l) throws MyException;

    void delete(Long id) throws MyException;

    Set<LabProblem> getAll();

    Set<LabProblem> filterProblemsByStatement(String stmt);

    Set<LabProblem> filterProblemsByLabNumber(int number);

    LabProblem getLabProblemById(Long id) throws MyException;

    List<LabProblem> getAllLabSorted();
}
