package bzh.terrevirtuelle.navisu.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 27/11/2013 00:00
 */
public class Proc {

    public static final Builder BUILDER = new BuilderImpl();

    protected static final String SPACE = " ";

    protected String cmd;
    protected List<String> args;

    protected OutputStream out;
    protected OutputStream err;
    protected OutputStream errors;
    protected Process process;
    protected int returnCode;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected static Properties properties;
    protected String sep = File.separator;

    public Proc() {
        this.args = new LinkedList<>();

        this.out = System.out;
        this.err = System.err;
        try {
            out = new FileOutputStream("navisu.log", true);
            errors = new FileOutputStream("errors.log", true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Proc.class.getName()).log(Level.SEVERE, null, ex);
        }
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(Proc.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    protected void exec() throws IOException, InterruptedException {

        Checker.notNull(cmd, "Command is null.");

        StringBuilder sb = new StringBuilder(cmd + SPACE);
        args.stream().forEach((arg) -> {
            sb.append(arg).append(SPACE);
        });
        System.out.println("exec : " + sb);
        process = Runtime.getRuntime().exec(sb.toString());

        redirectSreamAsync(process.getInputStream(), out);
        redirectSreamAsync(process.getErrorStream(), errors);

        this.returnCode = process.waitFor();
    }

    protected void exec(Map<String, String> environment)
            throws IOException, InterruptedException {

        Checker.notNull(cmd, "Command is null.");

        StringBuilder sb = new StringBuilder(cmd + SPACE);
        args.stream().forEach((arg) -> {
            sb.append(arg).append(SPACE);
        });
       // System.out.println("cmd : " + sb);
        String[] envp = new String[environment.size()];
        int count = 0;
        for (Map.Entry<String, String> entry : environment.entrySet()) {
            envp[count++] = entry.getKey() + "=" + entry.getValue();
        }
        process = Runtime.getRuntime().exec(sb.toString(), envp);
        redirectSreamAsync(process.getInputStream(), out);
        redirectSreamAsync(process.getErrorStream(), errors);

        this.returnCode = process.waitFor();
    }

    public static void redirectSreamAsync(final InputStream in, final OutputStream out) {
        Executors.newSingleThreadExecutor().execute(() -> {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            final PrintStream outStream = new PrintStream(out);
            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    outStream.println(line);
                }
            } catch (IOException ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, null, ex);
            }
        });
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

    private String createCmdSh(String cmd) {
        String cmdFile = null;
        if (OS.isWindows()) {
            cmdFile = "cmd\\cmd.bat";
        } else if (OS.isLinux()) {
            cmdFile = "cmd/cmd.sh";
        }
      //  System.out.println("cmd : " + cmd);
        try {
            Files.write(Paths.get(cmdFile), cmd.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Proc.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        chmodCmd(cmdFile);
        return cmdFile;
    }

    private void chmodCmd(String cmdFile) {
        File file = new File(cmdFile);
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);
    }

    public int waitFor() throws InterruptedException {
        return process.waitFor();
    }

    public interface Builder {

        Builder create();

        Builder setCmd(String cmd);

        Builder addArg(String... args);

        Builder setOut(OutputStream out);

        Builder setErr(OutputStream err);

        Proc exec() throws IOException, InterruptedException;

        Proc execSh() throws IOException, InterruptedException;

        Proc exec(Map<String, String> env) throws IOException, InterruptedException;
    }

    protected static class BuilderImpl implements Builder {

        protected Proc proc;

        @Override
        public Builder create() {
            this.proc = new Proc();
            return this;
        }

        @Override
        public Builder setCmd(String cmd) {
            this.proc.cmd = cmd;
            return this;
        }

        @Override
        public Builder addArg(String... args) {
            this.proc.args.addAll(Arrays.asList(args));
            return this;
        }

        @Override
        public Builder setOut(OutputStream out) {
            this.proc.out = out;
            return this;
        }

        @Override
        public Builder setErr(OutputStream err) {
            this.proc.err = err;
            return this;
        }

        @Override
        public Proc exec() throws IOException, InterruptedException {
            this.proc.exec();
            return this.proc;
        }

        @Override
        public Proc execSh() throws IOException, InterruptedException {
            this.proc.cmd = proc.createCmdSh(this.proc.cmd);
            this.proc.exec();
            return this.proc;
        }

        @Override
        public Proc exec(Map<String, String> env) throws IOException, InterruptedException {
            this.proc.exec(env);
            return this.proc;
        }
    }

    public int getReturnCode() {
        return returnCode;
    }
}
