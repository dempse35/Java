/**
 * 
 * @author james
 *
 */
public class StandardLogger implements Logger {
	private Level level;
   
	public StandardLogger() {
		this.level = Level.WARN;
	}
	
	public StandardLogger(Level level) {
		this.level = level;
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
			 System.out.println(text);
		 }
	}

	@Override
	public void debug(String text) {
		if (level == Level.INFO || level == Level.DEBUG) {
			System.out.println(text);
		}
	}

	@Override
	public void warn(String text) {
		if (level == Level.INFO || level == Level.DEBUG || level == Level.WARN) {
			System.out.println(text);
		}
	}

	@Override
	public void error(String text) {
		 System.out.println(text);	
	}
	
	

}
