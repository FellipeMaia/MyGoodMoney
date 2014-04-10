/**
* @file JIntegerField.java
* @brief Contém a classe e métodos da classe JIntegerField, um JTextField formatado.
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
 * @class JIntegerField
 */
public class JIntegerField extends JTextField
{
  private int valor;  /**< O valor armazenado no JTextField em formato inteiro. */

  /**
  * @brief Construtor da classe.
  */
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
        psValor = psValor.replaceAll( ",", "" );
      }
      if( psValor.indexOf( "." ) != -1 )
      {
        psValor = psValor.replaceAll( "\\.", "" );
      }
      
      int valorValido = Integer.parseInt( psValor );
      
      return( true );
    }
    catch( NumberFormatException e )
    {
      System.out.println( "O valor " + valor + " nao eh um int valido." );
      return( false );
    }
  }

  /**
  * @brief Obtém o valor do campo.
  * @return O valor do campo.
  */
  public int getValue()
  {
    return( this.valor );
  }

  /**
  * @brief Seta o valor no campo texto.
  * @param piValor O valor a ser setado.
  */
  public void setValue( int piValor )
  {
    this.valor = piValor;
    if( piValor == 0 )
    {
      setText( "" );
    }
    else
    {
      setText( new Integer( piValor ).toString() );
    }
  }
}