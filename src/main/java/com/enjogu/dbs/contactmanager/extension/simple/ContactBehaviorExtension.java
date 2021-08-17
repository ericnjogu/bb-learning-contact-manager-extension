package com.enjogu.dbs.contactmanager.extension.simple;

import com.backbase.buildingblocks.backend.communication.extension.annotations.BehaviorExtension;
import com.backbase.buildingblocks.backend.communication.extension.annotations.PostHook;
import com.backbase.buildingblocks.backend.communication.extension.annotations.PreHook;
import com.backbase.dbs.contactmanager.contact.route.ListContactRouteBuilder;
import com.backbase.dbs.contactmanager.contact.service.ContactListContainer;
import org.apache.camel.Exchange;
import org.springframework.core.annotation.Order;

/**
 * An example of how to provide a behavior extension using annotations.
 */
@BehaviorExtension(
    // The name parameter is used as a configuration key to enable/disable this specific extension.
    // For example, "backbase.behavior-extensions.example-behavior.enabled=false".
    // (Extensions are enabled by default.)
    name = "contact-add-description-behavior",
    // The routeId parameter is the value returned by the getRouteId() method of the target SimpleExtensibleRouteBuilder
    // and is typically exposed as a constant by that route builder.  E.g.:
    routeId = ListContactRouteBuilder.ROUTE_ID
)
@Order(1)
public class ContactBehaviorExtension {

    @PostHook
    public void postHook(ContactListContainer container) {
        container.getElements().stream().forEach(
            contact -> contact.withAddition("description", contact.toString())
        );
    }

}
