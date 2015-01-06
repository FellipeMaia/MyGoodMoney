/**
* @file BD.java
* @brief Contém métodos de acesso ao banco de dados.
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

import java.io.File;
import java.sql.*;

/**
* @class BD
*/
public class BD {
	private Connection connection;

	/**
	 * @brief Construtor da classe BD
	 */
	public BD() {
		conectar();
	}

	public String getDatabaseURL() {
		if( System.getProperty( "os.name" ).toUpperCase().contains( "WIN" ) ) {
			String base = System.getenv("APPDATA");
			File f = new File( base );
			if( f.exists() && f.isDirectory() ) {
				return( "jdbc:sqlite:" + f.getAbsolutePath() + "\\MyGoodMoney.db" );
			}
			else {
				return( "jdbc:sqlite:" + System.getProperty("user.home").substring( 0, System.getProperty("user.home").length()-1 ) + "\\MyGoodMoney.db" );
			}
		}
		else {
			File f = new File( System.getProperty( "user.home" ) );
			return( "jdbc:sqlite:" + f.getAbsolutePath() + "/.MyGoodMoney/MyGoodMoney.db" );
		}
	}

	private void conectar() {
		try {
			Class.forName( "org.sqlite.JDBC" );
			System.out.println( "Driver: " + getDatabaseURL() );
			this.connection = DriverManager.getConnection( getDatabaseURL(), "", "" );
			System.out.println( "Conexão estabelecida!" );
		}
		catch( SQLException ex ) {
			ex.printStackTrace();
		}
		catch( ClassNotFoundException ex ) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection(){
		return( this.connection );
	}

	/**
	 * @brief Fecha a conexão com o banco de dados.
	 */
	public void fecharConexao() {
		try {
			this.connection.close();
		}
		catch( SQLException ex ) {
			ex.printStackTrace();
		}
	}

	/**
	 * @brief Cria o banco de dados
	 * @param psCaminhoCompleto Contém o caminho absoluto para o banco de dados mais o nome do arquivo.
	 * @throws ClassNotFoundException
	 */
	public void criarBancoDeDados( String psCaminhoCompleto ) throws ClassNotFoundException {
		try {
			if( this.connection == null ) {
				conectar();
			}

			System.out.println( "SQL: SQLite Conectado!" );

			String[] comandosSQL = {
				"CREATE TABLE IF NOT EXISTS CONTAS " +
				"(" +
				" COD_CONTA INTEGER UNIQUE NOT NULL," +
				" NOME VARCHAR(30) NOT NULL," +
				" TIPO CHAR NOT NULL," +
				" PRIMARY KEY(COD_CONTA) " +
				")",
				"CREATE TABLE IF NOT EXISTS CAIXAS " +
				"(" +
				" COD_CAIXA INTEGER UNIQUE NOT NULL," +
				" NOME VARCHAR(30) NOT NULL," +
				" SALDO FLOAT NOT NULL," +
				" LIMITE CHAR," +
				" VALOR_LIMITE FLOAT," +
				" PRIMARY KEY(COD_CAIXA) " +
				")",
				"CREATE TABLE IF NOT EXISTS LANCAMENTOS " +
				"(" +
				" COD_LANCAMENTO INTEGER UNIQUE NOT NULL," +
				" DATA_EMISSAO INTEGER NOT NULL," +
				" DATA_VENCIMENTO INTEGER NOT NULL," +
				" DATA_QUITACAO INTEGER NOT NULL," +
				" DESCRICAO VARCHAR(50) NOT NULL," +
				" VALOR FLOAT NOT NULL," +
				" COD_CONTA INTEGER NOT NULL REFERENCES conta," +
				" COD_CAIXA INTEGER NOT NULL REFERENCES caixa," +
				" PRIMARY KEY(COD_LANCAMENTO) " +
				")"
			};

			Statement st = this.connection.createStatement();
			System.out.println( "SQL: Criando tabelas.." );

			for( String querySQL : comandosSQL ) {
				System.out.println( "SQL: " + querySQL );

				if( st.executeUpdate( querySQL ) == Statement.EXECUTE_FAILED ) {
					System.out.println( "SQL: Erro ao executar: " + querySQL );
					this.connection.rollback();
					break;
				}
			}
			this.connection.commit();
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
	}
}