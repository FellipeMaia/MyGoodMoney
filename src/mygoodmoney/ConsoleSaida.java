package mygoodmoney;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Daniel
 */
public class ConsoleSaida {
    
    private final boolean m_autoSave = false;
    private JTextArea saida = null;
    private String m_traceFilename = "";
    
    public ConsoleSaida(String nomeArquivo){
        this.m_traceFilename = nomeArquivo;
    }
    
    public ConsoleSaida(JTextArea saida){
        this.saida = saida;
    }
    
    public ConsoleSaida(String nomeArquivo, JTextArea saida){
        this.m_traceFilename = nomeArquivo;
        this.saida = saida;
    }
    
    /**
     * Default output stream.
     */
    private static final PrintStream STDOUT = System.out;
    
    /**
     * Out print stream.
     */
    private final transient PrintStream  m_stdoutPS = new PrintStream(new ConsoleOutStream(new ByteArrayOutputStream()));
    
    /**
     * Error print stream.
     */
    private final transient PrintStream  m_stderrPS = new PrintStream(new ConsoleOutStream(new ByteArrayOutputStream()));
    
    /**
     * Attachs the new streams to stdout and stderr.
     */
    public synchronized void startConsole() {
        System.setOut( m_stdoutPS );
        System.setErr( m_stderrPS );
    }
    //--------------------------------------------------------------------------
    public static void saveTxtFile(String pathname, String data, boolean append) throws IOException {
        File f = new File(pathname);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter( new FileWriter(f, append) );
            out.write( data );
        } catch(IOException e) {
            throw( e );
        } finally {
            if( out != null ) {
                try {
                    out.close();
                } catch(IOException ex) {
                    Logger.getLogger(ConsoleSaida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //--------------------------------------------------------------------------
    private final class ConsoleOutStream extends FilterOutputStream {

        public ConsoleOutStream( OutputStream aStream ){
            super( aStream );
        }

        @Override
        public synchronized void write( byte b[] ) throws IOException {
            String s = new String( b );
            appendMessage( s );
        }

        @Override
        public synchronized void write( byte b[], int off, int len ) throws IOException{
            String s = new String(b, off, len);
            appendMessage( s );
        }

        private synchronized void appendMessage(String s){
            STDOUT.append(s);
            if(saida != null)
                saida.append(s);
            if( m_autoSave ) {
                boolean append = true;
                try {
                    ConsoleSaida.saveTxtFile(m_traceFilename, s, append);
                } catch(IOException ex) {
                    Logger.getLogger(ConsoleOutStream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //--------------------------------------------------------------------------
}