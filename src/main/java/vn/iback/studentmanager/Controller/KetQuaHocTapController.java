package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.diem;
import vn.iback.studentmanager.entity.ketQuaHocTap;
import vn.iback.studentmanager.entity.kyHoc;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.hokyservice.hokyService;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;

import java.util.List;

@Controller
@RequestMapping("/ketquahoctap")
public class KetQuaHocTapController {
    private ketQuaHocTapService ketQuaHocTapService;
    private Userservice userservice;
    private hokyService hokyService;
    @Autowired
    public KetQuaHocTapController(vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService, Userservice userservice, vn.iback.studentmanager.service.hokyservice.hokyService hokyService) {
        this.ketQuaHocTapService = ketQuaHocTapService;
        this.userservice = userservice;
        this.hokyService = hokyService;
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
//        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
        return "redirect:/home/showHome";
    }
    @GetMapping("/showPageUpdate")
    public String showPageUpdate(@RequestParam("id") int id,Model model){
        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(id);
        model.addAttribute("ketQuaHocTap",ketQuaHocTap);
        return "ketqua/update";
    }
    @PostMapping("/update")
    public String updateKetQua(@ModelAttribute("ketQuaHocTap") ketQuaHocTap ketQuaHocTap,Model model){

        System.out.println(ketQuaHocTap);
        System.out.println(ketQuaHocTap.getDiemRenLuyen());
        ketQuaHocTap.setDiemRenLuyen(ketQuaHocTap.getDiemRenLuyen());
        ketQuaHocTapService.update(ketQuaHocTap);
//        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
        return "redirect:/home/showHome";
    }
    @PostMapping("/update-user")
    public String updateUser(@RequestParam("selectedOption") String username,@RequestParam("idKetQua") int idKetQua,Model model){
        user user=userservice.findByUsername(username);
        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(idKetQua);
        ketQuaHocTap.setUser(user);
        ketQuaHocTapService.update(ketQuaHocTap);
        return "redirect:/home/showHome";
    }

    @PostMapping("/update-hocKy")
    public String updateHocKy(@RequestParam("selectedOption") int idHocky,@RequestParam("idKetQua") int idKetQua,Model model){
        kyHoc hcKyHoc=hokyService.findkyHocid(idHocky);
        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(idKetQua);
        ketQuaHocTap.setKyHoc(hcKyHoc);
        ketQuaHocTapService.update(ketQuaHocTap);
        return "redirect:/home/showHome";
    }


    @GetMapping("delete")
    public String deleteDiem(@RequestParam("id") int id,Model model){
        ketQuaHocTapService.deleteById(id);
//        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
        return "redirect:/home/showHome";
    }

}
