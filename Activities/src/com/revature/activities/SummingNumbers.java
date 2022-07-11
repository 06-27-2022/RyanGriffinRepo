package com.revature.activities;

public class SummingNumbers {
	
	int counter;
	static int[] arr = {};
	
	static int addArray() {
		int counter = 0;
		int i;
		for(i = 0; i < arr.length; i++) 
			counter += arr[i];
		return counter;
		}
	
	
	public static void main(String[] args) {
		System.out.println("The sum of the array is " + addArray() + "." );
	}
	}