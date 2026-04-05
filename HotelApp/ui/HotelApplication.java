package ui;

import api.AdminResource;
import model.FreeRoom;
import model.RoomType;
import ui.MainMenu;

import java.util.Scanner;

public class HotelApplication {

    public static void main(String[] args) {

        seedSampleData();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     Welcome to Hotel Reservation     ║");
        System.out.println("╚══════════════════════════════════════╝");

        MainMenu.displayMainMenu(scanner);
        scanner.close();
    }

    private static void seedSampleData() {
        AdminResource admin = AdminResource.getInstance();
        try {
            admin.addRoom(new FreeRoom("101", RoomType.SINGLE, 89.99));
            admin.addRoom(new FreeRoom("102", RoomType.SINGLE, 89.99));
            admin.addRoom(new FreeRoom("201", RoomType.DOUBLE, 149.99));
            admin.addRoom(new FreeRoom("202", RoomType.DOUBLE, 149.99));
            admin.addRoom(new FreeRoom("301", RoomType.DOUBLE, 199.99));
        } catch (IllegalArgumentException ignored) {

        }
    }
}