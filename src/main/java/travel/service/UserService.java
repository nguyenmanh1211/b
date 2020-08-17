package travel.service;

import travel.entity.User;
import travel.model.request.UserRequest;
import travel.repository.UserRepository;

import java.util.Date;
import java.util.List;


public interface UserService {
    void insert(UserRequest userRequest);

    void update(UserRequest userRequest,long id);

    void delete(Long id);

    User findUserByUserNameAndPassword(String userName, String password);

    User findUserByUserName(String userName);

    List<User> findAll();

    User findUserById(Long id);
    List<User> findAllByProperties(Date fromDate, Date toDate, String fullName, String userName);
}
