package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.ketQuaHocTap;
@Repository
public interface KetQuaHocTapRespository extends JpaRepository<ketQuaHocTap,Integer> {
}
