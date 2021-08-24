import java.awt.*;
import java.util.ArrayList;
public class Cube {
    private int posX;
    private int posY;
    private double angleX;
    private double angleY;
    private double angleZ;
    private double[][] projection;
    private double[][] rotationZ;
    private double[][] rotationX;
    private double[][] rotationY;
    private Vector[] points = new Vector[8];
    private Vector center;

    public Cube(){
        //Initalizing our beautiful cube with its 8 points, the cube has side length of 1.
        points[0] = new Vector(-0.5, -0.5, -0.5);
        points[1] = new Vector(0.5, -0.5, -0.5);
        points[2] = new Vector(0.5, 0.5, -0.5);
        points[3] = new Vector(-0.5, 0.5, -0.5);
        points[4] = new Vector(-0.5, -0.5, 0.5);
        points[5] = new Vector(0.5, -0.5, 0.5);
        points[6] = new Vector(0.5, 0.5, 0.5);
        points[7] = new Vector(-0.5, 0.5, 0.5);
        
        //The center of the cube, should maybe be taken in as arguments in the constructor.
        center = new Vector(200,200,0);

        
        angleX = 0;
        angleY = 0;
        angleZ = 0;

        
        updateRotationMatrices();


        projection = new double[][]{
            new double[]{1.0,0.0,0.0},
            new double[]{0.0,1.0,0.0}
        };
    }


    /**
     * Updates the three rotation matrices.
     */
    public void updateRotationMatrices(){
        rotationZ = new double[][]{
            new double[]{Math.cos(angleZ),-Math.sin(angleZ),0},
            new double[]{Math.sin(angleZ),Math.cos(angleZ),0},
            new double[]{0,0,1}
        };
        rotationX = new double[][]{
            new double[]{1,0,0},
            new double[]{0, Math.cos(angleX),-Math.sin(angleX)},
            new double[]{0,Math.sin(angleX),Math.cos(angleX)}
        };
        rotationY = new double[][]{
            new double[]{Math.cos(angleY),0,-Math.sin(angleY)},
            new double[]{0,1,0},
            new double[]{Math.sin(angleY),0,Math.cos(angleY)}
        };
    }
    public int getPosX(){return posX;}
    public int getPosY(){return posY;}

    public void render(Graphics g){
        
        g.setColor(Color.black);

        ArrayList<Vector> projectedVectors = new ArrayList<>();
        for (Vector v : points) {
            Vector rotated = Matrix.multi(rotationX, v);
            rotated = Matrix.multi(rotationY, rotated);
            rotated= Matrix.multi(rotationZ, rotated);


            Vector projected2d = Matrix.multi(projection, rotated);

            //Scales the vectors, => Width of cube becomes 200px
            projected2d.mult(200);
            projectedVectors.add(projected2d);
        }

        /*Connect, draw lines between transformed vectors */
        for (int j = 0; j < 4; j++) {
            connectVec(g, j, (j+1)%4, projectedVectors);
            connectVec(g, j + 4, (j+1)%4 + 4, projectedVectors);
            connectVec(g, j, j + 4, projectedVectors);
        }
    }
    /**
     * 
     * @param g graphical component?
     * @param i index of first vector
     * @param j index of second vector
     * @param vectors the list of transformed vectors
     */
    public void connectVec(Graphics g,int i, int j,ArrayList<Vector> vectors){
        g.drawLine(
                    (int)(center.x + vectors.get(i).x),
                    (int)(center.y + vectors.get(i).y),
                    (int)(center.x + vectors.get(j).x),
                    (int)(center.y + vectors.get(j).y)
                );
    }

    public void spinX(double in){
        angleX += Math.toRadians(in);
        updateRotationMatrices();
    }
    public void spinY(double in){
        angleY += Math.toRadians(in);
        updateRotationMatrices();
    }
    public void spinZ(double in){
        angleZ += Math.toRadians(in);
        updateRotationMatrices();
    }

    /**
     * Spins in all directions
     */
    public void spin(){
        angleX += 0.02;
        angleY += 0.02;
        angleZ += 0.02;
        updateRotationMatrices();
    }


    /**
     * 
     * @param in amount to move our cube vertical, positive down, negative up.
     */
    public void moveY(int in){
        posY += in;
    }
    /**
     * 
     * @param in amount to move our cube sideways, positive right, negative left.
     */
    public void moveX(int in){
        posX += in;
    }
}
