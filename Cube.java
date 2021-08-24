import java.awt.*;
import java.awt.geom.Line2D;
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
    private Vector[] vectors = new Vector[8];
    private Vector center;
    private int sideLength;
    private boolean drawSides = false;

    public Cube(int centerX,int centerY){
        //Initalizing our beautiful cube with its 8 points, the cube has side length of 1.
        vectors[0] = new Vector(-0.5, -0.5, -0.5);
        vectors[1] = new Vector(0.5, -0.5, -0.5);
        vectors[2] = new Vector(0.5, 0.5, -0.5);
        vectors[3] = new Vector(-0.5, 0.5, -0.5);
        vectors[4] = new Vector(-0.5, -0.5, 0.5);
        vectors[5] = new Vector(0.5, -0.5, 0.5);
        vectors[6] = new Vector(0.5, 0.5, 0.5);
        vectors[7] = new Vector(-0.5, 0.5, 0.5);

        center = new Vector(centerX,centerY,0);

        
        angleX = 0;
        angleY = 0;
        angleZ = 0;

        sideLength = 200;
        
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
        Point[] points = new Point[8];

        int i = 0;
        for (Vector v : vectors) {
            Vector rotated = Matrix.multi(rotationX, v);
            rotated = Matrix.multi(rotationY, rotated);
            rotated= Matrix.multi(rotationZ, rotated);


            Vector projected2d = Matrix.multi(projection, rotated);

            //Scales the vectors, => Width of cube becomes 'sideLength'px
            projected2d.mult(sideLength);
            projectedVectors.add(projected2d);
            points[i] = new Point((int)(center.x + projected2d.x), (int)(center.y + projected2d.y));
            i++;
        }
        g.setColor(Color.blue);

        //Draws the six sides.
        if(drawSides){
            drawSide(g,new int[]{0,1,2,3},points);
            drawSide(g,new int[]{4,5,6,7},points);
            drawSide(g,new int[]{0,3,7,4},points);
            drawSide(g,new int[]{2,3,7,6},points);
            drawSide(g,new int[]{1,2,6,5},points);
            drawSide(g,new int[]{0,1,5,4},points);
        }


        g.setColor(Color.black);
        /*Connect, draw lines between transformed vectors */
        for (int j = 0; j < 4; j++) {
            connectVec(g, j, (j+1)%4, projectedVectors);
            connectVec(g, j + 4, (j+1)%4 + 4, projectedVectors);
            connectVec(g, j, j + 4, projectedVectors);
        }
    }


    public void drawSide(Graphics g,int[] indices,Point[] points){
        int[] xValues = new int[4];
        int[] yValues = new int[4];
        for (int i = 0; i < 4; i++) {
            xValues[i] = (int)points[indices[i]].getX();
            yValues[i] = (int)points[indices[i]].getY();
        }
        g.fillPolygon(xValues,yValues,4);
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

    public void toggleDrawSides(){drawSides = !drawSides;}
    public boolean getDrawSides(){return drawSides;}

    public int getSideLength(){return sideLength;}
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

    public void changeSideLength(int in){
        if(in < 0 && sideLength > 0){
            sideLength += in;
        }else if(in > 0){
            sideLength += in;
        }

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
