package vn.iback.studentmanager.service.baiVietService;

import vn.iback.studentmanager.dto.postDTO;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.classSchool;

import java.util.List;

public interface baiVietService {
    public baiViet save(baiViet baiViet);
    public void update(baiViet baiViet);
    public void saveAndUpdate(baiViet baiViet);
    public baiViet findClassByid(int id);
    public List<baiViet> findAllBaiViet();
    public List<postDTO> findBaiVietByUser(String username);
    public void deleteById(int id);
    public boolean exitsById(int id);

}
