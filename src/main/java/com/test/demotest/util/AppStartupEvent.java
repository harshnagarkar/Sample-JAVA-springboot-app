package com.test.demotest.util;

import java.util.Date;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.test.demotest.bussiness.GuestService;
import com.test.demotest.bussiness.ReservationService;
import com.test.demotest.bussiness.RoomReservation;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final DateUtils dateUtils;


    public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils,GuestService guestService) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
        this.guestService=guestService;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
        reservations.forEach(System.out::println);
    }
}
