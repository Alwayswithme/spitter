package me.phx.web.controler;

import lombok.extern.slf4j.Slf4j;
import me.phx.model.Comment;
import me.phx.mybatis.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author phoenix
 */
@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;


    @RequestMapping(value = "/", method = POST, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        log.info(comment.toString());

        return ResponseEntity.created(URI.create("test")).build();
    }

    @RequestMapping(value = "/{id}", method = PUT, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> replace(@PathVariable Integer id, @RequestBody Comment comment) {
        log.info(id.toString());
        comment.setId(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = PATCH, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Comment comment) {
        log.info(id.toString());
        comment.setId(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info(id.toString());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<?> get(@PathVariable Integer id) {
        log.info(id.toString());
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(comment);
    }
}
