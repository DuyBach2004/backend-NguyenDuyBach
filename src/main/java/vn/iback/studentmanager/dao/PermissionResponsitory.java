package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.dto.request.PermissionRequest;
import vn.iback.studentmanager.dto.respon.PermissionResponse;
import vn.iback.studentmanager.entity.permission;
@Repository
public interface PermissionResponsitory extends JpaRepository<permission,String>{
}
