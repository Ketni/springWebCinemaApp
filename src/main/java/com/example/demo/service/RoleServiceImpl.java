package com.example.demo.service;


import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean hasRole(String name) {
        if (roleRepository.findByName(name)!=null)
            return true;
        return false;
    }
}
