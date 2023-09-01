import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    int customer_id;
    String customer_name;
    long customer_phone;
    Scanner sc =new Scanner((System.in));
    public Customer(int c,String s,long p){
        this.customer_id=c;
        this.customer_name=s;
        this.customer_phone=p;
    }
    void print_details(){
        System.out.print(this.customer_id);
        System.out.print("\t"+this.customer_name);
        System.out.println("\t"+this.customer_phone);
    }
    boolean customer_one(ArrayList<Bill> bills){
        int j=0;
        for(Bill i:bills){
            if(i.c_id.customer_id==this.customer_id){
                j++;
            }
        }
        return j == 1;
    }
}
