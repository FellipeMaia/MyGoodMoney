/**
* @file ContaDAO.java
* @brief ContXm mXtodos de acesso a entidade Conta do banco de dados.
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContaDAO {
	private final Connection connection;
	public ContaDAO() {
		this.connection = ConnectionHolder.getConnection();
	}
	public ArrayList<Conta> selectListaTodosRegistros() {
		return( selectListaRegistros( null ) );
	}
	public ArrayList<Conta> selectListaRegistros( Character pTipoConta ) {
		ArrayList<Conta> contasList = new ArrayList<>();
		try {
			Statement st = this.connection.createStatement();
			String sql =
			"SELECT" +
			" COD_CONTA," +
			" NOME," +
			" TIPO " +
			"FROM" +
			" CONTAS " +
			"WHERE 1 = 1";
			if( pTipoConta != null ) {
				sql += " AND TIPO = '" + pTipoConta + "'";
			}
			sql += " ORDER BY NOME";
			System.out.println( "SQL: " + sql );
			ResultSet rs = st.executeQuery( sql );
			if( rs.next() ) {
				do {
					if( rs.getString( 2 ) != null ) {
						Conta conta = new Conta();
						conta.setCodConta( rs.getInt( 1 ) );
						conta.setNome( rs.getString( 2 ) );
						conta.setTipo( rs.getString( 3 ).charAt( 0 ) );
						contasList.add( conta );
					}
				}
				while( rs.next() );
				System.out.println( "SQL: " + contasList.size() + " Registro(s)" );
			}
			else {
				System.out.println( "SQL: 0 Registros" );
			}
			return( contasList );
		}
		catch( SQLException ex ) {
			System.out.println( "SQLException: " + ex.getLocalizedMessage() );
			if( ex.getErrorCode() == 1 ) {
				// 1 = SQLITE_ERROR = Error or missing database
				System.out.println( "SQL: Banco de dados nXo encontrado! Criando.." );
				ConnectionHolder.criarTabelas();
			}
			return( contasList );
		}
	}
	public void inserir( Conta pConta ) {
		try {
			Statement st = this.connection.createStatement();
			String sQuery =
			"INSERT INTO CONTAS (NOME, TIPO)" +
			" VALUES" +
			" (" +
			" '" + pConta.getNome() + "'," + // NOME
			" '" + pConta.getTipo() + "'" +	 // TIPO
			" )";
			System.out.println( "SQL: " + sQuery );
			if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
				System.out.println( "SQL: 0 Registros Inseridos" );
			}
			else {
				System.out.println( "SQL: 1 Registro Inserido" );
			}
		}
		catch( SQLException e ) {
			System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
		}
	}
	/**
	* @brief Exclui uma conta do banco de dados.
	* @param pConta Uma conta a ser excluXda.
	*/
	public void excluir( Conta pConta ) {
		try {
			Statement st = this.connection.createStatement();
			String sQuery =
			"DELETE FROM" +
			" CONTAS " +
			"WHERE" +
			" COD_CONTA = " + pConta.getCodConta();
			System.out.println( "SQL: " + sQuery );
			if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
				System.out.println( "SQL: 0 Registros Apagados." );
			}
			else {
				System.out.println( "SQL: 1 Registro Apagado." );
			}
		}
		catch( SQLException e ) {
			System.out.println( "SQLException: " + e.getLocalizedMessage() );
		}
	}
	/**
	* @brief ObtXm o valor total de um perXodo para uma Conta especXfica.
	* @param pConta O cXdigo de uma Conta.
	* @param pDataIni A data inicial do perXodo de consulta.
	* @param pDataFim A data final do perXodo de consulta.
	* @param pProvisao Um boolean que se true, considera provisXes.
	* @return O valor total da Conta, se encontrado, ou 0 se nXo encontrado.
	*/
	public double selectTotalPeriodo( Conta pConta, Integer pDataIni, Integer pDataFim, boolean pProvisao ) {
		try {
			double total = 0;
			Statement st = this.connection.createStatement();
			String sData1 = pDataIni.toString();
			String sData2 = pDataFim.toString();
			if( sData1.length() == 7 ) {
				sData1 = "0" + sData1;
			}
			if( sData2.length() == 7 ) {
				sData2 = "0" + sData2;
			}
			String sQuery =
			"SELECT" +
			" SUM(LANCAMENTOS.VALOR) " +
			"FROM" +
			" LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
			"WHERE" +
			" LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
			" LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";
			if( pProvisao ) {
				sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
			}
			else {
				sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
			}
			if( pConta.getCodConta() == null ) {
				sQuery += " CONTAS.TIPO = '" + pConta.getTipo() + "'";
			}
			else {
				sQuery += " CONTAS.COD_CONTA = " + pConta.getCodConta();
			}
			System.out.println( "SQL: " + sQuery );
			ResultSet rs = st.executeQuery( sQuery );
			if( rs.next() ) {
				do {
					if( rs.getDouble( 1 ) != 0 ) {
						System.out.println( "SQL: 1 Registro" );
						total = rs.getDouble( 1 );
					}
					else {
						System.out.println( "SQL: 0 Registros" );
					}
					break;
				}
				while( rs.next() );
			}
			else {
				System.out.println( "SQL: 0 Registros" );
			}
			return( total );
		}
		catch( SQLException e ) {
			System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
			return( 0.0 );
		}
	}
	/**
	* @brief Altera todos os campos de uma Conta baseado no cXdigo da Conta.
	* @param pConta A Conta jX prenchida a ser alterada.
	*/
	public void alterar( Conta pConta ) {
		try {
			Statement st = this.connection.createStatement();
			String sQuery =
			"UPDATE" +
			" CONTAS " +
			"SET " +
			" NOME = '" + pConta.getNome() + "'," +
			" TIPO = '" + pConta.getTipo() + "' " +
			"WHERE" +
			" COD_CONTA = " + pConta.getCodConta();
			System.out.println( "SQL: " + sQuery );
			if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
				System.out.println( "SQL: 0 Registros atualizados." );
			}
			else {
				System.out.println( "SQL: 1 Registro atualizado." );
			}
		}
		catch( SQLException e ) {
			System.out.println( "SQLException: " + e.getLocalizedMessage() );
		}
	}
	/**
	* @brief ObtXm um extrato de uma Conta em um perXodo.
	* @param pConta O cXdigo da Conta a ser obtido o extrato.
	* @param pDataIni A data inicial do perXodo de consulta.
	* @param pDataFim A data final do perXodo de consulta.
	* @param pProvisao Um boolean que se true, considera as provisXes.
	* @return Uma lista contendo a data de vencimento, data de quitaXXo, descriXXo e valor, ou lista vazia.
	*/
	public ArrayList<String[]> selectListaExtratoPeriodo( Conta pConta, Caixa pCaixa, Integer pDataIni, Integer pDataFim, boolean pProvisao ) {
		ArrayList<String[]> extratoList = new ArrayList<>();
		try {
			String sData1 = pDataIni.toString();
			String sData2 = pDataFim.toString();
			if( sData1.length() == 7 ) {
				sData1 = "0" + sData1;
			}
			if( sData2.length() == 7 ) {
				sData2 = "0" + sData2;
			}
			Statement st = this.connection.createStatement();
			String sQuery =
				"SELECT" +
				" LANCAMENTOS.DATA_VENCIMENTO," +
				" LANCAMENTOS.DATA_QUITACAO," +
				" LANCAMENTOS.DESCRICAO," +
				" LANCAMENTOS.VALOR," +
				" CAIXAS.NOME " +
				"FROM" +
				" LANCAMENTOS INNER JOIN CAIXAS ON (CAIXAS.COD_CAIXA = LANCAMENTOS.COD_CAIXA)" +
				"			  INNER JOIN CONTAS ON (CONTAS.COD_CONTA = LANCAMENTOS.COD_CONTA) " +
				"WHERE" +
				" LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
				" LANCAMENTOS.DATA_VENCIMENTO <= " + sData2;
			if( pProvisao ) {
				sQuery += " AND LANCAMENTOS.DATA_QUITACAO >= 0";
			}
			else {
				sQuery += " AND LANCAMENTOS.DATA_QUITACAO > 0";
			}
			if( pConta.getCodConta() != null ) {
				sQuery += " AND LANCAMENTOS.COD_CONTA = " + pConta.getCodConta();
			}
			else {
				sQuery += " AND CONTAS.TIPO = '" + pConta.getTipo() + "'";
			}
			if( pCaixa.getCodCaixa() != null ) {
				sQuery += " AND LANCAMENTOS.COD_CAIXA = " + pCaixa.getCodCaixa();
			}
			sQuery +=
				" ORDER BY" +
				" LANCAMENTOS.DATA_VENCIMENTO";
			System.out.println( "SQL: " + sQuery );
			ResultSet rs = st.executeQuery( sQuery );
			if( rs.next() ) {
				do {
					if( rs.getInt( 1 ) != 0 ) {
						String linha[] = new String[5];
						linha[0] = DateTools.formatDataIntToStringBR( rs.getInt( 1 ) ); // data_vencimento
						linha[1] = DateTools.formatDataIntToStringBR( rs.getInt( 2 ) ); // data_quitacao
						linha[2] = rs.getString( 3 );									// descricao
						linha[3] = ValueTools.format( rs.getDouble( 4 ) );				// valor
						linha[4] = rs.getString( 5 );									// nomecaixa
						extratoList.add( linha );
					}
				}
				while( rs.next() );
				System.out.println( "SQL: " + extratoList.size() + " Registro" );
			}
			else {
				System.out.println( "SQL: 0 Registros" );
			}
			return( extratoList );
		}
		catch( SQLException e ) {
			System.out.println( "SQLException: " + e.getLocalizedMessage() );
			return( extratoList );
		}
	}
	public boolean existeRegistro( Conta pConta ) {
		try {
			Statement st = this.connection.createStatement( ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY );
			String sql = "SELECT SUM(VALOR) FROM LANCAMENTOS WHERE COD_CONTA = " + pConta.getCodConta();
			ResultSet rs = st.executeQuery( sql );
			System.out.println( "SQL: " + sql );
			if( rs.next() ) {
				do {
					System.out.println( "SQL: Conta em uso: " + (rs.getDouble( 1 ) > 0D) );
					return( rs.getDouble( 1 ) > 0D );
				}
				while( rs.next() );
			}
			else {
				return( false );
			}
		}
		catch( SQLException ex ) {
			System.out.println( "SQLException: " + ex.getLocalizedMessage() );
			return( false );
		}
	}
}