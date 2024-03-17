package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.binhluan;
@Repository
public interface BinhluanResponsitory extends JpaRepository<binhluan,Integer> {
}
