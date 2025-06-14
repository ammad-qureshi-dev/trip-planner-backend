/* (C) 2025 
 Trip Planner App*/
package com.trip_planner.app.respositories;

import com.trip_planner.app.models.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  @Query("SELECT U FROM User U WHERE U.email = :email")
  Optional<User> findByEmail(@Param("email") String email);
}
