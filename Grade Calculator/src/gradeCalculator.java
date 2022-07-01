public class gradeCalculator{
	
	public static void main(String[] args) {
		
		
		int[] arr = {95, 85, 62, 40, 79};
		String[] subjects = new String[] {"Math: ","English: ", "Science: ", "History: ", "Art: "};
		
		for(int i = 0; i < arr.length; i++) {	
			
		if (arr[i] <= 100 && arr[i] >= 90) {
			System.out.println(subjects[i] + "A");
		} else if (arr[i] <= 89 && arr[i] >= 80) {
			System.out.println(subjects[i] + "B");
		} else if (arr[i] <= 79 && arr[i] >= 70) {
			System.out.println(subjects[i] + "C");
		} else if (arr[i] <= 69 && arr[i] >= 60) {
			System.out.println(subjects[i] + "D");
		} else {
			System.out.println(subjects[i] + "F");
		}
	}
}
}