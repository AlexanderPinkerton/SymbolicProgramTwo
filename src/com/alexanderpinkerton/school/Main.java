package com.alexanderpinkerton.school;

public class Main {

    public static void main(String[] args) {


        //5x^2 - 6x^3
        Polynomial polynomial = new Polynomial(
                new Term(3,"x",3),
                new Term(-2,"x",2),
                new Term(4,"x",1),
                new Term(-3,"x",0));

        Polynomial substep = new Polynomial(
                new Term(-11,"x",2),
                new Term(-5,"x",1),
                new Term(-3,"x",0));

        Polynomial divisor = new Polynomial(
                new Term(1,"x",2),
                new Term(3,"x",1),
                new Term(3,"x",0));

        Term quotientPart = new Term(-11,"x",0);

        //System.out.println(Polynomial.multiply(divisor,quotientPart));

        //System.out.println(Polynomial.subtract(p1,p2));

//        System.out.println(Term.divide(
//                new Term(28,"x",1),
//                new Term(1,"x",2)));


      /*  Polynomial p1 = new Polynomial(
                new Term(1,"x",2),
                new Term(3,"x",1),
                new Term(2,"x",0));

        Polynomial p2 = new Polynomial(
                new Term(1,"x",1),
                new Term(1,"x",0));

       Polynomial.longDivision(p1, p2);*/

        /*Polynomial p1 = new Polynomial(
                new Term(1,"x",3),
                new Term(1,"x",2),
                new Term(-3,"x",1),
                new Term(-3,"x",0));

        Polynomial p2 = new Polynomial(
                new Term(1,"x",2),
                new Term(3,"x",1),
                new Term(2,"x",0));*/

        Polynomial p1 = new Polynomial(
                new Term(1,"x",2),
                new Term(7,"x",1),
                new Term(6,"x",0));

        Polynomial p2 = new Polynomial(
                new Term(1,"x",2),
                new Term(-5,"x",1),
                new Term(-6,"x",0));

        //Polynomial.longDivision(p1, p2);
        Polynomial.GCD(p1,p2);




    }
}
