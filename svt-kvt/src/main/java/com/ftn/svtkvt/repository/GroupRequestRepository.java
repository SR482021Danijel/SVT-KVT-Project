package com.ftn.svtkvt.repository;

import com.ftn.svtkvt.model.entity.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRequestRepository extends JpaRepository<GroupRequest, Long> {

    @Query(nativeQuery = true, value = "select approved from group_request where group_id = ? and user_id = ?")
    Boolean isInGroup(Long groupId, Long userId);
}
