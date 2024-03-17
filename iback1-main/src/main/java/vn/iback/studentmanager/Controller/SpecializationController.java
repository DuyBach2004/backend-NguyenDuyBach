package vn.iback.studentmanager.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.subjectService;

import java.util.List;

@Controller
@RequestMapping("/specialization")
public class SpecializationController {
    private specializationService specializationService;
    private khoaService khoaService;
    private subjectService subjectService;
    private lopService lopService;
    private classService classService;
    private Userservice userservice;
    @Autowired
    public SpecializationController(vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.subjectService subjectService, vn.iback.studentmanager.service.lopSevice.lopService lopService, vn.iback.studentmanager.service.classService classService, Userservice userservice) {
        this.specializationService = specializationService;
        this.khoaService = khoaService;
        this.subjectService = subjectService;
        this.lopService = lopService;
        this.classService = classService;
        this.userservice = userservice;
    }

    @GetMapping("/showSpecialization")
    public String showSpecialization(Model model){
        List<specialization> specializations=specializationService.findAllSpecialization();
        model.addAttribute("specializations",specializations);
        return "specialization/home";
    }
    @GetMapping("/showListSpecialization-subject")
    public String showListSpecializationSubject(@RequestParam("id") String id,Model model){
            subjects subjects=subjectService.findNSubjectByid(id);
            List<specialization> specializationList=specializationService.findAllSpecialization();
            model.addAttribute("subjects",subjects);
            model.addAttribute("specializations",specializationList);
            return "specialization/home";
    }
    @GetMapping("/showListSpecialization-lop")
    public String showListSpecializationLop(@RequestParam("id") String id,Model model){
        lop lop=lopService.findLopById(id);
        List<specialization> specializations=specializationService.findAllSpecialization();
        model.addAttribute("specializations",specializations);
        model.addAttribute("lop",lop);
        return "specialization/home";
    }
    @GetMapping("/showListSpecialization-user")
    public String showListSpecializationUser(@RequestParam("id") String username,Model model){
        user user=userservice.findByUsername(username);
        List<specialization> specializations=specializationService.findAllSpecialization();
        model.addAttribute("specializations",specializations);
        model.addAttribute("user",user);
        return "specialization/home";
    }

    @PostMapping("/update-khoa")
    public String updateKhoa(@RequestParam("id") String khoaId,@ModelAttribute("specialization") specialization specialization,Model model){
        khoa khoa=khoaService.findkyHocid(khoaId);
        specialization.setMaKhoa(khoa);
        specializationService.update(specialization);
        return "redirect:/specialization/showSpecialization";
    }

    @GetMapping("/showPageUpdate")
    public String showPageUpdate(@RequestParam("id") String id,Model model){
        specialization specialization=specializationService.findSpecializationByid(id);
        model.addAttribute("specialization",specialization);
        return "specialization/create";
    }

    @GetMapping("/delete")
    public String deleteShowSpecialization(@RequestParam("id") String id,Model model){
        specializationService.deleteById(id);
        return "redirect:/specialization/showSpecialization";

    }
    @GetMapping("/update")
    public String updateShowSpecialization(@RequestParam("id") String id,Model model){
        specializationService.deleteById(id);
        return "redirect:/specialization/showSpecialization";

    }

    @GetMapping("/create")
    public String createSpecialization(Model model){
        specialization specialization=new specialization();
        model.addAttribute("specialization",specialization);
        return "specialization/create";
    }
    @PostMapping("/process")
    public String processSpecialization(@Valid @ModelAttribute("specialization") specialization specialization, BindingResult result, Model model ){
        if(result.hasErrors()){
            return "specialization/create";
        }
        specializationService.update(specialization);
        return "redirect:/specialization/showSpecialization";
    }

}
