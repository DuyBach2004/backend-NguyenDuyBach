package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.user;

@Repository
public interface UserRespository extends JpaRepository<user, String> {
    public user findByUsername(String username);
}
