package FileServices;

import Entities.Airplane;
import Entities.InLineAirplane;
import Entities.LowCostAirplane;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RunwayFileService {
    private static RunwayFileService instance = null;
    private final String path = "runways.csv";
    private RunwayFileService(){}

    public static RunwayFileService getInstance(){
        if(instance == null){
            return new RunwayFileService();
        }
        return instance;
    }

    public void writeRunway(Airplane newAirplane, int idRunway){
        FileWriter runwayFile = null;
        try {
            runwayFile = new FileWriter(path, true);
            List<String> details = new ArrayList<>();
            details.add(String.valueOf(newAirplane.getNumberRows()));
            details.add(String.valueOf(newAirplane.getNumberColumns()));
            Date dateManufacture = newAirplane.getManufacturingDate();
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String strDate = dateFormat.format(dateManufacture);
            details.add(strDate);
            details.add(newAirplane.getManufacturingCompany());
            details.add(newAirplane.getType());
            details.add(String.valueOf(idRunway));
            if(newAirplane instanceof InLineAirplane){
                details.add(0, "InLine");
                details.add(String.valueOf(((InLineAirplane) newAirplane).getCapacityBusiness()));
            }
            else{
                details.add(0, "LowCost");
            }
            runwayFile.append(String.join(",", details));
            runwayFile.append("\n");
        }
        catch(IOException exception){
            System.out.println("Error!");
        }
        finally {
            try {
                assert runwayFile != null;
                runwayFile.flush();
                runwayFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");

            }
        }
    }

    public HashMap<Airplane, Integer> readRunways(){
        HashMap<Airplane, Integer> runways= new HashMap<>();
        BufferedReader runwayFile = null;
        try{
            runwayFile = new BufferedReader(new FileReader(path));
            String line;
            while((line = runwayFile.readLine()) != null){
                String [] data = line.split(",");
                if(data[0].compareTo("LowCost") == 0){
                    Airplane recordedAirplane = new LowCostAirplane(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], data[4], data[5]);
                    runways.put(recordedAirplane, Integer.parseInt(data[6]));
                }
                else{
                    Airplane recordedAirplane = new InLineAirplane(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], data[4], data[5], Integer.parseInt(data[6]));
                    runways.put(recordedAirplane, Integer.parseInt(data[7]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error!");
        } finally {
            try {
                assert runwayFile != null;
                runwayFile.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");
            }
        }
        return runways;
    }

    public void updateRunways(Map<Airplane, Integer> runways){
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error!");
        }
        for(Airplane actualAirplane: runways.keySet()){
            this.writeRunway(actualAirplane, runways.get(actualAirplane));
        }
    }
}
