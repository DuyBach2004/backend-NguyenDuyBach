package vn.iback.studentmanager.service.baiVietService;

import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.classSchool;

import java.util.List;

public interface baiVietService {
    public void save(baiViet baiViet);
    public void update(baiViet baiViet);
    public void saveAndUpdate(baiViet baiViet);
    public baiViet findClassByid(int id);
    public List<baiViet> findAllBaiViet();
    public void deleteById(int id);


}
