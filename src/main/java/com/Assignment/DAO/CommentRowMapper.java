package com.Assignment.DAO;

import com.Assignment.CommentsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<CommentsDTO> {

    @Override
    public CommentsDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        CommentsDTO commentsDTO=new CommentsDTO();
        commentsDTO.setId(resultSet.getInt("id"));
        commentsDTO.setComment(resultSet.getString("comment"));
        return commentsDTO;
    }
}