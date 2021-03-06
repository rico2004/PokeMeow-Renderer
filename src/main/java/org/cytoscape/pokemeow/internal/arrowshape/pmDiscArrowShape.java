package main.java.org.cytoscape.pokemeow.internal.arrowshape;

import com.jogamp.opengl.GL4;
import main.java.org.cytoscape.pokemeow.internal.algebra.Vector2;

/**
 * Created by ZhangMenghe on 2017/7/5.
 */
public class pmDiscArrowShape extends pmBasicArrowShape {
    public int CircleSegment = 60;
    public float radius = 0.25f;
    public pmDiscArrowShape(){
        super();
        numOfVertices = CircleSegment;
        vertices = new float[3*numOfVertices];
        initPoints();
        setScale(new Vector2(0.75f, 0.5f));
    }

    public pmDiscArrowShape(GL4 gl4){
        super();
        numOfVertices = CircleSegment;
        vertices = new float[3*numOfVertices];
        initPoints();
        initBuffer(gl4);
        setScale(new Vector2(0.75f, 0.5f));
    }

    private void initPoints(){
        float theta = (float)Math.PI *2 / (float)CircleSegment;
        float tangetial_factor = (float)Math.tan(theta);
        float radial_factor = (float)Math.cos(theta);
        float x = radius;
        float y = .0f;
        int count = 3*numOfVertices;
        for(int i=0; i<count; i+=3){
            vertices[i] = x;//radius * (float)Math.cos(i * doublePi / CircleSegment);
            vertices[i+1] = y; //radius * (float)Math.sin( i * doublePi / CircleSegment);
            vertices[i+2]=.0f;
            float tx = -y;
            float ty = x;
            x += tx*tangetial_factor;
            y += ty*tangetial_factor;
            x *= radial_factor;
            y *= radial_factor;
        }

    }

    public void setZorder(GL4 gl4, float new_z){
        int length = vertices.length;
        for(int i =2; i<length; i+=3)
            vertices[i] = new_z;
        dirty = true;
    }
}
