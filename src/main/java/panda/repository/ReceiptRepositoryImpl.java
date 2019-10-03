package panda.repository;

import panda.domain.entities.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {

    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public Receipt save(Receipt entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public Receipt findById(String id) {
        return this.entityManager.createQuery("SELECT r FROM Receipt AS r where r.id = :id", Receipt.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Long size() {
        return null;
    }
}
