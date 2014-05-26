package new_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class compare {
	public static void main(String[] args) throws IOException {
		BufferedReader r =new BufferedReader(new InputStreamReader(System.in));
		String line ="";
		while ((line = r.readLine())!=null) {
			if (line.equals("1")){
				
				ed();
				r.close();
				break;
			}else if (line.equals("2")){
				
				eq();
				r.close();
				break;
			}
		}
	}
	public static void ed() throws IOException {
		Map<String, int[]> s1 = new HashMap<String, int[]>();
		Map<String, int[]> s2 = new HashMap<String, int[]>();
		Map<String, ArrayList<String>> m1 = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<String>> m2 = new HashMap<String, ArrayList<String>>();
		BufferedReader r =new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<Map<String, int[]>> sm = new ArrayList<Map<String, int[]>>();
		sm.add(s1);
		sm.add(s2);
		ArrayList<Map<String, ArrayList<String>>> m = new ArrayList<Map<String, ArrayList<String>>>();
		m.add(m1);
		m.add(m2);
		for (int i=0;i<=1;i++){
			System.out.println("step "+(i+1));
			line = "";
			while (!(line = r.readLine()).equals("exitok")) {
				String fl = line;
				
				if (line.contains("#endif")){
					continue;
				}
					
				if (line.contains("#define")){
					if (line.contains("//"))
						line = line.substring(0, line.indexOf("//"));
					String[] s =line.replaceAll(" +", " ").split(" ");
					if (s.length<3){
						s=Arrays.copyOf(s, 3);
					}
					m.get(i).put(s[1],new ArrayList<String>( Arrays.asList(new String[]{s[2]})));
					continue;
				}
				if (line.contains("#")){
					line = line.substring(0, line.indexOf("#"));
				}
				if (line.contains("=")){
					int sub = line.indexOf("=");
					
					m.get(i).put(line.substring(0, sub).trim(), new ArrayList<String>( Arrays.asList(line.substring(sub+1, line.length()).trim().split(" "))));
				
				}
				
//				sm.get(i).put(fl, )
			}
			
			
		}
	}
	public static void eq() throws IOException {
		Map<String, ArrayList<String>> m1 = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<String>> m2 = new HashMap<String, ArrayList<String>>();
		BufferedReader r =new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<Map<String, ArrayList<String>>> m = new ArrayList<Map<String, ArrayList<String>>>();
		m.add(m1);
		m.add(m2);
		for (int i=0;i<=1;i++){
			System.out.println("step "+(i+1));
			line = "";
			boolean comment = false;
			while (!(line = r.readLine()).equals("exitok")) {
				if (comment&&!line.contains("*/")) continue;
				if (line.contains("/*")){
					line = line.substring(0,line.indexOf("/*"));
					comment = true;
				}
				if (comment){
					if (line.contains("*/")){
						comment = false;
						line = line.substring(line.indexOf("*/")+2, line.length());
					}
				}
//				System.out.println(line);
				if (line.contains("#endif")){
					continue;
				}
					
				if (line.contains("#define")){
					if (line.contains("//"))
						line = line.substring(0, line.indexOf("//"));
					String[] s =line.replaceAll(" +", " ").split(" ");
					if (s.length<3){
						s=Arrays.copyOf(s, 3);
					}
					System.out.println(s[1]);
					m.get(i).put(s[1],new ArrayList<String>( Arrays.asList(new String[]{s[2]})));
					continue;
				}
				if (line.contains("#")){
					line = line.substring(0, line.indexOf("#"));
				}
				if (line.contains("=")){
					int sub = line.indexOf("=");
					
					m.get(i).put(line.substring(0, sub).trim(), new ArrayList<String>( Arrays.asList(line.substring(sub+1, line.length()).trim().split(" "))));
				}
			}
		}
		System.out.println(m2.keySet());
		m2.remove(null);
		Set<String> m1s = new TreeSet<String>( m1.keySet());
		Set<String> m2s = m2.keySet();
		
		m1s.addAll(m2s);
		
		int l =0;
		int l2 =0;
		for(String s:m1s){
			if (l<s.length())
				l=s.length();
			if (l2<String.valueOf(m1.get(s)).length())
				l2=String.valueOf(m1.get(s)).length();
		}
		
		for (String s:m1s){
			
			if (m1.get(s)==null||!m1.get(s).equals(m2.get(s)))
				System.out.println(s+" : "+String.format("%" + String.valueOf(l-s.length()+1) + "c", ' ')
						+m1.get(s)+"; "+String.format("%" + String.valueOf(l2-String.valueOf(m1.get(s)).length()+1) + "c", ' ')
						+m2.get(s));
		}
		
		System.out.println("bye");
		r.close();
	}

}
