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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Tela extends JFrame {
	private String comando; /**< Contém o comando a ser executado, disparado por eventos */
	private FrConsole console;

	private JPanel pnlSuperior;
	private JLabel lblPeriodo;
	private JButton btnMenosPeriodo;
	private JDateChooser dtcPeriodoIni;
	private JLabel lblPeriodoA;
	private JDateChooser dtcPeriodoFim;
	private JButton btnMaisPeriodo;
	private JTabbedPane tbpPainelAbas;
	private JPanel pnlHome;
	private JPanel pnlResumoCaixa;
	private JLabel lblResumoCaixa;
	private JLabel lblResNomeCaixa;
	private JComboBox cbxResCaixa;
	private JButton btnResCaixa;
	private JLabel lblResSaldoCaixa;
	private JTextField txfResValorSaldoCaixa;
	private JLabel lblResSaldoCaixaLimite;
	private JTextField txfResSaldoCaixaLimite;
	private JLabel lblResTotEntrCaixa;
	private JTextField txfResValTotEntrCaixa;
	private JLabel lblResTotSaidaCaixa;
	private JTextField txfResValTotSaidaCaixa;
	private JLabel lblResumoCaixaProvisao;
	private JCheckBox ckbResumoCaixa;
	private ChartPanel pnlGrafico;
	private JPanel pnlResumoConta;
	private JLabel lblResumoConta;
	private JLabel lblResNomeConta;
	private JLabel lblResumoContaProvisao;
	private JComboBox cbxResConta;
	private JButton btnResConta;
	private JCheckBox ckbResumoConta;
	private JLabel lblResMovContaPc;
	private JTextField txfResValorMovContaPc;
	private JLabel lblResMovContaRs;
	private JTextField txfResValorMovContaRs;
	private JLabel lblResMovContaAno;
	private JTextField txfResValorMovContaAno;
	private JPanel pnlMovimento;
	private JTabbedPane tbpMovimento;
	private JPanel pnlCadastroMov;
	private JLabel lblMovDescricao;
	private JTextField txfMovDescricao;
	private JLabel lblMovData;
	private JDateChooser dtcMovData;
	private JLabel lblInfoData;
	private JLabel lblMovValor;
	private JDoubleField dbfMovValor;
	private JLabel lblMovConta;
	private JComboBox cbxMovConta;
	private JLabel lblMovCaixa;
	private JComboBox cbxMovCaixa;
	private JCheckBox ckbMovPago;
	private JLabel lblMovRecorrencia;
	private JRadioButton rbtMovRecorrenciaSim;
	private JRadioButton rbtMovRecorrenciaNao;
	private JLabel lblRepetir;
	private JIntegerField itfNumVezes;
	private JLabel lblVezes;
	private JLabel lblACada;
	private JIntegerField itfNumPeriodo;
	private JComboBox cbxPeriodo;
	private JButton btnMovAdd;
	private JButton btnMovEditar;
	private JButton btnMovExcluir;
	private JButton btnMovCancelar;
	private JButton btnMovConfirmar;
	private JPanel pnlTransferencia;
	private JButton btnTransfAdd;
	private JButton btnTransfCancelar;
	private JButton btnTransfConfirmar;
	private JLabel lblTransfCaixaOrigem;
	private JComboBox cbxTransfCaixaOrigem;
	private JLabel lblTransfCaixaDestino;
	private JComboBox cbxTransfCaixaDestino;
	private JLabel lblTransfDescricao;
	private JTextField txfTransfDescricao;
	private JLabel lblTransfData;
	private JDateChooser dtcTransfData;
	private JLabel lblTransfValor;
	private JDoubleField dbfTransfValor;
	private JCheckBox ckbTransf;
	private JPanel pnlLancamentoMov;
	private JTable movimentoTable;
	private LancamentoModel movimentoModel;
	private JPanel pnlExtratos;
	private JPanel pnlExtrato;
	private JLabel lblExtratoConta;
	private JComboBox cbxExtratoConta;
	private JLabel lblExtratoCaixa;
	private JComboBox cbxExtratoCaixa;
	private JLabel lblExtratoPeriodo;
	private JDateChooser dtcExtratoIni;
	private JLabel lblExtratoHifen;
	private JDateChooser dtcExtratoFim;
	private JButton btnExtrato;
	private JCheckBox ckbExtratoProvisao;
	private JTextPane txtExtrato;
	private JScrollPane scbExtratoConta;
	private JPanel pnlCadastros;
	private JPanel pnlCadConta;
	private JLabel lblAddConta;
	private JLabel lblNomeConta;
	private JTextField txfNomeConta;
	private JLabel lblTipoConta;
	private JComboBox cbxTipoConta;
	private JButton btnAddConta;
	private JButton btnEditarConta;
	private JButton btnExcluirConta;
	private JButton btnConfirmarConta;
	private JButton btnCancelarConta;
	private JPanel pnlContas;
	private JTable contasTable;
	private ContaModel contasModel;
	private JPanel pnlCadCaixa;
	private JLabel lblAddCaixa;
	private JLabel lblNomeCaixa;
	private JTextField txfNomeCaixa;
	private JLabel lblSaldoInicial;
	private JDoubleField dbfSaldoInicialCaixa;
	private JLabel lblTipoCaixa;
	private JRadioButton rbtTipoCaixaCartao;
	private JRadioButton rbtTipoCaixaNormal;
	private JLabel lblValorLimite;
	private JDoubleField dbfValorLimite;
	private JButton btnAddCaixa;
	private JButton btnEditarCaixa;
	private JButton btnExcluirCaixa;
	private JButton btnConfirmarCaixa;
	private JButton btnCancelarCaixa;
	private JPanel pnlCaixas;
	private JTable caixasTable;
	private CaixaModel caixasModel;
	private JPanel pnlConfig;
	private JPanel pnlBD;
	private JLabel lblBD;
	private JLabel lblDiretorioBD;
	private JTextField txfDiretorioBD;
	private JLabel lblNomeBD;
	private JTextField txfNomeBD;
	private JPanel pnlSobre;
	private JPanel pnlAutor;
	private JLabel lblNomeAutor;
	private JLabel lblEmailAutor;
	private JLabel lblContatoAutor;
	private JLabel lblPaginaOficial;
	private JPanel pnlTexto;

	public Tela( String nomeParam ) {
		super( nomeParam );
		setarLookAndFeel();
		initComponents();
		this.getContentPane().setLayout( null );
		this.setSize( 740, 510 );
		this.setResizable( true );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
		criarModelLancamentos();
		criarModelContas();
		criarModelCaixas();
		obterImagemPrograma();
		habilitarRecorrencia( false );
		criarListener();
		iniciarConsole();
		Mensagem.setFont();
	}

	private void setarLookAndFeel(){
		try{
			if( System.getProperty( "os.name").toUpperCase().contains(  "WIN" ) ) {
				UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
			}
		}
		catch( ClassNotFoundException ex ){
			ex.printStackTrace();
		}
		catch( InstantiationException ex ){
			ex.printStackTrace();
		}
		catch( IllegalAccessException ex ){
			ex.printStackTrace();
		}
		catch( UnsupportedLookAndFeelException ex ){
			ex.printStackTrace();
		}
	}

	private void criarModelLancamentos() {
		this.movimentoModel = new LancamentoModel();
		this.movimentoTable = new JTable();

		this.movimentoTable.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				if( e.getClickCount() == 1 ) {
					comando = "CARREGAR_LANCAMENTO_SELECIONADO";
				}
			}
		});
		this.movimentoTable.setShowGrid( false );
		this.movimentoTable.getTableHeader().setReorderingAllowed( false );
		this.movimentoTable.getTableHeader().setResizingAllowed( false );
		this.movimentoTable.setBorder( BorderFactory.createEmptyBorder() );
		this.movimentoTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		this.movimentoTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this.movimentoTable.setModel( this.movimentoModel );
		this.movimentoTable.setAutoCreateRowSorter( true );

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView( this.movimentoTable );
		scroll.setBorder( BorderFactory.createEmptyBorder() );

		this.pnlLancamentoMov.setLayout( new BorderLayout() );
		this.pnlLancamentoMov.add( scroll, BorderLayout.CENTER );

		this.movimentoTable.getColumnModel().getColumn(0).setPreferredWidth( 90 );  // data
		this.movimentoTable.getColumnModel().getColumn(1).setPreferredWidth( 30 );  // tipo
		this.movimentoTable.getColumnModel().getColumn(2).setPreferredWidth( 158 ); // descricao
		this.movimentoTable.getColumnModel().getColumn(3).setPreferredWidth( 90 );  // valor
		this.movimentoTable.getColumnModel().getColumn(4).setPreferredWidth( 50 );  // pago/recebido

		class MyCellRenderer extends DefaultTableCellRenderer {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if( row % 2 == 0 ) {
					comp.setBackground(new Color(202, 225, 255));
				}
				else {
					comp.setBackground(new Color(254, 254, 254));
				}

				if( isSelected ) {
					comp.setBackground(new Color(185, 235, 132));
				}

				if( value instanceof Double ) {
					((JLabel) comp).setText(new DecimalFormat("#,##0.00").format((Double) value));
					((JLabel) comp).setHorizontalAlignment(SwingConstants.RIGHT);
				}
				else {
				  ((JLabel) comp).setHorizontalAlignment(SwingConstants.LEFT);
				}

				if( value instanceof Integer )
				{
				  ((JLabel) comp).setText( DateTools.formatDataIntToStringBR( (Integer) value ) );
				  ((JLabel) comp).setHorizontalAlignment(SwingConstants.LEFT);
				}

				return( comp );
			}
		}

		this.movimentoTable.setDefaultRenderer( BigDecimal.class, new MyCellRenderer() );
		this.movimentoTable.setDefaultRenderer( Double.class,     new MyCellRenderer() );
		this.movimentoTable.setDefaultRenderer( String.class,     new MyCellRenderer() );
		this.movimentoTable.setDefaultRenderer( Integer.class,    new MyCellRenderer() );
		this.movimentoTable.setDefaultRenderer( Object.class,     new MyCellRenderer() );
	}

	private void criarModelContas() {
		this.contasModel = new ContaModel();
		this.contasTable = new JTable();
		this.contasTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				if( e.getClickCount() == 1 ) {
					comando = "CARREGAR_CONTA_SELECIONADA";
				}
			}
		});

		this.contasTable.setShowGrid( false );
		this.contasTable.getTableHeader().setReorderingAllowed( false );
		this.contasTable.getTableHeader().setResizingAllowed( false );
		this.contasTable.setAutoCreateRowSorter( true );
		this.contasTable.setBorder( BorderFactory.createEmptyBorder() );
		this.contasTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		this.contasTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this.contasTable.setModel( this.contasModel );

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView( this.contasTable );
		scroll.setBorder( BorderFactory.createEmptyBorder() );

		this.pnlContas.setLayout( new BorderLayout() );
		this.pnlContas.add( scroll, BorderLayout.CENTER );

		this.contasTable.getColumnModel().getColumn( 0 ).setPreferredWidth( 208 ); // nome
		this.contasTable.getColumnModel().getColumn( 1 ).setPreferredWidth( 40 ); // tipo

		class MyRenderer extends DefaultTableCellRenderer {
			@Override
			public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
				Component comp = super.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, column);

				if( row % 2 == 0 ) {
				  comp.setBackground( new Color( 202, 225, 255 ) );
				}
				else {
				  comp.setBackground( new Color( 254, 254, 254 ) );
				}

				if( isSelected ) {
				  comp.setBackground( new Color( 185, 235, 132 ) );
				}

				return( comp );
			}
		}

		this.contasTable.setDefaultRenderer( String.class, new MyRenderer() );
		this.contasTable.setDefaultRenderer( Object.class, new MyRenderer() );
	}

	private void criarModelCaixas() {
		this.caixasModel = new CaixaModel();
		this.caixasTable = new JTable();
		this.caixasTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				if( e.getClickCount() == 1 ) {
					comando = "CARREGAR_CAIXA_SELECIONADO";
				}
			}
		});

		this.caixasTable.setShowGrid( false );
		this.caixasTable.getTableHeader().setReorderingAllowed( false );
		this.caixasTable.getTableHeader().setResizingAllowed( false );
		this.caixasTable.setBorder( BorderFactory.createEmptyBorder() );
		this.caixasTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		this.caixasTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this.caixasTable.setModel( this.caixasModel );
		this.caixasTable.setAutoCreateRowSorter( true );

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView( this.caixasTable );
		scroll.setBorder( BorderFactory.createEmptyBorder() );

		this.pnlCaixas.setLayout( new BorderLayout() );
		this.pnlCaixas.add( scroll, BorderLayout.CENTER );

		this.caixasTable.getColumnModel().getColumn( 0 ).setPreferredWidth( 148 ); // nome
		this.caixasTable.getColumnModel().getColumn( 1 ).setPreferredWidth( 80 ); // saldo
		this.caixasTable.getColumnModel().getColumn( 2 ).setPreferredWidth( 80 ); // saldo

		class MyRenderer extends DefaultTableCellRenderer {
			@Override
			public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
				Component comp = super.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, column);

				if( row % 2 == 0 ) {
				  comp.setBackground( new Color( 202, 225, 255 ) );
				}
				else {
				  comp.setBackground( new Color( 254, 254, 254 ) );
				}

				if( isSelected ) {
				  comp.setBackground( new Color( 185, 235, 132 ) );
				}

				if( value instanceof Double ) {
				  ((JLabel) comp).setText( new DecimalFormat( "#,##0.00" ).format( (Double) value) );
				  ((JLabel) comp).setHorizontalAlignment( SwingConstants.RIGHT );
				}
				else {
				  ((JLabel) comp).setHorizontalAlignment( SwingConstants.LEFT );
				}

				return( comp );
			}
		}

		this.caixasTable.setDefaultRenderer( String.class, new MyRenderer() );
		this.caixasTable.setDefaultRenderer( Object.class, new MyRenderer() );
		this.caixasTable.setDefaultRenderer( Double.class, new MyRenderer() );
	}

	private void obterImagemPrograma() {
		try {
			setIconImage( new ImageIcon( Tela.class.getResource( "money.png" ) ).getImage() );
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	private void initComponents() {
		this.comando = "";

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized( ComponentEvent e ) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				pnlSuperior.setBounds(0, 0, getSize().width, 60);
				tbpPainelAbas.setBounds( 0, 60, getSize().width, (screenSize.height - 60) );
			}
		});

		this.pnlSuperior = new JPanel();
		this.pnlSuperior.setBounds( new Rectangle( 0, 0, 640 , 60 ) );
		this.pnlSuperior.setEnabled( true );
		this.pnlSuperior.setLayout( null );

		this.tbpPainelAbas = new JTabbedPane();
		this.tbpPainelAbas.setBounds( 0,60, 640, 420 );
		this.tbpPainelAbas.setBorder( BorderFactory.createEtchedBorder() );
		this.tbpPainelAbas.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlHome = new JPanel();
		this.pnlHome.setLayout( null );
		this.pnlHome.setBounds( new Rectangle( 0, 0, 640, 420 ) );
		this.pnlHome.setName( "Home" );
		this.pnlHome.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlHome.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblPeriodo = new JLabel( "Período:" );
		this.lblPeriodo.setBounds( 80, 20, 60, 21 );
		this.lblPeriodo.setHorizontalAlignment( JLabel.RIGHT );
		this.lblPeriodo.setFont( new Font( "Verdana", 0, 12 ) );

		this.btnMenosPeriodo = new JButton( new ImageIcon( getClass().getResource( "seta_e.png" ) ) );
		this.btnMenosPeriodo.setBounds( 145, 20, 45, 21 );
		this.btnMenosPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMenosPeriodo.setFocusable( false );
		this.btnMenosPeriodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "PERIODO_MENOS";
			}
		});

		this.dtcPeriodoIni = new JDateChooser();
		this.dtcPeriodoIni.setBounds( 200, 20, 130, 21 );
		this.dtcPeriodoIni.setFont( new Font( "Verdana", 0, 12 ) );
		this.dtcPeriodoIni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "ATUALIZAR_PERIODO";
			}
		});

		this.lblPeriodoA = new JLabel( "à" );
		this.lblPeriodoA.setBounds( 340, 20, 15, 21 );
		this.lblPeriodoA.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblPeriodoA.setHorizontalAlignment( JLabel.CENTER );

		this.dtcPeriodoFim = new JDateChooser();
		this.dtcPeriodoFim.setBounds( 365, 20, 130, 21 );
		this.dtcPeriodoFim.setFont( new Font( "Verdana", 0, 12 ) );
		this.dtcPeriodoFim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "ATUALIZAR_PERIODO";
			}
		});

		this.btnMaisPeriodo = new JButton( new ImageIcon( getClass().getResource( "seta_d.png" ) ) );
		this.btnMaisPeriodo.setBounds( 500, 20, 45, 21 );
		this.btnMaisPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMaisPeriodo.setFocusable( false );
		this.btnMaisPeriodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "PERIODO_MAIS";
			}
		});

		this.pnlResumoCaixa = new JPanel();
		this.pnlResumoCaixa.setLayout( null );
		this.pnlResumoCaixa.setBounds( 10, 10, 710, 240 );
		this.pnlResumoCaixa.setBorder( BorderFactory.createEtchedBorder() );

		this.lblResumoCaixa = new JLabel( "Resumo Caixa (no Período)" );
		this.lblResumoCaixa.setBounds( 265, 5, 180, 21 );
		this.lblResumoCaixa.setHorizontalAlignment( JLabel.CENTER );
		this.lblResumoCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblResNomeCaixa = new JLabel( "Caixa:" );
		this.lblResNomeCaixa.setBounds( 10, 35, 120, 21 );
		this.lblResNomeCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.cbxResCaixa = new JComboBox();
		this.cbxResCaixa.setBounds( 135, 35, 170, 21 );
		this.cbxResCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.btnResCaixa = new JButton();
		this.btnResCaixa.setIcon( new ImageIcon( Tela.class.getResource( "atualizar.png" ) ) );
		this.btnResCaixa.setFocusable( false );
		this.btnResCaixa.setBounds( 310, 30, 30, 30 );
		this.btnResCaixa.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "RESUMO_CAIXA";
			}
		});

		this.lblResSaldoCaixa = new JLabel( "Saldo:" );
		this.lblResSaldoCaixa.setBounds( 100, 66, 50, 21 ); // x = 130
		this.lblResSaldoCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResSaldoCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResValorSaldoCaixa = new JTextField( ValueTools.formatToField( 0.0 ) );
		this.txfResValorSaldoCaixa.setBounds( 155, 66, 120, 21 );
		this.txfResValorSaldoCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResValorSaldoCaixa.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResValorSaldoCaixa.setEditable( false );

		this.lblResSaldoCaixaLimite = new JLabel( "Saldo (C/Limite):" );
		this.lblResSaldoCaixaLimite.setBounds( 30, 97, 120, 21 );
		this.lblResSaldoCaixaLimite.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResSaldoCaixaLimite.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResSaldoCaixaLimite = new JTextField( ValueTools.formatToField( 0.0 ) );
		this.txfResSaldoCaixaLimite.setBounds( 155, 97, 120, 21 );
		this.txfResSaldoCaixaLimite.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResSaldoCaixaLimite.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResSaldoCaixaLimite.setEditable( false );

		this.lblResTotEntrCaixa = new JLabel( "Total de Entradas: " );
		this.lblResTotEntrCaixa.setBounds( 20, 128, 130, 21 ); // x = 130 - 97
		this.lblResTotEntrCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResTotEntrCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResValTotEntrCaixa = new JTextField( ValueTools.formatToField( 0.0 ) );
		this.txfResValTotEntrCaixa.setBounds( 155, 128, 120, 21 ); // 97
		this.txfResValTotEntrCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResValTotEntrCaixa.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResValTotEntrCaixa.setEditable( false );

		this.lblResTotSaidaCaixa = new JLabel( "Total de Saídas: " );
		this.lblResTotSaidaCaixa.setBounds( 30, 159, 120, 21 ); // x = 130 - 128
		this.lblResTotSaidaCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResTotSaidaCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResValTotSaidaCaixa = new JTextField( ValueTools.formatToField( 0.0 ) );
		this.txfResValTotSaidaCaixa.setBounds( 155, 159, 120, 21 ); // 128
		this.txfResValTotSaidaCaixa.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResValTotSaidaCaixa.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResValTotSaidaCaixa.setEditable( false );

		this.lblResumoCaixaProvisao = new JLabel( "Considerar provisões:" );
		this.lblResumoCaixaProvisao.setBounds( 10, 190, 140, 21 ); // x = 150 - 128
		this.lblResumoCaixaProvisao.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResumoCaixaProvisao.setFont( new Font( "Verdana", 0, 12 ) );

		this.ckbResumoCaixa = new JCheckBox();
		this.ckbResumoCaixa.setBounds( 155, 190, 170, 21 ); // 159
		this.ckbResumoCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlGrafico = new ChartPanel( null );
		this.pnlGrafico.setLayout( null );
		this.pnlGrafico.setBounds( 370, 35, 260, 160 );

		this.pnlResumoConta = new JPanel();
		this.pnlResumoConta.setLayout( null );
		this.pnlResumoConta.setBounds( 10, 260, 710, 120 );
		this.pnlResumoConta.setBorder( BorderFactory.createEtchedBorder() );

		this.lblResumoConta = new JLabel( "Resumo Conta (no Período)" );
		this.lblResumoConta.setBounds( 265, 5, 180, 21 );
		this.lblResumoConta.setHorizontalAlignment( JLabel.CENTER );
		this.lblResumoConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblResNomeConta = new JLabel( "Conta:" );
		this.lblResNomeConta.setBounds( 100, 45, 50, 21 );
		this.lblResNomeConta.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResNomeConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.cbxResConta = new JComboBox();
		this.cbxResConta.setBounds( 155, 45, 160, 21 );
		this.cbxResConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.btnResConta = new JButton( new ImageIcon( Tela.class.getResource( "atualizar.png" ) ) );
		this.btnResConta.setBounds( 320, 40, 30, 30 );
		this.btnResConta.setFocusable( false );
		this.btnResConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "RESUMO_CONTA";
			}
		});

		this.lblResumoContaProvisao = new JLabel( "Considerar provisões:");
		this.lblResumoContaProvisao.setBounds( 10, 76, 140, 21 );
		this.lblResumoContaProvisao.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResumoContaProvisao.setFont( new Font( "Verdana", 0, 12 ) );

		this.ckbResumoConta = new JCheckBox();
		this.ckbResumoConta.setBounds( 155, 76, 200, 21 );

		this.lblResMovContaPc = new JLabel( "Movim. no período (%):" );
		this.lblResMovContaPc.setBounds( 380, 25, 170, 21 ); // x = 450
		this.lblResMovContaPc.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResMovContaPc.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResValorMovContaPc = new JTextField( ValueTools.formatToFieldPerc( 0.0) );
		this.txfResValorMovContaPc.setBounds( 555, 25, 120, 21 );
		this.txfResValorMovContaPc.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResValorMovContaPc.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResValorMovContaPc.setEditable( false );

		this.lblResMovContaRs = new JLabel( "Movim. no período (R$):" );
		this.lblResMovContaRs.setBounds( 380, 55, 170, 21 ); // x = 450
		this.lblResMovContaRs.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResMovContaRs.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResValorMovContaRs = new JTextField( ValueTools.formatToField( 0.0 ) );
		this.txfResValorMovContaRs.setBounds( 555, 55, 120, 21 );
		this.txfResValorMovContaRs.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResValorMovContaRs.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResValorMovContaRs.setEditable( false );

		this.lblResMovContaAno = new JLabel( "Movimentou no ano:" );
		this.lblResMovContaAno.setBounds( 380, 85, 170, 21 ); // x = 450
		this.lblResMovContaAno.setHorizontalAlignment( JLabel.RIGHT );
		this.lblResMovContaAno.setFont( new Font( "Verdana", 0, 12 ) );

		this.txfResValorMovContaAno = new JTextField( ValueTools.formatToField( 0.0 ) );
		this.txfResValorMovContaAno.setBounds( 555, 85, 120, 21 );
		this.txfResValorMovContaAno.setHorizontalAlignment( JLabel.RIGHT );
		this.txfResValorMovContaAno.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txfResValorMovContaAno.setEditable( false );

		this.pnlMovimento = new JPanel();
		this.pnlMovimento.setLayout( null );
		this.pnlMovimento.setBounds( new Rectangle( 0, 0, 640, 420 ) );
		this.pnlMovimento.setName( "Movimentos" );
		this.pnlMovimento.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlMovimento.setFont( new Font( "Verdana", 0, 12 ) );

		this.tbpMovimento = new JTabbedPane();
		this.tbpMovimento.setBorder( BorderFactory.createEtchedBorder() );
		this.tbpMovimento.setBounds( 10, 10, 260, 370 );
		this.tbpMovimento.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlCadastroMov = new JPanel();
		this.pnlCadastroMov.setLayout( null );
		this.pnlCadastroMov.setBounds( 10, 10, 260, 370 );
		this.pnlCadastroMov.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlCadastroMov.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblMovDescricao = new JLabel( "Descrição:" );
		this.lblMovDescricao.setBounds( 15, 40, 70, 21 );
		this.lblMovDescricao.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblMovDescricao.setHorizontalAlignment( JLabel.RIGHT );

		this.txfMovDescricao = new JTextField();
		this.txfMovDescricao.setBounds( 90, 40, 150, 21 );
		this.txfMovDescricao.setFont( new Font( "Verdana", 0, 12 ) );
		this.txfMovDescricao.setToolTipText( "Descrição do lançamento" );
		this.txfMovDescricao.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent k ) {
				if( k.getKeyCode() == KeyEvent.VK_ENTER ) {
					mudarFoco( dtcMovData );
				}
			}
		});
		this.txfMovDescricao.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent f ) {
				txfMovDescricao.setBorder( BorderFactory.createLineBorder( Color.gray ) );
			}
		});

		this.lblMovData = new JLabel( "Data:" );
		this.lblMovData.setBounds( 45, 71, 40, 21 );
		this.lblMovData.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblMovData.setHorizontalAlignment( JLabel.RIGHT );

		this.dtcMovData = new JDateChooser();
		this.dtcMovData.setDate( new Date() );
		this.dtcMovData.setBounds( 90, 71, 100, 21 );
		this.dtcMovData.setFont( new Font( "Verdana", 0, 12 ) );
		this.dtcMovData.setToolTipText( "Data do lançamento" );
		this.dtcMovData.getField().addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent k ) {
				if( k.getKeyCode() == KeyEvent.VK_ENTER ) {
					mudarFoco( dbfMovValor );
				}
			}
		});

		this.lblInfoData = new JLabel( new ImageIcon( Tela.class.getResource( "infoiconn.png" ) ) );
		this.lblInfoData.setBounds( 200, 71, 21, 21 );
		String infoData =
			"<html>" +
			 "Informação:<br>" +
			 "Para lançamento à vista, preencher a data do pagamento.<br>" +
			 "Para lançamento provisionado, preencher a data de vencimento." +
			 "</html>";
		this.lblInfoData.setToolTipText( infoData );

		this.lblMovValor = new JLabel( "Valor:" );
		this.lblMovValor.setBounds( 35, 102, 50, 21 );
		this.lblMovValor.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblMovValor.setHorizontalAlignment( JLabel.RIGHT );

		this.dbfMovValor = new JDoubleField();
		this.dbfMovValor.setBounds( 90, 102, 120, 21 );
		this.dbfMovValor.setFont( new Font( "Monospaced", 0, 12 ) );
		this.dbfMovValor.setToolTipText( "Valor do lançamento" );
		this.dbfMovValor.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent k ) {
				if( k.getKeyCode() == KeyEvent.VK_ENTER ) {
					mudarFoco( cbxMovConta );
				}
			}
		});

		this.lblMovConta = new JLabel( "Conta:" );
		this.lblMovConta.setBounds( 35, 133, 50, 21 );
		this.lblMovConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblMovConta.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxMovConta = new JComboBox();
		this.cbxMovConta.setBounds( 90, 133, 150, 21 );
		this.cbxMovConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.cbxMovConta.setToolTipText( "Escolha uma conta" );
		this.cbxMovConta.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent k ) {
				if( k.getKeyCode() == KeyEvent.VK_ENTER ) {
					mudarFoco( cbxMovCaixa );
				}
			}
		});
		this.cbxMovConta.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent f ) {
				cbxMovConta.setBorder( BorderFactory.createLineBorder( Color.gray ) );
			}
		});

		this.lblMovCaixa = new JLabel( "Caixa:" );
		this.lblMovCaixa.setBounds( 35, 164, 50, 21 );
		this.lblMovCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblMovCaixa.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxMovCaixa = new JComboBox();
		this.cbxMovCaixa.setBounds( 90, 164, 150, 21 );
		this.cbxMovCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.cbxMovCaixa.setToolTipText( "Escolha um caixa" );
		this.cbxMovCaixa.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent k ) {
				if( k.getKeyCode() == KeyEvent.VK_ENTER ) {
					mudarFoco( ckbMovPago );
				}
			}
		});
		this.cbxMovCaixa.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent f ) {
				cbxMovCaixa.setBorder( BorderFactory.createLineBorder( Color.gray ) );
			}
		});

		this.ckbMovPago = new JCheckBox( "Pago/Recebido" );
		this.ckbMovPago.setSelected( false );
		this.ckbMovPago.setBounds( 86, 192, 130, 21 );
		this.ckbMovPago.setFont( new Font( "Verdana", 0, 12 ) );
		this.ckbMovPago.setToolTipText( "Desmarcar para um lançamento futuro" );
		this.ckbMovPago.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent k ) {
				if( k.getKeyCode() == KeyEvent.VK_ENTER ) {
					mudarFoco( rbtMovRecorrenciaNao );
				}
			}
		});

		this.lblMovRecorrencia = new JLabel( "Recorrência:" );
		this.lblMovRecorrencia.setBounds( 5, 216, 80, 21 );
		this.lblMovRecorrencia.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblMovRecorrencia.setHorizontalAlignment( JLabel.RIGHT );

		this.rbtMovRecorrenciaSim = new JRadioButton( "Sim" );
		this.rbtMovRecorrenciaSim.setSelected( false );
		this.rbtMovRecorrenciaSim.setBounds( 86, 216, 50, 21 );
		this.rbtMovRecorrenciaSim.setFont( new Font( "Verdana", 0, 12 ) );
		this.rbtMovRecorrenciaSim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( rbtMovRecorrenciaSim.isSelected() )
					habilitarRecorrencia( rbtMovRecorrenciaSim.isSelected() );
			}
		});

		this.rbtMovRecorrenciaNao = new JRadioButton( "Não" );
		this.rbtMovRecorrenciaNao.setSelected( true );
		this.rbtMovRecorrenciaNao.setBounds( 140, 216, 50, 21 );
		this.rbtMovRecorrenciaNao.setFont( new Font( "Verdana", 0, 12 ) );
		this.rbtMovRecorrenciaNao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				if( rbtMovRecorrenciaNao.isSelected() ) {
					habilitarRecorrencia( !rbtMovRecorrenciaNao.isSelected() );
				}
			}
		});
		this.rbtMovRecorrenciaNao.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {
				if( e.getKeyCode() == KeyEvent.VK_ENTER ) {
					btnMovConfirmar.doClick();
				}
			}
		});

		ButtonGroup grupoMov = new ButtonGroup();
		grupoMov.add( this.rbtMovRecorrenciaSim );
		grupoMov.add( this.rbtMovRecorrenciaNao );

		this.lblRepetir = new JLabel( "Repetir" );
		this.lblRepetir.setBounds( 10, 247, 50, 21 );
		this.lblRepetir.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblRepetir.setHorizontalAlignment( JLabel.RIGHT );

		this.itfNumVezes = new JIntegerField();
		this.itfNumVezes.setBounds( 65, 247, 40, 21 );

		this.lblVezes = new JLabel( "vezes" );
		this.lblVezes.setBounds( 110, 247, 40, 21 );
		this.lblVezes.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblACada = new JLabel( "A cada");
		this.lblACada.setBounds( 10, 278, 50, 21 );
		this.lblACada.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblACada.setHorizontalAlignment( JLabel.CENTER );

		this.itfNumPeriodo = new JIntegerField();
		this.itfNumPeriodo.setBounds( 65, 278, 40, 21 );

		this.cbxPeriodo = new JComboBox();
		this.cbxPeriodo.setBounds( 110, 278, 110, 21 );
		this.cbxPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
		
		for( EnumPeriodo periodo : EnumPeriodo.values() ) {
			this.cbxPeriodo.addItem( periodo );
		}

		this.btnMovAdd = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
		this.btnMovAdd.setBounds( 5, 5, 30, 30 );
		this.btnMovAdd.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMovAdd.setToolTipText( "Novo" );
		this.btnMovAdd.setFocusable( false );
		this.btnMovAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "INCLUIR_LANCAMENTO";
			}
		});

		this.btnMovEditar = new JButton( new ImageIcon( Tela.class.getResource( "editar.png" ) ) );
		this.btnMovEditar.setBounds( 35, 5, 30, 30 );
		this.btnMovEditar.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMovEditar.setToolTipText( "Alterar" );
		this.btnMovEditar.setFocusable( false );
		this.btnMovEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "ALTERAR_LANCAMENTO";
			}
		});

		this.btnMovExcluir = new JButton( new ImageIcon( Tela.class.getResource( "deletar.gif" ) ) );
		this.btnMovExcluir.setBounds( 65, 5, 30, 30 );
		this.btnMovExcluir.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMovExcluir.setToolTipText( "Excluir" );
		this.btnMovExcluir.setFocusable( false );
		this.btnMovExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "EXCLUIR_LANCAMENTO";
			}
		});

		this.btnMovConfirmar = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
		this.btnMovConfirmar.setBounds( 95, 5, 30, 30 );
		this.btnMovConfirmar.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMovConfirmar.setToolTipText( "Confirmar" );
		this.btnMovConfirmar.setFocusable( false );
		this.btnMovConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CONFIRMAR_LANCAMENTO";
			}
		});

		this.btnMovCancelar = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
		this.btnMovCancelar.setBounds( 125, 5, 30, 30 );
		this.btnMovCancelar.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnMovCancelar.setToolTipText( "Cancelar" );
		this.btnMovCancelar.setFocusable( false );
		this.btnMovCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CANCELAR_LANCAMENTO_SELECIONADO";
			}
		});

		this.pnlTransferencia = new JPanel();
		this.pnlTransferencia.setLayout( null );

		this.btnTransfAdd = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
		this.btnTransfAdd.setBounds( 5, 5, 30, 30 );
		this.btnTransfAdd.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnTransfAdd.setToolTipText( "Novo" );
		this.btnTransfAdd.setFocusable( false );
		this.btnTransfAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "INCLUIR_TRANSFERENCIA";
			}
		});

		this.btnTransfConfirmar = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
		this.btnTransfConfirmar.setBounds( 35, 5, 30, 30 );
		this.btnTransfConfirmar.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnTransfConfirmar.setToolTipText( "Confirmar" );
		this.btnTransfConfirmar.setFocusable( false );
		this.btnTransfConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CONFIRMAR_TRANSFERENCIA";
			}
		});

		this.btnTransfCancelar = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
		this.btnTransfCancelar.setBounds( 65, 5, 30, 30 );
		this.btnTransfCancelar.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnTransfCancelar.setToolTipText( "Cancelar" );
		this.btnTransfCancelar.setFocusable( false );
		this.btnTransfCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CANCELAR_TRANSFERENCIA";
			}
		});

		this.lblTransfCaixaOrigem = new JLabel( "Origem:" );
		this.lblTransfCaixaOrigem.setBounds( 15, 40, 70, 21 );
		this.lblTransfCaixaOrigem.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTransfCaixaOrigem.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxTransfCaixaOrigem = new JComboBox();
		this.cbxTransfCaixaOrigem.setBounds( 90, 40, 150, 21 );
		this.cbxTransfCaixaOrigem.setFont( new Font( "Verdana", 0, 12 ) );
		this.cbxTransfCaixaOrigem.setEditable( false );

		this.lblTransfCaixaDestino = new JLabel( "Destino:" );
		this.lblTransfCaixaDestino.setBounds( 25, 71, 60, 21 );
		this.lblTransfCaixaDestino.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTransfCaixaDestino.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxTransfCaixaDestino = new JComboBox();
		this.cbxTransfCaixaDestino.setBounds( 90, 71, 150, 21 );
		this.cbxTransfCaixaDestino.setFont( new Font( "Verdana", 0, 12 ) );
		this.cbxTransfCaixaDestino.setEditable( false );

		this.lblTransfDescricao = new JLabel( "Descrição:" );
		this.lblTransfDescricao.setBounds( 15, 102, 70, 21 );
		this.lblTransfDescricao.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTransfDescricao.setHorizontalAlignment( JLabel.RIGHT );

		this.txfTransfDescricao = new JTextField();
		this.txfTransfDescricao.setBounds( 90, 102, 150, 21 );
		this.txfTransfDescricao.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblTransfData = new JLabel( "Data:" );
		this.lblTransfData.setBounds( 25, 133, 60, 21 );
		this.lblTransfData.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTransfData.setHorizontalAlignment( JLabel.RIGHT );

		this.dtcTransfData = new JDateChooser();
		this.dtcTransfData.setBounds( 90, 133, 100, 21 );
		this.dtcTransfData.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblTransfValor = new JLabel( "Valor:" );
		this.lblTransfValor.setBounds( 45, 164, 40, 21 );
		this.lblTransfValor.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTransfValor.setHorizontalAlignment( JLabel.RIGHT );

		this.dbfTransfValor = new JDoubleField();
		this.dbfTransfValor.setBounds( 90, 164, 120, 21 );

		this.ckbTransf = new JCheckBox( "Transferido" );
		this.ckbTransf.setBounds( 86, 195, 120, 21 );
		this.ckbTransf.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlLancamentoMov = new JPanel();
		this.pnlLancamentoMov.setLayout( null );
		this.pnlLancamentoMov.setBounds( 280, 10, 440, 370 );
		this.pnlLancamentoMov.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlLancamentoMov.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlExtratos = new JPanel();
		this.pnlExtratos.setLayout( null );
		this.pnlExtratos.setBounds( new Rectangle( 0, 0, 640, 420 ) );
		this.pnlExtratos.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlExtratos.setFont( new Font( "Verdana", 0, 12 ) );
		this.pnlExtratos.setName( "Extratos" );

		this.pnlExtrato = new JPanel();
		this.pnlExtrato.setLayout( null );
		this.pnlExtrato.setBounds( new Rectangle( 10, 10, 710, 370 ) );
		this.pnlExtrato.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlExtrato.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblExtratoConta = new JLabel( "Conta:" );
		this.lblExtratoConta.setBounds( 60, 15, 45, 21 );
		this.lblExtratoConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblExtratoConta.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxExtratoConta = new JComboBox();
		this.cbxExtratoConta.setBounds( 110, 15, cbxMovConta.getWidth(), 21 );
		this.cbxExtratoConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblExtratoCaixa = new JLabel( "Caixa:" );
		this.lblExtratoCaixa.setBounds( 60, 40, 45, 21 );
		this.lblExtratoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblExtratoCaixa.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxExtratoCaixa = new JComboBox();
		this.cbxExtratoCaixa.setBounds( 110, 40, cbxMovConta.getWidth(), 21 );
		this.cbxExtratoCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblExtratoPeriodo = new JLabel( "Período: " );
		this.lblExtratoPeriodo.setBounds( 270, 15, 60, 21 );
		this.lblExtratoPeriodo.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblExtratoPeriodo.setHorizontalAlignment( JLabel.RIGHT );

		this.dtcExtratoIni = new JDateChooser();
		this.dtcExtratoIni.setBounds( 340, 15, 110, 21 );
		this.dtcExtratoIni.setFont( new Font( "Verdana", 0, 12 ) );
		this.dtcExtratoIni.setDate( new Date() );

		this.lblExtratoHifen = new JLabel( "-" );
		this.lblExtratoHifen.setBounds( 460, 15, 15, 21 );
		this.lblExtratoHifen.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblExtratoHifen.setHorizontalAlignment( JLabel.CENTER );

		this.dtcExtratoFim = new JDateChooser();
		this.dtcExtratoFim.setBounds( 485, 15, 110, 21 );
		this.dtcExtratoFim.setFont( new Font( "Verdana", 0, 12 ) );
		this.dtcExtratoFim.setDate( new Date() );

		this.btnExtrato = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
		this.btnExtrato.setBounds( 610, 10, 30, 30 );
		this.btnExtrato.setFocusable( false );
		this.btnExtrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "SOLICITAR_EXTRATO";
			}
		});

		this.ckbExtratoProvisao = new JCheckBox( " Considerar provisões" );
		this.ckbExtratoProvisao.setBounds( 270, 40, 200, 21 );
		this.ckbExtratoProvisao.setFont( new Font( "Verdana", 0, 12 ) );

		this.txtExtrato = new JTextPane();
		this.txtExtrato.setFont( new Font( "Monospaced", 0, 12 ) );
		this.txtExtrato.setEditable( false );
		this.txtExtrato.setBackground( pnlSuperior.getBackground() );

		this.scbExtratoConta = new JScrollPane( txtExtrato );
		this.scbExtratoConta.setBounds( 30, 70, 640, 260 );

		this.pnlCadastros = new JPanel();
		this.pnlCadastros.setLayout( null );
		this.pnlCadastros.setBounds( new Rectangle( 0, 0, 740, 420 ) );
		this.pnlCadastros.setName( "Cadastros" );
		this.pnlCadastros.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlCadastros.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlCadConta = new JPanel();
		this.pnlCadConta.setLayout( null );
		this.pnlCadConta.setBounds( new Rectangle( 20, 20, pnlCadastros.getWidth()-50, (pnlCadastros.getHeight()/2)-30 ) );
		this.pnlCadConta.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlCadConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblAddConta = new JLabel( "Contas Cadastradas:" );
		this.lblAddConta.setBounds( 350, 5, 150, 21 );
		this.lblAddConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblNomeConta = new JLabel( "Nome:" );
		this.lblNomeConta.setBounds( 10, 40, 45, 21 );
		this.lblNomeConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblNomeConta.setHorizontalAlignment( JLabel.RIGHT );

		this.txfNomeConta = new JTextField();
		this.txfNomeConta.setBounds( 60, 40, 150, 21 );
		this.txfNomeConta.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblTipoConta = new JLabel( "Tipo:" );
		this.lblTipoConta.setBounds( 10, 70, 45, 21 );
		this.lblTipoConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTipoConta.setHorizontalAlignment( JLabel.RIGHT );

		this.cbxTipoConta = new JComboBox();
		this.cbxTipoConta.setBounds( 60, 70, 100, 21 );
		this.cbxTipoConta.setFont( new Font( "Verdana", 0, 12 ) );
		
		for( EnumTipoConta tipo : EnumTipoConta.values() ) {
			cbxTipoConta.addItem( tipo );
		}

		this.btnAddConta = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
		this.btnAddConta.setBounds( 5, 5, 30, 30 );
		this.btnAddConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnAddConta.setToolTipText( "Novo" );
		this.btnAddConta.setFocusable( false );
		this.btnAddConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "INCLUIR_CONTA";
			}
		});

		this.btnEditarConta = new JButton( new ImageIcon( Tela.class.getResource( "editar.png" ) ) );
		this.btnEditarConta.setBounds( 35, 5, 30, 30 );
		this.btnEditarConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnEditarConta.setToolTipText( "Alterar" );
		this.btnEditarConta.setFocusable( false );
		this.btnEditarConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "ALTERAR_CONTA";
			}
		});

		this.btnExcluirConta = new JButton( new ImageIcon( Tela.class.getResource( "deletar.gif" ) ) );
		this.btnExcluirConta.setBounds( 65, 5, 30, 30 );
		this.btnExcluirConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnExcluirConta.setToolTipText( "Excluir" );
		this.btnExcluirConta.setFocusable( false );
		this.btnExcluirConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "EXCLUIR_CONTA";
			}
		});

		this.btnConfirmarConta = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
		this.btnConfirmarConta.setBounds( 95, 5, 30, 30 );
		this.btnConfirmarConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnConfirmarConta.setToolTipText( "Confirmar" );
		this.btnConfirmarConta.setFocusable( false );
		this.btnConfirmarConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CONFIRMAR_CONTA";
			}
		});

		this.btnCancelarConta = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
		this.btnCancelarConta.setBounds( 125, 5, 30, 30 );
		this.btnCancelarConta.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnCancelarConta.setToolTipText( "Cancelar" );
		this.btnCancelarConta.setFocusable( false );
		this.btnCancelarConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CANCELAR_CONTA";
			}
		});

		this.pnlContas = new JPanel();
		this.pnlContas.setBounds( 350, 30, 270, 130 );
		this.pnlContas.setBorder( BorderFactory.createEtchedBorder() );

		this.pnlCadCaixa = new JPanel();
		this.pnlCadCaixa.setLayout( null );
		this.pnlCadCaixa.setBounds( new Rectangle( 20, pnlCadConta.getHeight()+40, pnlCadastros.getWidth()-50, (pnlCadastros.getHeight()/2)-50 ) );
		this.pnlCadCaixa.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlCadCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblAddCaixa = new JLabel( "Caixas cadastrados:" );
		this.lblAddCaixa.setBounds( 350, 5, 150, 21 );
		this.lblAddCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblNomeCaixa = new JLabel( "Nome:" );
		this.lblNomeCaixa.setBounds( 10, 40, 45, 21 );
		this.lblNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblNomeCaixa.setHorizontalAlignment( JLabel.RIGHT );

		this.txfNomeCaixa = new JTextField();
		this.txfNomeCaixa.setBounds( 60, 40, 150, 21 );
		this.txfNomeCaixa.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblSaldoInicial = new JLabel( "Saldo:" );
		this.lblSaldoInicial.setBounds( 10, 70, 45, 21 );
		this.lblSaldoInicial.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblSaldoInicial.setHorizontalAlignment( JLabel.RIGHT );

		this.dbfSaldoInicialCaixa = new JDoubleField();
		this.dbfSaldoInicialCaixa.setBounds( 60, 70, 100, 21 );

		this.lblTipoCaixa = new JLabel( "Tipo:" );
		this.lblTipoCaixa.setBounds( 10, 100, 45, 21 );
		this.lblTipoCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblTipoCaixa.setHorizontalAlignment( JLabel.RIGHT );

		this.rbtTipoCaixaCartao = new JRadioButton( "Cartão/Banco" );
		this.rbtTipoCaixaCartao.setBounds( 60, 100, 120, 21 );
		this.rbtTipoCaixaCartao.setFont( new Font( "Verdana", 0, 12 ) );
		this.rbtTipoCaixaCartao.setToolTipText( "Tipo cartão de crédito ou conta bancária." );
		this.rbtTipoCaixaCartao.setSelected( false );
		this.rbtTipoCaixaCartao.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ae ) {
				dbfValorLimite.setEnabled( true );
			}
		});

		this.rbtTipoCaixaNormal = new JRadioButton( "Normal" );
		this.rbtTipoCaixaNormal.setBounds( 185, 100, 70, 21 );
		this.rbtTipoCaixaNormal.setFont( new Font( "Verdana", 0, 12 ) );
		this.rbtTipoCaixaNormal.setToolTipText( "Tipo caixa normal." );
		this.rbtTipoCaixaNormal.setSelected( true );
		this.rbtTipoCaixaNormal.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent ae ) {
				dbfValorLimite.setValue( 0.0 );
				dbfValorLimite.setEnabled( false );
			}
		});

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add( this.rbtTipoCaixaCartao );
		bgroup.add( this.rbtTipoCaixaNormal );

		this.lblValorLimite = new JLabel( "Limite:" );
		this.lblValorLimite.setBounds( 5, 130, 50, 21 );
		this.lblValorLimite.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblValorLimite.setHorizontalAlignment( JLabel.RIGHT );

		this.dbfValorLimite = new JDoubleField();
		this.dbfValorLimite.setBounds( 60, 130, 100, 21 );

		this.btnAddCaixa = new JButton( new ImageIcon( Tela.class.getResource( "incluir.png" ) ) );
		this.btnAddCaixa.setBounds( 5, 5, 30, 30 );
		this.btnAddCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnAddCaixa.setToolTipText( "Novo" );
		this.btnAddCaixa.setFocusable( false );
		this.btnAddCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "INCLUIR_CAIXA";
			}
		});

		this.btnEditarCaixa = new JButton( new ImageIcon( Tela.class.getResource( "editar.png" ) ) );
		this.btnEditarCaixa.setBounds( 35, 5, 30, 30 );
		this.btnEditarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnEditarCaixa.setToolTipText( "Alterar" );
		this.btnEditarCaixa.setFocusable( false );
		this.btnEditarCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "ALTERAR_CAIXA";
			}
		});

		this.btnExcluirCaixa = new JButton( new ImageIcon( Tela.class.getResource( "deletar.gif" ) ) );
		this.btnExcluirCaixa.setBounds( 65, 5, 30, 30 );
		this.btnExcluirCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnExcluirCaixa.setToolTipText( "Excluir" );
		this.btnExcluirCaixa.setFocusable( false );
		this.btnExcluirCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "EXCLUIR_CAIXA";
			}
		});

		this.btnConfirmarCaixa = new JButton( new ImageIcon( Tela.class.getResource( "confirmar.png" ) ) );
		this.btnConfirmarCaixa.setBounds( 95, 5, 30, 30 );
		this.btnConfirmarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnConfirmarCaixa.setToolTipText( "Confirmar" );
		this.btnConfirmarCaixa.setFocusable( false );
		this.btnConfirmarCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CONFIRMAR_CAIXA";
			}
		});

		this.btnCancelarCaixa = new JButton( new ImageIcon( Tela.class.getResource( "cancelar.png" ) ) );
		this.btnCancelarCaixa.setBounds( 125, 5, 30, 30 );
		this.btnCancelarCaixa.setFont( new Font( "Verdana", 0, 12 ) );
		this.btnCancelarCaixa.setToolTipText( "Cancelar" );
		this.btnCancelarCaixa.setFocusable( false );
		this.btnCancelarCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				comando = "CANCELAR_CAIXA";
			}
		});

		this.pnlCaixas = new JPanel();
		this.pnlCaixas.setBounds( 320, 30, 330, 100 );
		this.pnlCaixas.setBorder( BorderFactory.createEtchedBorder() );

		this.pnlConfig = new JPanel();
		this.pnlConfig.setLayout( null );
		this.pnlConfig.setBounds( new Rectangle( 0, 0, 740, 420 ) );
		this.pnlConfig.setName( "Configurações" );
		this.pnlConfig.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlConfig.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlBD = new JPanel();
		this.pnlBD.setLayout( null );
		this.pnlBD.setBounds( 20, 20, 690, 120 );
		this.pnlBD.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlBD.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblBD = new JLabel( "Banco de Dados" );
		this.lblBD.setBounds( 10, 10, 110, 21 );
		this.lblBD.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblDiretorioBD = new JLabel( "Diretório:" );
		this.lblDiretorioBD.setBounds( 20, 40, 60, 21 );
		this.lblDiretorioBD.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblDiretorioBD.setHorizontalAlignment( JLabel.RIGHT );

		String caminho;
		if( System.getProperty( "os.name" ).toUpperCase().contains( "WIN" ) ) {
			caminho = new File( System.getenv( "APPDATA" ) ).getAbsolutePath() + "\\";
		}
		else {
			caminho = System.getProperty( "user.home" ) + "/.MyGoodMoney/";
		}
		this.txfDiretorioBD = new JTextField( caminho );
		this.txfDiretorioBD.setBounds( 85, 40, 600, 21 );
		this.txfDiretorioBD.setFont( new Font( "Verdana", 0, 12 ) );
		this.txfDiretorioBD.setEditable( false );

		this.lblNomeBD = new JLabel( "Nome:" );
		this.lblNomeBD.setBounds( 30, 70, 50, 21 );
		this.lblNomeBD.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblNomeBD.setHorizontalAlignment( JLabel.RIGHT );

		this.txfNomeBD = new JTextField( "MyGoodMoney.db" );
		this.txfNomeBD.setBounds( 85, 70, 120, 21 );
		this.txfNomeBD.setFont( new Font( "Verdana", 0, 12 ) );
		this.txfNomeBD.setEditable( false );

		this.pnlSobre = new JPanel();
		this.pnlSobre.setLayout( null );
		this.pnlSobre.setBounds( new Rectangle( 0, 0, 740, 420 ) );
		this.pnlSobre.setName( "Sobre" );
		this.pnlSobre.setBorder( BorderFactory.createEtchedBorder() );
		this.pnlSobre.setFont( new Font( "Verdana", 0, 12 ) );

		this.pnlAutor = new JPanel();
		this.pnlAutor.setLayout( null );
		this.pnlAutor.setBounds( 20, 20, 690, 120 );
		this.pnlAutor.setBorder( BorderFactory.createEtchedBorder() );

		this.lblNomeAutor = new JLabel( "Autor: Ricardo Montania Prado de Campos" );
		this.lblNomeAutor.setBounds( 20, 10, 400, 21 );
		this.lblNomeAutor.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblEmailAutor = new JLabel( "E-mail: ricardo@linuxafundo.com.br" );
		this.lblEmailAutor.setBounds( 20, 35, 400, 21 );
		this.lblEmailAutor.setFont( new Font( "Verdana", 0, 12 ) );

		this.lblContatoAutor = new JLabel( "<HTML>Contato: <FONT color=\"#000099\"><U>http://www.linuxafundo.com.br/contato</U></FONT></HTML>" );
		this.lblContatoAutor.setBounds( 20, 60, 310, 21 );
		this.lblContatoAutor.setToolTipText( "Clique para abrir" );
		this.lblContatoAutor.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblContatoAutor.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				abrirLink( "contato" );
			}
			@Override
			public void mouseEntered(MouseEvent e){
				setCursor( new Cursor( Cursor.HAND_CURSOR ) );
			}
			@Override
			public void mouseExited(MouseEvent e){
				setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
			}
		});

		this.lblPaginaOficial = new JLabel( "<HTML>Site oficial: <FONT color=\"#000099\"><U>http://www.linuxafundo.com.br/projects/mygoodmoney</U></FONT></HTML>" );
		this.lblPaginaOficial.setBounds( 20, 85, 470, 21 );
		this.lblPaginaOficial.setToolTipText( "Clique para abrir" );
		this.lblPaginaOficial.setFont( new Font( "Verdana", 0, 12 ) );
		this.lblPaginaOficial.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				abrirLink( "mygoodmoney" );
			}
			@Override
			public void mouseEntered(MouseEvent e){
				setCursor( new Cursor( Cursor.HAND_CURSOR ) );
			}
			@Override
			public void mouseExited(MouseEvent e){
				setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
			}
		});

		this.pnlTexto = new JPanel();
		this.pnlTexto.setLayout( null );
		this.pnlTexto.setBounds( 20, 160, 690, 220 );
		this.pnlTexto.setBorder( BorderFactory.createEtchedBorder() );

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
			"damente todas as movimentaçõees referente àquela conta num período\n" +
			"selecionado.\n\n" +
			"COMO USAR:\n\n" +
			"   Antes de iniciar o uso do programa, é necessária a criação de um\n" +
			"sistema de armazenamento para os dados do programa. Isso é feito automa-\n" +
			"ticamente quando o programa é iniciado e nenhum banco de dados foi localizado.\n" +
			"  O uso, incluindo exemplos, está demonstrado no manual do programa.\n" +
			"Para obter o manual de uso do programa, acesse o website\n" +
			"http://www.linuxafundo.com.br/mygoodmoney. O manual está disponível\n" +
			"na versão online e futuramente poderá ser baixado em PDF.\n\n" +
			"   Este software encontra-se sob a licença GPL v3, a qual lhe permite\n" +
			"executar para qualquer propósito, estudar o funcionamento, redistri-\n" +
			"buir e aperfeiçoar da maneira que melhor julgar.\n" +
			 "O código fonte pode ser obtido em: https://github.com/RMCampos/MyGoodMoney";

		JTextPane txtTexto = new JTextPane();
		txtTexto.setText( texto );
		txtTexto.setFont( new Font( "Monospaced", 0, 12 ) );
		txtTexto.setEditable( false );
		txtTexto.setBackground( pnlSobre.getBackground() );

		JScrollPane textoJ = new JScrollPane( txtTexto );
		textoJ.setBounds( 20, 20, 650, 180 );

		// Adicao dos componentes ao frame
		this.getContentPane().add( this.pnlSuperior );
		this.pnlSuperior.add( this.lblPeriodo );
		this.pnlSuperior.add( this.btnMenosPeriodo );
		this.pnlSuperior.add( this.dtcPeriodoIni );
		this.pnlSuperior.add( this.lblPeriodoA );
		this.pnlSuperior.add( this.dtcPeriodoFim );
		this.pnlSuperior.add( this.btnMaisPeriodo );
		this.getContentPane().add( this.tbpPainelAbas );
		this.tbpPainelAbas.add( this.pnlHome );
		this.pnlHome.add( this.pnlResumoCaixa );
		this.pnlResumoCaixa.add( this.lblResumoCaixa );
		this.pnlResumoCaixa.add( this.lblResNomeCaixa );
		this.pnlResumoCaixa.add( this.cbxResCaixa );
		this.pnlResumoCaixa.add( this.btnResCaixa );
		this.pnlResumoCaixa.add( this.lblResSaldoCaixa );
		this.pnlResumoCaixa.add( this.txfResValorSaldoCaixa );
		this.pnlResumoCaixa.add( this.lblResSaldoCaixaLimite );
		this.pnlResumoCaixa.add( this.txfResSaldoCaixaLimite );
		this.pnlResumoCaixa.add( this.lblResTotEntrCaixa );
		this.pnlResumoCaixa.add( this.txfResValTotEntrCaixa );
		this.pnlResumoCaixa.add( this.lblResTotSaidaCaixa );
		this.pnlResumoCaixa.add( this.txfResValTotSaidaCaixa );
		this.pnlResumoCaixa.add( this.lblResumoCaixaProvisao );
		this.pnlResumoCaixa.add( this.ckbResumoCaixa );
		this.pnlResumoCaixa.add( this.pnlGrafico );
		this.pnlHome.add( this.pnlResumoConta );
		this.pnlResumoConta.add( this.lblResumoConta );
		this.pnlResumoConta.add( this.lblResNomeConta );
		this.pnlResumoConta.add( this.cbxResConta );
		this.pnlResumoConta.add( this.lblResumoContaProvisao );
		this.pnlResumoConta.add( this.ckbResumoConta );
		this.pnlResumoConta.add( this.btnResConta );
		this.pnlResumoConta.add( this.lblResMovContaPc );
		this.pnlResumoConta.add( this.txfResValorMovContaPc );
		this.pnlResumoConta.add( this.lblResMovContaRs );
		this.pnlResumoConta.add( this.txfResValorMovContaRs );
		this.pnlResumoConta.add( this.lblResMovContaAno );
		this.pnlResumoConta.add( this.txfResValorMovContaAno );
		this.tbpPainelAbas.add( this.pnlMovimento );
		this.pnlMovimento.add( this.tbpMovimento );
		this.tbpMovimento.add( this.pnlCadastroMov );
		this.pnlCadastroMov.add( this.lblMovDescricao );
		this.pnlCadastroMov.add( this.txfMovDescricao );
		this.pnlCadastroMov.add( this.lblMovData );
		this.pnlCadastroMov.add( this.dtcMovData );
		this.pnlCadastroMov.add( this.lblInfoData );
		this.pnlCadastroMov.add( this.lblMovValor );
		this.pnlCadastroMov.add( this.dbfMovValor );
		this.pnlCadastroMov.add( this.lblMovConta );
		this.pnlCadastroMov.add( this.cbxMovConta );
		this.pnlCadastroMov.add( this.lblMovCaixa );
		this.pnlCadastroMov.add( this.cbxMovCaixa );
		this.pnlCadastroMov.add( this.ckbMovPago );
		this.pnlCadastroMov.add( this.lblMovRecorrencia );
		this.pnlCadastroMov.add( this.rbtMovRecorrenciaSim );
		this.pnlCadastroMov.add( this.rbtMovRecorrenciaNao );
		this.pnlCadastroMov.add( this.lblRepetir );
		this.pnlCadastroMov.add( this.itfNumVezes );
		this.pnlCadastroMov.add( this.lblVezes );
		this.pnlCadastroMov.add( this.lblACada );
		this.pnlCadastroMov.add( this.itfNumPeriodo );
		this.pnlCadastroMov.add( this.cbxPeriodo );
		this.pnlCadastroMov.add( this.btnMovAdd );
		this.pnlCadastroMov.add( this.btnMovEditar );
		this.pnlCadastroMov.add( this.btnMovExcluir );
		this.pnlCadastroMov.add( this.btnMovConfirmar );
		this.pnlCadastroMov.add( this.btnMovCancelar );
		this.tbpMovimento.add( this.pnlTransferencia );
		this.pnlTransferencia.add( this.btnTransfAdd );
		this.pnlTransferencia.add( this.btnTransfConfirmar );
		this.pnlTransferencia.add( this.btnTransfCancelar );
		this.pnlTransferencia.add( this.lblTransfCaixaOrigem );
		this.pnlTransferencia.add( this.cbxTransfCaixaOrigem );
		this.pnlTransferencia.add( this.lblTransfCaixaDestino );
		this.pnlTransferencia.add( this.cbxTransfCaixaDestino );
		this.pnlTransferencia.add( this.lblTransfDescricao );
		this.pnlTransferencia.add( this.txfTransfDescricao );
		this.pnlTransferencia.add( this.lblTransfData );
		this.pnlTransferencia.add( this.dtcTransfData );
		this.pnlTransferencia.add( this.lblTransfValor );
		this.pnlTransferencia.add( this.dbfTransfValor );
		this.pnlTransferencia.add( this.ckbTransf );
		this.pnlMovimento.add( this.pnlLancamentoMov );
		this.tbpPainelAbas.add( this.pnlExtratos );
		this.pnlExtratos.add( this.pnlExtrato );
		this.pnlExtrato.add( this.lblExtratoConta );
		this.pnlExtrato.add( this.cbxExtratoConta );
		this.pnlExtrato.add( this.lblExtratoCaixa );
		this.pnlExtrato.add( this.cbxExtratoCaixa );
		this.pnlExtrato.add( this.lblExtratoPeriodo );
		this.pnlExtrato.add( this.dtcExtratoIni );
		this.pnlExtrato.add( this.lblExtratoHifen );
		this.pnlExtrato.add( this.dtcExtratoFim );
		this.pnlExtrato.add( this.btnExtrato );
		this.pnlExtrato.add( this.ckbExtratoProvisao );
		this.pnlExtrato.add( this.scbExtratoConta );
		this.tbpPainelAbas.add( this.pnlCadastros );
		this.pnlCadastros.add( this.pnlCadConta );
		this.pnlCadConta.add( this.lblAddConta );
		this.pnlCadConta.add( this.lblNomeConta );
		this.pnlCadConta.add( this.txfNomeConta );
		this.pnlCadConta.add( this.lblTipoConta );
		this.pnlCadConta.add( this.cbxTipoConta );
		this.pnlCadConta.add( this.btnAddConta );
		this.pnlCadConta.add( this.btnEditarConta );
		this.pnlCadConta.add( this.btnExcluirConta );
		this.pnlCadConta.add( this.btnConfirmarConta );
		this.pnlCadConta.add( this.btnCancelarConta );
		this.pnlCadConta.add( this.pnlContas );
		this.pnlCadastros.add( this.pnlCadCaixa );
		this.pnlCadCaixa.add( this.lblAddCaixa );
		this.pnlCadCaixa.add( this.lblNomeCaixa );
		this.pnlCadCaixa.add( this.txfNomeCaixa );
		this.pnlCadCaixa.add( this.lblSaldoInicial );
		this.pnlCadCaixa.add( this.dbfSaldoInicialCaixa );
		this.pnlCadCaixa.add( this.lblTipoCaixa );
		this.pnlCadCaixa.add( this.rbtTipoCaixaCartao );
		this.pnlCadCaixa.add( this.rbtTipoCaixaNormal );
		this.pnlCadCaixa.add( this.lblValorLimite );
		this.pnlCadCaixa.add( this.dbfValorLimite );
		this.pnlCadCaixa.add( this.btnAddCaixa );
		this.pnlCadCaixa.add( this.btnEditarCaixa );
		this.pnlCadCaixa.add( this.btnExcluirCaixa );
		this.pnlCadCaixa.add( this.btnConfirmarCaixa );
		this.pnlCadCaixa.add( this.btnCancelarCaixa );
		this.pnlCadCaixa.add( this.pnlCaixas );
		this.tbpPainelAbas.add( this.pnlConfig );
		this.pnlConfig.add( this.pnlBD );
		this.pnlBD.add( this.lblBD );
		this.pnlBD.add( this.lblDiretorioBD );
		this.pnlBD.add( this.txfDiretorioBD );
		this.pnlBD.add( this.lblNomeBD );
		this.pnlBD.add( this.txfNomeBD );
		this.tbpPainelAbas.add( this.pnlSobre );
		this.pnlSobre.add( this.pnlAutor );
		this.pnlAutor.add( this.lblNomeAutor );
		this.pnlAutor.add( this.lblEmailAutor );
		this.pnlAutor.add( this.lblContatoAutor );
		this.pnlAutor.add( this.lblPaginaOficial );
		this.pnlSobre.add( this.pnlTexto );
		this.pnlTexto.add( textoJ );

		this.tbpMovimento.setTitleAt( 0, "Lançamentos" );
		this.tbpMovimento.setTitleAt( 1, "Transferência" );
	}
	public void abrirLink( String dest ) {
		try{
			final URI uri = new URI( "http://www.linuxafundo.com.br/" + dest );
			if( Desktop.isDesktopSupported() ) {
				try {
					Desktop.getDesktop().browse(uri);
				}
				catch( IOException ie ) {
					ie.printStackTrace();
				}
			}
		}
		catch( URISyntaxException ex ){
			ex.printStackTrace();
		}
	}
	public void acessar() {
		comando = "";

		while( comando.isEmpty() ) {
			try {
				Thread.sleep(50);
			}
			catch( InterruptedException ie ) {
				ie.printStackTrace();
			}
		}
	}
	public String getComandoTela() {
		return( this.comando );
	}
	public void limparCamposConta() {
		this.txfNomeConta.setText( "" );
		this.cbxTipoConta.setSelectedItem( null );
	}
	public void limparCamposCaixa() {
		this.txfNomeCaixa.setText( "" );
		this.dbfSaldoInicialCaixa.setValue( 0.0 );
		this.rbtTipoCaixaCartao.setSelected( false );
		this.rbtTipoCaixaNormal.setSelected( true );
		this.dbfValorLimite.setValue( 0.0 );
	}
	public void limparCamposLancamento() {
		this.txfMovDescricao.setText( "" );
		this.dtcMovData.setDate( new Date() );
		this.dbfMovValor.setValue( 0.0 );
		this.cbxMovConta.setSelectedIndex( -1 );
		this.cbxMovCaixa.setSelectedIndex( -1 );
		this.ckbMovPago.setSelected( false );
		this.rbtMovRecorrenciaNao.setSelected( true );
		this.rbtMovRecorrenciaSim.setSelected( false );
		this.itfNumPeriodo.setValue( 0 );
		this.itfNumVezes.setValue( 0 );
		habilitarRecorrencia( false );
	}
	public void mudarEstado( String pDestino, String pEstado ) {
		boolean estado = pEstado.equals( "NAVEGACAO" );

		if( pDestino.equals( "Conta" ) || pDestino.isEmpty() ) {
			this.btnAddConta.setEnabled( estado );
			this.btnEditarConta.setEnabled( estado );
			this.btnExcluirConta.setEnabled( estado );
			this.btnConfirmarConta.setEnabled( !estado );
			this.btnCancelarConta.setEnabled( !estado );
			this.txfNomeConta.setEditable( !estado );
			this.cbxTipoConta.setEnabled( !estado );
		}
		if( pDestino.equals( "Caixa" ) || pDestino.isEmpty() ) {
			this.btnAddCaixa.setEnabled( estado );
			this.btnEditarCaixa.setEnabled( estado );
			this.btnExcluirCaixa.setEnabled( estado );
			this.btnConfirmarCaixa.setEnabled( !estado );
			this.btnCancelarCaixa.setEnabled( !estado );
			this.txfNomeCaixa.setEditable( !estado );
			this.dbfSaldoInicialCaixa.setEditable( !estado );
			this.rbtTipoCaixaCartao.setEnabled( !estado );
			this.rbtTipoCaixaNormal.setEnabled( !estado );
			this.dbfValorLimite.setEnabled( !estado );
		}
		if( pDestino.equals( "Lancamento" ) || pDestino.isEmpty() ) {
			this.btnMovAdd.setEnabled( estado );
			this.btnMovEditar.setEnabled( estado );
			this.btnMovExcluir.setEnabled( estado );
			this.btnMovCancelar.setEnabled( !estado );
			this.btnMovConfirmar.setEnabled( !estado );
			this.txfMovDescricao.setEditable( !estado );
			this.dtcMovData.setEditable( !estado );
			this.dbfMovValor.setEditable( !estado );
			this.cbxMovConta.setEnabled( !estado );
			this.cbxMovCaixa.setEnabled( !estado );
			this.ckbMovPago.setEnabled( !estado );
			this.rbtMovRecorrenciaSim.setEnabled( !estado );
			this.rbtMovRecorrenciaNao.setEnabled( !estado );

			if( pEstado.equals( "NAVEGACAO" ) ) {
				limparCamposLancamento();
				habilitarRecorrencia( false );
			}
		}
		if( pDestino.equals( "Transferencia" ) || pDestino.isEmpty() ) {
			this.btnTransfAdd.setEnabled( estado );
			this.btnTransfCancelar.setEnabled( !estado );
			this.btnTransfConfirmar.setEnabled( !estado );
			this.cbxTransfCaixaOrigem.setEnabled( !estado );
			this.cbxTransfCaixaDestino.setEnabled( !estado );
			this.txfTransfDescricao.setEnabled( !estado );
			this.dtcTransfData.setEditable( !estado );
			this.dbfTransfValor.setEnabled( !estado );
			this.ckbTransf.setEnabled( !estado );
		}
	}
	public String getTxfNomeConta() {
		return( txfNomeConta.getText() );
	}
	public EnumTipoConta getCbxTipoConta() {
		if( cbxTipoConta.getSelectedIndex() >= 0 ) {
			return( (EnumTipoConta) cbxTipoConta.getSelectedItem() );
		}
		return( null );
	}
	public String getTxfMovDescricao() {
		return( txfMovDescricao.getText() );
	}
	public Date getDtcMovData() {
		return( dtcMovData.getDate() );
	}
	public void setDtcPeriodoIni( Date dataParam ) {
		dtcPeriodoIni.setDate( dataParam );
	}
	public void setDtcPeriodoFim( Date dataParam ) {
		dtcPeriodoFim.setDate( dataParam );
	}
	public double getDbfMovValor() {
		return( dbfMovValor.getValue() );
	}
	public Conta getCbxMovConta() {
		if( cbxMovConta.getSelectedIndex() >= 0 ){
			return( (Conta) cbxMovConta.getSelectedItem() );
		}
		return( null );
	}
	public Caixa getCbxMovCaixa() {
		if( cbxMovCaixa.getSelectedIndex() >= 0 ) {
			return( (Caixa) cbxMovCaixa.getSelectedItem() );
		}
		return( null );
	}
	public boolean getCkbMovPago() {
		return( ckbMovPago.isSelected() );
	}
	public char getMovRecorrencia() {
		return( (rbtMovRecorrenciaSim.isSelected())? 'S' : 'N' );
	}
	public Conta getContaSelecionada() {
		int linhaConta = this.contasTable.getSelectedRow();
		Conta conta = this.contasModel.getLinha( linhaConta );
		return( conta );
	}
	public Caixa getCaixaSelecionado() {
		int linhaCaixa = this.caixasTable.getSelectedRow();
		Caixa caixa = this.caixasModel.getLinha( linhaCaixa );
		return( caixa );
	}
	public Lancamento getLancamentoSelecionado() {
		int linha = movimentoTable.getSelectedRow();
		Lancamento lancamento = this.movimentoModel.getLinha( linha );
		return( lancamento );
	}
	private void habilitarRecorrencia( boolean habilitarParam ) {
		lblRepetir.setEnabled(habilitarParam);
		itfNumVezes.setEnabled(habilitarParam);
		lblVezes.setEnabled(habilitarParam);
		lblACada.setEnabled(habilitarParam);
		itfNumPeriodo.setEnabled(habilitarParam);
		cbxPeriodo.setEnabled(habilitarParam);
	}
	public String getTxfNomeCaixa() {
		return( txfNomeCaixa.getText() );
	}
	public void setTxfResValorSaldoCaixa( double pSaldo ) {
		this.txfResValorSaldoCaixa.setText( ValueTools.formatToField( pSaldo ) );
	}
	public Caixa getCbxResCaixa() {
		if( this.cbxResCaixa.getSelectedIndex() >= 0 ) {
			Caixa c;

			try {
				c = (Caixa) this.cbxResCaixa.getSelectedItem();
			}
			catch( ClassCastException ex ) {
				String opcao = (String) this.cbxResCaixa.getSelectedItem();
				c = new Caixa();
				c.setCodCaixa( null );
				c.setNome(opcao);
			}
			return( c );
		}
		return( null );
	}
	public Conta getCbxResConta() {
		if( this.cbxResConta.getSelectedIndex() >= 0 ) {
			Conta c;

			try {
				c = (Conta) this.cbxResConta.getSelectedItem();
			}
			catch( ClassCastException ex ) {
				String opcao = (String) this.cbxResConta.getSelectedItem();
				c = new Conta();
				c.setCodConta( null );
				c.setNome(opcao);
				c.setTipo( (opcao.equals("TODOS DÉBITO"))? 'D' : 'C' );
			}
			return( c );
		}
		return( null );
	}
	public Date getDtcPeriodoIni() {
		return( dtcPeriodoIni.getDate() );
	}
	public Date getDtcPeriodoFim() {
		return( dtcPeriodoFim.getDate() );
	}
	public void setTxfResValTotEntrCaixa( double pValor ) {
		this.txfResValTotEntrCaixa.setText( ValueTools.formatToField( pValor ) );
	}
	public void setTxfResValTotSaidaCaixa( double pValor ) {
		this.txfResValTotSaidaCaixa.setText( ValueTools.formatToField( pValor ) );
	}
	public void setTxfResValorMovContaPc( double pValor ) {
		this.txfResValorMovContaPc.setText( ValueTools.formatToFieldPerc( pValor ) );
	}
	public void setTxfResValorMovContaRs( double pValor ) {
		this.txfResValorMovContaRs.setText( ValueTools.formatToField( pValor ) );
	}
	public void setTxfResValorMovContaAno( double pValor ) {
		this.txfResValorMovContaAno.setText( ValueTools.formatToField( pValor ) );
	}
	public Caixa getCaixaTela() {
		Caixa caixa = new Caixa();
		caixa.setNome( this.txfNomeCaixa.getText() );
		caixa.setSaldo( this.dbfSaldoInicialCaixa.getValue() );
		caixa.setLimite( (this.rbtTipoCaixaCartao.isSelected())? 'S' : 'N' );
		caixa.setValorLimite( this.dbfValorLimite.getValue() );
		return( caixa );
	}
	public Conta getContaTela() {
		Conta conta = new Conta();
		conta.setNome( this.txfNomeConta.getText() );
		conta.setTipo( getCbxTipoConta().getCodigo() );
		return( conta );
	}
	public Lancamento getLancamentoTela() {
		Lancamento lancamento = new Lancamento();

		if( getCkbMovPago() ) {
			// lancamento a vista
			lancamento.setDataEmissao( DateTools.parseDateToInteger( getDtcMovData() ) );
			lancamento.setDataVencimento( DateTools.parseDateToInteger( getDtcMovData() ) );
			lancamento.setDataQuitacao( DateTools.parseDateToInteger( getDtcMovData() ) );
		}
		else {
			// provisao
			lancamento.setDataEmissao( DateTools.parseDateToInteger( new Date() ) );
			lancamento.setDataVencimento( DateTools.parseDateToInteger( getDtcMovData() ) );
			lancamento.setDataQuitacao( 0 );
		}

		lancamento.setDescricao( getTxfMovDescricao() );
		lancamento.setValor( getDbfMovValor() );
		Conta conta = (Conta) getCbxMovConta();
		lancamento.setConta( conta );
		Caixa caixa = (Caixa) getCbxMovCaixa();
		lancamento.setCaixa( caixa );

		return( lancamento );
	}
	public int getItfNumVezes() {
		return( this.itfNumVezes.getValue() );
	}
	public int getItfNumPeriodo() {
		return( this.itfNumPeriodo.getValue() );
	}
	public EnumPeriodo getCbxPeriodo() {
		if( this.cbxPeriodo.getSelectedIndex() >= 0 ) {
			return( (EnumPeriodo) this.cbxPeriodo.getSelectedItem() );
		}
		return( null );
	}
	public void setTxtExtrato( String pTexto ) {
		this.txtExtrato.setText( pTexto );
	}
	public String getTxtExtrato() {
		return( this.txtExtrato.getText() );
	}
	public Conta getCbxExtratoConta() {
		if( this.cbxExtratoConta.getSelectedIndex() >= 0 ) {
			Conta c;

			try {
				c = (Conta) this.cbxExtratoConta.getSelectedItem();
			}
			catch( ClassCastException ex ) {
				String opcao = (String) this.cbxExtratoConta.getSelectedItem();
				c = new Conta();
				c.setCodConta( null );
				c.setNome(opcao);
				c.setTipo( (opcao.equals("TODOS DÉBITO"))? 'D' : 'C' );
			}
			return( c );
		}
		return( null );
	}
	public Caixa getCbxExtratoCaixa() {
		if( this.cbxExtratoCaixa.getSelectedIndex() >= 0 ) {
			Caixa c;

			try {
				c = (Caixa) this.cbxExtratoCaixa.getSelectedItem();
			}
			catch( ClassCastException ex ) {
				String opcao = (String) this.cbxExtratoCaixa.getSelectedItem();
				c = new Caixa();
				c.setCodCaixa( null );
				c.setNome(opcao);
			}
			return( c );
		}
		return( null );
	}
	public Date getDtcExtratoIni() {
		return( this.dtcExtratoIni.getDate() );
	}
	public Date getDtcExtratoFim() {
		return( this.dtcExtratoFim.getDate() );
	}
	public boolean getCkbExtratoProvisao() {
		return( this.ckbExtratoProvisao.isSelected() );
	}
	public boolean getCkbResumoConta() {
		return( this.ckbResumoConta.isSelected() );
	}
	public boolean getCkbResumoCaixa() {
		return( this.ckbResumoCaixa.isSelected() );
	}
	public void habilitarComponentesRecorrencia( boolean hab ) {
		this.lblMovRecorrencia.setVisible( hab );
		this.rbtMovRecorrenciaSim.setVisible( hab );
		this.rbtMovRecorrenciaNao.setVisible( hab );
		this.lblRepetir.setVisible( hab );
		this.itfNumVezes.setVisible( hab );
		this.lblVezes.setVisible( hab );
		this.lblACada.setVisible( hab );
		this.itfNumPeriodo.setVisible( hab );
		this.cbxPeriodo.setVisible( hab );
	}
	public void setGrafico( JFreeChart pGrafico ) {
		if( pGrafico != null ) {
			this.pnlGrafico.setChart( pGrafico );
			this.pnlGrafico.setVisible( true );
			this.pnlGrafico.setMouseWheelEnabled( true );
			this.pnlGrafico.setMouseZoomable( true );
		}
		else {
			this.pnlGrafico.setVisible( false );
		}
	}
	public void focoConta() {
		this.txfNomeConta.requestFocus();
	}
	public void focoCaixa() {
		this.txfNomeCaixa.requestFocus();
	}
	public void focoLancamento() {
		this.txfMovDescricao.requestFocus();
	}
	public void addCaixaHomeTODOS() {
		this.cbxResCaixa.addItem( "TODOS" );
		this.cbxExtratoCaixa.addItem( "TODOS" );
	}
	public void addContaHomeTODOS() {
		this.cbxResConta.addItem( "TODOS CRÉDITO" );
		this.cbxResConta.addItem( "TODOS DÉBITO" );
		this.cbxExtratoConta.addItem( "TODOS CRÉDITO" );
		this.cbxExtratoConta.addItem( "TODOS DÉBITO" );
	}
	public void setLancamentos( ArrayList<Lancamento> pLancList ) {
		this.movimentoModel.setLinhas( pLancList );
	}
	public void setContas( ArrayList<Conta> pContasList ) {
		this.contasModel.setLinhas( pContasList );
	}
	public void setCaixas( ArrayList<Caixa> pCaixasList ) {
		this.caixasModel.setLinhas( pCaixasList );
	}
	public void limparContasGeral( ArrayList<Conta> contas ) {
		this.cbxResConta.removeAllItems();
		this.cbxExtratoConta.removeAllItems();
		this.cbxMovConta.removeAllItems();
		this.contasModel.limpar();

		for( Conta c : contas ) {
			this.cbxResConta.addItem( c );
			this.cbxExtratoConta.addItem( c );
			this.cbxMovConta.addItem( c );
		}

		this.contasModel.setLinhas( contas );
	}
	public void limparCaixasGeral( ArrayList<Caixa> caixas ) {
		this.cbxResCaixa.removeAllItems();
		this.cbxMovCaixa.removeAllItems();
		this.cbxTransfCaixaOrigem.removeAllItems();
		this.cbxTransfCaixaDestino.removeAllItems();
		this.cbxExtratoCaixa.removeAllItems();

		this.caixasModel.limpar();

		for( Caixa c : caixas ) {
			this.cbxResCaixa.addItem( c );
			this.cbxMovCaixa.addItem( c );
			this.cbxTransfCaixaOrigem.addItem( c );
			this.cbxTransfCaixaDestino.addItem( c );
			this.cbxExtratoCaixa.addItem( c );
		}

		this.caixasModel.setLinhas( caixas );
	}
	public void atualizarCampos( final Object pDestino ) {
		if( pDestino instanceof Caixa ) {
			Caixa c = (Caixa)pDestino;

			this.txfNomeCaixa.setText( c.getNome() );
			this.dbfSaldoInicialCaixa.setValue( c.getSaldo() );

			if( c.getLimite() == 'S' ) {
				this.rbtTipoCaixaCartao.setSelected( true );
				this.rbtTipoCaixaNormal.setSelected( false );
				this.dbfValorLimite.setValue( c.getValorLimite() );
			}
			else {
				this.rbtTipoCaixaCartao.setSelected( false );
				this.rbtTipoCaixaNormal.setSelected( true );
				this.dbfValorLimite.setValue( 0.0 );
			}
		}
		else if( pDestino instanceof Conta ) {
			Conta c = (Conta)pDestino;

			this.txfNomeConta.setText( c.getNome() );
			this.cbxTipoConta.setSelectedItem( EnumTipoConta.getPorCodigo( c.getTipo() ) );
		}
		else {
			try {
				SwingUtilities.invokeAndWait( new Runnable() {
					@Override
					public void run() {
						Lancamento l = (Lancamento)pDestino;
						int data = (l.getPago() == 'S')? l.getDataQuitacao() : l.getDataVencimento();
						boolean pago = l.getPago() == 'S';

						txfMovDescricao.setText( l.getDescricao() );
						dtcMovData.setDate( DateTools.parseDataIntToDate( data ) );
						dbfMovValor.setValue( l.getValor() );
						cbxMovConta.setSelectedItem( l.getConta() );
						cbxMovCaixa.setSelectedItem( l.getCaixa() );
						ckbMovPago.setSelected( pago );
					}
				});
			}
			catch( InvocationTargetException | InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
	}
	public void habilitarCampoAlteracao( String pObjeto, boolean hab ) {
		if( pObjeto.equals( "Caixa" ) ) {
			this.dbfSaldoInicialCaixa.setEnabled( hab );
			this.rbtTipoCaixaCartao.setEnabled( hab );
			this.rbtTipoCaixaNormal.setEnabled( hab );
		}
		else if( pObjeto.equals( "Conta" ) ) {
			this.cbxTipoConta.setEnabled( hab );
		}
		else if( pObjeto.equals( "Lancamento" ) ) {
			this.dtcMovData.setEnabled( hab );
			this.dtcMovData.setEditable( hab );
			this.cbxMovConta.setEnabled( hab );
			this.cbxMovCaixa.setEnabled( hab );
		}
		else if( pObjeto.equals( "Transferencia" ) ) {
			this.cbxTransfCaixaOrigem.setEnabled( hab );
			this.cbxTransfCaixaDestino.setEnabled( hab );
			this.txfTransfDescricao.setEnabled( hab );
			this.dtcTransfData.setEnabled( hab );
			this.dbfTransfValor.setEnabled( hab );
			this.ckbTransf.setEnabled( hab );
		}
	}
	public void habilitarTipoCaixa( boolean hab ) {
		this.dbfValorLimite.setEnabled( hab );
	}
	public boolean getRbtTipoCaixaCartao() {
		return( this.rbtTipoCaixaCartao.isSelected() );
	}
	public boolean getRbtTipoCaixaNormal() {
		return( this.rbtTipoCaixaNormal.isSelected() );
	}
	public double getDbfValorLimite() {
		return( this.dbfValorLimite.getValue() );
	}
	public void setTxfResSaldoCaixaLimite( double pValor ) {
		this.txfResSaldoCaixaLimite.setText( ValueTools.formatToField( pValor ) );
	}
	public void limparCamposTransferencia() {
		this.cbxTransfCaixaOrigem.setSelectedIndex( -1 );
		this.cbxTransfCaixaDestino.setSelectedIndex( -1 );
		this.txfTransfDescricao.setText( "" );
		this.dtcTransfData.setDate( null );
		this.dbfTransfValor.setValue( 0.0 );
		this.ckbTransf.setSelected( false );
	}
	public void focoTransferencia() {
		this.cbxTransfCaixaOrigem.requestFocus();
	}
	public Caixa getCbxTransfCaixaOrigem() {
		if( this.cbxTransfCaixaOrigem.getSelectedIndex() >= 0 ) {
			return( (Caixa) this.cbxTransfCaixaOrigem.getSelectedItem() );
		}
		return( null );
	}
	public Caixa getCbxTransfCaixaDestino() {
		if( this.cbxTransfCaixaDestino.getSelectedIndex() >= 0 ) {
			return( (Caixa) this.cbxTransfCaixaDestino.getSelectedItem() );
		}
		return( null );
	}
	public String getTxfTransfDescricao() {
		return( this.txfTransfDescricao.getText() );
	}
	public Date getDtcTransfData() {
		return( this.dtcTransfData.getDate() );
	}
	public double getDbfTransfValor() {
		return( this.dbfTransfValor.getValue() );
	}
	public boolean getCkbTransf() {
		return( this.ckbTransf.isSelected() );
	}
	public Lancamento getTransferenciaTela() {
		Lancamento transf = new Lancamento();

		transf.setCaixa( getCbxTransfCaixaOrigem() );
		transf.setConta( null ); // conta == null siginifica transferencia
		transf.setDescricao( getTxfTransfDescricao() );

		if( getCkbTransf() ) {
			// pago
			transf.setDataEmissao( DateTools.parseDateToInteger( getDtcTransfData() ) );
			transf.setDataQuitacao( DateTools.parseDateToInteger( getDtcTransfData() ) );
			transf.setDataVencimento( DateTools.parseDateToInteger( getDtcTransfData() ) );
			transf.setPago( 'S' );
		}
		else {
			// provisionado
			transf.setDataEmissao( DateTools.getDataAtual() );
			transf.setDataQuitacao( 0 );
			transf.setDataVencimento( DateTools.parseDateToInteger( getDtcTransfData() ) );
			transf.setPago( 'N' );
		}

		transf.setValor( getDbfTransfValor() );

		return( transf );
	}
	public void perderFocoLancamento() {
		this.pnlLancamentoMov.requestFocus();
	}
	public void perderFocoTransferencia() {
		this.pnlTransferencia.requestFocus();
	}
	public void perderFocoConta() {
		this.pnlCadConta.requestFocus();
	}
	public void perderFocoCaixa() {
		this.pnlCadCaixa.requestFocus();
	}
	private void criarListener() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				try {
					AOPRESSIONARTecla(e);
				}
				catch( Exception exc ) {
					exc.printStackTrace();
				}
				return false;
			}
		});
	}
	public void AOPRESSIONARTecla( KeyEvent e ) {
		if( e.isControlDown() && e.getKeyCode() == KeyEvent.VK_I && e.getID() == KeyEvent.KEY_PRESSED ) {
			if( this.console.isVisible() ) {
				this.console.setVisible( false );
			}
			else {
				this.console.setVisible( true );
			}
		}
	}
	private void iniciarConsole() {
		this.console = new FrConsole();
	}
	public void setCaixaResumoSemLimite() {
		this.txfResSaldoCaixaLimite.setText( "R$           -- " );
	}
	public void lostFocus() {
		this.dtcPeriodoIni.requestFocus();
	}
	public void mudarFoco( Component c ) {
		c.requestFocus();
	}
}