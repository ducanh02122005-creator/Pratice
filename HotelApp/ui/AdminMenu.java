package ui;

import api.AdminResource;
import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void displayAdminMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner);
            switch (choice) {
                case 1 -> seeAllCustomers();
                case 2 -> seeAllRooms();
                case 3 -> seeAllReservations();
                case 4 -> addARoom(scanner);
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Please enter 1–5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
 
                ══════════════════════════════
                       ADMIN MENU
                ══════════════════════════════
                 1. See all Customers
                 2. See all Rooms
                 3. See all Reservations
                 4. Add a Room
                 5. Back to Main Menu
                ══════════════════════════════
                Enter choice:\s""");
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        System.out.println("\n── All Customers ──────────────────────");
        if (customers.isEmpty()) {
            System.out.println("  (no customers registered)");
        } else {
            customers.forEach(c -> System.out.println("  " + c));
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        System.out.println("\n── All Rooms ──────────────────────────");
        if (rooms.isEmpty()) {
            System.out.println("  (no rooms added yet)");
        } else {
            rooms.forEach(r -> System.out.println("  " + r));
        }
    }

    private static void seeAllReservations() {
        Collection<Reservation> reservations = adminResource.getAllReservations();
        System.out.println("\n── All Reservations ───────────────────");
        if (reservations.isEmpty()) {
            System.out.println("  (no reservations yet)");
        } else {
            reservations.forEach(r -> System.out.println("  " + r));
        }
    }

    private static void addARoom(Scanner scanner) {
        System.out.println("\n── Add a Room ─────────────────────────");

        System.out.print("  Room number: ");
        String roomNumber = scanner.nextLine().trim();
        if (roomNumber.isEmpty()) {
            System.out.println("  Room number cannot be empty.");
            return;
        }

        double price = -1;
        while (price < 0) {
            System.out.print("  Room price per night (USD): ");
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price < 0) System.out.println("  Price must be non-negative.");
            } catch (NumberFormatException e) {
                System.out.println("  Invalid price. Please enter a number.");
            }
        }

        RoomType roomType = null;
        while (roomType == null) {
            System.out.print("  Room type (1 = Single, 2 = Double): ");
            int typeChoice = readInt(scanner);
            if (typeChoice == 1) roomType = RoomType.SINGLE;
            else if (typeChoice == 2) roomType = RoomType.DOUBLE;
            else System.out.println("  Please enter 1 or 2.");
        }

        try {
            adminResource.addRoom(new FreeRoom(roomNumber, roomType, price));
            System.out.println("  ✓ Room " + roomNumber + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        System.out.print("\n  Add another room? (y/n): ");
        String again = scanner.nextLine().trim();
        if (again.equalsIgnoreCase("y")) {
            addARoom(scanner);
        }
    }



    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("  Please enter a valid number: ");
            }
        }
    }
}