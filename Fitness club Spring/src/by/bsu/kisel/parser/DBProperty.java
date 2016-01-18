/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.parser;

/**
 * Business Object for database configuration parameters. 
 * @author Anastasiya Kisel
 */
public class DBProperty {
    private String user;
    private String password;
    private String driver;
    private String url;
    /**
     * Gets driver.
     * @return driver
     */
    public String getDriver() {
        return driver;
    }
    /**
     * Sets driver.
     * @param driver
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }
    /**
     * Gets password of database user.
     * @return password of database user
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets password of database user.
     * @param password - password of database user
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Gets url.
     * @return url
     */
    public String getUrl() {
        return url;
    }
    /**
     * Sets url.
     * @param url - url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * Gets the user.
     * @return user
     */
    public String getUser() {
        return user;
    }
    /**
     * Sets the user.
     * @param user - user
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * Overriden toString() method.
     */
    @Override
    public String toString() {
        return getClass().getName() + " [ User:" + user  + ", password"+password+
                                        ", driver"+driver+
                                        ", url"+url+"]";
    }
}
