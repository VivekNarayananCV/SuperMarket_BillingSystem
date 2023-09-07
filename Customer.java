public class Customer {
    int customer_id;
    String customer_name;
    long customer_phone;
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
}