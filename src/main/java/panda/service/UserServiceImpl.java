package panda.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import panda.domain.entities.User;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {
//        if (this.validator.validate(userServiceModel))
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.setUserRole(user);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserById(UserServiceModel userServiceModel) {
        return this.modelMapper.map(this.userRepository.findById(userServiceModel.getId()), UserServiceModel.class);
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsernameAndPassword(userServiceModel.getUsername(),
                DigestUtils.sha256Hex(userServiceModel.getPassword()));
        if (user == null){
            return null;
        }
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    void setUserRole(User user) {
        user.setRole(this.userRepository.size() == 0 ? "Admin" : "User");
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll() {

        return userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }
}
