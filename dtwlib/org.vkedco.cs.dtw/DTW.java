package org.vkedco.cs.dtw;

/**
 *
 * @author Vladimir Kulyukin
 */
import java.util.ArrayList;

public class DTW {
    
    static double[] XSEQ_01;
    static double[] YSEQ_01;
    static double[] ZSEQ_01;
    final static IDTWSimilarity SIM = new LDSim01();
    final static int DEFAULT_VAL = Integer.MAX_VALUE;
    final static PNode DEFAULT_PNODE = new PNode(DEFAULT_VAL, -1, -1);

    // These are the sequeneces from Section 4.1 of M. Muller's
    // "Information Retrieval for Music & Motion", Ch. 04, Springer,
    // ISBN: 978-3-540-74047-6.
    // The characters a, b, c, g are encoded as follows:
    // a -> 1
    // b -> 2
    // c -> 3
    // g -> 4
    static {
        // XSEQ_01 is 'abg'
        XSEQ_01 = new double[]{1, 2, 4};
        // YSEQ_01 is 'abbg'
        YSEQ_01 = new double[]{1, 2, 2, 4};
        // ZSEQ_01 is 'agg'
        ZSEQ_01 = new double[]{1, 4, 4};
    }

    // compute 2D DTW cost matrix of doubles for the sequences xseq and yseq where
    // xseq and yseq are also sequences of doubles; isim is a similarity metric.
    static double[][] dpDTWDoubleCostMatrix(double[] xseq, double[] yseq, IDTWSimilarity isim,
            IWarpingWindow iww) {
        double[][] cost_matrix = allocateDoubleCostMatrix(xseq.length, yseq.length);
        dpDTWDoubleCostMatrixAux(xseq, yseq, cost_matrix, isim, iww);
        return cost_matrix;
    }

    static double[][] allocateDoubleCostMatrix(int ncols, int nrows) {
        double[][] cost_matrix = new double[nrows][];
        for (int r = 0; r < nrows; r++) {
            cost_matrix[r] = new double[ncols];
            for (int c = 0; c < ncols; c++) {
                cost_matrix[r][c] = DEFAULT_VAL;
            }
        }
        return cost_matrix;
    }

    // compute 2D DTW cost matrix of PNodes (see PNode.java). PNodes allow
    // one to retrieve warping paths.
    public static PNode[][] dpDTWPNodeCostMatrix(double[] xseq, double[] yseq, IDTWSimilarity isim,
            IWarpingWindow iww) {
        PNode[][] cost_matrix = allocatePNodeCostMatrix(xseq.length, yseq.length);
        dpDTWPNodeCostMatrixAux(xseq, yseq, cost_matrix, isim, iww);
        return cost_matrix;
    }

    public static PNode[][] allocatePNodeCostMatrix(int ncols, int nrows) {
        PNode[][] cost_matrix = new PNode[nrows][];
        for (int r = 0; r < nrows; r++) {
            cost_matrix[r] = new PNode[ncols];
            for (int c = 0; c < ncols; c++) {
                cost_matrix[r][c] = new PNode(DEFAULT_VAL, r, c);
            }
        }
        return cost_matrix;
    }

