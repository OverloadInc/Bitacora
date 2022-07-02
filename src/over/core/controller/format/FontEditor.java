package over.core.controller.format;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <code>FontEditor</code> class provides a mechanism to set the font style used in the
 * <code>Bitacora</code>'s message console.
 *
 * @author Overload Inc.
 * @version 1.0, 18 Jun 2022
 */
public class FontEditor {

    /**
     * The instance to set the font style.
     */
    private SimpleAttributeSet attributeSet;

    /**
     * Class constructor.
     */
    public FontEditor() {
        attributeSet = new SimpleAttributeSet();
    }

    /**
     * Sets the console's font to bold type.
     * @param editor the <code>JTextPane</code> editor.
     * @param text the text to modify.
     */
    public void setBold(JTextPane editor, String text){
        StyleConstants.setBold(attributeSet, true);

        try {
            int position = editor.getStyledDocument().getLength();

            editor.getStyledDocument().insertString(position, text, attributeSet);
        }
        catch (BadLocationException ex) {
            Logger.getLogger(JTextPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the console's font to simple type.
     * @param editor the <code>JTextPane</code> editor.
     * @param text the text to modify.
     */
    public void setSimple(JTextPane editor, String text){
        StyleConstants.setBold(attributeSet, false);

        try {
            int position = editor.getStyledDocument().getLength();

            editor.getStyledDocument().insertString(position, text, attributeSet);
        }
        catch (BadLocationException ex) {
            Logger.getLogger(JTextPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}