/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.api.logging;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author tpensec
 */
public class ConsoleHandler extends Handler {

    protected PrintStream out;
    protected PrintStream err;
    
    public ConsoleHandler() {
        this.initialize(System.out, System.err);
    }

    public ConsoleHandler(OutputStream out, OutputStream err) {
        this.initialize(out, err);
    }
    
    private void initialize(OutputStream out, OutputStream err) {
        this.out = new PrintStream(out);
        this.err = new PrintStream(err);
        this.setFormatter(ConsoleFormatter.newInstance());
    }
    
    @Override
    public void publish(LogRecord record) {
        
        final String formattedLogRecord = this.getFormatter().format(record);
        final Level level = record.getLevel();
        PrintStream selStream;
        
        if(level == Level.INFO || level == Level.FINE || level == Level.FINER || level == Level.FINEST) {
            selStream = this.out;
        }
        else {
            selStream = this.err;
        }
        
        selStream.println(formattedLogRecord);
    }

    @Override
    public void flush() {
        this.out.flush();
        this.err.flush();
    }

    @Override
    public void close() throws SecurityException {
        this.out.close();
        this.err.close();
    }
}
