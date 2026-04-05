package ui;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {

    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    static {
        DATE_FORMAT.setLenient(false);
    }

    public static void displayMainMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner);
            switch (choice) {
                case 1 -> findAndReserveRoom(scanner);
                case 2 -> seeMyReservations(scanner);
                case 3 -> createAccount(scanner);
                case 4 -> AdminMenu.displayAdminMenu(scanner);
                case 5 -> {
                    System.out.println("\nThank you for choosing HotelApp. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please enter 1–5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
 
                ══════════════════════════════
                        MAIN MENU
                ══════════════════════════════
                 1. Find and reserve a room
                 2. See my reservations
                 3. Create an account
                 4. Admin
                 5. Exit
                ══════════════════════════════
                Enter choice:\s""");
    }


    private static void findAndReserveRoom(Scanner scanner) {
        System.out.println("\n── Find and Reserve a Room ────────────");

        Date checkIn  = readDate(scanner, "Check-in date  (MM/dd/yyyy): ");
        Date checkOut = readDate(scanner, "Check-out date (MM/dd/yyyy): ");

        if (!isValidDateRange(checkIn, checkOut)) return;

        Collection<IRoom> available = hotelResource.findARoom(checkIn, checkOut);

        if (available.isEmpty()) {
            System.out.println("\n  No rooms available for those dates.");


            Collection<IRoom> suggested = hotelResource.findRoomsSevenDaysLater(checkIn, checkOut);
            Calendar cal = Calendar.getInstance();

            cal.setTime(checkIn);  cal.add(Calendar.DAY_OF_YEAR, 7);
            Date altIn  = cal.getTime();
            cal.setTime(checkOut); cal.add(Calendar.DAY_OF_YEAR, 7);
            Date altOut = cal.getTime();

            System.out.printf("  Showing available rooms for %s → %s instead:%n",
                    DATE_FORMAT.format(altIn), DATE_FORMAT.format(altOut));

            if (suggested.isEmpty()) {
                System.out.println("  Sorry, no rooms available in that period either.");
                return;
            }
            printRooms(suggested);

            System.out.print("\n  Book with these alternative dates? (y/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) return;

            checkIn  = altIn;
            checkOut = altOut;
            available = suggested;
        } else {
            printRooms(available);
        }

        System.out.print("\n  Enter room number to book: ");
        String roomNumber = scanner.nextLine().trim();
        IRoom selectedRoom = findRoomInCollection(roomNumber, available);
        if (selectedRoom == null) {
            System.out.println("  Room '" + roomNumber + "' not found in available rooms.");
            return;
        }

        System.out.print("  Your account email: ");
        String email = scanner.nextLine().trim();

        if (hotelResource.getCustomer(email) == null) {
            System.out.println("  No account found for '" + email + "'. Please create an account first.");
            return;
        }

        try {
            Reservation reservation = hotelResource.bookARoom(email, selectedRoom, checkIn, checkOut);
            System.out.println("\n  ✓ Reservation confirmed!");
            System.out.println("  " + reservation);
        } catch (Exception e) {
            System.out.println("  Booking failed: " + e.getMessage());
        }
    }



    private static void seeMyReservations(Scanner scanner) {
        System.out.println("\n── My Reservations ────────────────────");
        System.out.print("  Enter your email: ");
        String email = scanner.nextLine().trim();

        Customer customer = hotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("  No account found for '" + email + "'.");
            return;
        }

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(email);
        if (reservations.isEmpty()) {
            System.out.println("  You have no reservations yet.");
        } else {
            reservations.forEach(r -> System.out.println("  " + r));
        }
    }



    private static void createAccount(Scanner scanner) {
        System.out.println("\n── Create an Account ──────────────────");
        System.out.print("  First name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("  Last name:  ");
        String lastName = scanner.nextLine().trim();

        System.out.print("  Email:      ");
        String email = scanner.nextLine().trim();

        try {
            hotelResource.createACustomer(firstName, lastName, email);
            System.out.println("  ✓ Account created for " + firstName + " " + lastName + " (" + email + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("  Error: " + e.getMessage());
        }
    }



    private static void printRooms(Collection<IRoom> rooms) {
        System.out.println();
        int i = 1;
        for (IRoom room : rooms) {
            System.out.printf("  [%d] Room %-6s | %-6s | $%.2f/night%n",
                    i++,
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getRoomPrice());
        }
    }

    private static IRoom findRoomInCollection(String roomNumber, Collection<IRoom> rooms) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equalsIgnoreCase(roomNumber)) return room;
        }
        return null;
    }

    private static Date readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print("  " + prompt);
            String input = scanner.nextLine().trim();
            try {
                Date date = DATE_FORMAT.parse(input);
                return date;
            } catch (ParseException e) {
                System.out.println("  Invalid date format. Please use MM/dd/yyyy (e.g., 12/31/2025).");
            }
        }
    }

    private static boolean isValidDateRange(Date checkIn, Date checkOut) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date todayMidnight = cal.getTime();

        if (checkIn.before(todayMidnight)) {
            System.out.println("  Check-in date cannot be in the past.");
            return false;
        }
        if (!checkOut.after(checkIn)) {
            System.out.println("  Check-out date must be strictly after check-in date.");
            return false;
        }
        return true;
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Please enter a valid number: ");
            }
        }
    }
}