package com;

import java.util.Scanner;

public class FolhaDePagamento3 {

    private final static int maxSize = 500; // max size of array
    private final static int patternId = 19002100; // used to generate ID
    private final static double commissionFee = 0.05; /// 5% commission
    private final static double hourSalary = 50; // R$50,00/hour worked

    private static Scanner read = new Scanner(System.in);
    private static String[] name = new String[maxSize];
    private static String[] address = new String[maxSize];
    private static String[] typePayment = new String[maxSize]; // salaried(monthly paid) , hourly(weekly paid) or commissioned(bi-weekly paid)
    private static String[] wayPayment = new String[maxSize]; // way the employee chose to be paid= check in hands, deposit via bank or check via mail
    private static boolean[] unionMember = new boolean[maxSize]; // if employee is part of union
    private static int[] id = new int[maxSize];
    private static String[] payday = new String[maxSize];
    // format: (char) (2 numbers x) (2 numbers y)
    // char = 'm' - monthly or 's' - weekly
    // x = '01' or '02' (meaning paid weekly or bi-weekly) or the day of payment, if is salaried, formatted as 'xx', '00-25'
    // y = '0' - '6' (meaning the day of the week (0 - Monday, 1- Tuesday...). Only used if char is m - monthly

    private static int[] weeksWorked = new int[maxSize]; // counter to check if employee worked the required weeks to be paid
    private static int[] daysWorked = new int[maxSize]; ///////// counter to check if salaried employee worked the required days to be paid full base salary
    private static boolean[] paidUnionFee = new boolean[maxSize]; // only used with commissioned and hourly. Check if employee already paid the union fee, or else, they'll pay it twice or 4 times in a month

    private static double[] salary = new double[maxSize];
    private static double[] baseSalary = new double[maxSize];
    private static double[] totalSalary = new double[maxSize];
    private static double[] commission = new double[maxSize];
    private static double[] unionFee = new double[maxSize];
    private static double[] serviceFee = new double[maxSize];


    private static String[] schedules = new String[maxSize];
    private static int counterSchedules = 0;

    private static int[] hourIn = new int[maxSize];
    private static int[] minuteIn = new int[maxSize];
    private static int[] hourOut = new int[maxSize];
    private static int[] minuteOut = new int[maxSize];

    private static int year = 2019;
    private static int[] day = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int counterDate = 1;
    private static String[] month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static int counterMonth = 0;
    private static String[] week = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static int dayWeek = 1; // monday, tuesday...
    private static int lastWorkDay = 0;


