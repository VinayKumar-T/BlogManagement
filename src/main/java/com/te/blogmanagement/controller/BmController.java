package com.te.blogmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.blogmanagement.customexception.DeletePostCommentUnsuccessfullException;
import com.te.blogmanagement.customexception.DeletePostUnsuccessfullException;
import com.te.blogmanagement.customexception.DeleteUserUnsuccessfullException;
import com.te.blogmanagement.customexception.LoginUnsuccessfullException;
import com.te.blogmanagement.customexception.PostAndCommentsUnAvailableException;
import com.te.blogmanagement.customexception.PostCommentRegistraionUnsuccessfullException;
import com.te.blogmanagement.customexception.PostUpdateUnSuccessfullException;
import com.te.blogmanagement.customexception.UpdatePostUnsuccessfullException;
import com.te.blogmanagement.customexception.UpdateUserUnsuccessfullException;
import com.te.blogmanagement.customexception.UserDataListUnAvailableException;
import com.te.blogmanagement.customexception.UserDataUnAvailableException;
import com.te.blogmanagement.customexception.UserRegistrationUnsuccessfullExcption;
import com.te.blogmanagement.dto.LoginDto;
import com.te.blogmanagement.dto.PostCommentDto;
import com.te.blogmanagement.dto.PostDto;
import com.te.blogmanagement.dto.TagDto;
import com.te.blogmanagement.dto.UpdatePostCommentDto;
import com.te.blogmanagement.dto.UpdatePostDto;
import com.te.blogmanagement.dto.UserDto;
import com.te.blogmanagement.dto.UserUpdateDto;
import com.te.blogmanagement.email.UserRegistrationEmail;
import com.te.blogmanagement.response.ApiResponse;
import com.te.blogmanagement.service.BmService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/public")
public class BmController {
	private final BmService bmService;
	private final UserRegistrationEmail userRegistrationEmail;

	@PostMapping(path = "/userRegistration")
	public ResponseEntity<ApiResponse<Object>> userRegistration(@RequestBody UserDto userDto) {
		Optional<UserDto> registration = bmService.registration(userDto);
		if (registration.isPresent()) {
			userRegistrationEmail.sendingEmail("chandramailsender@gmail.com", userDto.getEmail(),
					"Registration Successfull" + "\n" + "UserName:" + userDto.getMobile() + "\n" + "password:"
							+ userDto.getPasswordHash());
			return ResponseEntity.ok().body(ApiResponse.builder().message("Registration Successfull").build());
		}
		throw new UserRegistrationUnsuccessfullExcption("Registration Unsuccessfull");
	}

