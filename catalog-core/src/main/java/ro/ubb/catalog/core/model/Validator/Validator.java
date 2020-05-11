package ro.ubb.catalog.core.model.Validator;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface Validator<T> {
    void validate(T object) throws ValidatorException;
}
