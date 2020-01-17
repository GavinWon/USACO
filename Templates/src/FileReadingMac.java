import java.io.*;
import java.util.*;

public class FileReadingMac {
	
	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Gold/";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "pump.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "pump.out"));

	}

}
