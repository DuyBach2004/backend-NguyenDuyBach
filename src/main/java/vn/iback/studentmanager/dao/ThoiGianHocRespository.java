package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.thoiGianHoc;

import java.util.List;
@Repository
public interface ThoiGianHocRespository extends JpaRepository<thoiGianHoc,Integer> {
}
