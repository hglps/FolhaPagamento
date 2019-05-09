package allsystem;
import java.util.Scanner;

public class GeneralMain {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        Worker listWorkers[] = new Worker[1000];
        int indexArray=0;
        int choice;

        visualizeOptions(); // interface

        while(read.hasNextInt()){

            choice = read.nextInt();
            if(choice == -1){
                System.out.println("\n\nSystem finished!\n\n");
                break;
            }
            else if(choice == 1){
                //add new employee
                read.nextLine();
                System.out.print("Name:");
                String name = read.nextLine();
                System.out.print("Adress:");
                String address = read.nextLine();
                System.out.print("Type of payment:");
                String typePayment = read.nextLine();
                System.out.print("Attribute:");
                String typeSalary = read.nextLine();

                listWorkers[indexArray] = new Worker(name,address,typePayment,indexArray);
                indexArray+=1;


            }
            else if(choice == 2){
                //delete employee
                System.out.println("select id:");
                int selected = read.nextInt();
                System.out.println("name="+listWorkers[selected].getName());
                System.out.println("address="+listWorkers[selected].getAddress());
                System.out.println("type of payment="+listWorkers[selected].getTypePayment());
                System.out.println("id="+listWorkers[selected].getId());
            }
            else if(choice == 3){
                //get card info and insert in employee data
            }
            else if(choice == 4){
                //get selling info and insert in employee data
            }
            else if(choice == 5){
                //get service fee and insert in employee data
            }
            else if(choice == 6){
                //change basic employee info
            }
            else if(choice == 7){
                //get date, set salary and send to employees, if necessary
            }
            else if(choice == 8){
                // undo/redo one of previous options
            }
            else if(choice == 9){
                //set payday defined by employee
            }
            else if(choice == 10){
                //create new payday settings
            }
            else System.out.println("Invalid option!\nPlease, insert a valid option:\n\n");
            visualizeOptions(); // interface

        }





    }

    public static void visualizeOptions(){
            System.out.println("\n\nInsert 1 to ADD a new employee;");
            System.out.println("Insert 2 to REMOVE an employee;");
            System.out.println("Insert 3 to UPDATE TIME CARD of an employee;");
            System.out.println("Insert 4 to UPDATE THE RESULT OF SALES of an employee;");
            System.out.println("Insert 5 to UPDATE SERVICE FEE of an employee;");
            System.out.println("Insert 6 to CHANGE REGISTER of an employee;");
            System.out.println("Insert 7 to SET PAYMENTS OF PAYROLL of today;");
            System.out.println("Insert 8 to UNDO OR REDO any mistaken action;");
            System.out.println("Insert 9 to SET PAYMENT SCHEDULE;");
            System.out.println("Insert 10 to CREATE NEW PAYMENT SCHEDULES;");
            System.out.println("Insert -1 to end;");
            System.out.print("Option: ");

    }

//    public static void addEmployee(){
//
//
//
//    }

}
