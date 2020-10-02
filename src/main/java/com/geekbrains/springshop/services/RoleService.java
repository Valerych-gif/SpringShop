package com.geekbrains.springshop.services;

import com.geekbrains.springshop.entities.Role;
import com.geekbrains.springshop.entities.User;
import com.geekbrains.springshop.repositories.RoleRepository;
import com.geekbrains.springshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Role> getAllRoles(){
        return (List<Role>) roleRepository.findAll();
    }

    public void addNewRole(Role role){
        roleRepository.save(role);
    }

    public void deleteRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role!=null){
            Collection<User> users = role.getUsers();
            if (users.size()==0){
                roleRepository.delete(role);
            }
        }
    }
}
