package by.bsu.kisel.manager;
/**
 *
 * @author Kisel Anastasia
 */

public class PathManager {

    private static PathManager instance;
    public static final String ROOT = "/";
    public static String ABSOLUTE_PATH = "/";
    public static final String WEB_INF = "WEB-INF";

    public synchronized static PathManager getInstance() {
        if (instance == null) {
            instance = new PathManager();
        }
        return instance;
    }

    public static void setAbsolutePath(String path) {
        ABSOLUTE_PATH = path;
    }
}
