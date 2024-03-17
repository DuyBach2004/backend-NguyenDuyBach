package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.teacherService;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/lop")
public class LopController {
    private lopService lopService;
    private khoaService khoaService;
    private specializationService specializationService;
    private teacherService teacherService;
    private Userservice userservice;
    @Autowired
    public LopController(vn.iback.studentmanager.service.lopSevice.lopService lopService, vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.teacherService teacherService, Userservice userservice) {
        this.lopService = lopService;
        this.khoaService = khoaService;
        this.specializationService = specializationService;
        this.teacherService = teacherService;
        this.userservice = userservice;
    }

    @GetMapping("/showPageLop")
    public String showPageLop(Model model){
        List<lop> lopList=lopService.findAllLop();
        model.addAttribute("lopList",lopList);
        return "lop/home";
    }
    @PostMapping("/update-khoa")
    public String updateKhoa(@ModelAttribute("lop") lop lop,@RequestParam("id") String id,Model model){
        khoa khoa=khoaService.findkyHocid(id);
        lop.setKhoa(khoa);
        lopService.update(lop);
        return "redirect:/lop/showPageLop";
    }
    @PostMapping("/update-specialization")
    public String updateSpecialization(@ModelAttribute("lop") lop lop,@RequestParam("id") String id,Model model){
        specialization specialization=specializationService.findSpecializationByid(id);
        lop.setSpecialization(specialization);
        lopService.update(lop);
        return "redirect:/lop/showPageLop";
    }
    @PostMapping("/update-teacher")
    public String updateTeacher(@ModelAttribute("lop") lop lop,@RequestParam("id") String id,Model model){
        staff teacher=teacherService.findTeacherByid(id);
        lop.setStaff(teacher);
        lopService.update(lop);
        return "redirect:/lop/showPageLop";
    }
    @GetMapping("/showListClass-user")
    public String showListKhoaUser(@RequestParam("id") String id,Model model){
        user user=userservice.findByUsername(id);
        List<lop> lopList=lopService.findAllLop();
        model.addAttribute("user",user);
        model.addAttribute("lopList",lopList);
        return "lop/home";
    }

    @GetMapping("/delete")
    public String deleteLop(@RequestParam("id") String id,Model model){
        lopService.deleteById(id);
        return "redirect:/lop/showPageLop";
    }

    @GetMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") String id,Model model){
        lop lop=lopService.findLopById(id);
        model.addAttribute("lop",lop);
        return "lop/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("lop") lop lop,Model model){
        lopService.update(lop);
        return "redirect:/lop/showPageLop";
    }

    @GetMapping("/create")
    public String createLop(Model model){
        lop lop=new lop();
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
        String lopId= "LH" + randomNumber;
        lop.setMaLop(lopId);
        lopService.save(lop);
        return "redirect:/lop/showPageLop";
    }

}
