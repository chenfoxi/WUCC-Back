package org.wucc.backservice.controller.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wucc.backservice.model.dto.CommentDTO;
import org.wucc.backservice.model.dto.request.CommentRequest;
import org.wucc.backservice.model.dto.request.SimpleRequest;
import org.wucc.backservice.model.dto.response.CommonResponse;
import org.wucc.backservice.model.pojo.Comment;
import org.wucc.backservice.model.pojo.User;
import org.wucc.backservice.repository.CommentRepository;
import org.wucc.backservice.repository.UserRepository;
import org.wucc.backservice.repository.custom.CustomCommentRepository;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxi.chen on 23/09/20.
 *
 * @author foxi.chen
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment/")
public class CommentController {
    final CommentRepository commentRepository;

    final UserRepository userRepository;

    final CustomCommentRepository customCommentRepository;

    public CommentController(CommentRepository commentRepository, UserRepository userRepository,
                             CustomCommentRepository customCommentRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.customCommentRepository = customCommentRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        Long userId = commentRequest.getUserId();
        Long postId = commentRequest.getPostId();
        Integer commentType = commentRequest.getCommentType();
        String content = commentRequest.getContent();
        CommonResponse<Boolean> response = new CommonResponse<>();

        try {
            if (userRepository.existsById(userId)) {
                Comment comment = saveComment(new User(userId), postId, commentType, content);
                if (comment.getId() != null && comment.getContent().equals(content)) {
                    response.setData(true);
                    response.setCode(0);
                    response.setErrorMsg("");
                    return ResponseEntity.ok(response);
                } else {
                    response.setData(false);
                    response.setCode(-1);
                    response.setErrorMsg("save failed");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                throw new IllegalArgumentException("user does not exist.");
            }
        } catch (Exception e) {
            response.setData(false);
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private Comment saveComment(User user, Long postId, Integer commentType, String content) {
        return commentRepository.save(new Comment(user, postId, 0L, commentType, content,
            new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
    }

    @PostMapping("/getComments")
    public ResponseEntity<?> getComments(@Valid @RequestBody CommentRequest commentRequest) {
        Long postId = commentRequest.getPostId();
        Integer commentType = commentRequest.getCommentType();
        CommonResponse<List<CommentDTO>> response = new CommonResponse<>();
        try {
            List<CommentDTO> commentDTOList = customCommentRepository
                .findAllCommentsByPostIdAndCommentType(postId, commentType);
            response.setData(commentDTOList);
            response.setCode(0);
            response.setErrorMsg("");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(new ArrayList<>());
            response.setCode(-1);
            response.setErrorMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PostMapping("/deleteComment")
    public ResponseEntity<?> deleteComments(@Valid @RequestBody SimpleRequest simpleRequest){
        Long id = simpleRequest.getId();
        CommonResponse<Boolean> response = new CommonResponse<>();
        if(customCommentRepository.deleteCommentById(id)){
            response.setData(true);
        } else {
            response.setData(false);
        }
        response.setCode(0);
        response.setErrorMsg("");
        return ResponseEntity.ok(response);
    }
}
