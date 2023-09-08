package com.ftn.svtkvt.repository;

import com.ftn.svtkvt.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findFirstByPoster_Id(Long id);
}
