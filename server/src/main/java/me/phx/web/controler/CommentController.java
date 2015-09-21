package me.phx.web.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.InetAddresses;
import lombok.extern.slf4j.Slf4j;
import me.phx.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

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
    ObjectMapper objectMapper;

    @RequestMapping("/test")
    public Comment test() throws JsonProcessingException {
        Comment c = new Comment();
        c.setAuthor("hhh");
        c.setCreated(LocalDateTime.now());
        c.setEmail("aaa");
        c.setIpAddress(InetAddresses.forString("127.0.0.1").getAddress());
        c.setLikes(0);
        c.setParentId(null);
        c.setText("comments");
        c.setTopicId(1);

        String s = objectMapper.writeValueAsString(c);
        return c;
    }

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
}
