package vn.iback.studentmanager.service.phanHoiService;

import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.binhluan;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.phanHoi;

import java.util.List;

public interface phanHoiService {
    public void save(phanHoi phanHoi);
    public void update(phanHoi phanHoi);
    public void saveAndUpdate(phanHoi phanHoi);
    public phanHoi findPhanHoiByid(int id);
    public List<phanHoi> findAllPhanHoi();
    public void deleteById(int id);

    public List<phanHoi> findAllPhanHoiOfBinhLuan(binhluan binhluan);

}
