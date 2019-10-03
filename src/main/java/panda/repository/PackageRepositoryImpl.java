package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {
    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Package save(Package entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Package> findAll() {
        return this.entityManager.createQuery("SELECT p FROM Package AS p", Package.class)
                .getResultList();
    }

    @Override
    public Package findById(String id) {
        return this.entityManager.createQuery("SELECT p FROM Package AS p WHERE p.id = :id", Package.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public Package findByName(String name) {
        return this.entityManager.createQuery("SELECT p FROM Package AS p WHERE p.description = :name", Package.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Package> findAllPackagesByStatus(Status status) {
        return this.entityManager.createQuery("SELECT p FROM Package AS p WHERE p.status = :status", Package.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Package> findAllPackagesByStatus(Status statusOne, Status statusTwo) {
        return this.entityManager.createQuery("SELECT p FROM Package AS p " +
                "WHERE p.status = :statusOne OR p.status = :statusTwo", Package.class)
                .setParameter("statusOne", statusOne)
                .setParameter("statusTwo", statusTwo)
                .getResultList();
    }

    @Override
    public Long size() {
        return (Long) this.entityManager.createQuery("SELECT count(p) FROM Package AS p").getSingleResult();
    }

    @Override
    public void update(Package aPackage) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(aPackage);
        this.entityManager.getTransaction().commit();
    }
}
