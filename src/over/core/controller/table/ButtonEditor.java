package over.core.controller.table;

import javax.swing.*;
import java.awt.*;

/**
 * <code>ButtonEditor</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class ButtonEditor extends DefaultCellEditor {
    private JButton button = new JButton();
    private String label;

    /**
     * Class constructor.
     * @param checkBox the <code>JCheckBox</code> to display in the <code>JTable</code>.
     */
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button.addActionListener(event -> JOptionPane.showMessageDialog(null,"Do you want to modify this line?"));
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Modify" : value.toString();
        button.setText(label);
        return button;
    }

    public Object getCellEditorValue() {
        return new String(label);
    }
}