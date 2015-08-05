package me.phx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Profile("production")
@Configuration
public class ScheduleConfig {
}
