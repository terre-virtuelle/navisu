package org.stlviewer;

import bzh.terrevirtuelle.navisu.geometry.objects3D.Triangle;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;


import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;


public class PModel extends BranchGroup {
	
	private static final Logger logger = Logger.getLogger(PModel.class.getName());
	
	private boolean bnormstrip = true;

	public PModel() {		
		init();
	}

	public PModel(String name) {
		setName(name);
		setCapability(BranchGroup.ALLOW_DETACH);
	}		
	
	public boolean isBnormstrip() {
		return bnormstrip;
	}

	public void setBnormstrip(boolean bnormstrip) {
		this.bnormstrip = bnormstrip;
	}

	private void init() {
		setName("MODEL");
		setCapability(BranchGroup.ALLOW_DETACH);
	}

	
	public void addtriangles(List<Triangle> triangles) {

		// Store the scene info on a GeometryInfo
		GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);

		// Convert to j3d geometry
		int ntri = triangles.size();
		Point3f coordarray[] = new Point3f[ntri * 3];
		Vector3f normarray[] = new Vector3f[ntri];
		int i = 0;
		for (Triangle t : triangles) {
			Vec3d v = t.getNormal();
			normarray[i] = new Vector3f((float) v.x, (float) v.y, (float) v.z);
			Vec3d[] vertex = t.getVertices();
			for (int j = 0; j < 3; j++) {
				coordarray[i*3+j] = new Point3f(
						(float) vertex[j].x, 
						(float) vertex[j].y, 
						(float) vertex[j].z);
			}
			i++;
		}

		gi.setCoordinates(coordarray);
		gi.setNormals(normarray);
		// gi.setStripCounts(stripCounts);
		
		if (bnormstrip)
			try {
				// generate normals
				NormalGenerator ng = new NormalGenerator();
				ng.generateNormals(gi);
				// stripify
				Stripifier st = new Stripifier();
				st.stripify(gi);
			} catch (Exception e) {
				String msg = new String("unable to generate normals or stripify:");
				msg = msg.concat(e.getMessage());
				Logger.getLogger(PModel.class.getName()).log(Level.WARNING, msg);
			};

		// yellow appearance
		Appearance appearance = new Appearance();
		Color3f color = new Color3f(Color.yellow);
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Texture texture = new Texture2D();
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
		Material mat = new Material(color, black, color, white, 70f);
		appearance.setTextureAttributes(texAttr);
		appearance.setMaterial(mat);
		appearance.setTexture(texture);

		// Put geometry into Shape3d
		Shape3D shape = new Shape3D();
		shape.setGeometry(gi.getGeometryArray());
		shape.setAppearance(appearance);

		addChild(shape);
		//scene.addNamedObject(objectName, shape);

	} 

	
	public void cleanup() {
		detach();
		removeAllChildren();
	}

}
