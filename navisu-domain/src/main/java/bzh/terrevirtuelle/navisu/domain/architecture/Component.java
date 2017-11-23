/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.architecture;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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

    private Component root;

    private List<Component> subComponents;

    private String item;

    private int level;

    private String module;

    private String id;

    

    public Component(String module, String name, String implementation, String state, Component root, String item, int level) {
        this.module = module;
        this.name = name;
        this.implementation = implementation;
        this.state = state;
        this.root = root;
        this.item = item;
        this.level = level;
        servicesProvided = new ArrayList<>();
        eventsProvided = new ArrayList<>();
        usedServices = new ArrayList<>();
        consumedEvents = new ArrayList<>();
        subComponents = new ArrayList<>();
    }

    public Component(String name, String implementation, String state, String item, int level) {
        this(null, name, implementation, state, null, item, level);
    }

    public Component(String name, String implementation, String state, int level) {
        this(null, name, implementation, state, null, null, level);
    }

    public Component(String name) {
        this(null, name, null, null, null, null, 0);

    }

    /**
     * Get the value of module
     *
     * @return the value of module
     */
    public String getModule() {
        return module;
    }

    /**
     * Set the value of module
     *
     * @param module new value of module
     */
    public void setModule(String module) {
        this.module = module;
    }

    public boolean isServiceProvider(String service) {
        return servicesProvided.contains(service);
    }

    public boolean isServiceConsumer(String service) {
        return usedServices.contains(service);
    }

    public boolean isEventProvider(String event) {
        return eventsProvided.contains(event);
    }

    public boolean isEventConsumer(String event) {
        return consumedEvents.contains(event);
    }

    public boolean isSubComponent(Component component) {
        return root.equals(component);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.implementation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Component other = (Component) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.implementation, other.implementation)) {
            return false;
        }
        return true;
    }

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

    public void addConsumedEvent(String consumedEvent) {
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

    public void addUsedService(String service) {
        usedServices.add(service);
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

    @Override
    public String toString() {
        return "Component{" + "name=" + getShortName(name) + ", implementation=" + getShortName(implementation)
                + ", state=" + state + ", servicesProvided=" + servicesProvided
                + ", eventsProvided=" + eventsProvided + ", usedServices="
                + usedServices + ", consumedEvents=" + consumedEvents
                + ", root=" + root + ", subComponents=" + subComponents
                + ", item=" + item + ", level=" + level + '}';
    }

    public String getShortName(String name) {
        String[] name0 = name.split(Pattern.quote("."));
        return name0[name0.length-1].trim();
    }
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }
}