    public static void main(String[] args) {
        String password = "admin";
        String reading;
        while(true){
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||Insert password to access payroll system or insert 'quit' to quit the system:||");
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.print("Password: ");
            reading = read.nextLine();
            if(reading.equals(password)){
                setAllIds();
                getLastWorkDay();

                int choice, indexArray;
                int idEmployee;
                String currentPasswordCheck;
                visualizeOptions();

                while (read.hasNextInt()) {

                    choice = read.nextInt();

                    if (choice == -1) {
                        System.out.println("\n\nPayroll system finished\n");
                        break;
                    }
                    else if (choice == 1) {
                        //add new employee
                        indexArray = setIndexArray();
                        addEmployee(indexArray);
                    }
                    else if (choice == 2) {
                        //delete employee
                        System.out.println("Insert ID:");
                        idEmployee = read.nextInt();
                        deleteEmployee(idEmployee);
                    }
                    else if (choice == 3) {
                        //get bate ponto and insert in employee data
                        System.out.println("Insert ID:");
                        idEmployee = read.nextInt();
                        indexArray = getIndex(idEmployee);
                        if (id[indexArray] != -1) {
                            setTimeCheck(indexArray);
                        }
                        else System.out.println("Employee not found !");
                    }
                    else if (choice == 4) {
                        System.out.println("Insert ID:");
                        idEmployee = read.nextInt();
                        resultSales(idEmployee);
                        //get resultado de venda and insert in employee data
                    }
                    else if (choice == 5) {
                        //get service fee and insert in employee data
                        System.out.println("Insert ID:");
                        idEmployee = read.nextInt();
                        serviceFee(idEmployee);
                    }
                    else if (choice == 6) {
                        //change basic employee info
                        System.out.println("Insert ID:");
                        idEmployee = read.nextInt();
                        int index = getIndex(idEmployee);
                        if (id[index] != -1)
                            changeRegister(index);
                        else System.out.println("Employee not found!\nChanges must not be applied!");

                    } //change basic info
                    else if (choice == 7) {
                        warningPayroll();
                        if(read.nextInt() == 1)
                            payroll();
                        else
                            System.out.println("----------------------------------BACK TO MAIN SCREEN---------------------------------!");
                        //get rodar folha de pagamento
                    }
                    else if (choice == 8) {
                        // undo/redo one of previous options
                    }
                    else if (choice == 9) {
                        //set payday defined by employee
                        System.out.println("Insert ID:");
                        idEmployee = read.nextInt();
                        definePayday(idEmployee);
                    }
                    else if (choice == 10) {
                        //create payment schedules
                        createSchedules();
                    }
                    else if (choice == 11) {
                        //Shows info of 1 or all employees
                        showInfoEmployee();
                    }
                    else if(choice == 12){
                        System.out.print("Insert the current password:");
                        currentPasswordCheck = read.nextLine();
                        if(currentPasswordCheck.equals(password)){
                            System.out.print("Ok, now insert the new password: ");
                            password = read.nextLine();
                            System.out.println("The new password is "+ password + " is set");
                        }
                        else
                            System.out.println("Wrong password!");
                    }
                    else System.out.println("Invalid option!\nPlease, insert a valid option:\n");

                    visualizeOptions(); // interface

                }


            }
            else if(reading.equalsIgnoreCase("quit"))
                break;
            else
                System.out.println("\nInvalid password! Try again!\n");
        }
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||System completely finished!|||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");


    }

    private static void warningPayroll(){
        System.out.println("------------------------------------------WARNING-------------------------------------!");
        System.out.println("//Once you execute the daily payroll, a new date will be set!                        //");
        System.out.println("//If you want to continue, insert 1 or insert anything else to go back to Main Screen//");
    } ////////////////////////////////////////////////DONE

    private static void showInfoEmployee() {
        System.out.println("Show 1 employee or all of them? Insert '1' for 1 employee/// Insert 'all' for all employees");
        int index;
        int selectedEmployee;
        read.nextLine();
        String opt = read.nextLine();
        getDate();
        if (opt.equalsIgnoreCase("all")) {
            for (int i = 0; i < maxSize; i++) {
                if (id[i] != -1) {
                    System.out.println("Name= " + name[i]);
                    System.out.println("Address= " + address[i]);
                    System.out.print("Type of payment= ");
                    if (typePayment[i].equals("c")) {
                        System.out.println("commissioned");
                        System.out.println("Base salary: R$" + baseSalary[i]);
                    } else if (typePayment[i].equals("s")) {
                        System.out.println("salaried");
                        System.out.println("Base salary: R$" + baseSalary[i]);
                    } else {
                        System.out.println("hourly");
                        System.out.println("Id=" + id[i]);
                        System.out.print("Part of union= ");
                    }
                    if (unionMember[i])
                        System.out.println("yes");
                    else
                        System.out.println("no");
                    if (unionMember[i]) {
                        System.out.println("Union fee= " + unionFee[i]);
                        System.out.println("Services fee= " + serviceFee[i]);
                    }
                    System.out.println("Check in= " + hourIn[i] + ":" + minuteIn[i]);
                    System.out.println("Check out= " + hourOut[i] + ":" + minuteOut[i]);
                    System.out.println("Total salary until now: " + totalSalary[i]);
                    System.out.println("Way of payment: " + wayPayment[i]);
                }
            }
        } else if (opt.equalsIgnoreCase("1")) {
            System.out.println("Insert ID:");
            selectedEmployee = read.nextInt();
            index = getIndex(selectedEmployee);

            System.out.println("Name= " + name[index]);
            System.out.println("Address= " + address[index]);
            System.out.print("Type of payment= ");
            if (typePayment[index].equals("c")) {
                System.out.println("commissioned");
                System.out.println("Base salary: R$" + baseSalary[index]);
            } else if (typePayment[index].equals("s")) {
                System.out.println("salaried");
                System.out.println("Base salary: R$" + baseSalary[index]);
            } else
                System.out.println("hourly");
            System.out.println("Id=" + id[index]);
            System.out.print("Part of union= ");
            if (unionMember[index])
                System.out.println("yes");
            else
                System.out.println("no");
            if (unionMember[index]) {
                System.out.println("Union fee= " + unionFee[index]);
                System.out.println("Services fee= " + serviceFee[index]);
            }
            System.out.println("Check in= " + hourIn[index] + ":" + minuteIn[index]);
            System.out.println("Check out= " + hourOut[index] + ":" + minuteOut[index]);
            System.out.println("Total salary until now: " + totalSalary[index]);
            System.out.println("Way of payment: " + wayPayment[index]);
        } else
            System.out.println("Employee not found\n\n");
    } /////////////////////////////////////////////DONE

