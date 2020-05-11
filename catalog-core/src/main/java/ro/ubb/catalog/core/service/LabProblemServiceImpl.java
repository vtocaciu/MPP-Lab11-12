package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Exception.MyException;
import ro.ubb.catalog.core.model.LabProblem;
import ro.ubb.catalog.core.model.Validator.LabProblemsValidator;
import ro.ubb.catalog.core.model.Validator.ValidatorException;
import ro.ubb.catalog.core.repository.LabProblemsRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LabProblemServiceImpl implements LabProblemsServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(LabProblemServiceImpl.class);

    @Autowired
    private LabProblemsRepository labProblemsRepo;

    @Autowired
    private LabProblemsValidator validator;

    public LabProblemServiceImpl() {

    }

    @Override
    public void add(LabProblem l) throws MyException {
        log.trace("add - method entered: labproblem={}", l);
        try {
            validator.validate(l);
            log.debug("add - added: l={}", l);
            labProblemsRepo.save(l);
        } catch (ValidatorException | IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        log.trace("add - method finished");
    }

    @Override
    public void update(LabProblem l) throws MyException {
        log.trace("update - method entered: labproblem={}", l);

        try {
            validator.validate(l);
            if (labProblemsRepo.findById(l.getId()).isPresent()) {
                log.debug("update - updated: l={}", l);
                labProblemsRepo.save(l);
            } else throw new MyException("invalid id!");
        } catch (ValidatorException | IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        log.trace("update - method finished");
    }

    @Override
    public void delete(Long id) throws MyException {
        log.trace("delete - method entered: id={}", id);
        try {
            if (labProblemsRepo.findById(id).isPresent()) {
                log.debug("delete - deleted: id={}", id);
                labProblemsRepo.deleteById(id);
            } else throw new MyException("invalid id!");
        } catch (IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }
        log.trace("delete - method finished");

    }

    @Override
    public Set<LabProblem> getAll() {
        log.trace("getAll - method entered");
        Iterable<LabProblem> labProblems = labProblemsRepo.findAll();
        log.trace("getAll - method finished");
        return StreamSupport.stream(labProblems.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all lab problems whose statement contains the given string.
     * @param stmt string
     * @return filtered students
     */

    @Override
    public Set<LabProblem> filterProblemsByStatement(String stmt) {
        log.trace("filterByStmt - method entered: stmt={}", stmt);
        log.trace("filterByStmt - method finished");
        return StreamSupport.stream(labProblemsRepo.findAll().spliterator(), false).
                filter(labProblem -> labProblem.getProblemStatement().contains(stmt)).
                collect(Collectors.toSet());
    }

    /**
     * Return all problems that are in the same lab
     * @param number int
     * @return filtered labs
     */
    @Override
    public Set<LabProblem> filterProblemsByLabNumber(int number) {
        log.trace("filterByLabNr - method entered: number={}", number);
        log.trace("filterByLabNr - method finished");
        return StreamSupport.stream(labProblemsRepo.findAll().spliterator(), false).
                filter(labProblem -> labProblem.getLabNumber()==number).
                collect(Collectors.toSet());
    }

    @Override
    public LabProblem getLabProblemById(Long id) throws MyException {
        log.trace("getById - method entered: id={}", id);
        try {
            Optional<LabProblem> lab = this.labProblemsRepo.findById(id);
            if (!lab.isPresent())
                throw new IllegalArgumentException("No lab problem with the given id");
            log.trace("getById - method finished");
            return lab.get();
        } catch (IllegalArgumentException e) {
            throw new MyException(e.getMessage());
        }

    }
    @Override
    public List<LabProblem> getAllLabSorted() {
        Sort sort1 = new Sort(Sort.Direction.ASC, "id"); //sort by id
        Sort sort2 = new Sort(Sort.Direction.ASC, "problemStatement"); //sort asc by problemStatement
        Sort sort3 = new Sort(Sort.Direction.ASC, "labNumber"); //sort asc by labNumber
        Sort sort4 = new Sort(Sort.Direction.DESC, "labNumber", "problemStatement"); //sort desc by labNumber and desc by problemStatement
        Sort sort5 = new Sort(Sort.Direction.DESC, "labNumber").and(new Sort(Sort.Direction.ASC, "problemStatement")); //sort desc by labNumber and asc by problemStatement


        List<LabProblem> all = (List<LabProblem>) labProblemsRepo.findAll(sort2);
        return all;
    }
}
