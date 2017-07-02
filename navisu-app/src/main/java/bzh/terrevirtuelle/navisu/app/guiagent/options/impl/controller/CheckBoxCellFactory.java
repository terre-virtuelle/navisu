/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 *
 * @author serge
 * @date Jul 1, 2017
 */
public class CheckBoxCellFactory<S, T>
        implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    @Override
    public TableCell<S, T> call(TableColumn<S, T> p) {
        return new CheckBoxTableCell<>();
    }

}
