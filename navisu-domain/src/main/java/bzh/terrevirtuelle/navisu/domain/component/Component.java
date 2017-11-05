/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.component;

import java.util.List;

/**
 *
 * @author serge
 * @date Nov 5, 2017
 */
public class Component {

    private String name;

    private String implementation;

    private String state;

    private List<String> servicesProvided;

    private List<String> eventsProvided;

    private List<String> usedServices;

    private List<String> consumedEvents;

    private List<String> usedEventsSubscribe;

    private Component subComponent;

    private List<Component> subComponents;

    private String item;

        private int level;

    /**
     * Get the value of level
     *
     * @return the value of level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the value of level
     *
     * @param level new value of level
     */
    public void setLevel(int level) {
        this.level = level;
    }


    

    /**
     * Get the value of item
     *
     * @return the value of item
     */
    public String getItem() {
        return item;
    }

    /**
     * Set the value of item
     *
     * @param item new value of item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Get the value of subComponent
     *
     * @return the value of subComponents
     */
    public List<Component> getSubComponents() {
        return subComponents;
    }

    /**
     * Set the value of subComponent
     *
     * @param subComponent new value of subComponent
     */
    public void addSubComponent(Component subComponent) {
        this.subComponents.add(subComponent);
    }

    /**
     * Get the value of usedEventsSubscribe
     *
     * @return the value of usedEventsSubscribe
     */
    public List<String> getUsedEventsSubscribe() {
        return usedEventsSubscribe;
    }

    public void addUsedEventSubscribe(String eventSubscribe) {
        usedEventsSubscribe.add(eventSubscribe);
    }

    /**
     * Set the value of usedEventsSubscribe
     *
     * @param usedEventsSubscribe new value of usedEventsSubscribe
     */
    public void setUsedEventsSubscribe(List<String> usedEventsSubscribe) {
        this.usedEventsSubscribe = usedEventsSubscribe;
    }

    /**
     * Get the value of consumedEvents
     *
     * @return the value of consumedEvents
     */
    public List<String> getConsumedEvents() {
        return consumedEvents;
    }

    /**
     * Set the value of consumedEvents
     *
     * @param consumedEvents new value of consumedEvents
     */
    public void setConsumedEvents(List<String> consumedEvents) {
        this.consumedEvents = consumedEvents;
    }

    public void addConsumedEvents(String consumedEvent) {
        consumedEvents.add(consumedEvent);
    }

    /**
     * Get the value of usedServices
     *
     * @return the value of usedServices
     */
    public List<String> getUsedServices() {
        return usedServices;
    }

    /**
     * Set the value of usedServices
     *
     * @param usedServices new value of usedServices
     */
    public void setUsedServices(List<String> usedServices) {
        this.usedServices = usedServices;
    }

    /**
     * Get the value of eventProvided
     *
     * @return the value of eventProvided
     */
    public List<String> getEventsProvided() {
        return eventsProvided;
    }

    /**
     * Set the value of eventProvided
     *
     * @param eventProvided new value of eventProvided
     */
    public void setEventsProvided(List<String> eventProvided) {
        this.eventsProvided = eventProvided;
    }

    public void addEventProvided(String service) {
        eventsProvided.add(service);
    }

    /**
     * Get the value of serviceProvided
     *
     * @return the value of serviceProvided
     */
    public List<String> getServicesProvided() {
        return servicesProvided;
    }

    /**
     * Set the value of serviceProvided
     *
     * @param servicesProvided new value of serviceProvided
     */
    public void setServicesProvided(List<String> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    public void addServiceProvided(String service) {
        servicesProvided.add(service);
    }

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get the value of implementation
     *
     * @return the value of implementation
     */
    public String getImplementation() {
        return implementation;
    }

    /**
     * Set the value of implementation
     *
     * @param implementation new value of implementation
     */
    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}
