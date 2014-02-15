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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo
 */
public class Tela extends JFrame
{
  private String comando;
  private String OS;
  private String camIconePrograma;
  private String camIconeAdd;
  private String camIconeEditar;
  private String camIconeExcluir;
  private String camIconeCancelar;
  private String camIconeLupa;
  
  private JPanel pnlSuperior;
  private JTabbedPane tbpPainelAbas;
     private JPanel     pnlHome;
        private JPanel     pnlPeriodo;
           private JLabel       lblPeriodo;
           private JDateChooser dtcPeriodoIni;
           private JLabel       lblPeriodoA;
           private JDateChooser dtcPeriodoFim;
        private JPanel     pnlResumoCaixa;
           private JLabel     lblResumoCaixa;
           private JLabel     lblResNomeCaixa;
           private JComboBox  cbxResCaixa;
           private JLabel     lblResSaldoCaixa;
           private JLabel     lblResValorSaldoCaixa;
           private JLabel     lblResTotEntrCaixa;
           private JLabel     lblResValTotEntrCaixa;
           private JLabel     lblResTotSaidaCaixa;
           private JLabel     lblResValTotSaidaCaixa;
     private JPanel     pnlMovimento;
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
        private JPanel     pnlLancamentoMov;
           private JTable     movimentoTable;
           private DefaultTableModel movimentoModel;
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
           private JButton    btnCancelarConta;
           private JPanel     pnlContas;
           private JTable     contasTable;
           private DefaultTableModel contasModel;
        private JPanel     pnlCadCaixa;
           private JLabel     lblAddCaixa;
           private JLabel     lblNomeCaixa;
           private JTextField txfNomeCaixa;
           private JLabel     lblSaldoInicial;
           private JDoubleField dbfSaldoInicialConta;
           private JButton    btnAddCaixa;
           private JButton    btnEditarCaixa;
           private JButton    btnExcluirCaixa;
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
           private JButton    btnExportarBD;
           private JButton    btnImportarBD;
  
