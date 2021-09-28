package com.minjae.book.web.controller;

import com.minjae.book.web.dto.PostsListResponseDto;
import com.minjae.book.web.dto.PostsResponseDto;
import com.minjae.book.web.dto.PostsSaveRequestsDto;
import com.minjae.book.web.dto.PostsUpdateRequestDto;
import com.minjae.book.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @GetMapping("/")
    public List<PostsListResponseDto> index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return postsService.findAllDesc();
    }
}

