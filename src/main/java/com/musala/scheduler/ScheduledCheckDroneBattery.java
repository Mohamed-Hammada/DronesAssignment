package com.musala.scheduler;

import com.musala.constant.GeneralConstants;
import com.musala.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduledCheckDroneBattery {


    private final DroneRepository droneRepository;
    private final GeneralConstants generalConstants;

    @Scheduled(fixedDelay=5000)
    public void scheduledCheckDronesBattery() {
        droneRepository.findAll().stream().filter(e->generalConstants.getLowBatteryLevel().compareTo(e.getBattery()) >= 0)
                .forEach(d->{
                    log.error("Alert drone with serial No.{} low battery level", d.getSerialNumber());
                });
    }

}
