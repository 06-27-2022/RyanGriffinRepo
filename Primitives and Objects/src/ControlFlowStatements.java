public class ControlFlowStatements{
	
	public static void main(String[] args) {
		/*
		 * decision statements
		 * : if statements, else if, switch
		 * 
		 * 
		 * 
		 * looping statement
		 * : for, while. do while, enhanced for loop
		 * 
		 */
		
		//Decision statement
		//if statement:
		String day = "Wednesday";
		
		if (day == "Tuesday") {
			System.out.println(day);
		} else if (day == "Wednesday") {
			System.out.println(day);
		} else if (day == "Thursday") {
			System.out.println(day);
		} else if (day == "Friday") {
			System.out.println(day);
		} else {
			System.out.println("It's another day.");
		}
		
		//Switch statements:
		int num = 3;
		switch(num) {
		case 1:
			//if num == 1
			System.out.println("Inside case 1: ");
			System.out.println(num);
			break;
			//the break statement terminates the enclosing switch statement
			//if you don't have the break statement
			//the statements in the switch block will "fall through"
			//fall through = will print matching case + every case after
		case 2:
			//if num == 2
			System.out.println("Inside case 2: ");
			System.out.println(num);
			break;
		case 3:
			//if num == 3
			System.out.println("Inside case 3: ");
			System.out.println(num);
			break;
		default:
			System.out.println("Inside my default: ");
			System.out.println(num);
		}
		
		
		//looping statements:
		//for, while, do while, for each (enhanced loop)
		
		int numOfTimes = 11;
		
		for (int i = 0; i < numOfTimes; i++) {
			System.out.println(i);
		}
		
		int result = 0;
		result++; //result = result + 1
		
		//while loop:
		//while the condition is true, execute the code in the block
		
		int whileExample = 0;
		boolean keepGoing = false;
		while(whileExample < 10) {
			System.out.println(whileExample);
			whileExample++;
			
		if (whileExample == 10) {
			keepGoing = false;
			//break;
		}
		System.out.println("End of while block");
		}
		
		
		//do while
		boolean keepDoing = true;
		do {
			System.out.println("Hello do while!");
		} while (!keepDoing); 
		
		
		//enhanced for loop (for each)
		//use this loop to iterate/traverse through data structures
		//like arrays or collections
		
		int[] numberArray = {1, 2, 3, 4, 10, 35, 16};
		//in an enhanced for loop, we don't need to update the loop variable
		for (int numb: numberArray) {
			System.out.println(numb);
		}
	}
}