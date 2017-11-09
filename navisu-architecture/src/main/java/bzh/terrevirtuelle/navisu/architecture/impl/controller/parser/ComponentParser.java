/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;

import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author serge
 * @date Nov 9, 2017
 */
public class ComponentParser {

    private Handler handler;
    private List<Component> components;

    public ComponentParser(Handler handler) {
        this.handler = handler;
        components = new ArrayList<>();
    }

    public ComponentParser() {
        this(null);
    }

    public List<Component> parse(Handler handler, String content) {

        this.handler = handler;
        String[] tab0 = content.split("==+");
        List<String> contents = new ArrayList<>(tab0.length - 2);
        for (int i = 1; i < tab0.length - 2; i++) {
            if (!tab0[i].equals("\n\n")) {
                contents.add(tab0[i]);
            }
        }
        //System.out.println(contents);
        for (String c : contents) {
            String[] tab1 = c.split("--+");
            //Name
            String[] name0 = tab1[0].split(Pattern.quote("."));
            String[] name1 = name0[name0.length - 1].split(" ");
            Component component = new Component(name1[0].trim());
            //Level
            String[] l = name1[name1.length - 1].split(":");
            int level = Integer.parseInt((l[l.length - 1]).trim());
            component.setLevel(level);
            //Module
            for (int i = 0; i < name0.length; i++) {
                if (name0[i].equals("navisu")) {
                    component.setModule(name0[i + 1]);
                }
            }
            Map<String, List<String>> componentMap = new HashMap<>();
            String[] tab2 = tab1[1].split("\n");
            List<String> cmList = new ArrayList<>();
            for (String cm : tab2) {
                if (!cm.equals("  ") || !cm.equals("\n")) {
                    cmList.add(cm);
                }
            }
            cmList.remove(0);
            cmList.remove(2);
            // System.out.println(cmList);

            //Implementation
            String[] imp0 = cmList.get(0).split(" ");
            component.setImplementation(imp0[3]);

            //State
            String[] sta0 = cmList.get(1).split(" ");
            component.setState(sta0[2]);

            //Service provided
            String[] serv0 = cmList.get(3).split("interface");
            component.addServiceProvided(serv0[1].trim());

            //Event provided
            int i = 5;
            //  System.out.println("cmList.get(5)" + cmList.get(4));
            while (!cmList.get(i).contains("USED SERVICES")) {
                String[] evt = cmList.get(i).split("interface");
                component.addEventProvided(evt[1].trim());
                i++;
            }
            //  System.out.println(cmList.get(i));
            //Event used
            i++;
            while (!cmList.get(i).contains("CONSUMED EVENT")) {
                String[] evt = cmList.get(i).split("interface");
                component.addUsedService(evt[1].trim());
                i++;
            }
            i++;
            while (!cmList.get(i).contains("USED EVENT SUBSCRIBE")) {
                component.addConsumedEvent(cmList.get(i));
                i++;
            }
            components.add(component);
        }

        return components;

    }

    /**
     * Get the value of handler
     *
     * @return the value of handler
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Set the value of handler
     *
     * @param handler new value of handler
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    
}
