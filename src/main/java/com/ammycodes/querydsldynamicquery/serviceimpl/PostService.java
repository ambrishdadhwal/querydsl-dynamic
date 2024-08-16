package com.ammycodes.querydsldynamicquery.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import com.ammycodes.querydsldynamicquery.entity.Post;
import com.ammycodes.querydsldynamicquery.entity.PostType;
import com.ammycodes.querydsldynamicquery.entity.Users;
import com.ammycodes.querydsldynamicquery.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ammycodes.querydsldynamicquery.domain.PostDTO;
import com.ammycodes.querydsldynamicquery.repository.PostRepository;
import com.ammycodes.querydsldynamicquery.service.BaseService;
import com.ammycodes.querydsldynamicquery.utils.PostUtils;
import com.ammycodes.querydsldynamicquery.utils.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class PostService extends BaseService {

	static int counter = 1;

	@Autowired
	PostUtils pu;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UsersRepository usersRepository;

	public Page<PostDTO> getPostListing(String[] filter, Pageable pageable) {
		List<SearchCriteria> criteria = formatSearchCriteria(filter);
		BooleanExpression exp = pu.getPCQFilterExp(criteria);
		PageRequest pageRequest = pu.getCustomizablePage(pageable);
		return postRepository.findAll(exp, pageRequest).map(pcq -> pu.mapToDTO(pcq));
	}

	public PostDTO addUserPost(PostDTO post)
	{
		Users existingUser = usersRepository.findById(post.getUserId()).orElse(null);
		Post postEntity = new Post();
		PostType postType = new PostType();
		postType.setType(post.getPostType());
		postEntity.setTitle(post.getTitle() + " - "+ (counter++));
		postEntity.setPostType(postType);
		postEntity.setAuthor(existingUser);
		postEntity.setCreatedAt(LocalDateTime.now());
		postEntity.setCreatedBy(existingUser.getUserName());
		postEntity = postRepository.save(postEntity);
		return post;
	}

}
