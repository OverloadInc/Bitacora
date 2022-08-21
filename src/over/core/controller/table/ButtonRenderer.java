package over.core.controller.table;

import over.config.Configurator;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * <code>ButtonRenderer</code> class allows modifying the <code>JTable</code> component cells
 * to incorporate <code>JButton</code> properties.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {

    /**
     * Class constructor.
     */
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        switch (column) {
            case 5: setText(Configurator.getConfigurator().getProperty("playColumn")); break;
            case 6: setText(Configurator.getConfigurator().getProperty("stopColumn")); break;
            case 7: setText(Configurator.getConfigurator().getProperty("deleteColumn")); break;
            default: value.toString();
        }

        return this;
    }
}