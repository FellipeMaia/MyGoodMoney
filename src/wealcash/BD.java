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

package wealcash;

import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class BD
{
  private Connection connection;
  private String     caminhoAbsoluto;
  
  public BD()
  {
    this.connection = null;
  }
  public void exportar()
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String[] comandos =
      {
        ".output WealCash.sql",
        ".dump",
        ".output stdout"
      };
      
      for( String sql : comandos )
      {
        System.out.println( "SQL: " + sql );
        st.execute(sql);
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
    catch(SQLException ex)
    {
      Logger.getLogger(BD.class.getName()).log(Level.SEVERE,null,ex);
    }
  }
  public ArrayList<Conta> selectTodasAsContas()
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
            conta.setNome( rs.getString( 1 ) );
            conta.setTipo( rs.getString( 2 ).charAt( 0 ) );
            contasList.add( conta );
          } 
        }
        while( rs.next() );

        System.out.println( "SQL: " + contasList.size() + " Registro" );
        return( contasList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( contasList );
      }
    }
    catch( SQLException ex )
    {
      return( contasList );
    }
    catch( NullPointerException exc )
    {
      return( contasList );
    }
  }
  public ArrayList<Caixa> selectTodosOsCaixas()
  {
    ArrayList<Caixa> caixasList = new ArrayList<>();

    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT" +
        " NOME," +
        " SALDO " +
        "FROM" +
        " CAIXAS " +
        "ORDER BY" +
        " NOME";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null && rs.getBigDecimal( 2 ) != null )
          {
            Caixa caixa = new Caixa();
            caixa.setNome( rs.getString( 1 ) );
            caixa.setSaldo( rs.getBigDecimal( 2 ).doubleValue() );

            caixasList.add( caixa );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + caixasList.size() + " Registro" );
        return( caixasList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( caixasList );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( caixasList );
    }
  }
  public ArrayList<Lancamento> selectTodosOsLancamentos()
  {
    ArrayList<Lancamento> lancamentosList = new ArrayList<>();
    
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
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
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "ORDER BY" +
        " LANCAMENTOS.DATA_VENCIMENTO";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            Lancamento lancamento = new Lancamento();

            lancamento.setDataEmissao( rs.getInt( 1 ) );
            lancamento.setDataVencimento( rs.getInt( 2 ) );
            lancamento.setDataQuitacao( rs.getInt( 3 ) ); 
            lancamento.setTipo( rs.getString( 4 ).charAt( 0 ) );
            lancamento.setDescricao( rs.getString( 5 ) );
            lancamento.setValor( rs.getDouble( 6 ) );
            lancamento.setCodConta( rs.getInt( 7 ) );
            lancamento.setCodCaixa( rs.getInt( 8 ) );

            lancamentosList.add( lancamento );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + lancamentosList.size() + " Registro" );
        return( lancamentosList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( lancamentosList );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( lancamentosList );
    }
  }
  public boolean existeBD( String caminhoAbsolutoParam )
  {
    File f = new File( caminhoAbsolutoParam );
    
    boolean b = f.canRead() && f.canWrite();
    
    if( b )
    {
      this.caminhoAbsoluto = caminhoAbsolutoParam;
      carregarBDExistente();
    }
    
    return( b );
  }
  
  private void carregarBDExistente()
  {
    try
    {
      System.out.println( "Carregando BD existente: jdbc:sqlite:" + this.caminhoAbsoluto );
      
      Class.forName( "org.sqlite.JDBC" );
      this.connection = DriverManager.getConnection( "jdbc:sqlite:" + caminhoAbsoluto, "", "" );
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
  
  public String getCaminhoAbsoluto()
  {
    return( this.caminhoAbsoluto );
  }
  
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
  
  public void criarBancoDeDados( String caminhoENomeParam ) throws ClassNotFoundException
  {
    try
    {
      this.caminhoAbsoluto = caminhoENomeParam;

      System.out.println( "Criando BD: jdbc:sqlite:" + caminhoAbsoluto );
      
      Class.forName( "org.sqlite.JDBC" );
      this.connection = DriverManager.getConnection( "jdbc:sqlite:" + caminhoAbsoluto, "", "" );
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
  public void inserirConta( Conta contaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String insert =
        "INSERT INTO CONTAS" +
        " (" +
        " NOME," +
        " TIPO" +
        " )" +
        " VALUES" +
        " (" +
        " '" + contaParam.getNome() + "'," +
        " '" + contaParam.getTipo() + "'" +
        " )";
      
      System.out.println( "SQL: " + insert );

      if( st.executeUpdate( insert ) == Statement.EXECUTE_FAILED )
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
  public void excluirConta( Conta contaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "DELETE FROM" +
        " CONTAS " +
        "WHERE" +
        " COD_CONTA = " + contaParam.getCodConta();

      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate(sql) == Statement.EXECUTE_FAILED )
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
  public void inserirCaixa( Caixa caixaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String insert =
        "INSERT INTO CAIXAS" +
        " (" +
        " NOME," +
        " SALDO" +
        " )" +
        " VALUES" +
        " (" +
        " '" + caixaParam.getNome() + "'," +
        " " +  caixaParam.getSaldo() +
        " )";
      
      System.out.println( "SQL: " + insert );

      if( st.executeUpdate( insert ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: Erro ao executar: " + insert );
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
  public void excluirCaixa( Caixa caixaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "DELETE FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + caixaParam.getCodCaixa();

      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate(sql) == Statement.EXECUTE_FAILED )
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
  public void inserirLancamento( Lancamento lancParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String insert =
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
        " " + lancParam.getDataEmissao() + "," +
        " " + lancParam.getDataVencimento() + "," +
        " " + lancParam.getDataQuitacao() + "," +
        " '" + lancParam.getDescricao() + "'," +
        " " + lancParam.getValor() + "," +
        " " + lancParam.getCodConta() + "," +
        " " + lancParam.getCodCaixa() +
        " )";
      
      System.out.println( "SQL: " + insert );

      if( st.executeUpdate( insert ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: Erro ao executar: " + insert );
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
  public void excluirLancamento( Lancamento lancamentoParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "DELETE FROM" +
        " LANCAMENTOS " +
        "WHERE" +
        " LANCAMENTOS.COD_LANCAMENTO = " + lancamentoParam.getCodLancamento();
      
      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate( sql ) == Statement.EXECUTE_FAILED )
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
  public void subtrairDoSaldoCaixa( int codCaixaParam, Double valorParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "UPDATE CAIXAS " +
        "SET " +
        " SALDO = (SELECT SALDO FROM CAIXAS WHERE COD_CAIXA = " + codCaixaParam + ")-" + valorParam + " WHERE COD_CAIXA = " + codCaixaParam;
      
      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate( sql ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados" );
      }
      else
      {
        System.out.println( "SQL: 1 Reistro atualizado" );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }
  public void adicionarAoSaldoCaixa( int codCaixaParam, Double valorParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "UPDATE CAIXAS " +
        "SET " +
        " SALDO = (SELECT SALDO FROM CAIXAS WHERE COD_CAIXA = " + codCaixaParam + ")+" + valorParam + " WHERE COD_CAIXA = " + codCaixaParam;
      
      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate( sql ) == Statement.EXECUTE_FAILED )
      {
        System.out.println( "SQL: 0 Registros atualizados" );
      }
      else
      {
        System.out.println( "SQL: 1 Reistro atualizado" );
        this.connection.commit();
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
    }
  }
  public Character selectTipoConta( int codConta )
  {
    Character c = ' ';
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT" +
        " TIPO " +
        "FROM" +
        " CONTAS " +
        "WHERE" +
        " COD_CONTA = " + codConta;
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
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
  public int selectCodConta( String nomeParam )
  {
    int cod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sql =
        "SELECT" +
        " COD_CONTA " +
        "FROM" +
        " CONTAS " +
        "WHERE" +
        " NOME = '" + nomeParam + "'";
        
      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            cod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( cod );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( cod );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( cod );
    }
  }
  public int selectCodConta( int dataParam, Double valorParam )
  {
    int cod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String data = dataParam + "";
      if( data.length() == 7 ) data = "0" + data;
      
      String sql =
        "SELECT" +
        " CONTAS.COD_CONTA " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO = " + data + " AND" +
        " LANCAMENTOS.VALOR = " + valorParam;
        
      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            cod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( cod );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( cod );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( cod );
    }
  }
  public int selectCodLancamento( int dataParam, Double valorParam, int contaParam, int caixaParam )
  {
    int cod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String data = dataParam + "";
      if( data.length() == 7 ) data = "0" + data;
      
      String sql =
        "SELECT" +
        " LANCAMENTOS.COD_LANCAMENTO " +
        "FROM" +
        " LANCAMENTOS " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO = " + data + " AND" +
        " LANCAMENTOS.VALOR = " + valorParam + " AND" +
        " LANCAMENTOS.COD_CONTA = " + contaParam + " AND" +
        " LANCAMENTOS.COD_CAIXA = " + caixaParam;
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro: " + rs.getInt( 1 )  );
            cod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( cod );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( cod );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( cod );
    }
  }
  public int selectCodCaixa( String nomeParam )
  {
    int cod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String sql =
        "SELECT" + 
        " COD_CAIXA " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " NOME = '" + nomeParam + "'";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            cod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( cod );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( cod );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( cod );
    }
  }
  public int selectCodCaixa( int dataParam, Double valorParam )
  {
    int cod = 0;

    try
    {
      Statement st = this.connection.createStatement();
      String data = dataParam + "";
      if( data.length() == 7 ) data = "0" + data;
      
      String sql =
        "SELECT" +
        " CAIXAS.COD_CAIXA " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO = " + data + " AND" +
        " LANCAMENTOS.VALOR = " + valorParam;
        
      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getInt( 1 ) != 0 )
          {
            System.out.println( "SQL: 1 Registro" );
            cod = rs.getInt( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( cod );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( cod );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( cod );
    }
  }
  public Double selectSaldoCaixa( String caixaParam )
  {
    Double saldo = Double.NaN;

    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT" +
        " SALDO " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " NOME = '" + caixaParam + "'";
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getDouble( 1 ) != Double.NaN )
          {
            System.out.println( "SQL: 1 Registro" );
            saldo = rs.getDouble( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( saldo );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( saldo );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( saldo );
    }
  }
  public void excluirTodosRegistros()
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String[] querySQL =
      {
        "DELETE FROM CONTAS",
        "DELETE FROM CAIXAS",
        "DELETE FROM LANCAMENTOS"
      };
      
      for( String query : querySQL )
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
  public BigDecimal selectTotalEntradaPorCaixa( String caixaParam, int dataIni, int dataFim )
  {
    BigDecimal total = BigDecimal.ZERO;

    try
    {
      Statement st = this.connection.createStatement();
      String data1 = dataIni + "";
      String data2 = dataFim + "";
      if( data1.length() == 7 ) data1 = "0" + data1;
      if( data2.length() == 7 ) data2 = "0" + data2;
      
      String sql =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
        " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " CAIXAS.NOME = '" + caixaParam + "' AND" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " AND" +
        " CONTAS.TIPO = 'C'";
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getBigDecimal( 1 ) == null )
          {
            System.out.println( "SQL: 0 Registros" );
            return( total );
          }
          else
          {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getBigDecimal( 1 );
            return( total );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( total );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( total );
    }
  }
  public BigDecimal selectTotalSaidaPorCaixa( String caixaParam, int dataIni, int dataFim )
  {
    BigDecimal total = BigDecimal.ZERO;

    try
    {
      Statement st = this.connection.createStatement();
      String data1 = dataIni + "";
      String data2 = dataFim + "";
      if( data1.length() == 7 ) data1 = "0" + data1;
      if( data2.length() == 7 ) data2 = "0" + data2;
      
      String sql =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA)" +
        " INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " CAIXAS.NOME = '" + caixaParam + "' AND" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " AND" +
        " CONTAS.TIPO = 'D'";
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getBigDecimal( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getBigDecimal( 1 );
            return( total );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
            return( total );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( total );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( total );
    }
  }
  public BigDecimal selectTotalMovimentoPeriodo( char tipoConta, int dataIni, int dataFim )
  {
    BigDecimal total = BigDecimal.ZERO;

    try
    {
      Statement st = this.connection.createStatement();
      String data1 = dataIni + "";
      String data2 = dataFim + "";
      if( data1.length() == 7 ) data1 = "0" + data1;
      if( data2.length() == 7 ) data2 = "0" + data2;
      
      String sql =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " AND" +
        " LANCAMENTOS.DATA_QUITACAO > 0 AND" +
        " CONTAS.TIPO = '" + tipoConta + "'";
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getBigDecimal( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getBigDecimal( 1 );
            return( total );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
            return( total );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( total );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( total );
    }
  }
  public BigDecimal selectTotalContaPeriodo( int codConta, int dataIni, int dataFim )
  {
    BigDecimal total = BigDecimal.ZERO;

    try
    {
      Statement st = this.connection.createStatement();
      String data1 = dataIni + "";
      String data2 = dataFim + "";
      if( data1.length() == 7 ) data1 = "0" + data1;
      if( data2.length() == 7 ) data2 = "0" + data2;
      
      String sql =
        "SELECT" +
        " SUM(LANCAMENTOS.VALOR) " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CONTAS ON (LANCAMENTOS.COD_CONTA = CONTAS.COD_CONTA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " AND" +
        " LANCAMENTOS.DATA_QUITACAO > 0 AND" +
        " CONTAS.COD_CONTA = " + codConta;
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getBigDecimal( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            total = rs.getBigDecimal( 1 );
            return( total );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
            return( total );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( total );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( total );
    }
  }
  public String selectNomeConta( int codParam )
  {
    String nome = "";
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT" +
        " NOME " +
        "FROM" +
        " CONTAS " +
        "WHERE" +
        " COD_CONTA = " + codParam;
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            nome = rs.getString( 1 );
            return( nome );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
            return( nome );
          }
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( nome );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( nome );
    }
  }
  public String selectNomeCaixa( int codParam )
  {
    String nome = "";
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT" +
        " NOME " +
        "FROM" +
        " CAIXAS " +
        "WHERE" +
        " COD_CAIXA = " + codParam;
      
      System.out.println( "SQL: " + sql );
      
      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          if( rs.getString( 1 ) != null )
          {
            System.out.println( "SQL: 1 Registro" );
            nome = rs.getString( 1 );
          }
          else
          {
            System.out.println( "SQL: 0 Registros" );
          }
          return( nome );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( nome );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( nome );
    }
  }
  public void alterarConta( Conta contaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "UPDATE" +
        " CONTAS " +
        "SET " +
        " NOME = '" + contaParam.getNome() + "'," +
        " TIPO = '" + contaParam.getTipo() + "' " +
        "WHERE" +
        " COD_CONTA = " + contaParam.getCodConta();
      
      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate( sql ) == Statement.EXECUTE_FAILED )
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
  public void alterarCaixa( Caixa caixaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "UPDATE" +
        " CAIXAS " +
        "SET " +
        " NOME = '" + caixaParam.getNome() + "'," +
        " SALDO = '" + caixaParam.getSaldo() + "' " +
        "WHERE" +
        " COD_CAIXA = " + caixaParam.getCodCaixa();
      
      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate( sql ) == Statement.EXECUTE_FAILED )
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
  public void alterarLancamento( Lancamento lancamentoParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "UPDATE" +
        " LANCAMENTOS " +
        "SET" +
        " DATA_EMISSAO = " + lancamentoParam.getDataEmissao() + "," +
        " DATA_VENCIMENTO = " + lancamentoParam.getDataVencimento() + "," +
        " DATA_QUITACAO = " + lancamentoParam.getDataQuitacao() + "," +
        " DESCRICAO = '" + lancamentoParam.getDescricao() + "'," +
        " VALOR = " + lancamentoParam.getValor() + "," +
        " COD_CONTA = " + lancamentoParam.getCodConta() + "," +
        " COD_CAIXA = " + lancamentoParam.getCodCaixa() + " " +
        "WHERE" +
        " COD_LANCAMENTO = " + lancamentoParam.getCodLancamento();
      
      System.out.println( "SQL: " + sql );
      
      if( st.executeUpdate( sql ) == Statement.EXECUTE_FAILED )
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
  public ArrayList<Lancamento> selectTodosOsLancamentosPeriodo( int pDataIni, int pDataFim )
  {
    ArrayList<Lancamento> lancamentosList = new ArrayList<>();

    try
    {
      String data1 = pDataIni + "";
      String data2 = pDataFim + "";
      if( data1.length() == 7 ) data1 = "0" + data1;
      if( data2.length() == 7 ) data2 = "0" + data2;
      
      Statement st = this.connection.createStatement();
      
      String sql =
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
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " " +
        "ORDER BY" +
        " LANCAMENTOS.DATA_VENCIMENTO";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          Lancamento lancamento = new Lancamento();
          
          if( rs.getInt( 1 ) != 0 )
          {
            lancamento.setDataEmissao( rs.getInt( 1 ) );
            lancamento.setDataVencimento( rs.getInt( 2 ) );
            lancamento.setDataQuitacao( rs.getInt( 3 ) );
            lancamento.setTipo( rs.getString( 4 ).charAt(0) );
            lancamento.setDescricao( rs.getString( 5 ) );
            lancamento.setValor( rs.getDouble( 6 ) );
            lancamento.setCodConta( rs.getInt( 7 ) );
            lancamento.setCodCaixa( rs.getInt( 8 ) );

            lancamentosList.add( lancamento );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + lancamentosList.size() + " Registro" );
        return( lancamentosList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( lancamentosList );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( lancamentosList );
    }
  }
  public ArrayList<String[]> selectExtratoContaPeriodo( int pConta, int pDataIni, int pDataFim, boolean provisao )
  {
    ArrayList<String[]> lancamentosList = new ArrayList<>();
    try
    {
      String data1 = pDataIni + "";
      String data2 = pDataFim + "";
      if( data1.length() == 7 ) data1 = "0" + data1;
      if( data2.length() == 7 ) data2 = "0" + data2;
      
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT" +
        " LANCAMENTOS.DATA_VENCIMENTO," +
        " LANCAMENTOS.DATA_QUITACAO," +
        " LANCAMENTOS.DESCRICAO," +
        " LANCAMENTOS.VALOR," +
        " CAIXAS.NOME " +
        "FROM" +
        " LANCAMENTOS INNER JOIN CAIXAS ON (LANCAMENTOS.COD_CAIXA = CAIXAS.COD_CAIXA) " +
        "WHERE" +
        " LANCAMENTOS.DATA_VENCIMENTO >= " + data1 + " AND" +
        " LANCAMENTOS.DATA_VENCIMENTO <= " + data2 + " AND";
      
      if( provisao )
      {
        sql += " LANCAMENTOS.DATA_QUITACAO >= 0 AND";
      }
      else
      {
        sql += " LANCAMENTOS.DATA_QUITACAO > 0 AND";
      }
      
      sql +=
        " LANCAMENTOS.COD_CONTA = " + pConta + " " +
        "ORDER BY" +
        " LANCAMENTOS.DATA_VENCIMENTO";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
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

            lancamentosList.add( linha );
          }
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + lancamentosList.size() + " Registro" );
        return( lancamentosList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( lancamentosList );
      }
    }
    catch( SQLException e )
    {
      System.out.println( "SQL Exception: " + e.getLocalizedMessage() );
      return( lancamentosList );
    }
  }
}