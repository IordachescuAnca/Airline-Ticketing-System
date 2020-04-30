package FileServices;

import Entities.Client;
import Entities.IdentityCard;
import Entities.Ticket;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientFileService {
    private static ClientFileService instance = null;
    private final String path = "clients.csv";
    private ClientFileService(){}

    public static ClientFileService getInstance(){
        if(instance == null){
            return new ClientFileService();
        }
        return instance;
    }

    public void writeClients(Client newClient){
        FileWriter clientFile = null;
        try{
            clientFile = new FileWriter(path, true);
            List<String> details = new ArrayList<>();
            details.add(newClient.getUsername());
            details.add(newClient.getPassword());
            details.add(newClient.getPhoneNumber());
            details.add(newClient.getEmail().toString());
            details.add(newClient.getIC().getLastName());
            details.add(newClient.getIC().getFirstName());
            details.add(newClient.getIC().getCNP());

            Date dateBirth = newClient.getIC().getDateBirth();
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String strDate = dateFormat.format(dateBirth);
            details.add(strDate);

            details.add(newClient.getIC().getSerie());
            details.add(newClient.getIC().getNumber());
            details.add(newClient.getIC().getBirthPlace());
            details.add(newClient.getIC().getHomeAdress());
            details.add(newClient.getIC().getIssuedBy());

            Date dateBegin = newClient.getIC().getBeginValidity();
            String strDateBegin = dateFormat.format(dateBegin);
            details.add(strDateBegin);

            Date dateEnd = newClient.getIC().getEndValidity();
            String strDateEnd = dateFormat.format(dateEnd);
            details.add(strDateEnd);
            if(newClient.getClientTicket() == null){
                details.add("NO");
            }
            else{
                details.add("YES");
                details.add(String.valueOf(newClient.getClientTicket().getPrice()));
                details.add(newClient.getClientTicket().getFrom());
                details.add(newClient.getClientTicket().getTo());
                Date dateFrom = newClient.getClientTicket().getDateFrom();
                String strFrom = dateFormat.format(dateFrom);
                details.add(strFrom);
                details.add(String.valueOf(newClient.getClientTicket().getRow()));
                details.add(String.valueOf(newClient.getClientTicket().getCol()));
                details.add(String.valueOf(newClient.getClientTicket().getType()));
            }
            clientFile.append(String.join(",", details));
            clientFile.append("\n");
        }
        catch(IOException exception){
            System.out.println("Error!");
        }
        finally {
            try {
                assert clientFile != null;
                clientFile.flush();
                clientFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");

            }
        }
    }
    public List<Client> readClients(){
        List<Client> clients = new ArrayList<>();
        BufferedReader clientFile = null;
        try{
            clientFile = new BufferedReader(new FileReader(path));
            String line;
            while((line = clientFile.readLine()) != null){
                String [] data = line.split(",");
                IdentityCard recordedIC = new IdentityCard(data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14]);
                Client recordedClient = new Client(recordedIC, data[0], data[1], data[3], data[2]);
                if(data[15].compareTo("YES") == 0){
                    Ticket recordedTicket = new Ticket(data[4], data[5], data[7], data[8], Double.parseDouble(data[16]), data[17], data[18], data[19], data[21].charAt(0), Integer.parseInt(data[20]), data[22].charAt(0));
                    recordedClient.setClientTicket(recordedTicket);
                }
                clients.add(recordedClient);
            }
        }
        catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert clientFile != null;
                clientFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");
            }
        }
        return clients;
    }
    public void updateClients(List<Client> clients){
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error!");
        }
        for(Client actualClient: clients){
            this.writeClients(actualClient);
        }
    }
}
