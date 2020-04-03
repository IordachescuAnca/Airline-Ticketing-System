package Entities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee extends User{
    private Date hireDate;
    private String jobTitle;
    private String department;
    public Employee(IdentityCard IC, String username, String password, String email, String phoneNumber, String hireDate, String jobTitle, String department){
        super(IC, username, password, email, phoneNumber);
        try{
            String patternDate = "MM-dd-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
            this.hireDate = dateFormat.parse(hireDate);
        }
        catch(ParseException exception){
            this.hireDate = null;
        }
        this.jobTitle = jobTitle;
        this.department = department;
    }


    public void setHireDate(Date hireDate) {this.hireDate = hireDate;}
    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
    public void setDepartment(String department) {this.department = department;}

    public Date getHireDate() {return this.hireDate;}
    public String getJobTitle() {return this.jobTitle;}
    public String getDepartment() {return this.department;}

    @Override
    public String toString() {
        String hireDateStr = new SimpleDateFormat("MM-dd-YYYY").format(this.hireDate);
        return super.toString() + "\nHire Date: " + hireDateStr + "\nJob Title: "
                + this.jobTitle + "\nDepartment: " + this.department;
    }

    public Employee copy() {
        String hireDateStr = new SimpleDateFormat("MM-dd-YYYY").format(this.hireDate);
        return new Employee(IC, username, password, email.toString(), phoneNumber, hireDateStr, jobTitle, department);
    }
}
