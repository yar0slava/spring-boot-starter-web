package kma.topic3.webstarter.database;

import kma.topic3.webstarter.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT user FROM UserEntity user "
            + "LEFT JOIN FETCH user.permissions "
            + "WHERE user.login = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);

}