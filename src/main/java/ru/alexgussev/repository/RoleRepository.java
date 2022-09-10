package ru.alexgussev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexgussev.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where lower(r.name) like :role")
    Role findRoleByName(@Param("role") String role);
}
