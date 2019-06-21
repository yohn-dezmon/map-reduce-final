package stubs;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexPractice {

	public static void main(String[] args) {
		// 2015-01-10 00:00:00  1ea7fc17-7cf0-486d-8b8b-ad905e0d7a7a    PaiGow  Club    7
		
		String line = "2015-01-10 00:00:00  1ea7fc17-7cf0-486d-8b8b-ad905e0d7a7a    PaiGow  Club    7";
		
		
		Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})\\s*(\\S*)\\s*(\\S*)\\s*(\\w*)\\s*(\\w*)\\s*(\\d*)");
		Matcher m = pattern.matcher(line);
		
		if (m.matches()) {
			
			String date = m.group(1);
			System.out.println(date);
			String time = m.group(2);
			System.out.println(time);
			String id = m.group(3);
			System.out.println(id);
			String game = m.group(4);
			System.out.println(game);
			String suit = m.group(5);
			System.out.println(suit);
			String value = m.group(6);
			System.out.println(value);
			
		}
		

	}

}
