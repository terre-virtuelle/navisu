/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.objects3D.obj.impl;


import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponent;
import com.owens.oobjloader.builder.Build;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.VertexGeometric;
import com.owens.oobjloader.parser.Parse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serge Morvan
 */
public class ObjComponentImpl
        implements ObjComponent, ObjComponentServices, ComponentState {

    
    private static final Logger LOG = Logger.getLogger(ObjComponentImpl.class.getName());

    @Override
    public List<Face> getFaces(String filename) {
        Build builder = new Build();
        try {
            Parse obj = new Parse(builder, filename);
        } catch (IOException ex) {
            Logger.getLogger(ObjComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return builder.faces;
    }

    @Override
    public List<VertexGeometric> getVerticesG(String filename) {
        Build builder = new Build();
        try {
            Parse obj = new Parse(builder, filename);
        } catch (IOException ex) {
            Logger.getLogger(ObjComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return builder.verticesG;
    }

    

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Point3D toPoint3D(VertexGeometric vertexGeometric) {
        return new Point3D(vertexGeometric.x, vertexGeometric.y, vertexGeometric.z);
    }

    @Override
    public List<Point3D> toPoint3Ds(List<VertexGeometric> vertexGeometrics) {
        List<Point3D> result = new ArrayList<>();
        vertexGeometrics.forEach((v) -> {
            result.add(new Point3D(v.x, v.y, v.z));
        });
        return result;
    }

}
