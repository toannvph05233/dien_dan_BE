package com.dien_dan.service.impl;

import com.dien_dan.model.Location;
import com.dien_dan.repository.ILocationRepo;
import com.dien_dan.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements ILocationService {
    @Autowired
    ILocationRepo iLocationRepo;


    @Override
    public Location save(Location location) {
        return iLocationRepo.save(location);
    }

    @Override
    public Location findByIdUser(long id) {
        return iLocationRepo.findByAccount_Id(id);
    }
}
