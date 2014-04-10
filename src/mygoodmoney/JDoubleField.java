/**
* @file JDoubleField.java
* @brief Contém a classe e métodos da classe JDoubleField, um JTextField formatado.
* @copyright 2014 Ricardo Montania. Todos os Direitos Reservados.
* @license Este projeto encontra-se sob a licensa GNU.
*/

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

package mygoodmoney;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * @class JDoubleField
 */
public class JDoubleField extends JTextField
{
  private Double valor;  /**< O valor armazenado no JTextField em formato Double. */

  /**
  * @brief Construtor da classe JDoubleField.
  */
  public JDoubleField()
  {
    super( "0,00" );
    setHorizontalAlignment( JLabel.RIGHT );
    this.valor = new Double( 0.0 );
    this.addFocusListener( new FocusListener()
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

  /**
  * @brief Verifica se o valor recebido no campo é valido.
  * @param psValor O valor recebido.
  * @return true se sim, ou false se não.
  */
  private boolean valorValido( String psValor )
  {
    try
    {
      if( psValor.indexOf( "," ) != -1 )
      {
        psValor = psValor.replaceAll( ",", "." );
      }
      
      Double valorValido = Double.parseDouble( psValor );
      
      return( true );
    }
    catch( NumberFormatException e )
    {
      return( false );
    }
  }

  /**
  * @brief Obtém o valor do campo.
  * @return O valor do campo.
  */
  public Double getValue()
  {
    return( this.valor );
  }

  /**
  * @brief Seta o valor no campo texto.
  * @param pdValor O valor a ser setado.
  */
  public void setValue( Double pdValor )
  { 
    this.valor = pdValor;
    
    if( pdValor.equals( Double.NaN ) )
    {
      setText( "0,00" );
    }
    else
    {
      setText( ValueTools.format( pdValor ).replace( "R$","" ) );
      //setText( valorParam.toString() );
    }
  }
}