/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.string;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 6 sept. 2014 NaVisu project
 */
public class Format {

    public static String split(String s, int cars) {
        List<String> lines = new ArrayList<>();
        System.out.println("s : "+s);
        int l = s.length();
        int nb = l / cars;
        int j = -1;
        int k = 0;
        for (int i = 0; i < nb; i++) {
            // System.out.println(s.substring(i, i + cars));
            lines.add(s.substring(j+1, j + cars));
            j += cars;
            k++;
        }
        lines.add(s.substring(j, l - 1));
        System.out.println("lines " + lines);
        StringBuilder strbuff = new StringBuilder();
        for (String ss : lines) {
            strbuff.append(ss).append("\n");
        }
        return new String(strbuff);
    }
}
