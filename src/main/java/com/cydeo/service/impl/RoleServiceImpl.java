package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractMapServiceDB<Long,RoleDTO> implements RoleService {

    @Override
    public void save(RoleDTO role) {
        super.save(role.getId(), role);
    }

    @Override
    public List<RoleDTO> readAll() {
        return super.readAll();
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(RoleDTO role) {
        super.update(role.getId(), role);
    }
}
