package com.test.demotest.bussiness;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.demotest.data.Room;
import com.test.demotest.data.RoomRepository;
import com.test.demotest.bussiness.RoomServations;

@Service
public class RoomService {

    private final RoomRepository rooms;

    public RoomService(RoomRepository rooms) {
        this.rooms=rooms;        
    }


    public List<RoomServations> listRooms(){
        Iterable<Room> rooms = this.rooms.findAll();
        List<RoomServations> roomSReservations = new ArrayList<>();
        rooms.forEach(room->{
            RoomServations rs = new RoomServations();
            rs.setBedInfo((room.getBedInfo()));
            rs.setId(room.getId());
            rs.setName(room.getName());
            rs.setRoomNUmber(room.getRoomNUmber());
            roomSReservations.add(rs);
        });
        roomSReservations.sort(new Comparator<RoomServations>() {
            @Override
            public int compare(RoomServations o1, RoomServations o2) {
                return o1.getRoomNUmber().compareTo(o2.getRoomNUmber());
            }
        });
        return roomSReservations;


    }

    
    
}
