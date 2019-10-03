package panda.repository;

import panda.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("SELECT u FROM User AS u", User.class)
                .getResultList();
    }

    @Override
    public User findById(String id) {
        return this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            User user = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return (Long) this.entityManager.createQuery("SELECT count(u) FROM User AS u").getSingleResult();

    }

    @Override
    public User findByUsername(String username) {
        try {
            User user = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
