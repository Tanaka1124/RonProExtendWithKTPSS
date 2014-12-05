import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class SearchBlockImage {

	public String[] searchBlockImage(File searchDir) {
		String[] files = searchDir.list(new MyFilter());
		Arrays.sort(files);
		for (int i = 0; i < files.length; ++i) {
			System.out.println(files[i]);
		}
		return files;
	}
}

class MyFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		int index = name.lastIndexOf(".");
		String ext = name.substring(index + 1).toLowerCase();
		return ext.equals("jpg");
	}
}