package vn.iback.studentmanager.service.lopSevice;

import vn.iback.studentmanager.entity.khoa;
import vn.iback.studentmanager.entity.lop;

import java.util.List;

public interface lopService {
    public void save(lop lop);
    public void update(lop lop);
    public lop findLopById(String maLop);
    public List<lop> findAllLop();
    public void deleteById(String id);
}
