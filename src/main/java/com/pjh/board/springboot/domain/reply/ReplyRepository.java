package com.pjh.board.springboot.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Query("select r from Reply r where r.posts_id=:postId and r.parent=-1 order by r.id desc")
    List<Reply> findAllDescById(@Param("postId") Long id);
    //findAllChildDescById

    @Query("select r from Reply r where r.parent=:parentID order by r.id")
    List<Reply> findAllChildDescById(@Param("parentID") Long id);
}
