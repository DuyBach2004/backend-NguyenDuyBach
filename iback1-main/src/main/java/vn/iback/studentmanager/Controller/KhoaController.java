package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.teacherService;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/khoa")
public class KhoaController {
    private khoaService khoaService;
    private specializationService specializationService;
    private teacherService teacherService;
    private lopService lopService;
    private Userservice userservice;
    @Autowired
    public KhoaController(vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.lopSevice.lopService lopService, Userservice userservice) {
        this.khoaService = khoaService;
        this.specializationService = specializationService;
        this.teacherService = teacherService;
        this.lopService = lopService;
        this.userservice = userservice;
    }

    @GetMapping("/showPageKhoa")
    public String showPageKhoa(Model model){
        List<khoa> khoaList=khoaService.findAllKhoa();
        model.addAttribute("khoaList",khoaList);
        return "khoa/home";
    }
    @GetMapping("/showListKhoa-specialization")
    public String showPageKhoa(@RequestParam("id") String id,Model model){
        specialization specialization=specializationService.findSpecializationByid(id);
        List<khoa> khoaList=khoaService.findAllKhoa();
        model.addAttribute("specialization",specialization);
        model.addAttribute("khoaList",khoaList);
        return "khoa/home";
    }
    @GetMapping("/showListKhoa-lop")
    public String showListKhoaLop(@RequestParam("id") String id,Model model){
        lop lop=lopService.findLopById(id);
        List<khoa> khoaList=khoaService.findAllKhoa();
        model.addAttribute("lop",lop);
        model.addAttribute("khoaList",khoaList);
        return "khoa/home";
    }
    @GetMapping("/showListKhoa-teacher")
    public String showListKhoaTeacher(@RequestParam("id") String id,Model model){
            staff staff=teacherService.findTeacherByid(id);
            List<khoa> khoaList=khoaService.findAllKhoa();
            model.addAttribute("teacher",staff);
            model.addAttribute("khoaList",khoaList);
            return "khoa/home";
    }
    @GetMapping("/showListKhoa-user")
    public String showListKhoaUser(@RequestParam("id") String id,Model model){
        user user=userservice.findByUsername(id);
        List<khoa> khoaList=khoaService.findAllKhoa();
        model.addAttribute("user",user);
        model.addAttribute("khoaList",khoaList);
        return "khoa/home";
    }

    @GetMapping("/delete")
    public String deleteKhoa(@RequestParam("id") String maKhoa,Model model){
        khoaService.deleteById(maKhoa);
        return "redirect:/khoa/showPageKhoa";
    }
    @GetMapping("/showPageUpdate")
    public String showPageUpdate(@RequestParam("id") String id,Model model){
        khoa khoa=khoaService.findkyHocid(id);
        model.addAttribute("khoa",khoa);
        return "khoa/update";
    }
    @PostMapping("update")
    public String updateKhoa(@ModelAttribute("khoa") khoa khoa,Model model){
        khoaService.update(khoa);
        return "redirect:/khoa/showPageKhoa";
    }

    @GetMapping("/create")
    private String createKhoa(Model model){
        khoa khoa = new khoa();
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
        String Khoaid = "DHCN" + randomNumber;
        khoa.setMaKhoa(Khoaid);
        khoaService.save(khoa);
        return "redirect:/khoa/showPageKhoa";
    }

}
