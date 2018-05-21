package week1;

public class testRenter {

	public static void main(String[] args) {
		Renter[] renters = new Renter[2];
		renters[0] = new Renter("James", "Dempsey", "6486 Poinsetta St SW", "Grandville MI 49418", 200.00);
		renters[1] = new Renter("Anthony", "Haluska", "11501 Shanty Creek Lane", "Allendale MI 49401", 200.00);
		for (int x = 0; x < renters.length; x ++) {
			renters[x].printLabel();
			System.out.println();
		}


	}

}
