package com.te.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogmanagement.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

}
