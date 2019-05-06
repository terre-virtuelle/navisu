package org.stlviewer;

import java.awt.GraphicsConfiguration;
import java.util.ArrayList;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Locale;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class PCanvas3D extends Canvas3D {

    TransformGroup transform = null;

    public PCanvas3D(GraphicsConfiguration arg0) {
        super(arg0);
    }

    public PCanvas3D(GraphicsConfiguration arg0, boolean arg1) {
        super(arg0, arg1);
    }

    public void initcanvas(SimpleUniverse universe) {

        Locale locale = universe.getLocale();
        BranchGroup objRoot = new BranchGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 500.0);

        // Set up the background
        Color3f bgColor = new Color3f(0.05f, 0.05f, 0.5f);
        Background bgNode = new Background(bgColor);
        bgNode.setApplicationBounds(bounds);
        objRoot.addChild(bgNode);

        // Set up the ambient light
        Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);

        // Set up the directional lights
        Color3f light1Color = new Color3f(0.8f, 0.8f, 0.8f);
        Vector3f light1Direction = new Vector3f(1.0f, 1.0f, 1.0f);
        Color3f light2Color = new Color3f(0.8f, 0.8f, 0.8f);
        Vector3f light2Direction = new Vector3f(-1.0f, -1.0f, -1.0f);

        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        DirectionalLight light2 = new DirectionalLight(light2Color, light2Direction);
        light2.setInfluencingBounds(bounds);
        objRoot.addChild(light2);

        //draw the origin		
        LineArray linearray = new LineArray(6, GeometryArray.COORDINATES);
        ArrayList<Point3f> verts = new ArrayList<>(6);
        Point3f o, p;
        o = new Point3f(0.0f, 0.0f, 0.0f);
        p = new Point3f(1.0f, 0.0f, 0.0f);
        verts.add(o);
        verts.add(p);
        p = new Point3f(0.0f, 1.0f, 0.0f);
        verts.add(o);
        verts.add(p);
        p = new Point3f(0.0f, 0.0f, 1.0f);
        verts.add(o);
        verts.add(p);
        Point3f[] v = new Point3f[6];
        v = verts.toArray(v);
        linearray.setCoordinates(0, v);

        Shape3D origin = new Shape3D();
        origin.setGeometry(linearray);
        origin.setName("ORIGIN");
        objRoot.addChild(origin);

        /*
	    origin = new Shape3D();	    
	    Font3D font3D = new Font3D(new Font("Helvetica", Font.PLAIN, 1),
	    		new FontExtrusion());
	    Text3D tx = new Text3D(font3D, "X");
	    tx.setPosition(new Point3f(1.0f, 0.0f, 0.0f));
	    origin.addGeometry(tx);
	    Text3D ty = new Text3D(font3D, "Y");
	    ty.setPosition(new Point3f(0.0f, 1.0f, 0.0f));
	    origin.addGeometry(ty);
	    Text3D tz = new Text3D(font3D, "Z");
	    tz.setPosition(new Point3f(0.0f, 0.0f, 1.0f));
	    origin.addGeometry(tz);
	    origin.setName("ORIGINTX");
	    objRoot.addChild(origin);	    
         */
        locale.addBranchGraph(objRoot);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        ViewingPlatform viewingPlatform = universe.getViewingPlatform();
        viewingPlatform.setNominalViewingTransform();

    }

    public void rendermodel(PModel model, SimpleUniverse universe) {

        if (model != null) {

            TransformGroup objTrans = gettransform(universe);

            Bounds bounds = model.getBounds();
            Point3d center = new Point3d(0.0, 0.0, 0.0);
            //bounds.getCenter(center);

            //adjust scale of transform to objects
            if (bounds instanceof BoundingBox) {
                BoundingBox b = (BoundingBox) bounds;
                Point3d up = new Point3d();
                Point3d low = new Point3d();
                b.getUpper(up);
                b.getLower(low);
                double l = Math.abs(up.x - low.x);
                double w = Math.abs(up.y - low.y);
                double h = Math.abs(up.z - low.z);
                double max = Math.max(l, w);
                max = Math.max(max, h);
                double scale = 10.0 / max;
                double la = Math.sqrt(low.x * low.x + low.y * low.y);
                double lb = Math.sqrt(la * la + low.z * low.z);
                double ra = Math.sqrt(center.x * center.x + center.y * center.y);
                double rb = Math.sqrt(ra * ra + center.z * center.z);
                double dr = lb - rb;
                double tx = 0.0, ty = 0.0, tz = 0.0;
                if (dr > 0) {
                    tx = low.x * scale;
                    ty = low.y * scale;
                    tz = low.z * scale;
                }
                Vector3d vt = new Vector3d(-tx, -ty, -tz);

                Transform3D t3d = new Transform3D();
                objTrans.getTransform(t3d);
                t3d.setTranslation(vt);
                t3d.setScale(scale);
                objTrans.setTransform(t3d);
            } else if (bounds instanceof BoundingSphere) {
                BoundingSphere b = (BoundingSphere) bounds;
                double r = b.getRadius();
                double scale = 10.0 / r;
                double c = Math.sqrt(center.x * center.x + center.y * center.y);
                double d = Math.sqrt(c * c + center.z * center.z);
                double dr = d - r;
                double tx = 0.0, ty = 0.0, tz = 0.0;
                if (dr > 0) {
                    tx = dr * center.x * scale / d;
                    ty = dr * center.y * scale / d;
                    tz = dr * center.z * scale / d;
                }
                Vector3d vt = new Vector3d(-tx, -ty, -tz);

                Transform3D t3d = new Transform3D();
                objTrans.getTransform(t3d);
                t3d.setTranslation(vt);
                t3d.setScale(scale);
                objTrans.setTransform(t3d);

            };

            objTrans.addChild(model);

            // add mouse behaviors to the ViewingPlatform
            ViewingPlatform viewingPlatform = universe.getViewingPlatform();
            OrbitBehavior orbit = new OrbitBehavior(this, OrbitBehavior.REVERSE_ALL);
            //BoundingSphere bounds2 = new BoundingSphere(center, 100.0);
            BoundingSphere bounds2 = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
            orbit.setSchedulingBounds(bounds2);
            viewingPlatform.setViewPlatformBehavior(orbit);

        }
    }

    TransformGroup gettransform(SimpleUniverse universe) {
        if (transform == null) {
            Locale ulocale = universe.getLocale();

            BranchGroup objRoot = new BranchGroup();
            objRoot.setName("TRANSFORM");
            objRoot.setCapability(BranchGroup.ALLOW_DETACH);

            // Create a Transformgroup to scale all objects so they
            // appear in the scene.
            TransformGroup objScale = new TransformGroup();
            Transform3D t3d = new Transform3D();
            t3d.setScale(0.2);
            objScale.setTransform(t3d);
            objRoot.addChild(objScale);

            // Create the transform group node and initialize it to the
            // identity. Enable the TRANSFORM_WRITE capability so that
            // our behavior code can modify it at runtime. Add it to the
            // root of the subgraph.
            TransformGroup objTrans = new TransformGroup();
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
            objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
            objScale.addChild(objTrans);

            transform = objTrans;

            ulocale.addBranchGraph(objRoot);

        }
        return transform;
    }

    public void homeview(SimpleUniverse universe) {
        ViewingPlatform viewingplatform = universe.getViewingPlatform();
        viewingplatform.setNominalViewingTransform();
    }

}
