package bzh.terrevirtuelle.navisu.server;

import org.capcaval.c3.component.ComponentService;

/**
 * Created with IntelliJ IDEA.
 * User: Serge
 * Date: 18/12/13
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public interface DataServerServices
        extends ComponentService {
    public void init();
    public void init(String hostName, int port);
    public void openSerialPort();
    public void openSerialPort(String portName, int baudRate, int dataBits, int stopBits, int parity);
    public void openFile();
    public void openFile(String fileName);
    public String response(int currentReader);
}
