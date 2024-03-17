package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.thoiGianHoc;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService;

import java.util.List;

@Controller
@RequestMapping("/thoi-gian-hoc")
public class ThoiGianHocController {
    private thoiGianHocService thoiGianHocService;
    private classService classService;
    @Autowired
    public ThoiGianHocController(vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService thoiGianHocService, vn.iback.studentmanager.service.classService classService) {
        this.thoiGianHocService = thoiGianHocService;
        this.classService = classService;
    }

    @GetMapping("/updateCredits")
    public String updateCredits(@RequestParam("id") int id,@RequestParam("thoiGianId") int thoiGianId,Model model){
        thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(thoiGianId);
        if(id==1){
            thoiGianHoc.setTietHoc("1-3");
        } else if (id==3) {
            thoiGianHoc.setTietHoc("3-4");
        }else if (id==4) {
            thoiGianHoc.setTietHoc("4-6");
        }else if (id==7) {
            thoiGianHoc.setTietHoc("7-9");
        }else if (id==10) {
            thoiGianHoc.setTietHoc("10-12");
        }else if (id==13) {
            thoiGianHoc.setTietHoc("13-15");
        }
        thoiGianHocService.update(thoiGianHoc);
        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";

    }
    @GetMapping("/updateThuTrongTuan")
    public String updateThuTrongTuan(@RequestParam("id") int id,@RequestParam("thoiGianId") int thoiGianId,Model model){
        thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(thoiGianId);
        if(id==1){
            thoiGianHoc.setThuTrongTuan("Monday");
        } else if (id==2) {
            thoiGianHoc.setThuTrongTuan("Tuesday");
        }else if (id==3) {
            thoiGianHoc.setThuTrongTuan("Wednesday");
        }else if (id==4) {
            thoiGianHoc.setThuTrongTuan("Thursday");
        }else if (id==5) {
            thoiGianHoc.setThuTrongTuan("Friday");
        }else if (id==6) {
            thoiGianHoc.setThuTrongTuan("Saturday");
        }else if (id==7) {
            thoiGianHoc.setThuTrongTuan("Sunday");
        }
        thoiGianHocService.update(thoiGianHoc);
        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";

    }
    @GetMapping("/delete")
    public String deleteThoiGianHoc(@RequestParam("id") int id,Model model){
        thoiGianHocService.deleteById(id);
        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
    }

    @GetMapping("/showPageThoiGianHoc")
    public String showPageThoiGianHoc(Model model){
        List<thoiGianHoc> thoiGianHocs=thoiGianHocService.findAllThoiGianHoc();
        model.addAttribute("thoiGianHocList",thoiGianHocs);
        return "thoigianhoc/home";
    }
    @GetMapping("/showListThoiGianHoc-class")
    public String showListThoiGianHocClass(@RequestParam("id") int id ,Model model){
        List<thoiGianHoc> thoiGianHocs=thoiGianHocService.findAllThoiGianHoc();
        classSchool classSchool=classService.findClassByid(id);
        model.addAttribute("thoiGianHocList",thoiGianHocs);
        model.addAttribute("classSchool",classSchool);
        return "thoigianhoc/home";
    }
    @GetMapping("/create")
    public String createThoiGianHoc(Model model){
        thoiGianHoc thoiGianHoc=new thoiGianHoc();
        thoiGianHocService.save(thoiGianHoc);
        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
    }

}
