package api;


import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static HotelResource instance;

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {}

    public static HotelResource getInstance() {
        if (instance == null) {
            instance = new HotelResource();
        }
        return instance;
    }


    public void createACustomer(String firstName, String lastName, String email) {
        customerService.addCustomer(firstName, lastName, email);
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }


    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> findRoomsSevenDaysLater(Date checkIn, Date checkOut) {
        return reservationService.findRoomsFromDate(checkIn, checkOut);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkIn, Date checkOut) {
        Customer customer = customerService.getCustomer(customerEmail);
        if (customer == null) {
            throw new IllegalArgumentException("No account found for email: " + customerEmail);
        }
        return reservationService.reserveARoom(customer, room, checkIn, checkOut);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        if (customer == null) {
            return java.util.Collections.emptyList();
        }
        return reservationService.getCustomerReservation(customer);
    }
}