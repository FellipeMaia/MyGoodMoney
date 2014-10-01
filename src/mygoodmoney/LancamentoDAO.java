/**
 * @file LancamentoDAO.java
 * @brief Contém métodos de acesso a entidade Conta do banco de dados.
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

public class LancamentoDAO {
  private final Connection connection;
  
  public LancamentoDAO() {
    this.connection = ConnectionHolder.getConnection();
  }
  
  /**
   * @brief Altera todos os campos de um Lancamento baseado no código do Lancamento.
   * @param pLancamento Um Lancamento ja preenchido a ser alterado.
   */
  public void alterar( Lancamento pLancamento ) {
    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE" +
        " LANCAMENTOS " +
        "SET" +
        " DATA_EMISSAO = " + pLancamento.getDataEmissao() + "," +
        " DATA_VENCIMENTO = " + pLancamento.getDataVencimento() + "," +
        " DATA_QUITACAO = " + pLancamento.getDataQuitacao() + "," +
        " DESCRICAO = '" + pLancamento.getDescricao() + "'," +
        " VALOR = " + pLancamento.getValor() + "," +
        " COD_CONTA = " + pLancamento.getConta().getCodConta() + "," +
        " COD_CAIXA = " + pLancamento.getCaixa().getCodCaixa() + " " +
        "WHERE" +
        " COD_LANCAMENTO = " + pLancamento.getCodLancamento();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros atualizados." );
      }
      else {
        System.out.println( "SQL: 1 Registro atualizado." );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }
  /**
   * @brief Obtém todos os Lancamentos de um período.
   * @param pDataIni A data inicial do período de consulta.
   * @param pDataFim A data final do período de consulta.
   * @return Uma lista de Lancamentos se houver, ou uma lista vazia.
   */
  public ArrayList<Lancamento> selectListaRegistrosPeriodo( int pDataIni, int pDataFim ) {
    ArrayList<Lancamento> alLancamento = new ArrayList<>();

    try {
      String data1 = pDataIni + "";
      String data2 = pDataFim + "";

      if( data1.length() == 7 ) {
        data1 = "0" + data1;
      }

      if( data2.length() == 7 ) {
        data2 = "0" + data2;
      }

      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " LANCAMENTOS.COD_LANCAMENTO AS COD_LANCAMENTO," +
        " LANCAMENTOS.DATA_EMISSAO AS EMISSAO," +
        " LANCAMENTOS.DATA_VENCIMENTO AS VENCIMENTO," +
        " LANCAMENTOS.DATA_QUITACAO AS QUITACAO," +
        " LANCAMENTOS.DESCRICAO AS DESCRICAO," +
        " LANCAMENTOS.VALOR AS VALOR," +
        " CONTAS.COD_CONTA AS COD_CONTA," +
        " CONTAS.TIPO AS TIPO_CONTA," +
        " CONTAS.NOME AS NOME_CONTA," +
        " CAIXAS.COD_CAIXA AS COD_CAIXA," +
        " CAIXAS.NOME AS NOME_CAIXA," +
        " CAIXAS.SALDO AS SALDO_CAIXA " +
        "FROM" +
        " LANCAMENTOS LEFT JOIN CONTAS ON (CONTAS.COD_CONTA = LANCAMENTOS.COD_CONTA)" +
        "             LEFT JOIN CAIXAS ON (CAIXAS.COD_CAIXA = LANCAMENTOS.COD_CAIXA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " " +
        "ORDER BY" +
        " LANCAMENTOS.DATA_VENCIMENTO";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          Lancamento lancamento = new Lancamento();
          Conta conta = new Conta();
          Caixa caixa = new Caixa();

          if( rs.getInt( "EMISSAO" ) != 0 ) {
            lancamento.setCodLancamento( rs.getInt( "COD_LANCAMENTO" ) );
            lancamento.setDataEmissao( rs.getInt( "EMISSAO" ) );
            lancamento.setDataVencimento( rs.getInt( "VENCIMENTO" ) );
            lancamento.setDataQuitacao( rs.getInt( "QUITACAO" ) );
            lancamento.setDescricao( rs.getString( "DESCRICAO" ) );
            lancamento.setValor( rs.getDouble( "VALOR" ) );
            lancamento.setPago( (rs.getInt( "QUITACAO" ) > 0)? 'S' : 'N' );
            
            conta.setCodConta( rs.getInt( "COD_CONTA" ) );
            
            if( conta.getCodConta() > 0 ) {
              conta.setNome( rs.getString( "NOME_CONTA" ) );
              conta.setTipo( rs.getString( "TIPO_CONTA" ).charAt( 0 ) );
              lancamento.setConta( conta );
            }
            else {
              lancamento.setConta( null );
            }
            
            caixa.setCodCaixa( rs.getInt( "COD_CAIXA" ) );
            caixa.setNome( rs.getString( "NOME_CAIXA" ) );
            caixa.setSaldo( rs.getDouble( "SALDO_CAIXA" ) );
            lancamento.setCaixa( caixa );

            alLancamento.add( lancamento );
          }
        }
        while( rs.next() );

        System.out.println( "SQL: " + alLancamento.size() + " Registro" );
      }
      else {
        System.out.println( "SQL: 0 Registros" );
      }
      return( alLancamento );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      if( e.getErrorCode() == 1 ) {
        // 1 = SQLITE_ERROR = Error or missing database
        System.out.println( "SQL: Banco de dados não encontrado! Criando.." );
        ConnectionHolder.criarTabelas();
      }
      return( alLancamento );
    }
  }
  /**
   * @brief Exluir um Lancamento do banco de dados.
   * @param pLancamento Um Lancamento a ser excluído.
   */
  public void excluir( Lancamento pLancamento ) {
    try {
      Statement st = this.connection.createStatement();
      String sQuery =
        "DELETE FROM" +
        " LANCAMENTOS " +
        "WHERE" +
        " LANCAMENTOS.COD_LANCAMENTO = " + pLancamento.getCodLancamento();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED ) {
        System.out.println( "SQL: 0 Registros excluidos." );
      }
      else {
        System.out.println( "SQL: 1 Registro excluido." );
      }
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }
  /**
   * @brief Obtém o valor total de movimento por período e tipo de conta.
   * @param pConta O tipo de uma Conta 'C' ou 'D'.
   * @param pDataIni A data inicial do período de consulta.
   * @param pDataFim A data final do período de consulta.
   * @param pProvisao Um boolean que se true, considera as provisões.
   * @return O valor total para aquele tipo de conta, se maior do que 0, ou 0.
   */
  public double selectTotalMovimentoPeriodo( Conta pConta, int pDataIni, int pDataFim, boolean pProvisao ) {
    try {
      double total = 0;
      Statement st = this.connection.createStatement();
      String data1 = pDataIni + "";
      String data2 = pDataFim + "";

      if( data1.length() == 7 ) {
        data1 = "0" + data1;
      }

      if( data2.length() == 7 ) {
        data2 = "0" + data2;
      }

      String sQuery =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " AND";

      if( pProvisao ) {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.TIPO = '" + pConta.getTipo() + "'";

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
   * @brief Obtém um Lancamento específico.
   * @param pCodLancamento A data de emissão ou quitação formatada do Lancamento.
   * @return Um Lancamento válido, se encontrado, ou Lancamento vazio se não;
   */
  public Lancamento selectRegistro( Integer pCodLancamento ) {
    Lancamento lancamento = new Lancamento();

    try {
      Statement st = this.connection.createStatement();

      String sQuery = 
        "SELECT " +
        " LANCAMENTOS.COD_LANCAMENTO AS COD_LANCAMENTO," +
        " LANCAMENTOS.DATA_EMISSAO AS EMISSAO," +
        " LANCAMENTOS.DATA_VENCIMENTO AS VENCIMENTO," +
        " LANCAMENTOS.DATA_QUITACAO AS QUITACAO," +
        " LANCAMENTOS.DESCRICAO AS DESCRICAO," +
        " LANCAMENTOS.VALOR AS VALOR," +
        " CONTAS.COD_CONTA AS COD_CONTA," +
        " CONTAS.NOME AS NOME_CONTA," +
        " CONTAS.TIPO AS TIPO_CONTA," +
        " CAIXAS.COD_CAIXA AS COD_CAIXA," +
        " CAIXAS.NOME AS NOME_CAIXA," +
        " CAIXAS.SALDO AS SALDO_CAIXA " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (CONTAS.COD_CONTA = LANCAMENTOS.COD_CONTA)" +
        "             INNER JOIN CAIXAS ON (CAIXAS.COD_CAIXA = LANCAMENTOS.COD_CAIXA)" +
        "WHERE" +
        " COD_LANCAMENTOS = " + pCodLancamento;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getInt( 1 ) != 0 ) {
            lancamento.setCodLancamento( rs.getInt( "COD_LANCAMENTO" ) );
            lancamento.setDataEmissao( rs.getInt( "EMISSAO" ) );
            lancamento.setDataVencimento( rs.getInt( "VENCIMENTO" ) );
            lancamento.setDataQuitacao( rs.getInt( "QUITACAO" ) );
            lancamento.setDescricao( rs.getString( "DESCRICAO" ) );
            lancamento.setValor( rs.getDouble( "VALOR" ) );
            Conta conta = new Conta();
            conta.setCodConta( rs.getInt( "COD_CONTA" ) );

            if( conta.getCodConta() > 0 ) {
              conta.setNome( rs.getString( "NOME_CONTA" ) );
              conta.setTipo( rs.getString( "TIPO_CONTA" ).charAt(0) );
              lancamento.setConta( conta );
            }
            else {
              lancamento.setConta( null );
            }

            Caixa caixa = new Caixa();
            caixa.setCodCaixa( rs.getInt( "COD_CAIXA" ) );
            caixa.setNome( rs.getString( "NOME_CAIXA" ) );
            caixa.setSaldo( rs.getDouble( "SALDO_CAIXA" ) );
            lancamento.setCaixa( caixa );
            
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
      return( lancamento );
    }
    catch( SQLException e ) {
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
      return( lancamento );
    }
  }
  /**
   * @brief Obtém os Lancamentos para inserir no gráfico do painel Home.
   * @param pCodCaixa O código do Caixa a ser consultado.
   * @param pDataIni A data inicial do período a ser consultado.
   * @param pDataFim A data final do período a ser consultado.
   * @param pProvisao Um boolean, que se true, considera as provisões.
   * @return Uma lista com o nome de uma Conta e o valor total do período.
   */
  public ArrayList<Object[]> selectLancamentosGrafico( Integer pCodCaixa, int pDataIni, int pDataFim, boolean pProvisao ) {
    ArrayList<Object[]> alObject = new ArrayList<>();

    try {
      Statement st = this.connection.createStatement();
      String sQuery;
      
      if( pCodCaixa == null ) {
        sQuery = 
          "SELECT" +
          " CONTAS.NOME," +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "WHERE" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + pDataIni + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + pDataFim + " AND" +
          " CONTAS.TIPO = 'D' AND";
      }
      else {
        sQuery = 
          "SELECT" +
          " CONTAS.NOME," +
          " SUM(LANCAMENTOS.VALOR) " +
          "FROM" +
          " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
          "WHERE" +
          " LANCAMENTOS.COD_CAIXA = " + pCodCaixa + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO >= " + pDataIni + " AND" +
          " LANCAMENTOS.DATA_VENCIMENTO <= " + pDataFim + " AND" +
          " CONTAS.TIPO = 'D' AND";
      }

      if( pProvisao ) {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0";
      }
      else {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0";
      }

      sQuery += " GROUP BY CONTAS.NOME";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() ) {
        do {
          if( rs.getString( 1 ) != null ) {
            Object[] select = new Object[2];

            select[0] = rs.getString( 1 );
            select[1] = rs.getDouble( 2 );
            
            alObject.add( select );
          }
          else {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + alObject.size() + " Registro(s)" );
      }
      else {
        System.out.println( "SQL: 0 Registros" );
      }
      return( alObject );
    }
    catch( SQLException e ) {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( alObject );
    }
  }
  /**
   * @brief Insere um Lancamento no banco de dados.
   * @param pLancamento Lancamento a ser inserido.
   */
  public void inserir( Lancamento pLancamento ) {
    try {
      Statement st = this.connection.createStatement();
      
      Conta c;
      
      if( pLancamento.getConta() == null ) {
        c = new Conta();
        c.setCodConta( 0 );
      }
      else {
        c = pLancamento.getConta();
      }
      
      String sQuery =
        "INSERT INTO LANCAMENTOS (DATA_EMISSAO, DATA_VENCIMENTO, DATA_QUITACAO, DESCRICAO, VALOR, COD_CONTA, COD_CAIXA)" +
        " VALUES (" +
        " " + pLancamento.getDataEmissao() + "," +    // DATA_EMISSAO
        " " + pLancamento.getDataVencimento() + "," + // DATA_VENCIMENTO
        " " + pLancamento.getDataQuitacao() + "," +   // DATA_QUITACAO
        " '" + pLancamento.getDescricao() + "'," +    // DESCRICAO
        " " + pLancamento.getValor() + "," +          // VALOR
        " " + c.getCodConta() + "," +                 // CONTA
        " " + pLancamento.getCaixa().getCodCaixa() +  // CAIXA
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
      System.out.println( "SQLException: " + e.getLocalizedMessage() );
    }
  }
}