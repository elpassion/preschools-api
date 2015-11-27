package services;

import dao.Database;
import models.Comment;
import org.sql2o.Connection;

import java.util.List;

/**
 * Created by aserafin on 27/11/15.
 */
public class CommentsRepository {
    public static void insertComment(Comment comment) {
        String sql = "insert into comments(schoolid, nick, body, createdat) values(:schoolId, :nick, :body, :createdAt);";
        try(Connection connection = Database.getConnection()) {
            comment.id = (Integer)connection.createQuery(sql).bind(comment).executeUpdate().getKey();
        }
    }

    public static List<Comment> findForSchoolId(Integer schoolId, Integer offset, Integer limit) {
        String sql = "select * from comments where schoolid = :schoolId order by createdat desc offset :offset limit :limit";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false)
                    .addParameter("schoolId", schoolId)
                    .addParameter("offset", offset)
                    .addParameter("limit", limit).executeAndFetch(Comment.class);
        }
    }
}
