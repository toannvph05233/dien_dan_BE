package com.dien_dan.service.impl;

import com.dien_dan.model.Area;
import com.dien_dan.repository.IAreaRepo;
import com.dien_dan.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {
    @Autowired
    IAreaRepo iAreaRepo;

    @Override
    public Area save(Area area) {
        return iAreaRepo.save(area);
    }

    @Override
    public Area getById(long id) {
        return iAreaRepo.findById(id).get();
    }

    @Override
    public List<Area> getAll() {
        return (List<Area>) iAreaRepo.findAll();
    }
}
