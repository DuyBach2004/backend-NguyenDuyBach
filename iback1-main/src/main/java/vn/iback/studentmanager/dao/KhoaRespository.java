package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.khoa;
@Repository
public interface KhoaRespository extends JpaRepository<khoa,String> {

}
