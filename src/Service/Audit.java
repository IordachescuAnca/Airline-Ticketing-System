package Service;



import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Audit {
    public void writeAction(String function){
        FileWriter audit = null;
        try {
            audit = new FileWriter("audit.csv", true);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            audit.append(function).append(",").append(strDate).append("\n");
        }
        catch(IOException exception){
            System.out.println("Error!");
        }
        finally {
            try {
                assert audit != null;
                audit.flush();
                audit.close();
            }
            catch(IOException exception){
                System.out.println("Could not close the file.");

            }
        }
    }
}