    private static void getDate() {
        System.out.println(week[dayWeek] + " - " + month[counterMonth] + " " + counterDate + ", " + year);
    }///////////////////////////////////////////////////////DONE

    private static void setNewDate() {
        counterDate += 1;
        if (counterDate > day[counterMonth]) {
            counterDate = 1;
            if (counterMonth + 1 > 12)
                year += 1;
            counterMonth = (counterMonth + 1) % 12;
            for (int i = 0; i < maxSize; i++) { /// Evita que comissionados e horistas paguem + de 1 vez a taxa do sindicato.. ver em payroll
                if (id[i] != -1)
                    paidUnionFee[i] = false;
            }
            getLastWorkDay();
        }
        dayWeek = (dayWeek + 1) % 7;


    }////////////////////////////////////////////////////DONE

    private static void getLastWorkDay() {
        int lastDay = day[counterMonth];
        String dayOfLast = week[(dayWeek + lastDay) % 7];

        if (dayOfLast.equals("Saturday")) lastDay -= 1;
        else if (dayOfLast.equals("Sunday")) lastDay -= 2;

        lastWorkDay = lastDay;

    } ///////////////////////////////////////////////DONE

    private static void payroll() {
        int index;
        getDate();

        for (int i = 0; i < maxSize; i++) {
            index = i;
            if (id[index] != -1) {
                if (payday[index].substring(0, 1).equals("m")) {// if is salaried
                    if (payday[index].substring(2, 4).equals("00")) { // if is ultimo dia util
                        if (counterDate == lastWorkDay) {
                            salary[index] = salary[index] - unionFee[index] - serviceFee[index];
                            totalSalary[index] += salary[index];
                            System.out.println(name[index] + " - Salary = R$" + salary[index] + " via " + wayPayment[index]);
                            salary[index] = 0;
                            serviceFee[index] = 0;
                        }
                    }
                    else {
                        if (counterDate == Integer.parseInt(payday[index].substring(2, 4))) {
                            salary[index] = salary[index] - unionFee[index] - serviceFee[index];
                            totalSalary[index] += salary[index];
                            System.out.println(name[index] + " - Salary = R$" + salary[index] + " via " + wayPayment[index]);
                            salary[index] = 0;
                            serviceFee[index] = 0;
                        }
                    }
                } else if (payday[index].substring(0, 1).equals("s")) {
                    if (payday[index].substring(5).equals(Integer.toString(dayWeek))) {
                        weeksWorked[index] += 1;
                        if (paidUnionFee[index]) {
                            if (weeksWorked[index] == Integer.parseInt(payday[index].substring(2, 4))) {
                                salary[index] = salary[index] + commission[index] - serviceFee[index];
                                totalSalary[index] += salary[index];
                                System.out.println(name[index] + " - Salary = R$" + salary[index] + " via " + wayPayment[index]);
                                salary[index] = 0;
                                serviceFee[index] = 0;
                                commission[index] = 0;
                                weeksWorked[index] = 0;
                            }
                        }
                        if (weeksWorked[index] == Integer.parseInt(payday[index].substring(2, 4))) {
                            salary[index] = salary[index] + commission[index] - serviceFee[index] - unionFee[index];
                            totalSalary[index] += salary[index];
                            paidUnionFee[index] = true;
                            System.out.println(name[index] + " - Salary = R$" + salary[index] + " via " + wayPayment[index]);
                            salary[index] = 0;
                            serviceFee[index] = 0;
                            commission[index] = 0;
                            weeksWorked[index] = 0;
                        }
                    }


                }
            }
        }

        System.out.println("After paid all required employees, a new date is being set!");
        setNewDate();
        System.out.print("Today is: ");
        getDate();
        System.out.println("----------------------------------BACK TO MAIN SCREEN---------------------------------!");

    }///////////////////////////////////////////////////////DONE

