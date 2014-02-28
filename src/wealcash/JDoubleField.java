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
public class JDoubleField extends JTextField
{
  private Double valor;
  
  public JDoubleField()
  {
    super( "0,00" );
    setHorizontalAlignment( JLabel.RIGHT );
    this.valor = new Double( 0.0 );
    this.addFocusListener(
      new FocusListener()
      {
      @Override
      public void focusGained(FocusEvent e)
      {
        if( getText().equals( "0,00" ) )
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
          setText( "0,00");
        }
        if( getText().indexOf( "." ) != -1 )
        {
          setText( getText().replaceAll( "\\.", "," ) );
        }
        valor = Double.parseDouble( getText().replaceAll( ",", "\\." ) );
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
        valor = valor.replaceAll( ",", "." );
      }
      
      Double valorValido = Double.parseDouble( valor );
      
      return( true );
    }
    catch( NumberFormatException e )
    {
      return( false );
    }
  }
  
  public Double getValue()
  {
    return( this.valor );
  }
  
  public void setValue( Double valorParam )
  { 
    this.valor = valorParam;
    
    if( valorParam.equals( Double.NaN ) )
    {
      setText( "0,00" );
    }
    else
    {
      setText( ValueTools.format( valorParam ).replace( "R$","" ) );
      //setText( valorParam.toString() );
    }
  }
}