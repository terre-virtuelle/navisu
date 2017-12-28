/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.dir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class FileSystem {

    public static void copyFolder(String src, String dest) {
        Path srcP = Paths.get(src);
        Path destP = Paths.get(dest);
        copyFolder(srcP, destP);
    }

    public static void copyFolder(String src, String dest, String filter) {
        Path srcP = Paths.get(src);
        Path destP = Paths.get(dest);
        copyFolder(srcP, destP, filter);
    }

    public static void copyFolder(String src, String dest, List<String> filter) {
        Path srcP = Paths.get(src);
        Path destP = Paths.get(dest);
        copyFolder(srcP, destP, filter);
    }

    public static void copyFolder(Path src, Path dest) {
        try {
            Files.walk(src).forEach(s -> {
                try {
                    Path d = dest.resolve(src.relativize(s));
                    if (Files.isDirectory(s)) {
                        if (!Files.exists(d)) {
                            Files.createDirectory(d);
                        }
                        return;
                    }
                    Files.copy(s, d);// use flag to override existing
                } catch (IOException e) {
                    Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, e);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void copyFolder(Path src, Path dest, String filter) {

        try {
            Files.walk(src).forEach(s -> {
                try {
                    Path d = dest.resolve(src.relativize(s));
                    if (Files.isDirectory(s)) {
                        if (s.getFileName().toString().equals(filter)) {
                            if (!Files.exists(d)) {
                                Files.createDirectory(d);
                            }
                            return;
                        }
                    }

                    Files.copy(s, d);// use flag to override existing
                } catch (IOException ex) {
                    Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void copyFolder(Path src, Path dest, List<String> filter) {
        try {
            Files.walk(src).forEach(s -> {
                try {
                    Path d = dest.resolve(src.relativize(s));
                    if (Files.isDirectory(s)) {
                        if (filter.contains(s.getFileName().toString())) {
                            if (!Files.exists(d)) {
                                Files.createDirectory(d);
                            }
                            return;
                        }
                    }
                    Files.copy(s, d);// use flag to override existing
                } catch (IOException e) {
                    Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, e);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
