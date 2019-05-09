package allsystem;
import java.util.Scanner;
import java.util.ArrayList;
public class GeneralMain {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        ArrayList listWorkers = new ArrayList();
        Worker person;


        visualizeOptions(); // interface

        while(read.hasNextInt()){

            if(read.nextInt() == 1){
                //add new employee
                //make interface later
                //listWorkers.add(new Worker())
            }
            else if(read.nextInt() == 2){
                //delete employee
            }
            else if(read.nextInt() == 3){
                //get card info and insert in employee data
            }
            else if(read.nextInt() == 4){
                //get selling info and insert in employee data
            }
            else if(read.nextInt() == 5){
                //get service fee and insert in employee data
            }
            else if(read.nextInt() == 6){
                //change basic employee info
            }
            else if(read.nextInt() == 7){
                //get date, set salary and send to employees, if necessary
            }
            else if(read.nextInt() == 8){
                // undo/redo one of previous options
            }
            else if(read.nextInt() == 9){
                //set payday defined by employee
            }
            else if(read.nextInt() == 10){
                //create new payday settings
            }
            else if(read.nextInt() == -1) { System.out.println("System finished!"); break; }
            else System.out.println("Invalid option!\nPlease, insert a valid option:\n");
        }





    }

    public static void visualizeOptions(){
        System.out.println("Insert 1 to ADD a new employee;");
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
