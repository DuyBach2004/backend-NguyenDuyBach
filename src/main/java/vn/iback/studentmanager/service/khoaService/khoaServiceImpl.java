package vn.iback.studentmanager.service.khoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.KhoaRespository;
import vn.iback.studentmanager.dao.KyhocRespository;
import vn.iback.studentmanager.entity.khoa;

import java.util.List;
@Service
public class khoaServiceImpl implements khoaService {
    private KhoaRespository khoaRespository;
    @Autowired
    public khoaServiceImpl(KhoaRespository khoaRespository) {
        this.khoaRespository = khoaRespository;
    }

    @Override
    public void save(khoa khoa) {
        khoaRespository.save(khoa);
    }

    @Override
    public void update(khoa khoa) {
        khoaRespository.saveAndFlush(khoa);
    }

    @Override
    public khoa findkyHocid(String maKhoa) {
        return khoaRespository.getById(maKhoa);
    }

    @Override
    public List<khoa> findAllKhoa() {
        return khoaRespository.findAll();
    }

    @Override
    public void deleteById(String id) {
            khoaRespository.deleteById(id);
    }
}
