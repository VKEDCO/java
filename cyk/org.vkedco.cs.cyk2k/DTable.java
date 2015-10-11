package org.vkedco.cs.cyk2k;

/**
 ******************************************************************************
 * The 2D Table for the CYK Algorithm as explained
 * in the lecture on Cocke-Younger-Kasami Algorithm
 * http://www.slideshare.net/VladimirKulyukin/theory-of-computation-cockeyoungerkasami-algorithm-53717750
 * 
 * @author Vladimir Kulyukin
 *******************************************************************************
 */
import java.util.ArrayList;
import java.util.TreeMap;

class DTable {
	
	private TreeMap<Integer, ArrayList<ArrayList<String>>> table;
	private int n;
	
	private ArrayList<String> newCell() {
		ArrayList<String> dc = new ArrayList<String>();
		dc.add("|");
		return dc;
	}
	
	public DTable(String x) {
		n = x.length();
		table = new TreeMap<Integer, ArrayList<ArrayList<String>>>();
		for(int j = 1; j <= x.length(); j++) {
			ArrayList<ArrayList<String>> rowContents = new ArrayList<ArrayList<String>>();
			rowContents.add(newCell());
			for(int i = 1; i <= n - j + 1; i++) {
				rowContents.add(newCell());
			}
			table.put(new Integer(j), rowContents);
		}
	}
	
	public ArrayList<String> getCell(Integer c, Integer r) {
		//System.out.println(c + ", " + r);
		if ( c.intValue() > n || r.intValue() > n ) {
			return null;
		}
		else {
			return table.get(r).get(c); 
		}
	}
	
	public void addToCell(Integer c, Integer r, ArrayList<String> vars) {
		ArrayList<String> cell = getCell(c, r);
		if ( cell == null ) return;
		for(String v : vars) {
			if ( !cell.contains(v))
				cell.add(v);
		}
	}
	
	public boolean isInCell(Integer c, Integer r, String v) {
		ArrayList<String> cell = getCell(c, r);
		if ( cell == null )
			return false;
		else {
			for(String cv : cell) {
				if ( cv.equals(v) )
					return true;
			}
			return false;
		}
	}
	
	public void display() {
		for(Integer row : table.keySet()) {
			System.out.print(row + ": ");
			ArrayList<ArrayList<String>> rowlist = table.get(row);
			for(ArrayList<String> alist : rowlist.subList(1, rowlist.size())) {
				for(String v : alist) {
					System.out.print(v + " ");
				}
			}
			System.out.println("|");
		}
	}
}

