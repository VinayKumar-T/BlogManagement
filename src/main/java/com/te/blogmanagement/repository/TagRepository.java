package com.te.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogmanagement.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
