/*
 * Copyright (C) 2014 Ricardo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package wealcash;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 *
 * @author Ricardo
 */
public class JIntegerField extends JTextField
{
  private int valor;
  
  public JIntegerField()
  {
    super( "0" );
    setHorizontalAlignment( JLabel.RIGHT );
    this.valor = 0;
    this.addFocusListener(
      new FocusListener()
      {
      @Override
      public void focusGained(FocusEvent e)
      {
        if( getText().equals( "0" ) )
        {
          setText( "" );
        }
        else
        {
          selectAll();
        }
      }
      @Override
      public void focusLost(FocusEvent e)
      {
        if( getText().isEmpty() || !valorValido( getText() ) )
        {
          setText( "0");
        }
        if( getText().indexOf( "." ) != -1 )
        {
          setText( getText().replaceAll( "\\.", "" ) );
        }
        if( getText().indexOf( "," ) != -1 )
        {
          setText( getText().replaceAll( "\\.", "" ) );
        }
        valor = Integer.parseInt( getText() );
      }
    }
    );
  }
  
  private boolean valorValido( String valor )
  {
    try
    {
      if( valor.indexOf( "," ) != -1 )
      {
        valor = valor.replaceAll( ",", "" );
      }
      if( valor.indexOf( "." ) != -1 )
      {
        valor = valor.replaceAll( "\\.", "" );
      }
      
      int valorValido = Integer.parseInt( valor );
      
      return( true );
    }
    catch( NumberFormatException e )
    {
      System.out.println( "O valor " + valor + " nao eh um int valido." );
      return( false );
    }
  }
  
  public int getValue()
  {
    return( this.valor );
  }
  
  public void setValue( int valorParam )
  {
    this.valor = valorParam;
    if( valorParam == 0 )
    {
      setText( "" );
    }
    else
    {
      setText( valorParam + "" );
    }
  }
}