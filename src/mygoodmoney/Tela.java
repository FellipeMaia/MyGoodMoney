/**
* @file Tela.java
* @brief Contém métodos de acesso e rotinas utilitárias da Tela Gráfica.
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Ricardo
 */
public class Tela extends JFrame
{
  private String comando;                          /**< Contém o comando a ser executado, de eventos */
  
  private JPanel pnlSuperior;
     private JLabel     lblPeriodo;
     private JButton    btnMenosPeriodo;
     private JDateChooser dtcPeriodoIni;
     private JLabel     lblPeriodoA;
     private JDateChooser dtcPeriodoFim;
     private JButton    btnMaisPeriodo;
  private JTabbedPane tbpPainelAbas;
     private JPanel     pnlHome;
        private JPanel     pnlResumoCaixa;
           private JLabel     lblResumoCaixa;
           private JLabel     lblResNomeCaixa;
           private JComboBox  cbxResCaixa;
           private JButton    btnResCaixa;
           private JLabel     lblResSaldoCaixa;
           private JLabel     lblResValorSaldoCaixa;
           private JLabel     lblResTotEntrCaixa;
           private JLabel     lblResValTotEntrCaixa;
           private JLabel     lblResTotSaidaCaixa;
           private JLabel     lblResValTotSaidaCaixa;
           private JCheckBox  ckbResumoCaixa;
           private ChartPanel     pnlGrafico;
        private JPanel     pnlResumoConta;
           private JLabel     lblResumoConta;
           private JLabel     lblResNomeConta;
           private JComboBox  cbxResConta;
           private JButton    btnResConta;
           private JCheckBox  ckbResumoConta;
           private JLabel     lblResMovContaPc;
           private JLabel     lblResValorMovContaPc;
           private JLabel     lblResMovContaRs;
           private JLabel     lblResValorMovContaRs;
           private JLabel     lblResMovContaAno;
           private JLabel     lblResValorMovContaAno;
     private JPanel     pnlMovimento;
        private JTabbedPane   tbpMovimento;
           private JPanel     pnlCadastroMov;
              private JLabel     lblMovDescricao;
              private JTextField txfMovDescricao;
              private JLabel     lblMovData;
              private JDateChooser dtcMovData;
              private JLabel     lblMovValor;
              private JDoubleField dbfMovValor;
              private JLabel     lblMovConta;
              private JComboBox  cbxMovConta;
              private JLabel     lblMovCaixa;
              private JComboBox  cbxMovCaixa;
              private JCheckBox  ckbMovPago;
              private JLabel     lblMovRecorrencia;
              private JRadioButton rbtMovRecorrenciaSim;
              private JRadioButton rbtMovRecorrenciaNao;
              private JLabel     lblRepetir;
              private JIntegerField itfNumVezes;
              private JLabel     lblVezes;
              private JLabel     lblACada;
              private JIntegerField itfNumPeriodo;
              private JComboBox  cbxPeriodo;
              private JButton    btnMovAdd;
              private JButton    btnMovEditar;
              private JButton    btnMovExcluir;
              private JButton    btnMovCancelar;
              private JButton    btnMovConfirmar;
           private JPanel     pnlTransferencia;
        private JPanel     pnlLancamentoMov;
           private JTable     movimentoTable;
           private DefaultTableModel movimentoModel;
     private JPanel     pnlExtratos;
        private JPanel     pnlExtrato;
           private JLabel     lblExtratoConta;
           private JComboBox  cbxExtratoConta;
           private JLabel     lblExtratoPeriodo;
           private JDateChooser dtcExtratoIni;
           private JLabel     lblExtratoHifen;
           private JDateChooser dtcExtratoFim;
           private JButton    btnExtrato;
           private JCheckBox  ckbExtratoProvisao;
           private JTextPane  txtExtrato;
           private JScrollPane scbExtratoConta;
     private JPanel     pnlCadastros;
        private JPanel     pnlCadConta;
           private JLabel     lblAddConta;
           private JLabel     lblNomeConta;
           private JTextField txfNomeConta;
           private JLabel     lblTipoConta;
           private JComboBox  cbxTipoConta;
           private JButton    btnAddConta;
           private JButton    btnEditarConta;
           private JButton    btnExcluirConta;
           private JButton    btnConfirmarConta;
           private JButton    btnCancelarConta;
           private JPanel     pnlContas;
           private JTable     contasTable;
           private DefaultTableModel contasModel;
        private JPanel     pnlCadCaixa;
           private JLabel     lblAddCaixa;
           private JLabel     lblNomeCaixa;
           private JTextField txfNomeCaixa;
           private JLabel     lblSaldoInicial;
           private JDoubleField dbfSaldoInicialCaixa;
           private JButton    btnAddCaixa;
           private JButton    btnEditarCaixa;
           private JButton    btnExcluirCaixa;
           private JButton    btnConfirmarCaixa;
           private JButton    btnCancelarCaixa;
           private JPanel     pnlCaixas;
           private JTable     caixasTable;
           private DefaultTableModel caixasModel;
     private JPanel     pnlConfig;
        private JPanel     pnlBD;
           private JLabel     lblBD;
           private JLabel     lblDiretorioBD;
           private JTextField txfDiretorioBD;
           private JButton    btnPesquisarDir;
           private JLabel     lblNomeBD;
           private JTextField txfNomeBD;
        private JPanel     pnlGerBD;
           private JLabel     lblGerBD;
           private JButton    btnCriarBD;
           private JButton    btnLimparBD;
           private JButton    btnExcluirBD;
     private JPanel     pnlSobre;
        private JPanel     pnlAutor;
           private JLabel     lblNomeAutor;
           private JLabel     lblEmailAutor;
           private JLabel     lblContatoAutor;
        private JPanel     pnlTexto;
  
  public Tela( String nomeParam )
  {
    super( nomeParam );

    try
    {
      SwingUtilities.invokeAndWait
      (
        new Runnable()
        {
          @Override
          public void run()
          {
            initComponents();
          }
        }
      );
    }
    catch (InterruptedException | InvocationTargetException e)
    {
      e.printStackTrace();
    }

    obterImagemPrograma();
    habilitarRecorrencia( false );
  }
  
