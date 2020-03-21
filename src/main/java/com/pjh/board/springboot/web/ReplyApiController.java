package com.pjh.board.springboot.web;

import com.pjh.board.springboot.domain.reply.ReplyRepository;

import com.pjh.board.springboot.service.ReplyService;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("/api/v1/reply")
    public Long save(@RequestBody ReplySaveRequestDto requestDto){
       return replyService.save(requestDto);
    }
}
