package vn.iback.studentmanager.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.kyHoc;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.hokyservice.hokyService;

import java.util.List;

@Controller
@RequestMapping("/hocKy")
public class HokyController {
    private hokyService hokyService;
    private classService classService;
    @Autowired
    public HokyController(vn.iback.studentmanager.service.hokyservice.hokyService hokyService, vn.iback.studentmanager.service.classService classService) {
        this.hokyService = hokyService;
        this.classService = classService;
    }

    @GetMapping("/showHocKy")
    public String showHocKy(Model model){
        List<kyHoc> kyHocs=hokyService.findAllKyHoc();
        model.addAttribute("hocKyList",kyHocs);
        return "hocky/home";
    }
    @GetMapping("/delete")
    public String deleteHocKy(@RequestParam("id") int id,Model model){
        hokyService.deleteById(id);
        return "redirect:/hocKy/showHocKy";
    }
    @GetMapping("/showListHocKy-class")
    public String showListHocKyClass(@RequestParam("id") int id,Model model){
        classSchool classSchool=classService.findClassByid(id);
        List<kyHoc> hocKyList=hokyService.findAllKyHoc();
        model.addAttribute("classSchool",classSchool);
        model.addAttribute("hocKyList",hocKyList);
        return "hocky/home";
    }


    @GetMapping("/showUpdateHocKy")
    private String showUpdateHocKy(@RequestParam("id") int id,Model model){
        kyHoc kyHoc=hokyService.findkyHocid(id);
        model.addAttribute("kyHoc",kyHoc);
        return "hocky/update";
    }
    @PostMapping("/update")
    private String updateHocKy(@ModelAttribute("kyHoc") kyHoc kyHoc,Model model){
        hokyService.update(kyHoc);
        return "redirect:/hocKy/showHocKy";
    }

    @GetMapping("/create")
    private String createHocKy(Model model){
        kyHoc kyHoc=new kyHoc();
        model.addAttribute("hocKy",kyHoc);
        return "hocky/create";
}
    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("hocKy") kyHoc kyHoc, BindingResult result,Model model){
        hokyService.save(kyHoc);
        return "redirect:/hocKy/showHocKy";
    }


}
