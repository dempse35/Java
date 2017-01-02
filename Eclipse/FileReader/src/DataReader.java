import java.io.*;

public class DataReader {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			FileReader fr = new FileReader("./src/data.txt");
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter("./src/processed.txt");
		    BufferedWriter bw = new BufferedWriter(fw);

			String strLine;
			try {
				while ((strLine = br.readLine()) != null) {
					bw.write(strLine.toUpperCase());
					bw.newLine();
				}
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

