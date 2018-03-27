package org.thoth.jms.producer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Named(value = "helloWorldMessageController")
@RequestScoped
public class HelloWorldMessageController {

    @Inject
    protected HelloWorldMessageSender sender;

    @Inject
    protected HelloWorldMessageBean bean;

    public String getSimpleName() {
        return this.getClass().getSimpleName();
    }

    public String produce() {
        String message
            = sender.send();
        bean.setMessage(message);
        return "produced";
    }

}
