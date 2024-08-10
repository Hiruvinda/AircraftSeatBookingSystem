import java.util.Scanner;

public class w2053737_PlaneManagement {

    static int[][] seats;
    static Ticket[] tickets;

    public static void main(String[] args) {

        System.out.println("Welcome to the Plan Management application");
        // Initialize all seats
        seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];

        tickets = new Ticket[52]; // maximum tickets 48

        Scanner input = new Scanner(System.in);
        // Menu loop for seat management

        while(true){
            System.out.println("**************************************************");
            System.out.println("*                   MENU OPTIONS                 *");
            System.out.println("**************************************************");
            System.out.println("1) Buy s seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("**************************************************");
            System.out.println("Please select an option");
            int option = input.nextInt();
            switch (option){
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_ticket_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.out.println("Exiting");
                    return; // Quit the program
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
    private static void buy_seat(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter row letter [A,B,C,D]: ");
        String rowLetter = input.nextLine().toUpperCase();
        //Validation for row letter
        if (rowLetter.charAt(0) < 'A' || rowLetter.charAt(0) > 'D') {
            System.out.println("Invalid row letter.");
            return;
        }
        System.out.println("Enter seat number: ");
        int seatNumber = input.nextInt();
        int row = rowLetter.charAt(0) - 'A';
        //Validation for seat number
        if (seatNumber < 1 || seatNumber > seats[row].length  ){
            System.out.println("Invalid seat number.");
            return;
        }
        if (seats[row][seatNumber-1] == 1){
            System.out.println("Seat is already sold.");
            return;
        }
        // Person informations
        System.out.println("Enter person's name: ");
        String name = input.next();
        System.out.println("Enter person's surname: ");
        String surname = input.next();
        System.out.println("Enter person's email: ");
        String email = input.next();

        // person object
        Person person = new Person(name, surname, email);

        // seat price
        double price;
        if (seatNumber >=1 && seatNumber <=5){
            price = 200;
        } else if (seatNumber >= 6 && seatNumber <= 9){
            price = 150;
        } else if (seatNumber >= 10 && seatNumber <= 14){
            price = 180;
        } else {
            System.out.println("Invalid seat number");
            return;
        }
        // Ticket object
        Ticket ticket = new Ticket(rowLetter.charAt(0), seatNumber, price, person);

        //Array of ticket
        for (int i = 0; i< tickets.length; i++){
            if (tickets[i] == null){
                tickets[i] = ticket;
                break;
            }
        }

        // Sold seat
        seats[row][seatNumber-1]= 1;
        // Save ticket information to the file
        ticket.save();

        System.out.println("seat "+ rowLetter + seatNumber + " has been sold.");


    }
    private static void cancel_seat() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter row letter [A, B, C, D]: ");
        String rowLetter = input.nextLine().toUpperCase();
        System.out.println("Enter seat number: ");
        int seatNumber = input.nextInt();
        int row = rowLetter.charAt(0) - 'A';
        if (seatNumber < 1 || seatNumber > seats[row].length ||  row  < 0 || row >= seats.length) {
            System.out.println("Invalid row or seat number.");
            return;
        }
        if (seats[row][seatNumber-1] == 0){
            System.out.println("Seat is already available.");
            return;
        }

        // Remove ticket from array
        for (int i =0; i < tickets.length; i++){
            if (tickets[i] != null && tickets[i].getRow() == rowLetter.charAt(0) && tickets[i].getSeat() == seatNumber){
                tickets[i] = null;
                break;
            }
        }
        seats[row][seatNumber-1] = 0;
        System.out.println("Seat " + rowLetter + seatNumber + " has been cancelled.");


    }
    private static void find_first_available(){
        for (int row = 0; row < seats.length; row++){
            for (int seat = 0; seat < seats[row].length; seat++){
                if (seats[row][seat] == 0){
                    char rowLetter = (char) ('A' + row);
                    int seatNumber = seat + 1;
                    System.out.println("First available seat: " + rowLetter + seatNumber);
                    return;
                }
            }
        }
    }
    private static void show_seating_plan(){
        for (int row = 0; row < seats.length; row++) {
            // add a space
            if ( row == 2){
                System.out.println(" ");
            }
            for (int seat = 0; seat < seats[row].length; seat++) {
                if (seats[row][seat] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    private static void print_ticket_info(){
        double totalAmount = 0;
        for (Ticket ticket : tickets){
            if (ticket != null){
                System.out.println("Ticket Information:");
                System.out.println("Row: " + ticket.getRow());
                System.out.println("Seat: " + ticket.getSeat());
                System.out.println("Price: £" + ticket.getPrice());
                System.out.println("Person Information:");
                System.out.println("Name: " + ticket.getPerson().getName());
                System.out.println("Surname: " + ticket.getPerson().getSurname());
                System.out.println("Email: " + ticket.getPerson().getEmail());
                System.out.println("-----------------------");
                totalAmount += ticket.getPrice();
            }
        }
        System.out.println("Total sales: £"+ totalAmount);
    }
    private static void search_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter row letter [A,B,C,D]: ");
        String rowLetter = input.nextLine().toUpperCase();
        //Validation for row letter
        if (rowLetter.charAt(0) < 'A' || rowLetter.charAt(0) > 'D') {
            System.out.println("Invalid row letter.");
            return;
        }
        System.out.println("Enter seat number: ");
        int seatNumber = input.nextInt();
        int row = rowLetter.charAt(0) - 'A';
        //Validation for seat number
        if (seatNumber < 1 || seatNumber > seats[row].length  ){
            System.out.println("Invalid seat number.");
            return;
        }
        // Find the ticket
        Ticket foundTicket = null;
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getRow() == rowLetter.charAt(0) && ticket.getSeat() == seatNumber) {
                foundTicket = ticket;
                break;
            }
        }
        // Display informations
        if (foundTicket != null){
            System.out.println("Ticket Information:");
            System.out.println("Row: " + foundTicket.getRow());
            System.out.println("Seat: " + foundTicket.getSeat());
            System.out.println("Price: £" + foundTicket.getPrice());
            System.out.println("Person Information:");
            System.out.println("Name: " + foundTicket.getPerson().getName());
            System.out.println("Surname: " + foundTicket.getPerson().getSurname());
            System.out.println("Email: " + foundTicket.getPerson().getEmail());
        } else {
            System.out.println("This seat is available.");
        }


    }

}
