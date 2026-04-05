package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService instance;
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public void addRoom(IRoom room) {
        if (rooms.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room number '" + room.getRoomNumber() + "' already exists.");
        }
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> available = new ArrayList<>();
        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                available.add(room);
            }
        }
        return available;
    }

    public Collection<IRoom> findRoomsFromDate(Date checkInDate, Date checkOutDate) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(checkInDate);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date newCheckIn = cal.getTime();

        cal.setTime(checkOutDate);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date newCheckOut = cal.getTime();

        return findRooms(newCheckIn, newCheckOut);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalStateException("Room " + room.getRoomNumber() + " is not available for those dates.");
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCustomer().getEmail().equalsIgnoreCase(customer.getEmail())) {
                result.add(r);
            }
        }
        return result;
    }

    public Collection<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    private boolean isRoomAvailable(IRoom room, Date checkIn, Date checkOut) {
        for (Reservation r : reservations) {
            if (r.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                Date existIn  = r.getCheckInDate();
                Date existOut = r.getCheckOutDate();
                boolean noOverlap = !checkOut.after(existIn) || !checkIn.before(existOut);
                if (!noOverlap) {
                    return false;
                }
            }
        }
        return true;
    }
}
