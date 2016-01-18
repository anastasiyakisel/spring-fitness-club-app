package by.bsu.kisel.manager;
/**
 * This class implements the custom PathManager.
 * @author Anastasiya Kisel
 */
public class PathManager {

    private static PathManager instance;
    public static final String ROOT = "/";
    public static String ABSOLUTE_PATH = "/";
    public static final String WEB_INF = "WEB-INF";
	/**
	 * Gets PathManager instance itself.
	 * @return PathManager instance
	 */
    public synchronized static PathManager getInstance() {
        if (instance == null) {
            instance = new PathManager();
        }
        return instance;
    }
	/**
	 * Sets absolute path to the file to its' specified value.
	 * @param path - absolute path
	 */
    public static void setAbsolutePath(String path) {
        ABSOLUTE_PATH = path;
    }
}
