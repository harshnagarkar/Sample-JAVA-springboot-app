package com.test.demotest.web;


// import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.demotest.bussiness.ReservationService;



@Controller
@RequestMapping("/guests")
public class GuestController {

    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model){
        // List<Guest> data = new ArrayList<>(this.reservationService.getHotelGuests());
        // for (Guest guest : data) {
        //     System.out.println(guest.getEmail_addres());
        // }
        model.addAttribute("guests", this.reservationService.getHotelGuests());
        return "hotel-guests";
    }

}
