/**
 * @file CaixaDAO.java
 * @brief Contém métodos de acesso a entidade Caixa do banco de dados.
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CaixaDAO {
  private final Connection connection;

  public CaixaDAO() {
    this.connection = ConnectionHolder.getConnection();
  }

  public ArrayList<Caixa> selectListaTodosRegistros() {
    ArrayList<Caixa> caixasList = new ArrayList<Caixa>();

    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " COD_CAIXA," +
        " NOME," +
        " SALDO, " +
        " LIMITE," +
        " VALOR_LIMITE " +
        "FROM" +
        " CAIXAS " +
        "ORDER BY" +
        " NOME";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getString( 2 ) != null ) {
            Caixa caixa = new Caixa();
            caixa.setCodCaixa( rs.getInt( "COD_CAIXA" ) );
            caixa.setNome( rs.getString( "NOME" ) );
            caixa.setSaldo( rs.getDouble( "SALDO" ) );
            caixa.setLimite( rs.getString( "LIMITE" ).charAt(0) );
            caixa.setValorLimite( rs.getDouble( "VALOR_LIMITE" ) );

            caixasList.add( caixa );
          }
        }
        while( rs.next() );

        System.out.println( "SQL: " + caixasList.size() + " Registro(s)" );
      }
      else {
        System.out.println( "SQL: 0 Registros" );
      }
      return( caixasList );
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      if( e.getErrorCode() == 1 ) {
        // 1 = SQLITE_ERROR = Error or missing database
        System.out.println( "SQL: Banco de dados não encontrado! Criando.." );
        ConnectionHolder.criarTabelas();
      }
      return( caixasList );
    }
  }

  /**
   * @brief Exclui um caixa do banco de dados.
   * @param pCaixa Um caixa a ser excluído.
   */
  public void excluir( Caixa pCaixa ) {
    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "DELETE FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + pCaixa.getCodCaixa();

      System.out.println( "SQL: " + sQuery );
      
      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros Apagados." );
      }
      else {
        System.out.println( "SQL: 1 Registro Apagado." );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
   * @brief Altera todos os campos de um Caixa baseado no código do Caixa.
   * @param pCaixa Um Caixa já preenchido a ser alteado.
   */
  public void alterar( Caixa pCaixa ) {
    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE" +
        " CAIXAS " +
        "SET " +
        " NOME = '" + pCaixa.getNome() + "'," +
        " VALOR_LIMITE = " + pCaixa.getValorLimite() + " " +
        "WHERE" +
        " COD_CAIXA = " + pCaixa.getCodCaixa();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros alterados." );
      }
      else {
        System.out.println( "SQL: 1 Registro alterado." );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
   * @brief Insere um caixa no banco de dados.
   * @param pCaixa Um caixa a ser inserido.
   */
  public void inserir( Caixa pCaixa ) {
    try {
      Statement st = this.connection.createStatement();
      double saldo = (pCaixa.getSaldo().equals( Double.NaN ) )? 0.0 : pCaixa.getSaldo();
      double valorLimite = (pCaixa.getValorLimite().equals( Double.NaN ) )? 0.0 : pCaixa.getValorLimite();

      String sQuery = 
        "INSERT INTO CAIXAS (NOME, SALDO, LIMITE, VALOR_LIMITE)" +
        " VALUES" +
        " (" +
        " '" + pCaixa.getNome() + "'," +   // NOME
        "  " + saldo + "," +               // SALDO
        " '" + pCaixa.getLimite() + "'," + // LIMITE
        " " + valorLimite +                // VALOR_LIMITE
        " )";

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros incluidos" );
      }
      else {
        System.out.println( "SQL: 1 Registro incluido" );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Obtém um Caixa.
  * @param pCodCaixa O código do Caixa.
  * @return Um Caixa válido, se encontrado, ou um Caixa vazio se não.
  */
  public Caixa selectRegistro( Integer pCodCaixa ) {
    Caixa caixa = new Caixa();

    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " COD_CAIXA," +
        " NOME," +
        " SALDO," +
        " LIMITE," +
        " VALOR_LIMITE " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + pCodCaixa;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getString( 2 ) != null ) {
            caixa.setCodCaixa( rs.getInt( "COD_CAIXA" ) );
            caixa.setNome( rs.getString( "NOME" ) );
            caixa.setSaldo( rs.getDouble( "SALDO" ) );
            caixa.setLimite( rs.getString( "LIMITE").charAt( 0 ) );
            caixa.setValorLimite( rs.getDouble( "VALOR_LIMITE" ) );
            
            System.out.println( "SQL: 1 Registro" );
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
      return( caixa );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( caixa );
    }
  }

  /**
   * @brief Subtrai um valor do saldo de um Caixa.
   * @param pCodCaixa O Código do caixa.
   * @param pValor O valor a ser subtraído.
   */
  public void subtrairDoSaldo( int pCodCaixa, double pValor ) {
    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE CAIXAS " +
        "SET " +
        " SALDO = (SELECT SALDO FROM CAIXAS WHERE COD_CAIXA = " + pCodCaixa + ")-" + pValor + " WHERE COD_CAIXA = " + pCodCaixa;

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros atualizados" );
      }
      else {
        System.out.println( "SQL: 1 Registro atualizado" );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
   * @brief Adiciona um valor do saldo de um Caixa.
   * @param pCodCaixa O Código do caixa.
   * @param pValor O valor a ser adicionado.
   */
  public void adicionarAoSaldo( int pCodCaixa, double pValor ) {
    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE CAIXAS " +
        "SET " +
        " SALDO = (SELECT SALDO FROM CAIXAS WHERE COD_CAIXA = " + pCodCaixa + ")+" + pValor + " WHERE COD_CAIXA = " + pCodCaixa;

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros atualizados" );
      }
      else {
        System.out.println( "SQL: 1 Registro atualizado" );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
    }
  }

  /**
   * @param pCodCaixa
   * @brief Obtém o saldo de um Caixa.
   * @return O saldo do caixa se encontrado ou Double.NaN se não encontrado.
   */
  public double selectSaldo( Integer pCodCaixa ) {
    try
    {
      double saldo = 0;
      Statement st = this.connection.createStatement();
      String sQuery;
      
      if( pCodCaixa == null ) {
        sQuery =
          "SELECT" +
          " SUM(SALDO) " +
          "FROM" +
          " CAIXAS";
      }
      else {
        sQuery =
          "SELECT" +
          " SALDO " +
          "FROM" +
          " CAIXAS " +
          "WHERE" +
          " COD_CAIXA = " + pCodCaixa;
      }

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getDouble( 1 ) != 0 ) {
            System.out.println( "SQL: 1 Registro" );
            saldo = rs.getDouble( 1 );
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
      return( saldo );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( 0.0 );
    }
  }

  /**
   * @brief Obtém o valor total de saída de um Caixa.
   * @param pCodCaixa O nome de um Caixa.
   * @param pDataIni A data inicial da consulta.
   * @param pDataFim A data final da consulta.
   * @param pProvisao Um boolean que se true, considera as provisões.
   * @return O valor total de saídas do caixa, se encontrado, ou 0 se não encontrado.
   */
  public double selectTotalSaida( Integer pCodCaixa, int pDataIni, int pDataFim, boolean pProvisao ) {
    try {
      double total = 0;
      Statement st = this.connection.createStatement();
      String sData1 = pDataIni + "";
      String sData2 = pDataFim + "";

      if( sData1.length() == 7 ) {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 ) {
        sData2 = "0" + sData2;
      }

      String sQuery;
      
      if( pCodCaixa == null ) {
        sQuery =
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
          " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";
      }
      else {
        sQuery =
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
          " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "WHERE" +
          " CAIXAS.COD_CAIXA = " + pCodCaixa + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";
      }

      if( pProvisao ) {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.TIPO = 'D'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getDouble( 1 ) != 0 ) {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getDouble( 1 );
            break;
          }
          else {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
      }
      else {
        System.out.println( "SQL: 0 Registros" );
      }
      return( total );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( 0.0 );
    }
  }

  /**
   * @brief Obtém o total de entradas de um Caixa.
   * @param pCodCaixa O nome de um Caixa.
   * @param pDataIni A data inicial da consulta.
   * @param pDataFim A data final da consulta.
   * @param pProvisao Um boolean que se for true, considera provisões.
   * @return O total de entrada do caixa, se encontrado, ou 0 se não encontrado.
   */
  public double selectTotalEntrada( Integer pCodCaixa, int pDataIni, int pDataFim, boolean pProvisao ) {
    try {
      double total = 0;
      Statement st = this.connection.createStatement();
      String sData1 = pDataIni + "";
      String sData2 = pDataFim + "";

      if( sData1.length() == 7 ) {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 ) {
        sData2 = "0" + sData2;
      }

      String sQuery;
      
      if( pCodCaixa == null ) {
        sQuery =
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
          " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";
      }
      else {
        sQuery =
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
          " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "WHERE" +
          " CAIXAS.COD_CAIXA = " + pCodCaixa + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";
      }

      if( pProvisao ) {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.TIPO = 'C'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getDouble( 1 ) == 0 ) {
            System.out.println( "SQL: 0 Registros" );
          }
          else {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getDouble( 1 );
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
   * @brief Obtém o total de entrada provisionada de um Caixa em um período.
   * @param pCodCaixa O nome do Caixa.
   * @param pDataIni A data inicial do período de consulta.
   * @param pDataFim A data final do período de consulta.
   * @return O valor total das entradas provisionadas no período, se encontrado, ou 0.
   */
  public double selectTotalEntradaProvisionada( Integer pCodCaixa, int pDataIni, int pDataFim ) {
    try {
      double totalEntradas = 0;
      Statement st = this.connection.createStatement();
      String sQuery;
      
      if( pCodCaixa == null ) {
      sQuery = 
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "             INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + pDataIni + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + pDataFim + " AND" +
          " CONTAS.TIPO = 'C' AND" +
          " LANCAMENTOS.DATA_QUITACAO = 0";
      }
      else {
        sQuery = 
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "             INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + pDataIni + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + pDataFim + " AND" +
          " CONTAS.TIPO = 'C' AND" +
          " LANCAMENTOS.DATA_QUITACAO = 0 AND " +
          " CAIXAS.COD_CAIXA = " + pCodCaixa;
      }

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getBigDecimal( 1 ) != null ) {
            System.out.println( "SQL: 1 Registro" );
            totalEntradas = rs.getBigDecimal( 1 ).doubleValue();
          }
          else {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
      }
      else {
        System.out.println( "SQL: 0 Registros" );
      }
      return( totalEntradas );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( 0.0 );
    }
  }

  /**
   * @brief Obtém o total de saída provisionada de um Caixa em um período.
   * @param pCodCaixa O nome do Caixa.
   * @param pDataIni A data inicial do período de consulta.
   * @param pDataFim A data final do período de consulta.
   * @return O valor total das entradas provisionadas no período, se encontrado, ou 0.
   */
  public double selectTotalSaidaProvisionada( Integer pCodCaixa, int pDataIni, int pDataFim ) {
    try {
      double totalSaidas = 0;
      Statement st = this.connection.createStatement();
      String sQuery;
      
      if( pCodCaixa == null ) {
      sQuery = 
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "             INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + pDataIni + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + pDataFim + " AND" +
          " CONTAS.TIPO = 'D' AND" +
          " LANCAMENTOS.DATA_QUITACAO = 0";
      }
      else {
        sQuery = 
          "SELECT" +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "             INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + pDataIni + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + pDataFim + " AND" +
          " CONTAS.TIPO = 'D' AND" +
          " LANCAMENTOS.DATA_QUITACAO = 0 AND " +
          " CAIXAS.COD_CAIXA = " + pCodCaixa;
      }

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getBigDecimal( 1 ) != null ) {
            System.out.println( "SQL: 1 Registro" );
            totalSaidas = rs.getDouble( 1 );
          }
          else {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
      }
      else {
        System.out.println( "SQL: 0 Registros" );
      }
      return( totalSaidas );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( 0.0 );
    }
  }
  
  public double selectLimite( Integer pCodCaixa ) {
    try {
      double total = 0;
      Statement st = this.connection.createStatement();

      String sQuery;
      
      if( pCodCaixa == null ) {
        sQuery =
          "SELECT" +
          " SUM(CAIXAS.VALOR_LIMITE) " +
          "FROM" +
          " CAIXAS";
      }
      else {
        sQuery =
          "SELECT" +
          " CAIXAS.VALOR_LIMITE " +
          "FROM" +
          " CAIXAS " +
          "WHERE" +
          " CAIXAS.COD_CAIXA = " + pCodCaixa;
      }

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getDouble( 1 ) == 0 ) {
            System.out.println( "SQL: 0 Registros" );
          }
          else {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getDouble( 1 );
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
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( 0.0 );
    }
  }
  public boolean existeRegistro( Caixa pCaixa ) {
    try {
      Statement st = this.connection.createStatement();
      String sql = "SELECT SUM(VALOR) FROM LANCAMENTOS WHERE COD_CAIXA = " + pCaixa.getCodCaixa();
      ResultSet rs = st.executeQuery( sql );
      
      System.out.println( "SQL: " + sql );
      
      if( rs.next() ) {
        do {
          System.out.println( "SQL: Caixa em uso: " + (rs.getDouble( 1 ) > 0D) );
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
