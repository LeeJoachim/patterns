package design08.templatemethod;

import java.util.Scanner;

/* system establishing in super class */
/* e.g. using java library */
/* algorithm in super class, abstract class... */
/* java override */

/* high abstraction level */
abstract class Beverage {

    /* template method */
    final void prepare() { // final : can't override
        this.boil();
        this.brew();
        this.pour();
        if (hook()) {
            this.addCondiments();
        }
    } // algorithm

    void boil() {
        System.out.println("1.boil");
    }

    abstract void brew();

    void pour() {
        System.out.println("3.pour");
    }

    abstract void addCondiments();

    /* hook function : you can override it or you don't override to */
    boolean hook() {
        return true; // default value
    }

}

/* low abstraction level */
class Coffee extends Beverage {
    
    
    /* if you want to change something
     * you have to override function and impliment again
     */
    @Override
    void boil() {
        super.boil();
        System.out.println("...??"); // add something
    }

    @Override
    void brew() {
        System.out.println("2.coffee-brew");
    }

    @Override
    void addCondiments() {
        System.out.println("4.coffee-add");
    }

    @Override
    boolean hook() {
        // return false;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("do you want to add sugar? (y/n)");

        String answer = sc.next();
        sc.close();

        if (answer.equals("y")) return true;
        else return false;
    }
}

/* low abstraction level */
class Tea extends Beverage {

    @Override
    void brew() {
        System.out.println("2.Tea-brew");
    }

    @Override
    void addCondiments() {
        System.out.println("4.Tea-add");
    }
}

class TestDriver {
    public static void main(String[] args) {
        /*
         * java reflection is one side
         * just go to upside i.e. bottom up
         * so compiler knows about super's prototype compiles
         * and when runtime i.e. in heap area
         * flow goes bottom up and then if it doesn't matching with blueprint
         * flow goes next class for finds specific function
         */
        Beverage coffee = new Coffee();
        coffee.prepare();
    }
}