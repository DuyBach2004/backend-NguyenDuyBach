package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.phanHoi;
@Repository
public interface PhanHoiResponsitory extends JpaRepository<phanHoi,Integer> {
}
