package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.userPhanHoi;
@Repository
public interface UserPhanHoiResponsitory extends JpaRepository<userPhanHoi,Integer> {
}
