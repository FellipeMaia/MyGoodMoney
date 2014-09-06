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

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Main {
  private Tela frame;
  private Date perDataIni;
  private Date perDataFim;
  private char transacaoConta;
  private char transacaoCaixa;
  private char transacaoLancamento;
  private CaixaDAO caixaDAO;
  private ContaDAO contaDAO;
  private LancamentoDAO lancamentoDAO;
  private Caixa caixaAtual;
  private Conta contaAtual;
  private Lancamento lancamentoAtual;
  
  public Main() {
    try {
      SwingUtilities.invokeAndWait( new Runnable() {
        @Override
        public void run(){
          frame = new Tela( "My Good Money - Controle Financeiro - Versão 3.2 (23/07/2014)" );
        }
      });
    }
    catch( InterruptedException ex ) {
      System.out.println( "InterruptedException: " + ex.getMessage() );
    }
    catch( InvocationTargetException ex ) {
      System.out.println( "InvocationTargetException: " );
      ex.printStackTrace();
    }
  }
  public void exec() {
    iniciar();
    processar();
  }
  private void iniciar() {
    this.transacaoConta = 'C';
    this.transacaoCaixa = 'C';
    this.transacaoLancamento = 'C';
    this.caixaDAO = new CaixaDAO();
    this.contaDAO = new ContaDAO();
    this.lancamentoDAO = new LancamentoDAO();
    this.caixaAtual = null;
    this.contaAtual = null;
    this.lancamentoAtual = null;
    this.frame.mudarEstado( "", "NAVEGACAO" );

    setarPeriodoHome();
    recarregarDoBancoDeDados( "Contas" );
    recarregarDoBancoDeDados( "Caixas" );
    recarregarDoBancoDeDados( "Lancamentos" );
  }
  private void setarPeriodoHome() {
    Date dataIni, dataFim;
    
    try {
      dataIni = DateTools.getPrimeiraDataMesAtual();
      dataFim = DateTools.getUltimaDataMesAtual();
    }
    catch( ParseException pe ) {
      System.out.println( "ParseException: " + pe.getLocalizedMessage() );
      dataIni = new Date();
      dataFim = new Date();
    }

    this.frame.setDtcPeriodoIni( dataIni );
    this.frame.setDtcPeriodoFim( dataFim );
    this.perDataIni = dataIni;
    this.perDataFim = dataFim;

  }
  private void processar() {
    do {
      this.frame.acessar();

      if( this.frame.getComandoTela().equals( "ALTERAR_CAIXA" ) ) {
        alterar( "Caixa" );
      }
      if( this.frame.getComandoTela().equals( "ALTERAR_CONTA" ) ) {
        alterar( "Conta" );
      }
      if( this.frame.getComandoTela().equals( "ALTERAR_LANCAMENTO" ) ) {
        alterar( "Lancamento" );
      }
      if( this.frame.getComandoTela().equals( "ALTERAR_TRANSFERENCIA" ) ) {
        alterar( "Transferencia" );
      }
      if( this.frame.getComandoTela().equals( "ATUALIZAR_PERIODO" ) ) {
        atualizarPeriodo();
      }
      if( this.frame.getComandoTela().equals( "CANCELAR_CAIXA" ) ) {
        cancelar( "Caixa" );
      }
      if( this.frame.getComandoTela().equals( "CANCELAR_CONTA" ) ) {
        cancelar( "Conta" );
      }
      if( this.frame.getComandoTela().equals( "CANCELAR_LANCAMENTO_SELECIONADO" ) ) {
        cancelar( "Lancamento" );
      }
      if( this.frame.getComandoTela().equals( "CANCELAR_TRANSFERENCIA" ) ) {
        cancelar( "Transferencia" );
      }
      if( this.frame.getComandoTela().equals( "CARREGAR_CAIXA_SELECIONADO" ) ) {
        carregarSelecao( "Caixa" );
      }
      if( this.frame.getComandoTela().equals( "CARREGAR_CONTA_SELECIONADA" ) ) {
        carregarSelecao( "Conta" );
      }
      if( this.frame.getComandoTela().equals( "CARREGAR_LANCAMENTO_SELECIONADO" ) ) {
        carregarSelecao( "Lancamento" );
      }
      if( this.frame.getComandoTela().equals( "CONFIRMAR_LANCAMENTO" ) ) {
        confirmar( "Lancamento" );
      }
      if( this.frame.getComandoTela().equals( "CONFIRMAR_CAIXA" ) ) {
        confirmar( "Caixa" );
      }
      if( this.frame.getComandoTela().equals( "CONFIRMAR_CONTA" ) ) {
        confirmar( "Conta" );
      }
      if( this.frame.getComandoTela().equals( "CONFIRMAR_TRANSFERENCIA" ) ) {
        confirmar( "Transferencia" );
      }
      if( this.frame.getComandoTela().equals( "EXCLUIR_CAIXA" ) ) {
        excluir( "Caixa" );
      }
      if( this.frame.getComandoTela().equals( "EXCLUIR_CONTA" ) ) {
        excluir( "Conta" );
      }
      if( this.frame.getComandoTela().equals( "EXCLUIR_LANCAMENTO" ) ) {
        excluir( "Lancamento" );
      }
      if( this.frame.getComandoTela().equals( "INCLUIR_CAIXA" ) ) {
        incluir( "Caixa" );
      }
      if( this.frame.getComandoTela().equals( "INCLUIR_CONTA" ) ) {
        incluir( "Conta" );
      }
      if( this.frame.getComandoTela().equals( "INCLUIR_TRANSFERENCIA" ) ) {
        incluir( "Transferencia" );
      }
      if( this.frame.getComandoTela().equals( "INCLUIR_LANCAMENTO" ) ) {
        incluir( "Lancamento" );
      }
      if( this.frame.getComandoTela().equals( "PERIODO_MENOS" ) ) {
        mudarPeriodo( '-' );
      }
      if( this.frame.getComandoTela().equals( "PERIODO_MAIS" ) ) {
        mudarPeriodo( '+' );
      }
      if( this.frame.getComandoTela().equals( "RESUMO_CAIXA" ) ) {
        solicitarResumoCaixa();
      }
      if( this.frame.getComandoTela().equals( "RESUMO_CONTA" ) ) {
        solicitarResumoConta();
      }
      if( this.frame.getComandoTela().equals( "SOLICITAR_EXTRATO" ) ) {
        solicitarExtrato();
      }
    }
    while( !this.frame.getComandoTela().equals( "SAIR" ) );
  }
  private void atualizarPeriodo() {
    if( this.frame.getDtcPeriodoIni() == null || this.frame.getDtcPeriodoFim() == null ) {
      return;
    }
    this.perDataIni = this.frame.getDtcPeriodoIni();
    this.perDataFim = this.frame.getDtcPeriodoFim();
    recarregarDoBancoDeDados("Lancamentos" );
  }
  private void confirmarIncluirConta() {
    if( !validarCamposConta() ) {
      return;
    }

    Conta conta = this.frame.getContaTela();

    this.contaDAO.inserir( conta );
    this.frame.limparCamposConta();
    this.frame.mudarEstado( "Conta", "NAVEGACAO" );
    recarregarDoBancoDeDados("Contas" );

    Mensagem.info( "Conta incluída com sucesso.", this.frame );
  }
  private void recarregarDoBancoDeDados( String pObjeto) {
    if( pObjeto.equals( "Contas" ) ) {
      ArrayList<Conta> contasList = this.contaDAO.selectListaTodosRegistros();
      this.frame.limparContasGeral( contasList );
      this.frame.addContaHomeTODOS();
    }
    else if( pObjeto.equals( "Caixas" ) ) {
      ArrayList<Caixa> caixasList = this.caixaDAO.selectListaTodosRegistros();
      this.frame.limparCaixasGeral( caixasList );
      this.frame.addCaixaHomeTODOS();
    }
    else if( pObjeto.equals( "Lancamentos" ) ) {
      ArrayList<Lancamento> lancamentosList = this.lancamentoDAO.selectListaRegistrosPeriodo(
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim )
      );

      this.frame.setLancamentos( lancamentosList );
    }
  }
  private void confirmarAlterarConta() {
    if( !validarCamposConta() ) {
      return;
    }
    
    if( !Mensagem.confirmacao( "Confirma alterar a conta?", this.frame ) ) {
      return;
    }

    this.contaAtual.setNome( this.frame.getTxfNomeConta() );
    this.contaDAO.alterar( this.contaAtual );
    recarregarDoBancoDeDados("Contas" );
    this.frame.mudarEstado( "Conta", "NAVEGACAO" );
    this.transacaoConta = 'C';
  }
  private boolean validarCamposConta() {
    boolean contaValida = true;
    String mensagemErro = "";

    if( frame.getTxfNomeConta().isEmpty() ) {
      contaValida = false;
      mensagemErro += ">> Nome inválido.\n";
    }

    if( frame.getCbxTipoConta() == null ) {
      contaValida = false;
      mensagemErro += ">> Tipo inválido.\n";
    }

    if( !contaValida ) {
      Mensagem.info( "Erro ao inserir conta:\n" + mensagemErro, this.frame );
    }

    return( contaValida );
  }
  private void confirmarIncluirLancamento() {
    if( !validarCamposLancamento() ) {
      return;
    }
    
    Lancamento lancamento = this.frame.getLancamentoTela();

    // tratar recorrencia
    if( this.frame.getMovRecorrencia() == 'S' ) {
      ArrayList<Integer> datasVencimento = DateTools.calcularVencimentos(
        lancamento.getDataVencimento(),
        this.frame.getItfNumVezes(),
        (this.frame.getItfNumPeriodo()*this.frame.getCbxPeriodo().getIDias())
      );
      
      for( Integer data : datasVencimento ) {
        Lancamento provisao = new Lancamento();

        provisao.setDataEmissao( lancamento.getDataEmissao() );
        provisao.setDataVencimento( data );

        if( lancamento.getDataQuitacao() > 0 ) {
          provisao.setDataQuitacao( data );
        }
        else {
          provisao.setDataQuitacao( 0 );
        }

        provisao.setDescricao( lancamento.getDescricao() );
        provisao.setValor( lancamento.getValor() );
        provisao.setConta( lancamento.getConta() );
        provisao.setCaixa( lancamento.getCaixa() );

        this.lancamentoDAO.inserir( provisao );

        if( provisao.getDataQuitacao() > 0 ) {
          if( lancamento.getConta().getTipo() == 'D' ) {
            this.caixaDAO.subtrairDoSaldo( lancamento.getCaixa().getCodCaixa(), lancamento.getValor() );
          }
          else {
            this.caixaDAO.adicionarAoSaldo( lancamento.getCaixa().getCodCaixa(), lancamento.getValor() );
          }
        }
      }
    }
    else {
      this.lancamentoDAO.inserir( lancamento );

      if( lancamento.getDataQuitacao() > 0 ) {
        if( lancamento.getConta().getTipo() == 'D' ) {
          this.caixaDAO.subtrairDoSaldo( lancamento.getCaixa().getCodCaixa(), lancamento.getValor() );
        }
        else {
          this.caixaDAO.adicionarAoSaldo( lancamento.getCaixa().getCodCaixa(), lancamento.getValor() );
        }
      }
    }

    this.frame.mudarEstado( "Lancamento", "NAVEGACAO" );
    recarregarDoBancoDeDados( "Lancamentos" );
    recarregarDoBancoDeDados( "Caixas" );

    Mensagem.info( "Lançamento incluído com sucesso!" , this.frame);
  }
  private boolean validarCamposLancamento() {
    boolean lancamentoValido = true;
    String msg = "";

    if( this.frame.getTxfMovDescricao().isEmpty() ) {
      lancamentoValido = false;
      msg += ">> Descrição inválida.\n";
    }

    if( this.frame.getDtcMovData() == null ) {
      lancamentoValido = false;
      msg += ">> Data inválida.\n";
    }

    if( this.frame.getDbfMovValor() == 0.0 ) {
      lancamentoValido = false;
      msg += ">> Valor inválido.\n";
    }

    if( this.frame.getCbxMovConta() == null ) {
      lancamentoValido = false;
      msg += ">> Conta inválida.\n";
    }

    if( this.frame.getCbxMovCaixa() == null ) {
      lancamentoValido = false;
      msg += ">> Caixa inválida.\n";
    }

    if( this.frame.getDtcMovData() != null  && this.frame.getCbxMovConta() != null && this.frame.getCbxMovCaixa() != null && this.transacaoLancamento == 'I' ) {
      Lancamento lanc = this.frame.getLancamentoTela();
      if( lanc.getCodLancamento() != 0 ) {
        Mensagem.info( "Já existe um lançamento para este vencimento, valor, conta e caixa.", this.frame );
        return( false );
      }
    }

    if( !lancamentoValido ) {
      Mensagem.info( "Erro ao inserir lançamento:\n" + msg, this.frame );
    }

    return( lancamentoValido );
  }
  private void confirmarIncluirCaixa() {
    if( !validarCamposCaixa() ) {
      return;
    }
    
    Caixa caixa = this.frame.getCaixaTela();

    this.caixaDAO.inserir( caixa );
    this.frame.limparCamposCaixa();
    this.frame.mudarEstado( "Caixa", "NAVEGACAO" );
    recarregarDoBancoDeDados("Caixas" );
    
    Mensagem.info( "Caixa incluído com sucesso.", this.frame );
  }
  private boolean validarCamposCaixa() {
    boolean caixaValido = true;
    String mensagemErro = "";

    if( this.frame.getTxfNomeCaixa().isEmpty() ) {
      caixaValido = false;
      mensagemErro += ">> Nome inválido.\n";
    }

    if( !caixaValido ) {
      Mensagem.info( "Erro ao inserir caixa:\n" + mensagemErro, this.frame );
    }

    return( caixaValido );
  }
  private void confirmarAlterarCaixa() {
    if( !validarCamposCaixa() ) {
      return;
    }
    
    if( !Mensagem.confirmacao( "Confirma alterar o caixa?", this.frame ) ) {
      return;
    }

    this.caixaAtual.setNome( this.frame.getTxfNomeCaixa() );
    this.caixaAtual.setValorLimite( this.frame.getDbfValorLimite() );
    //this.caixaAtual.setSaldo( this.frame.getDbfSaldoInicialCaixa() );
    
    this.caixaDAO.alterar( this.caixaAtual );
    recarregarDoBancoDeDados("Caixas" );
    this.frame.mudarEstado( "Caixa", "NAVEGACAO" );
    this.transacaoCaixa = 'C';
  }
  private void excluir( String pObjeto ) {
    if( pObjeto.equals( "Caixa" ) ) {
      if( this.caixaAtual == null ) {
        Mensagem.info( "Selecione um caixa para excluir.", this.frame );
        return;
      }
      
      if( this.caixaDAO.existeRegistro( this.caixaAtual ) ) {
        Mensagem.info( "Não é possível excluir.\nCaixa com movimentação.", this.frame );
        return;
      }

      if( !Mensagem.confirmacao( "Confirma exclusão do Caixa?", this.frame ) ) {
        return;
      }
      
      this.caixaDAO.excluir( this.caixaAtual );
      recarregarDoBancoDeDados("Caixas" );
      this.frame.limparCamposCaixa();
      this.transacaoCaixa = 'C';
      this.caixaAtual = null;
    }
    else if( pObjeto.equals( "Conta" ) ) {
      if( this.contaAtual == null ) {
        Mensagem.info( "Selecione uma conta para excluir.", this.frame );
        return;
      }
      
      if( this.contaDAO.existeRegistro( this.contaAtual ) ) {
        Mensagem.info( "Não é possível excluir.\nConta com movimentação.", this.frame );
        return;
      }

      if( !Mensagem.confirmacao( "Confirma exclusão da conta?", this.frame ) ) {
        return;
      }
      
      this.contaDAO.excluir( this.contaAtual );
      recarregarDoBancoDeDados("Contas" );
      this.frame.limparCamposConta();
      this.transacaoConta = 'C';
      this.contaAtual = null;
    }
    else if( pObjeto.equals( "Lancamento" ) ) {
      if( this.lancamentoAtual == null ) {
        Mensagem.info( "Selecione um lançamento para excluir.", this.frame );
        return;
      }

      if( !Mensagem.confirmacao( "Confirma exclusão do lançamento?", this.frame ) ) {
        return;
      }
      
      this.lancamentoDAO.excluir( this.lancamentoAtual );
      
      if( this.lancamentoAtual.getPago() == 'S' ) {
        if( this.lancamentoAtual.getConta().getTipo() == 'D' ) {
           this.caixaDAO.adicionarAoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), this.lancamentoAtual.getValor() );
        }
        else {
          this.caixaDAO.subtrairDoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), this.lancamentoAtual.getValor() );
        }
      }
      recarregarDoBancoDeDados( "Lancamentos" );
      recarregarDoBancoDeDados( "Caixas" );
      this.frame.limparCamposLancamento();
      this.transacaoLancamento = 'C';
      this.lancamentoAtual = null;
    }
    
    this.frame.mudarEstado( pObjeto, "NAVEGACAO" );
  }
  private void cancelar( String pObjeto ) {
    if( pObjeto.equals( "Caixa" ) ) {
      if( this.transacaoCaixa == 'I' ) {
        this.frame.limparCamposCaixa();
        this.caixaAtual = null;
      }
      else {
        this.frame.atualizarCampos( this.caixaAtual );
      }
      this.transacaoCaixa = 'C';
    }
    else if( pObjeto.equals( "Conta" ) ) {
      if( this.transacaoConta == 'I' ) {
        this.frame.limparCamposConta();
        this.contaAtual = null;
      }
      else {
        this.frame.atualizarCampos( this.contaAtual );
      }
      this.transacaoConta = 'C';
    }
    else if( pObjeto.equals( "Lancamento" ) ) {
      if( this.transacaoLancamento == 'I' ) {
        this.frame.limparCamposLancamento();
        this.lancamentoAtual = null;
      }
      else {
        this.frame.atualizarCampos( this.lancamentoAtual );
      }
      this.transacaoLancamento = 'C';
      this.frame.habilitarComponentesRecorrencia( true );
    }
    else if( pObjeto.equals( "Transferencia" ) ) {
      this.frame.limparCamposTransferencia();
    }
    
    this.frame.mudarEstado( pObjeto, "NAVEGACAO" );
    this.frame.lostFocus();
  }
  private void carregarSelecao( String pObjeto ) {
    if( pObjeto.equals( "Caixa" ) ) {
      Caixa caixa = this.frame.getCaixaSelecionado();

      this.caixaAtual = caixa;
      this.transacaoCaixa = 'A';
      this.frame.atualizarCampos( caixa );
    }
    else if( pObjeto.equals( "Conta" ) ) {
      Conta conta = this.frame.getContaSelecionada();

      this.contaAtual = conta;
      this.transacaoConta = 'A';
      this.frame.atualizarCampos( conta );
    }
    else if( pObjeto.equals( "Lancamento" ) ) {
      Lancamento lancamento = this.frame.getLancamentoSelecionado();
      if( this.transacaoLancamento != 'I' ) {
        this.lancamentoAtual = lancamento;
        this.transacaoLancamento = 'A';
      }
      
      this.frame.habilitarComponentesRecorrencia( this.transacaoLancamento == 'I' );
      this.frame.atualizarCampos( lancamento );
    }
  }
  private void solicitarResumoCaixa() {
    Caixa caixaSelecionado = this.frame.getCbxResCaixa();
    
    if( caixaSelecionado == null ) {
      return;
    }
    
    // saldo do caixa
    Double saldoSelecionado = this.caixaDAO.selectSaldo( caixaSelecionado.getCodCaixa() );
    this.frame.setTxfResValorSaldoCaixa( saldoSelecionado );
    
    if( caixaSelecionado.getLimite().equals( 'S' ) || caixaSelecionado.getNome().equals( "TODOS" ) ) {
      double limitesTotais = this.caixaDAO.selectLimite( caixaSelecionado.getCodCaixa() );
      this.frame.setTxfResSaldoCaixaLimite( saldoSelecionado + limitesTotais );
    }
    else {
      this.frame.setCaixaResumoSemLimite();
    }
    
    // total de entradas
    double totalEntradas = this.caixaDAO.selectTotalEntrada(
      caixaSelecionado.getCodCaixa(),
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoCaixa()
    );

    this.frame.setTxfResValTotEntrCaixa( totalEntradas );

    // total de saidas
    double totalSaidas = this.caixaDAO.selectTotalSaida(
      caixaSelecionado.getCodCaixa(),
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoCaixa()
    );
    
    this.frame.setTxfResValTotSaidaCaixa( totalSaidas );
    
    // tratar saldo provisionado
    /* se marcado provisao, obter os creditos e debitos provisionados */
    if( this.frame.getCkbResumoCaixa() ) {
      Double entradaProvisionada = this.caixaDAO.selectTotalEntradaProvisionada(
        caixaSelecionado.getCodCaixa(),
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim )
      );
      
      if( entradaProvisionada.equals( Double.NaN ) ) {
        entradaProvisionada = 0.0;
      }

      Double saidaProvisionada = this.caixaDAO.selectTotalSaidaProvisionada(
        caixaSelecionado.getCodCaixa(),
        DateTools.parseDateToInteger( this.perDataIni ),
        DateTools.parseDateToInteger( this.perDataFim )
      );
      
      if( saidaProvisionada.equals( Double.NaN ) ) {
        saidaProvisionada = 0.0;
      }

      saldoSelecionado = saldoSelecionado + entradaProvisionada - saidaProvisionada;
      this.frame.setTxfResValorSaldoCaixa( saldoSelecionado );
    }

    carregarGrafico();
  }
  private void solicitarResumoConta() {
    Conta contaSelecionada = this.frame.getCbxResConta();

    if( contaSelecionada == null ){
      return;
    }

    // total de saida/entrada
    double totalMovPeriodo = this.lancamentoDAO.selectTotalMovimentoPeriodo(
      contaSelecionada,
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoConta()
    );

    double totalContaPeriodo = this.contaDAO.selectTotalPeriodo(
      contaSelecionada,
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoConta()
    );

    // valor do periodo
    this.frame.setTxfResValorMovContaRs( totalContaPeriodo );
      
    // porcentagem do periodo
    if( totalMovPeriodo > 0 ) {
      BigDecimal porcentagem = new BigDecimal( totalContaPeriodo ).divide( new BigDecimal( totalMovPeriodo ), 4, BigDecimal.ROUND_HALF_UP );
      Double porcentagemFinal = porcentagem.doubleValue() * 100;

      this.frame.setTxfResValorMovContaPc( porcentagemFinal );
    }
    else {
      this.frame.setTxfResValorMovContaPc( 0.0 );
    }

    // total do ano
    String inicioAno = DateTools.getSAnoAtual() + "0101";
    String fimAno    = DateTools.getSAnoAtual() + "1231";

    int inicioAnoInt = Integer.parseInt( inicioAno );
    int fimAnoInt = Integer.parseInt( fimAno );

    double totalAnual = this.contaDAO.selectTotalPeriodo( contaSelecionada, inicioAnoInt, fimAnoInt, this.frame.getCkbResumoConta() );

    this.frame.setTxfResValorMovContaAno( totalAnual );
  }
  private void mudarPeriodo( char pOpcao ) {
    Date dataIni = DateTools.somarSubtrairUmMes( this.perDataIni, pOpcao );
    Date dataFim = DateTools.somarSubtrairUmMes( this.perDataFim, pOpcao );
    
    this.perDataIni = dataIni;
    this.perDataFim = dataFim;
    
    this.frame.setDtcPeriodoIni( dataIni );
    this.frame.setDtcPeriodoFim( dataFim );
    
    recarregarDoBancoDeDados( "Lancamentos" );
  }
  private boolean validarCamposExtrato() {
    boolean valido = true;
    String mensagem = "";

    if( this.frame.getCbxExtratoConta() == null ) {
      valido = false;
      mensagem += ">> Conta inválida.\n";
    }
    if( this.frame.getDtcExtratoIni() == null ) {
      valido = false;
      mensagem += ">> Data inicial inválida.\n";
    }
    if( this.frame.getDtcExtratoFim() == null ) {
      valido = false;
      mensagem += ">> Data final inválida.\n";
    }

    if( !valido ) {
      Mensagem.aviso( "Erro ao solicitar extrato:\n" + mensagem, this.frame );
    }

    return( valido );
  }
  private void solicitarExtrato() {
    if( !validarCamposExtrato() ) {
      return;
    }
    
    Conta conta = this.frame.getCbxExtratoConta();
    
    ArrayList<String[]> extratoList = this.contaDAO.selectListaExtratoPeriodo(
      conta,                                                         // conta
      DateTools.parseDateToInteger( this.frame.getDtcExtratoIni() ),  // data inicial
      DateTools.parseDateToInteger( this.frame.getDtcExtratoFim() ),  // data final
      this.frame.getCkbExtratoProvisao()                              // provisao
     );
    
    if( extratoList.isEmpty() ) {
      this.frame.setTxtExtrato( "Nenhum lançamento para o período." );
      return;
    }

    String dtEmissao  = "Data Vcto";
    String dtQuitacao = "Data Quitação";
    String descricao  = "Descrição";
    String valor      = "Valor";
    String caixa      = "Caixa";

    dtEmissao  = String.format( "%1$-" + 15 + "s", dtEmissao );
    dtQuitacao = String.format( "%1$-" + 15 + "s", dtQuitacao );
    descricao  = String.format( "%1$-" + 25 + "s", descricao );
    valor      = String.format( "%1$-" + 15 + "s", valor );
    caixa      = String.format( "%1$-" + 15 + "s", caixa );

    String cabecalho = dtEmissao + dtQuitacao + descricao + valor + caixa;

    this.frame.setTxtExtrato( cabecalho );

    Double totalGeral = new Double( 0.0 );

    for( String[] linha : extratoList ) {
      String dtEmi = String.format( "%1$-" + 15 + "s", linha[0] );
      String dtQui = String.format( "%1$-" + 15 + "s", linha[1] );
      String descr = String.format( "%1$-" + 25 + "s", linha[2] );
      String val   = String.format( "%1$-" + 15 + "s", linha[3] );
      String cai   = String.format( "%1$-" + 15 + "s", linha[4] );

      // trata descricao para ter no maximo 15 caracteres
      if( descr.length() > 25 ) {
        descr = descr.substring( 0, 24 ) + " ";
      }

      totalGeral += ValueTools.unformat( val );
      String linhaFinal = dtEmi + dtQui + descr + val + cai;
      this.frame.setTxtExtrato( this.frame.getTxtExtrato() + "\n" + linhaFinal );
    }
    
    // linha do total
    String divisor = "----------------------------------------------------------------------------------------";
    String ultimaLinha =
      String.format( "%1$-" + 15 + "s", " " ) +
      String.format( "%1$-" + 15 + "s", " " ) +
      String.format( "%1$-" + 15 + "s", " " ) +
      String.format( "%1$-" + 10 + "s", "TOTAL:" ) +
      //String.format( "%1$-" + 15 + "s", ValueTools.format( totalGeral ) );
      ValueTools.format( totalGeral );

    this.frame.setTxtExtrato( this.frame.getTxtExtrato() + "\n" + divisor + "\n" + ultimaLinha );
  }
  public void carregarGrafico() {
    Caixa caixa;
    
    if( this.frame.getCbxResCaixa() != null ) {
      caixa = this.frame.getCbxResCaixa();
    }
    else {
      this.frame.setGrafico( null );
      return;
    }
    
    DefaultPieDataset grafico = new DefaultPieDataset();
    
    ArrayList<Object[]> dadosGrafico = this.lancamentoDAO.selectLancamentosGrafico(
      caixa.getCodCaixa(),
      DateTools.parseDateToInteger( this.perDataIni ),
      DateTools.parseDateToInteger( this.perDataFim ),
      this.frame.getCkbResumoCaixa()
    );
    
    if( dadosGrafico.isEmpty() ) {
      this.frame.setGrafico( null );
      return;
    }
    
    for( Object[] item : dadosGrafico ) {
      String conta = (String) item[0];
      Double valor = (Double) item[1];
      
      grafico.setValue( conta, valor );
    }
    
    JFreeChart chart = ChartFactory.createPieChart( "" , grafico );
    
    chart.removeLegend();
    chart.removeSubtitle( null );
    
    this.frame.setGrafico( chart );
  }
  private void confirmarAlterarLancamento() {
    if( !validarCamposLancamento() ) {
      return;
    }
    
    double valorAntigo = this.lancamentoAtual.getValor();
    double valorNovo = this.frame.getDbfMovValor();
    
    // seta no objeto os campos que podem ser alterado
    this.lancamentoAtual.setDescricao( this.frame.getTxfMovDescricao() );
    this.lancamentoAtual.setValor( this.frame.getDbfMovValor() );

    if( this.lancamentoAtual.getDataQuitacao() > 0 && !this.frame.getCkbMovPago() ) {
      System.out.println( "Transformando lançamento em provisão." );
      // transformar em provisao
      this.lancamentoAtual.setPago( 'N' );
      this.lancamentoAtual.setDataQuitacao( 0 );
      
      // -> remover o valor do lancamento do caixa
      if( this.lancamentoAtual.getConta().getTipo() == 'D' ) {
        // somar no caixa
        this.caixaDAO.adicionarAoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), this.lancamentoAtual.getValor() );
      }
      else if( this.lancamentoAtual.getConta().getTipo() == 'C' ) {
        // debitar no caixa
        this.caixaDAO.subtrairDoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), this.lancamentoAtual.getValor() );
      }
    }
    else if( this.lancamentoAtual.getDataQuitacao() == 0 && this.frame.getCkbMovPago() ) {
      System.out.println( "Transformando lançamento provisionado em a vista." );
      // transformat em lancamento a vista
      this.lancamentoAtual.setPago( 'S' );
      this.lancamentoAtual.setDataQuitacao( DateTools.getDataAtual() );
      
      // -> adicionar o valor do lancamento ao caixa
      if( this.lancamentoAtual.getConta().getTipo() == 'D' )
      {
        // debitar no caixa
        this.caixaDAO.subtrairDoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), this.lancamentoAtual.getValor() );
      }
      else if( this.lancamentoAtual.getConta().getTipo() == 'C' )
      {
        // somar no caixa
        this.caixaDAO.adicionarAoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), this.lancamentoAtual.getValor() );
      }
    }
    else {
      if( this.frame.getCkbMovPago() ) {
        // apenas alterar o valor
        if( this.lancamentoAtual.getConta().getTipo() == 'C' ) {
          this.caixaDAO.subtrairDoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), valorAntigo );
          this.caixaDAO.adicionarAoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), valorNovo );
        }
        else {
          this.caixaDAO.adicionarAoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), valorAntigo );
          this.caixaDAO.subtrairDoSaldo( this.lancamentoAtual.getCaixa().getCodCaixa(), valorNovo );
        }
        this.lancamentoAtual.setValor( valorNovo );
      }
      else {
        // eh provisao, apenas alterar o valor do lancamento sem alterar o caixa
        this.lancamentoAtual.setValor( valorNovo );
      }
    }

    this.lancamentoDAO.alterar( this.lancamentoAtual );
    this.frame.mudarEstado( "Lancamento", "NAVEGACAO" );
    recarregarDoBancoDeDados( "Lancamentos" );
    recarregarDoBancoDeDados( "Caixas" );
    this.transacaoLancamento = 'C';
    this.frame.habilitarComponentesRecorrencia( true );
  }
  public static void main( String[] s ) {
    Main programa = new Main();
    programa.exec();
  }
  private void incluir( String pObjeto ) {
    if( pObjeto.equals( "Caixa" ) ) {
      this.transacaoCaixa = 'I';
      this.frame.limparCamposCaixa();
      this.frame.habilitarTipoCaixa( false );
      this.frame.focoCaixa();
    }
    else if( pObjeto.equals( "Conta" ) ) {
      this.transacaoConta = 'I';
      this.frame.limparCamposConta();
      this.frame.focoConta();
    }
    else if( pObjeto.equals( "Lancamento" ) ) {
      this.transacaoLancamento = 'I';
      this.frame.habilitarComponentesRecorrencia( true );
      this.frame.limparCamposLancamento();
      this.frame.focoLancamento();
    }
    else if( pObjeto.equals( "Transferencia" ) ) {
      this.frame.limparCamposTransferencia();
      this.frame.focoTransferencia();
    }
    
    this.frame.habilitarCampoAlteracao( pObjeto, true );
    this.frame.mudarEstado( pObjeto, "EDICAO" );
    this.frame.habilitarTipoCaixa( false );
  }
  private void confirmar( String pObjeto ) {
    if( pObjeto.equals( "Caixa" ) ) {
      if( this.transacaoCaixa == 'I' ) {
        confirmarIncluirCaixa();
      }
      else {
        confirmarAlterarCaixa();
      }
    }
    else if( pObjeto.equals( "Conta" ) ) {
      if( this.transacaoConta == 'I' ) {
        confirmarIncluirConta();
      }
      else {
        confirmarAlterarConta();
      }
    }
    else if( pObjeto.equals( "Lancamento" ) ) {
      if( this.transacaoLancamento == 'I' ) {
        confirmarIncluirLancamento();
      }
      else {
        confirmarAlterarLancamento();
      }
    }
    else if( pObjeto.equals( "Transferencia" ) ) {
      confirmarIncluirTransferencia();
    }
  }
  private void confirmarIncluirTransferencia() {
    if( !validarCamposTransferencia() ) {
      return;
    }
    
    Lancamento transf = this.frame.getTransferenciaTela();
    
    if( transf.getPago() == 'S' ) {
      // efetivar valores dos caixas
      this.caixaDAO.adicionarAoSaldo( this.frame.getCbxTransfCaixaDestino().getCodCaixa(), transf.getValor() );
      this.caixaDAO.subtrairDoSaldo( this.frame.getCbxTransfCaixaOrigem().getCodCaixa(), transf.getValor() );
    }
    
    this.lancamentoDAO.inserir( transf );
    
    this.frame.mudarEstado( "Transferencia", "NAVEGACAO" );
    recarregarDoBancoDeDados( "Lancamentos" );
    recarregarDoBancoDeDados( "Caixas" );
    
    Mensagem.info( "Transferência incluída com sucesso.", this.frame );
  }
  private boolean validarCamposTransferencia() {
    boolean ok = true;
    String mensagem = "";
    
    if( this.frame.getCbxTransfCaixaOrigem() == null ) {
      ok = false;
      mensagem += ">> Caixa de origem inválido.\n";
    }
    
    if( this.frame.getCbxTransfCaixaDestino() == null ) {
      ok = false;
      mensagem += ">> Caixa de destino inválido.\n";
    }
    
    if( this.frame.getCbxTransfCaixaOrigem() != null && this.frame.getCbxTransfCaixaDestino() != null ) {
      Caixa a = this.frame.getCbxTransfCaixaOrigem();
      Caixa b = this.frame.getCbxTransfCaixaDestino();
      
      if( a.equals( b ) ) {
        ok = false;
        mensagem += ">> Caixas de origem e destino não podem ser iguais.\n";
      }
    }
    
    if( this.frame.getTxfTransfDescricao().isEmpty() ) {
      ok = false;
      mensagem += ">> Descrição inválida.\n";
    }
    
    if( this.frame.getDtcTransfData() == null ) {
      ok = false;
      mensagem += ">> Data inválida.\n";
    }
    
    if( this.frame.getDbfTransfValor() == 0.0 ) {
      ok = false;
      mensagem += ">> Valor inválido.\n";
    }
    
    if( !ok ) {
      Mensagem.info( "Erro ao inserir transferência:\n" + mensagem, frame);
    }
    
    return( ok );
  }
  private void alterar( String pObjeto ) {
    if( pObjeto.equals( "Caixa" ) ) {
      if( this.caixaAtual == null ) {
        Mensagem.info( "Selecione um caixa para alterar.", this.frame );
        return;
      }

      this.transacaoCaixa = 'A';
      this.frame.mudarEstado( pObjeto, "EDICAO" );
      this.frame.habilitarCampoAlteracao( pObjeto, false );
      this.frame.habilitarTipoCaixa( this.caixaAtual.getLimite() == 'S' );
    }
    else if( pObjeto.equals( "Conta" ) ) {
      if( this.contaAtual == null ) {
        Mensagem.info( "Selecione uma conta para alterar.", this.frame );
        return;
      }
    
      this.transacaoConta = 'A';
      this.frame.mudarEstado( pObjeto, "EDICAO" );
      this.frame.habilitarCampoAlteracao( pObjeto, false );
    }
    else if( pObjeto.equals( "Lancamento" ) ) {
      if( this.lancamentoAtual == null ) {
        Mensagem.info( "Selecione um lançamento para editar.", this.frame );
        return;
      }
      
      if( this.lancamentoAtual.getConta() == null ) {
        // eh uma transferencia.. nao pode ser editada
        Mensagem.info( "Não é possível editar uma transferência.", this.frame );
        return;
      }

      this.transacaoLancamento = 'A';
      this.frame.habilitarComponentesRecorrencia( false );
      this.frame.mudarEstado( pObjeto, "EDICAO" );
      this.frame.habilitarCampoAlteracao( pObjeto, false );
    }
  }
}