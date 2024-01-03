package com.dien_dan.repository;

import com.dien_dan.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface ILocationRepo extends CrudRepository<Location, Long> {
    Location findByAccount_Id(long id);
}
