package com.pjh.board.springboot.domain.posts;

import com.pjh.board.springboot.domain.BaseTimeEntity;
import com.pjh.board.springboot.domain.reply.Reply;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    //댓글 부분

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="posts_id")
    private List<Reply> reply=new ArrayList<>();


    @Builder
    public Posts(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

    public void addReply(Reply re){
        if(reply==null){
            reply=new ArrayList<>();
        }
        reply.add(re);
    }


}
