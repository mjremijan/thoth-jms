package org.thoth.jms.consumer.history;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Named(value = "helloWorldMessageHistory")
@ApplicationScoped
public class HelloWorldMessageHistory {
    protected LinkedList<String> queue;

    @PostConstruct
    public void postConstruct() {
        queue = new LinkedList<>();
    }

    public void add(String s) {
        queue.add(0, s);
    }

    public List<String> getHistory() {
        return queue;
    }
}
