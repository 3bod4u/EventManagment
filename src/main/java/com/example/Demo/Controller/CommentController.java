package com.example.Demo.Controller;


import com.example.Demo.DTO.CommentDTO;
import com.example.Demo.Model.Comment;
import com.example.Demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    public CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> findall(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getAllComment(id));
    }

    @PostMapping("/{uid}/{eid}")
    public ResponseEntity add(@RequestBody CommentDTO commentDTO, @PathVariable Long uid, @PathVariable Long eid, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(commentService.addComment(commentDTO,uid,eid));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody CommentDTO commentDTO, @PathVariable Long id){
        return ResponseEntity.ok(commentService.updateComment(commentDTO,id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        commentService.deleteComment(id);
    }

    @DeleteMapping("/delete")
    public void delete(){
        commentService.deleteAll();
    }

}