    private static void setTimeCheck ( int index){
            int hours = 0;
            double totalMoneyAccounted = 0;
            System.out.println("Are you checking in or checking out?\n" +
                    "1 - in\n" +
                    "2 - out");
            int option = read.nextInt();
            read.nextLine();
            System.out.println("Insert time in format:\n" +
                    "00:00 up to 23:59");
            String time = read.nextLine();

            if (option == 1) {
                hourIn[index] = Integer.parseInt(time.substring(0, 2));
                minuteIn[index] = Integer.parseInt(time.substring(3, 5));
                System.out.println("Check-in done!");
            }
            else if (option == 2) {
                hourOut[index] = Integer.parseInt(time.substring(0, 2));
                minuteOut[index] = Integer.parseInt(time.substring(3, 5));
                daysWorked[index] += 1;
                System.out.println("Check-out done!");

                if (hourOut[index] < hourIn[index])
                    hours = (24 - hourIn[index]) + hourOut[index];
                else
                    hours = hourOut[index] - hourIn[index];
                if (minuteOut[index] < minuteIn[index])
                    hours -= 1;

                if (typePayment[index].equals("h")) {
                    if (hours > 8)
                        totalMoneyAccounted = (hours - 8) * 1.5 * hourSalary + 8 * hourSalary;
                    else if (hours <= 8 && hours > 0)
                        totalMoneyAccounted = hours * hourSalary;
                } /// DONE
                else if (typePayment[index].equals("s")) {
                    totalMoneyAccounted = (baseSalary[index] / 20) * daysWorked[index]; // equivalente aos dias trabalhadas
                }/// DONE
                else if (typePayment[index].equals("c")) {
                    totalMoneyAccounted = (baseSalary[index] / 10) * daysWorked[index];
                }/// DONE

                salary[index] = totalMoneyAccounted;

                System.out.println("Total of R$" + totalMoneyAccounted + " to " + name[index] + " - " + hours + "worked at date:");
                getDate();
                hourIn[index] = 0;
                hourOut[index] = 0;
                minuteIn[index] = 0;
                minuteOut[index] = 0;

            }



        }/////////////////////////////////////////DONE

    private static void operationUndoRedo () {

        }

    private static void serviceFee ( int idEmployee){
            int index = getIndex(idEmployee);
            double fee;
            if (id[index] != -1) {
                if (unionMember[index]) {
                    System.out.print("Insert new service fee from union:\nNumber format: 9999,99\nR$ ");
                    fee = read.nextDouble();
                    serviceFee[index] += fee;
                } else System.out.println("The employee " + name[index] + "is not part of any union.");
            }
            System.out.println("Back to main screen.--------------------------------------------------------\n");
        } /////////////////////////////////////DONE

