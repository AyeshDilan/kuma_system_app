package edu.iset.service.signupAndLogin;

import edu.iset.dao.UserEntity;
import edu.iset.dto.User;

public interface UserService {
    UserEntity registerUser(User user);

//    UserEntity retriveUser();

    Iterable<UserEntity> retriveUserByPosition(String position);

    String authenticateUser(User user);
}
