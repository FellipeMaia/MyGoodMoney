package mygoodmoney;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {
  @Override
  public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr ) throws BadLocationException {
    fb.insertString( offset, text.replaceAll( "[^0-9,R$]", "" ), attr );
  }
  @Override
  public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr )  throws BadLocationException {
    fb.replace( offset, length, text.replaceAll( "[^0-9,R$]", "" ), attr );
  }
}
