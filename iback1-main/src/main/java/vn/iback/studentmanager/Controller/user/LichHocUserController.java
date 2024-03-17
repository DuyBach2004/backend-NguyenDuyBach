package vn.iback.studentmanager.Controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iback.studentmanager.entity.lichHoc;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.lichHocService.lichHocService;

import java.util.List;
@Controller
@RequestMapping("lich-hoc-user")
public class LichHocUserController {
    private vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService;
    private Userservice userservice;
    @Autowired
    public LichHocUserController(vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService, Userservice userservice) {
        this.lichHocService = lichHocService;
        this.userservice = userservice;
    }
    @GetMapping("/showPageLichHocUser")
    public String showPageLichHocUser(@RequestParam("username") String username , Model model){
        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
        user user=userservice.findByUsername(username);

        model.addAttribute("lichHocList",lichHocList);
        model.addAttribute("user",user);
        return "lichhoc/showLichHocUser";
    }

}
