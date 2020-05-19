package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.LabProblem;
import ro.ubb.catalog.core.service.LabProblemsServiceInterface;
import ro.ubb.catalog.web.converter.LabProblemsConverter;
import ro.ubb.catalog.web.dto.LabProblemDto;
import ro.ubb.catalog.web.dto.StudentDto;

import java.util.List;


@RestController
public class LabProblemsController {
    public static final Logger log= LoggerFactory.getLogger(LabProblemsController.class);

    @Autowired
    private LabProblemsServiceInterface labProblemService;

    @Autowired
    private LabProblemsConverter labProblemConverter;

    @RequestMapping(value = "/lab", method = RequestMethod.GET)
    List<LabProblemDto> getLabProblems() {
        log.trace("getLabProblems - method");
        return (labProblemConverter.convertModelsToDtos(labProblemService.getAll()));

    }

    @RequestMapping(value = "/lab", method = RequestMethod.POST)
    void addLabProblem(@RequestBody LabProblemDto labProblemDto){
        log.trace("addLabProblem - method, l={}", labProblemDto);

        try {
            labProblemService.add(labProblemConverter.convertDtoToModel(labProblemDto));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        };
    }

    @RequestMapping(value = "/lab/{id}", method = RequestMethod.PUT)
    void updateLabProblem(@PathVariable Long id, @RequestBody LabProblemDto labProblemDto){
        log.trace("updateLabProblem - method, l={}", labProblemDto);
        try {
            LabProblem l = labProblemConverter.convertDtoToModel(labProblemDto);
            l.setId(id);
            labProblemService.update(l);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        };
    }

    @RequestMapping(value = "/lab/{id}", method = RequestMethod.DELETE)
    void deleteLabProblem(@PathVariable Long id){
        log.trace("deleteLabProblem - method, id={}", id);
        try {
            labProblemService.delete(id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        };
    }

    @RequestMapping(value = "/lab/stmtFilter/{stmt}", method = RequestMethod.GET)
    List<LabProblemDto> filterByStmt(@PathVariable String stmt){
        log.trace("filterByStmt - method, stmt={}", stmt);
        return (labProblemConverter.convertModelsToDtos(labProblemService.filterProblemsByStatement(stmt)));
    }

    @RequestMapping(value = "/lab/numberFilter/{number}", method = RequestMethod.GET)
    List<LabProblemDto> filterByNumber(@PathVariable int number){
        log.trace("filterByNumber - method, number={}", number);
        return (labProblemConverter.convertModelsToDtos(labProblemService.filterProblemsByLabNumber(number)));
    }

    @RequestMapping(value = "/lab/sort/{dir}/{fields}", method = RequestMethod.GET)
    List<LabProblemDto> getAllSorted(@PathVariable String dir, @PathVariable String fields){
        log.trace("getAllSorted - method");
        return (labProblemConverter.convertModelsToDtos(labProblemService.getAllLabSorted(dir, fields)));
    }

    @RequestMapping(value ="/lab/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    List<LabProblemDto> pageLabs(@PathVariable int pageSize, @PathVariable int pageNumber){
        log.trace("page Labs - method");
        return labProblemConverter.convertModelsToDtoPage(labProblemService.pageLabs(pageSize, pageNumber));
    }
}
