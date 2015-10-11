package org.vkedco.cs.cyk2k;

/**
 * Right-Hand Side of a CNF Production
 * @author Vladimir Kulyukin
 */
class CNFProductionRHS {
	private String[] mRHS;
	
	public CNFProductionRHS(String rhs) {
		this.mRHS = new String[] { rhs };
	}
	
	public CNFProductionRHS(String rhs1, String rhs2) {
		this.mRHS = new String[] { rhs1, rhs2 };
	}
	
	public boolean isRHS1() {
		return mRHS.length == 1;
	}
	
	public boolean isRHS2() {
		return mRHS.length == 2;
	}
	
	public boolean isRHS1Equal(String rhs) {
		return this.mRHS[0].equals(rhs);
	}
	
	public boolean isRHS2Equal(String rhs1, String rhs2) {
		return this.mRHS[0].equals(rhs1) && this.mRHS[1].equals(rhs2);
	}
	
	@Override
	public String toString() {
		if ( isRHS1() )
			return mRHS[0];
		else if ( isRHS2() )
			return mRHS[0] + " " + mRHS[1];
		else 
			return "";
	}
}


