package com.bitlab.manyToOne.repositories;

import com.bitlab.manyToOne.models.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
    List<Operator> findByIdIn(List<Long> selectedOperatorIds);
}
