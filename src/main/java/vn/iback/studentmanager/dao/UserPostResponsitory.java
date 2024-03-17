package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.userPost;

@Repository
public interface UserPostResponsitory extends JpaRepository<userPost,Integer> {
}
