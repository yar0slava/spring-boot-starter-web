package kma.topic3.webstarter.database;

import kma.topic3.webstarter.model.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

}