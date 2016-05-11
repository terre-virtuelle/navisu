/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.main;

import com.sun.javafx.application.LauncherImpl;

/**
 *
 * @author serge
 * @date   Apr 26, 2016
 *
 */
public class Main {
   public static void main(String[] args) {
      LauncherImpl.launchApplication(AppMain.class, SplashScreenLoader.class, args);
   }
}
