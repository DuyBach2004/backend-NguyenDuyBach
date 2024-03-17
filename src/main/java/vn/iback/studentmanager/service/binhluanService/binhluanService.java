package vn.iback.studentmanager.service.binhluanService;

import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.binhluan;

import java.util.List;

public interface binhluanService {
    public void save(binhluan binhluan);
    public void update(binhluan binhluan);
    public binhluan findClassByid(int id);
    public List<binhluan> findAllBinhluan();
    public List<binhluan> findAllBinhluanOfBaiViet(baiViet baiViet);
    public void deleteById(int id);
}
