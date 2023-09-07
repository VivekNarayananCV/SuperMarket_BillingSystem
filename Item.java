public class Item {
    int Item_no;
    String Item_name;
    int Item_price;
    int discount=0;
    public Item(int id,String n,int p){
        this.Item_no=id;
        this.Item_name=n;
        this.Item_price=p;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    void print_details(){
        System.out.print(this.Item_no);
        System.out.print("\t"+this.Item_name);
        System.out.print("\t Price "+this.Item_price);
        System.out.println("\t Discount Provided "+this.discount);
    }
}