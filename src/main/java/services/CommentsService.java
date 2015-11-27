package services;

import models.Comment;
import org.joda.time.DateTime;

/**
 * Created by aserafin on 27/11/15.
 */
public class CommentsService {
    public static Comment createComment(Integer schoolId, String nick, String body) {
        Comment comment = new Comment();
        comment.schoolId = schoolId;
        comment.nick = nick;
        comment.body = body;
        comment.createdAt = DateTime.now();

        CommentsRepository.insertComment(comment);
        return comment;
    }
}
