/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.view;

import gov.nasa.worldwind.View;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Frustum;
import gov.nasa.worldwind.geom.Matrix;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.view.ViewPropertyLimits;
import java.awt.Rectangle;

/**
 * NaVisu
 *
 * @date 4 nov. 2015
 * @author Serge Morvan
 */
public class ViewState {

    private View view;

    public ViewState() {
    }

    public ViewState(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Vec4 getCenterPoint() {
       // return this.view.getCenterPoint();
        return null;
    }

    public Vec4 getCurrentEyePoint() {
        return getView().getCurrentEyePoint();
    }

    public Position getCurrentEyePosition() {
        return getView().getCurrentEyePosition();
    }

    public Vec4 getEyePoint() {
        return getView().getEyePoint();
    }

    public Position getEyePosition() {
        return getView().getEyePosition();
    }

    public double getFarClipDistance() {
        return getView().getFarClipDistance();
    }

    public Angle getFieldOfView() {
        return getView().getFieldOfView();
    }

    public Vec4 getForwardVector() {
        return getView().getForwardVector();
    }

    public Frustum getFrustum() {
        return getView().getFrustum();
    }

    public Frustum getFrustumInModelCoordinates() {
        return getView().getFrustumInModelCoordinates();
    }

    public Globe getGlobe() {
        return getView().getGlobe();
    }

    public Angle getHeading() {
        return getView().getHeading();
    }

    public double getHorizonDistance() {
        return getView().getHorizonDistance();
    }

    public Matrix getModelviewMatrix() {
        return getView().getModelviewMatrix();
    }

    public double getNearClipDistance() {
        return getView().getNearClipDistance();
    }

    public Angle getPitch() {
        return getView().getPitch();
    }

    public Matrix getProjectionMatrix() {
        return getView().getProjectionMatrix();
    }

    public Angle getRoll() {
        return getView().getRoll();
    }

    public Vec4 getUpVector() {
        return getView().getUpVector();
    }

    public Rectangle getViewport() {
        return getView().getViewport();
    }

    public ViewPropertyLimits getViewPropertyLimits() {
        return getView().getViewPropertyLimits();
    }

    public void goTo(Position position, double elevation) {
        getView().goTo(position, elevation);
    }

    public void print() {
        System.out.println("CenterPoint " + getCenterPoint());
        System.out.println("CurrentEyePoint "+ getCurrentEyePoint());
        System.out.println("CurrentEyePosition "+ getCurrentEyePosition());
        System.out.println("EyePoint "+ getEyePoint());
        System.out.println("FarClipDistance "+ getFarClipDistance());
        System.out.println("FieldOfView "+getFieldOfView() );
        System.out.println("ForwardVector "+ getForwardVector());
        System.out.println("Frustum "+ getFrustum());
        System.out.println("FrustumInModelCoordinates"+ getFrustumInModelCoordinates());
        System.out.println("Globe "+ getGlobe());
        System.out.println("Heading"+ getHeading());
        System.out.println("HorizonDistance"+ getHorizonDistance());
        System.out.println("ModelviewMatrix"+ getModelviewMatrix());
        System.out.println("NearClipDistance"+ getNearClipDistance());
        System.out.println("Pitch"+ getPitch());
        System.out.println("ProjectionMatrix"+ getProjectionMatrix());
        System.out.println("Roll"+ getRoll());
        System.out.println("UpVector"+ getUpVector());
        System.out.println("Viewport"+ getViewport());
        System.out.println("ViewPropertyLimits "+ getViewPropertyLimits());
    }
}
