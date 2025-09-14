/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vut;

/**
 *
 * @author MABASA NHLOHLOTELO
 */
public class StudentDetails {
    private String name;
    private String surname;
    private String studentNo;
    private String course;
    private int numberOfModules;
    private int year;

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
     * @return the studentNo
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * @param studentNo the studentNo to set
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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
     * @return the numberOfModules
     */
    public int getNumberOfModules() {
        return numberOfModules;
    }

    /**
     * @param numberOfModules the numberOfModules to set
     */
    public void setNumberOfModules(int numberOfModules) {
        this.numberOfModules = numberOfModules;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "~~~~~~~~~~~~~~~~~STUDENT DETAILS~~~~~~~~~~~~~~~~~"+"\nName: "+name+"\nSurname: "+surname+"\nStudent Number: "+studentNo+"\nCourse: "+course+"\nNumber of Modules: "+numberOfModules+"\nYear of study: "+year+"\n---------------------------------------------------------------------------------------------------"+"\nYOUR DETAILS ARE CAPTURED SUCCESSFULLY";
    }
    
    
}
