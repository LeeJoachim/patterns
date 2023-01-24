package design08.templatemethod.sorting;

import java.util.Arrays;

// class Student implements Comparable {

class Student implements Comparable<Student> {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    /* compare algorithms */

    // @Override
    // public int compareTo(Student o) {
    //     return this.grade - o.grade;
    // }

    @Override
    public int compareTo(Student o) {

        if (this.grade < o.grade) return -1;
        else if (this.grade == o.grade) return 0;
        else return 1;

    }
    /**/

    @Override
    public String toString() {
        return "Student [name=" + name + ", grade=" + grade + "]";
    }
    
    /* comare to Object x */
    /* implements just Comparable */
    // @Override
    // public int compareTo(Object x) {

    //     /* sorting grade */
    //     if (x instanceof Student) { // RTTI
    //         Student num = (Student)x;

    //         /* algorithm */
    //         // return this.grade - num.grade; // ascending order
    //         return num.grade - this.grade; // descending order
    //     }

    //     /* sorting name */
    //     // if (x instanceof Student) {
    //     //     Student s = (Student)x;
    //     //     return name.compareTo(s.name); 
    //     // }

    //     return 0;
    // }
    // public String toString() {
    //     return name + ":" + grade;
    // }
}

class TestDriver {
    public static void main(String[] args) {

        /* change int to Integer obj and use Integer.compareTo() */
        int[] intArr = new int[5];

        intArr[0] = 30;
        intArr[1] = 80;
        intArr[2] = 40;
        intArr[3] = 90;
        intArr[4] = 10;

        Arrays.sort(intArr);

        for(int i = 0; i < 5; i++) {
            System.out.println(intArr[i]);
        }


        /* have to Student class implements Comparable
         * and make compareTo() function
         */
        Student[] studentList = new Student[5];

        studentList[0] = new Student("kim", 10);
        studentList[1] = new Student("lee", 20);
        studentList[2] = new Student("park", 90);
        studentList[3] = new Student("kwon", 60);
        studentList[4] = new Student("choi", 30);
        Arrays.sort(studentList);

        for(int i = 0; i < 5; i++) {
            System.out.println(studentList[i].toString());
        }
    }
}