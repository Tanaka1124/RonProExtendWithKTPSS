import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BlockImageController {

	private JFrame frame = new JFrame("BlockImage");
	private JMenuBar menuBar = new JMenuBar();
	// private JPanel topPane = new JPanel();
	private JPanel imgPane = new JPanel();
	private JPanel bottomPane = new JPanel();
	private JLabel directory = new JLabel();

	void createWindow() {
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JButton chooseButton = new JButton("画像フォルダの選択");
		// chooseButton.addActionListener(this);

		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		JMenuItem openFolder = new JMenuItem("OpenFolder");
		menu.add(openFolder);
		openFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int selected = filechooser.showOpenDialog(frame);
				if (selected == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();

					directory.setText(file.toString());

				}
			}
		});

		// topPane.setBackground(Color.WHITE);
		// topPane.add(chooseButton);

		imgPane.setBackground(Color.black);

		bottomPane.setBackground(Color.WHITE);
		bottomPane.add(directory);

		// frame.add(topPane, BorderLayout.PAGE_START);
		frame.setJMenuBar(menuBar);
		frame.add(imgPane, BorderLayout.CENTER);
		frame.add(bottomPane, BorderLayout.PAGE_END);

		frame.setVisible(true);
	}

}
