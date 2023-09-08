package com.ftn.svtkvt.repository;

import com.ftn.svtkvt.model.entity.GroupAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupAdminRepository extends JpaRepository<GroupAdmin, Long> {
}
