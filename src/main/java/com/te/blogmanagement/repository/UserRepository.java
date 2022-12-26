package com.te.blogmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.te.blogmanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByMobile(String username);

	void save(MultipartFile file);

}
