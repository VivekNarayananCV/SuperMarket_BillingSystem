import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Sales_operations {
    Scanner sc= new Scanner(System.in);

    public void total_sales(ArrayList<Bill> bills) {
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
        int total_actual=0;
        int total_discount=0;
        for (Bill i : bills) {
            if (i.check_date_in_period(startdate, enddate)) {
                total_sales += i.sales;
                total_discount+=i.total_discount;
                total_actual+=i.actual_price;
            }
        }
        System.out.println("Total Bill Amount: "+total_actual);
        System.out.println("Total Discount: "+total_discount);
        System.out.println("Total Sales: " + total_sales);
    }

    public void total_sales_customer(int c_id,ArrayList<Bill> bills) {
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
        int total_discount=0;
        int total_actual=0;
        for (Bill i : bills) {
            if (i.c_id.customer_id == c_id && i.check_date_in_period(startdate, enddate)) {
                total_sales += i.sales;
                total_actual+=i.actual_price;
                total_discount+=i.total_discount;
            }
        }
        System.out.println("Total Bill Amount: "+total_actual);
        System.out.println("Total Discount: "+total_discount);
        System.out.println("Total Sales: " + total_sales);
    }
}