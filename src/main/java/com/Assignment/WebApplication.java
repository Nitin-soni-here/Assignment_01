package com.Assignment;

import com.Assignment.DAO.LoginDAO;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes({"loginDTO","parts"})
public class WebApplication {
    @Autowired
    private UtilityMethods utilityMethods;
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
        model.addAttribute("parts",new CommonMultiParts());
        model.addAttribute("userName",UserName.toUpperCase());
        return "SelectImage";
    }
    @RequestMapping(value = "/UploadImage")
    public String UploadImage(@SessionAttribute("loginDTO")LoginDTO loginDTO,@ModelAttribute("parts")CommonMultiParts parts
    , HttpSession session, Model model,Principal principal)
    {
       // String file=parts.getProfile();
        model.addAttribute("username",principal.getName().toUpperCase());
      // byte[] data=file.getBytes();
        String path = session.getServletContext().getRealPath("/")+"Image"+File.separator+parts.getProfile();
        System.out.println(path);

//            FileOutputStream fos=new FileOutputStream(path);
//            fos.write(data);
//            fos.close();
            //model.addAttribute("fileUpload",file.getOriginalFilename());
        return "UploadImage";
    }

    @RequestMapping("/Comment")
    public String Comment(@SessionAttribute("loginDTO") LoginDTO loginDTO
            ,Principal principal,@ModelAttribute("commentsDTO") CommentsDTO commentsDTO,Model model){
        model.addAttribute("username",principal.getName().toUpperCase());
        loginDao.InsertComments(commentsDTO,principal);
        return "Comment";
    }
    @RequestMapping("/Save")
    public String Save()
    {
        return"redirect:/home/UploadImage";
    }
    @RequestMapping("/ShowComments")
    public String ShowComments(Principal principal,Model model){
        List<CommentsDTO> commentsDTOS=loginDao.fetch(principal);
        model.addAttribute("commentsDTOS",commentsDTOS);
        String username=principal.getName();
        model.addAttribute("username",username.toUpperCase());

        return "ShowComments";
    }
    @RequestMapping("/AddNoteSheet")
    public String AddNoteSheet(@SessionAttribute("parts")CommonMultiParts parts,Principal principal,CommentsDTO commentsDTO){
       // System.out.println("file:"+parts.getProfile());
        List<CommentsDTO> commentsDTOS=loginDao.fetch(principal);
        String comment= utilityMethods.PrintComments(commentsDTOS);

        //System.out.println("principal:"+principal.getName());

        try {
            //Read File
            PDDocument document = PDDocument.load(new File("C:/Users/Pericent/IdeaProjects/Assignment/src/main/webapp/Image/" + parts.getProfile()));
            PDFTextStripper text = new PDFTextStripper();
            String pdf = text.getText(document);
            // model.addAttribute("pdf", pdf);
            // System.out.println(pdf);

            //write file
            PDDocument document1 = new PDDocument();
            PDPage pdPage = new PDPage();
            document1.addPage(pdPage);
            PDPageContentStream contentStream = new PDPageContentStream(document1, pdPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setLeading(15f);
            contentStream.newLineAtOffset(25, 700);
            for (CommentsDTO commentsDTO1 : commentsDTOS)
            {
                contentStream.showText(commentsDTO1.getComment());
            contentStream.newLine();
            }
                contentStream.endText();
                contentStream.close();

            document1.save("D:/Write/write.pdf");
            //Merge file
            File file1 = new File("C:/Users/Pericent/IdeaProjects/Assignment/src/main/webapp/Image/" + parts.getProfile());
            File file2 = new File("D:/Write/write.pdf");
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
            pdfMergerUtility.setDestinationFileName("D:/Merge/merge.pdf");
            pdfMergerUtility.addSource(file1);
            pdfMergerUtility.addSource(file2);
            pdfMergerUtility.mergeDocuments();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return "AddNoteSheet";
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
