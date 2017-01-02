
/**
 * @author james
 *
 */
public class TestLoggers {
	private static void testLogging(Logger logger) {
		logger.error("The level is set to " + logger.getLevel());
        logger.info("This is an info message");
        logger.debug("This is a debug message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.warn("-----------------------");
        logger.warn("");
    }
	public static void main(String... args) {
		Logger logger = new StandardLogger();
		testLogging(logger);
		logger.setLevel(Level.INFO); 
        testLogging(logger);
        logger = new DetailLogger();
        testLogging(logger);
        logger.setLevel(Level.INFO); 
        testLogging(logger);
	}

}
