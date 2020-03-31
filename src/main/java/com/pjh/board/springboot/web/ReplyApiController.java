package com.pjh.board.springboot.web;

import com.pjh.board.springboot.service.ReplyService;
import com.pjh.board.springboot.web.dto.ReplyListResponseDto;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
import com.pjh.board.springboot.web.dto.ReplyUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;

    //저장
    @PostMapping("/api/v1/reply")
    public Long save(@RequestBody ReplySaveRequestDto requestDto){
       return replyService.save(requestDto);
    }

    //불러오기
    @GetMapping("/api/v1/reply/{postId}")
    public List<ReplyListResponseDto> findAllDescById(@PathVariable Long postId){
        return replyService.findAllDescById(postId);
    }

    //자식 댓글 불러오기
    @GetMapping("/api/v1/reply/child/{parentId}")
    public List<ReplyListResponseDto> findAllChildDescById(@PathVariable Long parentId){
        return replyService.findAllChildDescById(parentId);
    }

    //수정하기
    @PutMapping("/api/v1/reply/{id}")
    public Long update(@PathVariable Long id,@RequestBody ReplyUpdateRequestDto requestDto){
        return replyService.update(id,requestDto);
    }

    //삭제
    @DeleteMapping("/api/v1/reply/{id}")
    public Long delete(@PathVariable Long id){
        replyService.delete(id);
        return id;
    }

}
