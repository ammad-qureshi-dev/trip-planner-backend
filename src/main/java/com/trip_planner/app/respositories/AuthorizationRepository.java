package com.trip_planner.app.respositories;

import com.trip_planner.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorizationRepository extends JpaRepository<User, UUID> {
}
