package org.stlviewer;

import bzh.terrevirtuelle.navisu.geometry.objects3D.Triangle;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class STLViewer
        extends JFrame
        implements ActionListener, WindowListener {

    PCanvas3D canvas;
    JLabel lstatusline;
    PModel model;
    SimpleUniverse universe;

    JCheckBoxMenuItem mnstrp;

    public STLViewer(String args[]) throws HeadlessException {
        super("STL Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createwin();
        addWindowListener(this);
    }

    public STLViewer() {
        super("STL Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createwin();
        addWindowListener(this);
    }

    public void createwin() {

        setPreferredSize(new Dimension(1024, 768));

        JMenuBar mbar = new JMenuBar();
        JMenu mfile = new JMenu("File");
        mfile.setMnemonic(KeyEvent.VK_F);
        JMenuItem mopen = new JMenuItem("Open", KeyEvent.VK_O);
        mopen.setActionCommand("FOPEN");
        mopen.addActionListener(this);
        mfile.add(mopen);
        mbar.add(mfile);
        JMenu mview = new JMenu("View");
        mfile.setMnemonic(KeyEvent.VK_V);
        JMenuItem mabt = new JMenuItem("About");
        mabt.setActionCommand("ABOUT");
        mabt.addActionListener(this);
        mfile.add(mabt);
        JMenuItem mres = new JMenuItem("Home", KeyEvent.VK_H);
        mres.setActionCommand("VHOME");
        mres.addActionListener(this);
        mview.add(mres);
        mbar.add(mview);
        JMenu mtools = new JMenu("Tools");
        mfile.setMnemonic(KeyEvent.VK_T);
        mnstrp = new JCheckBoxMenuItem("Regen Normals/Connect strips", true);
        mnstrp.addActionListener(this);
        mtools.add(mnstrp);
        mbar.add(mtools);

        setJMenuBar(mbar);

        JToolBar toolbar = new JToolBar();
        JButton button = null;

        button = makeNavigationButton("Open24.gif", "FOPEN", "Open", "Open");
        toolbar.add(button);
        button = makeNavigationButton("Home24.gif", "VHOME", "Home", "Home");
        toolbar.add(button);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas = new PCanvas3D(config);
        getContentPane().add(canvas, BorderLayout.CENTER);

        lstatusline = new JLabel(" ");
        getContentPane().add(lstatusline, BorderLayout.SOUTH);

        universe = new SimpleUniverse(canvas);
        canvas.initcanvas(universe);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, String altText) {
        // Look for the image.
        String imgLocation = "/images/" + imageName;
        URL imageURL = getClass().getResource(imgLocation);

        // Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageURL != null) { // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else { // no image found
            button.setText(altText);
        }

        return button;
    }

    private File currdir;

    private File askForFile() {

        JFileChooser jfc = new JFileChooser(currdir);
        int action = jfc.showOpenDialog(null);
        if (action != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File file = jfc.getSelectedFile();
        if (file.getParentFile() != null) {
            currdir = file.getParentFile();
        }
        return file;
    }

    private void loadfile() {
        File file = askForFile();
        if (file == null) {
            return;
        }
        loadfile(file);
    }

    public void loadfile(String filename) {
        System.out.println("OK2");
        loadfile(new File(filename));
    }

    public void loadfile(File file) {
        // read file to array of triangles
        try {
            List<Triangle> mesh;
            mesh = new STLParser().parseSTLFile(file.toPath());
            if (mesh == null || mesh.isEmpty()) {
                lstatusline.setText("no data read, possible file error");
                return;
            } else {
                lstatusline.setText(" ");
            }
            if (model != null) {
                model.cleanup();
            }
            model = new PModel();
            model.setBnormstrip(mnstrp.isSelected());
            model.addtriangles(mesh);
            //model.loadstl(file);
            canvas.rendermodel(model, universe);
        } catch (IOException | IllegalArgumentException e) {
            lstatusline.setText("no data read, possible file error");
            Logger.getLogger(STLViewer.class.getName()).log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("FOPEN")) {
            loadfile();
        } else if (e.getActionCommand().equals("VHOME")) {
            canvas.homeview(universe);
        } else if (e.getActionCommand().equals("ABOUT")) {
            About a = new About();
            a.pack();
            Point p = this.getLocationOnScreen();
            a.setLocation(new Point(p.x + 100, p.y + 100));
            a.setVisible(true);

        }
    }

    public static void main(String[] args) {
        System.out.println("OK");
        System.out.println("System : "+System.getProperty("user.dir"));
        new STLViewer().loadfile("lighthouse.stl");
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        universe.removeAllLocales();
        universe.cleanup();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
