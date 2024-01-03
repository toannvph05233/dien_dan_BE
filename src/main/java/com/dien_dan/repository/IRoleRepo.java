package com.dien_dan.repository;

import com.dien_dan.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoleRepo extends PagingAndSortingRepository<Role, Integer> {
}
