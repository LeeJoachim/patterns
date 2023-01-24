package design09.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* iterator : obj for traverse about collection obj's elements */
/* divide iterator and collection obj */

/* items form */
class MenuItem {
    String name;
    double price;

    MenuItem (String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}

/* need to unify skill of traverse for using iterator */
interface Menu {
    Iterator<MenuItem> createIterator();
}
/* use ArrayList<> */
class PancakeHouseMenu implements Menu {

    List<MenuItem> menuItems;

    /* set menu items */
    PancakeHouseMenu() {
        menuItems = new ArrayList<MenuItem>();

        addItem("pancake", 2.99);
        addItem("blueberry", 0.99);
    }

    void addItem(String name, double price) {
        MenuItem menuItem = new MenuItem(name, price);
        menuItems.add(menuItem);
    }

    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}
/* use basic arr */
class DinerMenu implements Menu {

    static final int MAX_ITEMS = 6;
    int index = 0;
    MenuItem[] menuItems;

    /* set menu items */
    DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Berger", 5.99);
        addItem("Pizza", 9.99);
    }

    void addItem(String name, double price) {

        MenuItem menuItem = new MenuItem(name, price);

        if (index >= menuItems.length) {
            throw new IllegalStateException("You can't add");

        } else {
            menuItems[index] = menuItem;
            index += 1;
        }
    }

    public Iterator<MenuItem> createIterator() {
        return new DinerIterator(menuItems);
    } // differnet from PancakeHouseMenu, we make DinerIterator implements Iterator<>
}
/**/

/* iterator */
/* implements next(), hasNext() */
class DinerIterator implements Iterator<MenuItem> {
    MenuItem[] menuItems;
    int position = 0;

    DinerIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;        
    }

    public MenuItem next() {
        MenuItem menuItem = menuItems[position];
        position += 1;
        return menuItem;
    }

    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void remove() {
        System.err.println("not completed yet!");
    }
}

/* this class traverses an ArrayList<Menu> */
class Waitress {
    ArrayList<Menu> menus;
    Waitress(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    void printMenu() {
        /* ArrayList's Itr obj */
        Iterator<Menu> menuIterator = menus.iterator();
        /**/

        while(menuIterator.hasNext()) {
            Menu m = menuIterator.next();
            /* each obj must have createIterator() */
            /* request two Itr obj */
            printMenu(m.createIterator());
        }
    }

    void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            System.out.println(item.getName());
            System.out.println(item.getPrice());
        }
    }
}

class TestDriver {
    public static void main(String[] args) {
        /* two menus */
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMneu = new DinerMenu();
        /**/

        /* two menus added to one list */
        ArrayList<Menu> menus = new ArrayList<Menu>();
        menus.add(pancakeHouseMenu);
        menus.add(dinerMneu);
        /**/

        /* just sending */
        Waitress waitress = new Waitress(menus);
        waitress.printMenu();
        /**/
    }
}