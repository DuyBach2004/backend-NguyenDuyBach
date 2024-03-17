package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.lop;
@Repository
public interface lopResponsitory extends JpaRepository<lop,String> {
}
