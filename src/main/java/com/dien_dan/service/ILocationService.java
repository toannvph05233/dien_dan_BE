package com.dien_dan.service;

import com.dien_dan.model.Location;

public interface ILocationService {
    Location save(Location location);
    Location findByIdUser(long id);
}
