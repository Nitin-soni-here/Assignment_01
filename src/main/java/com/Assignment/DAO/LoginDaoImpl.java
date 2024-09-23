package com.Assignment.DAO;

import com.Assignment.CommentsDTO;
import com.Assignment.LoginDTO;
import com.Assignment.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.security.Principal;
import java.util.List;

@Repository
public class LoginDaoImpl implements LoginDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    LoginDTO loginDTO=new LoginDTO();
    Principal principal=new Principal() {
        @Override
        public String getName() {
            return "";
        }
    };

    @Override
    public void Insert(RegistrationDTO registrationDTO) {
        String sql="Insert into users values(?,?,?)";
        String sql1="Insert into authorities values(?,?)";
        Object args[]={registrationDTO.getUsername(),registrationDTO.getPassword(),1 };
        jdbcTemplate.update(sql,args);
        jdbcTemplate.update(sql1,registrationDTO.getUsername(),"ADMIN");
    }

    @Override
    public void InsertComments(@SessionAttribute("loginDTO") CommentsDTO commentsDTO,Principal principal) {
        String username=principal.getName();
        System.out.println("comment"+username);
        String sql="Insert into commentsdto values(?,?,?)";
        Object args[]={commentsDTO.getId(),principal.getName(),commentsDTO.getComment()};
        jdbcTemplate.update(sql,args);
    }

    @Override
    public List<CommentsDTO> fetch(@SessionAttribute("loginDTO")Principal principal) {
        String sql="SELECT * FROM commentsdto where username=?";
        List<CommentsDTO> stu=jdbcTemplate.query(sql,new CommentRowMapper(),principal.getName());
        //System.out.println(stu);
        return stu;
    }
}