	@GetMapping(path = "/login")
	public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginDto loginDto) {
		Boolean isChecked = bmService.login(loginDto);
		if (isChecked) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Login successfull").build());
		}
		throw new LoginUnsuccessfullException("Login UnSuccessfull");

	}

	@PutMapping(path = "/updateUser/{id}")
	public ResponseEntity<ApiResponse<Object>> updateUser(@PathVariable(name = "id") Integer id,
			@RequestBody UserUpdateDto userUpdateDto) {
		Optional<Boolean> updateUser = bmService.updateUser(id, userUpdateDto);
		if (updateUser.isPresent()) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Update User Successfull").build());
		}
		throw new UpdateUserUnsuccessfullException("Update User UnSuccessfull");
	}

	@GetMapping(path = "/userData/{userid}")
	public ResponseEntity<ApiResponse<Object>> userData(@PathVariable(name = "userid") Integer userid) {
		Optional<UserDto> userDataDto = bmService.userData(userid);
		if (userDataDto.get().equals(true)) {
			return ResponseEntity.ok()
					.body(ApiResponse.builder().message("User Data Is Available").data(userDataDto.get()).build());
		}
		throw new UserDataUnAvailableException("User Data Is Available");
	}

	@GetMapping(path = "/userDataList")
	public ResponseEntity<ApiResponse<Object>> userDataList() {
		Optional<List<UserDto>> userDataList = bmService.UserDataList();
		if (userDataList.get().equals(true)) {
			return ResponseEntity.ok()
					.body(ApiResponse.builder().message("User DataList is Available").data(userDataList.get()).build());
		}
		throw new UserDataListUnAvailableException("User DataList is Not Available");
	}

	@PutMapping(path = "/deleteUser/{id}")
	public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable(name = "id") Integer id) {
		Optional<Boolean> deleteUser = bmService.deleteUser(id);
		if (deleteUser.get().equals(true)) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Delete User Successfull").build());
		}
		throw new DeleteUserUnsuccessfullException("Delete User UnSuccessfull");

	}

	@PostMapping(path = "/postRegistration/{id}")
	public void postRegistration(@PathVariable(name = "id") Integer id, @RequestBody PostDto postDto) {
		bmService.postRegistration(id, postDto);
	}

	@PutMapping(path = "/postUpdate/{id}")
	public ResponseEntity<ApiResponse<Object>> postUpdate(@PathVariable(name = "id") Integer id,
			@RequestBody UpdatePostDto updatePostDto) {
		Optional<Boolean> updatePost = bmService.updatePost(id, updatePostDto);
		if (updatePost.get()) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("update post successfull").build());
		}
		throw new PostUpdateUnSuccessfullException("update post Unsuccessfull");
	}

	@DeleteMapping(path = "/deletePost/{id}")
	public ResponseEntity<ApiResponse<Object>> deletePost(@PathVariable(name = "id") Integer id) {
		Optional<Boolean> deletePost = bmService.deletePost(id);
		if (deletePost.get().equals(true)) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Delete Post Successfull").build());
		}
		throw new DeletePostUnsuccessfullException("Delete Post UnSuccessfull");
	}

	@PostMapping(path = "/postComment/{id}")
	public ResponseEntity<ApiResponse<Object>> postCommentRegister(@PathVariable(name = "id") Integer id,
			@RequestBody PostCommentDto postCommentDto) {
		Optional<Boolean> commentRegistration = bmService.commentRegistration(id, postCommentDto);
		if (commentRegistration.get().equals(true)) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("PostComment added Successfull").build());
		}
		throw new PostCommentRegistraionUnsuccessfullException("PostComment added UnSuccessfull");
	}

	@PutMapping(path = "/updatePostComment/{id}")
	public ResponseEntity<ApiResponse<Object>> updatePostComment(@PathVariable(name = "id") Integer id,
			@RequestBody UpdatePostCommentDto updatePostCommentDto) {
		Optional<Boolean> updatePostComment = bmService.updatePostComment(id, updatePostCommentDto);
		if (updatePostComment.get().equals(true)) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Update Post Successfull").build());
		}
		throw new UpdatePostUnsuccessfullException("Update Post UnSuccessfull");
	}

	@GetMapping(path = "/readPostAndComments/{id}")
	public ResponseEntity<ApiResponse<Object>> readPostsAndComments(@PathVariable(name = "id") Integer id) {
		Optional<PostDto> readPostAndComments = bmService.readPostAndComments(id);
		if (readPostAndComments.isPresent()) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("PostsAndComments are Available")
					.data(readPostAndComments.get().getPostCommentDto()).build());
		}
		throw new PostAndCommentsUnAvailableException("PostsAndComments are Not Available");

	}

	@PutMapping(path = "/deletePostComment/{id}")
	public ResponseEntity<ApiResponse<Object>> deletePostComment(@PathVariable(name = "id") Integer id) {
		Optional<Boolean> deletePostComment = bmService.deletePostComment(id);
		if (deletePostComment.get().equals(true)) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Delete PostComment Successfull").build());
		}
		throw new DeletePostCommentUnsuccessfullException("Delete PostComment Unsuccessfull");
	}

	@PostMapping(path = "/tagRegistration/{id}")
	public ResponseEntity<ApiResponse<Object>> tagregistration(@PathVariable(name = "id") Integer id,@RequestBody TagDto tagDto ) {
		Optional<Boolean> tagRegistration = bmService.tagRegistration(id,tagDto);
		if(tagRegistration.get().equals(true)) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("TagRegistration Successfull").build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("TagRegistration UnSuccessfull").build());
	}
	
	@PostMapping(path = "/uploadFile")
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		bmService.uploadFile(file);
	}

}
