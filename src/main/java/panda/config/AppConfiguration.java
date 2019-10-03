package panda.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.Validator;

public class AppConfiguration {

    @Produces
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("panda").createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
