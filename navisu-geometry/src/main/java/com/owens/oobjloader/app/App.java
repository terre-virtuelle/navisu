package com.owens.oobjloader.app;

// This code was written by myself, Sean R. Owens, sean at guild dot net,
// and is released to the public domain. Share and enjoy. Since some
// people argue that it is impossible to release software to the public
// domain, you are also free to use this code under any version of the
// GPL, LPGL, Apache, or BSD licenses, or contact me for use of another
// license.  (I generally don't care so I'll almost certainly say yes.)
// In addition this code may also be used under the "unlicense" described
// at http://unlicense.org/ .  See the file UNLICENSE in the repo.
import java.util.logging.Logger;
import com.owens.oobjloader.builder.Build;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.parser.Parse;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class App {

    private static Logger log = Logger.getLogger(App.class.getName());

    /**
     * Application init
     *
     * @param args Commandline args
     */
    public static void main(String[] args) {

        String filename = "65_101_facades_blanches.obj";
    //  String filename = "/home/serge/Data/3DModels/BMO/obj/65_101_OBJ/65_101_BATI_TEXTURE/65_101_FACADES_TEXTURE/65_101_facades_texturees.obj";
      //  String filename = "65_101_toitures_blanches.obj";
       // String filename = "65_101_complet_blanc.obj";
        log.log(INFO, "Parsing WaveFront OBJ file");
        Build builder = new Build();
        try {
            Parse obj = new Parse(builder, filename);
        } catch (java.io.FileNotFoundException e) {
            log.log(SEVERE, "Exception loading object!  e={0}", e);
        } catch (java.io.IOException e) {
            log.log(SEVERE, "Exception loading object!  e={0}", e);
        }
        System.out.println("faces : " + builder.faces.size());
        
        for(Face f : builder.faces){
           
          //  System.out.println("vertices : " + f.getVertices());
          //  f.calculateTriangleNormal();
          //  System.out.println("normal : "+f.getFaceNormal());
            
            System.out.println("f : " + f);
        }
        System.out.println(builder.groups);
        System.out.println("");
                
        System.out.println(builder.mapLib);
        System.out.println("");
        System.out.println(builder.materialLib);
    }
}
