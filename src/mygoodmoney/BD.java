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
import java.util.ArrayList;

/**
* @class BD
*/
public class BD
{
  private Connection connection;       /**< A variável conexão */
  private String     sCaminhoAbsoluto; /**< O caminho absoluto para o banco de dados */
  
  /**
  * @brief Construtor da classe BD
  */
  public BD()
  {
    this.connection = null;
  }

  /**
  * @brief Exporta o banco de dados em uso para um arquivo schema.sql
  * @note Funcionalidade em desenvolvimento.
  */
  public void exportar()
  {
    try
    {
      Statement st = this.connection.createStatement();
      String[] sComandos =
      {
        ".output WealCash.sql",
        ".dump",
        ".output stdout"
      };
      
      for( String sQuery : sComandos )
      {
        System.out.println( "SQL: " + sQuery );
        st.execute( sQuery );
      }
      
      File sqlExportado = new File( "WealCash.sql" );
      
      if( sqlExportado.canRead() && sqlExportado.canWrite() )
      {
        Mensagem.info( "Banco de dados exportado com sucesso para: WealCash.sql", null );
      }
      else
      {
        Mensagem.info( "Erro ao exportar Banco de Dados!", null );
      }
    }
    catch( SQLException ex )
    {
      System.out.println( "SQL Exception: " + ex.getLocalizedMessage() );
    }
  }
  /**
  * @brief Obtém todas as contas.
  * @return Um ArrayList<Conta> contendo as contas ou um ArrayList vazio.
  */
  public ArrayList<Conta> selectAlTodasAsContas()
  {
    ArrayList<Conta> contasList = new ArrayList<>();

    try
    {
      Statement st = this.connection.createStatement();
      String sql = "SELECT NOME, TIPO " +
                   "FROM CONTAS " +
                   "ORDER BY NOME";
      
      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );

      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            Conta conta = new Conta();
            conta.setSNome( rs.getString( 1 ) );
            conta.setCTipo( rs.getString( 2 ).charAt( 0 ) );
            contasList.add( conta );
          } 
        }
        while( rs.next() );

        System.out.println( "SQL: " + contasList.size() + " Registro" );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( contasList );
    }
    catch( SQLException | NullPointerException ex )
    {
      System.out.println( "SQL Exception: " + ex.getLocalizedMessage() );
      return( contasList );
    }
  }

  /**
  * @brief Obtém todas os caixas.
  * @return Um ArrayList<Caixa> contendo os caixas ou um ArrayList vazio.
  */
  public ArrayList<Caixa> selectAlTodosOsCaixas()
  {
    ArrayList<Caixa> caixasList = new ArrayList<>();

    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " NOME," +
        " SALDO " +
        "FROM" +
        " CAIXAS " +
        "ORDER BY" +
        " NOME";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null && rs.getBigDecimal( 2 ) != null )
          {
            Caixa caixa = new Caixa();
            caixa.setSNome( rs.getString( 1 ) );
            caixa.setDSaldo( rs.getBigDecimal( 2 ).doubleValue() );

            caixasList.add( caixa );
          }
        }
        while( rs.next() );

        System.out.println( "SQL: " + caixasList.size() + " Registro" );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( caixasList );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( caixasList );
    }
  }

  /**
  * @brief Verifica se o arquivo informado na aba Configurações existe.
  * @param psCaminhoAbsoluto Uma String contendo o caminho absoluto e o nome do arquivo.
  * @return true se existe o arquivo informado ou false não existe.
  * @note Caso o arquivo exista, será chamdado o método carregarBDExistente().
  */
  public boolean existeBD( String psCaminhoAbsoluto )
  {
    File f = new File( psCaminhoAbsoluto );
    boolean b = f.canRead() && f.canWrite();

    if( b )
    {
      this.sCaminhoAbsoluto = psCaminhoAbsoluto;
      carregarBDExistente();
    }
    return( b );
  }

  /**
  * @brief Carrega um banco de dados a partir da variável local caminhoAbsoluto.
  */
  private void carregarBDExistente()
  {
    try
    {
      System.out.println( "Carregando BD existente: jdbc:sqlite:" + this.sCaminhoAbsoluto );
      Class.forName( "org.sqlite.JDBC" );
      this.connection = DriverManager.getConnection( "jdbc:sqlite:" + sCaminhoAbsoluto, "", "" );
      this.connection.setAutoCommit( false );

      System.out.println( "SQL: SQLite Conectado!" );
    }
    catch( ClassNotFoundException ex )
    {
      System.out.println( "Classe nao encontrada: org.sqlite.JDBC" );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Obtém o caminho absoluto para o banco de dados.
  * @return Uma String contendo o caminho absoluto.
  */
  public String getSCaminhoAbsoluto()
  {
    return( this.sCaminhoAbsoluto );
  }

  /**
  * @brief Fecha a conexão com o banco de dados.
  */
  public void fechar()
  {
    try
    {
      this.connection.close();
    }
    catch( SQLException ex )
    {
      System.out.println( "SQL Exception: " + ex.getLocalizedMessage() );
    }
  }

  /**
  * @brief Cria o banco de dados
  * @param psCaminhoCompleto Contém o caminho absoluto para o banco de dados mais o nome do arquivo.
  * @throws ClassNotFoundException
  */
  public void criarBancoDeDados( String psCaminhoCompleto ) throws ClassNotFoundException
  {
    try
    {
      this.sCaminhoAbsoluto = psCaminhoCompleto;
      System.out.println( "Criando BD: jdbc:sqlite:" + sCaminhoAbsoluto );
      Class.forName( "org.sqlite.JDBC" );
      this.connection = DriverManager.getConnection( "jdbc:sqlite:" + sCaminhoAbsoluto, "", "" );
      this.connection.setAutoCommit( false );

      System.out.println( "SQL: SQLite Conectado!" );

      String[] comandosSQL =
      {
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

      for( String querySQL : comandosSQL )
      {
        System.out.println( "SQL: " + querySQL );

        if( st.executeUpdate( querySQL ) == Statement.EXECUTE_FAILED )
        {
          System.out.println( "SQL: Erro ao executar: " + querySQL );
          this.connection.rollback();
          break;
        }
      }
      this.connection.commit();
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
    catch( ClassNotFoundException ex )
    {
      System.out.println( "Classe nao encontrada: org.sqlite.JDBC" );
    }
  }

  /**
  * @brief Insere uma conta no banco de dados.
  * @param pConta Uma conta a ser cadastrada.
  */
  public void inserirConta( Conta pConta )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "INSERT INTO CONTAS" +
        " (" +
        " NOME," +
        " TIPO" +
        " )" +
        " VALUES" +
        " (" +
        " '" + pConta.getSNome() + "'," +
        " '" + pConta.getCTipo() + "'" +
        " )";

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros Inseridos" );
        this.connection.rollback();
      }
      else
      {
        System.out.println( "SQL: 1 Registro Inserido" );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Exclui uma conta do banco de dados.
  * @param pConta Uma conta a ser excluída.
  */
  public void excluirConta( Conta pConta )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "DELETE FROM" +
        " CONTAS " +
        "WHERE" +
        " COD_CONTA = " + pConta.getICodConta();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros Apagados." );
      }
      else
      {
        System.out.println( "SQL: 1 Registro Apagado." );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Insere um caixa no banco de dados.
  * @param pCaixa Um caixa a ser inserido.
  */
  public void inserirCaixa( Caixa pCaixa )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "INSERT INTO CAIXAS" +
        " (" +
        " NOME," +
        " SALDO" +
        " )" +
        " VALUES" +
        " (" +
        " '" + pCaixa.getSNome() + "'," +
        " " +  pCaixa.getDSaldo() +
        " )";

      System.out.println( "SQL: " + sQuery );
      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: Erro ao executar: " + sQuery );
      }
      else
      {
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Exclui um caixa do banco de dados.
  * @param pCaixa Um caixa a ser excluído.
  */
  public void excluirCaixa( Caixa pCaixa )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "DELETE FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + pCaixa.getICodCaixa();

      System.out.println( "SQL: " + sQuery );
      
      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros Apagados." );
      }
      else
      {
        System.out.println( "SQL: 1 Registro Apagado." );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Insere um Lancamento no banco de dados.
  * @param pLancamento Um Lancamento a ser inserido.
  */
  public void inserirLancamento( Lancamento pLancamento )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "INSERT INTO LANCAMENTOS" +
        " (" +
        " DATA_EMISSAO," +
        " DATA_VENCIMENTO," +
        " DATA_QUITACAO," +
        " DESCRICAO," +
        " VALOR," +
        " COD_CONTA," +
        " COD_CAIXA" +
        " )" +
        " VALUES" +
        " (" +
        " " + pLancamento.getIDataEmissao() + "," +
        " " + pLancamento.getIDataVencimento() + "," +
        " " + pLancamento.getIDataQuitacao() + "," +
        " '" + pLancamento.getSDescricao() + "'," +
        " " + pLancamento.getDValor() + "," +
        " " + pLancamento.getICodConta() + "," +
        " " + pLancamento.getICodCaixa() +
        " )";

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: Erro ao executar: " + sQuery );
      }
      else
      {
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Exluir um Lancamento do banco de dados.
  * @param pLancamento Um Lancamento a ser excluído.
  */
  public void excluirLancamento( Lancamento pLancamento )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "DELETE FROM" +
        " LANCAMENTOS " +
        "WHERE" +
        " LANCAMENTOS.COD_LANCAMENTO = " + pLancamento.getICodLancamento();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros excluidos." );
      }
      else
      {
        System.out.println( "SQL: 1 Registro excluido." );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Subtrai um valor do saldo de um Caixa.
  * @param piCodCaixa O Código do caixa.
  * @param pdValor O valor a ser subtraído.
  */
  public void subtrairDoSaldoCaixa( int piCodCaixa, double pdValor )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE CAIXAS " +
        "SET " +
        " SALDO = (SELECT SALDO FROM CAIXAS WHERE COD_CAIXA = " + piCodCaixa + ")-" + pdValor + " WHERE COD_CAIXA = " + piCodCaixa;

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados" );
      }
      else
      {
        System.out.println( "SQL: 1 Registro atualizado" );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Adiciona um valor do saldo de um Caixa.
  * @param piCodCaixa O Código do caixa.
  * @param pdValor O valor a ser adicionado.
  */
  public void adicionarAoSaldoCaixa( int piCodCaixa, double pdValor )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE CAIXAS " +
        "SET " +
        " SALDO = (SELECT SALDO FROM CAIXAS WHERE COD_CAIXA = " + piCodCaixa + ")+" + pdValor + " WHERE COD_CAIXA = " + piCodCaixa;

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados" );
      }
      else
      {
        System.out.println( "SQL: 1 Registro atualizado" );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Obtém o tipo de uma Conta, Crédito ou Débito, a partir do seu código.
  * @param piCodConta O Código da conta.
  * @return O Tipo da conta se encontrado ou um caracter vazio (' ') se não encontrado.
  */
  public char selectCTipoConta( int piCodConta )
  {
    Character c = ' ';
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " TIPO " +
        "FROM" +
        " CONTAS " +
        "WHERE" +
        " COD_CONTA = " + piCodConta;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            c = rs.getString( 1 ).charAt( 0 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( c );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( c );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( c );
    }
  }

  /**
  * @brief Obtém o código de uma Conta a partir do seu nome.
  * @param psNome O nome da conta.
  * @return O código da conta se encontrado ou 0 se não encontrado.
  */
  public int selectICodConta( String psNome )
  {
    int iCod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " COD_CONTA " +
        "FROM" +
        " CONTAS " +
        "WHERE" +
        " NOME = '" + psNome + "'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            iCod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( iCod );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( iCod );
    }
  }
  /**
  * @brief Obtém o código de um Lancamento.
  * @param piData A data do Lancamento.
  * @param pdValor o valor do Lancamento.
  * @param piCodConta O Código da conta do Lancamento.
  * @param piCodCaixa O Código do caixa do Lancamento.
  * @return O Código do Lancamento se encontrado ou 0 se não encontrado.
  */
  public int selectICodLancamento( int piData, double pdValor, int piCodConta, int piCodCaixa )
  {
    int iCod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String data = piData + "";

      if( data.length() == 7 )
      {
        data = "0" + data;
      }

      String sQuery =
        "SELECT" +
        " LANCAMENTOS.COD_LANCAMENTO " +
        "FROM" +
        " LANCAMENTOS " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO = " + data + " AND" +
        " LANCAMENTOS.VALOR = " + pdValor + " AND" +
        " LANCAMENTOS.COD_CONTA = " + piCodConta + " AND" +
        " LANCAMENTOS.COD_CAIXA = " + piCodCaixa;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro: " + rs.getInt( 1 )  );
            iCod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( iCod );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( iCod );
    }
  }

  /**
  * @brief Obtém o código do caixa.
  * @param psNome O nome do caixa.
  * @return O Código do caixa se encontrado ou 0 se não encontrado.
  */
  public int selectICodCaixa( String psNome )
  {
    int iCod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" + 
        " COD_CAIXA " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " NOME = '" + psNome + "'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            iCod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( iCod );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( iCod );
    }
  }
  /**
  * @brief Obtém o saldo de um Caixa.
  * @param psNomeCaixa O nome de um Caixa.
  * @return O saldo do caixa se encontrado ou Double.NaN se não encontrado.
  */
  public double selectDSaldoCaixa( String psNomeCaixa )
  {
    double dSaldo = 0;

    try
    {
      Statement st = this.connection.createStatement();

      String sQuery =
        "SELECT" +
        " SALDO " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " NOME = '" + psNomeCaixa + "'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getDouble( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            dSaldo = rs.getDouble( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dSaldo );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dSaldo );
    }
  }

  /**
  * @brief Exclui todos os registros existentes do banco de dados.
  */
  public void excluirTodosRegistros()
  {
    try
    {
      Statement st = this.connection.createStatement();
      String[] sQuery =
      {
        "DELETE FROM CONTAS",
        "DELETE FROM CAIXAS",
        "DELETE FROM LANCAMENTOS"
      };

      for( String query : sQuery )
      {
        System.out.println( "SQL: " + query );
        
        if( st.executeUpdate( query ) == Statement.EXECUTE_FAILED )
        {
          System.out.println( "SQL: Erro ao executar: " + query );
          this.connection.rollback();
          break;
        }
      }
      this.connection.commit();
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Obtém o total de entradas de um Caixa.
  * @param psNomeCaixa O nome de um Caixa.
  * @param piDataIni A data inicial da consulta.
  * @param piDataFim A data final da consulta.
  * @param pbProvisao Um boolean que se for true, considera provisões.
  * @return O total de entrada do caixa, se encontrado, ou 0 se não encontrado.
  */
  public double selectDTotalEntradaPorCaixa( String psNomeCaixa, int piDataIni, int piDataFim, boolean pbProvisao )
  {
    double dTotal = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sData1 = piDataIni + "";
      String sData2 = piDataFim + "";

      if( sData1.length() == 7 )
      {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 )
      {
        sData2 = "0" + sData2;
      }

      String sQuery =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
        " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " CAIXAS.NOME = '" + psNomeCaixa + "' AND" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";

      if( pbProvisao )
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.TIPO = 'C'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getDouble( 1 ) == 0 )
          {
            System.out.println( "SQL: 0 Registros" );
          }
          else
          {
            System.out.println( "SQL: 1 Registro" );
            dTotal = rs.getDouble( 1 );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dTotal );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dTotal );
    }
  }

  /**
  * @brief Obtém o valor total de saída de um Caixa.
  * @param psNomeCaixa O nome de um Caixa.
  * @param piDataIni A data inicial da consulta.
  * @param piDataFim A data final da consulta.
  * @param pbProvisao Um boolean que se true, considera as provisões.
  * @return O valor total de saídas do caixa, se encontrado, ou 0 se não encontrado.
  */
  public double selectDTotalSaidaPorCaixa( String psNomeCaixa, int piDataIni, int piDataFim, boolean pbProvisao )
  {
    double dTotal = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sData1 = piDataIni + "";
      String sData2 = piDataFim + "";

      if( sData1.length() == 7 )
      {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 )
      {
        sData2 = "0" + sData2;
      }

      String sQuery =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
        " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " CAIXAS.NOME = '" + psNomeCaixa + "' AND" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";

      if( pbProvisao )
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.TIPO = 'D'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getDouble( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            dTotal = rs.getDouble( 1 );
            break;
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dTotal );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dTotal );
    }
  }

  /**
  * @brief Obtém o valor total de movimento por período e tipo de conta.
  * @param pcTipoConta O tipo de uma Conta 'C' ou 'D'.
  * @param piDataIni A data inicial do período de consulta.
  * @param piDataFim A data final do período de consulta.
  * @param pbProvisao Um boolean que se true, considera as provisões.
  * @return O valor total para aquele tipo de conta, se maior do que 0, ou 0.
  */
  public double selectDTotalMovimentoPeriodo( char pcTipoConta, int piDataIni, int piDataFim, boolean pbProvisao )
  {
    double dTotal = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sData1 = piDataIni + "";
      String sData2 = piDataFim + "";

      if( sData1.length() == 7 )
      {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 )
      {
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

      if( pbProvisao )
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.TIPO = '" + pcTipoConta + "'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getDouble( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            dTotal = rs.getDouble( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dTotal );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dTotal );
    }
  }

  /**
  * @brief Obtém o valor total de um período para uma Conta específica.
  * @param piCodConta O código de uma Conta.
  * @param piDataIni A data inicial do período de consulta.
  * @param piDataFim A data final do período de consulta.
  * @param pbProvisao Um boolean que se true, considera provisões.
  * @return O valor total da Conta, se encontrado, ou 0 se não encontrado.
  */
  public double selectDTotalContaPeriodo( int piCodConta, int piDataIni, int piDataFim, boolean pbProvisao )
  {
    double dTotal = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sData1 = piDataIni + "";
      String sData2 = piDataFim + "";

      if( sData1.length() == 7 )
      {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 )
      {
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
      
      if( pbProvisao )
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery += " CONTAS.COD_CONTA = " + piCodConta;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getDouble( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            dTotal = rs.getDouble( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dTotal );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dTotal );
    }
  }

  /**
  * @brief Obtém o nome de uma Conta.
  * @param piCodConta O código da Conta.
  * @return O nome da Conta, se encontado, ou vazio se não encontrado.
  */
  public String selectSNomeConta( int piCodConta )
  {
    String sNome = "";
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " NOME " +
        "FROM" +
        " CONTAS " +
        "WHERE" +
        " COD_CONTA = " + piCodConta;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            sNome = rs.getString( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( sNome );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( sNome );
    }
  }

  /**
  * @brief Obtém o nome de um Caixa.
  * @param piCodCaixa O código do Caixa.
  * @return O nome do Caixa, se encontrado, ou vazio se não encontrado.
  */
  public String selectSNomeCaixa( int piCodCaixa )
  {
    String sNome = "";
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " NOME " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + piCodCaixa;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            sNome = rs.getString( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( sNome );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( sNome );
    }
  }

  /**
  * @brief Altera todos os campos de uma Conta baseado no código da Conta.
  * @param pConta A Conta já prenchida a ser alterada.
  */
  public void alterarConta( Conta pConta )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE" +
        " CONTAS " +
        "SET " +
        " NOME = '" + pConta.getSNome() + "'," +
        " TIPO = '" + pConta.getCTipo() + "' " +
        "WHERE" +
        " COD_CONTA = " + pConta.getICodConta();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados." );
      }
      else
      {
        System.out.println( "SQL: 1 Registro atualizado." );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Altera todos os campos de um Caixa baseado no código do Caixa.
  * @param pCaixa Um Caixa já preenchido a ser alteado.
  */
  public void alterarCaixa( Caixa pCaixa )
  {
    try
    {
      Statement st = this.connection.createStatement();

      String sQuery =
        "UPDATE" +
        " CAIXAS " +
        "SET " +
        " NOME = '" + pCaixa.getSNome() + "'," +
        " SALDO = '" + pCaixa.getDSaldo() + "' " +
        "WHERE" +
        " COD_CAIXA = " + pCaixa.getICodCaixa();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados." );
      }
      else
      {
        System.out.println( "SQL: 1 Registro atualizado." );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Altera todos os campos de um Lancamento baseado no código do Lancamento.
  * @param pLancamento Um Lancamento ja preenchido a ser alterado.
  */
  public void alterarLancamento( Lancamento pLancamento )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "UPDATE" +
        " LANCAMENTOS " +
        "SET" +
        " DATA_EMISSAO = " + pLancamento.getIDataEmissao() + "," +
        " DATA_VENCIMENTO = " + pLancamento.getIDataVencimento() + "," +
        " DATA_QUITACAO = " + pLancamento.getIDataQuitacao() + "," +
        " DESCRICAO = '" + pLancamento.getSDescricao() + "'," +
        " VALOR = " + pLancamento.getDValor() + "," +
        " COD_CONTA = " + pLancamento.getICodConta() + "," +
        " COD_CAIXA = " + pLancamento.getICodCaixa() + " " +
        "WHERE" +
        " COD_LANCAMENTO = " + pLancamento.getICodLancamento();

      System.out.println( "SQL: " + sQuery );

      if( st.executeUpdate( sQuery ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados." );
      }
      else
      {
        System.out.println( "SQL: 1 Registro atualizado." );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }

  /**
  * @brief Obtém todos os Lancamentos de um período.
  * @param piDataIni A data inicial do período de consulta.
  * @param piDataFim A data final do período de consulta.
  * @return Uma lista de Lancamentos se houver, ou uma lista vazia.
  */
  public ArrayList<Lancamento> selectAlTodosOsLancamentosPeriodo( int piDataIni, int piDataFim )
  {
    ArrayList<Lancamento> alLancamento = new ArrayList<>();

    try
    {
      String sData1 = piDataIni + "";
      String sData2 = piDataFim + "";

      if( sData1.length() == 7 )
      {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 )
      {
        sData2 = "0" + sData2;
      }

      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " LANCAMENTOS.DATA_EMISSAO," +
        " LANCAMENTOS.DATA_VENCIMENTO," +
        " LANCAMENTOS.DATA_QUITACAO," +
        " CONTAS.TIPO," +
        " LANCAMENTOS.DESCRICAO," +
        " LANCAMENTOS.VALOR," +
        " LANCAMENTOS.COD_CONTA," +
        " LANCAMENTOS.COD_CAIXA " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA)" +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " " +
        "ORDER BY" +
        " LANCAMENTOS.DATA_VENCIMENTO";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          Lancamento lancamento = new Lancamento();

          if( rs.getInt( 1 ) != 0 )
          {
            lancamento.setIDataEmissao( rs.getInt( 1 ) );
            lancamento.setIDataVencimento( rs.getInt( 2 ) );
            lancamento.setIDataQuitacao( rs.getInt( 3 ) );
            lancamento.setCTipo( rs.getString( 4 ).charAt(0) );
            lancamento.setDescricao( rs.getString( 5 ) );
            lancamento.setDValor( rs.getDouble( 6 ) );
            lancamento.setICodConta( rs.getInt( 7 ) );
            lancamento.setICodCaixa( rs.getInt( 8 ) );

            alLancamento.add( lancamento );
          }
        }
        while( rs.next() );

        System.out.println( "SQL: " + alLancamento.size() + " Registro" );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( alLancamento );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( alLancamento );
    }
  }

  /**
  * @brief Obtém um extrato de uma Conta em um período.
  * @param piConta O código da Conta a ser obtido o extrato.
  * @param piDataIni A data inicial do período de consulta.
  * @param piDataFim A data final do período de consulta.
  * @param pbProvisao Um boolean que se true, considera as provisões.
  * @return Uma lista contendo a data de vencimento, data de quitação, descrição e valor, ou lista vazia.
  */
  public ArrayList<String[]> selectAlExtratoContaPeriodo( int piConta, int piDataIni, int piDataFim, boolean pbProvisao )
  {
    ArrayList<String[]> alString = new ArrayList<>();
    try
    {
      String sData1 = piDataIni + "";
      String sData2 = piDataFim + "";

      if( sData1.length() == 7 )
      {
        sData1 = "0" + sData1;
      }

      if( sData2.length() == 7 )
      {
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
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + sData1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + sData2 + " AND";

      if( pbProvisao )
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }

      sQuery +=
        " LANCAMENTOS.COD_CONTA = " + piConta + " " +
        "ORDER BY" +
        " LANCAMENTOS.DATA_VENCIMENTO";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            String linha[] = new String[5];

            linha[0] = DateTools.formatDataIntToStringBR( rs.getInt( 1 ) ); // data_vencimento
            linha[1] = DateTools.formatDataIntToStringBR( rs.getInt( 2 ) ); // data_quitacao
            linha[2] = rs.getString( 3 );                                   // descricao
            linha[3] = ValueTools.format( rs.getDouble( 4 ) );              // valor
            linha[4] = rs.getString( 5 );                                   // nomecaixa

            alString.add( linha );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + alString.size() + " Registro" );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( alString );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( alString );
    }
  }

  /**
  * @brief Obtém um Lancamento específico.
  * @param psData A data de emissão ou quitação formatada do Lancamento.
  * @param psDescricao A descrição do Lancamento.
  * @param psValor O valor do Lancamento.
  * @param pcPago Informar 'S' se pago ou 'N' se não.
  * @return Um Lancamento válido, se encontrado, ou Lancamento vazio se não;
  */
  public Lancamento selectLancamento( String psData, String psDescricao, String psValor, char pcPago )
  {
    Lancamento lancamento = new Lancamento();

    try
    {
      Statement st = this.connection.createStatement();

      String sQuery = 
        "SELECT " +
        " LANCAMENTOS.COD_LANCAMENTO," +
        " LANCAMENTOS.DATA_EMISSAO," +
        " LANCAMENTOS.DATA_VENCIMENTO," +
        " LANCAMENTOS.DATA_QUITACAO," +
        " LANCAMENTOS.DESCRICAO," +
        " LANCAMENTOS.VALOR," +
        " LANCAMENTOS.COD_CONTA," +
        " LANCAMENTOS.COD_CAIXA " +
        "FROM" +
        " LANCAMENTOS " +
        "WHERE";

      if( pcPago == 'S' )
        sQuery += " LANCAMENTOS.DATA_QUITACAO = " + DateTools.parseDataStringBRToInteger( psData ) + " AND";
      else
        sQuery += " LANCAMENTOS.DATA_VENCIMENTO = " + DateTools.parseDataStringBRToInteger( psData ) + " AND";

      sQuery +=
        " LANCAMENTOS.DESCRICAO = '" + psDescricao + "' AND" +
        " LANCAMENTOS.VALOR = " + psValor;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            lancamento.setICodLancamento( rs.getInt( 1 ) );
            lancamento.setIDataEmissao( rs.getInt( 2 ) );
            lancamento.setIDataVencimento( rs.getInt( 3 ) );
            lancamento.setIDataQuitacao( rs.getInt( 4 ) );
            lancamento.setDescricao( rs.getString( 5 ) );
            lancamento.setDValor( rs.getDouble( 6 ) );
            lancamento.setICodConta( rs.getInt( 7 ) );
            lancamento.setICodCaixa( rs.getInt( 8 ) );
            
            System.out.println( "SQL: 1 Registro" );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( lancamento );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( lancamento );
    }
  }

  /**
  * @brief Obtém um Caixa.
  * @param piCodCaixa O código do Caixa.
  * @return Um Caixa válido, se encontrado, ou um Caixa vazio se não.
  */
  public Caixa selectCaixa( int piCodCaixa )
  {
    Caixa caixa = new Caixa();

    try
    {
      Statement st = this.connection.createStatement();

      String sQuery =
        "SELECT" +
        " NOME," +
        " SALDO " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + piCodCaixa;

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            caixa.setSNome( rs.getString( 1 ) );
            caixa.setDSaldo( rs.getDouble( 2 ) );
            
            System.out.println( "SQL: 1 Registro" );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          break;
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( caixa );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( caixa );
    }
  }

  /**
  * @brief Obtém os Lancamentos para inserir no gráfico do painel Home.
  * @param piCodCaixa O código do Caixa a ser consultado.
  * @param piDataIni A data inicial do período a ser consultado.
  * @param piDataFim A data final do período a ser consultado.
  * @param pbProvisao Um boolean, que se true, considera as provisões.
  * @return Uma lista com o nome de uma Conta e o valor total do período.
  */
  public ArrayList<Object[]> selectAlLancamentosGrafico( int piCodCaixa, int piDataIni, int piDataFim, boolean pbProvisao )
  {
    ArrayList<Object[]> alObject = new ArrayList<>();

    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " CONTAS.NOME," +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " LANCAMENTOS.COD_CAIXA = " + piCodCaixa + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + piDataIni + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + piDataFim + " AND" +
        " CONTAS.TIPO = 'D' AND";

      if( pbProvisao )
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO >= 0";
      }
      else
      {
        sQuery += " LANCAMENTOS.DATA_QUITACAO > 0";
      }

      sQuery += " GROUP BY CONTAS.NOME";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            Object[] select = new Object[2];

            select[0] = rs.getString( 1 );
            select[1] = rs.getDouble( 2 );
            
            alObject.add( select );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + alObject.size() + " Registro(s)" );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( alObject );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( alObject );
    }
  }

  /**
  * @brief Obtém o total de entrada provisionada de um Caixa em um período.
  * @param psNomeCaixa O nome do Caixa.
  * @param piDataIni A data inicial do período de consulta.
  * @param piDataFim A data final do período de consulta.
  * @return O valor total das entradas provisionadas no período, se encontrado, ou 0.
  */
  public double selectDTotalEntradaProvisionada( String psNomeCaixa, int piDataIni, int piDataFim )
  {
    double dTotalEntradas = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "             INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + piDataIni + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + piDataFim + " AND" +
        " CONTAS.TIPO = 'C' AND" +
        " LANCAMENTOS.DATA_QUITACAO = 0 AND " +
        " CAIXAS.NOME = '" + psNomeCaixa + "'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getBigDecimal( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            dTotalEntradas = rs.getBigDecimal( 1 ).doubleValue();
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dTotalEntradas );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dTotalEntradas );
    }
  }

  /**
  * @brief Obtém o total de saída provisionada de um Caixa em um período.
  * @param psNomeCaixa O nome do Caixa.
  * @param piDataIni A data inicial do período de consulta.
  * @param piDataFim A data final do período de consulta.
  * @return O valor total das entradas provisionadas no período, se encontrado, ou 0.
  */
  public double selectDTotalSaidaProvisionada( String psNomeCaixa, int piDataIni, int piDataFim )
  {
    double dTotalSaidas = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sQuery =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "             INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + piDataIni + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + piDataFim + " AND" +
        " CONTAS.TIPO = 'D' AND" +
        " LANCAMENTOS.DATA_QUITACAO = 0 AND " +
        " CAIXAS.NOME = '" + psNomeCaixa + "'";

      System.out.println( "SQL: " + sQuery );

      ResultSet rs = st.executeQuery( sQuery );

      if( rs.next() )
      {
        do
        {
          if( rs.getBigDecimal( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            dTotalSaidas = rs.getDouble( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
      }
      return( dTotalSaidas );
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( dTotalSaidas );
    }
  }
}