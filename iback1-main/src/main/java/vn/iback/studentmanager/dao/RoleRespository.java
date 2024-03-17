package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.roles;
@Repository
public interface RoleRespository extends JpaRepository<roles, Integer> {
    public roles findByName(String name);
}
