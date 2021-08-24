/**
 * This class provides some simple math relevant to matrices
 */

public class Matrix {

    /** Is used when the wanted calculation is matrix x vector = vector
     * 
     * @param firstMatrix
     * @param v
     * @return
     */
    public static Vector multi(double[][] firstMatrix, Vector v){
        double[] res = new double[3];
        double[] vec = new double[]{v.x,v.y,v.z};

        for (int row = 0; row < firstMatrix.length; row++) {
            res[row] = multiplyMatricesCell(firstMatrix, vec, row);
        }

        return new Vector(res[0], res[1], res[2]);
    }

    private static double multiplyMatricesCell(double[][] firstMatrix, double[] v, int row) {
        double cell = 0;
        for (int i = 0; i < v.length; i++) {
            cell += firstMatrix[row][i] * v[i];
        }
        return cell;
    }
}
/** 
 * This class adds a vector objects which holds x,y,z coordinates.
 * 
 */
class Vector{
    double x,y,z;
    public Vector(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void mult(double mul){
        x *= mul;
        y *= mul;
        z *= mul;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "["+x+","+y+","+z+"]";
    }
}
