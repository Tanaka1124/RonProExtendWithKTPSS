import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		frame.setBounds(100, 100, 800, 550);// �E�C���h�E����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		topPane.setBackground(Color.WHITE);// �X���C�h�pTopPanel
		final JSlider imgSlider = new JSlider(0, 100, 0);// TopPanel�ɃX���C�_�[�ݒu
		imgSlider.setMajorTickSpacing(10);//�ڐ����
//		imgSlider.setMinorTickSpacing(1);//�ڐ��菬
		imgSlider.setPaintTicks(true);
//    	imgSlider.setPreferredSize(new Dimension(1000, imgSlider.getHeight()));
		topPane.add(imgSlider);
		imgSlider.addChangeListener(new ChangeListener() {// �X���C�_�[�̏���
					public void stateChanged(ChangeEvent e) {

						if (!(imgFiles == null || imgFiles.size() == 0)) {// �X���C�_����������摜�ς��
							imgLabel.setIcon(new ImageIcon(new File(
									selectedFile, imgFiles.get(imgSlider
											.getValue())).getAbsolutePath()));
						}
						// repaint();
					}
				});

		JButton back = new JButton("��");//�߂�{�^��
		topPane.add(back);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(imgFiles == null || imgFiles.size() == 0 || imgSlider
						.getValue() == 0)) {// �{�^���������炢�����߂�
					imgLabel.setIcon(new ImageIcon(new File(selectedFile,
							imgFiles.get(imgSlider.getValue() - 1))
							.getAbsolutePath()));
					imgSlider.setValue(imgSlider.getValue() - 1);
				}
			}
		});

		JButton go = new JButton("��");//�i�ރ{�^��
		topPane.add(go);
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(imgFiles == null || imgFiles.size() == 0 || imgSlider
						.getValue() == (imgFiles.size() - 1))) {// �{�^���������炢�����i��
					imgLabel.setIcon(new ImageIcon(new File(selectedFile,
							imgFiles.get(imgSlider.getValue() + 1))
							.getAbsolutePath()));
					imgSlider.setValue(imgSlider.getValue() + 1);
				}
			}
		});

		JMenu menu = new JMenu("Menu");// ���j���[�ݒu
		menuBar.add(menu);
		JMenuItem openDir = new JMenuItem("OpenFolder");
		menu.add(openDir);
		openDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//�f�B���N�g���w��
				JFileChooser filechooser = new JFileChooser(blockprintpath);
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int selected = filechooser.showOpenDialog(frame);
				if (selected == JFileChooser.APPROVE_OPTION) {
					selectedFile = filechooser.getSelectedFile();// chooser

					directory.setText(selectedFile.toString());//bottom�ɏ�������

					imgFiles = Arrays.asList(sbi.searchBlockImage(selectedFile));// �C���[�W��T��
					if (!(imgFiles == null || imgFiles.size() == 0)) {// �摜�t�@�C������������������
						imgSlider.setMaximum(imgFiles.size() - 1);// �X���C�_�[�̍ő��ύX
						imgSlider.setValue(0);
						imgLabel.setIcon(new ImageIcon(new File(selectedFile,
								imgFiles.get(0)).getAbsolutePath()));// �ŏ��̉摜��\��
					}
				}
			}
		});

		JMenu tool = new JMenu("Tool");// �X���C�_�[�̕\���C��\����ݒ�
		menuBar.add(tool);
		JMenuItem visibleSlider = new JMenuItem("VisibleSlider");
		tool.add(visibleSlider);
		visibleSlider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topPane.setVisible(!topPane.isVisible());

			}
		});
		JMenu sliderSize = new JMenu("SliderSize");
		menuBar.add(sliderSize);
		JMenuItem SSbig = new JMenuItem("Long");
		sliderSize.add(SSbig);
		SSbig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		    	imgSlider.setPreferredSize(new Dimension(1000, imgSlider.getHeight()));
				imgSlider.revalidate();
			}
		});
		JMenuItem SSmiddle = new JMenuItem("Middle");
		sliderSize.add(SSmiddle);
		SSmiddle.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
		    	imgSlider.setPreferredSize(new Dimension(500, imgSlider.getHeight()));
				imgSlider.revalidate();
			}
		});
		JMenuItem SSshort = new JMenuItem("short");
		sliderSize.add(SSshort);
		SSshort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		    	imgSlider.setPreferredSize(new Dimension(300, imgSlider.getHeight()));
				imgSlider.revalidate();
			}
		});
		
		imgPane.setBackground(Color.black);// img�p�p�l��
		imgLabel.setHorizontalTextPosition(JLabel.LEFT);
	    imgLabel.setVerticalTextPosition(JLabel.BOTTOM);
	    imgPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		imgPane.add(imgLabel);

		
		JScrollPane scrollpane = new JScrollPane(imgPane);
		

		bottomPane.setBackground(Color.WHITE);// Bottom�p�l���i���邩�s���j
		bottomPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPane.add(directory);

		frame.add(topPane, BorderLayout.PAGE_START);
		frame.setJMenuBar(menuBar);
		frame.add(scrollpane, BorderLayout.CENTER);
		frame.add(bottomPane, BorderLayout.PAGE_END);

		frame.setVisible(true);
	}

}
