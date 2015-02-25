/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleSensorData;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.FileSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * This class supports parsing of AIS messages from File.
 *
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class ReadPath {

    /**
     * A pattern that matches a line in a file with an optional time stamp and a
     * raw AIS message, used by the {@link #readFile readFile} method. The
     * predefined pattern will fit most reasonable file formats. If it does not
     * work for you, you can always change it.
     */
    static public Pattern linePattern = Pattern.compile(".*?(\\d+(?:\\.\\d*)?)?\\D*(!(?:[A-Z]{2})VD[MO].*)");

    /**
     * Reads a file with AIS messages and handles all individual messages in the
     * file.
     *
     * @param file the file to be processed
     */
    static private void readFile(File file, HandleSensorData lineHandler) {
        try {
            FileReader input = new FileReader(file);
            BufferedReader bufRead = new BufferedReader(input);
            double lastTime = 0;
            int lineCount = 0;

            String line; // String that holds current file line
            do {
                line = bufRead.readLine();
                if (line != null) {
                    line = line.trim();
                    lineCount++;
                    Matcher m = linePattern.matcher(line);
                    if (m.matches()) {
                        assert (m.groupCount() == 2);
                        if (m.group(1) != null) {
                            double newTime = Double.parseDouble(m.group(1));
                            if (newTime < lastTime) {
                                NumberFormat nf = NumberFormat.getInstance();
                                nf.setMaximumFractionDigits(3);
                                String differenceString = nf.format((lastTime - newTime));
                                String lastTimeString = nf.format(lastTime);
                                String newTimeString = nf.format(newTime);
                                System.err.println("Time goes backwards on line " + lineCount + " of file " + file.getName() + ": " + differenceString + " seconds backwards from " + lastTimeString + " to " + newTimeString);
                            }
                            lastTime = newTime;
                        }
                        FileSource fs = new FileSource(file, lineCount, line, lastTime);
                        lineHandler.handleSensorData(fs, m.group(2));
                    } else {
                        if (line.length() > 200) {
                            line = line.substring(0, 200);
                        }
                        System.err.println("Could not find VDM message on line number " + lineCount + " of file " + file.getName() + ": " + line);
                    }
                }
            } while (line != null);
            bufRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPath(String aisPath, HandleSensorData lineHandler) {
        readPath(aisPath, lineHandler, -1);
    }

    public void readPath(String aisPath, HandleSensorData lineHandler, long nrFiles) {
        File aisFile = new File(aisPath);
        if (aisFile.isFile()) {
            readFile(aisFile, lineHandler);
            lineHandler.finished();
        } else if (aisFile.isDirectory()) {
            readDirectory(aisFile, lineHandler, nrFiles);
            lineHandler.finished();
        } else {
            System.err.println("Illegal path '" + aisPath + "'");
        }
    }

    /**
     * Read all files in a directory (recursively) It is assumed that files end
     * arbitrarily (e.g. per minute or per hour) such that AIS messages might be
     * spread over multiple files.
     *
     * @param aisFile directory to read
     * @param lineHandler handler of correct lines
     * @param nrFiles maximum number of files to read (-1 indicates read all
     * files)
     */
    private void readDirectory(File aisFile, HandleSensorData lineHandler, long nrFiles) {
        File files[] = aisFile.listFiles();
        Arrays.sort(files);

        for (int i = 0; i < files.length && (nrFiles == -1 || nrofFilesRead < nrFiles); i++) {
            if (files[i].isFile()) {
                nrofFilesRead++;
                readFile(files[i], lineHandler);
            } else {
                readDirectory(files[i], lineHandler, nrFiles);
            }
        }
    }

    private long nrofFilesRead = 0;
}
