package vut;

import java.util.Scanner;

/**
 *
 * @author MABASA NHLOHLOTELO
 */
public class Main {
    public static void main(String[] args) {
        Residences obj = new Residences();
        obj.display();
    }
}
class Residences{
    private double nsfasBP, selfPB;
    private final double roomR = 2050.50;
    private final double elecB = 510.90;
    private final double transB = 1430.50;
    private final double externalB = 1008.1;
    private final double nsfasMaxB = 6500.0;
    private final int noOfRooms = 50;
    private int sharingRoomNsfas, sharingRoomSelf, singleRoomNsfas, singleRoomSelf;
    
    private Scanner sc = new Scanner(System.in);
    void userInputForRoomsAllocation(){
        System.out.print("From "+noOfRooms+" rooms, how many rooms for NSFAS sharing: ");
        sharingRoomNsfas = sc.nextInt();
        System.out.print("From "+(noOfRooms - sharingRoomNsfas)+ " rooms, how many rooms for self paying sharing: ");
        sharingRoomSelf = sc.nextInt();
        System.out.print("From "+(noOfRooms - (sharingRoomNsfas + sharingRoomSelf))+" rooms, how many rooms for NSFAS single: ");
        singleRoomNsfas = sc.nextInt();
        System.out.print("From "+ (noOfRooms - (sharingRoomNsfas + sharingRoomSelf + singleRoomNsfas))+" rooms, how many rooms for self paying single: ");
        singleRoomSelf = sc.nextInt();
        
    }
    double calculateMonthlyNsfasSharing(){
        double monthlyNsfasSharing;
        int sharingPeopleNsfas = sharingRoomNsfas  * 2;
        final double nsfasSharingRent = 4000.50;
        monthlyNsfasSharing = sharingPeopleNsfas * nsfasSharingRent;
        return monthlyNsfasSharing;
    }
    double calculateMonthlySelfPayingsharing(){
        double monthlySelfSharing;
        int sharingPeopleSelf = sharingRoomSelf  * 2;
        final double selfSharingRent = 3500.50;
        monthlySelfSharing = sharingPeopleSelf * selfSharingRent;
        return monthlySelfSharing;
    }
    double calculateMonthlyNsfasSingle(){
        double monthlyNsfasSingle;
        final double nsfasSingleRent = 6200.50;
        monthlyNsfasSingle = singleRoomNsfas * nsfasSingleRent;
        return monthlyNsfasSingle;
    }
    double calculateMonthlySelfPaySingle(){
        double monthlySelfSingle;
        final double selfSingleRent = 5500.50;
        monthlySelfSingle = singleRoomSelf * selfSingleRent;
        return monthlySelfSingle;
    }
    void nsfasBP(){
        nsfasBP = (calculateMonthlyNsfasSharing() * 10) + (calculateMonthlyNsfasSingle() * 10);
    }
    void selfPB(){
        selfPB = (calculateMonthlySelfPayingsharing() * 10)+(calculateMonthlySelfPaySingle() * 10);
    }
    double moneyInPerAnnum(){
        double moneyIn = nsfasBP + selfPB;
        return moneyIn;
    }
    double moneyUsedPerAnnum(){
        double usedMoney = ((elecB * 50)*10)+ ((transB * 15)*10) + ((externalB *10)*10)+((roomR * 50)*10);
        return usedMoney;
    }
    double profitPerAnnum(){
        double profit = moneyInPerAnnum() - moneyUsedPerAnnum();
        return profit;
    }
    void display(){
        userInputForRoomsAllocation();
        nsfasBP();
        selfPB();
        System.out.println("Money in is R"+moneyInPerAnnum());
        System.out.println("Money used is R"+moneyUsedPerAnnum());
        System.out.println("Profit is R"+profitPerAnnum());
    }
}