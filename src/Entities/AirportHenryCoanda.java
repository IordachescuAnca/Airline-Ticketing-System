package Entities;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import Comparator.SortByDate;

public class AirportHenryCoanda { // SINGLETON
    private static AirportHenryCoanda singleInstance = null;
    private List<Employee> employees;
    private List<Flight> flights;
    private List<Client> clients;
    private Map<Airplane, Integer> runways;
    private String address;

    private AirportHenryCoanda(String address){
        this.employees = new ArrayList<Employee>();
        this.flights = new ArrayList<Flight>();
        this.clients = new ArrayList<Client>();
        this.runways = new HashMap<Airplane, Integer>();
    }

    public static AirportHenryCoanda getInstance(String address){
        if(singleInstance == null){
            singleInstance = new AirportHenryCoanda(address);
        }
        return singleInstance;
    }


    public void addEmployee(Employee newEmployee){
        this.employees.add(newEmployee);
    }

    public void removeEmployee(Employee oldEmployee){
        this.employees.remove(oldEmployee);
    }

    public void displayEmployees(){
        for(Employee e: this.employees){
            System.out.println(e.toString());
        }
    }

    public void addFlight(Flight newFlight){
        this.flights.add(newFlight);
    }

    public void removeFlight(Flight oldFlight){
        this.flights.remove(oldFlight);
    }

    public void displayFlights(){
        if(this.flights.size() == 0) System.out.println("No flights.");
        for(Flight f: this.flights){
            System.out.println(f.toString());
            System.out.println();
        }
    }


    public void addRunway(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Manufacturing date: ");
        String manDate = sc.nextLine();
        System.out.println("Manufacturing company: ");
        String manCompany = sc.nextLine();
        System.out.println("Type: ");
        String type = sc.nextLine();
        System.out.println("Number rows, column");
        int numberRows = sc.nextInt();
        int numberCols = sc.nextInt();
        System.out.println("Enter runway:");
        int runway = sc.nextInt();

        System.out.println("Is the airplane IN LINE?");
        boolean isInLine = sc.nextBoolean();
        if(isInLine){
            System.out.println("Business capacity:");
            int businessCapacity = sc.nextInt();
            InLineAirplane newAirplane = new InLineAirplane(numberRows, numberCols, manDate, manCompany, type, businessCapacity);
            runways.put(newAirplane, runway);
        }
        else{
            LowCostAirplane newAirplane = new LowCostAirplane(numberRows, numberCols, manDate, manCompany, type);
            runways.put(newAirplane, runway);
        }
        System.out.println("Runway added.");
    }

    public void displayRunways(){
        for(Airplane a: this.runways.keySet()){
            System.out.println("Airplane " + a.getType() +" - Runway: " + this.runways.get(a));
        }
    }


