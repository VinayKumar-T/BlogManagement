package com.te.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogmanagement.entity.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, String>{

}
