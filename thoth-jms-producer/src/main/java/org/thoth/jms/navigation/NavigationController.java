package org.thoth.jms.navigation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Named(value = "navigationController")
@ApplicationScoped
public class NavigationController {

    public NavigationController() {
    }

    public String navigate() {
        return "navigated";
    }

}
