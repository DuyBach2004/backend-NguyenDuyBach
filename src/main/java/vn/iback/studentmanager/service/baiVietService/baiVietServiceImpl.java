package vn.iback.studentmanager.service.baiVietService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.BaivietResponsitory;
import vn.iback.studentmanager.entity.baiViet;

import java.util.List;
@Service
public class baiVietServiceImpl implements baiVietService {
    private BaivietResponsitory baivietResponsitory;
    @Autowired
    public baiVietServiceImpl(BaivietResponsitory baivietResponsitory) {
        this.baivietResponsitory = baivietResponsitory;
    }

    @Override
    public void save(baiViet baiViet) {
        baivietResponsitory.save(baiViet);
    }

    @Override
    public void update(baiViet baiViet) {
            baivietResponsitory.saveAndFlush(baiViet);
    }

    @Override
    public void saveAndUpdate(baiViet baiViet) {
            baivietResponsitory.saveAndFlush(baiViet);
    }

    @Override
    public baiViet findClassByid(int id) {
        return baivietResponsitory.getById(id);
    }

    @Override
    public List<baiViet> findAllBaiViet() {
        return baivietResponsitory.findAll();
    }

    @Override
    public void deleteById(int id) {
        baivietResponsitory.deleteById(id);
    }
}
