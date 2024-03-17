package vn.iback.studentmanager.service.diemService;

import vn.iback.studentmanager.entity.diem;
import vn.iback.studentmanager.entity.lop;

import java.util.List;

public interface diemService {
    public void save(diem diem);
    public void update(diem diem);
    public diem findDiemById(int maDiem);
    public List<diem> findAllDiem();
    public void deleteById(int id);
}
