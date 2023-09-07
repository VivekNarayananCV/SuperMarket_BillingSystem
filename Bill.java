import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Bill {
    int bill_no;
    Customer c_id;
    int sales;
    int actual_price;
    int total_discount;
    LocalDate date;
    ArrayList<Item> items=new ArrayList<>();
    HashMap<Integer,Integer> amt= new HashMap<>();
    public Bill(int bill_no,LocalDate date,ArrayList<Item> items,Customer customer,HashMap amt){
        this.bill_no=bill_no;
        this.c_id=customer;
        this.date=date;
        this.items=items;
        this.amt=amt;
    }
    void setSales(){
        this.sales=0;
        this.total_discount=0;
        this.actual_price=0;
        for(Integer i: this.amt.keySet()){
            for(Item j: this.items){
                if(i==j.Item_no){
                    actual_price+=j.Item_price*this.amt.get(i);
                    total_discount+=j.Item_price*this.amt.get(i)*j.discount/100;//implement discount
                }
            }
        }
        sales=actual_price-total_discount;
    }
    boolean check_date_in_period(LocalDate startdate,LocalDate enddate){
        return this.date.isAfter(startdate) && this.date.isBefore(enddate);
    }
    void print(){
        System.out.println("Customer Details");
        this.c_id.print_details();
        System.out.println("Item Details");
        for(Integer j: this.amt.keySet()){
            for(Item k:this.items){
                if(k.Item_no==j){
                    System.out.println(j+" "+k.Item_name+" "+this.amt.get(j));
                }
            }
        }
        System.out.println("SubTotal");
        System.out.println(this.actual_price);
        System.out.println("Total Discount");
        System.out.println(this.total_discount);
        System.out.println("The Net Sales amount");//total discount availed
        System.out.println(this.sales);
        System.out.println("Purchase Date");
        System.out.println(this.date);
    }
}