package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.core.model.Validator.StudentValidator;
import ro.ubb.catalog.core.model.Validator.ValidatorException;
import ro.ubb.catalog.core.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentValidator studentValidator;

    public StudentServiceImpl() {

    }

    @Override
    public void add(Student s) throws MyException {
        log.trace("add - method entered: student={}", s);
        try {
            studentValidator.validate(s);
            log.debug("add - added: s={}", s);
            studentRepo.save(s);
        } catch (ValidatorException | IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        log.trace("add - method finished");
    }

    @Override
    public void update(Student s) throws MyException {
        log.trace("update - method entered: student={}", s);
        try {
            studentValidator.validate(s);
            if (studentRepo.findById(s.getId()).isPresent()) {
                log.debug("update - updated: s={}", s);
                studentRepo.save(s);
            } else throw new MyException("invalid id");
        } catch (ValidatorException | IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        log.trace("update - method finished");
    }

    @Override
    public void delete(Long id) throws MyException {
        log.trace("delete - method entered: id={}", id);
        try {
            if (studentRepo.findById(id).isPresent()) {
                studentRepo.deleteById(id);
                log.debug("delete - deleted: id={}", id);

            } else throw new MyException("invalid id");
        } catch (IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        log.trace("delete - method finished");
    }

    @Override
    public Set<Student> getAll() {
        log.trace("getAll - method entered");
        Iterable<Student> students = studentRepo.findAll();
        log.trace("getAll - method finished");
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *Returns all students whose name contains the given string
     *
     * @param name string
     * @return filteres students
     */
    @Override
    public Set<Student> filterStudentsByName(String name) {
        log.trace("filterByName - method entered: name={}", name);
        Set<Student> all = StreamSupport.stream(studentRepo.findAll().spliterator(), false).
                filter(student -> student.getStudentName().contains(name)).
                collect(Collectors.toSet());
        log.trace("filterByName - method finished");
        return all;
    }

    /**
     * Returns all students in the same group
     * @param group int
     * @return all students
     */
    @Override
    public Set<Student> filterStudentsByGroup(int group) {
        log.trace("filterByName - method entered: group={}", group);
        Set<Student> all = StreamSupport.stream(studentRepo.findAll().spliterator(), false).
                filter(student -> student.getStudentGroup()==group).
                collect(Collectors.toSet());
        log.trace("filterByName - method finished");
        return all;
    }

    @Override
    public Student getStudentById(Long id) throws MyException {
        log.trace("getById - method entered: id={}", id);
        Optional<Student> s = this.studentRepo.findById(id);
        if (!s.isPresent()) {
            throw new MyException("No student with the given id");
        }
        log.trace("getById - method finished");
        return s.get();
    }

    @Override
    public List<Student> getAllStudentsSorted(String dir, String fields) {
        log.trace("getAllSorted - method entered");
//        Sort sort1 = new Sort(Sort.Direction.ASC, "id"); //sort by id
//        Sort sort2 = new Sort(Sort.Direction.ASC, "studentName"); //sort asc by name
//        Sort sort3 = new Sort(Sort.Direction.ASC, "studentGroup"); //sort asc by group
//        Sort sort4 = new Sort(Sort.Direction.DESC, "studentGroup", "studentName"); //sort desc by group and desc by name
//        Sort sort5 = new Sort(Sort.Direction.DESC, "studentGroup").and(new Sort(Sort.Direction.ASC, "studentName")); //sort desc by group and asc by name

        String[] directions = dir.split(",");
        String[] field = fields.split(",");
        Sort sort = null;
        for(int i = 0; i < directions.length; i++)
        {
            if(directions[i].equals("ASC"))
                if(sort == null) {
                    sort = new Sort(Sort.Direction.ASC, field[i]);
                }
                else{
                    sort = sort.and(new Sort(Sort.Direction.ASC, field[i]));
                }
            if(directions[i].equals("DESC"))
                if(sort == null) {
                    sort = new Sort(Sort.Direction.DESC, field[i]);
                }
                else{
                    sort = sort.and(new Sort(Sort.Direction.DESC, field[i]));
                }
        }

        List<Student> all = (List<Student>) studentRepo.findAll(sort);
        log.trace("getAllSorted - method finished");

        return all;
    }

    @Override
    public Page<Student> pageStudents(int pageSize, int pageNumber) {
        log.trace("pageable students");
        Pageable page = PageRequest.of(pageNumber-1, pageSize);
        return studentRepo.findAll(page);
    }


}
