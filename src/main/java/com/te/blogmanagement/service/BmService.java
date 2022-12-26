package com.te.blogmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.te.blogmanagement.dto.LoginDto;
import com.te.blogmanagement.dto.PostCommentDto;
import com.te.blogmanagement.dto.PostDto;
import com.te.blogmanagement.dto.TagDto;
import com.te.blogmanagement.dto.UpdatePostCommentDto;
import com.te.blogmanagement.dto.UpdatePostDto;
import com.te.blogmanagement.dto.UserDto;
import com.te.blogmanagement.dto.UserUpdateDto;
import com.te.blogmanagement.entity.Post;

public interface BmService {

	Optional<UserDto> registration(UserDto userDto);

	void postRegistration(Integer id, PostDto postDto);

	Boolean login(LoginDto loginDto);

	Optional<Boolean> updateUser(Integer id, UserUpdateDto userUpdateDto);

	Optional<Boolean> deleteUser(Integer id);

	Optional<UserDto> userData(Integer userid);

	Optional<List<UserDto>> UserDataList();

	Optional<Boolean> updatePost(Integer id, UpdatePostDto updatePostDto);

	Optional<Boolean> deletePost(Integer id);

	Optional<Boolean> commentRegistration(Integer id, PostCommentDto postCommentDto);

	Optional<Boolean> updatePostComment(Integer id, UpdatePostCommentDto updatePostCommentDto);

	Optional<Boolean> deletePostComment(Integer id);

	Optional<PostDto> readPostAndComments(Integer id);

	void uploadFile(MultipartFile file);

	Optional<Boolean> tagRegistration(Integer id, TagDto tagDto);

}
