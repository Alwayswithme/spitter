package me.phx.web.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.InetAddresses;
import lombok.extern.slf4j.Slf4j;
import me.phx.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
        c.setAuthor("hhh")
                .setCreated(LocalDateTime.now())
                .setEmail("aaa")
                .setIpAddress(InetAddresses.forString("127.0.0.1").getAddress())
                .setLikes(0)
                .setParentId(null).setText("comments")
                .setTopicId(1);

        String s = objectMapper.writeValueAsString(c);
        return c;
    }
}
