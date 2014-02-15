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
import java.awt.HeadlessException;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
/**
 *
 * @author Ricardo
 */
public class Main
{
  private final Tela frame;
  private BD banco;
  private boolean bancoCriado;
  
  public Main()
  {
    this.frame = new Tela( "WealCash - Controle Financeiro - Versão 1.0.0" );
    this.frame.limparCamposConta();
    this.frame.setModoBotoesConta( "NOVO" );
    this.frame.setModoBotoesCaixa( "NOVO" );
    this.frame.setModoBotoesMovimento( "NOVO" );
    this.bancoCriado = false;
    this.banco = new BD();
  }
  public void exec()
  {
    try
    {
      setarCaminhoDirBD();
      verificarSeExisteBD();
      processar();
    }
    catch( Exception e )
    {
      System.out.println( "Exception no medoto exec()" );
      e.printStackTrace();
    }
  }
  private void setarCaminhoDirBD()
  {
    try
    {
      File qualquer = new File( "." );
      String caminho = qualquer.getAbsolutePath();
      this.frame.setTxfDiretorioBD( caminho.substring( 0, caminho.length()-1) );
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo setarCaminhoDirBD()" );
      e.printStackTrace();
    }
  }
  private void verificarSeExisteBD()
  {
    String caminho = this.frame.getTxfDiretorioBD();
    String arquivo = this.frame.getTxfNomeBD();
    
    this.bancoCriado = this.banco.existeBD( caminho + arquivo );
    
    if( this.bancoCriado )
    {
      setarPeriodoHome();
      carregarContas();
      carregarCaixas();
      carregarLancamentos();
      this.frame.selecionarAba( 0 );
    }
    else
    {
      this.frame.selecionarAba( 3 );
    }
  }
  private void carregarContas()
  {
    try
    {
      ArrayList<Object[]> contasList = this.banco.getTodasAsContas();
      
      if( contasList == null || contasList.size() == 0 )
        return;
      
      System.out.println( "Contas recebidas: " );
      for( int i=0; i<contasList.size(); i++ )
      {
        Object[] select = contasList.get( i );
        String nomeConta = (String)select[0];
        String tipoConta = (String)select[1];
        
        Conta conta = new Conta();
        conta.setNome( nomeConta );
        conta.setTipo( tipoConta.charAt( 0 ) );
        this.frame.addConta( nomeConta, EnumTipoConta.getPorCodigo( tipoConta.charAt( 0 ) ).getDescricao() );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo carregarContas()" );
      e.printStackTrace();
    }
  }
  private void carregarCaixas()
  {
    try
    {
      ArrayList<Object[]> contasList = this.banco.getTodosOsCaixas();
      
      if( contasList == null || contasList.size() == 0 )
        return;
      
      System.out.println( "Caixas recebidos: " );
      for( int i=0; i<contasList.size(); i++ )
      {
        Object[] select = contasList.get( i );
        String nomeCaixa  = (String)select[0];
        BigDecimal saldoCaixa = (BigDecimal)select[1];
        
        Caixa caixa = new Caixa();
        caixa.setNome( nomeCaixa );
        caixa.setSaldo( saldoCaixa );
        this.frame.addCaixa( nomeCaixa, saldoCaixa.doubleValue() );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo carregarCaixas()" );
      e.printStackTrace();
    }
  }
  private void carregarLancamentos()
  {
    try
    {
      ArrayList<Object[]> lancamentosList = this.banco.getTodosOsLancamentos();
      
      if( lancamentosList == null || lancamentosList.size() == 0 )
        return;
      
      System.out.println( "Lançamentos recebidos: " );
      for( int i=0; i<lancamentosList.size(); i++ )
      {
        Object[] select = lancamentosList.get( i );
        String data  = (String)select[0];
        String descricao = (String)select[1];
        Double valor = (Double)select[2];
        
        data = data.replaceAll( "-", "/" );
        
        DateFormat f = new SimpleDateFormat( "dd/MM/yyyy" );
        
        Lancamento l = new Lancamento();
        l.setDataEmissao(f.parse( data ) );
        l.setDescricao( descricao );
        l.setValor( valor );
        
        this.frame.addLancamento( l );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo carregarLancamentos()" );
      e.printStackTrace();
    }
  }
  private void setarPeriodoHome()
  {
    try
    {
      DateFormat mesAno   = new SimpleDateFormat( "MM/yyyy" );
      DateFormat diaMesAno = new SimpleDateFormat( "dd/MM/yyyy" );
      String primeiroDia  = "01/";
      String ultimoDia    = "";
      String mesAnoStr    = mesAno.format( new Date() );
      
      int mes = Integer.parseInt( mesAnoStr.substring( 0, 2) );
      switch( mes )
      {
        case 1: ultimoDia = "31/"; break;
        case 2: ultimoDia = "28/"; break;
        case 3: ultimoDia = "31/"; break;
        case 4: ultimoDia = "30/"; break;
        case 5: ultimoDia = "31/"; break;
        case 6: ultimoDia = "30/"; break;
        case 7: ultimoDia = "31/"; break;
        case 8: ultimoDia = "31/"; break;
        case 9: ultimoDia = "30/"; break;
        case 10: ultimoDia = "31/"; break;
        case 11: ultimoDia = "30/"; break;
        case 12: ultimoDia = "31/"; break;
      }
      
      Date dataIni = diaMesAno.parse( primeiroDia + mesAnoStr );
      Date dataFim = diaMesAno.parse( ultimoDia + mesAnoStr );
      
      this.frame.setDtcPeriodoIni( dataIni );
      this.frame.setDtcPeriodoFim( dataFim );
    }
    catch( NumberFormatException | ParseException e )
    {
      System.out.println( "Exception no metodo setarPeriodoHome()" );
      e.printStackTrace();
    }
  }
  private void processar()
  {
    try
    {
      do
      {
        System.out.println( "Comando Tela: " + this.frame.getComandoTela() );
        
        this.frame.acessar();
        
        if( this.frame.getComandoTela().equals( "ADICIONAR_CAIXA" ) )
        {
          adicionarCaixa();
        }
        if( this.frame.getComandoTela().equals( "ADICIONAR_CONTA" ) )
        {
          adicionarConta();
        }
        if( this.frame.getComandoTela().equals( "ADICIONAR_LANCAMENTO" ) )
        {
          tratarLancamentoProvisao();
        }
        if( this.frame.getComandoTela().equals( "ATUALIZAR_PERIODO" ) )
        {
          atualizarPeriodoHome();
        }
        if( this.frame.getComandoTela().equals( "CANCELAR_CAIXA" ) )
        {
          cancelarCaixa();
        }
        if( this.frame.getComandoTela().equals( "CANCELAR_CONTA" ) )
        {
          cancelarConta();
        }
        if( this.frame.getComandoTela().equals( "CANCELAR_LANCAMENTO" ) )
        {
          cancelarLancamento();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_CAIXA" ) )
        {
          carregarCaixa();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_CONTA" ) )
        {
          carregarConta();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_LANCAMENTO" ) )
        {
          carregarLancamento();
        }
        if( this.frame.getComandoTela().equals( "CRIAR_BD" ) )
        {
          criarBD();
        }
        if( this.frame.getComandoTela().equals( "EDITAR_CAIXA" ) )
        {
          editarCaixa();
        }
        if( this.frame.getComandoTela().equals( "EDITAR_CONTA" ) )
        {
          editarConta();
        }
        if( this.frame.getComandoTela().equals( "EDITAR_LANCAMENTO" ) )
        {
          editarLancamento();
        }
        if( this.frame.getComandoTela().equals( "EXCLUIR_BD" ) )
        {
          excluirBD();
        }
        if( this.frame.getComandoTela().equals( "EXCLUIR_CAIXA" ) )
        {
          excluirCaixa();
        }
        if( this.frame.getComandoTela().equals( "EXCLUIR_CONTA" ) )
        {
          excluirConta();
        }
        if( this.frame.getComandoTela().equals( "EXCLUIR_LANCAMENTO" ) )
        {
          excluirLancamento();
        }
        if( this.frame.getComandoTela().equals( "EXPORTAR_BD" ) )
        {
          exportarBD();
        }
        if( this.frame.getComandoTela().equals( "IMPORTAR_BD" ) )
        {
          importarBD();
        }
        if( this.frame.getComandoTela().equals( "LIMPAR_BD" ) )
        {
          limparBD();
        }
        if( this.frame.getComandoTela().equals( "PESQUISAR_DIR" ) )
        {
          pesquisarDir();
        }
      }
      while( !this.frame.getComandoTela().equals( "SAIR" ) );
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo processar()" );
      e.printStackTrace();
    }
  }
  private void tratarLancamentoProvisao()
  {
    try
    {
      if( this.frame.getCkbMovPago() )
        adicionarLancamento();
      else
        adicionarProvisao();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void adicionarProvisao()
  {
    try
    {
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void criarBD()
  {
    try
    {
      if( this.bancoCriado )
      {
        Mensagem.aviso( "Banco de dados já criado.\nExclua o atual para criar um novo!", this.frame );
        return;
      }
      
      String caminhoBanco = this.frame.getTxfDiretorioBD();
      String nomeBanco = this.frame.getTxfNomeBD();
      
      if( this.frame.getOS().equals( "WINDOWS" ) && !caminhoBanco.endsWith( "\\") )
      {
        caminhoBanco += "\\";
      }
      else if( this.frame.getOS().equals( "LINUX" ) && !caminhoBanco.endsWith( "/" ) )
      {
        caminhoBanco += "/";
      }

      this.banco.criarBancoDeDados( caminhoBanco + nomeBanco );
      this.bancoCriado = true;
      setarPeriodoHome();
    }
    catch( ClassNotFoundException e )
    {
      System.out.println( "Exception no metodo criarBD()" );
      e.printStackTrace();
    }
  }
  private void atualizarPeriodoHome()
  {
    try
    {
      // fazer alguma coisa quando atualizar..
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo atualizarPeriodoHome()" );
      e.printStackTrace();
    }
  }
  private void limparBD()
  {
    try
    {
      if( this.bancoCriado )
      {
        if( Mensagem.confirmacao( "Confirma DELETAR todos os dados existentes?" , this.frame ) )
        {
          this.banco.limparGeral();
          this.frame.limparModel( this.frame.getMovimentoModel() );
          this.frame.limparModel( this.frame.getContasModel() );
          this.frame.limparModel( this.frame.getCaixasModel() );
          this.frame.limparCombos();
        }
      }
      else
      {
        Mensagem.aviso( "Não existe nenhum banco criado!", this.frame );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo limparBD()" );
      e.printStackTrace();
    }
  }
  private void excluirBD()
  {
    try
    {
      if( this.bancoCriado )
      {
        if( !Mensagem.confirmacao( "Confirma exclusão do Banco de Dados?", this.frame ) )
        {
          return;
        }
        
        this.banco.fechar();
        File bancoAtual = new File( this.banco.getCaminhoAbsoluto() );
        
        if( bancoAtual.delete() )
        {
          this.frame.limparModel( this.frame.getMovimentoModel() );
          this.frame.limparModel( this.frame.getContasModel() );
          this.frame.limparModel( this.frame.getCaixasModel() );
          
          System.out.println
          (
            "Deletando o arquivo:\n" +
            this.banco.getCaminhoAbsoluto() + "... \n" +
            "Banco de dados deletado com sucesso!"
          );
          
          this.bancoCriado = false;
        }
        else
        {
          System.out.println( "Erro ao apagar o arquivo: " + this.banco.getCaminhoAbsoluto() );
        }
      }
      else
      {
        Mensagem.aviso( "Não existe nenhum banco criado!", this.frame );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo excluirBD()" );
      e.printStackTrace();
    }
  }
  private void exportarBD()
  {
    try
    {
      if( !this.bancoCriado )
      {
        Mensagem.aviso( "Não existe nenhum banco criado!", this.frame );
        return;
      }
      this.banco.exportar();
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo exportarBD()" );
      e.printStackTrace();
    }
  }
  private void importarBD()
  {
    try
    {
      // fazer alguma coisa quando clicar..s
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo importarBD()" );
      e.printStackTrace();
    }
  }
  private void pesquisarDir()
  {
    try
    {
      JFileChooser folder = new JFileChooser();
      folder.setCurrentDirectory( new File( "." ) );
      folder.setDialogTitle( "Selecione uma pasta" );
      folder.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
      folder.setAcceptAllFileFilterUsed( false );

      if( folder.showOpenDialog( this.frame ) == JFileChooser.APPROVE_OPTION )
      {
        System.out.println( "Selecionado: " + folder.getSelectedFile());
        this.frame.setTxfDiretorioBD( folder.getSelectedFile().toString() );
      }
    }
    catch( HeadlessException e )
    {
      System.out.println( "Exception no metodo pesquisarDir()" );
      e.printStackTrace();
    }
  }
  private void adicionarConta()
  {
    try
    {
      if( validarCamposConta() )
      {
        Conta conta = new Conta();

        conta.setNome( this.frame.getTxfNomeConta() );
        conta.setTipo( this.frame.getCbxTipoConta().getCodigo() );
          
        String nomeConta = this.frame.getTxfNomeConta();
        String tipoConta = this.frame.getCbxTipoConta().getDescricao();        
        this.frame.addConta( nomeConta, tipoConta );
        
        this.banco.inserirConta(conta);
        
        this.frame.limparCamposConta();
        this.frame.setModoBotoesConta( "NOVO" );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo adicionarConta()" );
      e.printStackTrace();
    }
  }
  private void excluirConta()
  {
    try
    {
      if( Mensagem.confirmacao( "Confirma exclusão da Conta?", this.frame ) )
      {
        int linhaSelecionada = this.frame.getContaSelecionada();
        String contaRemovida = this.frame.removeConta(linhaSelecionada);
        this.frame.atualizarPainelContas();
        this.frame.limparCamposConta();
        this.frame.setModoBotoesConta( "NOVO" );
        
        this.banco.apagarConta( this.banco.obterCodConta( contaRemovida ) );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo excluirConta()" );
      e.printStackTrace();
    }
  }
  private void carregarConta()
  {
    try
    {
      this.frame.setModoBotoesConta( "SELECIONADO" );
      
      String[] linhaSelecionada = this.frame.getConta();

      this.frame.setTxfNomeConta( linhaSelecionada[0] );
      this.frame.setCbxTipoConta( EnumTipoConta.getPorDescricao( linhaSelecionada[1] ) );
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo carregarConta()");
      e.printStackTrace();
    }
  }
  private void editarConta()
  {
    try
    {
      if( validarCamposConta() )
      {
        String nomeConta = this.frame.getTxfNomeConta();
        String tipoConta = this.frame.getCbxTipoConta().getDescricao();
        
        this.frame.substituirConta( this.frame.getContaSelecionada(), nomeConta, tipoConta );
        this.frame.limparCamposConta();
        this.frame.setModoBotoesConta( "NOVO" );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo editarConta()" );
      e.printStackTrace();
    }
  }
  private void cancelarConta()
  {
    try
    {
      this.frame.limparCamposConta();
      this.frame.limparSelecaoConta();
      this.frame.setModoBotoesConta( "NOVO" );
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo cancelarConta()" );
      e.printStackTrace();
    }
  }
  private boolean validarCamposConta()
  {
    try
    {
      boolean contaValida = true;
      String mensagemErro = "";

      if( frame.getTxfNomeConta().isEmpty() )
      {
        contaValida = false;
        mensagemErro += "-> Nome da conta inválido.\n";
      }
      
      if( frame.getCbxTipoConta() == null )
      {
        contaValida = false;
        mensagemErro += "-> Tipo da conta não selecionado.\n";
      }
      
      if( !contaValida )
      {
        Mensagem.erro( "Erro ao adicionar conta:\n" + mensagemErro, this.frame );
      }
      
      return( contaValida );
    }
    catch( HeadlessException e )
    {
      System.out.println( "Exception no metodo validarCamposConta()" );
      e.printStackTrace();
      return( false );
    }
  }
  private void adicionarLancamento()
  {
    try
    {
      if( validarCamposLancamento() )
      {
        Lancamento lancamento = new Lancamento();

        lancamento.setDescricao( this.frame.getTxfMovDescricao() );
        lancamento.setDataEmissao(this.frame.getDtcMovData() );
        lancamento.setValor( this.frame.getDbfMovValor() );
        lancamento.setCod_caixa( this.banco.obterCodCaixa( this.frame.getCbxMovCaixa() ) );
        lancamento.setCod_conta( this.banco.obterCodConta( this.frame.getCbxMovConta() ) );
        
        this.frame.addLancamento( lancamento );
        this.banco.inserirLancamento( lancamento );
        this.frame.limparCamposLancamento();
        this.frame.setModoBotoesMovimento( "NOVO" );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo adicionarConta()" );
      e.printStackTrace();
    }
  }
  private boolean validarCamposLancamento()
  {
    try
    {
      boolean lancamentoValido = true;
      String mensagemErro = "";

      if( this.frame.getTxfMovDescricao().isEmpty() )
      {
        lancamentoValido = false;
        mensagemErro += "-> Descrição do lançamento inválida.\n";
      }
      
      if( this.frame.getDtcMovData() == null )
      {
        lancamentoValido = false;
        mensagemErro += "-> Data do lançamento inválida.\n";
      }
      
      if( this.frame.getDbfMovValor() == 0 )
      {
        lancamentoValido = false;
        mensagemErro += "-> Valor do lançamento inválido.\n";
      }
      
      if( this.frame.getCbxMovConta() == null )
      {
        lancamentoValido = false;
        mensagemErro += "-> Nenhuma conta selecionada.\n";
      }
      
      if( this.frame.getCbxMovCaixa() == null )
      {
        lancamentoValido = false;
        mensagemErro += "-> Nenhum caixa selecionado.\n";
      }
      
      if( !lancamentoValido )
      {
        Mensagem.erro( "Erro ao adicionar lançamento:\n" + mensagemErro, this.frame );
      }
      
      return( lancamentoValido );
    }
    catch( HeadlessException e )
    {
      System.out.println( "Exception no metodo validarCamposLancamento()" );
      e.printStackTrace();
      return( false );
    }
  }
  private void tratarProvisao( Lancamento lancamentoParam )
  {
     //
  }
  private void editarLancamento()
  {
    //
  }
  private void excluirLancamento()
  {
    //
  }
  private void cancelarLancamento()
  {
    //
  }
  private void carregarLancamento()
  {
    //
  }
  private void adicionarCaixa()
  {
    try
    {
      if( validarCamposCaixa() )
      {
        Caixa caixa = new Caixa();
        
        String nomeCaixa = frame.getTxfNomeCaixa();
        Double saldoInicial = frame.getDbfSaldoInicialConta();
        
        caixa.setNome( nomeCaixa );
        caixa.setSaldo( new BigDecimal( saldoInicial ) );
        
        this.frame.addCaixa( nomeCaixa, saldoInicial );
        
        this.banco.inserirCaixa(caixa);
        
        this.frame.limparCamposCaixa();
        this.frame.setModoBotoesCaixa( "NOVO" );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo adicionarCaixa()" );
      e.printStackTrace();
    }
  }
  private boolean validarCamposCaixa()
  {
    try
    {
      boolean caixaValido = true;
      String mensagemErro = "";

      if( this.frame.getTxfNomeCaixa().isEmpty() )
      {
        caixaValido = false;
        mensagemErro += "-> Nome do caixa inválido.\n";
      }
      
      if( !caixaValido )
      {
        Mensagem.erro( "Erro ao adicionar caixa:\n" + mensagemErro, this.frame );
      }
      
      return( caixaValido );
    }
    catch( HeadlessException e )
    {
      System.out.println( "Exception no metodo validarCamposCaixa()" );
      e.printStackTrace();
      return( false );
    }
  }
  private void editarCaixa()
  {
    try
    {
      if( validarCamposCaixa() )
      {
        String nomeCaixa  = this.frame.getTxfNomeCaixa();
        Double saldoCaixa = this.frame.getDbfSaldoInicialConta();
        
        this.frame.substituirCaixa( this.frame.getCaixaSelecionado(), nomeCaixa, saldoCaixa );
        this.frame.limparCamposCaixa();
        this.frame.setModoBotoesCaixa( "NOVO" );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo editarCaixa()" );
      e.printStackTrace();
    }
  }
  private void excluirCaixa()
  {
    try
    {
      if( Mensagem.confirmacao( "Confirma exclusão do Caixa?", this.frame ) )
      {
        int linhaSelecionada = this.frame.getCaixaSelecionado();
        String caixaRemovido = this.frame.removeCaixa(linhaSelecionada);
        this.frame.atualizarPainelCaixas();
        this.frame.limparCamposCaixa();
        this.frame.setModoBotoesCaixa( "NOVO" );
        
        this.banco.apagarCaixa( this.banco.obterCodCaixa( caixaRemovido ) );
      }
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo excluirCaixa()" );
      e.printStackTrace();
    }
  }
  private void cancelarCaixa()
  {
    try
    {
      this.frame.limparCamposCaixa();
      this.frame.limparSelecaoCaixa();
      this.frame.setModoBotoesCaixa( "NOVO" );
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo cancelarCaixa()" );
      e.printStackTrace();
    }
  }
  private void carregarCaixa()
  {
    try
    {
      this.frame.setModoBotoesCaixa( "SELECIONADO" );
      
      Object[] linhaSelecionada = this.frame.getCaixa();

      this.frame.setTxfNomeCaixa        ( (String) linhaSelecionada[0] );
      this.frame.setDbfSaldoInicialConta( (Double) linhaSelecionada[1] );
    }
    catch( Exception e )
    {
      System.out.println( "Exception no metodo carregarCaixa()");
      e.printStackTrace();
    }
  }
  public static void main( String[] s )
  {
    Main programa = new Main();
    programa.exec();
  }
}