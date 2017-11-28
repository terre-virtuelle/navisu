/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Nov 28, 2017
 */
public class Selection {

    private List<String> inputOutput;

    private List<String> modules;

    private List<String> components;

    public Selection(List<String> inputOutput, List<String> modules, List<String> components) {
        this.inputOutput = inputOutput;
        this.modules = modules;
        this.components = components;
    }

    public Selection() {
        this.inputOutput = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.components = new ArrayList<>();
    }

    /**
     * Get the value of components
     *
     * @return the value of components
     */
    public List<String> getComponents() {
        return components;
    }

    /**
     * Set the value of components
     *
     * @param components new value of components
     */
    public void setComponents(List<String> components) {
        this.components = components;
    }

    /**
     * Get the value of modules
     *
     * @return the value of modules
     */
    public List<String> getModules() {
        return modules;
    }

    /**
     * Set the value of modules
     *
     * @param modules new value of modules
     */
    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    /**
     * Get the value of inputOutput
     *
     * @return the value of inputOutput
     */
    public List<String> getInputOutput() {
        return inputOutput;
    }

    /**
     * Set the value of inputOutput
     *
     * @param inputOutput new value of inputOutput
     */
    public void setInputOutput(List<String> inputOutput) {
        this.inputOutput = inputOutput;
    }

    public void addInpuOutput(List<String> l) {
        inputOutput.addAll(l);
    }

    public void addModules(List<String> l) {
        modules.addAll(l);
    }

    public void addComponents(List<String> l) {
        components.addAll(l);
    }

    @Override
    public String toString() {
        return "Selection{" + "inputOutput=" + inputOutput + ", modules=" + modules + ", components=" + components + '}';
    }
    
}
