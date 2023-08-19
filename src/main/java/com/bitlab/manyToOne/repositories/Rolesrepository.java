package com.bitlab.manyToOne.repositories;

import com.bitlab.manyToOne.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rolesrepository extends JpaRepository<Roles,Long> {
}

