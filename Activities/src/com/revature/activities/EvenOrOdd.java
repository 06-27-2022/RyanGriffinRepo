
package com.revature.activities;

import java.util.Scanner;

public class EvenOrOdd {

	public static void main(String[] args) {
		
		System.out.print("Enter a number to discover if it's even or odd: ");
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		
		if(number % 2 == 0) {
		System.out.println("Even");
	} else {
		System.out.println("Odd");
	}
		scan.close();
	}
}

