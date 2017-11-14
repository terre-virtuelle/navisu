package bzh.terrevirtuelle.navisu.architecture.app.controlcommand;


import bzh.terrevirtuelle.navisu.architecture.impl.view.ComponentView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/* ListDemo.java requires no other files. */
public class ControlPanel extends JPanel
        implements ListSelectionListener {

    Map<String, List<ComponentView>> componentMap;

    private final JList<String> list;
    private final DefaultListModel<String> listModel;
    private static final String INVISIBLE_STRING = "Invisible";
    private static final String VISIBLE_STRING = "Visible";
    private final JButton visibleButton;
    private final JButton invisibleButton;

    @SuppressWarnings("unchecked")
    public ControlPanel(Map<String, List<ComponentView>> componentMap) {
        super(new BorderLayout());
        this.componentMap = componentMap;
        listModel = new DefaultListModel<>();
        for (String c : componentMap.keySet()) {
            listModel.addElement(c);
            // System.out.println("c " + c);
        }
        //  listModel.addElement("Jane Doe");

        //Create the list and put it in a scroll pane.
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        //  list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        visibleButton = new JButton(VISIBLE_STRING);
        visibleButton.setActionCommand(VISIBLE_STRING);
        visibleButton.addActionListener(new VisibleListener());

        invisibleButton = new JButton(INVISIBLE_STRING);
        invisibleButton.setActionCommand(INVISIBLE_STRING);
        invisibleButton.addActionListener(new InvisibleListener());

        String name;
        if (!listModel.isEmpty()) {
            name = listModel.getElementAt(list.getSelectedIndex());
        }

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(visibleButton);
        buttonPane.add(Box.createHorizontalStrut(50));
        buttonPane.add(invisibleButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    class VisibleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!listModel.isEmpty()) {
                int index = list.getSelectedIndex();
                list.getSelectedIndices();
                List<String> values=list.getSelectedValuesList();
                values.forEach((key) -> {
                    List<ComponentView> componentsSelected = componentMap.get(key);
                    for(ComponentView c : componentsSelected){
                      //  c.
                    }
                });
            }
        }
    }

    class InvisibleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!listModel.isEmpty()) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                int size = listModel.getSize();
                if (size == 0) {
                    visibleButton.setEnabled(false);
                } else {
                    if (index == listModel.getSize()) {
                        index--;
                    }
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        }
    }

    //This method is required by ListSelectionListener.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                visibleButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                visibleButton.setEnabled(true);
            }
        }
    }

}
