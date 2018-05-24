/**
* @file CaixaModel.java
* @brief ContXm mXtodos de acesso e tratamento para a tabela de caixas.
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
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.	If not, see <http://www.gnu.org/licenses/>.
*/

package mygoodmoney;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CaixaModel extends AbstractTableModel {
	private ArrayList<Caixa> linhas;
	public static final int COL_NOME = 0;
	public static final int COL_SALDO = 1;
	public static final int COL_LIMITE = 2;
	public static final int NUM_COLUNAS = 3;
	public CaixaModel() {
		this.linhas = new ArrayList<>();
	}
	@Override
	public Class getColumnClass( int column ) {
		switch( column ) {
			case COL_NOME:	 return( String.class );
			case COL_SALDO:	 return( Double.class );
			case COL_LIMITE: return( Double.class );
			default:		 return( String.class );
		}
	}
	@Override
	public int getColumnCount() {
		return( NUM_COLUNAS );
	}
	@Override
	public String getColumnName( int column ) {
		switch( column ) {
			case COL_NOME:	 return( "Nome" );
			case COL_SALDO:	 return( "Saldo" );
			case COL_LIMITE: return( "Limite" );
			default:		 return( "" );
		}
	}
	public Caixa getLinha( int row ) {
		return( this.linhas.get( row ) );
	}
	public ArrayList<Caixa> getLinhas() {
		return( this.linhas );
	}
	@Override
	public int getRowCount() {
		return( this.linhas.size() );
	}
	@Override
	public Object getValueAt( int row, int column ) {
		Caixa caixa = this.linhas.get( row );
		switch( column ) {
			case COL_NOME:	 return( caixa.getNome() );
			case COL_SALDO:	 return( caixa.getSaldo() );
			case COL_LIMITE: return( caixa.getValorLimite() );
			default:		 return( "" );
		}
	}
	@Override
	public boolean isCellEditable( int row, int column ) {
		return( false );
	}
	public void limpar() {
		this.linhas.clear();
		fireTableDataChanged();
	}
	public void setLinhas( ArrayList<Caixa> linhas ) {
		this.linhas = linhas;
		fireTableDataChanged();
	}
	public void addLinha( Caixa pCaixa ) {
		this.linhas.add( pCaixa );
	}
	public void removeLinha( Caixa pCaixa ) {
		this.linhas.remove( pCaixa );
	}
}