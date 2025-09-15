/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vut;

/**
 *
 * @author 222223219
 */
public class Student {
    //declaring variables
    private String name, surname, course, major;
    private double courseFee, paidFee, debt;
    
    //creating getters and setter for variables as they are private variables

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return the courseFee
     */
    public double getCourseFee() {
        return courseFee;
    }

    /**
     * @param courseFee the courseFee to set
     */
    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    /**
     * @return the paidFee
     */
    public double getPaidFee() {
        return paidFee;
    }

    /**
     * @param paidFee the paidFee to set
     */
    public void setPaidFee(double paidFee) {
        this.paidFee = paidFee;
    }

    /**
     * @return the debt
     */
    public double getDebt() {
        return debt;
    }

    /**
     * @param debt the debt to set
     */
    public void setDebt(double debt) {
        this.debt = debt;
    }
    
    public void calcDebt(){
        debt = courseFee - paidFee;
    }
    public String Display(){
        return "Name: "+name+"\nSurname: "+surname+"\nCourse: "+course+"\nMajor: "+major+"\nDebt: R"+debt;
    }
}
