public class Item {
    int Item_no;
    String Item_name;
    int Item_price;
    public Item(int id,String n,int p){
        this.Item_no=id;
        this.Item_name=n;
        this.Item_price=p;
    }
    void print_details(){
        System.out.print(this.Item_no);
        System.out.print("\t"+this.Item_name);
        System.out.println("\t"+this.Item_price);
    }
}
