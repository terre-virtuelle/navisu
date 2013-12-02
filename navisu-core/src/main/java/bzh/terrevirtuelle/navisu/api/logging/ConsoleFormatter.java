/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.api.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author tpensec
 */
public class ConsoleFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder(df.format(new Date(record.getMillis())));

        sb.append(" [").append(record.getSourceClassName()).append("]");
        sb.append("[").append(record.getSourceMethodName()).append("] ");
        sb.append(record.getMessage());
        
        return sb.toString();
    }   
}
