package com.pjh.board.springboot.web.dto;

import com.pjh.board.springboot.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private Long id;
    private Long parent;
    private String author;
    private String content;

    @Builder
    public ReplySaveRequestDto(Long id,Long parent,String content,String author){
       this.id=id;
       this.parent=parent;
       this.author=author;
       this.content=content;
    }

    public Reply toEntity(){
        return Reply.builder()
                .posts_id(id)
                .parent(parent)
                .author(author)
                .content(content)
                .build();
    }
}
