/**
 * @author james
 */
import java.util.Date;
public class DetailLogger implements Logger{
	private Level level;
	
	public DetailLogger() {
		this.level = Level.WARN;
	}
	public String formatString(String text, String lvl) {
		Date date = new Date();
		return  String.format("%-7s %s %s", "[" + lvl + "]", date.toString(), text);
	}

	@Override
	public Level getLevel() {
		return level;
	}

	@Override
	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public void info(String text) {
		 if (level == Level.INFO) {
			 System.out.println(formatString(text, "INFO"));
		 }
	}

	@Override
	public void debug(String text) {
		if (level == Level.INFO || level == Level.DEBUG) {
			System.out.println(formatString(text, "DEBUG"));
		}
	}

	@Override
	public void warn(String text) {
		if (level == Level.INFO || level == Level.DEBUG || level == Level.WARN) {
			System.out.println(formatString(text, "WARN"));
		}
	}

	@Override
	public void error(String text) {
		 System.out.println(formatString(text, "ERROR"));	
	}


}
