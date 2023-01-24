import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
    String getName();
    int getAge();
    String toString();
}

class PersonImpl implements Person {
    String name;
    int age;

    public PersonImpl (String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public int getAge() {
        // TODO Auto-generated method stub
        return age;
    }

    public String toString() {
        return name + age;
    }
}
class PersonInvocationHandler implements InvocationHandler {

    Person p;

    PersonInvocationHandler(Person p) {
        this.p = p;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // command pattern // 모든 함수를 대변하는 

        System.out.println("passed!!");

        if (method.getName().equals("getAge")) {
            Integer n = (Integer)method.invoke(p, args);
            return (n+10);
        } else if (method.getName().equals("getName")) {
            return "Hello";
        }
        return method.invoke(p, args); // always pass
    }
    
}

class ProxyTestDriver {
    public static void main(String[] args) {
        Person p = new PersonImpl("kim", 32);

        System.out.println(p.getName());
        System.out.println(p.getAge());
        System.out.println(p);

        Person personProxy = (Person)Proxy.newProxyInstance(
            p.getClass().getClassLoader(), 
            p.getClass().getInterfaces(), 
            new PersonInvocationHandler(p)); // 가로챌 수 있는 능력을 가진 class

        System.out.println(personProxy.getName()); // invoke function 를 거쳐서 출력, 조사 및 가공할 수도 거부할 수도
        System.out.println(personProxy.getAge());
        System.out.println(personProxy);
            
    }


}
