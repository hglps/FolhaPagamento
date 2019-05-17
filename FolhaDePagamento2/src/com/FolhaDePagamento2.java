package com;

import java.util.Scanner;

public class FolhaDePagamento2 {

    final static int maxSize=100;

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

    public static void main(String[] args) {
        setAllIds();
        int choice, indexArray = 19002100;
        int auxIndex = indexArray;
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
                addEmployee(auxIndex);
                auxIndex+=1;
            }
            else if(choice == 2){
                //delete employee
                System.out.println("Insert ID:");
                int selectedId = read.nextInt();

                deleteEmployee(selectedId);

                //if(listWorkers[idEmployee] != null){
                    //listWorkers[idEmployee] = null;
                   // System.out.println("\nEmployee deleted from system with success!");
                //}
                //else System.out.println("The selected ID is not associated with any employee registered!\nOperation not executed");



            }
            else if(choice == 3){
                //get bate ponto and insert in employee data
            }
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
                //int index = getIndexArray(idEmployee);

                //if(id){
                    //listWorkers[ idEmployee ] = addEmployee(listWorkers[ idEmployee ], idEmployee, 6);
                    //System.out.println("Info from employee: " +listWorkers[ idEmployee ].getName()+  " and ID: "+idEmployee+ " modified!");
                //}

                //else System.out.println("Employee not found!\nChanges must not be applied!");




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
                System.out.println("select id:");
                int selectedEmployee = read.nextInt();
                int index = getIndexArray(selectedEmployee);
                if(id[index] != -1){

                    System.out.println("name="+ name[index]);
                    System.out.println("address="+ address[index]);
                    System.out.println("type of payment="+typePayment[index] );
                    System.out.println("id="+ id[index] );
                    System.out.println("part of union= "+ unionMember[index] );
                    System.out.println("union fee= "+ unionFee[index]);
                }
                else System.out.println("Employee not found\n\n");
            }
            else System.out.println("Invalid option!\nPlease, insert a valid option:\n");
            visualizeOptions(); // interface

        }





    }

    private static void setAllIds() {
        for(int i=0; i< maxSize; i++){
            id[i] = -1;
        }
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

    private static void addEmployee(int idEmployee){
        read.nextLine();
//        for(int i=0;i<maxSize;i++){
//            if(name[i] == null){
//
//            }
//        }
        int indexArray = getIndexArray(idEmployee);
        String partOfUnion;
        System.out.println("/////////////////////////////////////////////////////////////////");
        System.out.print("Insert your name: "); name[indexArray] = read.nextLine();
        System.out.print("Insert your address: "); address[indexArray]= read.nextLine();
        System.out.println("Insert the type of payment:\n"+
                           "h - hourly / s - salaried / c - commissioned"); typePayment[indexArray]= read.nextLine();
        System.out.println("Insert how you want to get paid:\n"+
                           "mail - check via mail  /  hands - check via hands  /  deposit - pay via deposit");  wayPayment[indexArray] = read.nextLine();
        System.out.println("Are you part of any union?\n"+
                         "yes  /  no"); partOfUnion = read.nextLine();
        unionMember[indexArray] = partOfUnion.equals("yes");
        if(unionMember[indexArray]){
            System.out.print("Then, insert the union fee: "); unionFee[indexArray] = read.nextDouble();
        }
        id[indexArray] = idEmployee;
        System.out.println("Welcome "+ name[indexArray] + "!\nID: " + id[indexArray]);



    }

    private static void deleteEmployee(int idEmployee){
        int indexArray = getIndexArray(idEmployee);

        if(id[indexArray] != -1){
            String savedName = name[indexArray];
            name[indexArray] = null;
            address[indexArray] = null;
            typePayment[indexArray] = null;
            wayPayment[indexArray]= null;
            salary[indexArray] = 0;
            commission[indexArray] = 0;
            unionFee[indexArray] = 0;
            unionMember[indexArray] = false;
            id[indexArray] = 0;

            System.out.println(savedName + " - " + idEmployee +" was deleted from system successfully!");
        }
        else System.out.println("Employee not found!");


    }
    private static int getIndexArray(int index){
        return index - 19002100;
    }

    private static int biggerIndexArray(int index){
        return index + 19002100;
    }


}