    static void dpDTWPNodeCostMatrixAux(double[] xseq, double[] yseq,
            PNode[][] cost_matrix, IDTWSimilarity isim, IWarpingWindow iww) {

        final int ncols = xseq.length;
        final int nrows = yseq.length;
        cost_matrix[0][0].setDTWCost(isim.compare(xseq[0], yseq[0]));

        for (int r = 1; r < nrows; r++) {
            if (iww.isIn(r, 0)) {
                cost_matrix[r][0].setDTWCost(cost_matrix[r - 1][0].getDTWCost()
                        + isim.compare(xseq[0], yseq[r]));
                cost_matrix[r][0].addParent(cost_matrix[r - 1][0]);
            }
            else {
                
            }
        }

        for (int c = 1; c < ncols; c++) {
            if (iww.isIn(0, c)) {
                cost_matrix[0][c].setDTWCost(cost_matrix[0][c - 1].getDTWCost()
                        + isim.compare(xseq[c], yseq[0]));
                cost_matrix[0][c].addParent(cost_matrix[0][c - 1]);
            }
        }

        for (int r = 1; r < nrows; r++) {
            for (int c = 1; c < ncols; c++) {
                //System.out.println("r,c=" + r + "," + c);
                if (iww.isIn(r, c)) {
                    double min_cost = Math.min(cost_matrix[r - 1][c - 1].getDTWCost(),
                            cost_matrix[r - 1][c].getDTWCost());
                    min_cost = Math.min(cost_matrix[r][c - 1].getDTWCost(), min_cost);
                    // find and set the parent nodes whose cost == min_cost.
                    // there may be multiple parents.
                    if (cost_matrix[r - 1][c - 1].getDTWCost() == min_cost) {
                        cost_matrix[r][c].addParent(cost_matrix[r - 1][c - 1]);
                    }
                    if (cost_matrix[r - 1][c].getDTWCost() == min_cost) {
                        cost_matrix[r][c].addParent(cost_matrix[r - 1][c]);
                    }
                    if (cost_matrix[r][c - 1].getDTWCost() == min_cost) {
                        cost_matrix[r][c].addParent(cost_matrix[r][c - 1]);
                    }

                    min_cost += isim.compare(xseq[c], yseq[r]);
                    cost_matrix[r][c].setDTWCost(min_cost);
                }
            }
        }
    }

    // retrieve all possible Optimal Warping Paths (OWPs).
    public static ArrayList<PNodePath> getAllOWPs(PNode root, int nrows, int ncols) {
        ArrayList<PNodePath> paths = new ArrayList<PNodePath>();
        PNodePath pnp = new PNodePath();
        pnp.addPathNode(root);
        ArrayList<PNodePath> pNodePathQueue = new ArrayList<PNodePath>();
        pNodePathQueue.add(pnp);
        while (!pNodePathQueue.isEmpty()) {
            //System.out.println(pNodePathQueue.size());
            //System.out.println(pNodePathQueue.get(0));
            pnp = pNodePathQueue.remove(0);
            if (pnp.getHeadPNode().getParents().isEmpty()) {
                //System.out.println("Considering path: " + pnp.toString());
                //if ( DTW.isValidPath(pnp, nrows, ncols) ) {
                paths.add(pnp);
                //}
            } else {
                for (PNode p : pnp.getHeadPNode().getParents()) {
                    pNodePathQueue.add(new PNodePath(pnp, p));
                }
            }
        }
        return paths;
    }

    static void dpDTWDoubleCostMatrixAux(double[] xseq, double[] yseq,
            double[][] cost_matrix, IDTWSimilarity isim, IWarpingWindow iww) {
        int ncols = xseq.length;
        int nrows = yseq.length;
        //System.out.println("ncols=" + ncols);
        //System.out.println("nrows=" + nrows);

        cost_matrix[0][0] = isim.compare(xseq[0], yseq[0]);

        //System.out.println("check 00");
        for (int r = 1; r < nrows; r++) {
            if (iww.isIn(r, 0)) {
                cost_matrix[r][0] = cost_matrix[r - 1][0]
                        + isim.compare(xseq[0], yseq[r]);
            }
            else {
                cost_matrix[r][0] = DEFAULT_VAL;
            }
        }
        //System.out.println("check 01");
        //System.out.println("Initial Cost Matrix 01:");
        //displayCostMatrix(cost_matrix, xseq.length, yseq.length);
        for (int c = 1; c < ncols; c++) {
            if (iww.isIn(0, c)) {
                cost_matrix[0][c] = cost_matrix[0][c - 1]
                        + isim.compare(xseq[c], yseq[0]);
            }
            else {
                cost_matrix[0][c] = DEFAULT_VAL;
            }
        }
        //System.out.println("check 02");
        //System.out.println("Initial Cost Matrix 02:");
        //displayCostMatrix(cost_matrix, xseq.length, yseq.length);
        //System.out.println("check 03");
        for (int r = 1; r < nrows; r++) {
            for (int c = 1; c < ncols; c++) {
                //System.out.println("r,c=" + r + "," + c);
                if (iww.isIn(r, c)) {
                    double min_cost = Math.min(cost_matrix[r - 1][c - 1], cost_matrix[r - 1][c]);
                    min_cost = Math.min(cost_matrix[r][c - 1], min_cost);
                    min_cost += isim.compare(xseq[c], yseq[r]);
                    cost_matrix[r][c] = min_cost;
                }
                else {
                    cost_matrix[r][c] = DEFAULT_VAL;
                }
            }
        }
    }
    
