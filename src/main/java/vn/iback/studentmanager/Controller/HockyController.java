package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iback.studentmanager.entity.kyHoc;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.hokyservice.hokyService;

import java.util.List;

@RestController
@RequestMapping("/hocKy")
public class HockyController {
    private hokyService hokyService;
    private classService classService;
    @Autowired
    public HockyController(vn.iback.studentmanager.service.hokyservice.hokyService hokyService, vn.iback.studentmanager.service.classService classService) {
        this.hokyService = hokyService;
        this.classService = classService;
    }
    @GetMapping("")
    public List<kyHoc> showPageClassApi(Model model){
        List<kyHoc> kyHocs = hokyService.findAllKyHoc();
        return kyHocs;
    }

    @GetMapping("/ma-hoc-ki")
    public kyHoc getById(@RequestParam int id){
        try {
            kyHoc kyHoc1 = hokyService.findkyHocid(id);
            if (kyHoc1 != null){
                return kyHoc1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy học kỳ với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-hoc-ki")
    public ResponseEntity deleteHocKyApi(@RequestParam int id){
        kyHoc existing = hokyService.findkyHocid(id);
        if (existing != null){
            hokyService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-hoc-ki")
    public ResponseEntity updateHocKy(@RequestParam int id, @RequestBody kyHoc kyHoc, Model model){
        kyHoc kyHoc1 = hokyService.findkyHocid(id);
        try {
            if (kyHoc1 != null){
                kyHoc1.setHocKi(kyHoc.getHocKi());
                kyHoc1.setNamHoc(kyHoc.getNamHoc());
                hokyService.update(kyHoc1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public kyHoc createHocKy(@RequestBody kyHoc kyHoc){
        hokyService.save(kyHoc);
        return kyHoc;
    }

//    @GetMapping("/showHocKyRestApi")
//    public List<kyHoc> showHocKyRestApi(){
//        List<kyHoc> kyHocs=hokyService.findAllKyHoc();
//
//        return kyHocs;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<Void> deleteHocKyApi(@PathVariable int id, Model model){
//        kyHoc existing= hokyService.findkyHocid(id);
//        if (existing!=null){
//            hokyService.deleteById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public kyHoc updateApi(@PathVariable int id,@RequestBody kyHoc kyHoc,Model model){
//        vn.iback.studentmanager.entity.kyHoc kyHoc1= hokyService.findkyHocid(id);
//        kyHoc1.setHocKi(kyHoc.getHocKi());
//        kyHoc1.setNamHoc(kyHoc.getNamHoc());
//        hokyService.update(kyHoc1);
//        return kyHoc;
//    }
//    @PostMapping("/create-rest-api")
//    private kyHoc createHocKy(@RequestBody kyHoc kyHoc){
//        hokyService.save(kyHoc);
//        return kyHoc;
//    }
//    @GetMapping("/showHocKy")
//    public String showHocKy(Model model){
//        List<kyHoc> kyHocs=hokyService.findAllKyHoc();
//        model.addAttribute("hocKyList",kyHocs);
//        return "hocky/home";
//    }

//    @GetMapping("/delete")
//    public String deleteHocKy(@RequestParam("id") int id,Model model){
//        hokyService.deleteById(id);
//        return "redirect:/hocKy/showHocKy";
//    }

//    @GetMapping("/showListHocKy-class")
//    public String showListHocKyClass(@RequestParam("id") int id,Model model){
//        classSchool classSchool=classService.findClassByid(id);
//        List<kyHoc> hocKyList=hokyService.findAllKyHoc();
//        model.addAttribute("classSchool",classSchool);
//        model.addAttribute("hocKyList",hocKyList);
//        return "hocky/home";
//    }


//    @GetMapping("/showUpdateHocKy")
//    private String showUpdateHocKy(@RequestParam("id") int id,Model model){
//        kyHoc kyHoc=hokyService.findkyHocid(id);
//        model.addAttribute("kyHoc",kyHoc);
//        return "hocky/update";
//    }
//    @PostMapping("/update")
//    private String updateHocKy(@ModelAttribute("kyHoc") kyHoc kyHoc,Model model){
//        hokyService.update(kyHoc);
//        return "redirect:/hocKy/showHocKy";
//    }

//    @GetMapping("/create")
//    private String createHocKy(Model model){
//        kyHoc kyHoc=new kyHoc();
//        model.addAttribute("hocKy",kyHoc);
//        return "hocky/create";
//}

//    @PostMapping("/process")
//    public String process(@Valid @ModelAttribute("hocKy") kyHoc kyHoc, BindingResult result,Model model){
//        hokyService.save(kyHoc);
//        return "redirect:/home/showHome";
//    }


}
