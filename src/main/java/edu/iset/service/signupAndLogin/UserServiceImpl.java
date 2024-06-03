package edu.iset.service.signupAndLogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.UserEntity;
import edu.iset.dto.User;
import edu.iset.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    //save new registered user in database
     public UserEntity registerUser(User user){
         //encrypt password
         BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
         String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
         UserEntity userEntity = new UserEntity();

         userEntity.setFirstName(user.getFirstName());
         userEntity.setLastName(user.getLastName());
         userEntity.setPosition(user.getPosition());
         userEntity.setNicNumber(user.getNicNumber());
         userEntity.setEmail(user.getEmail());
         userEntity.setPassword(encryptedPassword);
         userEntity.setContact(user.getContact());

         userRepository.save(userEntity);
         return userEntity;
     }

    //Search user by position
    @Override
    public Iterable<UserEntity> retriveUserByPosition(String position){
     return userRepository.findAllByPosition(position);
    }

    //user authenticate
    @Override
    public String authenticateUser(User user) throws UsernameNotFoundException{
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<UserEntity> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            UserEntity dbUser = optionalUser.get();
             if(bCryptPasswordEncoder.matches(user.getPassword(),dbUser.getPassword())){
                return "true";
            }else {
                return "false";
            }
        }
        throw new UsernameNotFoundException("No user is not found for this username!!!");
    }
}
