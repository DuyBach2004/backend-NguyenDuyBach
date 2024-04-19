package vn.iback.studentmanager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

@RestController
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
    @GetMapping("")
    public List<subjects> showPageClassApi(Model model){
        List<subjects> subjects = subjectService.findAll();
        return subjects;
    }

    @GetMapping("/ma-mon-hoc")
    public subjects getById(@RequestParam String id){
        try {
            subjects subjects1 = subjectService.findNSubjectByid(id);
            if (subjects1 != null){
                return subjects1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy môn học với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-mon-hoc")
    public ResponseEntity deleteSubjectsApi(@RequestParam String id){
        subjects existing = subjectService.findNSubjectByid(id);
        if (existing != null){
            subjectService.findNSubjectByid(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-mon-hoc")
    public ResponseEntity updateSubjects(@RequestParam String id, @RequestBody subjects subjects, Model model){
        subjects subjects1 = subjectService.findNSubjectByid(id);
        try {
            if (subjects1 != null){
                subjects1.setMaChuyenNghanh(subjects.getMaChuyenNghanh());
                subjects1.setSubjectName(subjects.getSubjectName());
                subjects1.setNumberOfCredits(subjects.getNumberOfCredits());
                subjectService.update(subjects1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createSubjectsApi(@RequestBody subjects subjects){
        try {
            if (subjects != null){
                        Random random= new Random();
        int randomNumber=random.nextInt(1000000);
        String subjectid= "MH"+randomNumber;
        subjects.setSubject_id(subjectid);
                subjectService.update(subjects);
                return ResponseEntity.ok("success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/showListSubject")
//    public String showListSubject(@RequestParam("id") String id, Model model){
//         notebook notebook = nootebookService.findNotebookByid(Integer.parseInt(id));
//        List<subjects> subjects=subjectService.findAll();
//        model.addAttribute("subjectList",subjects);
//        model.addAttribute("notebook",notebook);
//        return "subject/home";
//    }
//    @GetMapping("/showListSubject-rest-api")
//    public List<subjects> showListSubjectApi(Model model){
//        List<subjects> subjects=subjectService.findAll();
//        return subjects;
//    }
//    @PostMapping("/create-rest-api")
//    public subjects createRest(@RequestBody subjects subjects){
//        Random random= new Random();
//        int randomNumber=random.nextInt(1000000);
//        String subjectid= "MH"+randomNumber;
//        subjects.setSubject_id(subjectid);
//        subjectService.update(subjects);
//        return subjects;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity deleteLichApi(@PathVariable String id, Model model){
//        subjects existing=subjectService.findNSubjectByid(id);
//        if (existing!=null){
//            subjectService.findNSubjectByid(id);
//            return ResponseEntity.ok("success");
//        }else{
//            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public subjects updateLop(@PathVariable String id,@RequestBody subjects subjects,Model model){
//        vn.iback.studentmanager.entity.subjects subjects1=subjectService.findNSubjectByid(id);
//        subjects1.setMaChuyenNghanh(subjects.getMaChuyenNghanh());
//        subjects1.setSubjectName(subjects.getSubjectName());
//        subjects1.setNumberOfCredits(subjects.getNumberOfCredits());
//        subjectService.update(subjects1);
//        return subjects1;
//    }
//    @GetMapping("/updateCredits")
//    public String updateCredits(@RequestParam("subjectId") String subjectId ,@RequestParam("id") String id,Model model){
//        subjects subjects=subjectService.findNSubjectByid(subjectId);
//        if(id.equals("1")){
//            subjects.setNumberOfCredits(1);
//        } else if (id.equals("2")) {
//            subjects.setNumberOfCredits(2);
//        }else if (id.equals("3")) {
//            subjects.setNumberOfCredits(3);
//        }else if (id.equals("4")) {
//            subjects.setNumberOfCredits(4);
//        }
//        subjectService.update(subjects);
//
//        return "redirect:/subject/showHomeSubject";
//    }


//    @GetMapping("/showHomeSubject")
//    public String showListSubject(Model model){
//        List<subjects> subjects =subjectService.findAll();
//        model.addAttribute("subjectList",subjects);
//        return "subject/home";
//    }
//    @GetMapping("/showListSubject-class")
//    public String showListSubjectClass(@RequestParam("id") int id,Model model){
//        classSchool classSchool=classService.findClassByid(id);
//        List<subjects> subjectsList=subjectService.findAll();
//        model.addAttribute("classSchool",classSchool);
//        model.addAttribute("subjectList",subjectsList);
//        return "subject/home";
//    }
//    @PostMapping("/update-specialization")
//    public String updateSpecialization(@ModelAttribute("subjects") subjects subjects,@RequestParam("id") String id,Model model){
//        specialization specialization=specializationService.findSpecializationByid(id);
//        subjects.setMaChuyenNghanh(specialization);
//        subjectService.update(subjects);
//        return "redirect:/subject/showHomeSubject";
//    }

//    @PostMapping("/update-specialization")
//    public String updateSpecialization(@RequestParam("selectedOption") String idChuyenNghanh,@RequestParam("idSubject") String idSubject,Model model){
//        specialization specialization=specializationService.findSpecializationByid(idChuyenNghanh);
//        subjects subjects=subjectService.findNSubjectByid(idSubject);
//        subjects.setMaChuyenNghanh(specialization);
//        subjectService.update(subjects);
//        return "redirect:/home/showHome";
//    }
//    @GetMapping("/delete")
//    public String deleteSubject(@RequestParam("id") String id, Model model  ){
//        subjectService.deleteById(id);
////        return "redirect:/subject/showHomeSubject";
//        return "redirect:/home/showHome";
//    }
//
//    @GetMapping("/create")
//    public String create(Model model){
//        subjects subjects=new subjects();
//        model.addAttribute("subject",subjects);
//        return "subject/create";
//    }

//    @GetMapping("/showPageUpdate")
//    public String updateSubject(@RequestParam("id") String id,Model model){
//        subjects subjects=subjectService.findNSubjectByid(id);
//        model.addAttribute("subject",subjects);
//        return "subject/update";
//    }
//    @PostMapping("/update")
//    public  String updateProcess(@ModelAttribute("subject") subjects subjects,Model model){
//                subjectService.update(subjects);
////                return "redirect:/subject/showHomeSubject";
//        return "redirect:/home/showHome";
//    }
//
//    @PostMapping("/process")
//    public String process(@Valid @ModelAttribute("subject") subjects subjects, BindingResult result, Model model, HttpSession session){
//
//        if(result.hasErrors()){
//            return "class/create";
//        }
//        Random random= new Random();
//        int randomNumber=random.nextInt(1000000);
//        String subjectid= "MH"+randomNumber;
//        subjects.setSubject_id(subjectid);
//        subjectService.update(subjects);
//
////        return "redirect:/subject/showHomeSubject";
//        return "redirect:/home/showHome";
//    }

}
