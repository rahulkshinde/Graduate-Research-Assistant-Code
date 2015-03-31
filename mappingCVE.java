import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class mappingCVE {

	private static ArrayList<String> addToList(BufferedReader file)
			throws IOException {
		ArrayList<String> mainList = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		String str;
		int count = 0;
		while ((str = file.readLine()) != null) {
			if (str.matches("------------------------------------------------------------------------")
					&& count > 0) {
				temp = reverseList(temp);
				for (String line : temp) {
					mainList.add(line);
				}
				temp.clear();
				count = 0;
			}
			if (str.matches("------------------------------------------------------------------------")
					|| str.contains("BUG=")
					|| str.contains("   M /")
					|| str.contains("   A /") || str.contains("   D /")) {

				temp.add(str);
				count++;
			}

		}
		return mainList;
	}

	private static ArrayList<String> reverseList(ArrayList<String> reverse) {
		ArrayList<String> temp = new ArrayList<String>();
		String bug = "";
		for (int i = reverse.size() - 1; i >= 0; i--) {
			if (reverse.get(i).contains("BUG=")) {
				bug = reverse.get(i);
			} else if (reverse
					.get(i)
					.contains(
							"------------------------------------------------------------------------")) {
				temp.add(reverse.get(i));
			} else {
				String addLine = bug + "," + reverse.get(i);
				temp.add(addLine+"\n");
			}
		}
		return temp;
	}

	public static void main(String[] args) {
		try {
			FileReader file = new FileReader("tabla.txt");
			FileWriter writer = null;
			try {
				writer = new FileWriter("abc.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			BufferedReader buffer = new BufferedReader(file);
			ArrayList<String> output = new ArrayList<String>();
			output = addToList(buffer);
			
			for (String line : output) {
				System.out.println(line);
			}
			
			for(String str: output) {
				  try {
					writer.write(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}


		} catch (IOException e) {
			System.out.println("File not found");
		}

	}
}
