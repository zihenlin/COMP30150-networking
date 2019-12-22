import java.io.File;

public class KidPaint {
	public static void main(String[] args) {

		startPage start = new startPage();
		start.setVisible(true);
		File f = new File(".");
		String absolutePath = f.getAbsolutePath();
		System.out.println(absolutePath);

		// UI ui = UI.getInstance();
		// ui.setData(new int[50][50], 20);
		// ui.setVisible(true);
	}
}
