package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.baiViet;
@Repository
public interface BaivietResponsitory extends JpaRepository<baiViet,Integer> {
}
