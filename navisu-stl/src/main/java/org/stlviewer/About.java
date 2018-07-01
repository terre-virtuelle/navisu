package org.stlviewer;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class About extends JFrame {

	public About() throws HeadlessException {
		super("About");
		setPreferredSize(new Dimension(800, 600));
		createwin();
	}

	private void createwin() {
		
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
		getContentPane().setLayout(layout);
		
		JLabel l1 = new JLabel("About STL Viewer for java");
		Font font = l1.getFont();
		font = font.deriveFont(Font.BOLD, font.getSize2D());
		l1.setFont(font);
		l1.setAlignmentX(LEFT_ALIGNMENT);
		getContentPane().add(l1);

		
		font = new JLabel("").getFont();
		font = font.deriveFont(Font.PLAIN, font.getSize2D()-1.0f);

		StringBuffer sb = new StringBuffer();
		try {
			InputStream istream = About.class.getResourceAsStream("copyright.htm");
			BufferedReader reader = new BufferedReader(new InputStreamReader(istream));
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				if(line.startsWith("<a")) {
					JLabel l;
					//close the prior lines
					if(sb.length()>0) {
						String buf;
						int b = sb.indexOf("<html>");
						if(b!=-1) 
							buf = sb.toString().concat("</html>");
						else
							buf = "<html>".concat(sb.toString()).concat("</html>");					
						l = new JLabel(buf);
						l.setFont(font);
						l.setAlignmentX(LEFT_ALIGNMENT);					
						getContentPane().add(l);
					}
					//create the url button
					String url = parseahref(line);
					line = "<html>".concat(line).concat("</html>");					
					try {
						JButton btn;
						btn = createlinkbtn(url, line);
						btn.setFont(font);
						btn.setAlignmentX(LEFT_ALIGNMENT);
						getContentPane().add(btn);
					} catch (URISyntaxException e) {
						l = new JLabel(line);
						l.setFont(font);
						l.setAlignmentX(LEFT_ALIGNMENT);					
						getContentPane().add(l);
					}
					//start a new buffer
					sb = new StringBuffer();
				} else
					sb.append(line);
			}			
			String buf;
			int b = sb.indexOf("<html>");
			if(b!=-1) 
				buf = sb.toString().concat("</html>");
			else
				buf = "<html>".concat(sb.toString()).concat("</html>");								
			l1 = new JLabel(buf);
			l1.setFont(font);
			l1.setAlignmentX(LEFT_ALIGNMENT);					
			getContentPane().add(l1);
			
		} catch (IOException e) {
			//nothing
		}
		
		
	}

	public String parseahref(String line) {
		int b = line.indexOf("href=");
		int e = line.indexOf('"',b+6); 
		
		return line.substring(b+6, e);
	}

	public JButton createlinkbtn(String url, String text) throws URISyntaxException {
		final URI uri = new URI(url);
		class OpenUrlAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				open(uri);
			}
		}

		JButton button = new JButton();
		//button.setText("<HTML><FONT color=\"#000099\"><U>" + url + "</U></FONT></HTML>");
		button.setText(text);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		//button.setBackground(Color.WHITE);
		button.setToolTipText(uri.toString());
		button.addActionListener(new OpenUrlAction());
		return button;
	}

	private void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				/* TODO: error handling */ }
		} else {
			/* TODO: error handling */ }
	}

}
