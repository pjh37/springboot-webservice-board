package com.pjh.board.springboot.web.dto;

import com.pjh.board.springboot.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyListResponseDto {
    private Long id;
    private Long posts_id;
    private Long count;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    public ReplyListResponseDto(Reply entity){
        this.id=entity.getId();
        this.posts_id=entity.getPosts_id();
        this.count=entity.getCount();
        this.author=entity.getAuthor();
        this.content=entity.getContent();
        this.modifiedDate=entity.getModifiedDate();
    }
}
