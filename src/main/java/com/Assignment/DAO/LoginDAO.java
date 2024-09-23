package com.Assignment.DAO;

import com.Assignment.CommentsDTO;
import com.Assignment.LoginDTO;
import com.Assignment.RegistrationDTO;

import java.security.Principal;
import java.util.List;

public interface LoginDAO {

    void Insert(RegistrationDTO registrationDTO);
    void InsertComments(CommentsDTO commentsDTO,Principal principal);
    List<CommentsDTO> fetch(Principal principal);
}