package design09.iterator.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


class DinerMenuIterator implements Iterator<String> {
    static int MAX = 10;
    String[] items;
    int position = 0;

    public DinerMenuIterator(String[] items) {
        this.items = items;
    }

    /* checking for null and 'end of arr' */
    @Override
    public boolean hasNext() {
        if (position >= items.length || items[position] == null) return false;
        else return true;
    }
    /* pull it out */
    @Override
    public String next() {
        String temp = items[position];
        position++;
        return temp;
    }
}

interface Menu {
    Iterator<String> createIterator();
}
class CafeMenu implements Menu {
    HashMap<Integer, String> menuItems;

    CafeMenu() {
        menuItems = new HashMap<Integer, String>();
        menuItems.put(1, "coffee");
        menuItems.put(2, "tea");
        menuItems.put(3, "milk");
    }
    public HashMap<Integer, String> getMenuItems() {
        return menuItems;
    }
    @Override
    public Iterator<String> createIterator() {
        return menuItems.values().iterator();
    }
    
}
/** has String[] menuItems */
class DinerMenu implements Menu {
    static int MAX = 10;
    int n = 0;
    String[] menuItems;

    DinerMenu() {
        menuItems = new String[MAX];

        menuItems[n++] = "kimbap";
        menuItems[n++] = "rameon";
        menuItems[n++] = "udong";
    }

    public Iterator<String> createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
/** has ArrayList<String> menuItems */
class PancakeHouseMenu implements Menu {
    ArrayList<String> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<String>();
        menuItems.add("AAA pancake");
        menuItems.add("BBB pancake");
        menuItems.add("CCC pancake");
    }
    public Iterator<String> createIterator() {
        return menuItems.iterator();
    }
}

class Waitress {

    ArrayList<Menu> menus;

    public Waitress(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    /* iterator's traverse mechanism */
    void printMenu() {

        Iterator<Menu> menuIterator = menus.iterator();
        while (menuIterator.hasNext()) {
            Menu m = menuIterator.next();
            Iterator<String> i = m.createIterator();
            print(i);
        }

        /* this is not using iterator */
        // for (Menu m : menus) {
        //     Iterator<String> iter = m.createIterator();
        //     print(iter);  
        // }
        /**/
        
        /* dirty codes */
        // String[] m1 = dinerMenu.getMenuItems();
        // for (int i = 0; i < m1.length; i++) {
        //     System.out.println(m1[i]);
        // }
        // ArrayList<String> m2 = pancakeHouseMenu.getMenuItems();
        // for (String s : m2) {
        //     System.out.println(s.toString());
        // }
        /**/
    }

    void print(Iterator<String> i) {
        while (i.hasNext()) {
            System.out.println(i.next()); 
        }
    }
    /**/

}

class TestDriver {
    public static void main(String[] args) {
        /* factory */
        DinerMenu x = new DinerMenu();
        PancakeHouseMenu y = new PancakeHouseMenu();
        CafeMenu z = new CafeMenu();
        /**/

        /* list */
        ArrayList<Menu> menus = new ArrayList<Menu>();
        menus.add(x);
        menus.add(y);
        menus.add(z);
        /**/

        Waitress w = new Waitress(menus);
        w.printMenu();
    }
}
