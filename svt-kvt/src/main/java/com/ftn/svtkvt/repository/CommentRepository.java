package com.ftn.svtkvt.repository;

import com.ftn.svtkvt.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "select * from comments where post_id = ?")
    List<Comment> findAllByPostId(Long postId);
}
