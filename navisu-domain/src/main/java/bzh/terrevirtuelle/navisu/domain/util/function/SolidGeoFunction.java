/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.util.function;

import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import java.util.List;

/**
 *
 * @author serge
 * @date Jul 29, 2019
 */
public interface SolidGeoFunction {

    void apply(List<SolidGeo> solids);
}
