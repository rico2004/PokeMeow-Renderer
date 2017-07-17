package main.java.org.cytoscape.pokemeow.internal.arrowshape;

import com.jogamp.opengl.GL4;

/**
 * Created by ZhangMenghe on 2017/7/5.
 */
public class pmTeeArrowShape extends pmBasicArrowShape{
    public float[] vertices = {
            -0.25f,  -0.25f, .0f,
            -0.125f, -0.25f, .0f,
            -0.125f, 0.25f, .0f,
            -0.25f,  0.25f, .0f
    };
    public int []elements = {
            0,2,3,
            0,1,2
    };
    public pmTeeArrowShape(GL4 gl4){
        super();
        numOfVertices = 4;
        numOfIndices = 6;
        this.initBuffer(gl4, vertices, elements);
        setScale(0.5f);
    }
    public pmTeeArrowShape(GL4 gl4, boolean skip){
        super();
        numOfVertices = 4;
        numOfIndices = 6;
        setScale(0.5f);
    }
    public void setZorder(GL4 gl4, float new_z){
        vertices[2] = new_z;
        vertices[5] = new_z;
        vertices[8] = new_z;
        vertices[11] = new_z;
        this.initBuffer(gl4, vertices, elements);
    }
}