package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.Grade;
import ro.ubb.catalog.core.model.Validator.GradeValidator;
import ro.ubb.catalog.core.model.Validator.ValidatorException;
import ro.ubb.catalog.core.repository.GradesRepository;
import ro.ubb.catalog.core.repository.LabProblemsRepository;
import ro.ubb.catalog.core.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GradeServiceImpl implements GradeServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(GradeServiceImpl.class);

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private LabProblemsRepository labProblemsRepo;
    @Autowired
    private GradesRepository gradesRepo;

    @Autowired
    private GradeValidator gradeValidator;

    public GradeServiceImpl() {

    }

    @Override
    public void assign(Grade g) throws MyException {
        log.trace("assign - method entered: g={}", g);
        if (!studentRepo.findById(g.getIdStudent()).isPresent())
            throw new MyException("invalid id for student");
        if (!labProblemsRepo.findById(g.getIdLabProblem()).isPresent())
            throw new MyException("invalid id for lab problem");
        try {
            gradeValidator.validate(g);
            log.debug("assign - assigned: g={}", g);
            this.gradesRepo.save(g);
            log.trace("assign - method ended");
        } catch (IllegalArgumentException | ValidatorException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void grade(Long id, int grade) throws MyException {
        log.trace("grade - method entered: id={}, grade={}", id, grade);

        Grade gr = getGradeById(id);
        Long student = gr.getIdStudent();
        Long labProblem = gr.getIdLabProblem();
        Grade g = new Grade(student, labProblem, grade);
        g.setId(id);
        try {
            gradeValidator.validate(g);
        } catch (ValidatorException e) {
            throw new MyException(e.getMessage());
        }
        System.out.println(gr.getGrade());
        if (gr.getGrade() != 0)
            throw new MyException("Assignment already graded. Try updating the grade.");
        this.gradesRepo.save(g);
        log.trace("grade - method finished");

    }

    @Override
    public void updateGrade(Long id, int grade) throws MyException {
        log.trace("update - method entered: id={}, grade={}", id, grade);
        Grade gr = getGradeById(id);
        Long student = gr.getIdStudent();
        Long labProblem = gr.getIdLabProblem();
        Grade g = new Grade(student, labProblem, grade);
        g.setId(id);
        try {
            gradeValidator.validate(g);
        } catch (ValidatorException e) {
            throw new MyException(e.getMessage());
        }
        this.gradesRepo.save(g);
        log.trace("update - method finished");
    }

    @Override
    public Grade getGradeById(Long id) throws MyException {
        Optional<Grade> g = this.gradesRepo.findById(id);
        try {
            if (!g.isPresent())
                throw new IllegalArgumentException("id doesn't exist");
        } catch (IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        return g.get();
    }

    @Override
    public Set<Grade> getAll() {
        log.trace("getAll - method entered");
        Iterable<Grade> grades = gradesRepo.findAll();
        return StreamSupport.stream(grades.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void removeGrade(Long id) throws MyException {
        log.trace("removeGrade - method, id={}", id);
        try {
            this.gradesRepo.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void deleteGradesWithStudent(Long studentId) {
        List<Grade> gradesToDelete = this.getAll().stream().filter(grade -> grade.getIdStudent().equals(studentId)).collect(Collectors.toList());
        gradesToDelete.forEach(grade -> gradesRepo.deleteById(grade.getId()));
    }

    @Override
    public void deleteGradesWithLabProblem(Long labProblemId) {
        List<Grade> gradesToDelete = this.getAll().stream().filter(grade -> grade.getIdLabProblem().equals(labProblemId)).collect(Collectors.toList());
        gradesToDelete.forEach(grade -> gradesRepo.deleteById(grade.getId()));

    }


    @Override
    public List<Grade> getAllGradesSorted() {
        log.trace("getAllGradesSorted - method entered");
        Sort sort1 = new Sort(Sort.Direction.ASC, "id"); //sort by id
        Sort sort2 = new Sort(Sort.Direction.ASC, "idStudent"); //sort asc by idStudent
        Sort sort3 = new Sort(Sort.Direction.ASC, "idLabProblem"); //sort asc by idLabProblem
        Sort sort4 = new Sort(Sort.Direction.ASC, "grade"); //sort asc by grade
        Sort sort5 = new Sort(Sort.Direction.DESC, "idStudent", "grade"); //sort idStudent by group and desc by grade
        Sort sort6 = new Sort(Sort.Direction.DESC, "idLabProblem").and(new Sort(Sort.Direction.ASC, "grade")); //sort desc by idLabProblem and asc by grade

        List<Grade> all = (List<Grade>) gradesRepo.findAll(sort1);
        return all;
    }

}
