/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

/**
 *
 * @author serge
 */
public interface TextPart {
    boolean contains(CharSequence str);
    String shorten();
}
