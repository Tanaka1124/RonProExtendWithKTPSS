import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlockImageController {

	private JFrame frame = new JFrame("BlockImage");
	private JMenuBar menuBar = new JMenuBar();
	private JPanel topPane = new JPanel();
	private JPanel imgPane = new JPanel();
	private JPanel bottomPane = new JPanel();
	private JLabel imgLabel = new JLabel();
	private JLabel directory = new JLabel();
	private File selectedFile;
	private List<String> imgFiles;

	String blockprintpath = "C:\\CRiPS\\RonproEditor\\testbase\\BlockPrint";

	SearchBlockImage sbi = new SearchBlockImage();

	void createWindow() {
		frame.setBounds(100, 100, 800, 550);// ウインドウ生成
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JButton chooseButton = new JButton("画像フォルダの選択");
		// chooseButton.addActionListener(this);
		topPane.setBackground(Color.WHITE);// スライド用TopPanel
		// topPane.add(chooseButton);
		final JSlider imgSlider = new JSlider(0, 100, 0);// TopPanelにスライダー設置
		topPane.add(imgSlider);
		imgSlider.addChangeListener(new ChangeListener() {// スライダーの処理
					public void stateChanged(ChangeEvent e) {
						System.out.println(imgSlider.getValue());

						if (!(imgFiles == null || imgFiles.size() == 0)) {// スライダ動かしたら画像変わる
							imgLabel.setIcon(new ImageIcon(new File(
									selectedFile, imgFiles.get(imgSlider
											.getValue())).getAbsolutePath()));
						}
						// repaint();
					}
				});

		JMenu menu = new JMenu("Menu");// メニュー設置
		menuBar.add(menu);
		JMenuItem openDir = new JMenuItem("OpenFolder");
		menu.add(openDir);
		openDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(blockprintpath);
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int selected = filechooser.showOpenDialog(frame);
				if (selected == JFileChooser.APPROVE_OPTION) {
					selectedFile = filechooser.getSelectedFile();// chooser

					directory.setText(selectedFile.toString());

					imgFiles = Arrays.asList(sbi.searchBlockImage(selectedFile));// イメージを探す
					// System.out.println("nullcheck " + imgFiles == null);
					// System.out.println("imgFiles size = " + imgFiles.size());
					if (!(imgFiles == null || imgFiles.size() == 0)) {// 画像ファイルが見つかったか判定
						imgSlider.setMaximum(imgFiles.size() - 1);// スライダーの最大を変更
						imgLabel.setIcon(new ImageIcon(new File(selectedFile,
								imgFiles.get(0)).getAbsolutePath()));// 最初の画像を表示
					}
				}
			}
		});

		JMenu tool = new JMenu("Tool");// スライダーの表示，非表示を設定
		menuBar.add(tool);
		JMenuItem slider = new JMenuItem("Slider");
		tool.add(slider);
		slider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topPane.setVisible(!topPane.isVisible());

			}
		});

		imgPane.setBackground(Color.black);// img用パネル
		imgPane.add(imgLabel);

		bottomPane.setBackground(Color.WHITE);// Bottoパネル（いるか不明）
		bottomPane.add(directory);

		frame.add(topPane, BorderLayout.PAGE_START);
		frame.setJMenuBar(menuBar);
		frame.add(imgPane, BorderLayout.CENTER);
		frame.add(bottomPane, BorderLayout.PAGE_END);

		frame.setVisible(true);
	}

}