    public static double dpDTW2ColDoubleCost(double[] xseq, double[] yseq, IDTWSimilarity isim, IWarpingWindow iww) {
        double[][] cm = dpDTWTwoColDoubleCostMatrix(xseq, yseq, isim, iww);
        //System.out.println("xseq's length = " + xseq.length);
        //System.out.println("yseq's length = " + yseq.length);
        //System.out.println(cm[0][0]);
        final double sim = cm[yseq.length-1][1];
        cm = null;
        return sim;
    }

    public static double[][] dpDTWTwoColDoubleCostMatrix(double[] xseq, double[] yseq, IDTWSimilarity isim, IWarpingWindow iww) {
        final int NROWS = yseq.length;
        final int NCOLS = xseq.length;
        double cost_matrix[][] = new double[NROWS][2];

        // compute 0th column
        cost_matrix[0][0] = isim.compare(xseq[0], yseq[0]);
        for (int r = 1; r < NROWS; r++) {
            if ( iww.isIn(r, 0) ) {
                cost_matrix[r][0] = cost_matrix[r - 1][0] + isim.compare(xseq[0], yseq[r]);
            }
            else {
                cost_matrix[r][0] = DEFAULT_VAL;
            }
        }
        // compute 1th column
        cost_matrix[0][1] = isim.compare(xseq[1], yseq[0]) + cost_matrix[0][0];
        for (int r = 1; r < NROWS; r++) {
            if ( iww.isIn(r, 1) ) {
                double min_cost = Math.min(cost_matrix[r-1][0], cost_matrix[r-1][1]);
                min_cost = Math.min(cost_matrix[r][0], min_cost);
                min_cost += isim.compare(xseq[1], yseq[r]);
                cost_matrix[r][1] = min_cost;
            }
            else {
                cost_matrix[r][1] = DEFAULT_VAL;
            }
        }
        //System.out.println("check 01");
        //System.out.println("Initial Cost Matrix 01:");
        //displayCostMatrix(cost_matrix, 2, yseq.length);
        double min_cost;
        for (int c = 2; c < NCOLS; c++) {
            DTW.swap_cols_0_and_1(cost_matrix, NROWS);
            if ( iww.isIn(0, c) ) {
                cost_matrix[0][1] = cost_matrix[0][0] + isim.compare(xseq[c], yseq[0]);
            }
            else {
                cost_matrix[0][1] = DEFAULT_VAL;
            }
            for (int r = 1; r < NROWS; r++) {
                if (iww.isIn(r, c)) {
                    min_cost = Math.min(cost_matrix[r-1][0], cost_matrix[r-1][1]);
                    min_cost = Math.min(cost_matrix[r][0], min_cost);
                    min_cost += isim.compare(xseq[c], yseq[r]);
                    cost_matrix[r][1] = min_cost;
                }
                else {
                    cost_matrix[r][1] = DEFAULT_VAL;
                }
            }
            //System.out.println("Cost Matrix:");
            //displayCostMatrix(cost_matrix, 2, yseq.length);
        }
        //System.out.println("End Cost Matrix 02:");
        //display_cost_matrix(cost_matrix, xseq.size(), TEMP_NCOLS);
        return cost_matrix;
    }

