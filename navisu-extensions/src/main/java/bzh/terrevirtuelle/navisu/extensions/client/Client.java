/*
 * This file is a part of navisu-extensions
 * Copyright (C) 2017 Di Falco Nicola
 *
 * navisu-extensions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * navisu-extensions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bzh.terrevirtuelle.navisu.extensions.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Jan 16, 2017
 */
public class Client {

    private static Client INSTANCE;

    private static BufferedReader in;
    private static PrintWriter out;

    private static int num = 0;

    private static final int PORT = 9090;
    private static String HOST = "localhost";

    public Client() {
        INSTANCE=this;
    }

    public static Client getInstance() {
        return INSTANCE;
    }

    public static void setInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Client();
        }
        HOST = "localhost";
    }

    public static void setInstance(String ip) {
        if (INSTANCE == null) {
            INSTANCE = new Client();
        }
        HOST = ip;
    }

    public static void actionPerformed() {
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending: " + num);
        num++;
        out.println(num);
        String response;
        try {
            response = in.readLine();
            if (response == null || response.equals("")) {
                System.exit(0);
            }
        } catch (IOException ex) {
            response = "Error: " + ex;
        }
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Received: " + response);
    }

    public static void openMenu() {
        if (INSTANCE == null) {
            return;
        }
        String cmd = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><arCommand><cmd>openMenu</cmd><arg></arg></arCommand>");
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending: " + cmd);
        out.println(cmd);
    }

    public static void closeMenu() {
        if (INSTANCE == null) {
            return;
        }
        String menu = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><arCommand><cmd>closeMenu</cmd><arg></arg></arCommand>");
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending: " + menu);
        out.println(menu);
    }

    public static void selectMenu() {
        if (INSTANCE == null) {
            return;
        }
        String menu = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><arCommand><cmd>selectMenu</cmd><arg></arg></arCommand>");
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending: " + menu);
        out.println(menu);
    }

    public static void leftMenu() {
        if (INSTANCE == null) {
            return;
        }
        String menu = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><arCommand><cmd>leftMenu</cmd><arg></arg></arCommand>");
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending: " + menu);
        out.println(menu);
    }

    public static void rightMenu() {
        if (INSTANCE == null) {
            return;
        }
        String menu = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><arCommand><cmd>rightMenu</cmd><arg></arg></arCommand>");
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending: " + menu);
        out.println(menu);
    }

    public  static void connectToServer() {

        try {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Creating Socket");
            Socket socket = null;
            try {
                socket = new Socket(HOST, PORT);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Connected");
out.println("/");
            //To Comment when using LeapMotion.
            //Simulate an Display Command Send to RA
            Client.openMenu();
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void disconnectFromServer() throws IOException {
        in.close();
        in = null;
        out.close();
        out = null;
        HOST = "localhost";
        INSTANCE = null;
    }

    /**
     * Runs the client application.
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connectToServer();
    }
}
