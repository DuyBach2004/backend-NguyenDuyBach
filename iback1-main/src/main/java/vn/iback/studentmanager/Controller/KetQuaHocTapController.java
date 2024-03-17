package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.diem;
import vn.iback.studentmanager.entity.ketQuaHocTap;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;

import java.util.List;

@Controller
@RequestMapping("/ketquahoctap")
public class KetQuaHocTapController {
    private ketQuaHocTapService ketQuaHocTapService;
    @Autowired
    public KetQuaHocTapController(vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService) {
        this.ketQuaHocTapService = ketQuaHocTapService;
    }
    @GetMapping("/showPageKetQuaHocTap")
    public String showPageKetQuaHocTap(Model model){
        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
        model.addAttribute("ketQuaHocTapList",ketQuaHocTapList);
        return "ketqua/home";
    }
    @GetMapping("/create")
    public String createDiem(Model model){
        ketQuaHocTap ketQuaHocTap=new ketQuaHocTap();
        ketQuaHocTap.setDiemTrungBinh(-1);
        ketQuaHocTap.setDiemRenLuyen(-1);

        ketQuaHocTapService.save(ketQuaHocTap);
        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
    }
    @GetMapping("/showPageUpdate")
    public String showPageUpdate(@RequestParam("id") int id,Model model){
        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(id);
        model.addAttribute("ketQuaHocTap",ketQuaHocTap);
        return "ketqua/update";
    }
    @PostMapping("/update")
    public String updateKetQua(@ModelAttribute("ketQuaHocTap") ketQuaHocTap ketQuaHocTap,Model model){

        System.out.println(ketQuaHocTap.getKetQuaHocTap());
        System.out.println(ketQuaHocTap.getDiemRenLuyen());
        ketQuaHocTapService.update(ketQuaHocTap);
        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
    }

    @GetMapping("delete")
    public String deleteDiem(@RequestParam("id") int id,Model model){
        ketQuaHocTapService.deleteById(id);
        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
    }

}
