package vn.iback.studentmanager.service.thoiGianHocService;

import vn.iback.studentmanager.entity.lichHoc;
import vn.iback.studentmanager.entity.thoiGianHoc;

import java.util.List;

public interface thoiGianHocService {
    public void save(thoiGianHoc thoiGianHoc);
    public void update(thoiGianHoc thoiGianHoc);
    public thoiGianHoc findkyHocid(int thoiGianHoc);
    public List<thoiGianHoc> findAllThoiGianHoc();
    public void deleteById(int id);

}
