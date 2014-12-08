import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlockImageController {

	private JFrame frame = new JFrame("BlockImageViewer");
	private JMenuBar menuBar = new JMenuBar();
	private JPanel topPane = new JPanel();
	private JPanel bottomPane = new JPanel();
	private JLabel imgLabel = new JLabel();
	private JLabel directory = new JLabel();
	private JScrollPane scrollpane = new JScrollPane();
	private static final boolean HEAVYWEIGHT_LIGHTWEIGHT_MIXING = false;

	private File selectedFile;
	private List<String> imgFiles;
	boolean canUpDownf = true;
	String blockprintpath = "C:\\CRiPS\\RonproEditor\\testbase\\BlockPrint";

	SearchBlockImage sbi = new SearchBlockImage();
	Point temp;

	void createWindow() {
		frame.setBounds(100, 100, 800, 550);// �E�C���h�E����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JSlider imgSlider = new JSlider(0, 100, 0);// TopPanel�ɃX���C�_�[�ݒu
		imgSlider.setMajorTickSpacing(10);// �ڐ����
		// imgSlider.setMinorTickSpacing(1);//�ڐ��菬
		imgSlider.setPaintTicks(true);

		topPane.add(imgSlider);
		imgSlider.addChangeListener(new ChangeListener() {// �X���C�_�[�̏���
					public void stateChanged(ChangeEvent e) {

						if (!(imgFiles == null || imgFiles.size() == 0)) {// �X���C�_����������摜�ς��
							imgLabel.setIcon(new ImageIcon(new File(
									selectedFile, imgFiles.get(imgSlider
											.getValue())).getAbsolutePath()));
						}
					}
				});

		JButton back = new JButton("��");// �߂�{�^��
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

		JButton go = new JButton("��");// �i�ރ{�^��
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
			public void actionPerformed(ActionEvent e) {// �f�B���N�g���w��
				JFileChooser filechooser = new JFileChooser(blockprintpath);
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int selected = filechooser.showOpenDialog(frame);
				if (selected == JFileChooser.APPROVE_OPTION) {
					selectedFile = filechooser.getSelectedFile();// chooser

					directory.setText(selectedFile.toString() + "�@���̉摜��\��");// bottom�ɏ�������

					imgFiles = Arrays.asList(sbi.searchBlockImage(selectedFile));// �C���[�W��T��
					if (!(imgFiles == null || imgFiles.size() == 0)) {// �摜�t�@�C������������������
						imgSlider.setMaximum(imgFiles.size() - 1);// �X���C�_�[�̍ő��ύX
						imgSlider.setValue(0);
						imgLabel.setIcon(new ImageIcon(new File(selectedFile,
								imgFiles.get(0)).getAbsolutePath()));// �ŏ��̉摜��\��
					} else {
						directory.setText("�t�@�C�����Ɂ@.jpg�@.png�@�̃t�@�C����������܂���ł���");// �G���[���b�Z�[�W
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
		JMenu sliderSize = new JMenu("SliderSize");// �X���C�h�̃T�C�Y�ύX
		menuBar.add(sliderSize);
		JMenuItem SSbig = new JMenuItem("Long");
		sliderSize.add(SSbig);
		SSbig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imgSlider.setPreferredSize(new Dimension(1000, imgSlider
						.getHeight()));
				imgSlider.revalidate();// �ĕ`��
			}
		});
		JMenuItem SSmiddle = new JMenuItem("Middle");
		sliderSize.add(SSmiddle);
		SSmiddle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgSlider.setPreferredSize(new Dimension(500, imgSlider
						.getHeight()));
				imgSlider.revalidate();
			}
		});
		JMenuItem SSshort = new JMenuItem("short");
		sliderSize.add(SSshort);
		SSshort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imgSlider.setPreferredSize(new Dimension(300, imgSlider
						.getHeight()));
				imgSlider.revalidate();
			}
		});
		JMenu DDM = new JMenu("Drag&DropMode");
		menuBar.add(DDM);
		JMenuItem canODD = new JMenuItem("CanOverDrag&Drop");
		DDM.add(canODD);
		canODD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canUpDownf = false;

			}
		});
		JMenuItem canNODD = new JMenuItem("CanNOTOverDrag&Drop");
		DDM.add(canNODD);
		canNODD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canUpDownf = true;

			}
		});

		JViewport imgView = new JViewport() {// �h���b�O�A���h�h���b�v�ł̈ړ��̎���

			private static final long serialVersionUID = 1L;
			private boolean flag;

			@Override
			public void revalidate() {
				if (!HEAVYWEIGHT_LIGHTWEIGHT_MIXING && flag) {
					return;
				}
				super.revalidate();
			}

			@Override
			public void setViewPosition(Point p) {
				if (HEAVYWEIGHT_LIGHTWEIGHT_MIXING) {
					super.setViewPosition(p);
				} else {
					flag = true;
					super.setViewPosition(p);
					flag = false;
				}
			}
		};

		// imgLabel.setPreferredSize(new Dimension(5000,
		// 5000));//�ŏ��ɉ�ʃT�C�Y�����߂��Ⴄ�I�Ȃ��
		imgLabel.setVerticalAlignment(JLabel.TOP);
		imgLabel.setHorizontalAlignment(JLabel.LEFT);

		MouseAdapter hsl1 = new HandScrollListener();
		imgView.add(imgLabel, BorderLayout.NORTH);
		imgView.addMouseMotionListener(hsl1);
		imgView.addMouseListener(hsl1);

		scrollpane.setViewport(imgView);// �X�N���[���p�l����imgpanel��\��t��
		scrollpane.getViewport().setBackground(Color.BLACK);

		bottomPane.setBackground(Color.WHITE);// Bottom�p�l���i���邩�s�������������j
		bottomPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPane.add(directory);

		frame.add(topPane, BorderLayout.PAGE_START);
		frame.setJMenuBar(menuBar);
		frame.add(scrollpane, BorderLayout.CENTER);
		frame.add(bottomPane, BorderLayout.PAGE_END);

		frame.setVisible(true);
	}

	class HandScrollListener extends MouseAdapter {// �h���b�O�A���h�h���b�v�ł̈ړ��̎���
		private final Cursor defCursor = Cursor
				.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		private final Cursor hndCursor = Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR);
		private final Point pp = new Point();

		@Override
		public void mouseDragged(MouseEvent e) {
			JViewport vport = (JViewport) e.getComponent();
			Point cp = e.getPoint();
			Point vp = vport.getViewPosition(); // =
												// SwingUtilities.convertPoint(vport,
												// 0, 0, label);
			vp.translate(pp.x - cp.x, pp.y - cp.y);
			if (canUpDownf) {
				imgLabel.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
			} else {
				vport.setViewPosition(vp);
			}
			pp.setLocation(cp);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			e.getComponent().setCursor(hndCursor);
			pp.setLocation(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			e.getComponent().setCursor(defCursor);
		}
	}
}
