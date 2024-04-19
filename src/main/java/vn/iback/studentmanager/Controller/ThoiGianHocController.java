package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.staff;
import vn.iback.studentmanager.entity.thoiGianHoc;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/thoi-gian-hoc")
public class ThoiGianHocController {
    private thoiGianHocService thoiGianHocService;
    private classService classService;
    @Autowired
    public ThoiGianHocController(vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService thoiGianHocService, vn.iback.studentmanager.service.classService classService) {
        this.thoiGianHocService = thoiGianHocService;
        this.classService = classService;
    }
    @GetMapping("")
    public List<thoiGianHoc> showPageThoiGianHocApi(Model model){
        List<thoiGianHoc> thoiGianHocs=thoiGianHocService.findAllThoiGianHoc();
        return thoiGianHocs;
    }
    @GetMapping("/ma-thoi-gian-hoc")
    public thoiGianHoc getById(@RequestParam int id){
        try {
            thoiGianHoc thoiGianHoc1 = thoiGianHocService.findkyHocid(id);
            if (thoiGianHoc1 != null){
                return thoiGianHoc1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy thời gian học với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-thoi-gian-hoc")
    public ResponseEntity deleteLichApi(@RequestParam int id){
        thoiGianHoc existing=thoiGianHocService.findkyHocid(id);
        if (existing!=null){
            thoiGianHocService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/ma-thoi-gian-hoc")
    public ResponseEntity updateLop(@RequestParam int id,@RequestBody thoiGianHoc thoiGianHoc,Model model){
        thoiGianHoc thoiGianHoc1=thoiGianHocService.findkyHocid(id);
        try {
            if (thoiGianHoc1!=null){
                thoiGianHoc1.setTietHoc(thoiGianHoc.getTietHoc());
                thoiGianHoc1.setThuTrongTuan(thoiGianHoc.getThuTrongTuan());
                thoiGianHocService.update(thoiGianHoc1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("")
    public staff createApi(@RequestBody staff teacher){
        String timeCurrently=String.valueOf(System.currentTimeMillis());
        Random random= new Random();
        int randomNumber=random.nextInt(100000);
        String teacherid= "GV"+randomNumber;
        teacher.setStaff_id(teacherid);

        return teacher;
    }
//    @PostMapping("/updateCredits")
//    public String updateCredits(@RequestParam("selectedOption") int id,@RequestParam("thoiGianId") int thoiGianId,Model model){
//        thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(thoiGianId);
//        if(id==1){
//            thoiGianHoc.setTietHoc("1-3");
//        } else if (id==3) {
//            thoiGianHoc.setTietHoc("3-4");
//        }else if (id==4) {
//            thoiGianHoc.setTietHoc("4-6");
//        }else if (id==7) {
//            thoiGianHoc.setTietHoc("7-9");
//        }else if (id==10) {
//            thoiGianHoc.setTietHoc("10-12");
//        }else if (id==13) {
//            thoiGianHoc.setTietHoc("13-15");
//        }
//        thoiGianHocService.update(thoiGianHoc);
////        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//        return "redirect:/home/showHome";
//
//    }
//    @PostMapping("/updateThuTrongTuan")
//    public String updateThuTrongTuan(@RequestParam("selectedOption") int id,@RequestParam("thoiGianId") int thoiGianId,Model model){
//        thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(thoiGianId);
//        if(id==1){
//            thoiGianHoc.setThuTrongTuan("Monday");
//        } else if (id==2) {
//            thoiGianHoc.setThuTrongTuan("Tuesday");
//        }else if (id==3) {
//            thoiGianHoc.setThuTrongTuan("Wednesday");
//        }else if (id==4) {
//            thoiGianHoc.setThuTrongTuan("Thursday");
//        }else if (id==5) {
//            thoiGianHoc.setThuTrongTuan("Friday");
//        }else if (id==6) {
//            thoiGianHoc.setThuTrongTuan("Saturday");
//        }else if (id==7) {
//            thoiGianHoc.setThuTrongTuan("Sunday");
//        }
//        thoiGianHocService.update(thoiGianHoc);
////        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//        return "redirect:/home/showHome";
//
//    }
//    @GetMapping("/delete")
//    public String deleteThoiGianHoc(@RequestParam("id") int id,Model model){
//        thoiGianHocService.deleteById(id);
////        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//        return "redirect:/home/showHome";
//    }
//
//    @GetMapping("/showPageThoiGianHoc")
//    public String showPageThoiGianHoc(Model model){
//        List<thoiGianHoc> thoiGianHocs=thoiGianHocService.findAllThoiGianHoc();
//        model.addAttribute("thoiGianHocList",thoiGianHocs);
//        return "thoigianhoc/home";
//    }

//    @GetMapping("/showListThoiGianHoc-class")
//    public String showListThoiGianHocClass(@RequestParam("id") int id ,Model model){
//        List<thoiGianHoc> thoiGianHocs=thoiGianHocService.findAllThoiGianHoc();
//        classSchool classSchool=classService.findClassByid(id);
//        model.addAttribute("thoiGianHocList",thoiGianHocs);
//        model.addAttribute("classSchool",classSchool);
//        return "thoigianhoc/home";
//    }

//    @GetMapping("/create")
//    public thoiGianHoc createThoiGianHoc(@RequestBody thoiGianHoc thoiGianHoc){
//        thoiGianHocService.save(thoiGianHoc);
////        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//        return thoiGianHoc;
//    }
//    @GetMapping("/create")
//    public String createThoiGianHoc(Model model){
//        thoiGianHoc thoiGianHoc=new thoiGianHoc();
//        thoiGianHocService.save(thoiGianHoc);
////        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/create-rest-api")
//    public thoiGianHoc createThoiGianHocApi(@RequestBody thoiGianHoc thoiGianHoc){
//        thoiGianHocService.save(thoiGianHoc);
////        return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//        return thoiGianHoc;
//    }

}
