/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mygoodmoney;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LancamentoModel extends AbstractTableModel {
	private ArrayList<Lancamento> linhas;
	public static final int COL_DATA = 0;
	public static final int COL_TIPO = 1;
	public static final int COL_DESCRICAO = 2;
	public static final int COL_VALOR = 3;
	public static final int COL_PAGO = 4;
	public static final int NUM_COLUNAS = 5;

	public LancamentoModel() {
		this.linhas = new ArrayList<>();
	}

	@Override
	public Class getColumnClass( int column ) {
		switch( column ) {
			case COL_DATA:      return( Integer.class );
			case COL_TIPO:      return( Character.class );
			case COL_DESCRICAO: return( String.class );
			case COL_VALOR:     return( Double.class );
			case COL_PAGO:      return( Character.class );
			default:            return( String.class );
		}
	}

	@Override
	public int getColumnCount() {
		return( NUM_COLUNAS );
	}

	@Override
	public String getColumnName( int column ) {
		switch( column ) {
			case COL_DATA:      return( "Data" );
			case COL_TIPO:      return( "Tipo" );
			case COL_DESCRICAO: return( "DescriXXo" );
			case COL_VALOR:     return( "Valor" );
			case COL_PAGO:      return( "Pg/Rc" );
			default:            return( "" );
		}
	}
	public Lancamento getLinha( int row ) {
		return( this.linhas.get( row ) );
	}
	public ArrayList<Lancamento> getLinhas() {
		return( this.linhas );
	}

	@Override
	public int getRowCount() {
		return( this.linhas.size() );
	}

	@Override
	public Object getValueAt( int row, int column ) {
		Lancamento lancamento = this.linhas.get( row );

		switch( column ) {
			case COL_DATA:      return( lancamento.getDataVencimento() );
			case COL_TIPO:      return( (lancamento.getConta() == null)? 'T' : lancamento.getConta().getTipo() );
			case COL_DESCRICAO: return( lancamento.getDescricao() );
			case COL_VALOR:     return( lancamento.getValor() );
			case COL_PAGO:      return( lancamento.getPago() );
			default:            return( "" );
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

	public void setLinhas( ArrayList<Lancamento> linhas ) {
		this.linhas = linhas;
		fireTableDataChanged();
	}

	public void addLinha( Lancamento lancamento ) {
		this.linhas.add( lancamento );
	}

	public void removeLinha( Lancamento lancamento ) {
		this.linhas.remove( lancamento );
	}
}