package com.test.demotest.bussiness;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.demotest.data.Guest;
import com.test.demotest.data.GuestRepository;


@Service
public class GuestService {

    private final GuestRepository guestRepository;
    
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestReservation> getGuestReservations(){
        Iterable<Guest> guests = this.guestRepository.findAll();
        
        List<GuestReservation> guestList = new ArrayList<>();
        guests.forEach (guest->{
            GuestReservation guestReservation = new GuestReservation();
            guestReservation.setFirstName(guest.getFirst_name());
            guestReservation.setLastName(guest.getLast_name());
            guestReservation.setEmailAddress(guest.getEmail_addres());
            guestReservation.setPhone(guest.getPhone_number());
            guestReservation.setAddress(guest.getAddress());
            guestReservation.setCountry(guest.getCountry());
            guestReservation.setState(guest.getState());
            guestList.add(guestReservation);
        });
        guestList.sort(new Comparator<GuestReservation>() {
            @Override
            public int compare(GuestReservation o1, GuestReservation o2) {
                if (o1.getLastName().equals(o2.getLastName())){
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return guestList;

    }

    public boolean addGuestReservations(Guest guest){
        if(guest==null){
            throw new RuntimeException("Guest cannot be null");
        }
        this.guestRepository.save(guest);
        return true;
    }


}
