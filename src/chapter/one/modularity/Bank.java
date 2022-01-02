package chapter.one.modularity;

import java.util.HashMap;
import java.util.Set;

// this class will hold only banking info
public class Bank {
    private HashMap<Integer, Integer> acctDetails = new HashMap<>() ;
    private double rate = 0.01 ;
    private int nextAcct = 0 ;

    // this method doesn't print any account info to the console. It just returns the newly created account number and send
    // it to the bank client. Bank client will print the acct number.
    public int newAccount() {
        int acctNum = nextAcct++ ;
        acctDetails.put(acctNum,0) ;
        return acctNum ;
    }

    // BankClient will call this method to get the balance.
    public int getBalance(int acctNum) {
        return acctDetails.get(acctNum) ;
    }

    // deposit method won't ask the user about deposit amount.
    // the BankClient will pass the amount as argument.
    public void deposit(int acctNum, int amount) {
        int currentBalance = acctDetails.get(acctNum) ;
        acctDetails.put(acctNum, currentBalance+amount) ;
    }

    // this method is not responsible for handling the I/O operation.
    // It just returns the decision as a boolean.
    public boolean authorizeLoan(int acctNum, int loanAmount) {
        int currentAmount = acctDetails.get(acctNum) ;
        return currentAmount >= loanAmount/2 ;
    }


    public void addInterest() {
        Set<Integer> accounts = acctDetails.keySet() ;
        for(Integer account : accounts){
            int currentBalance = acctDetails.get(account) ;
            int newBalance = (int) (currentBalance * (1+rate)) ;
            acctDetails.put(account, newBalance) ;
        }
    }

    // In BankProgramDemo1, showAll() was responsible for printing all accounts info.
    // Here, we've decoupled the bank-specific portion to collect info into a String.
    @Override
    public String toString() {
        Set<Integer> accounts = acctDetails.keySet() ;
        if(accounts.size() == 0)
            return "Bank has no account. Please open account to view" ;
        String result = "This bank has "+accounts.size()+" accounts.\n" ;
        for(int account : accounts) {
            result += "Account Number: "+account+"\tBalance: "+acctDetails.get(account)+"\n" ;
        }
        return result ;
    }
}
