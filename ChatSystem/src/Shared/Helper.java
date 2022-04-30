package Shared;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Server.Server;

public class Helper {
	
	public static String removeFirstChar(String str){ return str.substring(1); }
	public static String removeLastCharacter(String str) { return str.substring(0, str.length() - 1); }
	public static String RemoveSpaces(String str) {		
        while (str.startsWith(" ")) { str = removeFirstChar(str); }
        while (str.endsWith(" ")) { str = removeLastCharacter(str); }
		return str;
	}
	
	public static int HowManyInString(String str, char chr) {
		int count = (int) str.chars().filter(ch -> ch == chr).count();
		System.out.println(count);
		return count;
	}
	
	public static int HowManyCharInString(String str) {
		int count = str.length();;
		System.out.println(count);
		return count;
	}
	
	public static Boolean ValidUsername(String str) {
		return str.matches("^[a-zA-Z0-9_]*$") && str.length() <= 4 && str.length() > 0 && HowManyInString(str,'_') <= 1;
	}
	
}
