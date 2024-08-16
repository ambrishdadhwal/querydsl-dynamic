package com.ammycodes.querydsldynamicquery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ammycodes.querydsldynamicquery.domain.PostDTO;
import com.ammycodes.querydsldynamicquery.serviceimpl.PostService;
import com.ammycodes.querydsldynamicquery.vo.PageableResponse;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

	@Autowired
	PostService postService;

	// request :- post/all-posts?filter=title:My first Post - 1
	@GetMapping("/all-posts")
	public PageableResponse getAllPosts(@SortDefault(sort = "updatedAt", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String[] filter) {

		Page<PostDTO> pcq = postService.getPostListing(filter, pageable);
		return formatPageResponse(pcq);

	}

	@PostMapping("/add-post")
	public ResponseEntity<Object> addUser(@Validated @RequestBody PostDTO post)
	{
		post = postService.addUserPost(post);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
}
