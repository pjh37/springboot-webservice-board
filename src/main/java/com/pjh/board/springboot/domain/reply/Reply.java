package com.pjh.board.springboot.domain.reply;

import com.pjh.board.springboot.domain.BaseTimeEntity;
import com.pjh.board.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fk 외래키
    @JoinColumn(name = "posts_id")
    private Long posts_id;

    @Column(columnDefinition = "long default -1L")
    private Long parent;//부모 댓글의 id

    @Column(nullable = false)
    private Long re_reply_cnt;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Builder
    public Reply(String author, String content,Long posts_id,Long parent,Long re_reply_cnt){
        this.author=author;
        this.content=content;
        this.posts_id=posts_id;
        this.parent=parent;
        this.re_reply_cnt=re_reply_cnt;
    }
    public void update(String content){
        this.content=content;
    }
    public void reReplyCntUpdate(Long cnt){
        this.re_reply_cnt=cnt+1;
    }
}
