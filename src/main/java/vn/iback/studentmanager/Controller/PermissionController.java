//package vn.iback.studentmanager.Controller;
//
//import io.swagger.v3.oas.models.responses.ApiResponse;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import vn.iback.studentmanager.dto.request.PermissionRequest;
//import vn.iback.studentmanager.dto.respon.PermissionResponse;
//import vn.iback.studentmanager.service.permissionService.permissionService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/permissions")
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Slf4j
//public class PermissionController {
//    @Autowired
//    permissionService permissionService;
//    @Autowired
//    public PermissionController(vn.iback.studentmanager.service.permissionService.permissionService permissionService) {
//        this.permissionService = permissionService;
//    }
//
//    @PostMapping
//    public ResponseEntity<PermissionResponse> create(@RequestBody PermissionRequest request){
//        PermissionResponse result = permissionService.create(request);
//        return ResponseEntity.ok(result);
//    }
//    @GetMapping
//    public ResponseEntity<List<PermissionResponse>> getAll(){
//        List<PermissionResponse> results = permissionService.getAll();
//        return ResponseEntity.ok(results);
//    }
//    @DeleteMapping("/{permission}")
//    public ResponseEntity<Void> delete(@PathVariable String permission){
//        permissionService.delete(permission);
//        return ResponseEntity.noContent().build();
//    }
//}
