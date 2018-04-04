package org.thoth.jms.consumer.onmessage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.thoth.jms.consumer.history.HelloWorldMessageHistory;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "jms/HelloWorldQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class HelloWorldMessageConsumer implements MessageListener {

    @Resource
    protected MessageDrivenContext mdc;

    @Inject
    protected HelloWorldMessageHistory history;

    @PostConstruct
    protected void postConstruct() {
        System.out.printf("MessageListener ready! \"class=%s\"%n", getClass().getName());
    }

    @PreDestroy
    protected void preDestroy() {
        System.out.printf("MessageListener gone! \"class=%s\"%n", getClass().getName());
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String txt
                    = message.getBody(String.class);
                System.out.printf(
                      "HelloWorldMessageConsumer: TextMessage received: \"%s\"%n"
                    , txt
                );
                history.add(txt);
            } else {
                System.out.printf(
                    "HelloWorldMessageConsumer: %s received: \"%s\"%n"
                    , message.getClass().getName()
                    , String.valueOf(message)
                );
            }
        } catch (JMSException e) {
            e.printStackTrace(System.out);
        }
    }

}