    private static void screenChangeRegister () {
            System.out.println("Select the required change:\n" +
                    "1 - Name\n" +
                    "2 - Address\n" +
                    "3 - Type of payment\n" +
                    "4 - Method of payment\n" +
                    "5 - Part of union\n" +
                    "6 - Union fee\n" +
                    "0 - Back to main screen");
        }//////////////////////////////////////////DONE

    private static void visualizeOptions () {
            getDate();
            System.out.println("\n------------------------------------------------------------\n" +
                               "Insert 1 to ADD a new employee;");
            System.out.println("Insert 2 to REMOVE an employee;");
            System.out.println("Insert 3 to UPDATE POINT CARD of an employee;");
            System.out.println("Insert 4 to UPDATE THE RESULT OF SALES of an employee;");
            System.out.println("Insert 5 to UPDATE SERVICE FEE of an employee;");
            System.out.println("Insert 6 to CHANGE REGISTER of an employee;");
            System.out.println("Insert 7 to RUN TODAY'S PAYROLL;");
            System.out.println("Insert 8 to UNDO OR REDO;");
            System.out.println("Insert 9 to SET PAYMENT SCHEDULE of an employee;");
            System.out.println("Insert 10 to CREATE NEW PAYMENT SCHEDULES;");
            System.out.println("Insert 11 to LIST EMPLOYEE(S) INFO;");
            System.out.println("Insert 12 to change password of system;");
            System.out.println("Insert -1 to end;");
            System.out.print("Option:  ");

        } /////////////////////////////////////////////DONE

    private static int setIndexArray () {
            int indexArray = -1;
            for (int i = 0; i < maxSize; i++) {
                if (name[i] == null) {
                    indexArray = i;
                    break;
                }
            }
            return indexArray;
        } ////////////////////////////////////////////////DONE

    private static void setAllIds () {
            for (int i = 0; i < maxSize; i++) {
                id[i] = -1;
            }
        } // set all ID to -1  //////////////////////////////DONE

    private static int getId ( int index){
            return index + patternId;
        }////////////////////////DONE

    private static int getIndex ( int id){
            return id - patternId;
        }///////////////////////////DONE

    private static void addEmployee ( int index){
            read.nextLine();
            String partOfUnion;

            System.out.println("/////////////////////////////////////////////////////////////////");
            System.out.print("Insert your name (text entry): ");
            name[index] = read.nextLine();
            System.out.print("Insert your address (text entry): ");
            address[index] = read.nextLine();

            System.out.print("Insert the type of payment:\n" +
                    "Entry format:\nh - hourly / s - salaried / c - commissioned\nType of payment: ");
            typePayment[index] = read.nextLine();
            if (typePayment[index].equals("h")) payday[index] = "s 01 4"; // semanal 1 semana sexta
            else if (typePayment[index].equals("s")) payday[index] = "m 00"; // mensal ultimo util
            else if (typePayment[index].equals("c")) payday[index] = "s 02 4"; // semanal 2 semanas sexta

            if (typePayment[index].equals("s") || typePayment[index].equals("c")) {
                System.out.println("Insert base salary:\nNumber format: 9999,99\nR$ ");
                baseSalary[index] = read.nextDouble();
                read.nextLine();////////////////////////////*****
            }
            System.out.println("Insert how you want to get paid:\n" +
                    "Entry format:\n'mail' - check via mail  /  'check in hands' - check via hands  /  'deposit' - pay via deposit");
            wayPayment[index] = read.nextLine();
            System.out.println("Are you part of any union?\n" +
                    "Entry format:\n 'yes' or 'no'\nAnswer:");
            partOfUnion = read.nextLine();
            unionMember[index] = partOfUnion.equals("yes");
            if (unionMember[index]) {
                System.out.print("Then, insert the union fee:\nNumber format: 9999,99\n R$ ");
                unionFee[index] = read.nextDouble();
            }
            id[index] = getId(index);
            System.out.println("Welcome " + name[index] + "!\nID: " + id[index]);

            System.out.println("/////////////////////////////////////////////////////////////////");


        } /////////////////////////////////////////DONE