    public static PNode[][] dpDTWTwoColPNodeCostMatrix(double[] xseq, double[] yseq,
            IDTWSimilarity isim, IWarpingWindow iww) throws Exception {
        final int NROWS = yseq.length;
        final int NCOLS = xseq.length;
        PNode cost_matrix[][] = new PNode[NROWS][2];
        //System.out.println("NROWS = " + (NROWS-1));
        // compute 0th column
        cost_matrix[0][0] = new PNode(isim.compare(xseq[0], yseq[0]), 0, 0);
        //System.out.println("Computing 0th column");
        for (int r = 1; r < NROWS; r++) {
            if ( iww.isIn(r, 0) ) {
                System.out.println("" + r + ", " + 0 + "");
                cost_matrix[r][0] = new PNode(cost_matrix[r-1][0].getDTWCost()
                                        + isim.compare(xseq[0], yseq[r]), r, 0);
                cost_matrix[r][0].addParent(cost_matrix[r-1][0]);
            }
            else {
                cost_matrix[r][0] = DEFAULT_PNODE;
            }
        }
        //System.out.println("Computing first column");
        // compute 1th column - compute element[0][1]
        cost_matrix[0][1] = new PNode(isim.compare(xseq[1], yseq[0])
                                + cost_matrix[0][0].getDTWCost(), 0, 1);
        cost_matrix[0][1].addParent(cost_matrix[0][0]);
        for (int r = 1; r < NROWS; r++) {
            if ( iww.isIn(r, 1) ) {
                //System.out.println("row=" + r + " col=" + 1);
                //System.out.println(cost_matrix[r-1][0].toString());
                //System.out.println(cost_matrix[r-1][1].toString());
                //System.out.println(cost_matrix[r][0].toString());
                double min_cost = Math.min(cost_matrix[r-1][0].getDTWCost(),
                        cost_matrix[r-1][1].getDTWCost());
                min_cost = Math.min(cost_matrix[r][0].getDTWCost(), min_cost);
                // find and set the parent nodes whose cost == min_cost.
                // there may be multiple parents.
                cost_matrix[r][1] = new PNode(0, r, 1);
                if (cost_matrix[r-1][1].getDTWCost() == min_cost) {
                    cost_matrix[r][1].addParent(cost_matrix[r-1][1]);
                }
                if (cost_matrix[r-1][0].getDTWCost() == min_cost) {
                    cost_matrix[r][1].addParent(cost_matrix[r-1][0]);
                }
                if (cost_matrix[r][0].getDTWCost() == min_cost) {
                    cost_matrix[r][1].addParent(cost_matrix[r][0]);
                }
                min_cost += isim.compare(xseq[1], yseq[r]);
                cost_matrix[r][1].setDTWCost(min_cost);
            }
            else {
                cost_matrix[r][1].setDTWCost(DEFAULT_VAL);
            }
        }
        System.out.println("Cost Matrix:");
        displayCostMatrix(cost_matrix, 2, yseq.length);

        double min_cost;
        for (int c = 2; c < NCOLS; c++) {
            DTW.swap_cols_0_and_1_nr(cost_matrix, NROWS, c);
            if ( iww.isIn(0, c) ) {
                cost_matrix[0][1].setDTWCost(cost_matrix[0][0].getDTWCost()
                        + isim.compare(xseq[c], yseq[0]));
                cost_matrix[0][1].addParent(cost_matrix[0][0]);
            }
            else {
                cost_matrix[0][1].setDTWCost(DEFAULT_VAL);
            }
            for (int r = 1; r < NROWS; r++) {
                if (iww.isIn(r, c)) {
                    //if ( r == 2 && c == 2 ) System.out.println("adding parent to 2,2");
                    min_cost = Math.min(cost_matrix[r - 1][0].getDTWCost(),
                            cost_matrix[r - 1][1].getDTWCost());
                    min_cost = Math.min(cost_matrix[r][0].getDTWCost(), min_cost);
                    // find and set the parent nodes whose cost == min_cost.
                    // there may be multiple parents.
                    if (cost_matrix[r - 1][0].getDTWCost() == min_cost) {
                        if (r == 2 && c == 2) {
                            System.out.println("adding parent to 2,2");
                            System.out.println("parent: " + cost_matrix[r - 1][0].toString());
                            System.out.println("min_cost = " + min_cost);
                        }
                        cost_matrix[r][1].addParent(cost_matrix[r - 1][0]);
                    }
                    if (cost_matrix[r - 1][1].getDTWCost() == min_cost) {
                        if (r == 2 && c == 2) {
                            System.out.println("adding parent to 2,2");
                            System.out.println("parent: " + cost_matrix[r - 1][1].toString());
                            System.out.println("min_cost = " + min_cost);
                        }
                        cost_matrix[r][1].addParent(cost_matrix[r - 1][1]);
                    }
                    if (cost_matrix[r][0].getDTWCost() == min_cost) {
                        if (r == 2 && c == 2) {
                            System.out.println("adding parent to 2,2");
                            System.out.println("parent: " + cost_matrix[r][1].toString());
                            System.out.println("min_cost = " + min_cost);
                        }
                        cost_matrix[r][1].addParent(cost_matrix[r][0]);
                    }
                    //if ( cost_matrix[r][1].getParents().size() > 0) {
                    //    throw new Exception("Parent is undefined!");
                    //}

                    if (r == 2 && c == 2) {
                        System.out.println("node 2,2: " + cost_matrix[r][1].toString());
                        System.out.println("node 2,2 cost: " + cost_matrix[r][1].getDTWCost());
                    }

                    //assert(cost_matrix[r][1].getParents().size() > 0);

                    if (r == 2 && c == 3) {
                        System.out.println("old min_cost = " + min_cost);
                    }
                    min_cost += isim.compare(xseq[c], yseq[r]);
                    if (r == 2 && c == 3) {
                        System.out.println("new min_cost = " + min_cost);
                    }

                    cost_matrix[r][1].setDTWCost(min_cost);

                    if (r == 2 && c == 2) {
                        System.out.println("node 2,2: " + cost_matrix[r][1].toString());
                        System.out.println("node 2,2 cost: " + cost_matrix[r][1].getDTWCost());
                    }
                }
                else {
                    cost_matrix[r][1].setDTWCost(DEFAULT_VAL);
                }

                System.out.println("Cost Matrix:");
                displayCostMatrix(cost_matrix, 2, yseq.length);
            }
        }

        //System.out.println("End Cost Matrix 02:");
        //display_cost_matrix(cost_matrix, xseq.size(), TEMP_NCOLS);
        return cost_matrix;
    }

