import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlockImageController extends JFrame implements ActionListener {

	void createWindow() {
		JFrame frame = new JFrame("BlockImage");
		frame.setBounds(100, 100, 800, 550);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton chooseButton = new JButton("画像フォルダの選択");
		chooseButton.addActionListener(this);

		JPanel topPane = new JPanel();
		topPane.setBackground(Color.WHITE);

		topPane.add(chooseButton);

		frame.add(topPane, BorderLayout.PAGE_START);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser filechooser = new JFileChooser();

		int selected = filechooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
			//test用
//			Jlabel dir =new Jlabel(); 
			// label.setText(file.getName());
		}
	}
}
