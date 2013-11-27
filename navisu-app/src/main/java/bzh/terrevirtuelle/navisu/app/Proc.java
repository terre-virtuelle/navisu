package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.core.utility.Checker;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * NaVisu
 *
 * @author tibus
 * @date 27/11/2013 00:00
 */
public class Proc {

    public static final Builder builder = new BuilderImpl();

    protected static final String SPACE = " ";

    protected String cmd;
    protected List<String> args;

    protected OutputStream out;
    protected OutputStream err;

    protected int returnCode;

    public Proc() {
        args = new LinkedList<>();
    }

    protected void exec() throws IOException, InterruptedException {

        Checker.notNull(cmd, "Command is null.");

        StringBuilder sb = new StringBuilder(cmd + SPACE);
        args.stream().forEach((arg) -> {
            sb.append(arg).append(SPACE);
        });

        final Process process = Runtime.getRuntime().exec(sb.toString());

        //TODO refactor code repetition
        if(null != out) {
            Executors.newSingleThreadExecutor().execute(() -> {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                final PrintStream outStream = new PrintStream(out);
                String line;

                try {
                    while((line = reader.readLine()) != null) {
                        outStream.println(line);
                    }
                } catch (IOException ex) {}
            });
        }

        if(null != err) {
            Executors.newSingleThreadExecutor().execute(() -> {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                final PrintStream outStream = new PrintStream(err);
                String line;

                try {
                    while((line = reader.readLine()) != null) {
                        outStream.println(line);
                    }
                } catch (IOException ex) {}
            });
        }

        this.returnCode = process.waitFor();
    }

    public interface Builder {

        Builder create();
        Builder setCmd(String cmd);
        Builder addArg(String... args);
        Builder setOut(OutputStream out);
        Builder setErr(OutputStream err);
        Proc exec() throws IOException, InterruptedException;
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
    }

    public int getReturnCode() {
        return returnCode;
    }
}
