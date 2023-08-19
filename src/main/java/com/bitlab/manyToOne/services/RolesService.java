package com.bitlab.manyToOne.services;

import com.bitlab.manyToOne.models.Roles;
import com.bitlab.manyToOne.models.User;
import com.bitlab.manyToOne.repositories.Rolesrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolesService {
    @Autowired
    private Rolesrepository rolesrepository;

    public List<Roles> getRoles(){
        return rolesrepository.findAll();
    }

    public Roles getRoleById(Long roleId) {
        return rolesrepository.findById(roleId).orElse(null);
    }

}
