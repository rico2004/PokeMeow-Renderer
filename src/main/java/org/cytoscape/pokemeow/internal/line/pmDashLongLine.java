package main.java.org.cytoscape.pokemeow.internal.line;

import com.jogamp.opengl.GL4;

/**
 * Created by ZhangMenghe on 2017/7/10.
 */

public class pmDashLongLine extends pmLineVisual{
    public pmDashLongLine(GL4 gl4, float srcx, float srcy, float destx, float desty, Byte type){
        super(gl4, srcx, srcy, destx, desty, type);
        lineSegments = 10;
        if(curveType == LINE_STRAIGHT){
            float rlen = Math.abs(srcx-destx) + Math.abs(srcy-desty);
            int pointNum = lineSegments * (int)rlen+1;
            numOfVertices = 3*pointNum;
            float k = (desty - srcy) / (destx-srcx);
            vertices = new float[numOfVertices];
            float shrink = 0.25f*(destx-srcx)/(pointNum-1);
            vertices[0]=srcx; vertices[1]=srcy; vertices[2]=zorder;
            for(int i=3, n=1; i<numOfVertices; i+=3, n++){
                if(n%2==1)
                    vertices[i] = vertices[i-3] + shrink*7;
                else
                    vertices[i] = vertices[i-3] + shrink;
                vertices[i+1] = srcy + k*(vertices[i] - srcx);
                vertices[i+2] = zorder;
            }
        }
        connectMethod = CONNECT_SEGMENTS;
        initLineVisual(gl4, vertices);
    }
}

