package com.te.blogmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.blogmanagement.dto.LoginDto;
import com.te.blogmanagement.dto.PostCommentDto;
import com.te.blogmanagement.dto.PostDto;
import com.te.blogmanagement.dto.TagDto;
import com.te.blogmanagement.dto.UpdatePostCommentDto;
import com.te.blogmanagement.dto.UpdatePostDto;
import com.te.blogmanagement.dto.UserDto;
import com.te.blogmanagement.dto.UserUpdateDto;
import com.te.blogmanagement.entity.AppUser;
import com.te.blogmanagement.entity.Post;
import com.te.blogmanagement.entity.PostComment;
import com.te.blogmanagement.entity.Tag;
import com.te.blogmanagement.entity.User;
import com.te.blogmanagement.enums.Status;
import com.te.blogmanagement.repository.AppUserRepository;
import com.te.blogmanagement.repository.PostCommentRepository;
import com.te.blogmanagement.repository.PostRepository;
import com.te.blogmanagement.repository.TagRepository;
import com.te.blogmanagement.repository.UserRepository;
import com.te.blogmanagement.service.BmService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BmServiceImpl implements BmService {
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final AppUserRepository appUserRepository;
	private final PostCommentRepository postCommentRepository;
	private final TagRepository tagRepository;

	@Override
	public Optional<UserDto> registration(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		AppUser appUser = new AppUser();
		appUser.setUserName(userDto.getMobile());
		appUser.setPassword(userDto.getPasswordHash());
		user.setRegisteredAt(LocalDateTime.now());
		user.setStatus(Status.ACTIVE);
		userRepository.save(user);
		appUserRepository.save(appUser);
		return Optional.ofNullable(userDto);

	}

	@Override
	public Boolean login(LoginDto loginDto) {
		Optional<AppUser> loginDataFromDb = appUserRepository.findById(loginDto.getUsername());
		User user = userRepository.findByMobile(loginDto.getUsername());
		if (loginDataFromDb.isPresent()) {
			if (loginDataFromDb.get().getPassword().equals(loginDto.getPassword())
					&& user.getStatus().equals(Status.ACTIVE)) {
				user.setLastLogin(LocalDateTime.now());
				userRepository.save(user);
				return true;
			}
		}
		return false;

	}

	@Override
	public Optional<Boolean> updateUser(Integer id, UserUpdateDto userUpdateDto) {
		Optional<User> userFromDb = userRepository.findById(id);
		Optional<AppUser> appuserData = appUserRepository.findById(userFromDb.get().getMobile());
		if (userFromDb.isPresent()) {
			userFromDb.get().setFirstName(userUpdateDto.getFirstName());
			userFromDb.get().setMiddleName(userUpdateDto.getMiddleName());
			userFromDb.get().setLastName(userUpdateDto.getLastName());
			userFromDb.get().setMobile(userUpdateDto.getMobile());
			userFromDb.get().setEmail(userUpdateDto.getEmail());
			userFromDb.get().setPasswordHash(userUpdateDto.getPasswordHash());
			userFromDb.get().setIntro(userUpdateDto.getIntro());
			userFromDb.get().setProfile(userUpdateDto.getProfile());
			userRepository.save(userFromDb.get());
			if (appuserData.isPresent()) {
				appUserRepository.delete(appuserData.get());
				appuserData.get().setUserName(userFromDb.get().getMobile());
				appuserData.get().setPassword(userFromDb.get().getPasswordHash());
				appUserRepository.save(appuserData.get());
			}

			/*
			 * Logic for to change password only
			 * appuserData.get().setUserName(userUpdateDto.getMobile());
			 * appuserData.get().setPassword(userUpdateDto.getPasswordHash());
			 * appUserRepository.save(appuserData.get());
			 */
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

	@Override
	public Optional<UserDto> userData(Integer userid) {
		Optional<User> userDataFromDb = userRepository.findById(userid);
		if (userDataFromDb.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userDataFromDb.get(), userDto);
			List<PostDto> posts = new ArrayList<PostDto>();
			for (Post postEntity : userDataFromDb.get().getPost()) {
				if (postEntity.getPostStatus().equals(Status.ACTIVE)) {
					PostDto postDto = new PostDto();
					BeanUtils.copyProperties(postEntity, postDto);
					posts.add(postDto);
				}
			}
			userDto.setPostDto(posts);
			return Optional.ofNullable(userDto);
		}
		return Optional.ofNullable(null);

	}

	@Override
	public Optional<List<UserDto>> UserDataList() {
		List<User> userDataList = userRepository.findAll();
		List<UserDto> userDto = new ArrayList<UserDto>();
		for (User userEntity : userDataList) {
			UserDto userListDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userListDto);
			userDto.add(userListDto);
		}
		return Optional.ofNullable(userDto);
		// return Optional.ofNullable(null);

	}

	@Override
	public Optional<Boolean> deleteUser(Integer id) {
		Optional<User> userFromDb = userRepository.findById(id);
		if (userFromDb.isPresent()) {
			userFromDb.get().setStatus(Status.INACTIVE);
			userRepository.save(userFromDb.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);

	}

	@Override
	public void postRegistration(Integer id, PostDto postDto) {

		Post post = new Post();

		/* post registration */
		Optional<User> user = userRepository.findById(id);
		post.setUser(user.get());
		BeanUtils.copyProperties(postDto, post);
		post.setPublishedAt(LocalDateTime.now());
		post.setCreatedAt(LocalDateTime.now());
		post.setPostStatus(Status.ACTIVE);
		user.get().getPost().add(post);
		/*
		 * Category registration logic
		 * 
		 * List<Category> category = new ArrayList<Category>(); for (CategoryDto
		 * categoryDto : postDto.getCategoryDto()) { Category categoryEntity = new
		 * Category(); BeanUtils.copyProperties(categoryDto, categoryEntity);
		 * category.add(categoryEntity);
		 * 
		 * } post.setCategory(category); for (Category category2 : category) {
		 * category2.getPost().add(post); } Tag registration
		 * 
		 * List<Tag> tag = new ArrayList<Tag>(); for (TagDto tagDto :
		 * postDto.getTagDto()) { Tag tagEntity = new Tag();
		 * BeanUtils.copyProperties(tagDto, tagEntity); tag.add(tagEntity);
		 * 
		 * } post.setTag(tag); for (Tag tag2 : tag) { tag2.getPost().add(post); }
		 * 
		 * post meta registration
		 * 
		 * List<PostMeta> postmeta = new ArrayList<PostMeta>(); for (PostMetaDto
		 * postMetaDto : postDto.getPostMetaDto()) { PostMeta postMetaEntity = new
		 * PostMeta(); BeanUtils.copyProperties(postMetaDto, postMetaEntity);
		 * postmeta.add(postMetaEntity); } post.setPostMeta(postmeta); for (PostMeta
		 * postMeta2 : postmeta) { postMeta2.setPost(post); }
		 */
		postRepository.save(post);
	}

	@Override
	public Optional<Boolean> updatePost(Integer id, UpdatePostDto updatePostDto) {
		Optional<Post> postDataFromDb = postRepository.findById(id);
		if (postDataFromDb.isPresent()) {
			Post post = postDataFromDb.get();
			BeanUtils.copyProperties(updatePostDto, post);
			post.setUpdatedAt(LocalDateTime.now());
			postRepository.save(post);
			return Optional.ofNullable(true);

		}
		return Optional.ofNullable(false);

	}

	@Override
	public Optional<Boolean> deletePost(Integer id) {
		Optional<Post> postDataFromDb = postRepository.findById(id);
		if (postDataFromDb.isPresent()) {
			postDataFromDb.get().setPostStatus(Status.INACTIVE);
			postRepository.save(postDataFromDb.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

	@Override
	public Optional<Boolean> commentRegistration(Integer id, PostCommentDto postCommentDto) {
		Optional<Post> postFromDb = postRepository.findById(id);
		if (postFromDb.isPresent()) {
			PostComment postComment = new PostComment();
			BeanUtils.copyProperties(postCommentDto, postComment);
			postComment.setPost(postFromDb.get());
			postComment.setCreatedAt(LocalDateTime.now());
			postComment.setPublishedAt(LocalDateTime.now());
			postComment.setStatus(Status.ACTIVE);
			postCommentRepository.save(postComment);
			return Optional.ofNullable(true);

		}
		return Optional.ofNullable(false);
	}

	@Override
	public Optional<Boolean> updatePostComment(Integer id, UpdatePostCommentDto updatePostCommentDto) {
		Optional<PostComment> postCommentFromDb = postCommentRepository.findById(id);
		if (postCommentFromDb.isPresent()) {
			BeanUtils.copyProperties(updatePostCommentDto, postCommentFromDb.get());
			postCommentFromDb.get().setPublishedAt(LocalDateTime.now());
			postCommentRepository.save(postCommentFromDb.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

	@Override
	public Optional<Boolean> deletePostComment(Integer id) {
		Optional<PostComment> commentsFromDb = postCommentRepository.findById(id);
		if (commentsFromDb.isPresent()) {
			commentsFromDb.get().setStatus(Status.INACTIVE);
			postCommentRepository.save(commentsFromDb.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

	@Override
	public Optional<PostDto> readPostAndComments(Integer id) {
		Optional<User> userFromDb = userRepository.findById(id);
		if (userFromDb.isPresent()) {
			Optional<Post> PostFromDb = postRepository.findById(id);
			PostDto postDto = new PostDto();
			BeanUtils.copyProperties(PostFromDb.get(), postDto);

			List<PostCommentDto> postComment = new ArrayList<PostCommentDto>();
			for (PostComment postCommentEntity : PostFromDb.get().getPostComment()) {
				PostCommentDto postCommentDto = new PostCommentDto();
				BeanUtils.copyProperties(postCommentEntity, postCommentDto);
				postComment.add(postCommentDto);
			}
			postDto.getPostCommentDto().addAll(postComment);
			return Optional.ofNullable(postDto);
		}
		return Optional.ofNullable(null);
	}

	@Override
	public void uploadFile(MultipartFile file) {
		userRepository.save(file);
	}

	@Override
	public Optional<Boolean> tagRegistration(Integer id, TagDto tagDto) {
		Optional<Post> postFromDb = postRepository.findById(id);
		if (postFromDb.isPresent()) {
			List<Tag> tags = new ArrayList<Tag>();
			Tag tag = new Tag();
			BeanUtils.copyProperties(tagDto, tag);
		//	postFromDb.get().setTag(tags);
			for (Tag tag2 : tags) {
				tag2.getPost().add(postFromDb.get());
			}
			BeanUtils.copyProperties(tag, tags);
			tagRepository.saveAll(tags);
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);

	}
}
