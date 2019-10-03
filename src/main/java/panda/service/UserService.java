package panda.service;

import panda.domain.models.serviceModels.UserServiceModel;

import java.util.List;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);
    UserServiceModel findUserById(UserServiceModel userServiceModel);
    UserServiceModel loginUser(UserServiceModel userServiceModel);
    UserServiceModel findByUsername(String username);
    List<UserServiceModel> findAll();
}
