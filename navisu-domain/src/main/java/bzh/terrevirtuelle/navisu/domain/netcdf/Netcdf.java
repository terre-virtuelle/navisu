/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucar.ma2.Array;
import ucar.nc2.Attribute;
import ucar.nc2.Group;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;

/**
 *
 * @author serge
 * @date Sep 6, 2016
 */
public class Netcdf {

    NetcdfDataset netcdfDataset;
    List<String> variableNameList;
    List<Variable> variables;
    List<Attribute> attributes;

    public Netcdf(String fileName) {
        try {
            netcdfDataset = NetcdfDataset.openDataset(fileName);
            if (netcdfDataset != null) {
                List<Group> groups = netcdfDataset.getRootGroup().getGroups();
                if (groups.size() > 0) {
                    variables = netcdfDataset.getRootGroup().getGroups().get(0).getVariables();
                } else {
                    variables = netcdfDataset.getVariables();
                } 
            }
            attributes= netcdfDataset.getGlobalAttributes();
        } catch (IOException ex) {
            Logger.getLogger(Netcdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            netcdfDataset.close();
        } catch (IOException ex) {
            Logger.getLogger(Netcdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Array read(Variable variable) {
        Array value = null;
        try {
            value = variable.read();
        } catch (IOException ex) {
            Logger.getLogger(Netcdf.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return value;
    }

    public Array read(String variableName) {
        return read(getVariable(variableName));
    }

    public List<Variable> getVariables() {
        return variables;
    }

    // @Override
    public Variable getVariable(String variableName) {
        Variable variable = null;
        for (Variable v : variables) {
            if (v.getShortName().equals(variableName)) {
                variable = v;
            }
        }
        return variable;
    }

    boolean isVariableNameValid(String variableName) {
        boolean isValid = false;
        if (variableNameList != null) {
            for (String s : variableNameList) {
                if (s.equalsIgnoreCase(variableName)) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public NetcdfDataset getNetcdfDataset() {
        return netcdfDataset;
    }

    public List<String> getVariableNameList() {
        return variableNameList;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

}
