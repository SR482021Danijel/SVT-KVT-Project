package com.ftn.svtkvt.repository;

import com.ftn.svtkvt.model.entity.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRequestRepository extends JpaRepository<GroupRequest, Long> {
}
