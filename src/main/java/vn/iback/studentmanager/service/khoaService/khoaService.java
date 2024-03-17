package vn.iback.studentmanager.service.khoaService;

import vn.iback.studentmanager.entity.khoa;
import vn.iback.studentmanager.entity.kyHoc;

import java.util.List;

public interface khoaService {
    public void save(khoa khoa);
    public void update(khoa khoa);
    public khoa findkyHocid(String maKhoa);
    public List<khoa> findAllKhoa();
    public void deleteById(String id);
}
