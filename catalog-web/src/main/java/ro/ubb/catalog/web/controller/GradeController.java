package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.Grade;
import ro.ubb.catalog.core.service.GradeServiceInterface;
import ro.ubb.catalog.web.converter.GradesConverter;
import ro.ubb.catalog.web.dto.GradeDto;
import ro.ubb.catalog.web.dto.LabProblemDto;

import java.util.List;


@RestController
public class GradeController {

    public static final Logger log= LoggerFactory.getLogger(GradeController.class);

    @Autowired
    private GradeServiceInterface gradeService;

    @Autowired
    private GradesConverter gradesConverter;

    @RequestMapping(value = "/grade", method = RequestMethod.GET)
    List<GradeDto> getAll()
    {
        log.trace("getAll - method");
        return (gradesConverter.convertModelsToDtos(gradeService.getAll()));
    }

    @RequestMapping(value = "/grade/assg", method = RequestMethod.POST)
    void assign(@RequestBody GradeDto grade)
    {
        log.trace("assign - method, g={}", grade);
        Grade g = gradesConverter.convertDtoToModel(grade);
        try {
            gradeService.assign(g);
        } catch (MyException e)
        {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/grade/id={id}&gr={gr}", method = RequestMethod.PUT)
    void grade(@PathVariable("id") Long id, @PathVariable("gr") int grade){
        try {
            log.trace("grade - method, id={}, grade={}",id,grade);
            gradeService.grade(id, grade);
        }catch (MyException e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/grade/update/{id}/{grade}", method = RequestMethod.PUT)
    void updateGrade(@PathVariable("id") Long id, @PathVariable("grade") int grade){
        try {
            log.trace("updateGrade - method, id={}, grade={}", id, grade);
            gradeService.updateGrade(id, grade);
        }catch (MyException e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/grade/{id}", method = RequestMethod.DELETE)
    void removeGrade(@PathVariable Long id){
        try {
            log.trace("removeGrade - method, id={}", id);
            gradeService.removeGrade(id);
        }catch (MyException e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/grade/student/{id}", method = RequestMethod.DELETE)
    void deleteGradesWithStudent(@PathVariable Long id) {
        log.trace("deleting grades for StudentId={}", id);
        gradeService.deleteGradesWithStudent(id);
    }

    @RequestMapping(value = "/grade/lab/{id}", method = RequestMethod.DELETE)
    void deleteGradesWithLabProblem(@PathVariable Long id){
        log.trace("deleting grades for LabProblemId={}", id);
        gradeService.deleteGradesWithLabProblem(id);
    }

    @RequestMapping(value = "/grade/sort/{dir}/{fields}", method = RequestMethod.GET)
    List<GradeDto> getAllGradesSorted(@PathVariable String dir, @PathVariable String fields){
        log.trace("getAllGradesSorted - method");
        return (gradesConverter.convertModelsToDtos(gradeService.getAllGradesSorted(dir,fields)));
    }

    @RequestMapping(value ="/grade/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    List<GradeDto> pageGrades(@PathVariable int pageSize, @PathVariable int pageNumber){
        log.trace("page Labs - method");
        return gradesConverter.convertModelsToDtoPage(gradeService.pageGrades(pageSize, pageNumber));
    }
}
