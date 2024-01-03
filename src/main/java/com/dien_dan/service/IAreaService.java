package com.dien_dan.service;

import com.dien_dan.model.Area;

import java.util.List;

public interface IAreaService {
    Area save(Area area);
    Area getById(long id);
    List<Area> getAll();

}
