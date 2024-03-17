package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.khoa;
import vn.iback.studentmanager.entity.kyHoc;
@Repository
public interface KyhocRespository extends JpaRepository<kyHoc,Integer> {

}
