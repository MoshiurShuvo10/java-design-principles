package chapter.one.modularity;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BankProgramDemo1 {
    // this hashmap will contain the account number as key and balance as value
    private HashMap<Integer, Integer> acctDetails = new HashMap<>() ;

    private double rate = 0.01 ;
    private int nextAcct = 0 ;
    private int currentAcct = -1 ;
    private Scanner scanner ;
    private boolean done = false ;

    public static void main(String[] args) {
        BankProgramDemo1 bankProgramDemo1 = new BankProgramDemo1() ;
        bankProgramDemo1.run();
    }

    private boolean isValidAccount(int acctNumber){
        return acctDetails.containsKey(acctNumber) ;
    }

    private void run() {
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
        Set<Integer> accounts = acctDetails.keySet();
        for(Integer account : accounts) {
            int currentBalance = acctDetails.get(account);
            int newBalance = (int) (currentBalance * (1+rate)) ;
            acctDetails.put(account, newBalance) ;
        }
    }

    private void showAll() {
        Set<Integer> accounts = acctDetails.keySet() ;
        System.out.println("Bank has total "+accounts.size()+" accounts");
        for(Integer account : accounts)
            System.out.println("\tAccount: "+account+"\tBalance: "+acctDetails.get(account));
    }

    private void authorizeLoan() {
        System.out.println("Enter loan amount: ");
        int loanAmount = scanner.nextInt() ;
        int currentAmount = acctDetails.get(currentAcct) ;
        if(currentAmount >= loanAmount/2)
            System.out.println("Loan authorized");
        else
            System.out.println("Loan denied");
    }

    private void deposit() {
        if(acctDetails.isEmpty()) {
            System.out.println("You have no account in our bank. Please create a new account to deposit.");
            return ;
        }
        System.out.println("Enter the amount of deposit: ");
        int deposit = scanner.nextInt() ;
        System.out.println("Enter the account in which you want to deposit: ");
        int chosenAccountToDeposit = scanner.nextInt() ;
        if(isValidAccount(chosenAccountToDeposit)) {
            int balance = acctDetails.get(chosenAccountToDeposit) ;
            acctDetails.put(chosenAccountToDeposit, balance+deposit) ;
        }
        else
            System.out.println("Please select a valid account to deposit.");
    }

    private void select() {
        System.out.println("Enter which account details do you want to see#: ");
        currentAcct = scanner.nextInt() ;
        Set<Integer> currentAccountSet = acctDetails.keySet() ;
        if(currentAccountSet.contains(currentAcct)){
            int balance = acctDetails.get(currentAcct);
            System.out.println("AcctNum: "+currentAcct+"\tBalance: "+balance);
        }
        else
            System.out.println("Sorry!! Account number doesn't exits in our system");
    }

    private void newAccount() {
        currentAcct = nextAcct++ ;
        acctDetails.put(currentAcct, 0) ;
        System.out.println("New account number: "+currentAcct);
    }

    private void quit() {
        System.out.println("Goodbye!");
        done = true ;
    }
    
    
}
