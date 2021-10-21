package bstu.shust.jprojectv2.repository;


import bstu.shust.jprojectv2.models.Role;
import bstu.shust.jprojectv2.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role name);
}
