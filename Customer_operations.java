import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Customer_operations {
    Scanner sc=new Scanner(System.in);
    public void delete_customer(ArrayList<Integer>customer_ids,ArrayList<Bill>bills,ArrayList<Customer>customers){
        System.out.println("Customer IDs");
        for(Integer i:customer_ids){
            System.out.println(i);
        }
        System.out.println("Enter Customer id");
        int c_id=sc.nextInt();
        Iterator<Bill> billIterator=bills.iterator();
        Bill bill_to_remove=null;
        while (billIterator.hasNext()){
            Bill bill=billIterator.next();
            if(bill.c_id.customer_id==c_id){
                bill_to_remove=bill;
                billIterator.remove();
            }
        }
        if(customer_ids.contains(c_id)) {
            customer_ids.remove(Integer.valueOf(c_id));
            if (bill_to_remove != null) {
                customers.remove(bill_to_remove.c_id);
            }
        }
        else {
            System.out.println("Customer id does not Exist");
        }
    }

    public void edit_customer(ArrayList<Integer>customer_ids,ArrayList<Customer>customers){
        System.out.println("Edit Customer Details");
        System.out.println("NOTE: the changes are applicable to bills which are generated after the edit");
        int flag41=0;
        while(flag41==0) {
            System.out.println("\t1.Customer Name");
            System.out.println("\t2.Customer Phone Number");
            System.out.println("\t3.Back to main menu");
            int choice41 = sc.nextInt();
            switch (choice41) {
                case 1 -> {
                    System.out.println("Customer IDs");
                    for (Integer i : customer_ids) {
                        System.out.println(i);
                    }
                    System.out.println("Enter Customer ID");
                    int id = sc.nextInt();
                    System.out.println("Enter Updated Name");
                    sc.nextLine();
                    String n = sc.nextLine();
                    if (customer_ids.contains(id)) {
                        for (Customer i : customers) {
                            if (i.customer_id == id) {
                                i.customer_name = n;
                            }
                        }
                    } else {
                        System.out.println("Customer does not Exist");
                    }
                }
                case 2 -> {
                    System.out.println("Customer IDs");
                    for (Integer i : customer_ids) {
                        System.out.println(i);
                    }
                    System.out.println("Enter Customer number");
                    int id = sc.nextInt();
                    System.out.println("Enter Updated Phone Number");
                    long p = sc.nextLong();
                    if (customer_ids.contains(id)) {
                        for (Customer i : customers) {
                            if (i.customer_id == id) {
                                i.customer_phone = p;
                            }
                        }
                    } else {
                        System.out.println("Customer does not Exist");
                    }
                }
                case 3 -> flag41 = 1;
                default -> System.out.println("Invalid Choice");
            }

        }
    }

    public void view_customer(ArrayList<Integer>customer_ids,ArrayList<Customer>customers){
        System.out.println("Customer IDs");
        for (Integer i:customer_ids){
            System.out.println(i);
        }
        System.out.println("Enter the Customer ID to be viewed or 0 to view all Customers");
        int id=sc.nextInt();
        if(id==0){
            for (Customer i:customers){
                i.print_details();
            }
        }
        else{
            int flag=0;
            for (Customer i:customers){
                if(i.customer_id==id){
                    flag=1;
                    i.print_details();
                }
            }
            if(flag==0){
                System.out.println("Customer ID does not Exist");
            }
        }
    }
}