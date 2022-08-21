package over.core.controller.table;

import over.config.Configurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * <code>ButtonEditor</code> class allows including <code>JButton</code> components inside
 * the <code>JTable</code> cells.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class ButtonEditor extends DefaultCellEditor {
    private JButton button = new JButton();
    private String label;

    /**
     * Class constructor to initialize the listeners of the <code>JButton</code> components.
     * @param checkBox the <code>JCheckBox</code> to display in the <code>JTable</code>.
     */
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button.addActionListener(event -> actionSelector(event));
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        switch (column) {
            case 5: label = Configurator.getConfigurator().getProperty("playColumn"); break;
            case 6: label = Configurator.getConfigurator().getProperty("stopColumn"); break;
            case 7: label = Configurator.getConfigurator().getProperty("deleteColumn"); break;
            default: label = value.toString();
        }

        button.setText(label);

        return button;
    }

    public Object getCellEditorValue() {
        return label;
    }

    /**
     * Determines the <code>JButton</code> selected by the user through the graphical interface to invoke the
     * <code>play</code>, <code>stop</code> or <code>delete</code> method.
     * @param evt the <code>ActionEvent</code> instance.
     */
    public void actionSelector(ActionEvent evt) {
        TableController controller = new TableController();

        String buttonSelected = ((JButton) evt.getSource()).getText();
        String play = Configurator.getConfigurator().getProperty("playColumn");
        String stop = Configurator.getConfigurator().getProperty("stopColumn");
        String delete = Configurator.getConfigurator().getProperty("deleteColumn");

        if (buttonSelected.equals(play))
            controller.play();
        else if (buttonSelected.equals(stop))
            controller.stop();
        else if (buttonSelected.equals(delete))
            controller.deleteTask();
    }
}