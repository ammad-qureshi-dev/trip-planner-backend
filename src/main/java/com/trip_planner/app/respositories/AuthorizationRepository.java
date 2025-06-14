/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.respositories;

import com.trip_planner.app.models.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<User, UUID> {}
