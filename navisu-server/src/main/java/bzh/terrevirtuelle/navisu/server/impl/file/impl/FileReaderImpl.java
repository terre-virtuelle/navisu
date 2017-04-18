/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.impl.file.impl;

import bzh.terrevirtuelle.navisu.server.impl.file.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;

/**
 *
 * @author Serge
 */
public class FileReaderImpl
        implements FileReader {

    private final Vertx vertx;
    private final int index;
    private int dataIndex = 0;
    private final String fileName;
    private String[] buffer;
    private final int size;

    public FileReaderImpl(int index, Vertx vertx, String fileName, int size) {
        this.index = index;
        this.fileName = fileName;
        this.vertx = vertx;
        this.size = size;
        vertx.fileSystem().readFile(fileName,
                (AsyncResultHandler<Buffer>) (AsyncResult<Buffer> ar) -> {
                    if (ar.succeeded()) {
                        Logger.getLogger(FileReaderImpl.class.getName()).log(Level.INFO, "{0} bytes", Integer.toString(ar.result().length()));
                        buffer = ar.result().toString().split("\n");
                    } else {
                        Logger.getLogger(FileReaderImpl.class.getName()).log(Level.SEVERE, ar.cause().toString());
                    }
                });
    }

    @Override
    public void read() {
        for (int i = dataIndex; i < dataIndex + size; i++) {
            if (i < buffer.length - 2) {
                vertx.eventBus().send("comm-address" + index, buffer[i]);//OK
            }
        }
        dataIndex += size;
    }

    @Override
    public String[] readBuffer() {
        String[] tab = new String[size];
        int j = 0;
        for (int i = dataIndex; i < dataIndex + size; i++) {
            if (i < buffer.length) {
                tab[j] = buffer[i];
                j++;
            } else {
                tab = null;
            }
        }
        dataIndex += size;
        return tab;
    }

    @Override
    public String getData() {
        return null;
    }

    public String[] getBuffer() {
        return buffer;
    }

}
