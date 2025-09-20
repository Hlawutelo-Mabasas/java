/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vut;

/**
 *
 * @author MABASA NHLOHLOTELO
 */
public class Sale {
    private int quantity;
    private double price;
    private double totalPrice;
    private double totalAfterSale;
    private double totalAfterMembershipSale;
    private double sale = 0.15;
    private double membershipSale = sale + 0.20;
    
    public Sale(int quantity, double price){
        setQuantity(quantity);
        setPrice(price);
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    

    /**
     * @return the sale
     */
    public double getSale() {
        return sale;
    }

    

    /**
     * @return the membershipSale
     */
    public double getMembershipSale() {
        return membershipSale;
    }
    
    public void calcTotalPrice(){
        totalPrice = Math.round((quantity * price)*100.0)/100.0;
    }
    public void calcTotalAfterSale(){
        totalAfterSale = totalPrice - (Math.round((totalPrice * sale)*100.0)/100.0);
    }
    public void calcTotalAfterMembershipSale(){
        totalAfterMembershipSale = totalPrice - (Math.round((totalPrice * membershipSale)*100.0)/100.0);
    }
    
    public String display(){
        return "Purchased successfully";
    }
    
    public String toStringSale(){
        return "--------------------WELCOME--------------------"+"\nQuantity: "+quantity+"\nPrice: R"+price+"\nTotal Price: R"+totalPrice+"\nSale total Price: R"+totalAfterSale+"\n---------------------------------------------------------------";
        
    }
    public String toStringMember(){
        return "--------------------WELCOME--------------------"+"\nQuantity: "+quantity+"\nPrice: R"+price+"\nTotal Price: R"+totalPrice+"\nMembership total price: R"+totalAfterMembershipSale+"\n---------------------------------------------------------------";

    }

    /**
     * @return the totalAfterSale
     */
    public double getTotalAfterSale() {
        return totalAfterSale;
    }

    /**
     * @param totalAfterSale the totalAfterSale to set
     */
    public void setTotalAfterSale(double totalAfterSale) {
        this.totalAfterSale = totalAfterSale;
    }

    /**
     * @return the totalAfterMembershipSale
     */
    public double getTotalAfterMembershipSale() {
        return totalAfterMembershipSale;
    }

    /**
     * @param totalAfterMembershipSale the totalAfterMembershipSale to set
     */
    public void setTotalAfterMembershipSale(double totalAfterMembershipSale) {
        this.totalAfterMembershipSale = totalAfterMembershipSale;
    }
}
