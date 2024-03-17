package vn.iback.studentmanager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.notebook;
import vn.iback.studentmanager.entity.specialization;
import vn.iback.studentmanager.entity.subjects;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.nootebookService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.subjectService;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    private nootebookService nootebookService;
    private subjectService subjectService;
    private specializationService specializationService;
    private classService classService;
    @Autowired
    public SubjectController(vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.subjectService subjectService, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.classService classService) {
        this.nootebookService = nootebookService;
        this.subjectService = subjectService;
        this.specializationService = specializationService;
        this.classService = classService;
    }

    @GetMapping("/showListSubject")
    public String showListSubject(@RequestParam("id") String id, Model model){
         notebook notebook = nootebookService.findNotebookByid(Integer.parseInt(id));
        List<subjects> subjects=subjectService.findAll();
        model.addAttribute("subjectList",subjects);
        model.addAttribute("notebook",notebook);
        return "subject/home";
    }
    @GetMapping("/updateCredits")
    public String updateCredits(@RequestParam("subjectId") String subjectId ,@RequestParam("id") String id,Model model){
        subjects subjects=subjectService.findNSubjectByid(subjectId);
        if(id.equals("1")){
            subjects.setNumberOfCredits(1);
        } else if (id.equals("2")) {
            subjects.setNumberOfCredits(2);
        }else if (id.equals("3")) {
            subjects.setNumberOfCredits(3);
        }else if (id.equals("4")) {
            subjects.setNumberOfCredits(4);
        }
        subjectService.update(subjects);

        return "redirect:/subject/showHomeSubject";
    }


    @GetMapping("/showHomeSubject")
    public String showListSubject(Model model){
        List<subjects> subjects =subjectService.findAll();
        model.addAttribute("subjectList",subjects);
        return "subject/home";
    }
    @GetMapping("/showListSubject-class")
    public String showListSubjectClass(@RequestParam("id") int id,Model model){
        classSchool classSchool=classService.findClassByid(id);
        List<subjects> subjectsList=subjectService.findAll();
        model.addAttribute("classSchool",classSchool);
        model.addAttribute("subjectList",subjectsList);
        return "subject/home";
    }
    @PostMapping("/update-specialization")
    public String updateSpecialization(@ModelAttribute("subjects") subjects subjects,@RequestParam("id") String id,Model model){
        specialization specialization=specializationService.findSpecializationByid(id);
        subjects.setMaChuyenNghanh(specialization);
        subjectService.update(subjects);
        return "redirect:/subject/showHomeSubject";
    }
    @GetMapping("/delete")
    public String deleteSubject(@RequestParam("id") String id, Model model  ){
        subjectService.deleteById(id);
        return "redirect:/subject/showHomeSubject";
    }

    @GetMapping("/create")
    public String create(Model model){
        subjects subjects=new subjects();
        model.addAttribute("subject",subjects);
        return "subject/create";
    }
    @GetMapping("/showPageUpdate")
    public String updateSubject(@RequestParam("id") String id,Model model){
        subjects subjects=subjectService.findNSubjectByid(id);
        model.addAttribute("subject",subjects);
        return "subject/update";
    }
    @PostMapping("/update")
    public  String updateProcess(@ModelAttribute("subject") subjects subjects,Model model){
                subjectService.update(subjects);
                return "redirect:/subject/showHomeSubject";
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("subject") subjects subjects, BindingResult result, Model model, HttpSession session){

        if(result.hasErrors()){
            return "class/create";
        }
        Random random= new Random();
        int randomNumber=random.nextInt(1000000);
        String subjectid= "MH"+randomNumber;
        subjects.setSubject_id(subjectid);
        subjectService.update(subjects);

        return "redirect:/subject/showHomeSubject";

    }

}
