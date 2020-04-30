package FileServices;

import Entities.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightFileService {
    private static FlightFileService instance = null;
    private final String path = "flights.csv";
    private FlightFileService(){}

    public static FlightFileService getInstance(){
        if(instance == null){
            return new FlightFileService();
        }
        return instance;
    }

    public void writeFlight(Flight newFlight){
        FileWriter flightFile = null;
        try{
            flightFile = new FileWriter(path, true);
            List<String> details = new ArrayList<>();
            details.add(newFlight.getId());
            details.add(String.valueOf(newFlight.getPrice()));
            details.add(newFlight.getFrom());
            details.add(newFlight.getTo());

            Date dateFrom = newFlight.getDateFrom();
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String strDateFrom = dateFormat.format(dateFrom);
            details.add(strDateFrom);

            Date dateTo = newFlight.getDateTo();
            String strDateTo = dateFormat.format(dateTo);
            details.add(strDateTo);
            details.add(String.valueOf(newFlight.getAirplane().getNumberRows()));
            details.add(String.valueOf(newFlight.getAirplane().getNumberColumns()));

            Date dateManufacturing = newFlight.getAirplane().getManufacturingDate();
            String strDateManufacturing = dateFormat.format(dateManufacturing);
            details.add(strDateManufacturing);
            details.add(newFlight.getAirplane().getManufacturingCompany());
            details.add(newFlight.getAirplane().getType());

            List<Ticket> tickets = newFlight.getClientTickets();
            details.add(String.valueOf(tickets.size()));
            for(Ticket newTicket: tickets){
                details.add(newTicket.getLastName());
                details.add(newTicket.getFirstName());
                details.add(newTicket.getCNP());
                Date dateBirth = newTicket.getDateBirth();
                String strDateBirth = dateFormat.format(dateBirth);
                details.add(strDateBirth);
                details.add(String.valueOf(newTicket.getRow()));
                details.add(String.valueOf(newTicket.getCol()));
                details.add(String.valueOf(newTicket.getType()));
            }
            if(newFlight.getAirplane() instanceof InLineAirplane){
                details.add("InLine");
                details.add(String.valueOf(((InLineAirplane) newFlight.getAirplane()).getCapacityBusiness()));
            }
            else{
                details.add("LowCost");
            }
            flightFile.append(String.join(",", details));
            flightFile.append("\n");
        }
        catch(IOException exception){
            System.out.println("Error!");
        }
        finally {
            try {
                assert flightFile != null;
                flightFile.flush();
                flightFile.close();
            } catch (IOException exception) {
                System.out.println("Could not close the file.");

            }
        }
    }

    public List<Flight> readFlights(){
        List<Flight> flights = new ArrayList<>();
        BufferedReader flightFile = null;
        try{
            flightFile = new BufferedReader(new FileReader(path));
            String line;
            while((line = flightFile.readLine()) != null){
                String [] data = line.split(",");
                Airplane recordedAirplane = null;
                if(data[data.length - 1].compareTo("LowCost") == 0){
                    recordedAirplane = new LowCostAirplane(Integer.parseInt(data[6]), Integer.parseInt(data[7]), data[8], data[9], data[10]);
                }
                else{
                    recordedAirplane = new InLineAirplane(Integer.parseInt(data[6]), Integer.parseInt(data[7]), data[8], data[9], data[10], Integer.parseInt(data[data.length-1]));
                }
                Flight recordedFlight = new Flight(data[0], Double.parseDouble(data[1]), data[2], data[3], data[4], data[5], recordedAirplane);
                int numberFlight = Integer.parseInt(data[11]);
                int indexData = 11;
                for(int i = 0; i < numberFlight; ++i){
                    Ticket recordedTicket = new Ticket(data[indexData+1], data[indexData+2],data[indexData+3], data[indexData+4],
                            Double.parseDouble(data[1]), data[2], data[3], data[4], data[indexData+6].charAt(0), Integer.parseInt(data[indexData+5]), data[indexData+7].charAt(0));
                    indexData += 7;
                    recordedFlight.addTicket(recordedTicket);
                }
                flights.add(recordedFlight);
            }
        }
        catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert flightFile != null;
                flightFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");
            }
        }
        return flights;
    }

    public void updateFlights(List<Flight> flights) {
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        for (Flight actualFlight : flights) {
            this.writeFlight(actualFlight);
        }
    }
}
