
public class Person {
    private String name;
    private String surname;
    private String email;

    // Constuctor
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Getters
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){
        return email;
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setEmail(String email){
        this.email = email;
    }

    // Print informations
    public void printInfo(){
        System.out.println("Name: "+ name);
        System.out.println("Surname: "+ surname);
        System.out.println("Email: "+ email);
    }



}
