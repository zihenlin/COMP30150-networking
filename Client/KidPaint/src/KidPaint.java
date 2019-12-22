import java.io.File;

public class KidPaint {
	public static void main(String[] args) {

		startPage start = new startPage();
		start.setVisible(true);
		File f = new File(".");
		String absolutePath = f.getAbsolutePath();
		System.out.println(absolutePath);
		
	}
}
