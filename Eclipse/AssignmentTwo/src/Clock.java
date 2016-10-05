/**
 * @author james dempsey
 */
public class Clock {

	private int hours;
	private int minutes;
	private int seconds;

	public Clock() {
		this.setMilitaryHour(0);
		this.setMinute(0);
		this.setSecond(0);
	}

	public Clock(int hours, int minutes, int seconds) {
		this.setMilitaryHour(hours);
		this.setMinute(minutes);
		this.setSecond(seconds);
	}

	public void setMilitaryHour(int hours) {
		this.hours = Math.abs(hours % 24);
	}

	public void setMinute(int minutes) {
		this.minutes = Math.abs(minutes % 60);
	}

	public void setSecond(int seconds) {
		this.seconds = Math.abs(seconds % 60);
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	/**
	 * Pad the values less than ten with zeroes on the left
	 *
	 * @param value
	 *            integer to pad with zeroes if necessary
	 *
	 * @return zero padded string of the value parameter
	 */
	private String zeroPad(int value) {
		return String.format("%02d", value);
	}

	public String toString() {
		return zeroPad(getHours()) + ":" + zeroPad(getMinutes()) + ":" + zeroPad(getSeconds());
	}

	public static void main(String[] args) {
		Clock zero = new Clock();
		System.out.println(zero.toString());
		Clock exampleA = new Clock(0, 94, 56);
		System.out.println(exampleA.toString());
		Clock exampleB = new Clock(14, 63, 64);
		System.out.println(exampleB.toString());
		Clock exampleC = new Clock(98, 76, -64);
		System.out.println(exampleC.toString());
		Clock exampleD = new Clock(5, 8, 1);
		System.out.println(exampleD.toString());
		Clock exampleE = new Clock(23, 59, 59);
		System.out.println(exampleE.toString());
	}
}
