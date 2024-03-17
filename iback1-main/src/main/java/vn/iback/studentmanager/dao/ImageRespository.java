package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.image;
@Repository
public interface ImageRespository extends JpaRepository<image,Integer> {
}