    static void swap_cols_0_and_1(double[][] cost_matrix, int nrows) {
        for (int r = 0; r < nrows; r++) {
            cost_matrix[r][0] = cost_matrix[r][1];
            cost_matrix[r][1] = 0;
        }
    }

    static void swap_cols_0_and_1_nr(PNode[][] cost_matrix, int nrows, int ncol) {
        for (int r = 0; r < nrows; r++) {
            cost_matrix[r][0] = cost_matrix[r][1];
            cost_matrix[r][1] = new PNode(0, r, ncol);
        }
    }

    public static void displayCostMatrix(double[][] cm, int ncols, int nrows) {
        for (int r = 0; r < nrows; r++) {
            for (int c = 0; c < ncols; c++) {
                if ( cm[r][c] == DTW.DEFAULT_VAL ) 
                    System.out.print("*\t");
                else
                    System.out.print(cm[r][c] + "\t");
            }
            System.out.println();
        }
    }

    public static void displayCostMatrix(PNode[][] cm, int ncols, int nrows) {
        for (int r = 0; r < nrows; r++) {
            for (int c = 0; c < ncols; c++) {
                if ( cm[r][c].getDTWCost() == DTW.DEFAULT_VAL ) {
                    System.out.print("*\t");
                }
                else {
                    System.out.print(cm[r][c].toString() + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void displayPNodePaths(ArrayList<PNodePath> paths) {
        int pnp_counter = 0;
        for (PNodePath pnp : paths) {
            System.out.println("Path " + pnp_counter++ + " " + pnp.toString());
        }
    }

    public static final boolean isValidPath(PNodePath pnp, int nrows, int ncols) {
        if (pnp.length() == 0 || pnp.length() == 1) {
            return true;
        }
        ArrayList<PNode> pnodes = pnp.getPNodes();
        PNode prevPNode = pnodes.get(0);
        PNode lastPNode = pnodes.get(pnp.length() - 1);
        if (prevPNode.getRow() != 0
                || prevPNode.getCol() != 0
                || lastPNode.getRow() != (nrows - 1)
                || lastPNode.getCol() != (ncols - 1)) {
            return false;
        }
        for (int i = 1; i < pnodes.size(); i++) {
            if ((pnodes.get(i).getRow() < pnodes.get(i).getRow())
                    || (pnodes.get(i).getCol() < pnodes.get(i).getCol())) {
                return false;
            }
        }
        return true;
    }

    public final static IWarpingWindow MaxWW = new DefaultWarpingWindow(Integer.MAX_VALUE);
    public final static IWarpingWindow OneWW = new DefaultWarpingWindow(1);
    // 3 Tests of double cost matrices
    static void dtw_double_xseq_01_yseq_01() {
        double[][] cost_matrix = DTW.dpDTWDoubleCostMatrix(XSEQ_01, YSEQ_01, SIM, MaxWW);
        System.out.println("Cost Matrix:");
        displayCostMatrix(cost_matrix, XSEQ_01.length, YSEQ_01.length);
        System.out.println("DTW == " + dpDTW2ColDoubleCost(XSEQ_01, YSEQ_01, SIM, MaxWW));
    }

    static void dtw_double_xseq_01_zseq_01() {
        double[][] cost_matrix = DTW.dpDTWDoubleCostMatrix(XSEQ_01, ZSEQ_01, SIM, MaxWW);
        System.out.println("Cost Matrix:");
        displayCostMatrix(cost_matrix, XSEQ_01.length, ZSEQ_01.length);
        System.out.println("DTW == " + dpDTW2ColDoubleCost(XSEQ_01, ZSEQ_01, SIM, MaxWW));
    }

    static void dtw_double_yseq_01_zseq_01() {
        double[][] cost_matrix = DTW.dpDTWDoubleCostMatrix(YSEQ_01, ZSEQ_01, SIM, MaxWW);
        System.out.println("Cost Matrix:");
        final int ncols = YSEQ_01.length;
        final int nrows = ZSEQ_01.length;
        displayCostMatrix(cost_matrix, YSEQ_01.length, ZSEQ_01.length);
        System.out.println("DTW == " + dpDTW2ColDoubleCost(YSEQ_01, ZSEQ_01, SIM, MaxWW));
    }

    // 3 tests of pnode cost matrices
    static void dtw_pnode_XSEQ_01_YSEQ_01() {
        PNode[][] cost_matrix = DTW.dpDTWPNodeCostMatrix(XSEQ_01, YSEQ_01, SIM, MaxWW);
        System.out.println("Cost Matrix:");
        final int ncols = XSEQ_01.length;
        final int nrows = YSEQ_01.length;
        displayCostMatrix(cost_matrix, ncols, nrows);
        ArrayList<PNodePath> paths = DTW.getAllOWPs(cost_matrix[nrows-1][ncols-1], nrows, ncols);
        DTW.displayPNodePaths(paths);
    }

    static void dtw_pnode_YSEQ_01_ZSEQ_01() {
        PNode[][] cost_matrix = DTW.dpDTWPNodeCostMatrix(YSEQ_01, ZSEQ_01, SIM, MaxWW);
        System.out.println("Cost Matrix:");
        final int ncols = YSEQ_01.length;
        final int nrows = ZSEQ_01.length;
        displayCostMatrix(cost_matrix, ncols, nrows);
        ArrayList<PNodePath> paths = DTW.getAllOWPs(cost_matrix[nrows - 1][ncols - 1], nrows, ncols);
        DTW.displayPNodePaths(paths);
    }

    static void dtw_pnode_XSEQ_01_ZSEQ_01() {
        PNode[][] cost_matrix = DTW.dpDTWPNodeCostMatrix(XSEQ_01, ZSEQ_01, SIM, MaxWW);
        System.out.println("Final Cost Matrix:");
        final int ncols = XSEQ_01.length;
        final int nrows = ZSEQ_01.length;
        displayCostMatrix(cost_matrix, ncols, ncols);
        ArrayList<PNodePath> paths = DTW.getAllOWPs(cost_matrix[nrows - 1][ncols - 1], nrows, ncols);
        DTW.displayPNodePaths(paths);
    }

    static void dtw_2col_double_xseq_01_yseq_01() {
        double[][] cost_matrix = DTW.dpDTWTwoColDoubleCostMatrix(XSEQ_01, YSEQ_01, SIM, MaxWW);
        System.out.println("Final Cost Matrix:");
        DTW.displayCostMatrix(cost_matrix, 2, YSEQ_01.length);
    }

    static void dtw_2col_pnode_xseq_01_yseq_01() {
        PNode[][] cost_matrix = null;

        try {
            cost_matrix = DTW.dpDTWTwoColPNodeCostMatrix(XSEQ_01, YSEQ_01, SIM, MaxWW);
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return;
        }

        final int nrows = YSEQ_01.length;
        final int ncols = XSEQ_01.length;
        DTW.displayCostMatrix(cost_matrix, 2, YSEQ_01.length);
        ArrayList<PNodePath> paths = DTW.getAllOWPs(cost_matrix[YSEQ_01.length - 1][1], nrows, ncols);
        DTW.displayPNodePaths(paths);
    }

    static void dtw_2col_double_yseq_01_zseq_01() {
        IDTWSimilarity isim = new LDSim01();
        double[][] cost_matrix = DTW.dpDTWTwoColDoubleCostMatrix(YSEQ_01, ZSEQ_01, isim, OneWW);
        System.out.println("Cost Matrix:");
        DTW.displayCostMatrix(cost_matrix, 2, ZSEQ_01.length);
    }

    static void dtw_2col_pnode_yseq_01_zseq_01() {
        PNode[][] cost_matrix = null;
        try {
            cost_matrix = DTW.dpDTWTwoColPNodeCostMatrix(YSEQ_01, ZSEQ_01, SIM, OneWW);
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return;
        }
        DTW.displayCostMatrix(cost_matrix, 2, ZSEQ_01.length);
        final int nrows = ZSEQ_01.length;
        final int ncols = YSEQ_01.length;
        ArrayList<PNodePath> paths = DTW.getAllOWPs(cost_matrix[ZSEQ_01.length - 1][1], nrows, ncols);
        DTW.displayPNodePaths(paths);
    }

    static void dtw_2col_double_xseq_01_zseq_01() {
        IDTWSimilarity isim = new LDSim01();
        double[][] cost_matrix = DTW.dpDTWTwoColDoubleCostMatrix(XSEQ_01, ZSEQ_01, isim, MaxWW);
        System.out.println("Final Cost Matrix:");
        DTW.displayCostMatrix(cost_matrix, 2, ZSEQ_01.length);
    }

    static void dtw_2col_pnode_xseq_01_zseq_01() {
        PNode[][] cost_matrix = null;

        try {
            cost_matrix = DTW.dpDTWTwoColPNodeCostMatrix(XSEQ_01, ZSEQ_01, SIM, OneWW);
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return;
        }
        DTW.displayCostMatrix(cost_matrix, 2, ZSEQ_01.length);
        final int nrows = ZSEQ_01.length;
        final int ncols = XSEQ_01.length;
        ArrayList<PNodePath> paths = DTW.getAllOWPs(cost_matrix[ZSEQ_01.length - 1][1], nrows, ncols);
        DTW.displayPNodePaths(paths);
    }
    
    public static void main(String[] args) {
        DTW.dtw_double_xseq_01_yseq_01();
        //DTW.dtw_2col_double_xseq_01_yseq_01();
        
        //DTW.dtw_pnode_XSEQ_01_YSEQ_01();
        //DTW.dtw_2col_pnode_xseq_01_yseq_01();
        
        //DTW.dtw_double_yseq_01_zseq_01();
        //DTW.dtw_2col_double_yseq_01_zseq_01();
        
        //DTW.dtw_pnode_YSEQ_01_ZSEQ_01();
        //DTW.dtw_2col_pnode_yseq_01_zseq_01();
        
        //DTW.dtw_double_xseq_01_zseq_01();
        //DTW.dtw_2col_double_xseq_01_zseq_01();
        
        //DTW.dtw_pnode_XSEQ_01_ZSEQ_01();
        //DTW.dtw_2col_pnode_xseq_01_zseq_01();
        
    }
}

