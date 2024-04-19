//package vn.iback.studentmanager.service.permissionService;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import vn.iback.studentmanager.dao.PermissionResponsitory;
//import vn.iback.studentmanager.dto.request.PermissionRequest;
//import vn.iback.studentmanager.dto.respon.PermissionResponse;
//import vn.iback.studentmanager.entity.permission;
//import vn.iback.studentmanager.mapper.PermissionMapper;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
//public class permissionService {
//
//    PermissionResponsitory permissionResponsitory;
//
//    PermissionMapper permissionMapper;
//    @Autowired
//    public permissionService(PermissionResponsitory permissionResponsitory, PermissionMapper permissionMapper) {
//        this.permissionResponsitory = permissionResponsitory;
//        this.permissionMapper = permissionMapper;
//    }
//    public PermissionResponse create(PermissionRequest request){
//        permission permission = permissionMapper.toPermisstion(request);
//        permission= permissionResponsitory.save(permission);
//        return permissionMapper.toPermissionResponse(permission);
//    }
//
//    public List<PermissionResponse> getAll(){
//        var permisstion=permissionResponsitory.findAll();
//        return permisstion.stream().map(permissionMapper::toPermissionResponse).toList();
//    }
//    public void delete(String permission){
//        permissionResponsitory.deleteById(permission);
//    }
//
//}
