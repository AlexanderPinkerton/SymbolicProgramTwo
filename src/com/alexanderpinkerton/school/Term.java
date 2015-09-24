package com.alexanderpinkerton.school;

/**
 * Created by Ace on 9/19/2015.
 */
public class Term {

    private long coefficient = 0;
    private long exponent = 1;
    //THIS NEEDS TO BE UPGRADED
    private String variable = "";

    //helper method for calculations.....
    private boolean marked = false;

    public Term(){}

    public Term(long coefficient, String variable, long exponent){
        this.variable = variable;
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(long constant){
        this.coefficient = constant;
        this.exponent = 0;
    }

    public long getCoefficient() {
        return coefficient;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public void setCoefficient(long coefficient) {
        this.coefficient = coefficient;
    }

    public long getExponent() {
        return exponent;
    }

    public void setExponent(long exponent) {
        this.exponent = exponent;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public boolean isConstant(){
        return getExponent() == 0;
    }

    @Override
    public String toString() {
        if(getExponent() == 0){
            return coefficient + "";
        }else{
            return coefficient + variable + "^" + exponent;
        }

    }

    //=============================================STATIC CLASS FUNCTIONS=============================================

    public static Term subtract(Term t1, Term t2){
        String variable;

        if(t1.getExponent() != t2.getExponent()){
            throw new IllegalArgumentException("Exponents must match when subtracting terms.");
        }

        if(t1.getVariable().equals(t2.getVariable())){
            variable = t1.getVariable();
        }else{
            throw new IllegalArgumentException("Only single variable polynomials are supported currently.");
        }

        return new Term(t1.getCoefficient()-t2.getCoefficient(), variable,t1.getExponent());
    }


    public static Term multiply(Term t1, Term t2){
        //TODO Handle constants

        Term result=null;

        if(t1.getVariable().equals(t2.getVariable()) && !t1.isConstant() && !t2.isConstant()){
            result =  new Term(t1.getCoefficient()*t2.getCoefficient(), t1.getVariable() ,t1.getExponent() + t2.getExponent());
        }else if(t1.isConstant() && !t2.isConstant()){
            result = new Term(t1.getCoefficient()*t2.getCoefficient(), t2.getVariable(),t2.getExponent());
        }else if(!t1.isConstant() && t2.isConstant()){
            result = new Term(t1.getCoefficient()*t2.getCoefficient(), t1.getVariable(),t1.getExponent());
        }else if(t1.isConstant() && t2.isConstant()){
            result = new Term(t1.getCoefficient()*t2.getCoefficient(), "x",0);
        }else{
            throw new IllegalArgumentException("Only single variable polynomials are supported currently.");

        }

        return result;
    }

    public static Term divide(Term t1, Term divisor){
        Term result=null;

        if(t1.getVariable().equals(divisor.getVariable()) && !t1.isConstant() && !divisor.isConstant()){
            //if(t1.getExponent() - divisor.getExponent() >= 0){
                result =  new Term(t1.getCoefficient()/divisor.getCoefficient(), t1.getVariable() ,t1.getExponent() - divisor.getExponent());
           // }else{
               // result = new Term(0,"x",0);
            //}
        }else if(t1.isConstant() && !divisor.isConstant()){
            result = new Term(t1.getCoefficient()/divisor.getCoefficient(), divisor.getVariable(),divisor.getExponent());
        }else if(!t1.isConstant() && divisor.isConstant()){
            result = new Term(t1.getCoefficient()/divisor.getCoefficient(), t1.getVariable(),t1.getExponent());
        }else{
            throw new IllegalArgumentException("Only single variable polynomials are supported currently.");
        }

        return result;
    }


}
