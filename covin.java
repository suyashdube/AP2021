package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class covin {
    Scanner sc = new Scanner(System.in);

    static ArrayList<hospital> hospitals = new ArrayList<hospital>();
    ArrayList<vaccines> vaccine = new ArrayList<vaccines>();
    ArrayList<slot> slots = new ArrayList<slot>();
    ArrayList<citizens> citizen = new ArrayList<citizens>();
     public void registerHospital(){

        System.out.println("Hospital name: ");
        String name = sc.nextLine();
        System.out.println("Pincode: ");
        int pin = sc.nextInt();
        hospital hop = new hospital(name, pin);
        hospitals.add(hop);

    }
     public void registerCitizen(){
        String name; int age, uniqueid;
        System.out.println("Citizen name: ");
        name = sc.nextLine();
        System.out.println("Age:");
        age = sc.nextInt();
        System.out.println("Unique ID: ");
        uniqueid = sc.nextInt();
        citizens cit = new citizens(name, age, uniqueid);
        citizen.add(cit);
    }
     private void addVaccine(){
        String name; int doses, gap;
        System.out.println("Vaccine name: ");
        name = sc.nextLine();
        System.out.println("Number of doses");
        doses = sc.nextInt();
        System.out.println("Gap between doses: ");
        gap = sc.nextInt();
        vaccines vac = new vaccines(name, doses, gap);

        vaccine.add(vac);

    }
     public void addSlot(){
        System.out.println("Enter hospital id: ");
        int hospitalID = sc.nextInt();
        slot sl = new slot(hospitalID);
        slots.add(sl);
    }
    public void bookSlot(){
        int uniqueID; int option, pincode, hospitalid;
        System.out.println("Enter patient unique ID: ");
        uniqueID = sc.nextInt();
        System.out.println("1. Search by area");
        System.out.println("2. Search by vaccine");
        System.out.println("3. Exit\nEnter option: ");
        option = sc.nextInt();
        while(true) {
            if (option == 1) {
                System.out.println("Enter pincode: ");
                pincode = sc.nextInt();
                for (int i = 0; i < hospitals.size(); i++) {
                    if (hospitals.get(i).pinCode == pincode) {
                        System.out.println(hospitals.get(i).uniqueID + hospitals.get(i).name);
                    }
                }
                System.out.println("Enter hospital ID: ");
                hospitalid = sc.nextInt();
                int a = 0;
                for (int i = 0; i < slots.size(); i++) {
                    if (slots.get(i).hospitalID == hospitalid) {
                        for (int j = 1; j <= slots.get(i).numOfSlots; j++) {
                            System.out.println(a + "->Day:" + j + "Available qty: " + slots.get(i).quantity + " Vaccine: " + slots.get(i).vaccinename);
                            a++;
                        }

                    }
                }
                System.out.println("Choose slot: ");
                int s = sc.nextInt();
//            String n;
                if (s == 0) {
                    String n;
                    for (int i = 0; i < citizen.size(); i++) {
                        if (citizen.get(i).uniqueID == uniqueID) {
                            n = citizen.get(i).name;
                            System.out.println(n + " vaccinated with covax");
                            citizen.get(i).vaccinename = "covax";
                            citizen.get(i).dosecount++;
                            if (citizen.get(i).dosecount == 1)
                                citizen.get(i).vaccineStatus = "PARTIALLY VACCINATED";
                            else if (citizen.get(i).dosecount == 2)
                                citizen.get(i).vaccineStatus = "FULLY VACCINATED";
                            for (int j = 0; j < slots.size(); j++) {
                                if (slots.get(j).hospitalID == hospitalid) {
                                    slots.get(j).covaxquantity--;
                                    slots.get(i).quantity--;
                                }
                            }
                        } else
                            System.out.println("the citizen is not registered");
                    }


                } else if (s == 1) {
                    String n;
                    for (int i = 0; i < citizen.size(); i++) {
                        if (citizen.get(i).uniqueID == uniqueID) {
                            n = citizen.get(i).name;
                            System.out.println(n + " vaccinated with covi");
                            citizen.get(i).vaccinename = "covi";
                            if (citizen.get(i).dosecount == 1)
                                citizen.get(i).vaccineStatus = "PARTIALLY VACCINATED";
                            else if (citizen.get(i).dosecount == 2)
                                citizen.get(i).vaccineStatus = "FULLY VACCINATED";
                            for (int j = 0; j < slots.size(); j++) {
                                if (slots.get(j).hospitalID == hospitalid) {
                                    slots.get(j).coviquantity--;
                                    slots.get(i).quantity--;

                                }
                            }
                        } else
                            System.out.println("the citizen is not registered");
                    }
                } else {
                    System.out.println("Invalid choice");
                }


            }
            if (option == 2) {
                System.out.println("Enter vaccine name: ");
                // String n;
                String name = sc.nextLine();
                for (int i = 0; i < slots.size(); i++) {
                    //String n;
                    if (slots.get(i).vaccinename == name) {
                        String n;
                        for (int j = 0; j < hospitals.size(); j++) {
                            if (hospitals.get(j).uniqueID == slots.get(i).hospitalID) {
                                n = hospitals.get(j).name;
                                System.out.println(Integer.toString(slots.get(i).hospitalID) + " " + n);
                                break;
                            }

                        }

                    }
                }
                System.out.println("Enter hospital id: ");
                int id = sc.nextInt();
                int slotNum = 0;
                for (int i = 0; i < slots.size(); i++) {
                    if (slots.get(i).hospitalID == id) {
                        for (int j = 1; j <= slots.get(i).numOfSlots; j++) {

                            System.out.println(slotNum + " ->Day:" + j + "Available qty: " + slots.get(i).quantity + " Vaccine: " + slots.get(i).vaccinename);
                            slotNum++;
                        }

                    }
                }
                System.out.println("Choose slot: ");
                int slot = sc.nextInt();
                for (int i = 0; i < citizen.size(); i++) {
                    if (citizen.get(i).uniqueID == uniqueID) {
                        System.out.println(citizen.get(i).name + " vaccinated with " + slots.get(slot).vaccinename);
                        if (slots.get(slot).vaccinename == "Covax"){
                            slots.get(slot).covaxquantity--;
                            citizen.get(i).dosecount++;
                            citizen.get(i).vaccinename = "Covax";
                        }
                        else if (slots.get(slot).vaccinename == "covi") {
                            slots.get(slot).coviquantity--;
                            citizen.get(i).dosecount++;
                            citizen.get(i).vaccinename = "covi";
                        }
                        if(citizen.get(i).dosecount == 1){
                            citizen.get(i).vaccineStatus = "PARTIALLY VACCINATED";

                        }
                        else if(citizen.get(i).dosecount == 2){
                            citizen.get(i).vaccineStatus = "FULLY VACCINATED";
                        }

                        slots.get(i).quantity--;
                        break;
                    }
                }


            }
            if (option == 3)
                break;
        }
        System.out.println("--------------------------------------------------------------------");


    }

    public void listAllSlots(){
        System.out.println("Enter hospital ID: ");
        int id = sc.nextInt();
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).hospitalID == id) {
                for (int j = 1; j <= slots.get(i).numOfSlots; j++) {

                    System.out.println("Day:" + j + " Vaccine: " + slots.get(i).vaccinename + "Available qty: " + slots.get(i).quantity );

                }

            }
        }

    }

    public void checkVaccineStatus(){
        System.out.println("Enter patient id: ");
        int id = sc.nextInt();
        for(int i = 0; i < citizen.size(); i++)
        {
            if(citizen.get(i).uniqueID == id){
                System.out.println(citizen.get(i).vaccineStatus);
                System.out.println("Vaccine given: " +citizen.get(i).vaccinename);
                System.out.println("number of doses given: " + citizen.get(i).dosecount);
                System.out.println("next dose due date: " + 1+vaccine.get(i).gap);
            }
        }
        System.out.println("-------------------------------------------------------------------");
    }

    public void main(String[] args) {
        while(true){
        System.out.println("CoWin Portal initialized...");
        System.out.println("---------------------------------------");
        System.out.println("1. Add vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add slot for Vaccination");
        System.out.println("5. Book slot for Vaccination");
        System.out.println("6. List all slots for a hospital");
        System.out.println("7. Check vaccination status");
        System.out.println("8. Exit");
        System.out.println("---------------------------------------");

        int option = sc.nextInt();
        if(option != 8) {
            switch (option) {
                case 1:
                    addVaccine();
                    break;
                case 2:
                    registerHospital();
                    break;
                case 3:
                    registerCitizen();
                    break;
                case 4:
                    addSlot();
                    break;
                case 5:
                    bookSlot();
                    break;
                case 6:
                    listAllSlots();
                    break;
                case 7:
                    checkVaccineStatus();
                    break;


            }
        }
        if(option == 8)
            break;


        }




    }
}
class hospital{

