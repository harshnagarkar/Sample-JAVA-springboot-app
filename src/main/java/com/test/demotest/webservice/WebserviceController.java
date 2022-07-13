package com.test.demotest.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.bussiness.GuestReservation;
import com.test.demotest.bussiness.GuestService;
import com.test.demotest.bussiness.ReservationService;
import com.test.demotest.bussiness.RoomReservation;
import com.test.demotest.bussiness.RoomServations;
import com.test.demotest.bussiness.RoomService;
import com.test.demotest.data.Guest;
import com.test.demotest.util.DateUtils;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;


    public WebserviceController(DateUtils dateUtils, ReservationService reservationService,GuestService guestService,RoomService roomService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.guestService=guestService;
        this.roomService=roomService;
    }

    @RequestMapping(path="/reservations", method=RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date",required = false) String dateString){
        // System.out.println(dateString);
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);

    }

    @RequestMapping(path="/guests", method=RequestMethod.GET)
    public List<GuestReservation> getGuests(){
        // System.out.println(dateString);
        return this.guestService.getGuestReservations();

    }

    @PostMapping("/guests")
    public Boolean addGuests(@RequestBody Guest guest){
        if (this.guestService.addGuestReservations(guest)){
            return true;
        }else{
            return false;
        }

    }

    @GetMapping("/rooms")
    public List<RoomServations> getRooms(){
        return this.roomService.listRooms();
    }


}
