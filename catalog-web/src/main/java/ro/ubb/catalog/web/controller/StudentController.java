package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.core.service.StudentServiceInterface;
import ro.ubb.catalog.web.converter.StudentConverter;
import ro.ubb.catalog.web.dto.StudentDto;

import java.util.List;

@RestController
public class StudentController {
    public static final Logger log= LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceInterface studentService;

    @Autowired
    private StudentConverter studentConverter;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    List<StudentDto> getStudents() {
        log.trace("getStudents - method");
        return (studentConverter
                .convertModelsToDtos(studentService.getAll()));

    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    void addStudent(@RequestBody StudentDto studentDto){
        log.trace("addStudent - method, s={}", studentDto);

        try {
            studentService.add(studentConverter.convertDtoToModel(studentDto));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        };
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    void updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        log.trace("updateStudent - method, s={}", studentDto);
        try {
            Student s = studentConverter.convertDtoToModel(studentDto);
            s.setId(id);
            studentService.update(s);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        };
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    void deleteStudent(@PathVariable Long id){
        log.trace("deleteStudent - method, id={}", id);
        try {
            studentService.delete(id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        };
    }

    @RequestMapping(value = "/students/nameFilter/{name}", method = RequestMethod.GET)
    List<StudentDto> filterByName(@PathVariable String name){
        log.trace("filterByName - method, name={}", name);
        return (studentConverter.convertModelsToDtos(studentService.filterStudentsByName(name)));
    }

    @RequestMapping(value = "/students/groupFilter/{group}", method = RequestMethod.GET)
    List<StudentDto> filterByName(@PathVariable int group){
        log.trace("filterByGroup - method, group={}", group);
        return (studentConverter.convertModelsToDtos(studentService.filterStudentsByGroup(group)));
    }

    @RequestMapping(value = "/students/sort/{dir}/{fields}", method = RequestMethod.GET)
    List<StudentDto> getAllSorted(@PathVariable String dir, @PathVariable String fields){
        log.trace("getAllSorted - method");
        return (studentConverter.convertModelsToDtos(studentService.getAllStudentsSorted(dir, fields)));
    }

    @RequestMapping(value ="/students/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    List<StudentDto> pageStudents(@PathVariable int pageSize, @PathVariable int pageNumber){
        log.trace("page Students - method");
        return studentConverter.convertModelsToDtoPage(studentService.pageStudents(pageSize, pageNumber));
    }

}
