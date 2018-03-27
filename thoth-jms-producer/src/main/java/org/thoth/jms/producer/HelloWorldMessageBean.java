package org.thoth.jms.producer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Named(value = "helloWorldMessageBean")
@RequestScoped
public class HelloWorldMessageBean {

    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
