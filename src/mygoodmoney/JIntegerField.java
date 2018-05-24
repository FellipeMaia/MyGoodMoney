/**
* @file JIntegerField.java
* @brief ContXm a classe e mXtodos da classe JIntegerField, um JTextField formatado.
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

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @class JIntegerField
 */
public class JIntegerField extends JTextField {
	private int valor;  /**< O valor armazenado no JTextField em formato inteiro. */

	/**
	 * @brief Construtor da classe.
	 */
	public JIntegerField() {
		super( "0" );
		this.valor = 0;
		setHorizontalAlignment( JLabel.RIGHT );
		setFont( new Font( "Verdana", 0, 12 ) );
		adicionarListeners();
	}
	private void adicionarListeners() {
		this.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				selectAll();
			}
		});
		this.getDocument().addDocumentListener( new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent de) {
				atualizarValor();
			}
			@Override
			public void removeUpdate(DocumentEvent de) {
				atualizarValor();
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				atualizarValor();
			}
		});
	}
	public void atualizarValor() {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				if( getText().isEmpty() || !valorValido( getText() ) ) {
					setText( "0" );
				}
				if( getText().indexOf( "." ) != -1 ) {
					setText( getText().replaceAll( "\\.", "" ) );
				}
				if( getText().indexOf( "," ) != -1 ) {
					setText( getText().replaceAll( "\\.", "" ) );
				}
			}
		});

		try {
			valor = Integer.parseInt( getText() );
		}
		catch( NumberFormatException ex ) {
			valor = 0;
		}
	}
	/**
	 * @brief Verifica se o valor recebido no campo X vXlido.
	 * @param pValor O valor recebido.
	 * @return true se sim, ou false se nXo.
	 */
	private boolean valorValido( String pValor ) {
		try {
			if( pValor.indexOf( "," ) != -1 ) {
				pValor = pValor.replaceAll( ",", "" );
			}
			if( pValor.indexOf( "." ) != -1 ) {
				pValor = pValor.replaceAll( "\\.", "" );
			}

			int valorValido = Integer.parseInt( pValor );
			return( true );
		}
		catch( NumberFormatException e ) {
			System.out.println( "NumberFormatException: " + e.getMessage() );
			return( false );
		}
	}
	/**
	 * @brief ObtXm o valor do campo.
	 * @return O valor do campo.
	 */
	public int getValue() {
		return( this.valor );
	}
	/**
     * @brief Seta o valor no campo texto.
     * @param pValor O valor a ser setado.
     */
	public void setValue( int pValor ) {
		this.valor = pValor;
		if( pValor == 0 ) {
			setText( "" );
		}
		else {
			setText( String.valueOf( pValor ) );
		}
	}
}