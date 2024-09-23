package com.Assignment;

import com.Assignment.DAO.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("loginDTO")
public class WebApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    LoginDAO loginDao;
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDTO",new LoginDTO());
        return "FrontLoginPage";
    }
    @RequestMapping("/SelectImage")
    public String SelectImage(Principal principal,Model model){
        String UserName=principal.getName();
        model.addAttribute("loginDTO",new LoginDTO());
        model.addAttribute("userName",UserName.toUpperCase());
        return "SelectImage";
    }
    @RequestMapping(value = "/UploadImage" ,method = RequestMethod.POST)
    public String UploadImage(@SessionAttribute("loginDTO")LoginDTO loginDTO,@RequestParam("profile")CommonsMultipartFile file,
                              HttpSession session, Model model,Principal principal)  {

        model.addAttribute("username",principal.getName().toUpperCase());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getStorageDescription());
        byte[] data=file.getBytes();
        String path = session.getServletContext().getRealPath("/")+"Image"+File.separator+file.getOriginalFilename();
        System.out.println(path);
        try{
            FileOutputStream fos=new FileOutputStream(path);
            fos.write(data);
            fos.close();
            System.out.println("file upload");
            model.addAttribute("fileUpload",file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return "UploadImage";
    }
    @RequestMapping("/Comment")
    public String Comment(@SessionAttribute("loginDTO") LoginDTO loginDTO
            ,Principal principal,@ModelAttribute("commentsDTO") CommentsDTO commentsDTO,Model model){
        model.addAttribute("username",principal.getName().toUpperCase());
        loginDao.InsertComments(commentsDTO,principal);
        return "Comment";
    }

    @RequestMapping("/ShowComments")
    public String ShowComments(Principal principal,Model model){
        List<CommentsDTO> commentsDTOS=loginDao.fetch(principal);
        model.addAttribute("commentsDTOS",commentsDTOS);
        String username=principal.getName();
        model.addAttribute("username",username.toUpperCase());

        return "ShowComments";
    }
    @RequestMapping("/Registration")
    public String Registration(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO){
        return "Registration";
    }
    @RequestMapping("/processRegistration")
    public String processRegistration(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO){
       String p= passwordEncoder.encode(registrationDTO.getPassword());

        loginDao.Insert(registrationDTO);
        return "redirect:/home/login";
    }
}