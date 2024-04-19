package vn.iback.studentmanager.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.subjectService;

import java.util.List;
import java.util.Random;

@RestController
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
    @GetMapping("")
    public List<specialization> showPageClassApi(Model model){
        List<specialization> specializations = specializationService.findAllSpecialization();
        return specializations;
    }

    @GetMapping("/ma-chuyen-nghanh")
    public specialization getById(@RequestParam String id){
        try {
            specialization specialization1 = specializationService.findSpecializationByid(id);
            if (specialization1 != null){
                return specialization1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy chuyên ngành với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-chuyen-nghanh")
    public ResponseEntity deleteSpecializationApi(@RequestParam String id){
        specialization existing = specializationService.findSpecializationByid(id);
        if (existing != null){
            specializationService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-chuyen-nghanh")
    public ResponseEntity updateSpecialization(@RequestParam String id, @RequestBody specialization specialization, Model model){
        specialization specialization1 = specializationService.findSpecializationByid(id);
        try {
            if (specialization1 != null){
                specialization1.setSpecializedName(specialization.getSpecializedName());
                specialization1.setMaKhoa(specialization.getMaKhoa());
                specialization1.setNumberOfCredits(specialization.getNumberOfCredits());
                specializationService.update(specialization1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createSpecializationApi(@RequestBody specialization specialization){
        try {
            if (specialization != null){
                Random random = new Random();
                int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

                // Kết hợp chuỗi "CN" và số ngẫu nhiên để tạo MaChuyenNghanh
                String MaChuyenNghanh = "CN" + randomNumber;
                specialization.setSpecializedCode(MaChuyenNghanh);
                specializationService.save(specialization);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/showSpecialization")
//    public String showSpecialization(Model model){
//        List<specialization> specializations=specializationService.findAllSpecialization();
//        model.addAttribute("specializations",specializations);
//        return "specialization/home";
//    }
//    @GetMapping("/showSpecialization-rest-api")
//    public List<specialization> showSpecializationApi(){
//        List<specialization> specializations=specializationService.findAllSpecialization();
//        System.out.println(specializations);
//        return specializations;
//    }
//    @PostMapping("/create-rest-api")
//    public specialization createSpecializationApi(@RequestBody specialization specialization){
//        System.out.println(specialization);
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String MaChuyenNghanh = "CN" + randomNumber;
//        specialization.setSpecializedCode(MaChuyenNghanh);
//        specializationService.save(specialization);
//        return specialization;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity deleteLichApi(@PathVariable String id, Model model){
//        specialization existing=specializationService.findSpecializationByid(id);
//        if (existing!=null){
//            specializationService.deleteById(id);
//            return ResponseEntity.ok("success");
//        }else{
//            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public specialization updateLop(@PathVariable String id,@RequestBody specialization specialization,Model model){
//        vn.iback.studentmanager.entity.specialization specialization1= specializationService.findSpecializationByid(id);
//        specialization1.setSpecializedName(specialization.getSpecializedName());
//        specialization1.setMaKhoa(specialization.getMaKhoa());
//        specialization1.setNumberOfCredits(specialization.getNumberOfCredits());
//        specializationService.update(specialization1);
//        return specialization1;
//    }
//    @GetMapping("/showListSpecialization-subject")
//    public String showListSpecializationSubject(@RequestParam("id") String id,Model model){
//            subjects subjects=subjectService.findNSubjectByid(id);
//            List<specialization> specializationList=specializationService.findAllSpecialization();
//            model.addAttribute("subjects",subjects);
//            model.addAttribute("specializations",specializationList);
//            return "specialization/home";
//    }
//    @GetMapping("/showListSpecialization-lop")
//    public String showListSpecializationLop(@RequestParam("id") String id,Model model){
//        lop lop=lopService.findLopById(id);
//        List<specialization> specializations=specializationService.findAllSpecialization();
//        model.addAttribute("specializations",specializations);
//        model.addAttribute("lop",lop);
//        return "specialization/home";
//    }
//    @GetMapping("/showListSpecialization-user")
//    public String showListSpecializationUser(@RequestParam("id") String username,Model model){
//        user user=userservice.findByUsername(username);
//        List<specialization> specializations=specializationService.findAllSpecialization();
//        model.addAttribute("specializations",specializations);
//        model.addAttribute("user",user);
//        return "specialization/home";
//    }
//    @PostMapping("/update-khoa")
//    public String updateKhoa(@RequestParam("selectedOption") String khoaId,@RequestParam("idChuyenNghanh") String idChuyenNghanh,Model model){
//        khoa khoa=khoaService.findkyHocid(khoaId);
//        specialization specialization=specializationService.findSpecializationByid(idChuyenNghanh);
//        specialization.setMaKhoa(khoa);
//        specializationService.update(specialization);
////        return "redirect:/specialization/showSpecialization";
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/update-khoa")
//    public String updateKhoa(@RequestParam("id") String khoaId,@ModelAttribute("specialization") specialization specialization,Model model){
//        khoa khoa=khoaService.findkyHocid(khoaId);
//        specialization.setMaKhoa(khoa);
//        specializationService.update(specialization);
//        return "redirect:/specialization/showSpecialization";
//    }

//    @GetMapping("/showPageUpdate")
//    public String showPageUpdate(@RequestParam("id") String id,Model model){
//        specialization specialization=specializationService.findSpecializationByid(id);
//        model.addAttribute("specialization",specialization);
//        return "specialization/create";
//    }
//
//    @GetMapping("/delete")
//    public String deleteShowSpecialization(@RequestParam("id") String id,Model model){
//        specializationService.deleteById(id);
////        return "redirect:/specialization/showSpecialization";
//        return "redirect:/home/showHome";
//    }
//    @GetMapping("/update")
//    public String updateShowSpecialization(@RequestParam("id") String id,Model model){
//        specializationService.deleteById(id);
//        return "redirect:/specialization/showSpecialization";
//
//    }

//    @GetMapping("/create")
//    public String createSpecialization(Model model){
//        specialization specialization=new specialization();
//        model.addAttribute("specialization",specialization);
//        return "specialization/create";
//    }

//    @PostMapping("/process")
//    public String processSpecialization(@Valid @ModelAttribute("specialization") specialization specialization, BindingResult result, Model model ){
//        if(result.hasErrors()){
//            return "specialization/create";
//        }
//        specializationService.update(specialization);
////        return "redirect:/specialization/showSpecialization";
//        return "redirect:/home/showHome";
//    }


}
