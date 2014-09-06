package mygoodmoney;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Daniel
 */
public class FrConsole extends javax.swing.JFrame {
    
    private ConsoleSaida console = null;
    private JFileChooser fileChooser;
    
    public FrConsole() {
        initComponents();
        this.setTitle("Console");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                fecharTela();
            }
        });
        iniciarConsole();
    }
    //--------------------------------------------------------------------------
    private void iniciarConsole(){
        console = new ConsoleSaida(taSaida);
        console.startConsole();
        DefaultCaret caret = (DefaultCaret)taSaida.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        taSaida.requestFocus();
    }
    //--------------------------------------------------------------------------
    private void fecharTela(){
        this.setVisible(false);
    }
    //--------------------------------------------------------------------------
    private void instanciaFileChooser(){
        if(fileChooser == null){
            fileChooser = new JFileChooser();
            fileChooser.setDialogType( JFileChooser.SAVE_DIALOG );
            fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
            fileChooser.setMultiSelectionEnabled( false );
            fileChooser.setAcceptAllFileFilterUsed( true );
            fileChooser.setControlButtonsAreShown( true );
        }
    }
    //--------------------------------------------------------------------------
    public void salvarLog(File f, String data) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f), "ISO-8859-1" ));
            out.write( data );
        } catch(IOException e) {
            throw( e );
        } finally {
            if( out != null ){
                try {
                    out.close();
                } catch(IOException ex){
                    throw( ex );
                }
            }
        }
    }
    //--------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    taSaida = new javax.swing.JTextArea();
    btSalvar = new javax.swing.JButton();
    btLimpar = new javax.swing.JButton();
    btnFechar = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    taSaida.setEditable(false);
    taSaida.setBackground(new java.awt.Color(0, 0, 0));
    taSaida.setColumns(20);
    taSaida.setForeground(new java.awt.Color(255, 255, 255));
    taSaida.setRows(5);
    jScrollPane1.setViewportView(taSaida);

    btSalvar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
    btSalvar.setText("Salvar");
    btSalvar.setFocusable(false);
    btSalvar.setPreferredSize(new java.awt.Dimension(100, 30));
    btSalvar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSalvarActionPerformed(evt);
      }
    });

    btLimpar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
    btLimpar.setText("Limpar");
    btLimpar.setFocusable(false);
    btLimpar.setPreferredSize(new java.awt.Dimension(100, 30));
    btLimpar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btLimparActionPerformed(evt);
      }
    });

    btnFechar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
    btnFechar.setText("Fechar");
    btnFechar.setFocusable(false);
    btnFechar.setPreferredSize(new java.awt.Dimension(100, 30));
    btnFechar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnFecharActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(6, 6, 6))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        instanciaFileChooser();
        int returnVal = fileChooser.showSaveDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION ) {
            File f = fileChooser.getSelectedFile();
            if(!f.getName().toLowerCase().endsWith(".txt"))
                f = new File(f.getAbsolutePath() + ".txt");
            try {
                if(f.exists()){
                    int res = JOptionPane.showConfirmDialog( this, "Arquivo já existe. Deseja substituir?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if( res != 0 )
                        return;
                }
                salvarLog(f, taSaida.getText());
            } catch(Exception ex) {
                JOptionPane.showMessageDialog( this, "Houve erro ao salvar arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrConsole.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        taSaida.setText("");
    }//GEN-LAST:event_btLimparActionPerformed

  private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
    setVisible( false );
  }//GEN-LAST:event_btnFecharActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btLimpar;
  private javax.swing.JButton btSalvar;
  private javax.swing.JButton btnFechar;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea taSaida;
  // End of variables declaration//GEN-END:variables

}
