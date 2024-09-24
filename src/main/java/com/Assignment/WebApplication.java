package com.Assignment;

import com.Assignment.DAO.LoginDAO;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
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
    public String UploadImage(@SessionAttribute("loginDTO")LoginDTO loginDTO,@RequestParam("profile")
    CommonsMultipartFile file, HttpSession session, Model model,Principal principal)  {

        model.addAttribute("username",principal.getName().toUpperCase());
        byte[] data=file.getBytes();
        String path = session.getServletContext().getRealPath("/")+"Image"+File.separator+file.getOriginalFilename();
        System.out.println(path);

        try{
            FileOutputStream fos=new FileOutputStream(path);
            fos.write(data);
            fos.close();
            //model.addAttribute("fileUpload",file.getOriginalFilename());
                                           //Read File
              PDDocument document=PDDocument.load(new File("C:/Users/Pericent/IdeaProjects/Assignment/src/main/webapp/Image/"+file.getOriginalFilename()));
              PDFTextStripper text=new PDFTextStripper();
              String pdf= text.getText(document);
              model.addAttribute("pdf",pdf);
              System.out.println(pdf);
                                           //write file
            PDDocument document1=new PDDocument();
            PDPage pdPage=new PDPage();
            document1.addPage(pdPage);
            PDPageContentStream contentStream=new PDPageContentStream(document1,pdPage);
            contentStream.beginText();
            contentStream.newLineAtOffset(100,700);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);
           // contentStream.showText();
            contentStream.endText();
            contentStream.close();
            document1.save("D:/pdf/new2pdf.pdf");
                                            //Merge file
            File file1=new File("C:/Users/Pericent/IdeaProjects/Assignment/src/main/webapp/Image/new.pdf");
            File file2=new File("D:/pdf/new2pdf.pdf");
            PDFMergerUtility pdfMergerUtility=new PDFMergerUtility();
            pdfMergerUtility.setDestinationFileName("D:/MergePdf/newMerge.pdf");
            pdfMergerUtility.addSource(file1);
            pdfMergerUtility.addSource(file2);
            pdfMergerUtility.mergeDocuments();
        }
        catch (IOException e) {
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

//        try{
////C:/Users/Pericent/IdeaProjects/Assignment/src/main/webapp/Image/Nancy Resume.pdf
////Read File
//PDDocument document=PDDocument.load("C:/Users/Pericent/IdeaProjects/Assignment/src/main/webapp/Image/"+file.getOriginalFilename());
//PDFTextStripper text=new PDFTextStripper();
//String pdf= text.getText(document);

//write file

//            PDDocument document1=new PDDocument();
//            PDPage pdPage=new PDPage();
//            document1.addPage(pdPage);
//            PDPageContentStream contentStream=new PDPageContentStream(document1,pdPage);
//            contentStream.beginText();
//            contentStream.newLineAtOffset(100,700);
//            contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);
//            contentStream.showText("hello,pdf");
//            contentStream.endText();
//FileOutputStream fos=new FileOutputStream(path);
//            fos.write(data);
//            fos.close();
//            model.addAttribute("fileUpload",file.getOriginalFilename());
//        model.addAttribute("pdf",pdf);
//            System.out.println(pdf);