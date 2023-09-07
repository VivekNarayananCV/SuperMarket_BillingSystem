import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String []args){
        System.out.println("Welcome to the SuperMarket");
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
            switch (choice) {
                case 1 -> {
                    Sales_operations s_opr = new Sales_operations();
                    int flag1 = 0;
                    while (flag1 == 0) {
                        System.out.println("\t1.Check total sales done in a given period");
                        System.out.println("\t2.Check total Sales done in by a particular customer in a given period");
                        System.out.println("\t3.Sales done in a particular bill");
                        System.out.println("\t4.Back to Main Menu");
                        int choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1 -> s_opr.total_sales(bills);
                            case 2 -> {
                                System.out.println("Customer IDs");
                                for (Integer i : customer_ids) {
                                    System.out.println(i);
                                }
                                System.out.println("Enter the customer id");
                                int c_id = sc.nextInt();
                                if (!customer_ids.contains(c_id)) {
                                    System.out.println("Customer id does not Exist!");
                                    break;
                                }
                                s_opr.total_sales_customer(c_id, bills);
                            }
                            case 3 -> {
                                System.out.println("Bill Numbers");
                                for (Bill i : bills) {
                                    System.out.println(i.bill_no);
                                }
                                int flag13=0;
                                System.out.println("Enter the bill number");
                                int b_no = sc.nextInt();
                                for (Bill i : bills) {
                                    if (i.bill_no == b_no) {
                                        flag13=1;
                                        i.print();
                                    }
                                }
                                if (flag13==0) {
                                    System.out.println("Bill Number does ot Exist");
                                }
                            }
                            case 4 -> flag1 = 1;
                            default -> System.out.println("Invalid Choice");
                        }
                    }
                }
                case 2 -> {
                    BillManagement_operations b_opr = new BillManagement_operations();
                    int flag2 = 0;
                    while (flag2 == 0) {
                        System.out.println("\t1.Add bill information");
                        System.out.println("\t2.Delete a bill information");
                        System.out.println("\t3.Edit a Bill Information");
                        System.out.println("\t4.View a Bill Information");
                        System.out.println("\t5.Back to Main Menu");
                        int choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1 -> {
                                int flag21 = b_opr.create_bill(bill_no, customer_id, customer, customer_ids, customers, items, bills);
                                if(flag21==1){
                                    bill_no++;
                                    customer_id++;
                                }
                            }
                            case 2 -> b_opr.delete_bill(bills, customer_ids);
                            case 3 -> b_opr.edit_bill(bills);
                            case 4 -> b_opr.view_bill(bills, customer_ids);
                            case 5 -> flag2 = 1;
                            default -> System.out.println("Invalid Choice");
                        }
                    }
                }
                case 3 -> {
                    Item_operations i_opr = new Item_operations();
                    int flag3 = 0;
                    while (flag3 == 0) {
                        System.out.println("\t1.Add Items");
                        System.out.println("\t2.Delete Items");
                        System.out.println("\t3.Edit Items");
                        System.out.println("\t4.View Item");
                        System.out.println("\t5.Add/Edit Discounts");
                        System.out.println("\t6.Back to main menu");
                        int choice3 = sc.nextInt();
                        switch (choice3) {
                            case 1 -> item_id=i_opr.create_item(item_id, items, item_nos);
                            case 2 -> i_opr.delete_item(item_nos, items);
                            case 3 -> i_opr.edit_item(item_nos, items);
                            case 4 -> i_opr.view_item(item_nos, items);
                            case 5 -> i_opr.add_discount(item_nos, items);
                            case 6 -> flag3 = 1;
                            default -> System.out.println("Invalid Choice");
                        }

                    }
                }
                case 4 -> {
                    Customer_operations c_opr = new Customer_operations();
                    int flag4 = 0;
                    while (flag4 == 0) {
                        System.out.println("\t1.Delete Customer");
                        System.out.println("\t2.Edit Customer Details");
                        System.out.println("\t3.View Customer Details");
                        System.out.println("\t4.Back to main menu");
                        int choice4 = sc.nextInt();
                        switch (choice4) {
                            case 1 -> c_opr.delete_customer(customer_ids, bills, customers);
                            case 2 -> c_opr.edit_customer(customer_ids, customers);
                            case 3 -> c_opr.view_customer(customer_ids, customers);
                            case 4 -> flag4 = 1;
                            default -> System.out.println("Invalid Choice");
                        }

                    }
                }
                case 0 -> {
                    System.out.println("Bye");
                    flag = 1;
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}