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

import java.awt.HeadlessException;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Ricardo
 */
public class Main
{
  private final Tela frame;
  private final BD banco;
  private boolean bancoCriado;
  private Date perDataIni;
  private Date perDataFim;
  private char transacaoConta;
  private char transacaoCaixa;
  private char transacaoLancamento;
  
  public Main()
  {
    // 1.0.1 - Periodo foi para painel superior
    // 1.0.2 - Banco de dados totalmente modificado
    // 1.0.3 - Muitos bugs corrigidos e provisao funcionando
    // 1.0.4 - Grafico funcionando
    this.frame = new Tela( "My Good Money - Controle Financeiro - Versão 1.0.4" );
    this.frame.limparCamposConta();
    this.frame.setModoBotoesConta( "NOVO" );
    this.frame.setModoBotoesCaixa( "NOVO" );
    this.frame.setModoBotoesMovimento( "NOVO" );
    this.bancoCriado = false;
    this.banco = new BD();
    this.perDataIni = new Date();
    this.perDataFim = new Date();
    this.transacaoConta = 'I';
    this.transacaoCaixa = 'I';
    this.transacaoLancamento = 'I';
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
      carregarContasAoIniciarPrograma();
      carregarCaixasAoIniciarPrograma();
      carregarLancamentosAoIniciarPrograma();
      this.frame.selecionarAba( 0 );
      this.frame.atualizarPainelCaixas();
      this.frame.atualizarPainelContas();
      this.frame.atualizarPainelMovimento();
      carregarGrafico();
    }
    else
    {
      this.frame.selecionarAba( 4 );
    }
  }
  private void carregarContasAoIniciarPrograma()
  {
    try
    {
      ArrayList<Conta> contasList = this.banco.selectAlTodasAsContas();
      
      if( contasList.isEmpty() )
      {
        return;
      }

      for( Conta conta : contasList )
      {
        this.frame.addConta( conta );
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void carregarCaixasAoIniciarPrograma()
  {
    try
    {
      ArrayList<Caixa> caixasList = this.banco.selectAlTodosOsCaixas();
      
      if( caixasList.isEmpty() )
      {
        return;
      }

      for( Caixa caixa : caixasList )
      {
        this.frame.addCaixa( caixa );
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void carregarLancamentosAoIniciarPrograma()
  {
    ArrayList<Lancamento> lancamentosList = this.banco.selectAlTodosOsLancamentosPeriodo
    (
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim )
    );
    
    if( lancamentosList.isEmpty() )
    {
      return;
    }
    
    for( Lancamento lancamento : lancamentosList )
    {
      this.frame.addLancamento( lancamento );
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
        case 1:  ultimoDia = "31/"; break;
        case 2:  ultimoDia = "28/"; break;
        case 3:  ultimoDia = "31/"; break;
        case 4:  ultimoDia = "30/"; break;
        case 5:  ultimoDia = "31/"; break;
        case 6:  ultimoDia = "30/"; break;
        case 7:  ultimoDia = "31/"; break;
        case 8:  ultimoDia = "31/"; break;
        case 9:  ultimoDia = "30/"; break;
        case 10: ultimoDia = "31/"; break;
        case 11: ultimoDia = "30/"; break;
        case 12: ultimoDia = "31/"; break;
      }
      
      Date dataIni = diaMesAno.parse( primeiroDia + mesAnoStr );
      Date dataFim = diaMesAno.parse( ultimoDia + mesAnoStr );
      
      this.frame.setDtcPeriodoIni( dataIni );
      this.frame.setDtcPeriodoFim( dataFim );
      this.perDataIni = dataIni;
      this.perDataFim = dataFim;
    }
    catch( NumberFormatException | ParseException e )
    {
      e.printStackTrace();
    }
  }
  private void processar()
  {
    try
    {
      do
      {
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
          adicionarLancamento();
        }
        if( this.frame.getComandoTela().equals( "ATUALIZAR_PERIODO" ) )
        {
          atualizarPeriodo();
        }
        if( this.frame.getComandoTela().equals( "CANCELAR_CAIXA_SELECIONADO" ) )
        {
          cancelarCaixaSelecionado();
        }
        if( this.frame.getComandoTela().equals( "CANCELAR_CONTA_SELECIONADA" ) )
        {
          cancelarContaSelecionada();
        }
        if( this.frame.getComandoTela().equals( "CANCELAR_LANCAMENTO_SELECIONADO" ) )
        {
          cancelarLancamentoSelecionado();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_CAIXA_SELECIONADO" ) )
        {
          carregarCaixaSelecionado();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_CAIXA_HOME" ) )
        {
          carregarCaixaHome();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_CONTA_SELECIONADA" ) )
        {
          carregarContaSelecionada();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_CONTA_HOME" ) )
        {
          carregarContaHome();
        }
        if( this.frame.getComandoTela().equals( "CARREGAR_LANCAMENTO_SELECIONADO" ) )
        {
          carregarLancamentoSelecionado();
        }
        if( this.frame.getComandoTela().equals( "CONFIRMAR_EXTRATO" ) )
        {
          obterExtratoConta();
        }
        if( this.frame.getComandoTela().equals( "CRIAR_BD" ) )
        {
          criarBD();
        }
        if( this.frame.getComandoTela().equals( "DECREMENTAR_PERIODO" ) )
        {
          decrementarPeriodo();
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
        if( this.frame.getComandoTela().equals( "INCREMENTAR_PERIODO" ) )
        {
          incrementarPeriodo();
        }
        if( this.frame.getComandoTela().equals( "LIMPAR_BD" ) )
        {
          limparBD();
        }
        if( this.frame.getComandoTela().equals( "PESQUISAR_DIR" ) )
        {
          pesquisarDir();
        }
        this.frame.acessar();
      }
      while( !this.frame.getComandoTela().equals( "SAIR" ) );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void atualizarPeriodo()
  {
    this.perDataIni = this.frame.getDtcPeriodoIni();
    this.perDataFim = this.frame.getDtcPeriodoFim();
    recarregarLancamentos();
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

      this.banco.criarBancoDeDados( caminhoBanco + nomeBanco );
      this.bancoCriado = true;
      setarPeriodoHome();
    }
    catch( ClassNotFoundException e )
    {
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
          this.banco.excluirTodosRegistros();
          this.frame.limparMovimentosModel();
          this.frame.limparContasModel();
          this.frame.limparCaixasModel();
          this.frame.limparCombos();
        }
      }
      else
      {
        Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
      }
    }
    catch( Exception e )
    {
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
        File bancoAtual = new File( this.banco.getSCaminhoAbsoluto() );
        
        if( bancoAtual.delete() )
        {
          this.frame.limparMovimentosModel();
          this.frame.limparContasModel();
          this.frame.limparCaixasModel();
          
          System.out.println
          (
            "Deletando o arquivo: " + this.banco.getSCaminhoAbsoluto() + "... \n" +
            "Banco de dados deletado com sucesso!"
          );
          
          // limpa as contas e caixas do home
          this.frame.limparCombos();
          this.bancoCriado = false;
        }
        else
        {
          System.out.println( "Erro ao apagar o arquivo: " + this.banco.getSCaminhoAbsoluto() );
        }
      }
      else
      {
        Mensagem.aviso( "Não existe nenhum banco de dadaos criado!", this.frame );
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void exportarBD()
  {
    try
    {
      Mensagem.aviso( "Função em desenvolvimento...!", this.frame );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void importarBD()
  {
    try
    {
      Mensagem.aviso( "Função em desenvolvimento...!", this.frame );
    }
    catch( Exception e )
    {
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
      e.printStackTrace();
    }
  }
  private void adicionarConta()
  {
    try
    {
      if( !this.bancoCriado )
      {
        Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
        return;
      }

      if( validarCamposConta() )
      {
        Conta conta = this.frame.getContaTela();

        this.frame.addConta( conta );
        this.banco.inserirConta( conta );
        this.frame.setModoBotoesConta( "NOVO" );
        this.frame.limparCamposLancamento();
        this.frame.limparCamposConta();
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void excluirConta()
  {
    try
    {
      if( Mensagem.confirmacao( "Confirma exclusão da Conta?", this.frame ) )
      {
        Conta conta = this.frame.getContaSelecionada();
        conta.setICodConta( this.banco.selectICodConta( conta.getSNome() ) );
        
        this.frame.removeConta( conta );
        this.banco.excluirConta( conta );
        this.frame.limparCamposConta();
        this.frame.setModoBotoesConta( "NOVO" );
        this.transacaoConta = 'I';
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void carregarContaSelecionada()
  {
    try
    {
      Conta conta = this.frame.getContaSelecionada();
      
      this.frame.setModoBotoesConta( "SELECIONADO" );
      this.frame.setTxfNomeConta( conta.getSNome() );
      this.frame.setCbxTipoConta( EnumTipoConta.getPorCodigo( conta.getCTipo() ) );
      this.transacaoConta = 'A';
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void editarConta()
  {
    try
    {
      if( validarCamposConta() )
      {
        Conta contaVelha = this.frame.getContaSelecionada();
        Conta contaNova = new Conta();
        
        contaVelha.setICodConta( this.banco.selectICodConta( contaVelha.getSNome() ) );
        contaNova.setICodConta( contaVelha.getICodConta() );
        contaNova.setSNome( this.frame.getTxfNomeConta() );
        contaNova.setCTipo( this.frame.getCbxTipoConta().getCCodigo() );
        
        this.frame.substituirConta( contaVelha, contaNova );
        this.banco.alterarConta( contaNova );
        this.frame.limparCamposConta();
        this.frame.setModoBotoesConta( "NOVO" );
        this.transacaoConta = 'I';
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void cancelarContaSelecionada()
  {
    try
    {
      this.frame.limparCamposConta();
      this.frame.limparSelecaoConta();
      this.frame.setModoBotoesConta( "NOVO" );
      this.transacaoConta = 'I';
    }
    catch( Exception e )
    {
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

      if( this.frame.getCbxTipoConta() != null && this.transacaoConta == 'I' )
      {
        Conta conta = this.frame.getContaTela();
        int codContaNova = 0;
        codContaNova = this.banco.selectICodConta( conta.getSNome() );

        if( codContaNova != 0 )  
        {
          contaValida = false;
          mensagemErro += "-> Não é permitido duas contas com o mesmo nome!\n";
        }
      }
      
      if( !contaValida )
      {
        Mensagem.erro( "Erro ao adicionar conta:\n" + mensagemErro, this.frame );
      }
      
      return( contaValida );
    }
    catch( HeadlessException e )
    {
      e.printStackTrace();
      return( false );
    }
  }
  private void adicionarLancamento()
  {
    try
    {
      if( !this.bancoCriado )
      {
        Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
        return;
      }

      if( validarCamposLancamento() )
      {
        Lancamento lancamento = this.frame.getLancamentoTela( this.banco );
        
        // tratar recorrencia
        if( this.frame.getMovRecorrencia() == 'S' )
        {
          ArrayList<Integer> datasVencimento = DateTools.calcularVencimentos
          (
            lancamento.getIDataVencimento(),
            this.frame.getItfNumVezes(),
            (this.frame.getItfNumPeriodo()*this.frame.getCbxPeriodo().getIDias())
          );
          
          for( Integer data : datasVencimento )
          {
            Lancamento provisao = new Lancamento();
            
            provisao.setIDataEmissao( lancamento.getIDataEmissao() );
            provisao.setIDataVencimento( data );
            
            if( lancamento.getIDataQuitacao() > 0 )
              provisao.setIDataQuitacao( data );
            else
              provisao.setIDataQuitacao( 0 );
            
            provisao.setDescricao( lancamento.getSDescricao() );
            provisao.setDValor( lancamento.getDValor() );
            provisao.setICodConta( lancamento.getICodConta() );
            provisao.setICodCaixa( lancamento.getICodCaixa() );
            
            this.banco.inserirLancamento( provisao );
            
            if( provisao.getIDataQuitacao() > 0 )
            {
              if( lancamento.getCTipo() == 'D' )
              {
                this.banco.subtrairDoSaldoCaixa( lancamento.getICodCaixa(), lancamento.getDValor() );
                atualizarSaldoCadastroCaixa( this.frame.getCbxMovCaixa(), lancamento.getDValor(), "-" );
              }
              else
              {
                this.banco.adicionarAoSaldoCaixa( lancamento.getICodCaixa(), lancamento.getDValor() );
                atualizarSaldoCadastroCaixa( this.frame.getCbxMovCaixa(), lancamento.getDValor(), "+" );
              }
            }
          }
        }
        else
        {
          this.banco.inserirLancamento( lancamento );
          
          if( lancamento.getIDataQuitacao() > 0 )
          {
            if( lancamento.getCTipo() == 'D' )
            {
              this.banco.subtrairDoSaldoCaixa( lancamento.getICodCaixa(), lancamento.getDValor() );
              atualizarSaldoCadastroCaixa( this.frame.getCbxMovCaixa(), lancamento.getDValor(), "-" );
            }
            else
            {
              this.banco.adicionarAoSaldoCaixa( lancamento.getICodCaixa(), lancamento.getDValor() );
              atualizarSaldoCadastroCaixa( this.frame.getCbxMovCaixa(), lancamento.getDValor(), "+" );
            }
          }
        }

        this.frame.limparCamposLancamento();
        this.frame.setModoBotoesMovimento( "NOVO" );
        recarregarLancamentos();
        
        Mensagem.info( "Lançamento incluído com sucesso!" , this.frame);
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void recarregarLancamentos()
  {
    this.frame.limparMovimentosModel();
    ArrayList<Lancamento> lancamentosList = this.banco.selectAlTodosOsLancamentosPeriodo
    (
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim )
    );
    
    if( lancamentosList.isEmpty() )
    {
      return;
    }
    
    for( Lancamento lancamento : lancamentosList )
    {
      this.frame.addLancamento( lancamento );
    }
  }
  private void atualizarSaldoCadastroCaixa( String caixaParam, Double valorParam, String operacaoParam )
  {
    try
    {
      Caixa caixa = this.frame.getCaixa( caixaParam );
      
      switch( operacaoParam )
      {
        case "+":
        {
          caixa.setDSaldo( caixa.getDSaldo() + valorParam);
          break;
        }
        case "-":
        {
          caixa.setDSaldo( caixa.getDSaldo() - valorParam);
        }
      }
      
      this.frame.substituirCaixa( caixa, caixa );
    }
    catch( Exception e )
    {
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
      
      if( this.frame.getDtcMovData() != null &&
          this.frame.getCbxMovConta() != null &&
          this.frame.getCbxMovCaixa() != null &&
          this.transacaoLancamento == 'I'
        )
      {
        Lancamento lanc = this.frame.getLancamentoTela( this.banco );
        int codLancNovo = this.banco.selectICodLancamento( lanc.getIDataVencimento(), lanc.getDValor(), lanc.getICodConta(), lanc.getICodCaixa() );
        if( codLancNovo != 0 )
        {
          lancamentoValido = false;
          mensagemErro += "-> Já existe um lançamento para este vencimento, valor, conta e caixa.\n";
        }
      }
      
      if( !lancamentoValido )
      {
        Mensagem.erro( "Erro ao adicionar lançamento:\n" + mensagemErro, this.frame );
      }
      
      return( lancamentoValido );
    }
    catch( HeadlessException e )
    {
      e.printStackTrace();
      return( false );
    }
  }
  private void editarLancamento()
  {
    try
    {
      if( validarCamposLancamento() )
      {
        Lancamento lancamentoVelho = this.frame.getLancamentoSelecionado( this.banco );
        Lancamento lancamentoNovo  = this.frame.getLancamentoTela( this.banco );
        lancamentoNovo.setICodLancamento( lancamentoVelho.getICodLancamento() );
        
        if( lancamentoNovo.getCTipo() != lancamentoVelho.getCTipo() )
        {
          Mensagem.erro( "Alteração não permidita: D -> C ou C-> D", this.frame );
          return;
        }
        
        if( lancamentoVelho.getIDataQuitacao() != 0 && !this.frame.getCkbMovPago() )
        {
          // transformar em provisao
          lancamentoNovo.setIDataEmissao( lancamentoVelho.getIDataEmissao() );
          lancamentoNovo.setIDataQuitacao( 0 );
          // -> remover o valor do lancamento do caixa
          if( lancamentoVelho.getCTipo() == 'D' )
          {
            // somar no caixa
            this.banco.adicionarAoSaldoCaixa( lancamentoNovo.getICodCaixa(), lancamentoNovo.getDValor() );
          }
          else if( lancamentoVelho.getCTipo() == 'C' )
          {
            // debitar no caixa
            this.banco.subtrairDoSaldoCaixa( lancamentoVelho.getICodCaixa(), lancamentoVelho.getDValor() );
          }
        }
        else if( lancamentoVelho.getIDataQuitacao() == 0 && this.frame.getCkbMovPago() )
        {
          // transformat em lancamento a vista
          lancamentoNovo.setIDataVencimento( lancamentoVelho.getIDataVencimento() );
          // -> adicionar o valor do lancamento ao caixa
          if( lancamentoVelho.getCTipo() == 'D' )
          {
            // debitar no caixa
            this.banco.subtrairDoSaldoCaixa( lancamentoNovo.getICodCaixa(), lancamentoNovo.getDValor() );
          }
          else if( lancamentoVelho.getCTipo() == 'C' )
          {
            // somar no caixa
            this.banco.adicionarAoSaldoCaixa( lancamentoNovo.getICodCaixa(), lancamentoNovo.getDValor() );
          }
        }
        
        this.frame.substituirLancamento( lancamentoVelho, lancamentoNovo );
        this.banco.alterarLancamento( lancamentoNovo );
        this.frame.limparCamposLancamento();
        this.frame.setModoBotoesMovimento( "NOVO" );
        recarregarLancamentos();
        this.transacaoLancamento = 'I';
        Caixa caixa = this.banco.selectCaixa( lancamentoNovo.getICodCaixa() );
        this.frame.atualizarSaldoCaixa( caixa );
        this.frame.habilitarComponentesRecorrencia( true );
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void excluirLancamento()
  {
    try
    {
      if( Mensagem.confirmacao( "Confirma exclusão do Lançamento?", this.frame ) )
      {
        Lancamento lancamento = this.frame.getLancamentoSelecionado( this.banco );
        
        if( lancamento.getCTipo() == 'D' )
        {
          this.banco.adicionarAoSaldoCaixa( lancamento.getICodCaixa(), lancamento.getDValor() );
          atualizarSaldoCadastroCaixa( this.banco.selectSNomeCaixa( lancamento.getICodCaixa()), lancamento.getDValor(), "+" );
        }
        else
        {
          this.banco.subtrairDoSaldoCaixa( lancamento.getICodCaixa(), lancamento.getDValor() );
          atualizarSaldoCadastroCaixa( this.banco.selectSNomeCaixa(lancamento.getICodCaixa()), lancamento.getDValor(), "-" );
        }
        
        this.frame.removeLancamento( lancamento );
        this.banco.excluirLancamento( lancamento );
        this.frame.limparCamposLancamento();
        this.frame.setModoBotoesMovimento("NOVO" );
        this.transacaoLancamento = 'I';
        this.frame.habilitarComponentesRecorrencia( true );
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void cancelarLancamentoSelecionado()
  {
    try
    {
      this.frame.limparCamposLancamento();
      this.frame.limparSelecaoMovimento();
      this.frame.setModoBotoesMovimento( "NOVO" );
      this.frame.habilitarComponentesRecorrencia( true );
      this.transacaoLancamento = 'I';
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void carregarLancamentoSelecionado()
  {
    try
    {
      this.frame.habilitarComponentesRecorrencia( false );

      Lancamento lanc = this.frame.getLancamentoSelecionado( this.banco );
      
      this.frame.setTxfMovDescricao( lanc.getSDescricao() );
      
      if( lanc.getIDataQuitacao() == 0 )
      {
        this.frame.setDtcMovData( DateTools.parseDataIntToDate( lanc.getIDataVencimento() ) );
        this.frame.setCkbMovPago( false );
      }
      else
      {
        this.frame.setDtcMovData( DateTools.parseDataIntToDate( lanc.getIDataQuitacao() ) );
        this.frame.setCkbMovPago( true );
      }
      this.frame.setDbfMovValor( lanc.getDValor() );
      
      String nomeConta = this.banco.selectSNomeConta( lanc.getICodConta() );
      String nomeCaixa = this.banco.selectSNomeCaixa( lanc.getICodCaixa() );
      
      this.frame.setCbxMovConta( nomeConta );
      this.frame.setCbxMovCaixa( nomeCaixa );
      
      this.frame.setModoBotoesMovimento( "SELECIONADO" );
      this.transacaoLancamento = 'A';
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void adicionarCaixa()
  {
    try
    {
      if( !this.bancoCriado )
      {
        Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
        return;
      }

      if( validarCamposCaixa() )
      {
        Caixa caixa = this.frame.getCaixaTela();
        
        this.frame.addCaixa( caixa );
        this.banco.inserirCaixa( caixa );
        this.frame.limparCamposCaixa();
        this.frame.setModoBotoesCaixa( "NOVO" );
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void selecionarCaixaRecemAdicionado( Caixa caixaParam )
  {
    try
    {
      this.frame.selecionarCaixaHome( caixaParam.getSNome() );
      
      NumberFormat df = new DecimalFormat( "#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR") ) );
      this.frame.setLblResValorSaldoCaixa( "R$ " + df.format( caixaParam.getDSaldo() ).toString() );
    }
    catch( Exception e )
    {
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
      
      if( this.transacaoCaixa == 'I' )
      {
        Caixa caixa = this.frame.getCaixaTela();
        int codCaixaNovo = this.banco.selectICodCaixa( caixa.getSNome() );
        if( codCaixaNovo != 0 )
        {
          caixaValido = false;
          mensagemErro += "-> Não é permitido dois caixas com o mesmo nome!\n";
        }
      }
      
      if( !caixaValido )
      {
        Mensagem.erro( "Erro ao adicionar caixa:\n" + mensagemErro, this.frame );
      }
      
      return( caixaValido );
    }
    catch( HeadlessException e )
    {
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
        Caixa caixaVelho = this.frame.getCaixaSelecionado();
        Caixa caixaNovo  = new Caixa();
        
        caixaVelho.setICodCaixa( this.banco.selectICodCaixa( caixaVelho.getSNome() ) );
        caixaNovo.setICodCaixa( caixaVelho.getICodCaixa() );
        caixaNovo.setSNome( this.frame.getTxfNomeCaixa() );
        caixaNovo.setDSaldo( this.frame.getDbfSaldoInicialCaixa() );
        
        this.frame.substituirCaixa( caixaVelho, caixaNovo );
        this.banco.alterarCaixa( caixaNovo );
        this.frame.limparCamposCaixa();
        this.frame.setModoBotoesCaixa( "NOVO" );
        this.transacaoCaixa = 'I';
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void excluirCaixa()
  {
    try
    {
      if( Mensagem.confirmacao( "Confirma exclusão do Caixa?", this.frame ) )
      {
        Caixa caixa = this.frame.getCaixaSelecionado();
        caixa.setICodCaixa( this.banco.selectICodCaixa( caixa.getSNome() ) );
        
        this.frame.removeCaixa( caixa );
        this.banco.excluirCaixa( caixa );
        this.frame.limparCamposCaixa();
        this.frame.setModoBotoesCaixa( "NOVO" );
        this.transacaoCaixa = 'I';
      }
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void cancelarCaixaSelecionado()
  {
    try
    {
      this.frame.limparCamposCaixa();
      this.frame.limparSelecaoCaixa();
      this.frame.setModoBotoesCaixa( "NOVO" );
      this.transacaoCaixa = 'I';
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void carregarCaixaSelecionado()
  {
    try
    {
      Caixa caixa = this.frame.getCaixaSelecionado();
      
      this.frame.setModoBotoesCaixa( "SELECIONADO" );
      this.frame.setTxfNomeCaixa( caixa.getSNome() );
      this.frame.setDbfSaldoInicialCaixa( caixa.getDSaldo() );
      this.transacaoCaixa= 'A';
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }
  private void carregarCaixaHome()
  {
    if( !this.bancoCriado )
    {
      Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
      return;
    }
    
    String caixaSelecionado = this.frame.getCbxResCaixa();
    
    // saldo do caixa
    Double saldoSelecionado = this.banco.selectDSaldoCaixa( caixaSelecionado );
    this.frame.setLblResValorSaldoCaixa( ValueTools.format( saldoSelecionado ) );
    
    // total de entradas
    double totalEntradas = this.banco.selectDTotalEntradaPorCaixa
    (
      caixaSelecionado,
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoCaixa()
    );
    
    if( totalEntradas != 0 )
      this.frame.setLblResValTotEntrCaixa( ValueTools.format( totalEntradas ) );
    else
      this.frame.setLblResValTotEntrCaixa( "R$ 0,00" );
    
    // total de saidas
    double totalSaidas = this.banco.selectDTotalSaidaPorCaixa
    (
      caixaSelecionado,
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoCaixa()
    );
    
    if( totalSaidas != 0 )
      this.frame.setLblResValTotSaidaCaixa( ValueTools.format( totalSaidas ) );
    else
      this.frame.setLblResValTotSaidaCaixa( "R$ 0,00" );
    
    // tratar saldo provisionado
    /* se marcado provisao, obter os creditos e debitos provisionados */
    if( this.frame.getCkbResumoCaixa() )
    {
      Double entradaProvisionada = this.banco.selectDTotalEntradaProvisionada
      (
        caixaSelecionado,
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim )
      );
      
      if( entradaProvisionada.equals( Double.NaN ) )
        entradaProvisionada = 0.0;

      Double saidaProvisionada = this.banco.selectDTotalSaidaProvisionada
      (
        caixaSelecionado,
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim )
      );
      
      if( saidaProvisionada.equals( Double.NaN ) )
        saidaProvisionada = 0.0;
      
      saldoSelecionado = saldoSelecionado + entradaProvisionada - saidaProvisionada;
      
      this.frame.setLblResValorSaldoCaixa( ValueTools.format( saldoSelecionado ) );
    }
    
    carregarGrafico();
  }
  private void carregarContaHome()
  {
    if( !this.bancoCriado )
    {
      Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
      return;
    }
    
    try
    {
      String contaSelecionada = this.frame.getCbxResConta();
      int codConta = this.banco.selectICodConta( contaSelecionada );
      char tipoConta = this.banco.selectCTipoConta( codConta );
    
      // total de saida/entrada
      double totalMovPeriodo = this.banco.selectDTotalMovimentoPeriodo
      (
        tipoConta,
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim ),
        this.frame.getCkbResumoConta()
      );
      
      if( totalMovPeriodo == 0 )
      {
        this.frame.limparResumoConta();
        return;
      }
      
      double totalContaPeriodo = this.banco.selectDTotalContaPeriodo
      (
        codConta,
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim ),
        this.frame.getCkbResumoConta()
      );
        
      if( totalContaPeriodo == 0 )
      {
        this.frame.limparResumoConta();
        return;
      }
      
      // porcentagem do periodo
      BigDecimal porcentagem = new BigDecimal( totalContaPeriodo ).divide( new BigDecimal( totalMovPeriodo ), 4, BigDecimal.ROUND_HALF_UP );
      Double porcentagemFinal = porcentagem.doubleValue() * 100;
      String formatado = porcentagemFinal.toString();
      
      
      if( formatado.length() - formatado.indexOf( "." ) >= 3 )
      {
        formatado = formatado.substring( 0, formatado.indexOf( "." )+3 );
      }
      else if( formatado.length() - formatado.indexOf( "." ) == 2 )
      {
        formatado = formatado + "0";
      }
      else if( formatado.length() - formatado.indexOf( "." ) == 1 )
      {
        formatado = formatado + "00";
      }

      this.frame.setLblResValorMovContaPc( formatado + " %" );
      
      // valor do periodo
      this.frame.setLblResValorMovContaRs( ValueTools.format( totalContaPeriodo ) );
    
      // total do ano
      String inicioAno = DateTools.getSAnoAtual() + "0101";
      String fimAno    = DateTools.getSAnoAtual() + "1231";
      
      int inicioAnoInt = Integer.parseInt( inicioAno );
      int fimAnoInt = Integer.parseInt( fimAno );
      
      double totalAnual = this.banco.selectDTotalContaPeriodo( codConta, inicioAnoInt, fimAnoInt, this.frame.getCkbResumoConta() );

      if( totalAnual != 0 )
        this.frame.setLblResValorMovContaAno( ValueTools.format( totalAnual ) );
    }
    catch( NumberFormatException e )
    {
      e.printStackTrace();
    }
  }
  public void decrementarPeriodo()
  {
    if( !this.bancoCriado )
    {
      Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
      return;
    }
    
    Date dataIni = DateTools.somarSubtrairUmMes( this.perDataIni, '-' );
    Date dataFim = DateTools.somarSubtrairUmMes( this.perDataFim, '-' );
    
    this.perDataIni = dataIni;
    this.perDataFim = dataFim;
    
    this.frame.setDtcPeriodoIni( dataIni );
    this.frame.setDtcPeriodoFim( dataFim );
    
    recarregarLancamentos();
  }
  public void incrementarPeriodo()
  {
    if( !this.bancoCriado )
    {
      Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
      return;
    }

    Date dataIni = DateTools.somarSubtrairUmMes( this.perDataIni, '+' );
    Date dataFim = DateTools.somarSubtrairUmMes( this.perDataFim, '+' );
    
    this.perDataIni = dataIni;
    this.perDataFim = dataFim;
    
    this.frame.setDtcPeriodoIni( dataIni );
    this.frame.setDtcPeriodoFim( dataFim );
    
    recarregarLancamentos();
  }
  private boolean validarCamposExtrato()
  {
    try
    {
      boolean valido = true;
      String mensagem = "";
      
      if( this.frame.getCbxExtratoConta() == null )
      {
        valido = false;
        mensagem += "Selecione uma conta.\n";
      }
      if( this.frame.getDtcExtratoIni() == null )
      {
        valido = false;
        mensagem += "Período Inicial inválido.\n";
      }
      if( this.frame.getDtcExtratoFim() == null )
      {
        valido = false;
        mensagem += "Período Final inválido.\n";
      }
      
      if( !valido )
      {
        Mensagem.erro( "Erro ao solicitar extrato:\n" + mensagem, this.frame );
      }
      
      return( valido );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return( false );
    }
  }
  private void obterExtratoConta()
  {
    if( !this.bancoCriado )
    {
      Mensagem.aviso( "Não existe nenhum banco de dados criado!", this.frame );
      return;
    }

    if( !validarCamposExtrato() )
    {
      return;
    }
    
    int codConta = this.banco.selectICodConta( this.frame.getCbxExtratoConta() );
    
    ArrayList<String[]> extratoList = this.banco.selectAlExtratoContaPeriodo
    (
      codConta,                                                       // conta
      DateTools.parseDateToInteger( this.frame.getDtcExtratoIni() ),  // data inicial
      DateTools.parseDateToInteger( this.frame.getDtcExtratoFim() ),  // data final
      this.frame.getCkbExtratoProvisao()                              // provisao?
     );
    
    if( extratoList.isEmpty() )
    {
      this.frame.setTxtExtrato( "Não existem lançamentos para esta conta." );
      return;
    }
    
    String dtEmissao  = "Data Vcto";
    String dtQuitacao = "Data Quitação";
    String descricao  = "Descrição";
    String valor      = "Valor";
    String caixa      = "Caixa";
      
    dtEmissao  = String.format( "%1$-" + 15 + "s", dtEmissao );
    dtQuitacao = String.format( "%1$-" + 15 + "s", dtQuitacao );
    descricao  = String.format( "%1$-" + 15 + "s", descricao );
    valor      = String.format( "%1$-" + 15 + "s", valor );
    caixa      = String.format( "%1$-" + 15 + "s", caixa );
      
    String cabecalho =
      dtEmissao + dtQuitacao + descricao + valor + caixa;

    this.frame.setTxtExtrato( cabecalho );
    
    Double totalGeral = new Double( 0.0 );

    for( String[] linha : extratoList )
    {
      String dtEmi = String.format( "%1$-" + 15 + "s", linha[0] );
      String dtQui = String.format( "%1$-" + 15 + "s", linha[1] );
      String descr = String.format( "%1$-" + 15 + "s", linha[2] );
      String val   = String.format( "%1$-" + 15 + "s", linha[3] );
      String cai   = String.format( "%1$-" + 15 + "s", linha[4] );

      // trata descricao para ter no maximo 15 caracteres
      if( descr.length() > 15 )
        descr = descr.substring( 0, 14 ) + " ";

      totalGeral += ValueTools.unformat( val );
      String linhaFinal = dtEmi + dtQui + descr + val + cai;
      this.frame.setTxtExtrato( this.frame.getTxtExtrato() + "\n" + linhaFinal );
    }
    
    // linha do total
    String divisor = "---------------------------------------------------------------------------";
    String ultimaLinha =
      String.format( "%1$-" + 15 + "s", " " ) +
      String.format( "%1$-" + 15 + "s", " " ) +
      String.format( "%1$-" + 15 + "s", "TOTAL:" ) +
      String.format( "%1$-" + 15 + "s", ValueTools.format( totalGeral ) ) +
      String.format( "%1$-" + 15 + "s", " " );

    this.frame.setTxtExtrato( this.frame.getTxtExtrato() + "\n" + divisor + "\n" + ultimaLinha );
  }
  public void carregarGrafico()
  {
    String caixa = "";
    
    if( this.frame.getCbxResCaixa() != null )
      caixa = this.frame.getCbxResCaixa();
    else
    {
      this.frame.setGrafico( null );
      return;
    }
    
    DefaultPieDataset grafico = new DefaultPieDataset();
    int codCaixa = this.banco.selectICodCaixa( caixa );
    
    ArrayList<Object[]> dadosGrafico = this.banco.selectAlLancamentosGrafico
    (
      codCaixa,
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoCaixa()
    );
    
    if( dadosGrafico.isEmpty() )
    {
      this.frame.setGrafico( null );
      return;
    }
    
    for( Object[] item : dadosGrafico )
    {
      String conta = (String) item[0];
      Double valor = (Double) item[1];
      
      grafico.setValue( conta, valor );
    }
    
    JFreeChart chart = ChartFactory.createPieChart( "" , grafico );
    
    chart.removeLegend();
    chart.removeSubtitle( null );
    
    this.frame.setGrafico( chart );
  }
  public static void main( String[] s )
  {
    Main programa = new Main();
    programa.exec();
  }
}