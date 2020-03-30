package com.pjh.board.springboot.web.dto;

import com.pjh.board.springboot.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private Long id;
    private Long parent;
    private Long re_reply_cnt;
    private String author;
    private String content;

    @Builder
    public ReplySaveRequestDto(Long id,Long parent,Long re_reply_cnt,String content,String author){
       this.id=id;
       this.parent=parent;
       this.author=author;
       this.content=content;
       this.re_reply_cnt=re_reply_cnt;
    }

    public Reply toEntity(){
        return Reply.builder()
                .posts_id(id)
                .parent(parent)
                .re_reply_cnt(re_reply_cnt)
                .author(author)
                .content(content)
                .build();
    }
}