    String name; int pinCode;  int uniqueID = 100000;

    public hospital(String name, int pinCode){
        this.name = name;
        this.pinCode = pinCode;
        System.out.println("Hospital Name: " + name + ", Pincode: " + pinCode + "unique ID: " + uniqueID);
        uniqueID++;

    }

}
class citizens{
    String name, vaccineStatus, vaccinename; int age, uniqueID, dosecount;
    public citizens(String name, int age, int uniqueID){
        this.name = name;
        this.age = age;
        this.uniqueID = uniqueID;
        vaccineStatus = "REGISTERED";
        dosecount = 0;
        System.out.println("Citizen name: " + name + " Age: " + age + " unique ID: "+ uniqueID);
    }
}
class vaccines{
    String name; int doses;
    int gap;
    public vaccines(String name, int doses, int gap){
        this.name = name; this.doses = doses; this.gap = gap;
        System.out.println("Name of vaccine: " + name + " \ndoses needed: " + doses + "\ngap between doses: " + gap);


    }

    public String getVaccineName(){
        return name;
    }
    public int getVaccineDoses(){
        return doses;
    }
    public int getVaccineGap(){
        return gap;
    }

}

class slot{

    int hospitalID, quantity, covaxquantity = 0, coviquantity = 0;
    int numOfSlots; String vaccinename;
    Scanner sc = new Scanner(System.in);
    public slot(int hospitalID){
        this.hospitalID = hospitalID;

        System.out.println("Enter the number of slots to be added: ");
        numOfSlots = sc.nextInt();
        while(numOfSlots != 0){
            System.out.println("Enter day number: ");
            int day = sc.nextInt();
            System.out.println("Enter quantity: ");
            quantity = sc.nextInt();
            System.out.println("Select vaccine");
            System.out.println("0.covax");
            System.out.println("1. Covi");
            int vaccine  = sc.nextInt();

            if(vaccine == 0) {
                vaccinename = "Covax";
                System.out.println("Slot added by Hospital " + hospitalID + "for Day: " + day + ", Available quanity" + quantity + " of vaccine " + vaccinename);
                covaxquantity += quantity;
            }
            else if(vaccine == 1) {
                vaccinename = "Covi";
                System.out.println("Slot added by Hospital " + hospitalID + "for Day: " + day + ", Available quanity" + quantity + " of vaccine " + vaccinename);
                coviquantity += quantity;

            }
            else
                System.out.println("Invalid vaccine number given!");


        }
        System.out.println("___________________________________________________________________________");


    }

}