    private static void changeRegister ( int index){
            System.out.println("\n------------------------------------------------------------\n");
            boolean nonStop = true;
            while (nonStop) {
                screenChangeRegister();
                int option = read.nextInt();
                read.nextLine();
                if (option == 0) nonStop = false;

                else if (option == 1) {
                    System.out.println("\nInsert new name (text entry):");
                    name[index] = read.nextLine();
                    System.out.println("New name: " + name[index] + "\nDone!");
                } else if (option == 2) {
                    System.out.println("\nInsert new address (text entry):");
                    address[index] = read.nextLine();
                    System.out.println("New address: " + address[index] + "\nDone!");
                } else if (option == 3) {
                    System.out.println("\nInsert new type of payment:\n" +
                            "h - hourly / s - salaried / c - commissioned");
                    typePayment[index] = read.nextLine();
                    if (typePayment[index].equals("h")) payday[index] = "s 01 4"; // semanal 1 em 1, sexta
                    else if (typePayment[index].equals("s")) {
                        // mensal, ultimo util
                        payday[index] = "m 00";
                        System.out.print("Insert base salary:\nNumber format: 9999,99\nBase salary:");
                        baseSalary[index] = read.nextDouble();
                        read.nextLine(); /// precaucao
                    } else if (typePayment[index].equals("c")) {
                        //semanal 2 em 2, sexta
                        payday[index] = "s 02 4";
                        commission[index] = 0;
                        System.out.print("Insert base salary:\nNumber format: 9999,99\nBase salary:");
                        baseSalary[index] = read.nextDouble();
                        read.nextLine(); /// precaucao
                    }
                    System.out.println("Done!");
                } else if (option == 4) {
                    System.out.println("\nInsert new method of payment:\n" +
                            "Entry format:\n'mail' - check via mail  /  'hands' - check via hands  /  'deposit' - pay via deposit");
                    wayPayment[index] = read.nextLine();
                    System.out.println("Done!");
                } else if (option == 5) {
                    String unionPart;
                    System.out.println("\nAre you part of any union?\n" +
                            "Entry format: 'yes'  or  'no'");
                    unionPart = read.nextLine();
                    unionMember[index] = unionPart.equals("yes");
                    if (!unionMember[index]) {
                        unionFee[index] = 0;
                    }
                    System.out.println("Done!");
                } else if (option == 6) {
                    if (unionMember[index]) {
                        System.out.print("\nInsert new union fee:\nNumber format: 9999,99\nUnion fee: ");
                        unionFee[index] = read.nextDouble();
                        System.out.println("New union fee: " + unionFee[index] + "\nDone!");
                    } else System.out.println("\nOption only available for union members!");
                }
            }

            System.out.println("\nAll changes made successfully! Going back to main screen!");
            System.out.println("\n------------------------------------------------------------\n");


        } /////////////////////////////////////DONE

    private static void resultSales ( int idEmployee){
            int index = getIndex(idEmployee);
            double value;
            if (typePayment[index].equals("c")) {
                if (id[index] != -1) {
                    System.out.print("Insert value of sale:\nNumber format: 9999,99\nValue: R$");
                    value = read.nextDouble();
                    commission[index] += value * commissionFee;
                    System.out.println("Sale by " + name[index] + " on ");
                    getDate();
                    System.out.println(" of R$" + value + " added successfully ");
                } else System.out.println("------------Employee not found!---------------");
            } else System.out.println("-----------Employee not found OR not a commissioned employee!----------");


        } ////////////////////////////////////DONE

