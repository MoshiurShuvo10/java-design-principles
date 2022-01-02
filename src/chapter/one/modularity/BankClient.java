package chapter.one.modularity;

import java.util.Scanner;

// handles I/O, current account and am-I-done flag
public class BankClient {
    private int currentAccount = -1 ;
    private Scanner scanner = new Scanner(System.in) ;
    private boolean done = false ;
    private Bank bank = new Bank() ;

    public void run() {
        scanner = new Scanner(System.in);
        while(!done) {
            System.out.println("Enter command (0=quit, 1=new, 2=select, 3=deposit, 4=loan, 5=show, 6=interest): )");
            int command = scanner.nextInt() ;
            processCommand(command) ;
        }
        scanner.close();
    }

    private void processCommand(int command) {
        if(command == 0) quit();
        else if(command == 1) newAccount();
        else if(command == 2) select();
        else if(command == 3) deposit();
        else if(command == 4) authorizeLoan();
        else if(command == 5) showAll();
        else if(command == 6) addInterest();
        else System.out.println("Invalid Command");
    }

    private void addInterest() {
        bank.addInterest();
    }

    private void showAll() {
        System.out.println(bank.toString());
    }

    private void authorizeLoan() {
        System.out.println("Enter loan amount#: ");
        int loanAmount = scanner.nextInt();
        if(bank.authorizeLoan(currentAccount, loanAmount))
            System.out.println("Loan is authorized");
        else System.out.println("Loan is denied due to low balance");
    }

    private void deposit() {
        System.out.println("Enter deposit amount#: ");
        int depositAmount = scanner.nextInt() ;
        bank.deposit(currentAccount, depositAmount);
    }

    // switch to any account number and display the balance of that account number.
    private void select() {
        System.out.println("Enter account number#: ");
        currentAccount = scanner.nextInt() ;
        int balance = bank.getBalance(currentAccount) ;
        System.out.println("Account Number: "+ currentAccount + " ==> Balance: "+balance);
    }

    private void newAccount() {
        currentAccount = bank.newAccount() ;
        System.out.println("New account number created:# "+currentAccount);
    }

    private void quit() {
        System.out.println("Goodbye!");
        done = true ;
    }
}
