package vn.iback.studentmanager.service.phanHoiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.PhanHoiResponsitory;
import vn.iback.studentmanager.entity.binhluan;
import vn.iback.studentmanager.entity.phanHoi;

import java.util.ArrayList;
import java.util.List;
@Service
public class phanHoiServiceImpl implements phanHoiService {
    private PhanHoiResponsitory phanHoiResponsitory;
    @Autowired
    public phanHoiServiceImpl(PhanHoiResponsitory phanHoiResponsitory) {
        this.phanHoiResponsitory = phanHoiResponsitory;
    }

    @Override
    public void save(phanHoi phanHoi) {
        phanHoiResponsitory.save(phanHoi);
    }

    @Override
    public void update(phanHoi phanHoi) {
        phanHoiResponsitory.saveAndFlush(phanHoi);
    }

    @Override
    public void saveAndUpdate(phanHoi phanHoi) {
        phanHoiResponsitory.saveAndFlush(phanHoi);
    }

    @Override
    public phanHoi findPhanHoiByid(int id) {
        return phanHoiResponsitory.getById(id);
    }

    @Override
    public List<phanHoi> findAllPhanHoi() {
        return phanHoiResponsitory.findAll();
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<phanHoi> findAllPhanHoiOfBinhLuan(binhluan binhluan) {
        List<phanHoi> phanHoiList=phanHoiResponsitory.findAll();
        List<phanHoi> list= new ArrayList<>();
        for (phanHoi phanHoi:phanHoiList){
            if (phanHoi.getBinhluan().getId()==binhluan.getId()){
                list.add(phanHoi);
            }
        }
        return list;
    }
}
