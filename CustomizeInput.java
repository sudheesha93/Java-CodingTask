package walmart.code;

import java.util.Arrays;

class CustomizeString{
	
	public String customSort(String s) {
		
		char [] charArray=s.toCharArray();
		String lower = "";
		String upper = "";
		String num = "";
		
		for (char c : charArray) {
			if (Character.isDigit(c))
				num += c;
			else if (Character.isLowerCase(c))
				lower += c;
			else if(Character.isUpperCase(c))
				upper += c;
		}
		lower = sort(lower);
		upper = sort(upper);
		num = sort(num);
		

		int li = 0, ui = 0, ni = 0;
		String ans = "";
		
		for (char c : charArray) {
			if (Character.isDigit(c))
				ans += num.charAt(ni++);
			else if (Character.isLowerCase(c))
				ans += lower.charAt(li++);
			else
				ans += upper.charAt(ui++);
		}
		return ans;
	}

	public String sort(String inputString) {
		char tempArray[] = inputString.toCharArray();

		Arrays.sort(tempArray);

		return new String(tempArray);
	}
}


public class CustomizeInput{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomizeString cs= new CustomizeString();
		System.out.println(cs.customSort("89aDcQAb51"));
		System.out.println(cs.customSort("62uHwLowIT92"));
	}

}
