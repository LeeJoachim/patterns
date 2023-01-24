package design07.adapt.adapter.enumerationiterator;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

class IteratorEnumeration implements Enumeration<Object> {
    Iterator<?> iterator;

    public IteratorEnumeration(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public Object nextElement() {
        return iterator.next();
    }

    
}


class Enumerationiterator implements Iterator<Object> {
    Enumeration<?> enumeration;

    public Enumerationiterator(Enumeration<?> enumeration) {
        this.enumeration = enumeration;
    }
    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }
    @Override
    public Object next() {
        return enumeration.nextElement();
    }
    @Override
    public void remove() {
        // throw new UnsupportedOperationException();
        System.err.println("Unsupported Operation");
    }
}

public class TestDriver {
    public static void main(String[] args) {

        /* vector */
        Vector<String> v = new Vector<String>();
        v.add("kim");
        v.add("na");
        v.add("park");
        v.add("lee");
        /**/


        /* old style */
        System.out.println("/* old style */");
        Enumeration<String> e = v.elements();
        while(e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s);
        }
        System.out.println("");

        /* latest style */
        System.out.println("/* latest style */");
        Iterator<String> i = v.iterator();
        while(i.hasNext()) {
            String s = i.next();
            System.out.println(s);
        }
        System.out.println("");

        /* adapter */
        System.out.println("/* adapter */");
        Iterator<Object> iter = new Enumerationiterator(v.elements());
        // Iterator<Object> iter = new Enumerationiterator(e);
        while(iter.hasNext()) { // or RTTI and type casting
            Object s = iter.next();
            System.out.println(s);
        }
        iter.remove();
        System.out.println("");
        
        /* adapter2 */
        System.out.println("/* adapter2 */");
        Enumeration<Object> enu = new IteratorEnumeration(v.iterator());
        while(enu.hasMoreElements()) {
            Object s = enu.nextElement();
            System.out.println(s);
        }

    }
}
