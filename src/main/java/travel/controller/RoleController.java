package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.entity.Role;
import travel.service.RoleService;

import java.util.List;

@RestController
public class RoleController {
    private RoleService roleService;

//    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }


//    @GetMapping("/role")
//    public ResponseEntity<List<Role>> findOne(){
//        return ResponseEntity.ok(roleService.findOne());
}
