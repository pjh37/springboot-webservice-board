package com.pjh.board.springboot.service;

import com.pjh.board.springboot.domain.posts.Posts;
import com.pjh.board.springboot.domain.posts.PostsRepository;
import com.pjh.board.springboot.domain.reply.ReplyRepository;
import com.pjh.board.springboot.web.dto.ReplyListResponseDto;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final PostsRepository postsRepository;
    private final ReplyRepository replyRepository;
    @Transactional
    public Long save(ReplySaveRequestDto requestDto){
        Posts posts=postsRepository.findById(requestDto.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다"));;
        posts.getReply().add(requestDto.toEntity());
        return requestDto.getId();//어떤 게시판의 댓글인지 게시판의 id 리턴
    }

    @Transactional(readOnly = true)
    public List<ReplyListResponseDto> findAllDescById(Long id){
        return replyRepository.findAllDescById(id)
                .stream()
                .map(ReplyListResponseDto::new)
                .collect(Collectors.toList());
    }
}
