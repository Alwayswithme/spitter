package me.phx.Job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(fixedRate = 3000)
    public void task1() {
        log.info("1:The time is now " + LocalDateTime.now());
    }

    @Scheduled(fixedRate = 5000)
    public void task2() {
        log.info("2:The time is now " + LocalDateTime.now());
    }
}
