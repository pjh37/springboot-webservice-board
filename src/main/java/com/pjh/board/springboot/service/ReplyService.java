package com.pjh.board.springboot.service;

import com.pjh.board.springboot.domain.posts.Posts;
import com.pjh.board.springboot.domain.posts.PostsRepository;
import com.pjh.board.springboot.domain.reply.ReplyRepository;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(ReplySaveRequestDto requestDto){

        Posts posts=postsRepository.findById(requestDto.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다"));;
        posts.getReply().add(requestDto.toEntity());


        return 1L;
    }
}
