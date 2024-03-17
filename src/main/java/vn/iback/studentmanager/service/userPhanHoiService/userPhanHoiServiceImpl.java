package vn.iback.studentmanager.service.userPhanHoiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.UserPhanHoiResponsitory;
import vn.iback.studentmanager.entity.userPhanHoi;

import java.util.List;
@Service
public class userPhanHoiServiceImpl implements userPhanHoiService {
    private UserPhanHoiResponsitory userPhanHoiResponsitory;
    @Autowired
    public userPhanHoiServiceImpl(UserPhanHoiResponsitory userPhanHoiResponsitory) {
        this.userPhanHoiResponsitory = userPhanHoiResponsitory;
    }

    @Override
    public userPhanHoi findById(int id) {
        return userPhanHoiResponsitory.getById(id);
    }

    @Override
    public void save(userPhanHoi userPhanHoi) {
            userPhanHoiResponsitory.save(userPhanHoi);
    }

    @Override
    public void update(userPhanHoi userPhanHoi) {
            userPhanHoiResponsitory.saveAndFlush(userPhanHoi);
    }

    @Override
    public List<userPhanHoi> findAll() {
        return userPhanHoiResponsitory.findAll();
    }

    @Override
    public void deleteById(int id) {
            userPhanHoiResponsitory.deleteById(id);
    }
}
