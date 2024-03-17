package vn.iback.studentmanager.service.ketQuaHocTapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.KetQuaHocTapRespository;
import vn.iback.studentmanager.entity.ketQuaHocTap;

import java.util.List;
@Service
public class ketQuaHocTapServiceImpl implements ketQuaHocTapService {
    private KetQuaHocTapRespository ketQuaHocTapRespository;
    @Autowired
    public ketQuaHocTapServiceImpl(KetQuaHocTapRespository ketQuaHocTapRespository) {
        this.ketQuaHocTapRespository = ketQuaHocTapRespository;
    }

    @Override
    public void save(ketQuaHocTap ketQuaHocTap) {
            ketQuaHocTapRespository.save(ketQuaHocTap);
    }

    @Override
    public void update(ketQuaHocTap ketQuaHocTap) {
            ketQuaHocTapRespository.saveAndFlush(ketQuaHocTap);
    }

    @Override
    public ketQuaHocTap findKetQuaHocTapById(int maDiem) {
        return ketQuaHocTapRespository.getById(maDiem);
    }

    @Override
    public List<ketQuaHocTap> findAllKetQuaHocTap() {
        return ketQuaHocTapRespository.findAll();
    }

    @Override
    public void deleteById(int id) {
            ketQuaHocTapRespository.deleteById(id);
    }
}
