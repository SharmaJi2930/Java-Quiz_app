package com.quiz;

import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
    	UserCRUD userCrud = new UserCRUD();
    	Scanner sc = new Scanner(System.in);
    	int choice;
    	
    	do {
        System.out.println("===Welcome to Quiz Application===");
        System.out.println("""
                1) Create an account\s
                2) LogIn\s
                3) Exit""");
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        
        switch(choice)
        {
        case 1:
        	userCrud.createUser();
        	break;
        	
        case 2:
        	userCrud.logIn();
        	break;
        	
        case 3:
        	System.out.println("Thank you for visiting!!");
        	System.exit(0);
        	break;
        	
        default:
        	System.out.println("Wrong Choice");
        }
    	}while(true);
    }
}