    public void addDataBase(String path){
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            readEmployees(sc);
        }
        catch (FileNotFoundException exception){
            System.out.println("File not found.");
        }
    }

    public void readEmployees(Scanner sc){
        int numberEmployees = sc.nextInt();
        for(int i = 0; i < numberEmployees; ++i){
            Employee newEmployee = readEmployee();
            this.addEmployee(newEmployee);
        }
    }

    public Employee readEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Last Name:");
        String lastName = sc.nextLine();
        System.out.println("First Name:");
        String firstName = sc.nextLine();
        System.out.println("CNP:");
        String CNP = sc.nextLine();
        System.out.println("Date birth:");
        String dateBirth = sc.nextLine();
        System.out.println("Serie:");
        String serie = sc.nextLine();
        System.out.println("Number:");
        String no = sc.nextLine();
        System.out.println("Birth place:");
        String birthPlace = sc.nextLine();
        System.out.println("Home adress:");
        String homeAdress = sc.nextLine();
        System.out.println("Issued by:");
        String issuedBy = sc.nextLine();
        System.out.println("Begin validity:");
        String beginValidity = sc.nextLine();
        System.out.println("End validity:");
        String endValidity = sc.nextLine();
        IdentityCard IC = new IdentityCard(lastName, firstName, CNP, dateBirth, serie, no, birthPlace, homeAdress, issuedBy, beginValidity, endValidity);

        System.out.println("Username:");
        String username = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();
        System.out.println("Mail:");
        String email = sc.nextLine();
        System.out.println("Phone number:");
        String phoneNumber = sc.nextLine();
        System.out.println("Hire date:");
        String hireDate = sc.nextLine();
        System.out.println("Job title:");
        String jobTitle = sc.nextLine();
        System.out.println("Department:");
        String department = sc.nextLine();
        return new Employee(IC, username, password, email, phoneNumber, hireDate, jobTitle, department);
    }

    public boolean logInEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello. Introduce username and password to check if you are an employee.");
        System.out.println("Introduce username: ");
        String username = sc.nextLine();
        System.out.println("Introduce password: ");
        String password = sc.nextLine();
        for(Employee e: this.employees){
            if(e.getUsername().equals(username) && e.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


    public void addFlight(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Id: ");
        String id = sc.nextLine();
        System.out.println("From: ");
        String from = sc.nextLine();
        System.out.println("To: ");
        String to = sc.nextLine();
        System.out.println("Date from: ");
        String datefrom = sc.nextLine();
        System.out.println("Date to: ");
        String dateto = sc.nextLine();
        System.out.println("AIRPLANE");
        System.out.println("Manufacturing date: ");
        String manDate = sc.nextLine();
        System.out.println("Manufacturing company: ");
        String manCompany = sc.nextLine();
        System.out.println("Type: ");
        String type = sc.nextLine();
        System.out.println("Number rows, column, price");
        int numberRows = sc.nextInt();
        int numberCols = sc.nextInt();
        int price = sc.nextInt();

        System.out.println("Is the airplane IN LINE?");
        boolean isInLine = sc.nextBoolean();
        if(isInLine){
            System.out.println("Business capacity:");
            int businessCapacity = sc.nextInt();
            InLineAirplane newAirplane = new InLineAirplane(numberRows, numberCols, manDate, manCompany, type, businessCapacity);
            Flight newFlight = new Flight(id, price, from, to, datefrom, dateto, newAirplane);
            this.addFlight(newFlight);
        }
        else{
            LowCostAirplane newAirplane = new LowCostAirplane(numberRows, numberCols, manDate, manCompany, type);
            Flight newFlight = new Flight(id, price, from, to, datefrom, dateto, newAirplane);
            this.addFlight(newFlight);
            this.flights.sort(new SortByDate());
        }
        System.out.println("Flight added.");
    }

    public void removeFlight(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce id: ");
        String id = sc.nextLine();
        int isInList = -1;
        for(int i = 0; i < this.flights.size(); ++i){
            if(this.flights.get(i).getId().equals(id)){
                isInList = i;
                break;
            }
        }
        if(isInList == -1){
            System.out.println("Not in list.");
        }
        else{
            this.removeFlight(this.flights.get(isInList));
            System.out.println("Flight deleted.");
        }
    }

    public Client logInClient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        for(Client c: this.clients){
            if(c.getUsername().equals(username) && c.getUsername().equals(username)){
                System.out.println("Logged in.");
                return c;
            }
        }

        System.out.println("You don't have an account. Enter your information to register.");
        String lastName = sc.nextLine();
        String firstName = sc.nextLine();
        String CNP = sc.nextLine();
        String dateBirth = sc.nextLine();
        String serie = sc.nextLine();
        String no = sc.nextLine();
        String birthPlace = sc.nextLine();
        String homeAdress = sc.nextLine();
        String issuedBy = sc.nextLine();
        String beginValidity = sc.nextLine();
        String endValidity = sc.nextLine();

        IdentityCard IC = new IdentityCard(lastName, firstName, CNP, dateBirth, serie, no, birthPlace, homeAdress, issuedBy, beginValidity, endValidity);

        String email = sc.nextLine();
        String phoneNumber = sc.nextLine();
        Client newClient = new Client(IC, username, password, email, phoneNumber);

        System.out.println("Account created");
        this.clients.add(newClient);
        return newClient;
    }

    public void displayClients(){
        for(Client c: this.clients){
            System.out.println(c.toString());
        }
    }

    public void buyTicket(Client cl, String flightID){
        if(cl.getClientTicket() != null) return;
        Scanner sc = new Scanner(System.in);
        int found = -1;
        for(int i = 0; i < this.flights.size(); i++){
            if(this.flights.get(i).getId().equals(flightID)){
                found = i;
                break;
            }
        }
        if(found == -1){
            System.out.println("There is no such flight.");
        }
        else{
            System.out.println("Do you want Economic or Business seat?");
            char typeSeat = sc.next().charAt(0);
            Airplane actualAirplane = this.flights.get(found).getAirplane();
            Flight actualFlight = this.flights.get(found);
            for(Seat s: actualAirplane.getSeats()){
                if(s.getType() == typeSeat && s.getAvailability()){
                    String dateBirth = new SimpleDateFormat("MM-dd-YYYY").format(cl.getIC().dateBirth);
                    String dateFrom = new SimpleDateFormat("MM-dd-YYYY").format(actualFlight.getDateFrom());

                    double price = actualFlight.getPrice();
                    if(s.getType() == 'B') price *= price;

                    Ticket newTicket = new Ticket(cl.getIC().getLastName(), cl.getIC().firstName, cl.getIC().getCNP(), dateBirth, price,
                            actualFlight.getFrom(), actualFlight.getTo(), dateFrom, s.getColumn(), s.getRow(), s.getType());
                    cl.setClientTicket(newTicket);

                    this.flights.get(found).addTicket(newTicket);
                    System.out.println("You bought a ticket! Price is " + price);
                    return;
                }
            }
            System.out.println("No available seat.");
        }
    }

    public void unavailableSeats(String flightID){
        Scanner sc = new Scanner(System.in);
        int found = -1;
        for(int i = 0; i < this.flights.size(); i++){
            if(this.flights.get(i).getId().equals(flightID)){
                found = i;
                break;
            }
        }
        if(found == -1){
            System.out.println("There is no such flight.");
        }
        else{
            this.flights.get(found).displayUnavailableSeats();
        }
    }
    public void mostPopularandRecentFlight(){
        if(this.flights.size() == 0){
            System.out.println("There is no flight.");
            return;
        }
        this.flights.sort(new SortByDate());
        int maximumClients = 0;
        int numberFlight = -1;
        for(int i = 0; i < this.flights.size(); ++i){
            Flight f = this.flights.get(i);
            int actualClients = 0;
            for(Seat s: f.getAirplane().getSeats()){
                if(!s.getAvailability()){
                    actualClients += 1;
                }
            }
            if(actualClients > maximumClients){
                maximumClients = actualClients;
                numberFlight = i;
            }
        }
        System.out.println(this.flights.get(numberFlight).toString());
    }

    public void removeTicket(Client cl, String flightID){
        if(cl.getClientTicket() == null) return;
        Scanner sc = new Scanner(System.in);
        int found = -1;
        for(int i = 0; i < this.flights.size(); i++){
            if(this.flights.get(i).getId().equals(flightID)){
                found = i;
                break;
            }
        }
        if(found == -1){
            System.out.println("There is no such flight.");
        }
        else{
            this.flights.get(found).removeTicket(cl.getClientTicket());
            cl.setClientTicket(null);
            System.out.println("You removed the ticket.");
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Map<Airplane, Integer> getRunways() {
        return runways;
    }

    public void setRunways(Map<Airplane, Integer> runways) {
        this.runways = runways;
    }
}
