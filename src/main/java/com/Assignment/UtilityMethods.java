package com.Assignment;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UtilityMethods {
     String PrintComments(List<CommentsDTO> comments) {
        String finalComments = "";
        for (CommentsDTO comment : comments) {
            finalComments = finalComments.concat(comment.getComment());
        }
        return finalComments;
    }
}
