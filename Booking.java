import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Booking {
    static ArrayList<Patient> allPatients = new ArrayList<Patient>();
    static Scanner s = new Scanner(System.in);
    static HashMap<Integer, Boolean> slots = new HashMap<Integer, Boolean>();

    public static void main(String[] args) {
        addSlots();

        while (true) {
            System.out.println("\n");
            System.out.println("1.view slots : ");
            System.out.println("2.remove patient : ");
            System.out.println("3.views patient : ");
            System.out.println("4.Exit : ");
            System.out.print("\n\nEnter your choice : ");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    viewAllSlots();
                    System.out.println("Enter Slot : ");
                    int slot = s.nextInt();
                    if(slot>12){
                        System.out.print("You enter wrong slot , please try again");
                        break;
                    }
                    boolean status = checkSlot(slot);
                    if (status)
                        if(addPatient(slot))
                            System.out.println("Patient added....");
                        else
                            System.out.println("Patient is not added...");
                    else
                        try {
                            throw new SlotBookException("Slot Already Booked.\n");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    break;
                case 2:
                    if(removePatient())
                        System.out.println("Patient Remove Successfully...\n");
                    else
                        System.out.println("Patient is not removed please try again...");
                    break;
                case 3:
                    viewPatients();
                    break;
                case 4:
                        System.out.println("Thank you...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("You enter wrong choice : ");
                    break;
            }
        }
    }

    public static boolean removePatient() {
        viewPatientsForRemove();
        System.out.print("Enter Slot : ");
        int slot = s.nextInt();
        boolean status = false;

        for (int i = 0; i < allPatients.size(); i++) {
            if (allPatients.get(i).getSlot() == slot) {
                status = true;
                allPatients.remove(allPatients.get(i));
                break;
            }
        }

        if (status) {
            for (Map.Entry<Integer, Boolean> e : slots.entrySet()) {
                if (e.getKey() == slot) {
                    e.setValue(false);
                    return true;
                }
            }
        } else {
            System.out.print("This slot is not available please try again: ");
        }
        return false;
    }

    public static void viewPatients() {
        System.out.println("All Patients : \n");
        for (int i = 0; i < allPatients.size(); i++) {
            Patient p = allPatients.get(i);
            System.out.print("Name : " + p.getName());
            System.out.print("\nAge : " + p.getAge());
            System.out.print("\nEmail : " + p.getEmail());
            System.out.print("\nDieses : " + p.getDieses());
            System.out.print("\nSlot : " + p.getSlot());
            System.out.println("\n\n");
        }
    }

    public static void viewPatientsForRemove() {
        System.out.println("Available Patients : \n");
        for (int i = 0; i < allPatients.size(); i++) {
            Patient p = allPatients.get(i);
            System.out.print("Name : " + p.getName());
            System.out.print("\nSlot : " + p.getSlot());
            System.out.println("\n");
        }
    }

    public static boolean addPatient(int slot) {
        HashSet<String> hs = new HashSet<String>();
        s.nextLine();
        System.out.print("Enter a name : ");
        String name = s.next();
        System.out.print("Enter a age : ");
        int age = 0;
        try {
            age = s.nextInt();
        } catch (Exception e) {
            System.out.println("Only Number Is Valid : ");
            s.next();
            addPatient(slot);
        }
        System.out.print("Enter a email : ");
        String email = s.next();
        if (!checkEmail(email)) {
            try {
                throw new EmailNotValidException("Email Not Valid : ");
            } catch (Exception e) {
                System.out.println(e);
            }
            addPatient(slot);
        }

        while (true) {
            System.out.print("Enter a diseases : ");
            String d = s.next();
            hs.add(d);
            System.out.print("Enter y for add more dieses or n/N: ");
            String chec = s.next();
            if (chec.equals("y") || chec.equals("Y"))
                continue;
            else{
                s.nextLine();
                break;
            }
        }

        Patient p = new Patient(name, age, hs, email, slot);
        allPatients.add(p);
        for (Map.Entry<Integer, Boolean> e : slots.entrySet()) {
            if (e.getKey() == slot){
                e.setValue(true);
                return true;
            }
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        String[] st = email.split("@");
        int count=0;
        for (int i = 0; i < st.length; i++)
            if (st[i].equals("gmail.com"))
                count++;

        for (int i = 0; i < allPatients.size(); i++) {
            if (allPatients.get(i).getEmail().equals(email))
                return false;
        }

        if(count==1)
            return true;

        return false;
    }

    public static boolean checkSlot(int slot) {
        for (Map.Entry<Integer, Boolean> entry : slots.entrySet()) {
            if (entry.getKey() == slot && entry.getValue() == false)
                return true;
        }
        return false;
    }

    public static void viewAllSlots() {
        System.out.println("Available Slots : ");
        for (Map.Entry<Integer, Boolean> entry : slots.entrySet()) {
            if (entry.getValue() == false)
                System.out.println("Slot : " + entry.getKey());
        }
    }

    public static void addSlots() {
        slots.put(1, false);
        slots.put(2, false);
        slots.put(3, false);
        slots.put(4, false);
        slots.put(5, false);
        slots.put(6, false);
        slots.put(7, false);
        slots.put(8, false);
        slots.put(9, false);
        slots.put(10, false);
        slots.put(11, false);
        slots.put(12, false);
    }
}





