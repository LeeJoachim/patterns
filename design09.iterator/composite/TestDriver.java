package design09.iterator.composite;

import java.util.ArrayList;
import java.util.Iterator;

/* hou to make hierarchically information to tree structure. */
/* composite pattern : making tree */

class MenuComponent {

    void add(MenuComponent menuComponent) { // hook functions
        throw new UnsupportedOperationException();
    }
    void print() {
        throw new UnsupportedOperationException();
    }
}

class MenuItem extends MenuComponent {
    String name;
    String description;

    MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    @Override
    void print() {
        System.out.println(getName());
        System.out.println(getDescription());
    }
    
}

class Menu extends MenuComponent{

    ArrayList<MenuComponent> list = new ArrayList<MenuComponent>();
    String name;
    String description;

    Menu (String name, String description) {
        this.name = name;
        this.description = description;
    }

    void add(MenuComponent menuComponent) {
        list.add(menuComponent);
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    void print() {
        System.out.println(getName());
        System.out.println(getDescription());
        
        Iterator<MenuComponent> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();

        }
    }

}

class Waitress {
    MenuComponent allMenus; // maintain root reference

    Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    void printMenu() {
        allMenus.print();
    }
}

class TestDriver {
    public static void main(String[] args) {
        MenuComponent dinerMenu = new Menu("DINER MENU", "Dinner");
        MenuComponent cafeMenu = new Menu("CAFE MENU", "Cafe");

        MenuComponent allMenus = new Menu("ALL MENUS", "All Menus Combined");

        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        dinerMenu.add(new MenuItem("pancake", "with scrambled eggs and toast"));
        cafeMenu.add(new MenuItem("coffee", "hot americano coffee made by fresh beans"));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }
}

/* cohesion */
/* 
 * tight cohesion == good
 * 1. functional cohesion
 * 2. sequential cohesion
 * 3. comunicational cohesion
 * 4. procedural cohesion
 * 5. temporal cohesion
 * 6. logical cohesion
 * 7. coincidental cohesion
 * loose cohesion == bed 
 */

/* coupling */
/*
 * loose coupling == good
 * 1. data coupling
 * 2. stamp coupling
 * 3. control coupling
 * 4. common coupling
 * 5. content coupling
 * tight coupling == bed
 */