  private void obterImagemPrograma()
  {
    try
    {
      setIconImage( new ImageIcon( Tela.class.getResource( "money.png" ) ).getImage() );
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
  }
  private void initComponents()
  {
    comando = "";
    
    this.getContentPane().setLayout( null );
    this.setSize( 640, 480 );
    this.setResizable( true );
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    this.setVisible( true );
    this.addComponentListener
    (
      new ComponentAdapter()
      {
        @Override
        public void componentResized( ComponentEvent e )
        {
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          pnlSuperior.setBounds(0, 0, getSize().width, 60);
          tbpPainelAbas.setBounds( 0, 60, getSize().width, (screenSize.height - 60) );
        }
      }
    );
    
    pnlSuperior = new JPanel();
    pnlSuperior.setBounds( new Rectangle( 0, 0, 640 , 60 ) );
    pnlSuperior.setName( "pnlSuperior" );
    pnlSuperior.setEnabled( true );
    pnlSuperior.setLayout( null );
    
    tbpPainelAbas = new JTabbedPane();
    tbpPainelAbas.setBounds( 0,60, 640, 420 );
    tbpPainelAbas.setName( "tbpPainelAbas" );
    tbpPainelAbas.setBorder( BorderFactory.createEtchedBorder() );
    tbpPainelAbas.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlHome = new JPanel();
    pnlHome.setLayout( null );
    pnlHome.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlHome.setName( "Home" );
    pnlHome.setBorder( BorderFactory.createEtchedBorder() );
    pnlHome.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblPeriodo = new JLabel( "Período:" );
    lblPeriodo.setBounds( 30, 20, 60, 21 );
    lblPeriodo.setHorizontalAlignment( JLabel.RIGHT );
    lblPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnMenosPeriodo = new JButton( "-" );
    btnMenosPeriodo.setBounds( 95, 20, 45, 21 );
    btnMenosPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    btnMenosPeriodo.setFocusable( false );
    btnMenosPeriodo.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMenosPeriodo();
        }
      }
    );
    
    dtcPeriodoIni = new JDateChooser();
    dtcPeriodoIni.setBounds( 150, 20, 130, 21 );
    dtcPeriodoIni.setFont( new Font( "Verdana", 0, 12 ) );
    dtcPeriodoIni.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARdtcPeriodoIni();
        }
      }
    );
    
    lblPeriodoA = new JLabel( "à" );
    lblPeriodoA.setBounds( 290, 20, 15, 21 );
    lblPeriodoA.setFont( new Font( "Verdana", 0, 12 ) );
    lblPeriodoA.setHorizontalAlignment( JLabel.CENTER );
    
    dtcPeriodoFim = new JDateChooser();
    dtcPeriodoFim.setBounds( 315, 20, 130, 21 );
    dtcPeriodoFim.setFont( new Font( "Verdana", 0, 12 ) );
    dtcPeriodoFim.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARdtcPeriodoFim();
        }
      }
    );
    
    btnMaisPeriodo = new JButton( "+" );
    btnMaisPeriodo.setBounds( 450, 20, 45, 21 );
    btnMaisPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    btnMaisPeriodo.setFocusable( false );
    btnMaisPeriodo.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMaisPeriodo();
        }
      }
    );
    
    pnlResumoCaixa = new JPanel();
    pnlResumoCaixa.setLayout( null );
    pnlResumoCaixa.setBounds( 10, 10, 610, 210 );
    pnlResumoCaixa.setBorder( BorderFactory.createEtchedBorder() );
    
    lblResumoCaixa = new JLabel( "Resumo Caixa (no Período)" );
    lblResumoCaixa.setBounds( 215, 5, 180, 21 );
    lblResumoCaixa.setHorizontalAlignment( JLabel.CENTER );
    lblResumoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResNomeCaixa = new JLabel( "Caixa:" );
    lblResNomeCaixa.setBounds( 10, 35, 120, 21 );
    lblResNomeCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    cbxResCaixa = new JComboBox();
    cbxResCaixa.setBounds( 135, 35, 120, 21 );
    cbxResCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnResCaixa = new JButton();
    btnResCaixa.setIcon( new ImageIcon( Tela.class.getResource( "atualizar.png" ) ) );
    btnResCaixa.setFocusable( false );
    btnResCaixa.setContentAreaFilled( true );
    btnResCaixa.setFocusPainted( true );
    btnResCaixa.setBounds( 260, 35, 21, 21 );
    btnResCaixa.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnResCaixa();
        }
      }
    );

    lblResSaldoCaixa = new JLabel( "Saldo: " );
    lblResSaldoCaixa.setBounds( 80, 66, 50, 21 ); // x = 130
    lblResSaldoCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResSaldoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValorSaldoCaixa = new JLabel( "R$ 0,00" );
    lblResValorSaldoCaixa.setBounds( 145, 66, 100, 21 );
    lblResValorSaldoCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResValorSaldoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResTotEntrCaixa = new JLabel( "Total de Entradas: " );
    lblResTotEntrCaixa.setBounds( 0, 97, 130, 21 ); // x = 130
    lblResTotEntrCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResTotEntrCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValTotEntrCaixa = new JLabel( "R$ 0,00" );
    lblResValTotEntrCaixa.setBounds( 145, 97, 100, 21 );
    lblResValTotEntrCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResValTotEntrCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResTotSaidaCaixa = new JLabel( "Total de Saídas: " );
    lblResTotSaidaCaixa.setBounds( 10, 128, 120, 21 ); // x = 130
    lblResTotSaidaCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResTotSaidaCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValTotSaidaCaixa = new JLabel( "R$ 0,00" );
    lblResValTotSaidaCaixa.setBounds( 145, 128, 100, 21 );
    lblResValTotSaidaCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResValTotSaidaCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    ckbResumoCaixa = new JCheckBox( " Considerar provisões" );
    ckbResumoCaixa.setBounds( 105, 159, 170, 21 );
    ckbResumoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlGrafico = new ChartPanel( null );
    pnlGrafico.setLayout( null );
    pnlGrafico.setBounds( 320, 35, 260, 160 );
    //pnlGrafico.setBorder( BorderFactory.createEtchedBorder() );
    
    pnlResumoConta = new JPanel();
    pnlResumoConta.setLayout( null );
    pnlResumoConta.setBounds( 10, 230, 610, 120 );
    pnlResumoConta.setBorder( BorderFactory.createEtchedBorder() );
    
    lblResumoConta = new JLabel( "Resumo Conta (no Período)" );
    lblResumoConta.setBounds( 215, 5, 180, 21 );
    lblResumoConta.setHorizontalAlignment( JLabel.CENTER );
    lblResumoConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResNomeConta = new JLabel( "Conta:" );
    lblResNomeConta.setBounds( 30, 45, 50, 21 );
    lblResNomeConta.setHorizontalAlignment( JLabel.RIGHT );
    lblResNomeConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    cbxResConta = new JComboBox();
    cbxResConta.setBounds( 85, 45, 180, 21 );
    cbxResConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnResConta = new JButton( new ImageIcon( Tela.class.getResource( "atualizar.png" ) ) );
    btnResConta.setBounds( 270, 45, 21, 21 );
    btnResConta.setFocusable( false );
    btnResConta.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnResConta();
        }
      }
    );
    
    ckbResumoConta = new JCheckBox( " Considerar provisões" );
    ckbResumoConta.setBounds( 85, 76, 200, 21 );
    ckbResumoConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResMovContaPc = new JLabel( "Movimentou (%):" );
    lblResMovContaPc.setBounds( 330, 25, 120, 21 ); // x = 450
    lblResMovContaPc.setHorizontalAlignment( JLabel.RIGHT );
    lblResMovContaPc.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValorMovContaPc = new JLabel( "0,00 %" );
    lblResValorMovContaPc.setBounds( 455, 25, 100, 21 );
    lblResValorMovContaPc.setHorizontalAlignment( JLabel.RIGHT );
    lblResValorMovContaPc.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResMovContaRs = new JLabel( "Movimentou (R$):" );
    lblResMovContaRs.setBounds( 330, 55, 120, 21 ); // x = 450
    lblResMovContaRs.setHorizontalAlignment( JLabel.RIGHT );
    lblResMovContaRs.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValorMovContaRs = new JLabel( "R$ 0,00" );
    lblResValorMovContaRs.setBounds( 455, 55, 100, 21 );
    lblResValorMovContaRs.setHorizontalAlignment( JLabel.RIGHT );
    lblResValorMovContaRs.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResMovContaAno = new JLabel( "Movimentou no ano:" );
    lblResMovContaAno.setBounds( 300, 85, 150, 21 ); // x = 450
    lblResMovContaAno.setHorizontalAlignment( JLabel.RIGHT );
    lblResMovContaAno.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValorMovContaAno = new JLabel( "R$ 0,00" );
    lblResValorMovContaAno.setBounds( 455, 85, 100, 21 );
    lblResValorMovContaAno.setHorizontalAlignment( JLabel.RIGHT );
    lblResValorMovContaAno.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlMovimento = new JPanel();
    pnlMovimento.setLayout( null );
    pnlMovimento.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlMovimento.setName( "Lançamentos" );
    pnlMovimento.setBorder( BorderFactory.createEtchedBorder() );
    pnlMovimento.setFont( new Font( "Verdana", 0, 12 ) );
    
    tbpMovimento = new JTabbedPane();
    tbpMovimento.setBorder( BorderFactory.createEtchedBorder() );
    tbpMovimento.setBounds( 10, 10, 260, 340 );
    tbpMovimento.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlCadastroMov = new JPanel();
    pnlCadastroMov.setLayout( null );
    pnlCadastroMov.setBounds( 10, 10, 260, 340 );
    pnlCadastroMov.setBorder( BorderFactory.createEtchedBorder() );
    pnlCadastroMov.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblMovDescricao = new JLabel( "Descrição:" );
    lblMovDescricao.setBounds( 15, 40, 70, 21 );
    lblMovDescricao.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovDescricao.setHorizontalAlignment( JLabel.RIGHT );
    
    txfMovDescricao = new JTextField( "" );
    txfMovDescricao.setBounds( 90, 40, 150, 21 );
    txfMovDescricao.setFont( new Font( "Verdana", 0, 12 ) );
    txfMovDescricao.setToolTipText( "Descrição do lançamento" );
    
    lblMovData = new JLabel( "Data:" );
    lblMovData.setBounds( 45, 71, 40, 21 );
    lblMovData.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovData.setHorizontalAlignment( JLabel.RIGHT );
    
    dtcMovData = new JDateChooser();
    dtcMovData.setDate( new Date() );
    dtcMovData.setBounds( 90, 71, 100, 21 );
    dtcMovData.setFont( new Font( "Verdana", 0, 12 ) );
    dtcMovData.setToolTipText( "Data do lançameneto" );
    
    lblMovValor = new JLabel( "Valor:" );
    lblMovValor.setBounds( 35, 102, 50, 21 );
    lblMovValor.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovValor.setHorizontalAlignment( JLabel.RIGHT );
    
    dbfMovValor = new JDoubleField();
    dbfMovValor.setBounds( 90, 102, 80, 21 );
    dbfMovValor.setFont( new Font( "Verdana", 0, 12 ) );
    dbfMovValor.setToolTipText( "Valor do lançamento" );
    
    lblMovConta = new JLabel( "Conta:" );
    lblMovConta.setBounds( 35, 133, 50, 21 );
    lblMovConta.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovConta.setHorizontalAlignment( JLabel.RIGHT );
    
    cbxMovConta = new JComboBox();
    cbxMovConta.setBounds( 90, 133, 150, 21 );
    cbxMovConta.setFont( new Font( "Verdana", 0, 12 ) );
    cbxMovConta.setToolTipText( "Escolha uma conta" );
    
    lblMovCaixa = new JLabel( "Caixa:" );
    lblMovCaixa.setBounds( 35, 164, 50, 21 );
    lblMovCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovCaixa.setHorizontalAlignment( JLabel.RIGHT );
    
    cbxMovCaixa = new JComboBox();
    cbxMovCaixa.setBounds( 90, 164, 150, 21 );
    cbxMovCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    cbxMovCaixa.setToolTipText( "Escolha um caixa" );
    cbxMovCaixa.addKeyListener(
      new KeyAdapter()
      {
        @Override
        public void keyPressed( KeyEvent e )
        {
          AOPRESSIONARcbxMovCaixa( e );
        }
      }
    );
    
    ckbMovPago = new JCheckBox( "Pago/Recebido" );
    ckbMovPago.setSelected( false );
    ckbMovPago.setBounds( 86, 192, 130, 21 );
    ckbMovPago.setFont( new Font( "Verdana", 0, 12 ) );
    ckbMovPago.setToolTipText( "Deixe desmarcado para um lançamento futuro." );
    
    lblMovRecorrencia = new JLabel( "Recorrência:" );
    lblMovRecorrencia.setBounds( 5, 216, 80, 21 );
    lblMovRecorrencia.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovRecorrencia.setHorizontalAlignment( JLabel.RIGHT );
    
    rbtMovRecorrenciaSim = new JRadioButton( "Sim" );
    rbtMovRecorrenciaSim.setSelected( false );
    rbtMovRecorrenciaSim.setBounds( 86, 216, 50, 21 );
    rbtMovRecorrenciaSim.setFont( new Font( "Verdana", 0, 12 ) );
    rbtMovRecorrenciaSim.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          if( rbtMovRecorrenciaSim.isSelected() )
            habilitarRecorrencia( rbtMovRecorrenciaSim.isSelected() );
        }
      }
    );
    
    rbtMovRecorrenciaNao = new JRadioButton( "Não" );
    rbtMovRecorrenciaNao.setSelected( true );
    rbtMovRecorrenciaNao.setBounds( 140, 216, 50, 21 );
    rbtMovRecorrenciaNao.setFont( new Font( "Verdana", 0, 12 ) );
    rbtMovRecorrenciaNao.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          if( rbtMovRecorrenciaNao.isSelected() )
            habilitarRecorrencia( !rbtMovRecorrenciaNao.isSelected() );
        }
      }
    );
    
    ButtonGroup grupoMov = new ButtonGroup();
    grupoMov.add( rbtMovRecorrenciaSim );
    grupoMov.add( rbtMovRecorrenciaNao );
    
    lblRepetir = new JLabel( "Repetir" );
    lblRepetir.setBounds( 10, 247, 50, 21 );
    lblRepetir.setFont( new Font( "Verdana", 0, 12 ) );
    lblRepetir.setHorizontalAlignment( JLabel.RIGHT );
    
    itfNumVezes = new JIntegerField();
    itfNumVezes.setBounds( 65, 247, 40, 21 );
    itfNumVezes.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblVezes = new JLabel( "vezes" );
    lblVezes.setBounds( 110, 247, 40, 21 );
    lblVezes.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblACada = new JLabel( "A cada");
    lblACada.setBounds( 10, 278, 50, 21 );
    lblACada.setFont( new Font( "Verdana", 0, 12 ) );
    lblACada.setHorizontalAlignment( JLabel.CENTER );
    
    itfNumPeriodo = new JIntegerField();
    itfNumPeriodo.setBounds( 65, 278, 40, 21 );
    itfNumPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    
    cbxPeriodo = new JComboBox();
    cbxPeriodo.setBounds( 110, 278, 110, 21 );
    cbxPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    for( EnumPeriodo periodo : EnumPeriodo.values() )
      cbxPeriodo.addItem( periodo );
    

    btnMovAdd = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
    btnMovAdd.setBounds( 5, 5, 21, 21 );
    btnMovAdd.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovAdd.setToolTipText( "Novo" );
    btnMovAdd.setFocusable( false );
    btnMovAdd.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMovAdd();
        }
      }
    );

    btnMovEditar = new JButton( new ImageIcon( Tela.class.getResource( "editar.png" ) ) );
    btnMovEditar.setBounds( 26, 5, 21, 21 );
    btnMovEditar.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovEditar.setToolTipText( "Editar" );
    btnMovEditar.setFocusable( false );
    btnMovEditar.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMovEditar();
        }
      }
    );

    btnMovExcluir = new JButton( new ImageIcon( Tela.class.getResource( "deletar.gif" ) ) );
    btnMovExcluir.setBounds( 47, 5, 21, 21 );
    btnMovExcluir.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovExcluir.setToolTipText( "Excluir" );
    btnMovExcluir.setFocusable( false );
    btnMovExcluir.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMovExcluir();
        }
      }
    );
    
    btnMovConfirmar = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
    btnMovConfirmar.setBounds( 68, 5, 21, 21 );
    btnMovConfirmar.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovConfirmar.setToolTipText( "Confirmar" );
    btnMovConfirmar.setFocusable( false );
    btnMovConfirmar.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMovConfirmar();
        }
      }
    );

    btnMovCancelar = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
    btnMovCancelar.setBounds( 89, 5, 21, 21 );
    btnMovCancelar.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovCancelar.setToolTipText( "Cancelar" );
    btnMovCancelar.setFocusable( false );
    btnMovCancelar.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnMovCancelar();
        }
      }
    );
    
    pnlTransferencia = new JPanel();
    pnlTransferencia.setLayout( null );
    
    pnlLancamentoMov = new JPanel();
    pnlLancamentoMov.setLayout( null );
    pnlLancamentoMov.setBounds( 280, 10, 340, 340 );
    pnlLancamentoMov.setBorder( BorderFactory.createEtchedBorder() );
    pnlLancamentoMov.setFont( new Font( "Verdana", 0, 12 ) );
    
    // movimento model inicio
    String[] colunasMov = { "Data", "Tipo", "Descrição", "Valor", "Pg/Rc" };
    Object[][] dadosMov = {};
    
    movimentoModel = new DefaultTableModel(dadosMov, colunasMov);
    movimentoTable = new JTable(movimentoModel)
    {
      @Override
      public boolean isCellEditable(int rowIndex, int colIndex)
      {
        return( false ); //Disallow the editing of any cell
      }
    };
    movimentoTable.addMouseListener
    (
      new MouseAdapter()
      {
        @Override
        public void mouseClicked( MouseEvent e )
        {
          AOCLICARpnlMovimento( e );
        }
      }
    );
    movimentoTable.getColumnModel().getColumn(0).setPreferredWidth( 70 );  // data
    movimentoTable.getColumnModel().getColumn(1).setPreferredWidth( 30 );  // tipo
    movimentoTable.getColumnModel().getColumn(2).setPreferredWidth( 80 ); // descricao
    movimentoTable.getColumnModel().getColumn(3).setPreferredWidth( 70 );  // valor
    movimentoTable.getColumnModel().getColumn(4).setPreferredWidth( 30 );  // pago/recebido
    
    pnlLancamentoMov.setLayout( new BorderLayout() );
    pnlLancamentoMov.add( new JScrollPane( movimentoTable ) );
    
    movimentoTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    // alinhar a direita um JTable - http://stackoverflow.com/questions/3467052/how-to-set-right-alignment-in-jtable-cell
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
    movimentoTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    movimentoTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    movimentoTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
    movimentoTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
    // movimento model fim
    
    pnlExtratos = new JPanel();
    pnlExtratos.setLayout( null );
    pnlExtratos.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlExtratos.setName( "Extratos" );
    pnlExtratos.setBorder( BorderFactory.createEtchedBorder() );
    pnlExtratos.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlExtrato = new JPanel();
    pnlExtrato.setLayout( null );
    pnlExtrato.setBounds( new Rectangle( 10, 10, 610, 340 ) );
    pnlExtrato.setBorder( BorderFactory.createEtchedBorder() );
    pnlExtrato.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblExtratoConta = new JLabel( "Conta:" );
    lblExtratoConta.setBounds( 10, 15, 45, 21 );
    lblExtratoConta.setFont( new Font( "Verdana", 0, 12 ) );
    lblExtratoConta.setHorizontalAlignment( JLabel.RIGHT );
    
    cbxExtratoConta = new JComboBox();
    cbxExtratoConta.setBounds( 60, 15, cbxMovConta.getWidth(), 21 );
    cbxExtratoConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblExtratoPeriodo = new JLabel( "Período: " );
    lblExtratoPeriodo.setBounds( 220, 15, 60, 21 );
    lblExtratoPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    lblExtratoPeriodo.setHorizontalAlignment( JLabel.RIGHT );
    
    dtcExtratoIni = new JDateChooser();
    dtcExtratoIni.setBounds( 290, 15, 110, 21 );
    dtcExtratoIni.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblExtratoHifen = new JLabel( "-" );
    lblExtratoHifen.setBounds( 410, 15, 15, 21 );
    lblExtratoHifen.setFont( new Font( "Verdana", 0, 12 ) );
    lblExtratoHifen.setHorizontalAlignment( JLabel.CENTER );
    
    dtcExtratoFim = new JDateChooser();
    dtcExtratoFim.setBounds( 435, 15, 110, 21 );
    dtcExtratoFim.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnExtrato = new JButton( new ImageIcon( Tela.class.getResource( "atualizar.png" ) ) );
    btnExtrato.setBounds( 560, 15, 21, 21 );
    btnExtrato.setFocusable( false );
    btnExtrato.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnExtrato();
        }
      }
    );
    
    ckbExtratoProvisao = new JCheckBox( " Considerar provisões" );
    ckbExtratoProvisao.setBounds( 220, 42, 200, 21 );
    ckbExtratoProvisao.setFont( new Font( "Verdana", 0, 12 ) );
    
    txtExtrato = new JTextPane();
    txtExtrato.setFont( new Font( "Monospaced", 0, 12 ) );
    txtExtrato.setEditable( false );
    txtExtrato.setBackground( pnlSuperior.getBackground() );
    
    scbExtratoConta = new JScrollPane( txtExtrato );
    scbExtratoConta.setBounds( 30, 70, 540, 230 );
    
    pnlCadastros = new JPanel();
    pnlCadastros.setLayout( null );
    pnlCadastros.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlCadastros.setName( "Cadastros" );
    pnlCadastros.setBorder( BorderFactory.createEtchedBorder() );
    pnlCadastros.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlCadConta = new JPanel();
    pnlCadConta.setLayout( null );
    pnlCadConta.setBounds( new Rectangle( 20, 20, pnlCadastros.getWidth()-50, (pnlCadastros.getHeight()/2)-70 ) );
    pnlCadConta.setName( "pnlCadConta" );
    pnlCadConta.setBorder( BorderFactory.createEtchedBorder() );
    pnlCadConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblAddConta = new JLabel( "Contas Cadastradas:" );
    lblAddConta.setBounds( 300, 5, 150, 21 );
    lblAddConta.setName( "lblAddConta" );
    lblAddConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblNomeConta = new JLabel( "Nome:" );
    lblNomeConta.setBounds( 10, 40, 45, 21 );
    lblNomeConta.setFont( new Font( "Verdana", 0, 12 ) );
    lblNomeConta.setHorizontalAlignment( JLabel.RIGHT );
    
    txfNomeConta = new JTextField();
    txfNomeConta.setBounds( 60, 40, 150, 21 );
    txfNomeConta.setName( "txfNomeConta" );
    txfNomeConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblTipoConta = new JLabel( "Tipo:" );
    lblTipoConta.setBounds( 10, 70, 45, 21 );
    lblTipoConta.setName( "lblTipoConta" );
    lblTipoConta.setFont( new Font( "Verdana", 0, 12 ) );
    lblTipoConta.setHorizontalAlignment( JLabel.RIGHT );
    
    cbxTipoConta = new JComboBox();
    cbxTipoConta.setBounds( 60, 70, 100, 21 );
    cbxTipoConta.setFont( new Font( "Verdana", 0, 12 ) );
    for( EnumTipoConta tipo : EnumTipoConta.values() )
      cbxTipoConta.addItem( tipo );
    

    btnAddConta = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
    btnAddConta.setBounds( 5, 5, 21, 21 );
    btnAddConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnAddConta.setToolTipText( "Incluir conta" );
    btnAddConta.setFocusable( false );
    btnAddConta.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnAddConta();
        }
      }
    );
    btnAddConta.addKeyListener
    (
      new KeyAdapter()
      {
        @Override
        public void keyPressed( KeyEvent e )
        {
          if( e.getKeyCode() == KeyEvent.VK_ENTER )
          {
            AOPRESSIONARbtnAddConta( e );
          }
        }
      }
    );
    
    btnEditarConta = new JButton( new ImageIcon( Tela.class.getResource( "editar.png" ) ) );
    btnEditarConta.setBounds( 26, 5, 21, 21 );
    btnEditarConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnEditarConta.setToolTipText( "Editar conta" );
    btnEditarConta.setFocusable( false );
    btnEditarConta.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnEditarConta();
        }
      }
    );
    
    btnExcluirConta = new JButton( new ImageIcon( Tela.class.getResource( "deletar.gif" ) ) );
    btnExcluirConta.setBounds( 47, 5, 21, 21 );
    btnExcluirConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnExcluirConta.setToolTipText( "Excluir conta" );
    btnExcluirConta.setFocusable( false );
    btnExcluirConta.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnExcluirConta();
        }
      }
    );
    
    btnConfirmarConta = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
    btnConfirmarConta.setBounds( 68, 5, 21, 21 );
    btnConfirmarConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnConfirmarConta.setToolTipText( "Confirmar" );
    btnConfirmarConta.setFocusable( false );
    btnConfirmarConta.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnConfirmarConta();
        }
      }
    );
    
    btnCancelarConta = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
    btnCancelarConta.setBounds( 89, 5, 21, 21 );
    btnCancelarConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnCancelarConta.setToolTipText( "Cancelar" );
    btnCancelarConta.setFocusable( false );
    btnCancelarConta.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnCancelarConta();
        }
      }
    );
    
    pnlContas = new JPanel();
    pnlContas.setBounds( 300, 30, 270, 100 );
    pnlContas.setBorder( BorderFactory.createEtchedBorder() );
    
    // contas model inicio
    String[] colunas = {"Nome","Tipo"};
    Object[][] dados = {};
    
    contasModel = new DefaultTableModel(dados, colunas);
    contasTable = new JTable(contasModel)
    {
      @Override
      public boolean isCellEditable(int rowIndex, int colIndex)
      {
        return( false ); //Disallow the editing of any cell
      }
    };
    contasTable.addMouseListener
    (
      new MouseAdapter()
      {
        @Override
        public void mouseClicked( MouseEvent e )
        {
          AOCLICARpnlContas( e );
        }
      }
    );
    // Fonte: http://www.java-forums.org/awt-swing/541-how-change-color-jtable-row-having-particular-value.html
    contasTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
    {
      @Override
      public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
        final Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        
        String tipoConta = contasTable.getModel().getValueAt( row, 1 ).toString();
        
        if( tipoConta.equals( "Débito" ) )
            c.setForeground(new Color(255,69,0) );
        else
            c.setForeground( Color.BLUE );

        return( c );
      }
    });
    
    pnlContas.setLayout( new BorderLayout() );
    pnlContas.add( new JScrollPane( contasTable ) );
    
    contasTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    // contas model fim
    
    pnlCadCaixa = new JPanel();
    pnlCadCaixa.setLayout( null );
    pnlCadCaixa.setBounds( new Rectangle( 20, pnlCadConta.getHeight()+40, pnlCadastros.getWidth()-50, (pnlCadastros.getHeight()/2)-50 ) );
    pnlCadCaixa.setName( "pnlCadCaixa" );
    pnlCadCaixa.setBorder( BorderFactory.createEtchedBorder() );
    pnlCadCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblAddCaixa = new JLabel( "Caixas cadastrados:" );
    lblAddCaixa.setBounds( 300, 5, 150, 21 );
    lblAddCaixa.setName( "lblAddCaixa" );
    lblAddCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblNomeCaixa = new JLabel( "Nome:" );
    lblNomeCaixa.setBounds( 10, 40, 45, 21 );
    lblNomeCaixa.setName( "lblNomeCaixa" );
    lblNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    lblNomeCaixa.setHorizontalAlignment( JLabel.RIGHT );
    
    txfNomeCaixa = new JTextField();
    txfNomeCaixa.setBounds( 60, 40, 150, 21 );
    txfNomeCaixa.setName( "txfNomeCaixa" );
    txfNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblSaldoInicial = new JLabel( "Saldo:" );
    lblSaldoInicial.setBounds( 10, 70, 45, 21 );
    lblSaldoInicial.setName( "lblSaldoInicial" );
    lblSaldoInicial.setFont( new Font( "Verdana", 0, 12 ) );
    lblSaldoInicial.setHorizontalAlignment( JLabel.RIGHT );
    
    dbfSaldoInicialCaixa = new JDoubleField();
    dbfSaldoInicialCaixa.setBounds( 60, 70, 100, 21 );
    dbfSaldoInicialCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnAddCaixa = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
    btnAddCaixa.setBounds( 5, 5, 21, 21 );
    btnAddCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnAddCaixa.setToolTipText( "Incluir caixa" );
    btnAddCaixa.setFocusable( false );
    btnAddCaixa.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnAddCaixa();
        }
      }
    );
    btnAddCaixa.addKeyListener
    (
      new KeyAdapter()
      {
        @Override
        public void keyPressed( KeyEvent e )
        {
          AOCLICARbtnAddCaixa();
        }
      }
    );
    
    btnEditarCaixa = new JButton( new ImageIcon( Tela.class.getResource( "editar.png" ) ) );
    btnEditarCaixa.setBounds( 26, 5, 21, 21 );
    btnEditarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnEditarCaixa.setToolTipText( "Alterar caixa" );
    btnEditarCaixa.setFocusable( false );
    btnEditarCaixa.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnEditarCaixa();
        }
      }
    );
    
    btnExcluirCaixa = new JButton( new ImageIcon( Tela.class.getResource( "deletar.gif" ) ) );
    btnExcluirCaixa.setBounds( 47, 5, 21, 21 );
    btnExcluirCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnExcluirCaixa.setToolTipText( "Excluir caixa" );
    btnExcluirCaixa.setFocusable( false );
    btnExcluirCaixa.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnExcluirCaixa();
        }
      }
    );
    
    btnConfirmarCaixa = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
    btnConfirmarCaixa.setBounds( 68, 5, 21, 21 );
    btnConfirmarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnConfirmarCaixa.setToolTipText( "Confirmar" );
    btnConfirmarCaixa.setFocusable( false );
    btnConfirmarCaixa.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnConfirmarCaixa();
        }
      }
    );
    
    btnCancelarCaixa = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
    btnCancelarCaixa.setBounds( 89, 5, 21, 21 );
    btnCancelarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnCancelarCaixa.setToolTipText( "Cancelar" );
    btnCancelarCaixa.setFocusable( false );
    btnCancelarCaixa.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnCancelarCaixa();
        }
      }
    );
    
    pnlCaixas = new JPanel();
    pnlCaixas.setBounds( 300, 30, 270, 100 );
    pnlCaixas.setBorder( BorderFactory.createEtchedBorder() );
    
    // caixas model inicio
    String[] colunasCaixas = { "Nome", "Saldo" };
    Object[][] dadosCaixas = {};
    
    caixasModel = new DefaultTableModel( dadosCaixas, colunasCaixas );
    caixasTable = new JTable( caixasModel )
    {
      @Override
      public boolean isCellEditable(int rowIndex, int colIndex)
      {
        return( false ); //Disallow the editing of any cell
      }
    };
    caixasTable.addMouseListener
    (
      new MouseAdapter()
      {
        @Override
        public void mouseClicked( MouseEvent e )
        {
          AOCLICARpnlCaixas( e );
        }
      }
    );
    
    pnlCaixas.setLayout( new BorderLayout() );
    pnlCaixas.add( new JScrollPane( caixasTable ) );
    
    caixasTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    // alinhar a direita um JTable - http://stackoverflow.com/questions/3467052/how-to-set-right-alignment-in-jtable-cell
    caixasTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    // caixas model fim
    
    pnlConfig = new JPanel();
    pnlConfig.setLayout( null );
    pnlConfig.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlConfig.setName( "Configurações" );
    pnlConfig.setBorder( BorderFactory.createEtchedBorder() );
    pnlConfig.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlBD = new JPanel();
    pnlBD.setLayout( null );
    pnlBD.setBounds( 20, 20, 590, 120 );
    pnlBD.setBorder( BorderFactory.createEtchedBorder() );
    pnlBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblBD = new JLabel( "Banco de Dados" );
    lblBD.setBounds( 10, 10, 110, 21 );
    lblBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblDiretorioBD = new JLabel( "Diretório:" );
    lblDiretorioBD.setBounds( 20, 40, 60, 21 );
    lblDiretorioBD.setFont( new Font( "Verdana", 0, 12 ) );
    lblDiretorioBD.setHorizontalAlignment( JLabel.RIGHT );
    
    txfDiretorioBD = new JTextField();
    txfDiretorioBD.setBounds( 85, 40, 300, 21 );
    txfDiretorioBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnPesquisarDir = new JButton( new ImageIcon( Tela.class.getResource( "folder.png" ) ) );
    btnPesquisarDir.setBounds( 390, 35, 30, 30 );
    btnPesquisarDir.setFont( new Font( "Verdana", 0, 12 ) );
    btnPesquisarDir.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnPesquisarDir();
        }
      }
    );
    
    lblNomeBD = new JLabel( "Nome:" );
    lblNomeBD.setBounds( 30, 70, 50, 21 );
    lblNomeBD.setFont( new Font( "Verdana", 0, 12 ) );
    lblNomeBD.setHorizontalAlignment( JLabel.RIGHT );
    
    txfNomeBD = new JTextField( "MyGoodMoney.db" );
    txfNomeBD.setBounds( 85, 70, 120, 21 );
    txfNomeBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlGerBD = new JPanel();
    pnlGerBD.setLayout( null );
    pnlGerBD.setBounds( 20, 160, 590, 90 );
    pnlGerBD.setBorder( BorderFactory.createEtchedBorder() );
    pnlGerBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblGerBD = new JLabel( "Gerenciamento da base de dados:" );
    lblGerBD.setBounds( 10, 10, 400, 21 );
    lblGerBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnCriarBD = new JButton( "Criar BD" );
    btnCriarBD.setBounds( 10, 40, 100, 21 );
    btnCriarBD.setFont( new Font( "Verdana", 0, 12 ) );
    btnCriarBD.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnCriarBD();
        }
      }
    );
    
    btnLimparBD = new JButton( "Limpar BD" );
    btnLimparBD.setBounds( 120, 40, 100, 21 );
    btnLimparBD.setFont( new Font( "Verdana", 0, 12 ) );
    btnLimparBD.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnLimparBD();
        }
      }
    );
    
    btnExcluirBD = new JButton( "Excluir BD" );
    btnExcluirBD.setBounds( 230, 40, 100, 21 );
    btnExcluirBD.setFont( new Font( "Verdana", 0, 12 ) );
    btnExcluirBD.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnExcluirBD();
        }
      }
    );
    
    pnlSobre = new JPanel();
    pnlSobre.setLayout( null );
    pnlSobre.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlSobre.setName( "Sobre" );
    pnlSobre.setBorder( BorderFactory.createEtchedBorder() );
    pnlSobre.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlAutor = new JPanel();
    pnlAutor.setLayout( null );
    pnlAutor.setBounds( 20, 20, 590, 120 );
    pnlAutor.setBorder( BorderFactory.createEtchedBorder() );
    
    lblNomeAutor = new JLabel( "Autor: Ricardo Montania Prado de Campos" );
    lblNomeAutor.setBounds( 20, 15, 400, 21 );
    lblNomeAutor.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblEmailAutor = new JLabel( "E-mail: ricardo@linuxafundo.com.br" );
    lblEmailAutor.setBounds( 20, 45, 400, 21 );
    lblEmailAutor.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblContatoAutor = new JLabel( "Contato: http://www.linuxafundo.com.br/contato" );
    lblContatoAutor.setBounds( 20, 75, 400, 21 );
    lblContatoAutor.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlTexto = new JPanel();
    pnlTexto.setLayout( null );
    pnlTexto.setBounds( 20, 160, 590, 180 );
    pnlTexto.setBorder( BorderFactory.createEtchedBorder() );
    
    String texto =
      "   O QUE ESTE PROGRAMA OFERECE:\n\n" +
      "Este programa visa oferecer um controle financeiro pessoal a nível\n" +
      "intermediário, através de movimentações e agendamentos de valores. É\n" +
      "possível cadastrar cada uma de suas contas, (Ex: Aluguel, Internet) e\n" +
      "caixas (Ex: Carteira, Conta Bancária) a fim de obter um breve resumo\n" +
      "em um dado período.\n\n" +
      "   Na versão atual não é possível gerenciar de maneira clara o fluxo\n" +
      "de caixa. Todavia, através das totalizações de entrada e saída por\n" +
      "período, é possível obter um resumo prático e simples que oferece\n" +
      "informações precisas baseadas nos lançamentos feitos.\n\n" +
      "   Além dos totalizadores, este programa oferece uma interface para\n" +
      "obtenção de um extrato de uma determinada conta. Que mostra detalha-\n" +
      "damente todas as movimentações referente àquela conta num período\n" +
      "selecionado.\n\n" +
      "COMO USAR:\n\n" +
      "   Antes de iniciar o uso do programa, é necessária a criação de um\n" +
      "sistema de armazenamento para os dados do programa. Isso é evidenciado\n" +
      "quando o programa é iniciado e nenhum banco de dados foi localizado.\n" +
      "Neste caso, a aba selecionada será \"Configurações\", para que seja\n" +
      "criado o banco de dados. Para isto, basta clicar no botão \"Criar BD\".\n" +
      "O banco de dados será criado, e o uso do programa pode ser iniciado.\n\n" +
      "  O uso, incluindo exemplos, está demonstrado no manual do programa.\n" +
      "Para obter o manual de uso do programa, acesse o website\n" +
      "http://www.linuxafundo.com.br/mygoodmoney. O manual está disponível\n" +
      "na versão online e também pode ser baixado em PDF.\n\n" +
      "   Este software encontra-se sob a licença GPL v3, a qual lhe permite\n" +
      "executar para qualquer propósito, estudar o funcionamento, redistri-\n" +
      "buir e aperfeiçoar da maneira que melhor julgar.\n" +
         "O código fonte pode ser obtido em: https://github.com/RMCampos/WealCash";
    
    JTextPane txtTexto = new JTextPane();
    txtTexto.setText( texto );
    txtTexto.setFont( new Font( "Monospaced", 0, 12 ) );
    txtTexto.setEditable( false );
    txtTexto.setBackground( pnlSobre.getBackground() );
    
    JScrollPane textoJ = new JScrollPane( txtTexto );
    textoJ.setBounds( 20, 20, 550, 140 );
    
    // Adicao dos componentes ao frame
    this.getContentPane().add( pnlSuperior );
       pnlSuperior.add( lblPeriodo );
       pnlSuperior.add( btnMenosPeriodo );
       pnlSuperior.add( dtcPeriodoIni );
       pnlSuperior.add( lblPeriodoA );
       pnlSuperior.add( dtcPeriodoFim );
       pnlSuperior.add( btnMaisPeriodo );
    this.getContentPane().add( tbpPainelAbas );
       tbpPainelAbas.add( pnlHome );
          pnlHome.add( pnlResumoCaixa );
             pnlResumoCaixa.add( lblResumoCaixa );
             pnlResumoCaixa.add( lblResNomeCaixa );
             pnlResumoCaixa.add( cbxResCaixa );
             pnlResumoCaixa.add( btnResCaixa );
             pnlResumoCaixa.add( lblResSaldoCaixa );
             pnlResumoCaixa.add( lblResValorSaldoCaixa );
             pnlResumoCaixa.add( lblResTotEntrCaixa );
             pnlResumoCaixa.add( lblResValTotEntrCaixa );
             pnlResumoCaixa.add( lblResTotSaidaCaixa );
             pnlResumoCaixa.add( lblResValTotSaidaCaixa );
             pnlResumoCaixa.add( ckbResumoCaixa );
             pnlResumoCaixa.add( pnlGrafico );
          pnlHome.add( pnlResumoConta );
             pnlResumoConta.add( lblResumoConta );
             pnlResumoConta.add( lblResNomeConta );
             pnlResumoConta.add( cbxResConta );
             pnlResumoConta.add( ckbResumoConta );
             pnlResumoConta.add( btnResConta );
             pnlResumoConta.add( lblResMovContaPc );
             pnlResumoConta.add( lblResValorMovContaPc );
             pnlResumoConta.add( lblResMovContaRs );
             pnlResumoConta.add( lblResValorMovContaRs );
             pnlResumoConta.add( lblResMovContaAno );
             pnlResumoConta.add( lblResValorMovContaAno );
       tbpPainelAbas.add( pnlMovimento );
          pnlMovimento.add( tbpMovimento );
             tbpMovimento.add( pnlCadastroMov );
                pnlCadastroMov.add( lblMovDescricao );
                pnlCadastroMov.add( txfMovDescricao );
                pnlCadastroMov.add( lblMovData );
                pnlCadastroMov.add( dtcMovData );
                pnlCadastroMov.add( lblMovValor );
                pnlCadastroMov.add( dbfMovValor );
                pnlCadastroMov.add( lblMovConta );
                pnlCadastroMov.add( cbxMovConta );
                pnlCadastroMov.add( lblMovCaixa );
                pnlCadastroMov.add( cbxMovCaixa );
                pnlCadastroMov.add( ckbMovPago );
                pnlCadastroMov.add( lblMovRecorrencia );
                pnlCadastroMov.add( rbtMovRecorrenciaSim );
                pnlCadastroMov.add( rbtMovRecorrenciaNao );
                pnlCadastroMov.add( lblRepetir );
                pnlCadastroMov.add( itfNumVezes );
                pnlCadastroMov.add( lblVezes );
                pnlCadastroMov.add( lblACada );
                pnlCadastroMov.add( itfNumPeriodo );
                pnlCadastroMov.add( cbxPeriodo );
                pnlCadastroMov.add( btnMovAdd );
                pnlCadastroMov.add( btnMovEditar );
                pnlCadastroMov.add( btnMovExcluir );
                pnlCadastroMov.add( btnMovConfirmar );
                pnlCadastroMov.add( btnMovCancelar );
             tbpMovimento.add( pnlTransferencia );
          pnlMovimento.add( pnlLancamentoMov );
       tbpPainelAbas.add( pnlExtratos );
          pnlExtratos.add( pnlExtrato );
             pnlExtrato.add( lblExtratoConta );
             pnlExtrato.add( cbxExtratoConta );
             pnlExtrato.add( lblExtratoPeriodo );
             pnlExtrato.add( dtcExtratoIni );
             pnlExtrato.add( lblExtratoHifen );
             pnlExtrato.add( dtcExtratoFim );
             pnlExtrato.add( btnExtrato );
             pnlExtrato.add( ckbExtratoProvisao );
             pnlExtrato.add( scbExtratoConta );
       tbpPainelAbas.add( pnlCadastros );
          pnlCadastros.add( pnlCadConta );
             pnlCadConta.add( lblAddConta );
             pnlCadConta.add( lblNomeConta );
             pnlCadConta.add( txfNomeConta );
             pnlCadConta.add( lblTipoConta );
             pnlCadConta.add( cbxTipoConta );
             pnlCadConta.add( btnAddConta );
             pnlCadConta.add( btnEditarConta );
             pnlCadConta.add( btnExcluirConta );
             pnlCadConta.add( btnConfirmarConta );
             pnlCadConta.add( btnCancelarConta );
             pnlCadConta.add( pnlContas );
          pnlCadastros.add( pnlCadCaixa );
             pnlCadCaixa.add( lblAddCaixa );
             pnlCadCaixa.add( lblNomeCaixa );
             pnlCadCaixa.add( txfNomeCaixa );
             pnlCadCaixa.add( lblSaldoInicial );
             pnlCadCaixa.add( dbfSaldoInicialCaixa );
             pnlCadCaixa.add( btnAddCaixa );
             pnlCadCaixa.add( btnEditarCaixa );
             pnlCadCaixa.add( btnExcluirCaixa );
             pnlCadCaixa.add( btnConfirmarCaixa );
             pnlCadCaixa.add( btnCancelarCaixa );
             pnlCadCaixa.add( pnlCaixas );
       tbpPainelAbas.add( pnlConfig );
          pnlConfig.add( pnlBD );
             pnlBD.add( lblBD );
             pnlBD.add( lblDiretorioBD );
             pnlBD.add( txfDiretorioBD );
             pnlBD.add( btnPesquisarDir );
             pnlBD.add( lblNomeBD );
             pnlBD.add( txfNomeBD );
          pnlConfig.add( pnlGerBD );
             pnlGerBD.add( lblGerBD );
             pnlGerBD.add( btnCriarBD );
             pnlGerBD.add( btnLimparBD );
             pnlGerBD.add( btnExcluirBD );
       tbpPainelAbas.add( pnlSobre );
          pnlSobre.add( pnlAutor );
             pnlAutor.add( lblNomeAutor );
             pnlAutor.add( lblEmailAutor );
             pnlAutor.add( lblContatoAutor );
          pnlSobre.add( pnlTexto );
             pnlTexto.add( textoJ );
             
    tbpMovimento.setTitleAt( 0, "Déb/Créd/Provisão" );
    tbpMovimento.setTitleAt( 1, "Transferência" );
  }
  public void acessar()
  {
    try
    {
      comando = "";

      while( comando.isEmpty() )
      {
        Thread.sleep(50);
      }
    }
    catch( InterruptedException e )
    {
      e.printStackTrace();
    }
  }
  public void selecionarAba( int indexParam )
  {
    tbpPainelAbas.setSelectedIndex( indexParam );
  }
  public String getComandoTela()
  {
    return( this.comando );
  }
  public void limparCamposConta()
  {
    setTxfNomeConta( "" );
    setCbxTipoConta( null );
  }
  public void limparCamposCaixa()
  {
    setTxfNomeCaixa( "" );
    setDbfSaldoInicialCaixa( Double.NaN );
  }
  public void limparCamposLancamento()
  {
    this.txfMovDescricao.setText( "" );
    this.dtcMovData.setDate( new Date() );
    this.dbfMovValor.setValue( Double.NaN );
    this.cbxMovConta.setSelectedIndex( -1 );
    this.cbxMovCaixa.setSelectedIndex( -1 );
    this.ckbMovPago.setSelected( false );
    this.rbtMovRecorrenciaNao.setSelected( true );
    this.rbtMovRecorrenciaSim.setSelected( false );
    setModoBotoesMovimento( "NOVO" );
    habilitarRecorrencia( false );
  }
  public void setModoBotoesConta( String modoParam )
  {
    switch( modoParam )
    {
      case "NAVEGACAO":
      {
        this.btnAddConta.setEnabled( true );
        this.btnEditarConta.setEnabled( true );
        this.btnExcluirConta.setEnabled( true );
        this.btnConfirmarConta.setEnabled( false );
        this.btnCancelarConta.setEnabled( false );
        this.txfNomeConta.setEditable( false );
        this.cbxTipoConta.setEnabled( false );
        break;
      }
      case "EDICAO":
      {
        this.btnAddConta.setEnabled( false );
        this.btnEditarConta.setEnabled( false );
        this.btnExcluirConta.setEnabled( false );
        this.btnConfirmarConta.setEnabled( true );
        this.btnCancelarConta.setEnabled( true );
        this.txfNomeConta.setEditable( true );
        this.cbxTipoConta.setEnabled( true );
      }
    }
  }
  public void setModoBotoesCaixa( String modoParam )
  {
    switch( modoParam )
    {
      case "NAVEGACAO":
      {
        this.btnAddCaixa.setEnabled( true );
        this.btnEditarCaixa.setEnabled( true );
        this.btnExcluirCaixa.setEnabled( true );
        this.btnConfirmarCaixa.setEnabled( false );
        this.btnCancelarCaixa.setEnabled( false );
        this.txfNomeCaixa.setEditable( false );
        this.dbfSaldoInicialCaixa.setEditable( false );
        break;
      }
      case "EDICAO":
      {
        this.btnAddCaixa.setEnabled( false );
        this.btnEditarCaixa.setEnabled( false );
        this.btnExcluirCaixa.setEnabled( false );
        this.btnConfirmarCaixa.setEnabled( true );
        this.btnCancelarCaixa.setEnabled( true );
        this.txfNomeCaixa.setEditable( true );
        this.dbfSaldoInicialCaixa.setEditable( true );
      }
    }
  }
  public void setModoBotoesMovimento( String modoParam )
  {
    switch( modoParam )
    {
      case "NAVEGACAO":
      {
        this.btnMovAdd.setEnabled( true );
        this.btnMovEditar.setEnabled( true );
        this.btnMovExcluir.setEnabled( true );
        this.btnMovCancelar.setEnabled( false );
        this.btnMovConfirmar.setEnabled( false );
        this.txfMovDescricao.setEditable( false );
        this.dtcMovData.setEditable( false );
        this.dbfMovValor.setEditable( false );
        this.cbxMovConta.setEnabled( false );
        this.cbxMovCaixa.setEnabled( false );
        this.ckbMovPago.setEnabled( false );
        this.rbtMovRecorrenciaSim.setEnabled( false );
        this.rbtMovRecorrenciaNao.setEnabled( false );
        break;
      }
      case "EDICAO":
      {
        this.btnMovAdd.setEnabled( false );
        this.btnMovEditar.setEnabled( false );
        this.btnMovExcluir.setEnabled( false );
        this.btnMovCancelar.setEnabled( true );
        this.btnMovConfirmar.setEnabled( true );
        this.txfMovDescricao.setEditable( true );
        this.dtcMovData.setEditable( true );
        this.dbfMovValor.setEditable( true );
        this.cbxMovConta.setEnabled( true );
        this.cbxMovCaixa.setEnabled( true );
        this.ckbMovPago.setEnabled( true );
        this.rbtMovRecorrenciaSim.setEnabled( true );
        this.rbtMovRecorrenciaNao.setEnabled( true );
        break;
      }
    }
  }
  public void AOCLICARbtnResCaixa()
  {
    this.comando = "CARREGAR_CAIXA_HOME";
  }
  public void AOCLICARbtnResConta()
  {
    this.comando = "CARREGAR_CONTA_HOME";
  }
  public void AOCLICARbtnAddConta()
  {
    this.comando = "INCLUIR_CONTA";
  }
  public void AOPRESSIONARbtnAddConta( KeyEvent e )
  {
    if( e.getKeyCode() == KeyEvent.VK_ENTER )
      this.comando = "ADICIONAR_CONTA";
  }
  public void AOPRESSIONARcbxMovCaixa( KeyEvent e )
  {
    if( e.getKeyCode() == KeyEvent.VK_ENTER )
      this.comando = "ADICIONAR_LANCAMENTO";
  }
  public void AOCLICARbtnAddCaixa()
  {
    this.comando = "ADICIONAR_CAIXA";
  }
  public void AOCLICARbtnEditarConta()
  {
    this.comando = "ALTERAR_CONTA";
  }
  public void AOCLICARbtnEditarCaixa()
  {
    this.comando = "EDITAR_CAIXA";
  }
  public void AOCLICARbtnExcluirConta()
  {
    this.comando = "EXCLUIR_CONTA";
  }
  public void AOCLICARbtnExcluirCaixa()
  {
    this.comando = "EXCLUIR_CAIXA";
  }
  public void AOCLICARpnlContas( MouseEvent e )
  {
    if( e.getClickCount() == 1 )
      this.comando = "CARREGAR_CONTA_SELECIONADA";
  }
  public void AOCLICARpnlCaixas( MouseEvent e )
  {
    if( e.getClickCount() == 1 )
      this.comando = "CARREGAR_CAIXA_SELECIONADO";
  }
  public void AOCLICARpnlMovimento( MouseEvent e )
  {
    if( e.getClickCount() == 1 )
      this.comando = "CARREGAR_LANCAMENTO_SELECIONADO";
  }
  public void AOCLICARbtnCancelarConta()
  {
    this.comando = "CANCELAR_CONTA";
  }
  public void AOCLICARbtnConfirmarConta()
  {
    this.comando = "CONFIRMAR_CONTA";
  }
  public void AOCLICARbtnConfirmarCaixa()
  {
    this.comando = "CONFIRMAR_CAIXA";
  }
  public void AOCLICARbtnCancelarCaixa()
  {
    this.comando = "CANCELAR_CAIXA_SELECIONADO";
  }
  public void AOCLICARbtnMovCancelar()
  {
    this.comando = "CANCELAR_LANCAMENTO_SELECIONADO";
  }
  public void AOCLICARbtnMovExcluir()
  {
    this.comando = "EXCLUIR_LANCAMENTO";
  }
  public void AOCLICARbtnMovConfirmar()
  {
    this.comando = "CONFIRMAR_LANCAMENTO";
  }
  public void AOCLICARbtnMovEditar()
  {
    this.comando = "EDITAR_LANCAMENTO";
  }
  public void AOCLICARbtnMovAdd()
  {
    this.comando = "INCLUIR_LANCAMENTO";
  }
  public void AOCLICARbtnPesquisarDir()
  {
    this.comando = "PESQUISAR_DIR";
  }
  public void AOCLICARbtnExtrato()
  {
    this.comando = "CONFIRMAR_EXTRATO";
  }
  public void AOCLICARdtcPeriodoIni()
  {
    this.comando = "ATUALIZAR_PERIODO";
  }
  public void AOCLICARdtcPeriodoFim()
  {
    this.comando = "ATUALIZAR_PERIODO";
  }
  public void AOCLICARbtnCriarBD()
  {
    this.comando = "CRIAR_BD";
  }
  public void AOCLICARbtnLimparBD()
  {
    this.comando = "LIMPAR_BD";
  }
  public void AOCLICARbtnExcluirBD()
  {
    this.comando = "EXCLUIR_BD";
  }
  public void AOCLICARbtnMaisPeriodo()
  {
    this.comando = "INCREMENTAR_PERIODO";
  }
  public void AOCLICARbtnMenosPeriodo()
  {
    this.comando = "DECREMENTAR_PERIODO";
  }
  public String getTxfNomeConta()
  {
    return( txfNomeConta.getText() );
  }
  public void setTxfNomeConta( String nomeParam )
  {
    txfNomeConta.setText( nomeParam );
  }
  public String getTxfDiretorioBD()
  {
    return( txfDiretorioBD.getText() );
  }
  public void setTxfDiretorioBD( String dirParam )
  {
    txfDiretorioBD.setText( dirParam );
  }
  public EnumTipoConta getCbxTipoConta()
  {
    if( cbxTipoConta.getSelectedIndex() >= 0 )
      return( (EnumTipoConta) cbxTipoConta.getSelectedItem() );
    return( null );
  }
  public void setCbxTipoConta( EnumTipoConta tipo )
  {
    cbxTipoConta.setSelectedItem( tipo );
  }
  public String getTxfMovDescricao()
  {
    return( txfMovDescricao.getText() );
  }
  public void setTxfMovDescricao( String descrParam )
  {
    txfMovDescricao.setText( descrParam );
  }
  public String getTxfNomeBD()
  {
    return( txfNomeBD.getText() );
  }
  public void setTxfNomeBD( String nomeParam )
  {
    txfNomeBD.setText( nomeParam );
  }
  public Date getDtcMovData()
  {
    return( dtcMovData.getDate() );
  }
  public void setDtcMovData( Date dataParam )
  {
    dtcMovData.setDate( dataParam );
  }
  public void setDtcPeriodoIni( Date dataParam )
  {
    dtcPeriodoIni.setDate( dataParam );
  }
  public void setDtcPeriodoFim( Date dataParam )
  {
    dtcPeriodoFim.setDate( dataParam );
  }
  public Double getDbfMovValor()
  {
    return( dbfMovValor.getValue() );
  }
  public void setDbfMovValor( Double valorParam )
  {
    dbfMovValor.setValue( valorParam );
  }
  public String getCbxMovConta()
  {
    if( cbxMovConta.getSelectedIndex() >= 0 )
      return( (String) cbxMovConta.getSelectedItem() );
    return( null );
  }
  public void setCbxMovConta( String contaParam )
  {
    cbxMovConta.setSelectedItem( contaParam );
  }
  public String getCbxMovCaixa()
  {
    if( cbxMovCaixa.getSelectedIndex() >= 0 )
      return( (String) cbxMovCaixa.getSelectedItem() );
    return( null );
  }
  public void setCbxMovCaixa( String caixaParam )
  {
    cbxMovCaixa.setSelectedItem( caixaParam );
  }
  public boolean getCkbMovPago()
  {
    return( ckbMovPago.isSelected() );
  }
  public void setCkbMovPago( boolean selParam )
  {
    ckbMovPago.setSelected( selParam );
  }
  public char getMovRecorrencia()
  {
    return( (rbtMovRecorrenciaSim.isSelected())? 'S' : 'N' );
  }
  public void addConta( Conta contaParam )
  {
    Object[] dados = { contaParam.getSNome(), EnumTipoConta.getPorCodigo( contaParam.getCTipo() ).toString() };
    contasModel.addRow(dados);
    
    // adiciona ao comboBox de movimento
    atualizarContaAdicionada( contaParam.getSNome() );
  }
  public void addCaixa( Caixa caixaParam )
  {
    Object[] dados = { caixaParam.getSNome(), ValueTools.format( caixaParam.getDSaldo() ) };
    caixasModel.addRow( dados );
    // adiciona ao comboBox de movimento
    atualizarCaixaAdicionado( caixaParam.getSNome() );
  }
  public void addLancamento( Lancamento lancamentoParam )
  {
    Object[] dados = { "", ' ', "", "", ' ' };
    
    if( lancamentoParam.getIDataQuitacao() == 0 )
    {
      dados[0] = DateTools.formatDataIntToStringBR( lancamentoParam.getIDataVencimento() );
      dados[4] = 'N';
    }
    else
    {
      dados[0] = DateTools.formatDataIntToStringBR( lancamentoParam.getIDataQuitacao() );
      dados[4] = 'S';
    }
    
    dados[1] = lancamentoParam.getCTipo();
    dados[2] = lancamentoParam.getSDescricao();
    dados[3] = ValueTools.format( lancamentoParam.getDValor() );
  
    movimentoModel.addRow( dados );
  }
  public Conta getContaSelecionada()
  {
    Conta conta = new Conta();
    
    conta.setSNome( (String) contasModel.getValueAt(contasTable.getSelectedRow(), 0 ) );
    conta.setCTipo( EnumTipoConta.getPorDescricao( (String) contasModel.getValueAt(contasTable.getSelectedRow(), 1 ) ).getCCodigo() );
    
    return( conta );
  }
  public Caixa getCaixa( String nomeParam )
  { 
    for( int i=0; i<caixasModel.getRowCount(); i++ )
    {
      String nomeTela = (String) caixasModel.getValueAt( i, 0 );
      
      if( nomeTela.equals( nomeParam ) )
      {
        Caixa caixa = new Caixa();
        caixa.setSNome( (String) caixasModel.getValueAt( i, 0 ) );
        caixa.setDSaldo( ValueTools.unformat( (String) caixasModel.getValueAt( i, 1 ) ) );
        return( caixa );
      }
    }
    return( null );
  }
  public Caixa getCaixaSelecionado()
  {
    Caixa caixa = new Caixa();
    
    caixa.setSNome( (String) caixasModel.getValueAt( caixasTable.getSelectedRow(), 0 ) );
    caixa.setDSaldo( ValueTools.unformat( (String)caixasModel.getValueAt( caixasTable.getSelectedRow(), 1 ) ) );
    
    return( caixa );
  }
  public Lancamento getLancamentoSelecionado( BD banco )
  {
    Lancamento lancamento;
    
    // pega os itens da tela
    int linha = movimentoTable.getSelectedRow();
    
    String campo1Data = (String) movimentoModel.getValueAt( linha, 0 );
    char   campo2Tipo = (char)   movimentoModel.getValueAt( linha, 1 );
    String campo3Desc = (String) movimentoModel.getValueAt( linha, 2 );
    String campo4Valo = (String) movimentoModel.getValueAt( linha, 3 );
    char campo5Pago   = (char)   movimentoModel.getValueAt( linha, 4 );
    
    // tratamentos
    campo4Valo = campo4Valo.replace( "R$", "" );
    campo4Valo = campo4Valo.replaceAll( "\\.", "" );
    campo4Valo = campo4Valo.replaceAll( ",", "." );
    
    // tratamento a vista/provisao
    lancamento = banco.selectLancamento( campo1Data, campo3Desc, campo4Valo, campo5Pago );
    lancamento.setCTipo( campo2Tipo );
    
    return( lancamento );
  }
  public void removeConta( Conta contaParam )
  {
    if( contasModel.getRowCount() > 0 )
    {
      for( int i=0; i<contasModel.getRowCount(); i++ )
      {
        String conta = (String) contasModel.getValueAt( i, 0 );
        if( conta.equals( contaParam.getSNome() ) )
        {
          contasModel.removeRow( i );
          atualizarContaRemovida( conta );
        }
      }
    }
  }
  public void removeCaixa( Caixa caixaParam )
  {
    if( caixasModel.getRowCount() > 0 )
    {
      for( int i=0; i<caixasModel.getRowCount(); i++ )
      {
        String caixa = (String) caixasModel.getValueAt( i, 0 );
        if( caixa.equals( caixaParam.getSNome() ) )
        {
          contasModel.removeRow( i );
          atualizarCaixaRemovido( caixa );
        }
      }
    }
  }
  public void removeLancamento( Lancamento lancamentoParam )
  {
    if( movimentoModel.getRowCount() > 0 )
    {
      String dataFormatada = DateTools.formatDataIntToStringBR( lancamentoParam.getIDataVencimento() );
      String valorFormatado = ValueTools.format( lancamentoParam.getDValor() );
      
      for( int i=0; i<movimentoModel.getRowCount(); i++ )
      {
        String dataTela      = (String) movimentoModel.getValueAt( i, 0 );
        String descricaoTela = (String) movimentoModel.getValueAt( i, 2 );
        String valorTela     = (String) movimentoModel.getValueAt( i, 3 );
        
        if( dataTela.equals( dataFormatada ) &&
            descricaoTela.equals( lancamentoParam.getSDescricao() ) &&
            valorTela.equals( valorFormatado )
          )
        {
          movimentoModel.removeRow( i );
        }
      }
    }
  }
  public void substituirConta( Conta contaVelhaParam, Conta contaNovaParam )
  {
    for( int i=0; i<contasModel.getRowCount(); i++ )
    {
      String contaAtual = (String) contasModel.getValueAt( i, 0 );
      if( contaAtual.equals( contaVelhaParam.getSNome() ) )
      {
        contasModel.setValueAt( contaNovaParam.getSNome(), i, 0 );
        contasModel.setValueAt( EnumTipoConta.getPorCodigo( contaNovaParam.getCTipo() ).toString(), i, 1 );
        atualizarPainelContas();
        return;
      }
    }
  }
  public void substituirLancamento( Lancamento lancamentoVelhoParam, Lancamento lancamentoNovoParam )
  {
    String dataFormatada;
    
    if( lancamentoVelhoParam.getIDataQuitacao() == 0 )
    {
      dataFormatada = DateTools.formatDataIntToStringBR( lancamentoVelhoParam.getIDataVencimento() );
    }
    else
    {
      dataFormatada = DateTools.formatDataIntToStringBR( lancamentoVelhoParam.getIDataQuitacao() );
    }
    
    String valorFormatado = ValueTools.format( lancamentoVelhoParam.getDValor() );
      
    for( int i=0; i<movimentoModel.getRowCount(); i++ )
    {
      String dataTela      = (String) movimentoModel.getValueAt( i, 0 );
      String descricaoTela = (String) movimentoModel.getValueAt( i, 2 );
      String valorTela     = (String) movimentoModel.getValueAt( i, 3 );
        
      if( dataTela.equals( dataFormatada ) &&
          descricaoTela.equals( lancamentoVelhoParam.getSDescricao() ) &&
          valorTela.equals( valorFormatado )
        )
      {
        String dataF;
        if( lancamentoNovoParam.getIDataQuitacao() == 0 )
        {
          dataF = DateTools.formatDataIntToStringBR( lancamentoNovoParam.getIDataVencimento() );
        }
        else
        {
          dataF = DateTools.formatDataIntToStringBR( lancamentoNovoParam.getIDataQuitacao() );
        }
        
        movimentoModel.setValueAt( dataF, i, 0 );
        movimentoModel.setValueAt( lancamentoNovoParam.getSDescricao(), i, 2 );
        movimentoModel.setValueAt( ValueTools.format( lancamentoNovoParam.getDValor() ), i, 3 );
        
        if( lancamentoNovoParam.getIDataQuitacao() > 0 ) // pago
        {
          movimentoModel.setValueAt( 'S', i, 4 );
        }
        else // a pagar
        {
          movimentoModel.setValueAt( 'N', i, 4 );
        }
        
        atualizarPainelMovimento();
        return;
      }
    }
  }
  public void substituirCaixa( Caixa caixaVelhoParam, Caixa caixaNovoParam )
  {
    for( int i=0; i<caixasModel.getRowCount(); i++ )
    {
      String caixaAtual = (String) caixasModel.getValueAt( i, 0 );
      if( caixaAtual.equals( caixaVelhoParam.getSNome() ) )
      {
        caixasModel.setValueAt( caixaNovoParam.getSNome(), i, 0 );
        caixasModel.setValueAt( ValueTools.format( caixaNovoParam.getDSaldo() ), i, 1 );
        atualizarPainelCaixas();
        atualizarCaixaRemovido( caixaVelhoParam.getSNome() );
        atualizarCaixaAdicionado( caixaNovoParam.getSNome() );
        return;
      }
    }
  }
  public void atualizarPainelContas()
  {
    contasTable.repaint();
  }
  public void atualizarPainelCaixas()
  {
    caixasTable.repaint();
  }
  public void atualizarPainelMovimento()
  {
    movimentoTable.repaint();
  }
  public void atualizarContaRemovida( String conta )
  {
    JComboBox[] combos = {cbxMovConta, cbxExtratoConta, cbxResConta};
    
    for( JComboBox combo : combos )
    {
      int indexRemover = -1;

      for( int i=0; i<combo.getItemCount(); i++ )
      {
        String itemCombo = (String)combo.getItemAt( i );

        if( itemCombo.equals( conta ) ){
          indexRemover = i;
          break;
        }
      }

      if( indexRemover != -1 )
      {
        combo.removeItemAt( indexRemover );
      }
    }
  }
  public void atualizarCaixaRemovido( String caixa )
  {
    
    JComboBox[] combos = {cbxMovCaixa, cbxResCaixa};
    
    for( JComboBox combo : combos )
    {
      int indexRemover = -1;

      for( int i=0; i<combo.getItemCount(); i++ )
      {
        String itemCombo = (String)combo.getItemAt( i );

        if( itemCombo.equals( caixa ) )
        {
          indexRemover = i;
          break;
        }
      }

      if( indexRemover != -1 )
      {
        combo.removeItemAt( indexRemover );
      }
    }
  }
  public void atualizarContaAdicionada( String conta )
  {
    cbxMovConta.addItem( conta );
    cbxResConta.addItem( conta );
    cbxExtratoConta.addItem( conta );
    
    cbxMovConta.setSelectedIndex( -1 );
    cbxResConta.setSelectedIndex( -1 );
    cbxExtratoConta.setSelectedIndex( -1 );
  }
  public void atualizarCaixaAdicionado( String caixa )
  {
    cbxMovCaixa.addItem( caixa );
    cbxResCaixa.addItem( caixa );
    
    cbxMovCaixa.setSelectedIndex( -1 );
    cbxResCaixa.setSelectedIndex( -1 );
  }
  private void habilitarRecorrencia( boolean habilitarParam )
  {
    lblRepetir.setEnabled(habilitarParam);
    itfNumVezes.setEnabled(habilitarParam);
    lblVezes.setEnabled(habilitarParam);
    lblACada.setEnabled(habilitarParam);
    itfNumPeriodo.setEnabled(habilitarParam);
    cbxPeriodo.setEnabled(habilitarParam);
  }
  public String getTxfNomeCaixa()
  {
    return( txfNomeCaixa.getText() );
  }
  public void setTxfNomeCaixa( String nomeParam )
  {
    txfNomeCaixa.setText( nomeParam );
  }
  public Double getDbfSaldoInicialCaixa()
  {
    return( dbfSaldoInicialCaixa.getValue() );
  }
  public void setDbfSaldoInicialCaixa( Double valorParam )
  {
    dbfSaldoInicialCaixa.setValue( valorParam );
  }
  public void limparSelecaoMovimento()
  {
    movimentoTable.getSelectionModel().clearSelection();
  }
  public void limparSelecaoConta()
  {
    contasTable.getSelectionModel().clearSelection();
  }
  public void limparSelecaoCaixa()
  {
    caixasTable.getSelectionModel().clearSelection();
  }
  public void limparMovimentosModel()
  { 
    movimentoModel.setRowCount(0);
  }
  public void limparCaixasModel()
  {
    caixasModel.setRowCount( 0 );
  }
  public void limparContasModel()
  {
    contasModel.setRowCount( 0 );
  }
  public void limparResumoConta()
  {
    setLblResValorMovContaPc( "0,00 %" );
    setLblResValorMovContaRs( "R$ 0,00" );
    setLblResValorMovContaAno( "R$ 0,00" );
  }
  public DefaultTableModel getMovimentoModel()
  {
    return( movimentoModel );
  }
  public DefaultTableModel getContasModel()
  {
    return( contasModel );
  }
  public DefaultTableModel getCaixasModel()
  {
    return( caixasModel );
  }
  public void limparCombos()
  {
    JComboBox[] j = new JComboBox[5];
    j[0] = cbxMovCaixa;
    j[1] = cbxMovConta;
    j[2] = cbxExtratoConta;
    j[3] = cbxResCaixa;
    j[4] = cbxResConta;
    
    for( JComboBox combo : j )
    {
      limparCombo( combo );
    }
  }
  public void limparCombo( JComboBox combo )
  {
    if( combo.getItemCount() > 0 )
      combo.removeAllItems();
  }
  public void limparTudo()
  {
    // Home
    cbxResCaixa.setSelectedIndex( -1 );
    setLblResValorSaldoCaixa( "R$ 0,00" );
    setLblResValTotEntrCaixa( "R$ 0,00" );
    setLblResValTotSaidaCaixa( "R$ 0,00" );
    cbxResConta.setSelectedIndex( -1 );
    setLblResValorMovContaPc( "0.00 %" );
    setLblResValorMovContaRs( "R$ 0,00" );
    setLblResValorMovContaAno( "R$ 0,00" );
    // Movimento
    txfMovDescricao.setText( "" );
    dtcMovData.setDate( new Date() );
    dbfMovValor.setValue( 0.0 );
    cbxMovConta.setSelectedIndex( -1 );
    cbxMovCaixa.setSelectedIndex( -1 );
    ckbMovPago.setSelected( false );
    rbtMovRecorrenciaNao.setSelected( true );
    rbtMovRecorrenciaSim.setSelected( false );
    habilitarRecorrencia( false );
    // Cadastros
    txfNomeConta.setText( "" );
    cbxTipoConta.setSelectedIndex( -1 );
    txfNomeCaixa.setText( "" );
  }
  public void limparCamposHome()
  {
    cbxResCaixa.setSelectedIndex( -1 );
    cbxResConta.setSelectedIndex( -1 );
  }
  public void selecionarCaixaHome( String caixaParam )
  {
    cbxResCaixa.setSelectedItem( caixaParam );
  }
  public void setLblResValorSaldoCaixa( String saldoParam )
  {
    lblResValorSaldoCaixa.setText( saldoParam );
  }
  public String getCbxResCaixa()
  {
    if( cbxResCaixa.getSelectedIndex() >= 0 )
    {
      return( (String) cbxResCaixa.getSelectedItem() );
    }
    return( null );
  }
  public String getCbxResConta()
  {
    if( cbxResConta.getSelectedIndex() >= 0 )
    {
      return( (String) cbxResConta.getSelectedItem() );
    }
    return( null );
  }
  public Date getDtcPeriodoIni()
  {
    return( dtcPeriodoIni.getDate() );
  }
  public Date getDtcPeriodoFim()
  {
    return( dtcPeriodoFim.getDate() );
  }
  public void setLblResValTotEntrCaixa( String valorParam )
  {
    lblResValTotEntrCaixa.setText( valorParam );
  }
  public void setLblResValTotSaidaCaixa( String valorParam )
  {
    lblResValTotSaidaCaixa.setText( valorParam );
  }
  public void setLblResValorMovContaPc( String valorParam )
  {
    lblResValorMovContaPc.setText( valorParam );
  }
  public void setLblResValorMovContaRs( String valorParam )
  {
    lblResValorMovContaRs.setText( valorParam );
  }
  public void setLblResValorMovContaAno( String valorParam )
  {
    lblResValorMovContaAno.setText( valorParam );
  }
  public Caixa getCaixaTela()
  {
    Caixa caixa = new Caixa();
    
    caixa.setSNome( getTxfNomeCaixa() );
    caixa.setDSaldo( getDbfSaldoInicialCaixa() );
    return( caixa );
  }
  public Conta getContaTela()
  {
    Conta conta = new Conta();
    conta.setSNome( getTxfNomeConta() );
    conta.setCTipo( getCbxTipoConta().getCCodigo() );
    return( conta );
  }
  public Lancamento getLancamentoTela( BD banco )
  {
    Lancamento lancamento = new Lancamento();
    
    if( getCkbMovPago() )
    {
      // lancamento a vista
      lancamento.setIDataEmissao( DateTools.parseDateToInteger( getDtcMovData() ) );
      lancamento.setIDataVencimento( DateTools.parseDateToInteger( getDtcMovData() ) );
      lancamento.setIDataQuitacao( DateTools.parseDateToInteger( getDtcMovData() ) );
    }
    else
    {
      // provisao
      lancamento.setIDataEmissao( DateTools.parseDateToInteger( new Date() ) );
      lancamento.setIDataVencimento( DateTools.parseDateToInteger( getDtcMovData() ) );
      lancamento.setIDataQuitacao( 0 );
    }
    
    lancamento.setDescricao( getTxfMovDescricao() );
    lancamento.setDValor( getDbfMovValor() );
    lancamento.setCTipo( banco.selectCTipoConta( banco.selectICodConta( getCbxMovConta() ) ) );
    lancamento.setICodConta( banco.selectICodConta( getCbxMovConta() ) );
    lancamento.setICodCaixa( banco.selectICodCaixa( getCbxMovCaixa() ) );
    
    return( lancamento );
  }
  public JTable getCaixasTable()
  {
    return( caixasTable );
  }
  public JTable getContasTable()
  {
    return( contasTable );
  }
  public JTable getMovimentoTable()
  {
    return( movimentoTable );
  }
  public int getItfNumVezes()
  {
    return( itfNumVezes.getValue() );
  }
  public int getItfNumPeriodo()
  {
    return( itfNumPeriodo.getValue() );
  }
  public EnumPeriodo getCbxPeriodo()
  {
    if( cbxPeriodo.getSelectedIndex() >= 0 )
    {
      return( (EnumPeriodo) cbxPeriodo.getSelectedItem() );
    }
    return( null );
  }
  public void setTxtExtrato( String pTexto )
  {
    this.txtExtrato.setText( pTexto );
  }
  public String getTxtExtrato()
  {
    return( this.txtExtrato.getText() );
  }
  public String getCbxExtratoConta()
  {
    if( cbxExtratoConta.getSelectedIndex() >= 0 )
    {
      return( (String) this.cbxExtratoConta.getSelectedItem() );
    }
    return( null );
  }
  public Date getDtcExtratoIni()
  {
    return( this.dtcExtratoIni.getDate() );
  }
  public Date getDtcExtratoFim()
  {
    return( this.dtcExtratoFim.getDate() );
  }
  public boolean getCkbExtratoProvisao()
  {
    return( ckbExtratoProvisao.isSelected() );
  }
  public boolean getCkbResumoConta()
  {
    return( ckbResumoConta.isSelected() );
  }
  public boolean getCkbResumoCaixa()
  {
    return( ckbResumoCaixa.isSelected() );
  }
  public void habilitarComponentesRecorrencia( boolean hab )
  {
    lblMovRecorrencia.setVisible(hab);
    rbtMovRecorrenciaSim.setVisible(hab);
    rbtMovRecorrenciaNao.setVisible(hab);
    lblRepetir.setVisible(hab);
    itfNumVezes.setVisible(hab);
    lblVezes.setVisible(hab);
    lblACada.setVisible(hab);
    itfNumPeriodo.setVisible(hab);
    cbxPeriodo.setVisible(hab);
  }
  public void atualizarSaldoCaixa( Caixa pCaixa )
  {
    int linhas = caixasModel.getRowCount();
    
    if( linhas <= 0 )
      return;
    
    for( int linha=0; linha<linhas; linha++ )
    {
      String caixaLinha = (String) caixasModel.getValueAt( linha, 0 );
      
      if( caixaLinha.equals( pCaixa.getSNome() ) )
      {
        caixasModel.setValueAt( ValueTools.format( pCaixa.getDSaldo() ), linha, 1 );
        break;
      }
    }
  }
  public void setGrafico( JFreeChart pGrafico )
  {
    if( pGrafico != null )
    {
      pnlGrafico.setChart( pGrafico );
      pnlGrafico.setVisible( true );
      pnlGrafico.setMouseWheelEnabled( true );
      pnlGrafico.setMouseZoomable( true );
    }
    else
    {
      pnlGrafico.setMouseWheelEnabled( false );
      pnlGrafico.setMouseZoomable( false );
      pnlGrafico.setVisible( false );
    }
  }
}