    private static void deleteEmployee ( int idEmployee){
            int index = getIndex(idEmployee);

            if (id[index] != -1) {
                String savedName = name[index];
                name[index] = null;
                address[index] = null;
                typePayment[index] = null;
                wayPayment[index] = null;
                salary[index] = 0;
                commission[index] = 0;
                baseSalary[index] = 0;
                totalSalary[index] = 0;
                unionFee[index] = 0;
                unionMember[index] = false;
                serviceFee[index] = 0;
                payday[index] = null;
                hourIn[index] = -1;
                minuteIn[index] = -1;
                hourOut[index] = -1;
                minuteOut[index] = -1;
                weeksWorked[index] = 0;
                daysWorked[index] = 0;
                id[index] = -1;

                System.out.println(idEmployee + " - " + savedName + " was deleted from the system successfully!\n");
                System.out.println("\n------------------------------------------------------------\n");
            } else {
                System.out.println("The selected ID is not associated with any employee registered!\n" +
                        "Operation not executed\n");
                System.out.println("\n------------------------------------------------------------\n");
            }
        } ////////////////////////////////DONE

    private static void definePayday ( int idEmployee){
            int index = getIndex(idEmployee);
            int optionSchedule;
            String opt;

            if (id[index] != -1) {
                System.out.println("Options:");
                for (int i = 0; i < counterSchedules; i++) {
                    if (typePayment[index].equals("h") && schedules[i].substring(0, 4).equals("s 01")) // if horista, e de 1 em 1 semana
                        System.out.println(i + " - " + schedules[i]);
                    else if (typePayment[index].equals("c") && schedules[i].substring(0, 4).equals("s 02"))
                        System.out.println(i + " - " + schedules[i]);
                    else if (typePayment[index].equals("s") && schedules[i].substring(0, 1).equals("m")) // if salaried e mensal
                        System.out.println(i + " - " + schedules[i]);
                }

                System.out.println("If you want to change your payment schedule, select one valid shown number\nElse, insert 'over' to go back to main screen");
                opt = read.nextLine();
                if (opt.equals("over"))
                    System.out.println("Back to main screen.\n-----------------------------------------------------------------------");
                else {
                    optionSchedule = Integer.parseInt(opt);
                    payday[index] = schedules[optionSchedule];
                    System.out.println("Operation registered!");
                }
            }
            System.out.println("Back to main screen.\n-----------------------------------------------------------------------");

        } ///////////////////////////////////DONE

    private static void createSchedules () {
            //create new payday settings
            String schedule = "";
            while (true) {
                String entry;
                System.out.println("Insert 'over' to go back to main screen");
                System.out.println("....Adding new payment schedules....\n");
                System.out.println("Insert type: m - monthly  /  s - weekly");
                entry = read.nextLine();
                if (entry.equalsIgnoreCase("over")) break;
                schedule += entry;

                if (schedule.equals("m")) {
                    System.out.println("Insert a number for the day of payment:\nNumber format: '01' - '25' or '00' to last business day");

                    entry = read.nextLine();
                    if (entry.equalsIgnoreCase("over")) break;
                    schedule += " " + entry;
                } else if (schedule.equals("s")) {
                    System.out.println("Insert number of worked weeks required: 01 or 02");

                    entry = read.nextLine();
                    if (entry.equalsIgnoreCase("over")) break;
                    schedule += " " + entry;

                    System.out.println("Insert the day of week:\n" +
                            "0 - Monday\n1 - Tuesday\n2 - Wednesday\n3 - Thursday\n4 - Friday\n5 - Saturday\n6 - Sunday");

                    entry = read.nextLine();
                    if (entry.equalsIgnoreCase("over")) break;
                    schedule += " " + entry;
                }
                schedules[counterSchedules] = schedule;
                counterSchedules += 1;
            }

        } //////////////////////////////////////////////DONE

}



