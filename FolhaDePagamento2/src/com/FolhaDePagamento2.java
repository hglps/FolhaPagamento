package com;

import java.util.Scanner;

public class FolhaDePagamento2 {

    final static int maxSize=100;
    final static int patternId = 19002100;

    private static Scanner read = new Scanner(System.in);
    private static String[] name = new String[maxSize];
    private static String[] address = new String[maxSize];
    private static String[] typePayment = new String[maxSize];
    private static String[] wayPayment = new String[maxSize];
    private static double[] salary = new double[maxSize];
    private static double[] commission = new double[maxSize];
    private static boolean[] unionMember = new boolean[maxSize];
    private static double[] unionFee = new double[maxSize];
    private static int[] id = new int[maxSize];
    private static int[] hourIn = new int[maxSize];
    private static int[] minuteIn = new int[maxSize];
    private static int[] secIn = new int[maxSize];
    private static int[] hourOut = new int[maxSize];
    private static int[] minuteOut = new int[maxSize];
    private static int[] secOut = new int[maxSize];

    public static void main(String[] args) {
        setAllIds();
        int choice, indexArray;
        int idEmployee;
        visualizeOptions();

        while(read.hasNextInt()){

            choice = read.nextInt();

            if(choice == -1){
                System.out.println("\n\nSystem finished!\n");
                break;
            }
            else if(choice == 1){
                //add new employee
                indexArray = setIndexArray();
                addEmployee(indexArray);
            }
            else if(choice == 2){
                //delete employee
                System.out.println("Insert ID:"); idEmployee = read.nextInt();
                deleteEmployee(idEmployee);
            }
            else if(choice == 3){
                //get bate ponto and insert in employee data
                System.out.println("Insert ID:");
                idEmployee = read.nextInt();
                indexArray = getIndex(idEmployee);
                if(id[indexArray] != -1){
                    setTimeCheck(indexArray);
                }
                else System.out.println("Employee not found!");
            }// still missing date
            else if(choice == 4){
                //get resultado de venda and insert in employee data
            }
            else if(choice == 5){
                //get service fee and insert in employee data
            }
            else if(choice == 6){
                //change basic employee info
                System.out.println("Insert ID:");
                idEmployee = read.nextInt();
                int index = getIndex(idEmployee);
                if(id[index] != -1)
                    changeRegister(index);
                else System.out.println("Employee not found!\nChanges must not be applied!");

            } //change basic info
            else if(choice == 7){
                //get rodar folha de pagamento
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
            else if(choice == 11){
                //control method
                System.out.println("Select id:");
                int selectedEmployee = read.nextInt();
                int index = getIndex(selectedEmployee);

                if(id[index] != -1){

                    System.out.println("name="+ name[index]);
                    System.out.println("address="+ address[index]);
                    System.out.println("type of payment="+typePayment[index] );
                    System.out.println("id="+ id[index] );
                    System.out.println("part of union= "+ unionMember[index] );
                    System.out.println("union fee= "+ unionFee[index]);
                    System.out.println("Check in= " + hourIn[index] + ":" + minuteIn[index] + ":" + secIn[index]);
                    System.out.println("Check out= " + hourOut[index] + ":" + minuteOut[index] + ":" + secOut[index]);
                }
                else System.out.println("Employee not found\n\n");
            }
            else System.out.println("Invalid option!\nPlease, insert a valid option:\n");
            visualizeOptions(); // interface

        }





    }

    private static void setTimeCheck(int indexArray) {
        System.out.println("Are you checking in or checking out?\n"+
                "1 - in\n"+
                "2 - out");
        int option = read.nextInt();
        read.nextLine();
        System.out.println("Insert time in format:\n"+
                "00:00:00 up to 23:59:59");
        String time = read.nextLine();

        if(option == 1){
            hourIn[indexArray] = Integer.parseInt(time.substring(0,2));
            minuteIn[indexArray] = Integer.parseInt(time.substring(3,5));
            secIn[indexArray] = Integer.parseInt(time.substring(6,8));
            System.out.println("Check-in done!");
        }
        else if(option == 2){
            hourOut[indexArray] = Integer.parseInt(time.substring(0,2));
            minuteOut[indexArray] = Integer.parseInt(time.substring(3,5));
            secOut[indexArray] = Integer.parseInt(time.substring(6,8));
            System.out.println("Check-out done!");
        }
    }


    private static void screenChangeRegister(){
        System.out.println("Select the required change:\n"+
                "1 - Name\n"+
                "2 - Address\n"+
                "3 - Type of payment\n"+
                "4 - Method of payment\n"+
                "5 - Part of union\n"+
                "6 - Union fee\n"+
                "0 - Back to main screen");
    }

    private static void visualizeOptions(){
        System.out.println("\n------------------------------------------------------------\n"+
                "Insert 1 to ADD a new employee;");
        System.out.println("Insert 2 to REMOVE an employee;");
        System.out.println("Insert 3 to UPDATE CARTAO DO PONTO of an employee;");
        System.out.println("Insert 4 to UPDATE THE RESULT OF SALES of an employee;");
        System.out.println("Insert 5 to UPDATE SERVICE FEE of an employee;");
        System.out.println("Insert 6 to CHANGE BASIC INFO of an employee;");
        System.out.println("Insert 7 to RODAR FOLHA DE PAGAMENTO DE today;");
        System.out.println("Insert 8 to UNDO OR REDO any action;");
        System.out.println("Insert 9 to SET PAYMENT SCHEDULE;");
        System.out.println("Insert 10 to CREATE NEW PAYMENT SCHEDULES;");
        System.out.println("11 to see info using ID;");
        System.out.println("Insert -1 to end;");
        System.out.print("Option:  ");

    }

    private static int setIndexArray() {
        int indexArray = -1;
        for(int i=0; i<maxSize; i++){
            if(name[i] == null){
                indexArray = i;
                break;
            }
        }
        return indexArray;
    } // really bad performance.. fix it later //return index (0 to maxSize)

    private static void setAllIds() {
        for(int i=0; i< maxSize; i++){
            id[i] = -1;
        }
    } // set all ID to -1

    private static int getId(int index){
        return index + patternId;
    }

    private static int getIndex(int id){
        return id - patternId;
    }

    private static void addEmployee(int index){
        read.nextLine();
        String partOfUnion;

        System.out.println("/////////////////////////////////////////////////////////////////");
        System.out.print("Insert your name: "); name[index] = read.nextLine();
        System.out.print("Insert your address: "); address[index]= read.nextLine();
        System.out.println("Insert the type of payment:\n"+
                           "h - hourly / s - salaried / c - commissioned"); typePayment[index]= read.nextLine();
        System.out.println("Insert how you want to get paid:\n"+
                           "mail - check via mail  /  hands - check via hands  /  deposit - pay via deposit");  wayPayment[index] = read.nextLine();
        System.out.println("Are you part of any union?\n"+
                         "yes  /  no"); partOfUnion = read.nextLine();
        unionMember[index] = partOfUnion.equals("yes");
        if(unionMember[index]){
            System.out.print("Then, insert the union fee: "); unionFee[index] = read.nextDouble();
        }
        id[index] = getId(index);
        System.out.println("Welcome "+ name[index] + "!\nID: " + id[index]);

        System.out.println("/////////////////////////////////////////////////////////////////");



    }

    private static void changeRegister(int index) {
        System.out.println("\n------------------------------------------------------------\n");
        boolean nonStop = true;
        while(nonStop){
            screenChangeRegister();
            int option = read.nextInt();

            if(option == 0) nonStop = false;

            else if(option == 1){
                System.out.println("\nInsert new name:");
                name[index] = read.nextLine();
                System.out.println("New name: "+ name[index] + "\nDone!");
            }

            else if(option == 2){
                System.out.println("\nInsert new address");
                address[index]= read.nextLine();
                System.out.println("New address:"+ address[index]+ "\nDone!");
            }

            else if(option == 3){
                System.out.println("\nInsert new type of payment:\n"+
                        "h - hourly / s - salaried / c - commissioned"); typePayment[index]= read.nextLine();
                System.out.println("Done!" );
            }

            else if(option == 4){
                System.out.println("\nInsert new method of payment:\n"+
                        "mail - check via mail  /  hands - check via hands  /  deposit - pay via deposit");  wayPayment[index] = read.nextLine();
                System.out.println("Done!");
            }

            else if(option == 5){
                String unionPart;
                System.out.println("\nAre you part of any union?\n"+
                        "yes  /  no"); unionPart = read.nextLine();
                unionMember[index] = unionPart.equals("yes");
                System.out.println("Done!");
            }

            else if(option == 6){
                if(unionMember[index]){
                    System.out.println("\nInsert new union fee:");
                    unionFee[index]= read.nextDouble();
                    System.out.println("New union fee: "+ unionFee[index]+ "\nDone!");
                }
                else System.out.println("\nOption only available for union members!");
            }
        }

        System.out.println("\nAll changes made successfully! Going back to main screen!");
        System.out.println("\n------------------------------------------------------------\n");


    }

    private static void deleteEmployee(int idEmployee){
        int index = getIndex(idEmployee);

        if(id[index] != -1){
            String savedName = name[index];
            name[index] = null;
            address[index] = null;
            typePayment[index] = null;
            wayPayment[index]= null;
            salary[index] = 0;
            commission[index] = 0;
            unionFee[index] = 0;
            unionMember[index] = false;
            id[index] = -1;

            System.out.println(savedName + " - " + idEmployee +" was deleted from the system successfully!\n");
            System.out.println("\n------------------------------------------------------------\n");
        }
        else{
            System.out.println("The selected ID is not associated with any employee registered!\n"+
                    "Operation not executed\n");
            System.out.println("\n------------------------------------------------------------\n");
        }



    }




}
