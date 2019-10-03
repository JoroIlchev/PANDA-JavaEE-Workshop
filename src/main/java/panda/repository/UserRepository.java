package panda.repository;

import panda.domain.entities.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User, String> {
  User findByUsernameAndPassword(String username, String password);
  User findByUsername(String username);
//  List<User> findAll();
}
