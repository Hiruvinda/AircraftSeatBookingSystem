import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    //Constructor
    public Ticket(char row, int seat, double price, Person person){
        setRow(row);
        setSeat(seat);
        setPrice(price);
        setPerson(person);
    }

    //Getters
    public char getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person;
    }

    //Setters
    public void setRow(char row){
        this.row = row;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setPerson(Person person){
        this.person = person;
    }

    //Save ticket informations
    public void save(){
        String fileName = this.row + String.valueOf(this.seat) + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Ticket Information:\n");
            writer.write("Row: " + this.row + "\n");
            writer.write("Seat: " + this.seat + "\n");
            writer.write("Price: £" + this.price + "\n");
            writer.write("Person Information:\n");
            writer.write("Name: " + this.person.getName() + "\n");
            writer.write("Surname: " + this.person.getSurname() + "\n");
            writer.write("Email: " + this.person.getEmail() + "\n");
            System.out.println("Ticket information saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information to file.");

        }
    }
}
