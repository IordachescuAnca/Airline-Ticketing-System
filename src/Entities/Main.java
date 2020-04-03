package Entities;

import java.util.*;

public class Main {
    public static void main(String[]args){
        AirportHenryCoanda Airport = AirportHenryCoanda.getInstance("Calea Bucurestilor nr.224 E");
        String path = "C:\\Users\\iorda\\IdeaProjects\\PAO Project\\src\\Entities\\data.txt";
        Airport.addDataBase(path);

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Menu");
            System.out.println("1. You are an employee");
            System.out.println("2. You are a client");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            if(choice == 1){
                boolean isValid = Airport.logInEmployee();
                if(!isValid){
                    System.out.println("Username or password wrong. Try again.");
                }
                else{
                    while(true){
                        System.out.println("Menu - Employee");
                        System.out.println("1. Add Flight.");
                        System.out.println("2. Remove flight.");
                        System.out.println("3. Display flights.");
                        System.out.println("4. Introduce runways.");
                        System.out.println("5. Display runways.");
                        System.out.println("6. Display clients");
                        System.out.println("7. Display employees");
                        System.out.println("8. Exit.");

                        int choiceEmployee = sc.nextInt();
                        if(choiceEmployee == 1) Airport.addFlight();
                        if(choiceEmployee == 2) Airport.removeFlight();
                        if(choiceEmployee == 3) Airport.displayFlights();
                        if(choiceEmployee == 4) Airport.addRunway();
                        if(choiceEmployee == 5) Airport.displayRunways();
                        if(choiceEmployee == 6) Airport.displayClients();
                        if(choiceEmployee == 7) Airport.displayEmployees();
                        if(choiceEmployee == 8) break;
                    }
                }
            }
            if(choice == 2){
                Client actualClient = Airport.logInClient();
                while(true){
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Menu - Client");
                    System.out.println("1. Buy ticket.");
                    System.out.println("2. Cancel ticket.");
                    System.out.println("3. Display unavailable seats of a flight.");
                    System.out.println("4. Display the most popular flight at this moment.");
                    System.out.println("5. Display flights.");
                    System.out.println("6. Exit.");

                    int choiceClient = sc.nextInt();
                    if(choiceClient == 1){
                        System.out.println("Enter the Flight ID");
                        String flightID = sc1.nextLine();
                        Airport.buyTicket(actualClient, flightID);
                    }
                    if(choiceClient == 2){
                        System.out.println("Enter the Flight ID");
                        String flightID = sc1.nextLine();
                        Airport.removeTicket(actualClient, flightID);
                    }
                    if(choiceClient == 3){
                        System.out.println("Enter the Flight ID");
                        String flightID = sc1.nextLine();
                        Airport.unavailableSeats(flightID);
                    }
                    if(choiceClient == 4) Airport.mostPopularandRecentFlight();
                    if(choiceClient == 5) Airport.displayFlights();
                    if(choiceClient == 6) break;
                }
            }

            if(choice == 3) break;
        }
    }
}
