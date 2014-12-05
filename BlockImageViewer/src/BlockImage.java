import javax.swing.UIManager;

public class BlockImage {

	public static void main(String[] args) {
		new BlockImage().test();
	}

	void test() {
		// TODO Auto-generated method stub
		initializeLookAndFeel();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {//テスト用のrun
				BlockImageController bic = new BlockImageController();
				bic.createWindow();
			}
		});

	}

	private void initializeLookAndFeel() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
