package nl.esi.metis.aisparser.provenance;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import nl.esi.metis.aisparser.annotations.Annotation;

/** An object of this class indicates that information is coming from a file.
 * It can report the file and the line number 
 * (in addition to the {@link #getTime()} method that is required by {@link Provenance}.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class FileSource implements Provenance {
	/** The time when the information was created. */
	private double time;

	/** Returns the time stamp when the information item was created. */
	public double getTime() {
		return time;
	}

	/** Returns a description of the provenance of the information item. */
	public String getProvenanceTree(String layout) {
		try {
			return layout + file.getCanonicalPath() + ":" + lineNumber;
		} catch (IOException e) {
			e.printStackTrace();
			return layout + "Unknown file :" + lineNumber;
		}
	}

	/** The file where the information was found. */
	private File file;
	
	/** Returns the file where the information was found. */
	public File getFile() {
		return file;
	}
		
	/** The line number in the file where the information was found */
	private int lineNumber;
	
	/** The line number in the file where the information was found */
	public int getLineNumber () {
		return lineNumber;
	}

	/** The line in the file / the actual information */
	private String line;
	
	/** The line in the file / the actual information */
	public String getLine () {
		return line;
	}

	/** Constructs a new object describing information that came from a file.
	 * @param file the file where the information was found
	 * @param lineNumber the line number where the information was found.
	 * @param time the time when the information was created (in seconds from January 1, 1970)
	 */
	public FileSource (File file, int lineNumber, String line, double time)
	{
		this.file = file;
		this.lineNumber = lineNumber;
		this.line = line;
		this.time = time;
	}

	/** Returns annotations associated with the file.
	 * @return an array of annotations
	 */
	@Override
	public List<Annotation> getAnnotations() {
		return new ArrayList<Annotation>();
	}
}
