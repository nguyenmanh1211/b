package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travel.entity.User;
import travel.model.request.UserRequest;
import travel.model.request.UserRequestFillter;
import travel.repository.UserRepository;
import travel.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@RequestBody UserRequest userRequest){
        userService.insert(userRequest);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUserName(@RequestParam String userName)
    {
//        return new ResponseEntity<>(userService.findUserByUserName(userName), HttpStatus.OK);
        return ResponseEntity.ok(userService.findUserByUserName(userName));
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        List<User> users= userService.findAll();
        return users;
    }
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UserRequest userRequest){
        User user= userService.findUserByUserName(userRequest.getUserName());
        long id=user.getId();
        userService.update(userRequest,id);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping("/search")
//    public List<User> getUserByProperties(@RequestBody UserRequestFillter userRequestFillter)
//    {
//        List<User> users=userService.findAllByProperties(userRequestFillter.getFromDate(),userRequestFillter.getToDate()
//                ,userRequestFillter.getFullName(),userRequestFillter.getUserName());
//        return users;
//    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<User> getUserByProperties(@RequestParam Date fromDate, @RequestParam Date toDate
            ,@RequestParam String fullName,@RequestParam String userName) {
        List<User> list=userService.findAllByProperties(fromDate,toDate,fullName,userName);
        return list;
    }

}
