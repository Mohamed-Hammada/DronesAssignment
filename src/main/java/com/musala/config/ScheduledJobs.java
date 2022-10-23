package com.musala.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduledJobs {
    @Bean
    @ConditionalOnProperty(value="jobs.enabled", matchIfMissing=true, havingValue="true")
    public ScheduledJobs scheduledJob() {
        return new ScheduledJobs();
    }
}
