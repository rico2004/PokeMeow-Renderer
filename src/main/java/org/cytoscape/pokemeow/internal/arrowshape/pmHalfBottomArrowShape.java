package main.java.org.cytoscape.pokemeow.internal.arrowshape;

import com.jogamp.opengl.GL4;

/**
 * Created by ZhangMenghe on 2017/7/5.
 */
public class pmHalfBottomArrowShape extends pmBasicArrowShape{
    public float[] vertices = {
            -0.15f, 0.05f, .0f,
            -0.65f, 0.55f, .0f,
            -0.72f,  0.55f, .0f,
            -0.25f, 0.1f, .0f,
            -0.25f, 0.05f, .0f
    };
    public int []elements = {
            1,2,0,
            2,0,3,
            0,3,4
    };
    public pmHalfBottomArrowShape(GL4 gl4){
        super();
        numOfVertices = 5;
        numOfIndices = 9;
        initBuffer(gl4, vertices, elements);
        setScale(0.5f);
    }
    public void setZorder(GL4 gl4, float new_z){
        vertices[2] = new_z;
        vertices[5] = new_z;
        vertices[8] = new_z;
        vertices[11] = new_z;
        vertices[14] = new_z;
        this.initBuffer(gl4, vertices, elements);
    }
}