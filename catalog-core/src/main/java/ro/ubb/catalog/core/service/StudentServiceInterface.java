package ro.ubb.catalog.core.service;


import org.springframework.data.domain.Page;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StudentServiceInterface {
    void add(Student s) throws MyException;

    void update(Student s) throws MyException;

    void delete(Long id) throws MyException;

    Set<Student> getAll();

    Set<Student> filterStudentsByName(String name);

    Set<Student> filterStudentsByGroup(int group);

    Student getStudentById(Long id) throws MyException;

    List<Student> getAllStudentsSorted(String dir, String fields);

    Page<Student> pageStudents(int pageSize, int pageNumber);
}
