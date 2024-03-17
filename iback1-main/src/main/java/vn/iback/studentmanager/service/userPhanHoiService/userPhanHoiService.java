package vn.iback.studentmanager.service.userPhanHoiService;

import vn.iback.studentmanager.entity.userPhanHoi;
import vn.iback.studentmanager.entity.userPost;

import java.util.List;

public interface userPhanHoiService {
    public userPhanHoi findById(int id);
    public void save(userPhanHoi userPhanHoi);
    public void update(userPhanHoi userPhanHoi);
    public List<userPhanHoi> findAll();
    public void deleteById(int id);
}
