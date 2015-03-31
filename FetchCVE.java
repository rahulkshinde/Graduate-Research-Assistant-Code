import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.io.FileWriter;

public class FetchCVE {
	
	public static void searchSVNlogs(List<String> issuesList,
			List<String> svnLogsList) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("tabla.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<String> listResult = new ArrayList<String>();
		

		
		for (int i=0; i<issuesList.size();i++) {
			for (int j=0; j<svnLogsList.size(); j++) {
				if(svnLogsList.get(j).contains(issuesList.get(i)+"\n"))
					listResult.add(svnLogsList.get(j));
			}
			}
		

		System.out.println("\nMatches Found");
		

		for(String str: listResult) {
		  try {
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<String> loadIssues(BufferedReader issues)
			throws IOException {
		List<String> issueList = new ArrayList<String>();

		String[] line = null;
		String str;
		while ((str = issues.readLine()) != null) {
			line = str.split("\\r?\\n");
			for (String temp : line) {
				issueList.add(temp);
			}
		}

		return (ArrayList<String>) issueList;

	}

	public static ArrayList<String> loadSVNlogs(BufferedReader svnLog)
			throws IOException {

		List<String> svnList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		// String[] line = null;
		String str1;
		int count = 0;
		while ((str1 = svnLog.readLine()) != null) {
			if (str1.matches("------------------------------------------------------------------------")
					&& count != 0) {
				svnList.add(sb.toString());
				sb.delete(0, sb.length());
				count = 0;
			}
			if (str1.matches("------------------------------------------------------------------------")
					|| str1.contains("   M /")
					|| str1.contains("   A /")
					|| str1.contains("   D /") || str1.contains("BUG=")) {
				sb.append(str1 + "\n");
				count++;
			}
		}

		return (ArrayList<String>) svnList;

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader issues = new BufferedReader(new FileReader("input.txt"));
		BufferedReader svnLog = new BufferedReader(
				new FileReader("SVNLog2.txt"));

		List<String> issueToSearch = new ArrayList<String>(loadIssues(issues));
		List<String> svnToSearch = new ArrayList<String>(loadSVNlogs(svnLog));

		searchSVNlogs(issueToSearch, svnToSearch);
	}

}
