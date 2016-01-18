/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.parser;

/**
 *
 * @author Kisel Anastasia
 */
public class DBProperty {
    private String user;
    private String password;
    private String driver;
    private String url;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return getClass().getName() + " [ User:" + user  + ", password"+password+
                                        ", driver"+driver+
                                        ", url"+url+"]";
    }
}
