package vn.iback.studentmanager.mapper;

import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.dto.request.PermissionRequest;
import vn.iback.studentmanager.dto.respon.PermissionResponse;
import vn.iback.studentmanager.entity.permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    public permission toPermisstion(PermissionRequest request);

    public PermissionResponse toPermissionResponse(permission permission);
}
