package com.te.blogmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogmanagement.entity.DatabaseFile;

@Repository
public interface DataBaseFileUpload  extends JpaRepository<DatabaseFile, Integer>{

	Optional<DatabaseFile> findById(String fileId);

}
