import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Bill {
    int bill_no;
    Customer c_id;
    int sales;
    LocalDate date;
    ArrayList<Item> items=new ArrayList<>();
    HashMap<Integer,Integer> amt= new HashMap<>();
    public Bill(int bill_no,int year,int month,LocalDate date,ArrayList<Item> items,Customer customer,HashMap amt){
        this.bill_no=bill_no;
        this.c_id=customer;
        this.date=date;
        this.items=items;
        this.amt=amt;
        this.sales=0;
        for(Integer i: this.amt.keySet()){
            for(Item j: this.items){
                if(i==j.Item_no){
                    sales+=j.Item_price*this.amt.get(i);
                }
            }
        }
    }
    boolean check(LocalDate startdate,LocalDate enddate){
        if(this.date.isAfter(startdate) && this.date.isBefore(enddate)){
            return true;
        }
        else{
            return false;
        }
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
        System.out.println("Sales amount");
        System.out.println(this.sales);
        System.out.println("Purchase Date");
        System.out.println(this.date);
    }
}