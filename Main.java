import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String []args){
        System.out.println("SuperMarket");
        Scanner sc=new Scanner(System.in);
        ArrayList<Bill> bills= new ArrayList<>();
        ArrayList<Integer> customer_ids= new ArrayList<>();
        ArrayList<Customer> customers=new ArrayList<>();
        ArrayList<Item> items=new ArrayList<>();
        ArrayList<Integer> item_nos=new ArrayList<>();
        int flag=0;
        int bill_no=100000;
        int customer_id=1000;
        int item_id=0;
        Customer customer = null;
        while(flag==0){
            System.out.println("\n1.Sales Details");
            System.out.println("2.Bill Management");
            System.out.println("3.Items Management");
            System.out.println("4.Customer Management");
            System.out.println("0.Exit SuperMarket");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    int flag1=0;
                    while(flag1==0) {
                        System.out.println("\t1.Check total sales done in a given period");
                        System.out.println("\t2.Check total Sales done in by a particular customer in a given period");
                        System.out.println("\t3.Sales done in a particular bill");
                        System.out.println("\t4.Back to Main Menu");
                        int choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1:
                                System.out.println("Enter the start date (DD MM YYYY)");
                                int date1 = sc.nextInt();
                                int month1 = sc.nextInt();
                                int year1 = sc.nextInt();
                                System.out.println("Enter the end date (DD MM YYYY)");
                                int date2 = sc.nextInt();
                                int month2 = sc.nextInt();
                                int year2 = sc.nextInt();
                                LocalDate startdate = LocalDate.of(year1, month1, date1);
                                LocalDate enddate = LocalDate.of(year2, month2, date2);
                                int total_sales = 0;
                                for (Bill i : bills) {
                                    if (i.check(startdate, enddate)) {
                                        total_sales += i.sales;
                                    }
                                }
                                System.out.println("Total Sales: " + total_sales);
                                break;
                            case 2:
                                System.out.println("Customer IDs");
                                for (Integer i : customer_ids) {
                                    System.out.println(i);
                                }
                                System.out.println("Enter the customer id");
                                int c_id = sc.nextInt();
                                if (!customers.contains(c_id)) {
                                    System.out.println("Customer id does not Exist!");
                                    break;
                                }
                                System.out.println("Enter the start date (DD MM YYYY)");
                                date1 = sc.nextInt();
                                month1 = sc.nextInt();
                                year1 = sc.nextInt();
                                System.out.println("Enter the end date (DD MM YYYY)");
                                date2 = sc.nextInt();
                                month2 = sc.nextInt();
                                year2 = sc.nextInt();
                                startdate = LocalDate.of(year1, month1, date1);
                                enddate = LocalDate.of(year2, month2, date2);
                                total_sales = 0;
                                for (Bill i : bills) {
                                    if (i.c_id.customer_id == c_id && i.check(startdate, enddate)) {
                                        total_sales += i.sales;
                                    }
                                }
                                System.out.println("Total Sales: " + total_sales);
                                break;
                            case 3:
                                System.out.println("Bill Numbers");
                                for (Bill i:bills){
                                    System.out.println(i.bill_no);
                                }
                                System.out.println("Enter the bill number");
                                int b_no = sc.nextInt();
                                for (Bill i : bills) {
                                    if (i.bill_no == b_no) {
                                        System.out.println("Sales: " + i.sales);
                                    }
                                }
                                break;
                            case 4:
                                flag1=1;
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }
                    }
                    break;
                case 2:
                    int flag2=0;
                    while(flag2==0) {
                        System.out.println("\t1.Add bill information");
                        System.out.println("\t2.Delete a bill information");
                        System.out.println("\t3.Edit a Bill Information");
                        System.out.println("\t4.View a Bill Information");
                        System.out.println("\t5.Back to Main Menu");
                        int choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1:
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
                                    String y=sc.nextLine();
                                    String customer_name=sc.nextLine();
                                    System.out.println("Enter customer phone number");
                                    long customer_phone=sc.nextLong(10);
                                    customer=new Customer(c_id,customer_name,customer_phone);
                                    customers.add(customer);
                                }
                                else if (choice4==2) {
                                    System.out.println("\tEnter the customer id");
                                    int c_id=sc.nextInt();
                                    if(!customers.contains(c_id)){
                                        System.out.println("\tThe above customer id does not exist!");
                                        break;
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
                                    break;
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
                                bills.add(new Bill(++bill_no,year,month,date,bill_items,customer,amt));
                                break;
                            case 2:
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
                                        if(bill_to_remove.c_id.customer_one(bills)){
                                            customer_ids.remove(Integer.valueOf(bill_to_remove.c_id.customer_id));
                                        }
                                        bills.remove(bill_to_remove);
                                        customers.remove(bill_to_remove.c_id);
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
                                            if (bill_to_remove.c_id.customer_one(bills)){
                                                customer_ids.remove(Integer.valueOf(bill_to_remove.c_id.customer_id));
                                            }
                                            bills.remove(bill_to_remove);
                                            customers.remove(bill_to_remove.c_id);
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
                                break;
                            case 3:
                                System.out.println("Bill numbers");
                                for(Bill i:bills){
                                    System.out.println(i.bill_no);
                                }
                                System.out.println("Enter the Bill number to be edited");
                                int b_no=sc.nextInt();
                                for(Bill i:bills){
                                    if(i.bill_no==b_no){
                                        int flag7=0;
                                        while(flag7==0) {
                                            System.out.println("\t1.Edit Customer id");
                                            System.out.println("\t2.Edit Sales date");
                                            System.out.println("\t3.Edit Qty of Items purchased");
                                            System.out.println("\t4.Back to main menu");
                                            int choice7 = sc.nextInt();
                                            switch (choice7) {
                                                case 1:
                                                    System.out.println("\t\t1.Change to New Customer");
                                                    System.out.println("\t\t2.Change to Existing Customer");
                                                    int choice71=sc.nextInt();
                                                    if(choice71==1){
                                                        if(i.c_id.customer_one(bills)){
                                                            customer_ids.remove(Integer.valueOf(i.c_id.customer_id));
                                                            customers.remove(i.c_id);
                                                        }
                                                        i.c_id.customer_id=++customer_id;
                                                        customer_ids.add(i.c_id.customer_id);
                                                        customers.add(i.c_id);
                                                    }
                                                    else if(choice71==2){
                                                        System.out.println("Customer IDs");
                                                        for(Integer j:customer_ids){
                                                            System.out.println(j);
                                                        }
                                                        System.out.println("Enter the new customer id");
                                                        int c_id=sc.nextInt();
                                                        if(i.c_id.customer_one(bills)){
                                                            customer_ids.remove(Integer.valueOf(i.c_id.customer_id));
                                                            customers.remove(i.c_id);
                                                        }
                                                        i.c_id.customer_id=c_id;
                                                        if(!customer_ids.contains(i.c_id.customer_id)){
                                                            customer_ids.add(i.c_id.customer_id);
                                                            customers.add(i.c_id);
                                                        }
                                                    }
                                                    else{
                                                        System.out.println("Invalid choice");
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("Enter the new date of purchase (DD MM YYYY)");
                                                    int date7=sc.nextInt();
                                                    int month7=sc.nextInt();
                                                    int year7=sc.nextInt();
                                                    LocalDate Date=LocalDate.of(year7,month7,date7);
                                                    i.date=Date;
                                                    break;
                                                case 3:
                                                    System.out.println("Enter the Item number");
                                                    int id=sc.nextInt();
                                                    int flag8=0;
                                                    if(i.amt.containsKey(id)){
                                                        flag8=1;
                                                        int sales=i.sales;
                                                        int price=0;
                                                        for(Item j:i.items){
                                                            if(j.Item_no==id){
                                                                price=j.Item_price;
                                                                sales-=price*i.amt.get(id);
                                                            }
                                                        }
                                                        System.out.println("Enter the Updated Qty");
                                                        int qty=sc.nextInt();
                                                        i.amt.put(id,qty);
                                                        sales+=price*qty;
                                                        i.sales=sales;
                                                    }
                                                    else {
                                                        System.out.println("Item is not purchased");
                                                    }
                                                    break;
                                                case 4:
                                                    flag7=1;
                                                    break;
                                                default:
                                                    System.out.println("Invalid Choice");
                                            }

                                        }
                                    }
                                    else{
                                        System.out.println("Bill number does not Exist");
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("\t1.List the Bill numbers");
                                System.out.println("\t2.Show Customer ids to filter the bill numbers");
                                int choice7=sc.nextInt();
                                if(choice7==1){
                                    System.out.println("Bill Numbers");
                                    for(Bill i:bills){
                                        System.out.println(i.bill_no);
                                    }
                                    System.out.println("Enter Bill number");
                                    b_no=sc.nextInt();
                                    for(Bill i: bills){
                                        if(i.bill_no==b_no){
                                            i.print();
                                        }
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
                                        b_no = sc.nextInt();
                                        for (Bill i : bills) {
                                            if (i.bill_no == b_no) {
                                                i.print();
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Customer id does not Exist");
                                    }
                                }
                                else{
                                    System.out.println("Invalid Choice");
                                }
                                break;
                            case 5:
                                flag2=1;
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }
                    }
                    break;
                case 3:
                    int flag3=0;
                    while(flag3==0) {
                        System.out.println("\t1.Add Items");
                        System.out.println("\t2.Delete Items");
                        System.out.println("\t3.Edit Items");
                        System.out.println("\t4.View Item");
                        System.out.println("\t5.Back to main menu");
                        int choice3 = sc.nextInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Enter Item Name");
                                String x=sc.nextLine();
                                String n=sc.nextLine();
                                System.out.println("Enter Price");
                                int p=sc.nextInt();
                                int id=++item_id;
                                items.add(new Item(id,n,p));
                                item_nos.add(id);
                                break;
                            case 2:
                                System.out.println("Item Numbers");
                                for(Integer i: item_nos){
                                    System.out.println(i);
                                }
                                System.out.println("Enter Item number");
                                id=sc.nextInt();
                                Item item_to_remove=null;
                                if(item_nos.contains(id)){
                                    for(Item i:items){
                                        if(i.Item_no==id){
                                            item_to_remove=i;
                                        }
                                    }
                                    items.remove(item_to_remove);
                                    item_nos.remove(Integer.valueOf(id));
                                }
                                else {
                                    System.out.println("Item does not Exist");
                                }
                                break;
                            case 3:
                                System.out.println("Edit Items");
                                System.out.println("NOTE: the changes are applicable to bills which are generated after the edit");
                                int flag31=0;
                                while(flag31==0) {
                                    System.out.println("\t1.Item Name");
                                    System.out.println("\t2.Item Price");
                                    System.out.println("\t3.Back to main menu");
                                    int choice31 = sc.nextInt();
                                    switch (choice31) {
                                        case 1:
                                            System.out.println("Item Numbers");
                                            for (Integer i:item_nos){
                                                System.out.println(i);
                                            }
                                            System.out.println("Enter Item Number");
                                            id=sc.nextInt();
                                            System.out.println("Enter Updated Name");
                                            x=sc.nextLine();
                                            n=sc.nextLine();
                                            if(item_nos.contains(id)) {
                                                for (Item i : items) {
                                                    if (i.Item_no == id) {
                                                        i.Item_name = n;
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Item does not Exist");
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Item Numbers");
                                            for (Integer i:item_nos){
                                                System.out.println(i);
                                            }
                                            System.out.println("Enter Item number");
                                            id=sc.nextInt();
                                            System.out.println("Enter Updated Price");
                                            p=sc.nextInt();
                                            if(item_nos.contains(id)){
                                                for(Item i:items){
                                                    if(i.Item_no==id){
                                                        i.Item_price=p;
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Item does not Exist");
                                            }
                                            break;
                                        case 3:
                                            flag31=1;
                                            break;
                                        default:
                                            System.out.println("Invalid Choice");
                                    }

                                }
                                break;
                            case 4:
                                System.out.println("Item Numbers");
                                for (Integer i:item_nos){
                                    System.out.println(i);
                                }
                                System.out.println("Enter the Item number to be viewed or 0 to view all Items");
                                id=sc.nextInt();
                                if(id==0){
                                    for (Item i:items){
                                        i.print_details();
                                    }
                                }
                                else{
                                    for (Item i:items){
                                        if(i.Item_no==id){
                                            i.print_details();
                                        }
                                    }
                                }
                                break;
                            case 5:
                                flag3=1;
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }

                    }
                    break;
                case 4:
                    int flag4=0;
                    while(flag4==0) {
                        System.out.println("\t1.Delete Customer");
                        System.out.println("\t2.Edit Customer Details");
                        System.out.println("\t3.View Customer Details");
                        System.out.println("\t4.Back to main menu");
                        int choice4 = sc.nextInt();
                        switch (choice4) {
                            case 1:
                                System.out.println("Custoemr IDs");
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
                                break;
                            case 2:
                                System.out.println("Edit Customer Details");
                                System.out.println("NOTE: the changes are applicable to bills which are generated after the edit");
                                int flag41=0;
                                while(flag41==0) {
                                    System.out.println("\t1.Customer Name");
                                    System.out.println("\t2.Customer Phone Number");
                                    System.out.println("\t3.Back to main menu");
                                    int choice41 = sc.nextInt();
                                    switch (choice41) {
                                        case 1:
                                            System.out.println("Customer IDs");
                                            for (Integer i:customer_ids){
                                                System.out.println(i);
                                            }
                                            System.out.println("Enter Customer ID");
                                            int id=sc.nextInt();
                                            System.out.println("Enter Updated Name");
                                            String x=sc.nextLine();
                                            String n=sc.nextLine();
                                            if(customer_ids.contains(id)) {
                                                for (Customer i : customers) {
                                                    if (i.customer_id == id) {
                                                        i.customer_name = n;
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Customer does not Exist");
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Customer IDs");
                                            for (Integer i:customer_ids){
                                                System.out.println(i);
                                            }
                                            System.out.println("Enter Customer number");
                                            id=sc.nextInt();
                                            System.out.println("Enter Updated Phone Number");
                                            long p=sc.nextLong();
                                            if(customer_ids.contains(id)){
                                                for(Customer i:customers){
                                                    if(i.customer_id==id){
                                                        i.customer_phone=p;
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Customer does not Exist");
                                            }
                                            break;
                                        case 3:
                                            flag41=1;
                                            break;
                                        default:
                                            System.out.println("Invalid Choice");
                                    }

                                }
                                break;
                            case 3:
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
                                    for (Customer i:customers){
                                        if(i.customer_id==id){
                                            i.print_details();
                                        }
                                    }
                                }
                                break;
                            case 4:
                                flag4=1;
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }

                    }
                    break;
                case 0:
                    System.out.println("Bye");
                    flag=1;
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
