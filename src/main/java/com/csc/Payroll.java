package com.csc;

import java.util.Scanner;

public class Payroll {

  public static void main(String[] args) {
    Scanner hours = new Scanner(System.in);
    Payroll payroll = new Payroll();
    double pay;
    System.out.println("Welcome to the Payroll Program!!");
    System.out.println("Enter hom many hours you worked and the number of children you have:");
    double time = hours.nextDouble();
    int family = hours.nextInt();
    while (time < 0) {
      System.out.print("invalid time. Enter your time: ");
      time = hours.nextDouble();
    }
    if (family < 0)
      family = -(family);
    // asking for a custom rate
    System.out.print("what is your pay rate?\n");
    pay = hours.nextDouble();
    hours.close();
    // payroll text layout seperate from calculations for ease of use in
    // methods
    System.out.print("Payroll Stub\n\n\n");
    System.out.print("Hours you worked:  " + time + "\n");
    System.out.print("Your current rate: " + pay + " $/hr \n");
    System.out.print(String.format("Your Total pay:    $ %.2f\n\n", payroll.grosspay(time, pay)));
    System.out.print(payroll.expenses(payroll.grosspay(time, pay), family));
    System.out.print("thank you for loging your hours with us!!\n\n");

  }

  public Double grosspay(double time, double pay) {
    // the raw calcuated payout
    double grosspay;
    if (time > 40) {
      double overtime = time - 40;
      grosspay = (time - overtime) * pay;
      grosspay += (1.5 * overtime * pay);
    } else
      grosspay = pay * time;
    return grosspay;
  }

  public String expenses(double money, int child) {
    double SS = money * .06;
    double FedIn = money * .14;
    double StateIn = money * .05;
    double union = 10.00;
    double Ins;
    if (child >= 3)
      Ins = 35.00;
    else
      Ins = 15.00;
    // validation for too little funds and if so, the output of the function
    // is switched to a different formatted string along with a dedicated
    // output
    if (money < 60) {
      double total = money - SS - FedIn - StateIn;
      System.out.print(String.format(
          "Expenses:\n\nSS:                $ %.2f\nFedIn:             $ %.2f\nStateIn:           $ %.2f\n\nnet:               $ %.2f\n\n\n",
          SS, FedIn, StateIn, total));
      return String.format("You still owe:\n\nUnion:             $ %.2f\nIns:               $ %.2f\n\n", union, Ins);
    } else {
      double total = money - SS - FedIn - StateIn - union - Ins;
      return String.format(
          "Expenses:\n\nSS:                $ %.2f\nFedIn:             $ %.2f\nStateIn:           $ %.2f\nUnion:             $ %.2f\nIns:               $ %.2f\n\nNet:               $ %.2f\n\n\n",
          SS, FedIn, StateIn, union, Ins, total);
    }
  }
}