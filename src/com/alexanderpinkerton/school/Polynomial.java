package com.alexanderpinkerton.school;

import java.util.Arrays;

/**
 * Created by Ace on 9/19/2015.
 */
public class Polynomial {

    long[] coefficients;
    Term[] terms;
    long degree = 1;


    //Prime or Composite
    public Polynomial(Term... terms){

        //Copy all of the terms into the polynomial
        this.terms = terms;
        //Loop through the terms to find the degree of the polynomial.
        for(Term t : terms){
            if(t.getExponent() > degree){
                degree = t.getExponent();
            }
        }

        coefficients = new long[(int)degree+1];

        //Order the polynomial: Highest power first.
        orderByDegree();

    }


    public Polynomial(String polynomial){
        //Parse input with form -5x^(3+1)+6x^2
        //This needs new Expression system.
    }


    public void orderByDegree(){

        for(int j = 1; j < terms.length; j++){
            Term key = terms[j];
            int i = j-1;

            while((i>=0) && (terms[i].getExponent() < key.getExponent())){
                terms[i+1] = terms[i];
                i--;
            }
            terms[i+1] = key;
        }

        //System.out.println("Polynomial has been ordered: ");
        //System.out.println(Arrays.toString(terms));

    }


    public void addTerm(Term newTerm){
        Term[] result = new Term[terms.length + 1];
        for(int i=0;i<terms.length;i++){
            result[i] = terms[i];
            if(result[i].getExponent() > getDegree()){
                setDegree(result[i].getExponent());
            }
        }
        result[terms.length] = newTerm;
        terms = result;
        orderByDegree();
    }


    public Term[] getTerms() {
        return terms;
    }

    public Term getTerm(int i){
        return terms[i];
    }

    public long getDegree() {
        return degree;
    }

    public void setDegree(long degree) {
        this.degree = degree;
    }

    //==============================================STATIC CLASS FUNCTIONS=============================================

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {

        //System.out.println("SUBTRACT: " + p2 + "  FROM  " + p1);

        //Terrible Implementation.

        Polynomial result = new Polynomial();

        for(Term t1 : p1.getTerms()){
            boolean found = false;
            for(Term t2 : p2.getTerms()){
                if(t1.getExponent() == t2.getExponent() && !t1.isMarked() && !t2.isMarked()){
                    Term difference = Term.subtract(t1, t2);
                    if(difference.getCoefficient() != 0){
                        result.addTerm(difference);
                    }
                    found = true;
                    t1.setMarked(true);
                    t2.setMarked(true);
                }
            }
            if(!found && !t1.isMarked()){
                result.addTerm(t1);
            }
        }

        for(Term t1 : p2.getTerms()){
            boolean found = false;
            for(Term t2 : p1.getTerms()){
                if(t1.getExponent() == t2.getExponent() && !t1.isMarked() && !t2.isMarked()){
                    Term difference = Term.subtract(t1, t2);
                    if(difference.getCoefficient() != 0){
                        result.addTerm(difference);
                    }
                    found = true;
                    t1.setMarked(true);
                    t2.setMarked(true);
                }
            }
            if(!found && !t1.isMarked()){
                result.addTerm(t1);
            }
        }

        return result;
    }


    public static Polynomial multiply(Polynomial p1, Polynomial p2){
        Polynomial result = null;




        return result;
    }

    public static Polynomial multiply(Polynomial polynomial, Term singleTerm){

        //System.out.println("MULTIPLY: " + polynomial + "  AGAINST  " + singleTerm);

        Polynomial result  = new Polynomial();
        //Loop through all the terms in the polynomial and multiple them against the single term.
        for(int i=0;i<polynomial.getTerms().length;i++){
            result.addTerm(Term.multiply(polynomial.getTerms()[i], singleTerm));
        }

        //Loop through the terms to find the degree of the polynomial.
        for(Term t : result.getTerms()){
            if(t.getExponent() > result.getDegree()){
                result.setDegree(t.getExponent());
            }
        }

        return result;
    }

    public static Polynomial[] longDivision(Polynomial polynomial, Polynomial divisor){

        boolean complete = false;

        Term quotientPart;

        Polynomial substep = null;
        Polynomial quotient = new Polynomial();

        //Make sure the polynomials are ordered before preforming division.
        polynomial.orderByDegree();
        divisor.orderByDegree();

        if(divisor.degree > polynomial.degree){
            throw new IllegalArgumentException("Denominator must be of equal or lower degree.");
        }

        int step = 0;
        //Step one of long division
        while(!complete){

            quotientPart = Term.divide(polynomial.getTerms()[0], divisor.getTerms()[0]);
            //System.out.println(quotientPart);
            if(!quotientPart.getVariable().equals("Y")){
                quotient.addTerm(quotientPart);
                substep = Polynomial.subtract(polynomial, Polynomial.multiply(divisor, quotient.getTerm(step)));
                polynomial = substep;
//                System.out.println("SUBSTEP:  " + substep);
//                System.out.println("QUOTIENT:  " + quotient);
            }else{complete = true;}

            step++;
        }

        //System.out.println(quotient);

        Polynomial[] result = new Polynomial[2];
        //Quotient
        result[0] = quotient;
        //Remainder
        result[1] = substep;
        //System.out.println("QUOTIENT: " + quotient + "\tREMAINDER\t" + substep+ " over " + divisor);
        return result;
    }


    public static Polynomial GCD(Polynomial p1, Polynomial p2){
        Polynomial result = new Polynomial();

        Polynomial[] divResults = Polynomial.longDivision(p1, p2);
        System.out.println(Arrays.toString(divResults));
        divResults = Polynomial.longDivision(p2, divResults[1]);
        System.out.println(Arrays.toString(divResults));







        return result;
    }













    @Override
    public String toString() {
        return "Polynomial{" +
                "terms=" + Arrays.toString(terms) +
                ", degree=" + degree +
                '}';
    }
}
