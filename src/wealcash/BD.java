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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
  public ArrayList<Object[]> getTodasAsContas()
  {
    try
    {
      ArrayList<Object[]> contasList = new ArrayList<>();
      
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT \n" +
        "  NOME, \n" +
        "  TIPO \n" +
        "FROM \n" +
        "  CONTAS \n" +
        "ORDER BY \n" +
        "  NOME";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          Object[] select = { "", "" };
          select[0] = rs.getString( 0 );
          select[1] = rs.getString( 1 );
          
          contasList.add( select );
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + contasList.size() + " Registro" );
        return( contasList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( null );
      }
    }
    catch( SQLException e )
    {
      e.printStackTrace();
      return( null );
    }
  }
  public ArrayList<Object[]> getTodosOsCaixas()
  {
    try
    {
      ArrayList<Object[]> caixasList = new ArrayList<>();
      
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT \n" +
        "  NOME, \n" +
        "  SALDO \n" +
        "FROM \n" +
        "  CAIXAS \n" +
        "ORDER BY \n" +
        "NOME";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          Object[] select = { "", BigDecimal.ZERO };
          select[0] = rs.getString( 0 );
          select[1] = rs.getBigDecimal( 1 );
          
          caixasList.add( select );
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + caixasList.size() + " Registro" );
        return( caixasList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( null );
      }
    }
    catch( SQLException e )
    {
      e.printStackTrace();
      return( null );
    }
  }
  public ArrayList<Object[]> getTodosOsLancamentos()
  {
    try
    {
      ArrayList<Object[]> lancamentosList = new ArrayList<>();
      
      Statement st = this.connection.createStatement();
      
      String sql =
        "SELECT \n" +
        "  DATA_EMISSAO, \n" +
        "  DESCRICAO, \n" +
        "  VALOR \n" +
        "FROM \n" +
        "  LANCAMENTOS \n" +
        "ORDER BY \n" +
        "  DATA_EMISSAO";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          Object[] select = { "", "", Double.NaN, "" };
          select[0] = rs.getString( 0 );
          select[1] = rs.getString( 1 );
          select[2] = rs.getDouble( 2 );
          
          lancamentosList.add( select );
        }
        while( rs.next() );
        
        System.out.println( "SQL: " + lancamentosList.size() + " Registro" );
        return( lancamentosList );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( null );
      }
    }
    catch( SQLException e )
    {
      e.printStackTrace();
      return( null );
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
    catch( ClassNotFoundException | SQLException e )
    {
      e.printStackTrace();
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
      Logger.getLogger(BD.class.getName()).log(Level.SEVERE,null,ex);
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
        "CREATE TABLE IF NOT EXISTS CONTAS\n" +
        "( \n" +
        "  COD_CONTA INTEGER UNIQUE NOT NULL, \n" +
        "  NOME      VARCHAR(30) NOT NULL, \n" +
        "  TIPO      CHAR NOT NULL, \n" +
        "  PRIMARY KEY(COD_CONTA)\n" +
        ")\n",
        "CREATE TABLE IF NOT EXISTS CAIXAS\n" +
        "(\n" +
        "  COD_CAIXA INTEGER UNIQUE NOT NULL,\n" +
        "  NOME      VARCHAR(30) NOT NULL,\n" +
        "  SALDO     FLOAT NOT NULL,\n" +
        "  PRIMARY KEY(COD_CAIXA)\n" +
        ")\n",
        "CREATE TABLE IF NOT EXISTS LANCAMENTOS\n" +
        "(\n" +
        "  COD_LANCAMENTO  INTEGER UNIQUE NOT NULL,\n" +
        "  DATA_EMISSAO    DATE NOT NULL,\n" +
        "  DESCRICAO       VARCHAR(50) NOT NULL,\n" +
        "  VALOR           FLOAT NOT NULL,\n" +
        "  COD_CONTA       INTEGER NOT NULL REFERENCES conta,\n" +
        "  COD_CAIXA       INTEGER NOT NULL REFERENCES caixa,\n" +
        "  PRIMARY KEY(COD_LANCAMENTO)\n" +
        ")\n",
        "CREATE TABLE IF NOT EXISTS PROVISOES\n" +
        "(\n" +
        "  COD_PROVISAO    INTEGER UNIQUE NOT NULL,\n" +
        "  DATA_EMISSAO    DATE NOT NULL,\n" +
        "  DATA_VENCIMENTO DATE NOT NULL,\n" +
        "  COD_CONTA       INTEGER NOT NULL REFERENCES conta,\n" +
        "  COD_CAIXA       INTEGER NOT NULL REFERENCES caixa,\n" +
        "  VALOR           FLOAT NOT NULL,\n" +
        "  PRIMARY KEY (COD_PROVISAO)\n" +
        ")\n"
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
      e.printStackTrace();
    }
  }
  public void inserirConta( Conta contaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String insert =
        "INSERT INTO CONTAS \n" +
        "  (\n" +
        "  NOME,\n" +
        "  TIPO\n" +
        "  )\n" +
        "VALUES \n" +
        "  (\n" +
        "  '" + contaParam.getNome() + "', \n" +
        "  '" + contaParam.getTipo() + "'\n" +
        "  )\n";
      
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
      e.printStackTrace();
    }
  }
  public void apagarConta( Integer cod_conta )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "DELETE FROM \n" +
        "  CONTAS \n" +
        "WHERE \n" +
        "  COD_CONTA = " + cod_conta;

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
      e.printStackTrace();
    }
  }
  public void inserirCaixa( Caixa caixaParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String insert =
        "INSERT INTO CAIXAS \n" +
        "  (\n" +
        "  NOME,\n" +
        "  SALDO\n" +
        "  )\n" +
        "VALUES\n" +
        "  (\n" +
        "  '" + caixaParam.getNome() + "', \n" +
            caixaParam.getSaldo() + "\n" +
        "  )";
      
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
      e.printStackTrace();
    }
  }
  public void apagarCaixa( Integer cod_caixa )
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String sql =
        "DELETE FROM\n" +
        "  CAIXAS \n" +
        "WHERE\n" +
        "  COD_CAIXA = " + cod_caixa;

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
      e.printStackTrace();
    }
  }
  public void inserirLancamento( Lancamento lancParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
      
      String insert =
        "INSERT INTO LANCAMENTOS \n" +
        "  (\n" +
        "  DATA_EMISSAO, \n" +
        "  DESCRICAO, \n" +
        "  VALOR, \n" +
        "  COD_CONTA, \n" +
        "  COD_CAIXA \n" +
        "  )\n" +
        "VALUES \n" +
        "  (\n" +
        "  '" + format.format( lancParam.getDataEmissao()) + "', \n" +
        "  '" + lancParam.getDescricao() + "', \n" +
           lancParam.getValor() + ", \n" +
           lancParam.getCodConta() + ", \n" +
           lancParam.getCodCaixa() + " \n" +
        "  )";
      
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
      e.printStackTrace();
    }
  }
  public void inserirProvisao( Provisao provisaoParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
      
      String insert =
        "INSERT INTO PROVISOES \n" +
        "  (\n" +
        "  DATA_EMISSAO, \n" +
        "  DATA_VENCIMENTO, \n" +
        "  DESCRICAO, \n" +
        "  VALOR, \n" +
        "  COD_CONTA, \n" +
        "  COD_CAIXA \n" +
        "  )\n" +
        "VALUES \n" +
        "  (\n" +
        "  '" + format.format( provisaoParam.getDataEmissao()) + "', \n" +
        "  '" + format.format( provisaoParam.getDataVencimento()) + "', \n" +
        "  '" + provisaoParam.getDescricao() + "', \n" +
        "   " + provisaoParam.getValor() + ", \n" +
        "   " + provisaoParam.getCodConta() + ", \n" +
        "   " + provisaoParam.getCodCaixa() + " \n" +
        "  )";
      
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
      e.printStackTrace();
    }
  }
  public Integer obterCodConta( String nomeParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sql =
        "SELECT \n" +
        "  COD_CONTA \n" +
        "FROM\n" +
        "  CONTAS \n" +
        "WHERE\n" +
        "  NOME = '" + nomeParam + "'";
        
      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          System.out.println( "SQL: 1 Registro" );
          return( rs.getInt( 0 ) );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( null );
      }
    }
    catch( SQLException e )
    {
      e.printStackTrace();
      return( null );
    }
  }
  
  public Integer obterCodCaixa( String nomeParam )
  {
    try
    {
      Statement st = this.connection.createStatement();
      String sql =
        "SELECT \n" + 
        "  COD_CAIXA \n" +
        "FROM \n" +
        "  CAIXAS \n" +
        "WHERE \n" +
        "  NOME = '" + nomeParam + "'";

      System.out.println( "SQL: " + sql );

      ResultSet rs = st.executeQuery( sql );
      
      if( rs.next() )
      {
        do
        {
          System.out.println( "SQL: 1 Registro" );
          return( rs.getInt( 0 ) );
        }
        while( rs.next() );
      }
      else
      {
        System.out.println( "SQL: 0 Registros" );
        return( null );
      }
    }
    catch( SQLException e )
    {
      e.printStackTrace();
      return( null );
    }
  }
  public void limparGeral()
  {
    try
    {
      Statement st = this.connection.createStatement();
      
      String[] querySQL =
      {
        "DELETE FROM CONTAS",
        "DELETE FROM CAIXAS",
        "DELETE FROM LANCAMENTOS",
        "DELETE FROM PROVISOES"
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
      e.printStackTrace();
    }
  }
}