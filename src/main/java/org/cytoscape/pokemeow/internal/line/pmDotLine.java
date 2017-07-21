package main.java.org.cytoscape.pokemeow.internal.line;

import com.jogamp.opengl.GL4;

/**
 * Created by ZhangMenghe on 2017/7/10.
 */

public class pmDotLine extends pmLineVisual{
    public pmDotLine(GL4 gl4, float srcx, float srcy, float destx, float desty, Byte type){
        super(gl4, srcx, srcy, destx, desty, type);
        if(curveType == LINE_STRAIGHT){
            float rlen = Math.abs(srcx-destx) + Math.abs(srcy-desty);
            numOfVertices = lineSegments * (int)rlen +1;
            int numOfPoints = 3*numOfVertices;
            float k = (desty - srcy) / (destx-srcx);
            vertices = new float[numOfPoints];
            float shrink = (destx-srcx)/(numOfVertices-1);
            for(int i=0, n=0; i<numOfPoints; i+=3, n++){
                vertices[i] = srcx + shrink*n;
                vertices[i+1] = srcy + k*(vertices[i] - srcx);
                vertices[i+2] = zorder;
            }
        }
        connectMethod = CONNECT_DOTS;
        initLineVisual(gl4, vertices);
    }
}