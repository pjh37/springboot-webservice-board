package com.pjh.board.springboot.service;

import com.pjh.board.springboot.domain.posts.Posts;
import com.pjh.board.springboot.domain.posts.PostsRepository;
import com.pjh.board.springboot.domain.reply.Reply;
import com.pjh.board.springboot.domain.reply.ReplyRepository;
import com.pjh.board.springboot.web.dto.ReplyListResponseDto;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
import com.pjh.board.springboot.web.dto.ReplyUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public Long save(ReplySaveRequestDto requestDto){
        if(requestDto.getParent()==null){requestDto.setParent(-1L);}
        else{
            Reply parentReply=replyRepository.findById(requestDto.getParent())
                    .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+requestDto.getParent()));
            parentReply.countUpdate(parentReply.getCount()+1);
        }
        return replyRepository.save(requestDto.toEntity()).getId();//댓글 번호 리턴
    }

    //부모댓글만 불러오기
    @Transactional(readOnly = true)
    public List<ReplyListResponseDto> findAllDescById(Long id){
        return replyRepository.findAllDescById(id)
                .stream()
                .map(ReplyListResponseDto::new)
                .collect(Collectors.toList());
    }

    //자식댓글 불러오기
    @Transactional(readOnly = true)
    public List<ReplyListResponseDto> findAllChildDescById(Long id){
        return replyRepository.findAllChildDescById(id)
                .stream()
                .map(ReplyListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, ReplyUpdateRequestDto requestDto){
        Reply reply=replyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        reply.update(requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Reply reply=replyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        reply.countUpdate(reply.getCount()-1);
        replyRepository.delete(reply);
    }
}
