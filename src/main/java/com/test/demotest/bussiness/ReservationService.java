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
import com.test.demotest.data.Reservation;
import com.test.demotest.data.ReservationRepository;
import com.test.demotest.data.Room;
import com.test.demotest.data.RoomRepository;

@Service
public class ReservationService {



    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository,GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository=roomRepository;
        this.guestRepository=guestRepository;
        this.reservationRepository=reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach (room->{
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNUmber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getReservationId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById((reservation.getGuestId())).get();
            roomReservation.setFirstName(guest.getFirst_name());
            roomReservation.setLastName(guest.getLast_name());
            roomReservation.setGuestId(guest.getId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            };
        });
        return roomReservations;
    }



    public List<Guest> getHotelGuests(){
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guest->{guestList.add(guest);});
        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                if (o1.getLast_name().equals(o2.getLast_name())){
                    return o1.getFirst_name().compareTo(o2.getFirst_name());
                }
                return o1.getLast_name().compareTo(o2.getLast_name());
            }
        });
        return guestList;
    }
    
    
}
