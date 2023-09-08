package com.ftn.svtkvt.repository;

import com.ftn.svtkvt.model.entity.EReactionType;
import com.ftn.svtkvt.model.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    @Query(nativeQuery = true, value = "select count(r.type) from reaction r, reacted_to re where r.id = re.reaction_id and re.post_id = ? and r.type = 2")
    int countAllHearts(Long id);

    @Query(nativeQuery = true, value = "select count(r.type) from reaction r, reacted_to re where r.id = re.reaction_id and re.post_id = ? and r.type = 0")
    int countAllLikes(Long id);

    @Query(nativeQuery = true, value = "select count(r.type) from reaction r, reacted_to re where r.id = re.reaction_id and re.post_id = ? and r.type = 1")
    int countAllDislikes(Long id);

    @Query(nativeQuery = true, value = "select r.type from reaction r, reacted_to re where r.id = re.reaction_id and user_id = ? and post_id = ?")
    EReactionType findChecked(Long userId, Long postId);

    @Modifying
    @Query(nativeQuery = true, value = "update reaction r inner join reacted_to re on r.id = re.reaction_id set r.type = ? where r.user_id = ? and re.post_id = ?")
    void updateReaction(int reactionType, Long user_id, Long post_id);
}
