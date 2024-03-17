package vn.iback.studentmanager.service.lichHocService;

import vn.iback.studentmanager.entity.khoa;
import vn.iback.studentmanager.entity.lichHoc;

import java.util.List;

public interface lichHocService {
    public void save(lichHoc lichHoc);
    public void update(lichHoc lichHoc);
    public lichHoc findkyHocid(int maLichHoc);
    public List<lichHoc> findAllLichHoc();
    public void deleteById(int id);

}
