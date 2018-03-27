package org.thoth.jms.producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Stateless
public class HelloWorldMessageSender {

    @Inject
    @JMSConnectionFactory("jms/HelloWorldConnectionFactory")
    protected JMSContext context;

    @Resource(lookup = "jms/HelloWorldQueue")
    protected Queue queue;

    public String send() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String txt = String.format("Hello world message %s", sdf.format(new Date()));
        if (context != null) {
            System.out.printf("Use JMSContext to produce%n");
            context.createProducer().send(queue, txt);
        }
        else {
            throw new RuntimeException("NO CONNECTION FACTORY. MESSAGE NOT PRODUCED!");
        }
        return txt;
    }
}
