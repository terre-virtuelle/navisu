/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentParser;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.ComponentHandler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.model.Selection;
import bzh.terrevirtuelle.navisu.architecture.impl.view.ComponentView;
import bzh.terrevirtuelle.navisu.architecture.impl.view.SceneSupport;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author serge
 */
public class ComponentViewer {

    private VMDGraphScene graphScene;
    private final List<Component> components;
    private VMDPinWidget widget;
    private static int edgeID = 0;
    private Map<String, List<Component>> componentsMap;
    private boolean isSingleComponent = false;
    private Component singleComponent;

    public ComponentViewer(String filename) {
        Handler handler = new ComponentHandler();
        ComponentParser parser = new ComponentParser();
        String content = read(filename);
        components = parser.parse(handler, content);
        componentsMap = createComponentsMap(components);
    }

    public final String read(String fileName) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return content;
    }

    public final Map<String, List<Component>> createComponentsMap(List<Component> components) {
        Map<String, List<Component>> componentMap = new HashMap<>();
        Set<String> modules = new HashSet<>();
        components.forEach((c) -> {
            modules.add(c.getModule());
        });
        modules.forEach((m) -> {
            componentMap.put(m, new ArrayList<>());
        });
        components.forEach((c) -> {
            componentMap.get(c.getModule()).add(c);
        });
        return componentMap;
    }

    public Map<String, List<Component>> getComponentsMap() {
        return componentsMap;
    }

    public final void runScene(Selection selection) {
        graphScene = new VMDGraphScene();

        Map<String, List<ComponentView>> componentViewMap = new HashMap<>();
        Set<String> keys = componentsMap.keySet();

        //Creation de la map de view
        keys.forEach((k) -> {
            componentViewMap.put(k, new ArrayList<>());
        });

        List<String> keyList = selection.getModules();
        // Cas particulier pour un module
        // On cree un Set des composant out concernés pour les afficher

        if (keyList.isEmpty()) {
            isSingleComponent = true;
            String componentName = selection.getComponents().get(0);
            keys.forEach((k) -> {
                for (Component c : componentsMap.get(k)) {
                    if (c.getName().equals(componentName)) {
                        keyList.add(k);
                        singleComponent = c;
                    }
                }
            });
        }
        keyList.forEach((k) -> {
            // System.out.println("k : " + k);
            Set<Component> componentProviderServicesSet = new HashSet<>();
            if (isSingleComponent == false) {
                componentsMap.get(k).forEach((component) -> {
                    component.getUsedServices().forEach((n) -> {
                        components.forEach((c) -> {
                            c.getServicesProvided().forEach((nn) -> {
                              //  if (n.equals(nn) && !component.getModule().equals(c.getModule())) {
                                    if (n.equals(nn)) {
                                    componentProviderServicesSet.add(c);
                                }
                            });
                        });
                    });
                });
            } else {
                singleComponent.getUsedServices().forEach((n) -> {
                    components.forEach((c) -> {
                        c.getServicesProvided().forEach((nn) -> {
                          //  if (n.equals(nn) && !singleComponent.getModule().equals(c.getModule())) {
                                if (n.equals(nn) ) {
                                componentProviderServicesSet.add(c);
                            }
                        });
                    });
                });
            }
            // Affichage des view concernes
            // Affichage des services utilises
            int col = 0;
            int line = 0;
            createScene(graphScene, componentViewMap, componentProviderServicesSet, line, col);

            Set<Component> componentUserServicesSet = new HashSet<>();
            if (isSingleComponent == false) {
                componentsMap.get(k).forEach((component) -> {
                    componentUserServicesSet.add(component);
                });
            } else {
                componentUserServicesSet.add(singleComponent);
            }
            line += 100;
            col = 0;
            createScene(graphScene, componentViewMap, componentUserServicesSet, line, col);

            Set<Component> componentSet = new HashSet<>();
            componentSet.addAll(componentUserServicesSet);
            componentSet.addAll(componentProviderServicesSet);

            //  componentsMap.get(k).forEach((component) -> {
            componentSet.forEach((component) -> {
                component.getUsedServices().forEach((n) -> {
                    componentSet.forEach((c) -> {
                        c.getServicesProvided().forEach((nn) -> {
                            if (n.equals(nn) && component.getModule().equals(k)) {
                                createEdge(graphScene, component.getShortName(nn) + "_P", component.getName());
                            }
                        });
                    });
                });
            });
            graphScene.getActions().addAction(ActionFactory.createEditAction((Widget widget1) -> {
                graphScene.layoutScene();
            }));
        });
        SceneSupport.show(graphScene);
    }

    VMDPinWidget createPin(VMDGraphScene scene, String nodeID, String pinID, String name) {
        VMDPinWidget pinWidget = ((VMDPinWidget) scene.addPin(nodeID, pinID));
        pinWidget.setProperties(name, null);
        return pinWidget;
    }

    void createEdge(VMDGraphScene scene, String sourcePinID, String targetNodeID) {
        String edgeIDString = "edge" + ComponentViewer.edgeID++;
        scene.addEdge(edgeIDString);
        scene.setEdgeSource(edgeIDString, sourcePinID);
        scene.setEdgeTarget(edgeIDString, targetNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
    }

    void createScene(final VMDGraphScene scene,
            Map<String, List<ComponentView>> componentViewMap,
            Set<Component> components,
            int line, int col) {
        List<Widget> pinProviderWidget = new ArrayList<>();
        List<Widget> pinUsedWidget = new ArrayList<>();
        HashMap<String, List<Widget>> categories = new HashMap<>();

        for (Component c : components) {
            pinProviderWidget.clear();
            pinUsedWidget.clear();
            categories.clear();
            int x = col;
            int y = line;
            ComponentView componentView = new ComponentView(c, null, null, x, y);
            componentViewMap.get(c.getModule()).add(componentView);
            componentView.setScene(scene);
            c.getServicesProvided().forEach((n) -> {
                widget = createPin(scene, componentView.getNodeID(), c.getShortName(n) + "_P", c.getShortName(n));
                pinProviderWidget.add(widget);
            });
            c.getUsedServices().forEach((n) -> {
                widget = createPin(scene, componentView.getNodeID(), c.getShortName(n) + "_U", c.getShortName(n));
                pinUsedWidget.add(widget);
            });
            line += 20;
            col += 120;
            categories.put("Provided Services", pinProviderWidget);
            categories.put("Used Services", pinUsedWidget);
            componentView.getWidget().sortPins(categories);
        }
    }

}
