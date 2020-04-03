package Entities;


public class Client extends User{
    private Ticket clientTicket;
    public Client(){
        super();
    }
    public Client(IdentityCard IC, String username, String password, String email, String phoneNumber) {
        super(IC, username, password, email, phoneNumber);
        clientTicket = null;
    }
    public Client(IdentityCard IC, String username, String password, String email, String phoneNumber, Ticket clientTicket) {
        super(IC, username, password, email, phoneNumber);
        this.clientTicket = clientTicket.copy();
    }

    @Override
    public String toString() {
        if(this.clientTicket != null) {
            return super.toString() + "\n\nTICKET:\n" + "Price: " + this.clientTicket.getPrice()
                    + "\nFrom: " + this.clientTicket.getFrom();
        }
        else{
            return super.toString() + "\nNo ticket.";
        }
    }

    public Client copy() {
        return new Client(IC, username, password, email.toString(), phoneNumber);
    }

    public void setClientTicket(Ticket clientTicket) {this.clientTicket = clientTicket;}

    public Ticket getClientTicket() {return this.clientTicket;}
}
