import java.util.ArrayList;
import java.util.Scanner;

public class Item_operations {
    Scanner sc=new Scanner(System.in);
    public int create_item(int item_id, ArrayList<Item> items,ArrayList<Integer> item_nos){
        System.out.println("Enter Price");
        int p=sc.nextInt();
        System.out.println("Enter Item Name");
        sc.nextLine();
        String n=sc.nextLine();
        int id=++item_id;
        items.add(new Item(id,n,p));
        item_nos.add(id);
        return item_id;
    }

    public void delete_item(ArrayList<Integer>item_nos,ArrayList<Item> items){
        System.out.println("Item Numbers");
        for(Integer i: item_nos){
            System.out.println(i);
        }
        System.out.println("Enter Item number");
        int id=sc.nextInt();
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
    }

    public void edit_item(ArrayList<Integer>item_nos,ArrayList<Item>items){
        System.out.println("Edit Items");
        System.out.println("NOTE: the changes are applicable to bills which are generated after the edit");
        int flag31=0;
        while(flag31==0) {
            System.out.println("\t1.Item Name");
            System.out.println("\t2.Item Price");
            System.out.println("\t3.Back to main menu");
            int choice31 = sc.nextInt();
            switch (choice31) {
                case 1 -> {
                    System.out.println("Item Numbers");
                    for (Integer i : item_nos) {
                        System.out.println(i);
                    }
                    System.out.println("Enter Item Number");
                    int id = sc.nextInt();
                    System.out.println("Enter Updated Name");
                    sc.nextLine();
                    String n = sc.nextLine();
                    if (item_nos.contains(id)) {
                        for (Item i : items) {
                            if (i.Item_no == id) {
                                i.Item_name = n;
                            }
                        }
                    } else {
                        System.out.println("Item does not Exist");
                    }
                }
                case 2 -> {
                    System.out.println("Item Numbers");
                    for (Integer i : item_nos) {
                        System.out.println(i);
                    }
                    System.out.println("Enter Item number");
                    int id = sc.nextInt();
                    System.out.println("Enter Updated Price");
                    int p = sc.nextInt();
                    if (item_nos.contains(id)) {
                        for (Item i : items) {
                            if (i.Item_no == id) {
                                i.Item_price = p;
                            }
                        }
                    } else {
                        System.out.println("Item does not Exist");
                    }
                }
                case 3 -> flag31 = 1;
                default -> System.out.println("Invalid Choice");
            }

        }
    }

    public void view_item(ArrayList<Integer>item_nos,ArrayList<Item>items){
        System.out.println("Item Numbers");
        for (Integer i:item_nos){
            System.out.println(i);
        }
        System.out.println("Enter the Item number to be viewed or 0 to view all Items");
        int id=sc.nextInt();
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
    }

    public void add_discount(ArrayList<Integer>item_nos,ArrayList<Item>items){
        System.out.println("Item Numbers");
        for (Integer i:item_nos){
            System.out.println(i);
        }
        System.out.println("Enter the Item Number to add Discount to it");
        int id=sc.nextInt();
        int flag33=0;
        for(Item i:items){
            if(i.Item_no==id){
                flag33=1;
                System.out.println("Enter the Discount percentage");
                i.setDiscount(sc.nextInt());
            }
        }
        if (flag33==0){
            System.out.println("Item does not Exist");
        }
    }
}