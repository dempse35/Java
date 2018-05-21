package week1;

public class Renter {
	private String fName = "";
	private String lName = "";
	private String address = "";
	private String cityStateZip = "";
	private double rent = 0.0;
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityStateZip() {
		return cityStateZip;
	}

	public void setCityStateZip(String cityStateZip) {
		this.cityStateZip = cityStateZip;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public Renter() {
	}
	
	public Renter(String fName, String lName, String address,
			String cityStateZip, double rent) {
		this.fName=fName;
		this.lName=lName;
		this.address=address;
		this.cityStateZip=cityStateZip;
		this.rent=rent;
		
	}
	public void printLabel() {
		System.out.println(fName + " " + lName);
		System.out.println(address);
		System.out.println(cityStateZip);
		
	}
	
}
