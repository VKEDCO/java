package org.vkedco.cs.cyk2k;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * **********************************************
 * CNFG Chomsky Normal Form Grammar
 * @author Vladimir Kulyukin
 * **********************************************
 */


public class CNFG {

	// Guaranteed log(n) performance and no extra space overload.
	private TreeMap<String, ArrayList<CNFProductionRHS>> productions;
	private String startSymbol;
	
	public CNFG() {
		productions = new TreeMap<String, ArrayList<CNFProductionRHS>>();
		startSymbol = "S";
	}
	
	public CNFG(String startSym) {
		startSymbol = startSym;
	}
	
	public CNFG(String startSym, TreeMap<String, ArrayList<CNFProductionRHS>> prods) {
		productions = prods;
	}
	
        // add lhs -> rhs1 rhs2, e.g., S -> AB
	public void addProduction(String lhs, String rhs1, String rhs2) {
		ArrayList<CNFProductionRHS> rhs;
		if ( productions.containsKey(lhs) ) {
			rhs = productions.get(lhs);
			rhs.add(new CNFProductionRHS(rhs1, rhs2));
		}
		else {
			rhs = new ArrayList<CNFProductionRHS>();
			rhs.add(new CNFProductionRHS(rhs1, rhs2));
			productions.put(lhs, rhs);
		}
	}
	
	public void addProduction(String lhs, String rhs1) {
		ArrayList<CNFProductionRHS> rhs;
		if ( productions.containsKey(lhs) ) {
			rhs = productions.get(lhs);
			rhs.add(new CNFProductionRHS(rhs1));
		}
		else {
			rhs = new ArrayList<CNFProductionRHS>();
			rhs.add(new CNFProductionRHS(rhs1));
			productions.put(lhs, rhs);
		}
	}
	
	// find all lhs such that lhs -> rhs is a production
	public ArrayList<String> fetchLHS(String rhs) {
		ArrayList<String> lhslist = new ArrayList<String>();
		for(String lhs : productions.keySet()) {
			for(CNFProductionRHS prodrhs : productions.get(lhs)) {
				if ( prodrhs.isRHS1() && prodrhs.isRHS1Equal(rhs) )
					lhslist.add(lhs);
			}
		}
		return lhslist;
	}
	
	// find all lhs such that lhs --> rhs1 rhs2
	public ArrayList<String> fetchLHS(String rhs1, String rhs2) {
		ArrayList<String> lhslist = new ArrayList<String>();
		for(String lhs : productions.keySet()) {
			for(CNFProductionRHS prodrhs : productions.get(lhs)) {
				if ( prodrhs.isRHS2() && prodrhs.isRHS2Equal(rhs1, rhs2)) {
					lhslist.add(lhs);
				}
			}
		}
		return lhslist;
	}
	
	public void display() {
		for(String lhs : productions.keySet()) {
			for(CNFProductionRHS rhs : productions.get(lhs)) {
				System.out.println(lhs + " -> " + rhs.toString());
			}
		}
	}
	
	public String getStartSymbol() {
		return startSymbol;
	}
}