  public Tela( String nomeParam )
  {
    super( nomeParam );
    obterCaminhoIcones( "LINUX" );

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
  
  private void obterCaminhoIcones(String ambiente )
  {
    this.OS = ambiente;

    switch( ambiente )
    {
      case "WINDOWS":
      {
        this.camIconePrograma = "K:\\Ricardo\\Projetos NetBeans\\WealCash\\icons\\money.png";
        this.camIconeAdd      = "K:\\Ricardo\\Projetos NetBeans\\WealCash\\icons\\add.png";
        this.camIconeEditar   = "K:\\Ricardo\\Projetos NetBeans\\WealCash\\icons\\edit.png";
        this.camIconeExcluir  = "K:\\Ricardo\\Projetos NetBeans\\WealCash\\icons\\delete.png";
        this.camIconeCancelar = "K:\\Ricardo\\Projetos NetBeans\\WealCash\\icons\\cancel.png";
        this.camIconeLupa     = "K:\\Ricardo\\Projetos NetBeans\\WealCash\\icons\\lupa.png";
        break;
      }
      case "LINUX":
      {
        this.camIconePrograma = "/home/ricardo/java/WealCash/icons/money.png";
        this.camIconeAdd      = "/home/ricardo/java/WealCash/icons/add.png";
        this.camIconeEditar   = "/home/ricardo/java/WealCash/icons/edit.png";
        this.camIconeExcluir  = "/home/ricardo/java/WealCash/icons/delete.png";
        this.camIconeCancelar = "/home/ricardo/java/WealCash/icons/cancel.png";
        this.camIconeLupa     = "/home/ricardo/java/WealCash/icons/lupa.png";
      }
    }
  }
  private void obterImagemPrograma()
  {
    try
    {
      setIconImage( new ImageIcon( this.camIconePrograma ).getImage() );
    }
    catch( Exception ex )
    {
      System.out.println( "Exception ao setar imagem " + this.camIconePrograma );
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
    
    pnlPeriodo = new JPanel();
    pnlPeriodo.setLayout( null );
    pnlPeriodo.setBounds( 10, 10, 610, 70 );
    pnlPeriodo.setBorder( BorderFactory.createEtchedBorder() );
    
    lblPeriodo = new JLabel( "Período" );
    lblPeriodo.setBounds( 280, 5, 50, 21 );
    lblPeriodo.setHorizontalAlignment( JLabel.CENTER );
    lblPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    
    dtcPeriodoIni = new JDateChooser();
    dtcPeriodoIni.setBounds( 140, 31, 130, 21 );
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
    lblPeriodoA.setBounds( 295, 31, 15, 21 );
    lblPeriodoA.setFont( new Font( "Verdana", 0, 12 ) );
    lblPeriodoA.setHorizontalAlignment( JLabel.CENTER );
    
    dtcPeriodoFim = new JDateChooser();
    dtcPeriodoFim.setBounds( 330, 31, 130, 21 );
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
    
    pnlResumoCaixa = new JPanel();
    pnlResumoCaixa.setLayout( null );
    pnlResumoCaixa.setBounds( 10, 90, 610, 120 );
    pnlResumoCaixa.setBorder( BorderFactory.createEtchedBorder() );
    
    lblResumoCaixa = new JLabel( "Caixas" );
    lblResumoCaixa.setBounds( 280, 5, 50, 21 );
    lblResumoCaixa.setHorizontalAlignment( JLabel.CENTER );
    lblResumoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResNomeCaixa = new JLabel( "Escolha um caixa:" );
    lblResNomeCaixa.setBounds( 10, 45, 120, 21 );
    lblResNomeCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    cbxResCaixa = new JComboBox();
    cbxResCaixa.setBounds( 135, 45, 120, 21 );
    cbxResCaixa.setFont( new Font( "Verdana", 0, 12 ) );

    lblResSaldoCaixa = new JLabel( "Saldo: " );
    lblResSaldoCaixa.setBounds( 400, 15, 50, 21 ); // x = 450
    lblResSaldoCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResSaldoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValorSaldoCaixa = new JLabel( "R$ 0,00" );
    lblResValorSaldoCaixa.setBounds( 455, 15, 100, 21 );
    lblResValorSaldoCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResValorSaldoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResTotEntrCaixa = new JLabel( "Total de Entradas: " );
    lblResTotEntrCaixa.setBounds( 320, 45, 130, 21 ); // x = 450
    lblResTotEntrCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResTotEntrCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValTotEntrCaixa = new JLabel( "R$ 0,00" );
    lblResValTotEntrCaixa.setBounds( 455, 45, 100, 21 );
    lblResValTotEntrCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResValTotEntrCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResTotSaidaCaixa = new JLabel( "Total de Saídas: " );
    lblResTotSaidaCaixa.setBounds( 330, 80, 120, 21 ); // x = 450
    lblResTotSaidaCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResTotSaidaCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblResValTotSaidaCaixa = new JLabel( "R$ 0,00" );
    lblResValTotSaidaCaixa.setBounds( 455, 80, 100, 21 );
    lblResValTotSaidaCaixa.setHorizontalAlignment( JLabel.RIGHT );
    lblResValTotSaidaCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlMovimento = new JPanel();
    pnlMovimento.setLayout( null );
    pnlMovimento.setBounds( new Rectangle( 0, 0, 640, 420 ) );
    pnlMovimento.setName( "Movimento" );
    pnlMovimento.setBorder( BorderFactory.createEtchedBorder() );
    pnlMovimento.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblMovDescricao = new JLabel( "Descrição:" );
    lblMovDescricao.setBounds( 20, 10, 70, 21 );
    lblMovDescricao.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovDescricao.setHorizontalAlignment( JLabel.RIGHT );
    
    pnlCadastroMov = new JPanel();
    pnlCadastroMov.setLayout( null );
    pnlCadastroMov.setBounds( 10, 10, 280, 340 );
    pnlCadastroMov.setBorder( BorderFactory.createEtchedBorder() );
    pnlCadastroMov.setFont( new Font( "Verdana", 0, 12 ) );
    
    txfMovDescricao = new JTextField( "" );
    txfMovDescricao.setBounds( 100, 10, 150, 21 );
    txfMovDescricao.setFont( new Font( "Verdana", 0, 12 ) );
    txfMovDescricao.setToolTipText( "Descrição do lançamento" );
    
    lblMovData = new JLabel( "Data:" );
    lblMovData.setBounds( 50, 41, 40, 21 );
    lblMovData.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovData.setHorizontalAlignment( JLabel.RIGHT );
    
    dtcMovData = new JDateChooser();
    dtcMovData.setBounds( 100, 41, 100, 21 );
    dtcMovData.setFont( new Font( "Verdana", 0, 12 ) );
    dtcMovData.setToolTipText( "Data do lançameneto" );
    
    lblMovValor = new JLabel( "Valor:" );
    lblMovValor.setBounds( 40, 72, 50, 21 );
    lblMovValor.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovValor.setHorizontalAlignment( JLabel.RIGHT );
    
    dbfMovValor = new JDoubleField();
    dbfMovValor.setBounds( 100, 72, 80, 21 );
    dbfMovValor.setFont( new Font( "Verdana", 0, 12 ) );
    dbfMovValor.setToolTipText( "Valor do lançamento" );
    
    lblMovConta = new JLabel( "Conta:" );
    lblMovConta.setBounds( 40, 103, 50, 21 );
    lblMovConta.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovConta.setHorizontalAlignment( JLabel.RIGHT );
    
    cbxMovConta = new JComboBox();
    cbxMovConta.setBounds( 100, 103, 150, 21 );
    cbxMovConta.setFont( new Font( "Verdana", 0, 12 ) );
    cbxMovConta.setToolTipText( "Escolha uma conta" );
    
    lblMovCaixa = new JLabel( "Caixa:" );
    lblMovCaixa.setBounds( 40, 134, 50, 21 );
    lblMovCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovCaixa.setHorizontalAlignment( JLabel.RIGHT );
    
    cbxMovCaixa = new JComboBox();
    cbxMovCaixa.setBounds( 100, 134, 150, 21 );
    cbxMovCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    cbxMovCaixa.setToolTipText( "Escolha um caixa" );
    
    ckbMovPago = new JCheckBox( "Pago" );
    ckbMovPago.setSelected( true );
    ckbMovPago.setBounds( 100, 165, 60, 21 );
    ckbMovPago.setFont( new Font( "Verdana", 0, 12 ) );
    ckbMovPago.setToolTipText( "Deixe desmarcado para um lançamento futuro." );
    
    lblMovRecorrencia = new JLabel( "Recorrência:" );
    lblMovRecorrencia.setBounds( 10, 196, 80, 21 );
    lblMovRecorrencia.setFont( new Font( "Verdana", 0, 12 ) );
    lblMovRecorrencia.setHorizontalAlignment( JLabel.RIGHT );
    
    rbtMovRecorrenciaSim = new JRadioButton( "Sim" );
    rbtMovRecorrenciaSim.setSelected( false );
    rbtMovRecorrenciaSim.setBounds( 100, 196, 50, 21 );
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
    rbtMovRecorrenciaNao.setBounds( 170, 196, 50, 21 );
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
    lblRepetir.setBounds( 10, 237, 50, 21 );
    lblRepetir.setFont( new Font( "Verdana", 0, 12 ) );
    lblRepetir.setHorizontalAlignment( JLabel.RIGHT );
    
    itfNumVezes = new JIntegerField();
    itfNumVezes.setBounds( 65, 237, 40, 21 );
    itfNumVezes.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblVezes = new JLabel( "vezes" );
    lblVezes.setBounds( 110, 237, 40, 21 );
    lblVezes.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblACada = new JLabel( "A cada");
    lblACada.setBounds( 10, 268, 50, 21 );
    lblACada.setFont( new Font( "Verdana", 0, 12 ) );
    lblACada.setHorizontalAlignment( JLabel.CENTER );
    
    itfNumPeriodo = new JIntegerField();
    itfNumPeriodo.setBounds( 65, 268, 40, 21 );
    itfNumPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    
    cbxPeriodo = new JComboBox();
    cbxPeriodo.setBounds( 110, 268, 110, 21 );
    cbxPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
    for( EnumPeriodo periodo : EnumPeriodo.values() )
      cbxPeriodo.addItem( periodo );
    

    btnMovAdd = new JButton( new ImageIcon( this.camIconeAdd ) );
    btnMovAdd.setBounds( 30, 300, 30, 30 );
    btnMovAdd.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovAdd.setToolTipText( "Adicione um lançamento" );
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

    btnMovEditar = new JButton( new ImageIcon( this.camIconeEditar ) );
    btnMovEditar.setBounds( 90, 300, 30, 30 );
    btnMovEditar.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovEditar.setToolTipText( "Selecione um lançamento para editar." );
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

    btnMovExcluir = new JButton( new ImageIcon( this.camIconeExcluir ) );
    btnMovExcluir.setBounds( 150, 300, 30, 30 );
    btnMovExcluir.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovExcluir.setToolTipText( "Selecione um lançamento para excluir." );
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

    btnMovCancelar = new JButton( new ImageIcon( this.camIconeCancelar ) );
    btnMovCancelar.setBounds( 210, 300, 30, 30 );
    btnMovCancelar.setFont( new Font( "Verdana", 0, 12 ) );
    btnMovCancelar.setToolTipText( "Cancele a edição/seleção." );
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
    
    pnlLancamentoMov = new JPanel();
    pnlLancamentoMov.setLayout( null );
    pnlLancamentoMov.setBounds( 300, 10, 320, 340 );
    pnlLancamentoMov.setBorder( BorderFactory.createEtchedBorder() );
    pnlLancamentoMov.setFont( new Font( "Verdana", 0, 12 ) );
    
    // movimento model inicio
    String[] colunasMov = { "Data", "Descrição", "Valor", "Pago" };
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
    movimentoTable.getColumnModel().getColumn(0).setPreferredWidth(70);
    movimentoTable.getColumnModel().getColumn(1).setPreferredWidth(150);
    movimentoTable.getColumnModel().getColumn(2).setPreferredWidth(60);
    movimentoTable.getColumnModel().getColumn(3).setPreferredWidth(40);
    
    pnlLancamentoMov.setLayout( new BorderLayout() );
    pnlLancamentoMov.add( new JScrollPane( movimentoTable ) );
    
    movimentoTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    // movimento model fim
    
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
    
    lblAddConta = new JLabel( "Cadastrar Contas:" );
    lblAddConta.setBounds( 10, 10, 200, 21 );
    lblAddConta.setName( "lblAddConta" );
    lblAddConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblNomeConta = new JLabel( "Nome:" );
    lblNomeConta.setBounds( 10, 40, 45, 21 );
    lblNomeConta.setName( "lblNomeConta" );
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
    

    btnAddConta = new JButton( new ImageIcon( this.camIconeAdd ) );
    btnAddConta.setBounds( 30, 103, 30, 30 );
    btnAddConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnAddConta.setToolTipText( "Adicione uma conta" );
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
    
    btnEditarConta = new JButton( new ImageIcon( this.camIconeEditar ) );
    btnEditarConta.setBounds( 90, 103, 30, 30 );
    btnEditarConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnEditarConta.setToolTipText( "Selecione uma conta para editar." );
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
    
    btnExcluirConta = new JButton( new ImageIcon( this.camIconeExcluir ) );
    btnExcluirConta.setBounds( 150, 103, 30, 30 );
    btnExcluirConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnExcluirConta.setToolTipText( "Selecione uma conta para excluir." );
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
    
    btnCancelarConta = new JButton( new ImageIcon( this.camIconeCancelar ) );
    btnCancelarConta.setBounds( 210, 103, 30, 30 );
    btnCancelarConta.setFont( new Font( "Verdana", 0, 12 ) );
    btnCancelarConta.setToolTipText( "Cancele a edição/seleção." );
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
    pnlContas.setBounds( 300, 20, 270, 100 );
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
    
    lblAddCaixa = new JLabel( "Cadastrar Caixas:" );
    lblAddCaixa.setBounds( 10, 10, 200, 21 );
    lblAddCaixa.setName( "lblAddCaixa" );
    lblAddCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblNomeCaixa = new JLabel( "Nome:" );
    lblNomeCaixa.setBounds( 45, 40, 45, 21 );
    lblNomeCaixa.setName( "lblNomeCaixa" );
    lblNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    lblNomeCaixa.setHorizontalAlignment( JLabel.RIGHT );
    
    txfNomeCaixa = new JTextField();
    txfNomeCaixa.setBounds( 95, 40, 150, 21 );
    txfNomeCaixa.setName( "txfNomeCaixa" );
    txfNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblSaldoInicial = new JLabel( "Saldo Incial:" );
    lblSaldoInicial.setBounds( 10, 70, 80, 21 );
    lblSaldoInicial.setName( "lblSaldoInicial" );
    lblSaldoInicial.setFont( new Font( "Verdana", 0, 12 ) );
    lblSaldoInicial.setHorizontalAlignment( JLabel.RIGHT );
    
    dbfSaldoInicialConta = new JDoubleField();
    dbfSaldoInicialConta.setBounds( 95, 70, 100, 21 );
    dbfSaldoInicialConta.setFont( new Font( "Verdana", 0, 12 ) );
    
    btnAddCaixa = new JButton( new ImageIcon( this.camIconeAdd ) );
    btnAddCaixa.setBounds( 30, 103, 30, 30 );
    btnAddCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnAddCaixa.setToolTipText( "Adicione um caixa" );
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
    
    btnEditarCaixa = new JButton( new ImageIcon( this.camIconeEditar ) );
    btnEditarCaixa.setBounds( 90, 103, 30, 30 );
    btnEditarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnEditarCaixa.setToolTipText( "Selecione um caixa para editar." );
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
    
    btnExcluirCaixa = new JButton( new ImageIcon( this.camIconeExcluir ) );
    btnExcluirCaixa.setBounds( 150, 103, 30, 30 );
    btnExcluirCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnExcluirCaixa.setToolTipText( "Selecione um caixa para excluir." );
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
    
    btnCancelarCaixa = new JButton( new ImageIcon( this.camIconeCancelar ) );
    btnCancelarCaixa.setBounds( 210, 103, 30, 30 );
    btnCancelarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
    btnCancelarCaixa.setToolTipText( "Cancele a edição/seleção." );
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
    pnlCaixas.setBounds( 300, 20, 270, 100 );
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
    
    btnPesquisarDir = new JButton( new ImageIcon( this.camIconeLupa ) );
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
    
    txfNomeBD = new JTextField( "WealCash.db" );
    txfNomeBD.setBounds( 85, 70, 120, 21 );
    txfNomeBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    pnlGerBD = new JPanel();
    pnlGerBD.setLayout( null );
    pnlGerBD.setBounds( 20, 160, 590, 90 );
    pnlGerBD.setBorder( BorderFactory.createEtchedBorder() );
    pnlGerBD.setFont( new Font( "Verdana", 0, 12 ) );
    
    lblGerBD = new JLabel( "Gerenciamento:" );
    lblGerBD.setBounds( 10, 10, 110, 21 );
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
    
    btnExportarBD = new JButton( "Exportar BD" );
    btnExportarBD.setBounds( 340, 40, 110, 21 );
    btnExportarBD.setFont( new Font( "Verdana", 0, 12 ) );
    btnExportarBD.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnExportarBD();
        }
      }
    );
    
