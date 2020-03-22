package com.pjh.board.springboot.web;

import com.pjh.board.springboot.service.ReplyService;
import com.pjh.board.springboot.web.dto.ReplyListResponseDto;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
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

    @GetMapping("/api/v1/reply/{id}")
    public List<ReplyListResponseDto> findAllDescById(@PathVariable Long id){
        return replyService.findAllDescById(id);
    }
}
