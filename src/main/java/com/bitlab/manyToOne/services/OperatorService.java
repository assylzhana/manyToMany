package com.bitlab.manyToOne.services;

import com.bitlab.manyToOne.models.Operator;
import com.bitlab.manyToOne.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    public List<Operator> getOperators(){
        return operatorRepository.findAll();
    }


    public List<Operator> getOperatorById(List<Long> selectedOperatorIds) {
        return operatorRepository.findById(selectedOperatorIds);
    }
}
