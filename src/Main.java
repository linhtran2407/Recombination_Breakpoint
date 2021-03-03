import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /*
     * This program returns the indices of 2 recombination break-points on chromosome C1 and C2
     * @author: Linh Tran
     * @ver: Feb 28, 2021
     */
    public static void main(String[] args) throws FileNotFoundException {

        File data = new File("/Users/linhtran/Downloads/BMC/Sophomore/Spring_2021/" +
                "Recombination_Breakpoint/src/data");
        Scanner scan = new Scanner(data);

        String A1 = scan.nextLine();
        String A2 = scan.nextLine();
        String B1 = scan.nextLine();
        String B2 = scan.nextLine();
        String C1 = scan.nextLine();
        String C2 = scan.nextLine();

        char[] chA1 = convertToCharArr(A1);
        char[] chA2 = convertToCharArr(A2);
        char[] chC1 = convertToCharArr(C1);
        char[] chB1 = convertToCharArr(B1);
        char[] chB2 = convertToCharArr(B2);
        char[] chC2 = convertToCharArr(C2);

        System.out.println("Index of the break point of C1: " + breakPoint(chA1, chA2, chC1));
        System.out.println("Index of the break point of C2: " + breakPoint(chB1, chB2, chC2));
    }


    /**
     * Returns an array of characters for the given string
     * @param str The string being converted to array of characters
     * @return The character array ch for String str
     */
    public static char[] convertToCharArr (String str){
        char[] ch = new char[str.length()];

        for (int i=0; i<str.length(); i++){
            ch[i] = str.charAt(i);
        }

        return ch;
    }


    /*
     * The function will loop through the child's sequence and compare to one
     * of the parent's sequences. It stops and returns index of the point (called
     * temporary break-point) where, after that point, the 2 sequences being
     * compared is no longer the same.
     * The idea of temporary break-point means we are not certain if it's the
     * actual break-point, or it just indicates the small subsequence on the child's
     * chromosome that accidentally bears the same nitrogenous bases as its parent's
     * one of the 2 chromosomes.
     *
     * @param parent sequence of the parent's chromosome
     * @param child sequence of the child's chromosome
     * @return Index of a temporary break point
     */
    public static int tempBreakPoint (char[] parent, char[] child) {
        int index = 0;

        for (int i=0; i < child.length-1; i++){
            if (child[i] == parent[i]){
                index = i;
            } else {
                break;
            }
        }
        return index;
    }


    /**
     * Returns the actual break point on the child's chromosome
     * @param parent1 The first mother's or father's chromosome
     * @param parent2 The second mother's or father's chromosome
     * @param child The child's chromosome that is a mosaic of parent1 and parent2
     * @return The actual index of the breakpoint on the child's chromosome
     */
    public static int breakPoint (char[] parent1, char[] parent2, char[] child){
        int breakPoint;
        int tempBreakPoint1 = tempBreakPoint(parent1, child);
        int tempBreakPoint2 = tempBreakPoint(parent2, child);

        /* The actual break-point would be the larger temporary break-point
         * Because the smaller temporary break-point only indicates where
         * a small subsequence of the child's chromosome accidentally bears
         * the same nitrogenous bases as its parent's chromosome.
         */
        if (tempBreakPoint1 > tempBreakPoint2) {
            breakPoint = tempBreakPoint1;
        }  else {
            breakPoint = tempBreakPoint2;
        }

        return breakPoint;
    }
}
