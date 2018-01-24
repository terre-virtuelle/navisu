package bzh.terrevirtuelle.navisu.core.util;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public Proc() {
        this.args = new LinkedList<>();

        this.out = System.out;
        this.err = System.err;
        try {
            errors = new FileOutputStream("errors.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Proc.class.getName()).log(Level.SEVERE, null, ex);
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
          System.out.println("sb : " + sb);
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
        public Proc exec(Map<String, String> env) throws IOException, InterruptedException {
            this.proc.exec(env);
            return this.proc;
        }
    }

    public int getReturnCode() {
        return returnCode;
    }
}