    btnImportarBD = new JButton( "Importar BD" );
    btnImportarBD.setBounds( 460, 40, 110, 21 );
    btnImportarBD.setFont( new Font( "Verdana", 0, 12 ) );
    btnImportarBD.addActionListener
    (
      new ActionListener()
      {
        @Override
        public void actionPerformed( ActionEvent e )
        {
          AOCLICARbtnImportarBD();
        }
      }
    );
    
    // Adicao dos componentes ao frame
    this.getContentPane().add( pnlSuperior );
    this.getContentPane().add( tbpPainelAbas );
       tbpPainelAbas.add( pnlHome );
          pnlHome.add( pnlPeriodo );
             pnlPeriodo.add( lblPeriodo );
             pnlPeriodo.add( dtcPeriodoIni );
             pnlPeriodo.add( lblPeriodoA );
             pnlPeriodo.add( dtcPeriodoFim );
          pnlHome.add( pnlResumoCaixa );
             pnlResumoCaixa.add( lblResumoCaixa );
             pnlResumoCaixa.add( lblResNomeCaixa );
             pnlResumoCaixa.add( cbxResCaixa );
             pnlResumoCaixa.add( lblResSaldoCaixa );
             pnlResumoCaixa.add( lblResValorSaldoCaixa );
             pnlResumoCaixa.add( lblResTotEntrCaixa );
             pnlResumoCaixa.add( lblResValTotEntrCaixa );
             pnlResumoCaixa.add( lblResTotSaidaCaixa );
             pnlResumoCaixa.add( lblResValTotSaidaCaixa );
       tbpPainelAbas.add( pnlMovimento );
          pnlMovimento.add( pnlCadastroMov );
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
             pnlCadastroMov.add( btnMovCancelar );
          pnlMovimento.add( pnlLancamentoMov );
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
             pnlCadConta.add( btnCancelarConta );
             pnlCadConta.add( pnlContas );
          pnlCadastros.add( pnlCadCaixa );
             pnlCadCaixa.add( lblAddCaixa );
             pnlCadCaixa.add( lblNomeCaixa );
             pnlCadCaixa.add( txfNomeCaixa );
             pnlCadCaixa.add( lblSaldoInicial );
             pnlCadCaixa.add( dbfSaldoInicialConta );
             pnlCadCaixa.add( btnAddCaixa );
             pnlCadCaixa.add( btnEditarCaixa );
             pnlCadCaixa.add( btnExcluirCaixa );
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
             pnlGerBD.add( btnImportarBD );
             pnlGerBD.add( btnExportarBD );
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
      System.out.println( "Exception no metodo acessar()" );
      e.printStackTrace();
    }
  }
  public void selecionarAba( Integer indexParam )
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
    setDbfSaldoInicialConta( Double.NaN );
  }
  public void limparCamposLancamento()
  {
    setTxfMovDescricao( "" );
    setDtcMovData( null );
    setDbfMovValor( Double.NaN );
    setCbxMovConta( null );
    setCbxMovCaixa( null );
    setCkbMovPago( true );
    rbtMovRecorrenciaNao.setSelected( true );
    rbtMovRecorrenciaSim.setSelected( false );
    setModoBotoesMovimento( "NOVO" );
  }
  public void setModoBotoesConta( String modoParam )
  {
    switch( modoParam )
    {
      case "NOVO":
      {
        btnAddConta.setEnabled( true );
        btnEditarConta.setEnabled( false );
        btnExcluirConta.setEnabled( false );
        btnCancelarConta.setEnabled( false );
        break;
      }
      case "SELECIONADO":
      {
        btnAddConta.setEnabled( false );
        btnEditarConta.setEnabled( true );
        btnExcluirConta.setEnabled( true );
        btnCancelarConta.setEnabled( true );
        break;
      }
    }
  }
  public void setModoBotoesCaixa( String modoParam )
  {
    switch( modoParam )
    {
      case "NOVO":
      {
        btnAddCaixa.setEnabled( true );
        btnEditarCaixa.setEnabled( false );
        btnExcluirCaixa.setEnabled( false );
        btnCancelarCaixa.setEnabled( false );
        break;
      }
      case "SELECIONADO":
      {
        btnAddCaixa.setEnabled( false );
        btnEditarCaixa.setEnabled( true );
        btnExcluirCaixa.setEnabled( true );
        btnCancelarCaixa.setEnabled( true );
      }
    }
  }
  public void setModoBotoesMovimento( String modoParam )
  {
    switch( modoParam )
    {
      case "NOVO":
      {
        btnMovAdd.setEnabled( true );
        btnMovEditar.setEnabled( false );
        btnMovExcluir.setEnabled( false );
        btnMovCancelar.setEnabled( false );
        break;
      }
      case "SELECIONADO":
      {
        btnMovAdd.setEnabled( false );
        btnMovEditar.setEnabled( true );
        btnMovExcluir.setEnabled( true );
        btnMovCancelar.setEnabled( true );
      }
    }
  }
  public void AOCLICARbtnAddConta()
  {
    this.comando = "ADICIONAR_CONTA";
  }
  public void AOCLICARbtnAddCaixa()
  {
    this.comando = "ADICIONAR_CAIXA";
  }
  public void AOCLICARbtnEditarConta()
  {
    this.comando = "EDITAR_CONTA";
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
      this.comando = "CARREGAR_CONTA";
  }
  public void AOCLICARpnlCaixas( MouseEvent e )
  {
    if( e.getClickCount() == 1 )
      this.comando = "CARREGAR_CAIXA";
  }
  public void AOCLICARpnlMovimento( MouseEvent e )
  {
    if( e.getClickCount() == 1 )
      this.comando = "CARREGAR_LANCAMENTO";
  }
  public void AOCLICARbtnCancelarConta()
  {
    this.comando = "CANCELAR_CONTA";
  }
  public void AOCLICARbtnCancelarCaixa()
  {
    this.comando = "CANCELAR_CAIXA";
  }
  public void AOCLICARbtnMovCancelar()
  {
    this.comando = "CANCELAR_LANCAMENTO";
  }
  public void AOCLICARbtnMovExcluir()
  {
    this.comando = "EXCLUIR_LANCAMENTO";
  }
  public void AOCLICARbtnMovEditar()
  {
    this.comando = "EDITAR_LANCAMENTO";
  }
  public void AOCLICARbtnMovAdd()
  {
    this.comando = "ADICIONAR_LANCAMENTO";
  }
  public void AOCLICARbtnPesquisarDir()
  {
    this.comando = "PESQUISAR_DIR";
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
  public void AOCLICARbtnExportarBD()
  {
    this.comando = "EXPORTAR_BD";
  }
  public void AOCLICARbtnImportarBD()
  {
    this.comando = "IMPORTAR_BD";
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
    System.out.println( "Data lida: " + dtcMovData.getDate() );
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
  public void addConta( String nomeParam, String tipoParam )
  {
    Object[] dados = { nomeParam, tipoParam };
    contasModel.addRow(dados);
    // adiciona ao comboBox de movimento
    atualizarContaAdicionada( nomeParam );
  }
  public void addCaixa( String nomeParam, Double saldoParam )
  {
    Object[] dados = { nomeParam, saldoParam };
    caixasModel.addRow( dados );
    // adiciona ao comboBox de movimento
    atualizarCaixaAdicionado( nomeParam );
  }
  public void addLancamento( Lancamento lancamentoParam )
  {
    DateFormat f = new SimpleDateFormat( "dd/MM/yyyy" );
    
    Object[] dados =
    {
      f.format( lancamentoParam.getDataEmissao()),
      lancamentoParam.getDescricao(),
      lancamentoParam.getValor(),
      'S'
    };
  
    movimentoModel.addRow( dados );
  }
  public String[] getConta()
  {
    String[] dados = { "", "" };
    dados[0] = (String) contasModel.getValueAt(contasTable.getSelectedRow(), 0 );
    dados[1] = (String) contasModel.getValueAt(contasTable.getSelectedRow(), 1 );
    return( dados );
  }
  public Object[] getCaixa()
  {
    Object[] dados = { "", Double.NaN };    
    dados[0] = (String) caixasModel.getValueAt(caixasTable.getSelectedRow(), 0 );
    dados[1] = (Double) caixasModel.getValueAt(caixasTable.getSelectedRow(), 1 );
    return( dados );
  }
  public int getContaSelecionada()
  {
    return( contasTable.getSelectedRow() );
  }
  public int getCaixaSelecionado()
  {
    return( caixasTable.getSelectedRow() );
  }
  public String removeConta( int linha )
  {
    if( contasModel.getRowCount() > 0 )
    {
      String conta = (String) contasModel.getValueAt( linha, 0 );
      contasModel.removeRow(linha);
      atualizarContaRemovida(conta);
      return( conta );
    }
    return( null );
  }
  public String removeCaixa( int linha )
  {
    if( caixasModel.getRowCount() > 0 )
    {
      String caixa = (String) caixasModel.getValueAt( linha, 0 );
      caixasModel.removeRow(linha);
      atualizarCaixaRemovido( caixa );
      return( caixa );
    }
    return( null );
  }
  public void substituirConta( int linhaParam, String nomeParam, String tipoParam )
  {
    contasModel.removeRow( linhaParam );
    Object[] dados = { nomeParam, tipoParam };
    contasModel.addRow(dados);
  }
  public void substituirCaixa( int linhaParam, String nomeParam, Double saldoParam )
  {
    caixasModel.removeRow( linhaParam );
    Object[] dados = { nomeParam, saldoParam };
    caixasModel.addRow(dados);
  }
  public void atualizarPainelContas()
  {
    contasTable.repaint();
  }
  public void atualizarPainelCaixas()
  {
    caixasTable.repaint();
  }
  public void atualizarContaRemovida( String conta )
  {
    JComboBox[] combos = {cbxMovConta};
    
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
  }
  public void atualizarCaixaAdicionado( String caixa )
  {
    cbxMovCaixa.addItem( caixa );
    cbxResCaixa.addItem( caixa );
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
  public Double getDbfSaldoInicialConta()
  {
    return( dbfSaldoInicialConta.getValue() );
  }
  public void setDbfSaldoInicialConta( Double valorParam )
  {
    dbfSaldoInicialConta.setValue( valorParam );
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
  public void limparModel( DefaultTableModel model )
  {
    for( int i=0; i<model.getRowCount(); i++ )
      model.removeRow( i );
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
    j[2] = cbxPeriodo;
    j[3] = cbxResCaixa;
    j[4] = cbxTipoConta;
    
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
  public String getOS()
  {
    return( this.OS );
  }
}