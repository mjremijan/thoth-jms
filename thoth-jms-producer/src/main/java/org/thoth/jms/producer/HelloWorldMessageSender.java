package org.thoth.jms.producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Stateless
public class HelloWorldMessageSender {

    @Inject
    @JMSConnectionFactory("jms/HelloWorldConnectionFactory")
    protected JMSContext context;

    @Resource(lookup = "jms/HelloWorldConnectionFactory")
    protected ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/HelloWorldQueue")
    protected Queue queue;

    public String send() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String txt = String.format("Hello world message %s", sdf.format(new Date()));
        if (context != null) {
            System.out.printf("Use JMSContext to produce%n");
            context.createProducer().send(queue, txt);
        }
        else
        if (connectionFactory != null) {
            System.out.printf("Use ConnectionFactory to produce%n");
            try (
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(queue);
            ) {
                TextMessage message = session.createTextMessage();
                message.setText(txt);
                producer.send(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new RuntimeException("NO CONNECTION FACTORY. MESSAGE NOT PRODUCED!");
        }
        return txt;
    }
}
