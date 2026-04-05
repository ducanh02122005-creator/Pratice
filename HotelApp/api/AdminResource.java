package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResource {

    private static AdminResource instance;

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private AdminResource() {}

    public static AdminResource getInstance() {
        if (instance == null) {
            instance = new AdminResource();
        }
        return instance;
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    public void addRoom(IRoom room) {
        reservationService.addRoom(room);
    }
}
