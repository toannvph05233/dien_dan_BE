package com.dien_dan.repository;

import com.dien_dan.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostRepo extends PagingAndSortingRepository<Post, Integer> {
}
