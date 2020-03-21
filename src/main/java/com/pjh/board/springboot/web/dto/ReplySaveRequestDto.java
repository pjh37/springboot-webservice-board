package com.pjh.board.springboot.web.dto;

import com.pjh.board.springboot.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private Long id;
    private String author;
    private String content;


    @Builder
    public ReplySaveRequestDto(Long id,String content,String author){
       this.id=id;
       this.author=author;
       this.content=content;

    }
    public Reply toEntity(){

        return Reply.builder()
                .posts_id(id)
                .author(author)
                .content(content)
                .build();
    }
}
