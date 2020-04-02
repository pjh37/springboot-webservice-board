package com.pjh.board.springboot.service;

import com.pjh.board.springboot.domain.posts.Posts;
import com.pjh.board.springboot.domain.posts.PostsRepository;
import com.pjh.board.springboot.web.dto.PostsListResponseDto;
import com.pjh.board.springboot.web.dto.PostsResponseDto;
import com.pjh.board.springboot.web.dto.PostsSaveRequestDto;
import com.pjh.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity =postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(Integer curPage){
        Pageable pageable= PageRequest.of(curPage,10,new Sort(Sort.Direction.DESC,"id"));
        return postsRepository.findAllDesc(pageable)
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Long Count(){
        return postsRepository.count();
    }

    @Transactional
    public void delete(Long id){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
