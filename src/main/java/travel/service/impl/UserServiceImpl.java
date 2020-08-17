package travel.service.impl;

import org.apache.catalina.security.SecurityUtil;
import travel.entity.Role;
import travel.entity.User;
import travel.model.request.UserRequest;
import travel.repository.RoleRepository;
import travel.repository.UserRepository;
import travel.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void insert(UserRequest userRequest) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userRequest, user);

            Set<Role> roles = new HashSet<Role>();
            for (Long id : userRequest.getIds()) {
                Role role = roleRepository.getOne(id);
                roles.add(role);
            }
            user.setRoles(roles);
            user.setCreatedDate(new Date());
            user.setCreatedBy(userRequest.getCreatedBy());
            userRepository.save(user);
        }catch (NullPointerException e) {
            System.out.println("ccaca " + e);
        }

    }

    @Override
    public void update(UserRequest userRequest,long id) {
        User user = userRepository.getOne(id);
        BeanUtils.copyProperties(userRequest, user);
//        user.setUserName(userRepository.findUserNameById(userRequest.getId()).getUserName());
//        user.setCreatedDate(userRepository.findUserNameById(userRequest.getId()).getCreatedDate());
            Set<Role> roles = new HashSet<>();
            for (Long ids : userRequest.getIds()) {
                Role role = roleRepository.getOne(ids);
                roles.add(role);
            }
            user.setRoles(roles);

            user.setModifiedDate(new Date());
            user.setModifiedBy(userRequest.getModifiedBy());
            userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.getOne(id);
        for (Role role : user.getRoles()) {
            role.getUsers().remove(user);
        }
        userRepository.delete(user);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        return userRepository.findUserByUserNameAndPassword(userName,password);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAllByProperties(Date fromDate, Date toDate, String fullName, String userName) {
        List<User> users=userRepository.findAllByProperties(fromDate,toDate,fullName,userName);
        return users;
    }
}
