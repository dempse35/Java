/**
 * 
 * @author james
 *
 */
public interface Logger {
	public Level getLevel(); 
	
	public void setLevel(Level level);
	
	/**
	 * The info method prints the message when the level is set to INFO
	 * @param text 
	 * @return 
	 */
	public void info(String text);
	
	/**
	 * The debug method prints the message when the level is INFO, or DEBUG
	 * @param text
	 * @return
	 */
	public void debug(String text);
	
	/**
	 * The warn method prints the message when the level is INFO, DEBUG or WARN
	 * @param text
	 * @return
	 */
	public void warn(String text);
	
	/**
	 * The error method always prints the message when called
	 * @param text
	 * @return
	 */
	public void error(String text);
	
	
}
