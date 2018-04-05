package org.thoth.jms.producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
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

    @Resource(lookup = "java:module/jms/radio-tide")
    protected ConnectionFactory factory;

    @Resource(lookup = "java:module/jms/herald-scarecrow")
    protected Queue queue;

    public String send() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String txt = String.format("Hello world message %s", sdf.format(new Date()));
        try (
            Connection connection = factory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
        ) {
            TextMessage message = session.createTextMessage();
            message.setText(txt);
            producer.send(message);
        } catch (Exception e) {
            txt = "Exception sending message: " + e.getMessage();
        }
        return txt;
    }
}
