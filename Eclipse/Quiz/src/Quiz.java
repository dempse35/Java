import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;

public class Quiz {

	public static void main(String[] args) {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("What is the Square root of 4?", "2");
		hmap.put("What is the Square root of 9?","3");
		hmap.put("What is the Square root of 16?", "4");
		hmap.put("What is the Square root of 25?", "5");
	
		Set<String> keys = hmap.keySet();
		
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String question = it.next();
			String answer = JOptionPane.showInputDialog(question);
			String value = hmap.get(question);
			if (answer.equals(value)) {
				JOptionPane.showMessageDialog(null, "Correct");
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect, the correct answer is " + value);
			}	
		}
	}

}
