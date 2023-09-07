import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BillManagement_operations {
    Scanner sc=new Scanner(System.in);
    public int create_bill(int bill_no,int customer_id,Customer customer,ArrayList<Integer> customer_ids,ArrayList<Customer> customers,ArrayList<Item> items,ArrayList<Bill> bills){
        System.out.println("Customer IDs");
        for(Integer i:customer_ids){
            System.out.println(i);
        }
        System.out.println("\t1.New Customer");
        System.out.println("\t2.Existing Customer");
        int choice4=sc.nextInt();
        if(choice4==1){
            int c_id=++customer_id;
            customer_ids.add(c_id);
            System.out.println("Enter the customer Name");
            sc.nextLine();
            String customer_name=sc.nextLine();
            System.out.println("Enter customer phone number");
            long customer_phone=sc.nextLong(10);
            customer=new Customer(c_id,customer_name,customer_phone);
            customers.add(customer);
        }
        else if (choice4==2) {
            System.out.println("\tEnter the customer id");
            int c_id=sc.nextInt();
            if(!customer_ids.contains(c_id)){
                System.out.println("\tThe above customer id does not exist!");
                return 0;
            }
            for(Customer i:customers){
                if(i.customer_id==c_id){
                    customer=i;
                    break;
                }
            }
        }
        else{
            System.out.println("Invalid Choice");
            return 0;
        }
        ArrayList<Item> bill_items=new ArrayList<>();
        HashMap<Integer,Integer> amt=new HashMap<>();
        System.out.println("Enter the date of purchase (YYYY MM DD)");
        int year=sc.nextInt();
        int month=sc.nextInt();
        int d=sc.nextInt();
        LocalDate date=LocalDate.of(year,month,d);
        System.out.println("Enter the Item numbers of the items and the purchased Qty (Item number=0 for ending the list)");
        while(true){
            int item_no=sc.nextInt();
            if(item_no==0){
                break;
            }
            else{
                int flag4=0;
                int qty=sc.nextInt();
                for(Item i: items){
                    if(i.Item_no==item_no){
                        bill_items.add(i);
                        amt.put(item_no,qty);
                        flag4=1;
                    }
                }
                if(flag4==0){
                    System.out.println("Item is not available");
                }
            }
        }
        Bill b=new Bill(++bill_no,date,bill_items,customer,amt);
        b.setSales();
        bills.add(b);
        return 1;
    }

    public void delete_bill(ArrayList<Bill> bills,ArrayList<Integer>customer_ids){
        System.out.println("Delete Bill");
        System.out.println("\t1.Show Existing Bill numbers");
        System.out.println("\t2.Show Customer ids to filter the bill numbers");
        int choice5=sc.nextInt();
        if(choice5==1){
            System.out.println("Bill Numbers");
            for(Bill i:bills){
                System.out.println(i.bill_no);
            }
            System.out.println("Enter Bill number");
            int b_no=sc.nextInt();
            Bill bill_to_remove = null;
            for(Bill i:bills){
                if(i.bill_no==b_no){
                    bill_to_remove=i;
                    break;
                }
            }
            if(bill_to_remove!=null){
                bills.remove(bill_to_remove);
            }
            else {
                System.out.println("Bill number does not exist");
            }
        }
        else if(choice5==2){
            System.out.println("Customer IDs");
            for(Integer i:customer_ids){
                System.out.println(i);
            }
            System.out.println("Enter the customer id");
            int c_id=sc.nextInt();
            if(customer_ids.contains(c_id)){
                System.out.println("Filtered Bill Numbers");
                for(Bill i:bills){
                    if(i.c_id.customer_id==c_id){
                        System.out.println(i.bill_no);
                    }
                }
                System.out.println("Enter the bill number");
                int b_no=sc.nextInt();
                Bill bill_to_remove=null;
                for(Bill i:bills){
                    if(i.bill_no==b_no && i.c_id.customer_id==c_id){
                        bill_to_remove=i;
                        break;
                    }
                }
                if(bill_to_remove!=null){
                    bills.remove(bill_to_remove);
                }
                else{
                    System.out.println("Bill number does not Exist for the selected Customer");
                }
            }
            else {
                System.out.println("Customer id does not Exist");
            }
        }
        else{
            System.out.println("Invalid Choice");
        }
    }

    public  void edit_bill(ArrayList<Bill> bills){
        System.out.println("Bill numbers");
        for(Bill i:bills){
            System.out.println(i.bill_no);
        }
        System.out.println("Enter the Bill number to be edited");
        int b_no=sc.nextInt();
        int flag3=0;
        for(Bill i:bills){
            if(i.bill_no==b_no){
                flag3=1;
                int flag7=0;
                while(flag7==0) {
                    System.out.println("\t1.Edit Sales date");
                    System.out.println("\t2.Edit Qty of Items purchased");
                    System.out.println("\t3.Back to main menu");
                    int choice7 = sc.nextInt();
                    switch (choice7) {
                        case 1 -> {
                            System.out.println("Enter the new date of purchase (DD MM YYYY)");
                            int date7 = sc.nextInt();
                            int month7 = sc.nextInt();
                            int year7 = sc.nextInt();
                            i.date = LocalDate.of(year7, month7, date7);
                        }
                        case 2 -> {
                            System.out.println("Enter the Item number");
                            int id = sc.nextInt();
                            if (i.amt.containsKey(id)) {
                                System.out.println("Enter the Updated Qty");
                                int qty = sc.nextInt();
                                i.amt.put(id, qty);
                                i.setSales();
                            } else {
                                System.out.println("Item is not purchased");
                            }
                        }
                        case 3 -> flag7 = 1;
                        default -> System.out.println("Invalid Choice");
                    }

                }
            }
        }
        if (flag3==0){
            System.out.println("Bill Number does not Exist");
        }
    }

    public void view_bill(ArrayList<Bill> bills,ArrayList<Integer> customer_ids){
        System.out.println("\t1.List the Bill numbers");
        System.out.println("\t2.Show Customer ids to filter the bill numbers");
        int choice7=sc.nextInt();
        if(choice7==1){
            System.out.println("Bill Numbers");
            for(Bill i:bills){
                System.out.println(i.bill_no);
            }
            System.out.println("Enter Bill number");
            int b_no=sc.nextInt();
            int flag4=0;
            for(Bill i: bills){
                flag4=1;
                if(i.bill_no==b_no){
                    i.print();
                }
            }
            if(flag4==0){
                System.out.println("Bill Number does not Exist");
            }
        }
        else if(choice7==2) {
            System.out.println("Customer IDs");
            for (Integer i : customer_ids) {
                System.out.println(i);
            }
            System.out.println("Enter the customer id");
            int c_id = sc.nextInt();
            if (customer_ids.contains(c_id)) {
                System.out.println("Filtered Bill Numbers");
                for (Bill i : bills) {
                    if (i.c_id.customer_id == c_id) {
                        System.out.println(i.bill_no);
                    }
                }
                System.out.println("Enter the bill number");
                int b_no = sc.nextInt();
                int flag4=0;
                for (Bill i : bills) {
                    if (i.bill_no == b_no) {
                        flag4=1;
                        i.print();
                    }
                }
                if(flag4==0){
                    System.out.println("Bill Number does not Exist");
                }
            }
            else {
                System.out.println("Customer id does not Exist");
            }
        }
        else{
            System.out.println("Invalid Choice");
        }
    }
}