package over.core.controller.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * <code>ButtonRenderer</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {

    /**
     *
     */
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Modify" : value.toString());
        return this;
    }
}