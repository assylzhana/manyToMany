package com.bitlab.manyToOne.services;

import com.bitlab.manyToOne.models.Roles;
import com.bitlab.manyToOne.models.User;
import com.bitlab.manyToOne.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesService roleService;

    public User addUser(User user){
        return userRepository.save(user);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Roles role = roleService.getRoleById(roleId);
        if (user != null && role != null) {
            Set<Roles> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
    public void deleteRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Roles role = roleService.getRoleById(roleId);
        if (user != null && role != null) {
            Set<Roles> roles = user.getRoles();
            roles.remove(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}
