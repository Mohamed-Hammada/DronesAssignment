package com.musala.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConditionalOnProperty(value="jobs.enabled", matchIfMissing=true, havingValue="true")
@Configuration
@EnableScheduling
public class ScheduledJobs {
    @Bean
    public ScheduledJobs scheduledJob() {
        return new ScheduledJobs();
    }
}
