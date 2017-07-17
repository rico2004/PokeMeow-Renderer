package main.java.org.cytoscape.pokemeow.internal.line;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import main.java.org.cytoscape.pokemeow.internal.algebra.Vector3;
import main.java.org.cytoscape.pokemeow.internal.algebra.Vector4;
import main.java.org.cytoscape.pokemeow.internal.arrowshape.pmBasicArrowShape;

/**
 * Created by ZhangMenghe on 2017/7/6.
 */
public class pmLineVisual extends pmBasicArrowShape {
    private float width = 1.0f;
    public Byte connectMethod = 0; //default to be connect strip
    private int[] connectArray = null;//if use CONNECT_ARRAY, then specify
    public pmBasicArrowShape[] patternList;
    public pmLineVisual [] plineList;
    public Byte drawMethod = 0; //default to use GL
    private boolean useAntialias = true;
    protected float[] vertices;

    public static final byte CONNECT_STRIP = 0;//line to be drawn with each vertex connected to the next.
    public static final byte CONNECT_SEGMENTS = 1;//draw an independent line segment
    public static final byte CONNECT_DOTS = 2;//draw independent dots
    public static final byte CONNECT_PATTERN = 3;//draw triangle fans
    public static final byte CONNECT_ARRAY = 4;//should specify an array of segment pairs
    public static final byte CONNECT_PARALLEL = 5;//should specify an array of segment pairs

    public static final byte DRAWMETHOD_GL = 0;
    public static final byte DRAWMETHOD_AGG = 1;

    public pmLineVisual(GL4 gl4){
        super();
    }
    public pmLineVisual(GL4 gl4, float[] pos){
        super();
        initLineVisual(gl4, pos);
    }
    public pmLineVisual(GL4 gl4, pmLineVisual line){
        super();
        width = line.width;
        connectMethod = line.connectMethod;
        connectArray = line.connectArray;
        patternList = line.patternList;
        plineList = line.plineList;
        drawMethod = line.drawMethod;
        useAntialias = line.useAntialias;
        vertices = line.vertices;
        initLineVisual(gl4, vertices);
    }
    public pmLineVisual(GL4 gl4, float[] pos, Byte mDrawMethod, Byte mconnectMethod){
        super();
        drawMethod = mDrawMethod;
        if(mconnectMethod != CONNECT_ARRAY)
            connectMethod = mconnectMethod;
        initLineVisual(gl4, pos);
    }

    public pmLineVisual(GL4 gl4, float[] pos, Byte mDrawMethod, int [] mconnectArray){
        super();
        drawMethod = mDrawMethod;
        connectMethod = CONNECT_ARRAY;
        connectArray = mconnectArray;
        initLineVisual(gl4, pos);
    }

    public pmLineVisual(GL4 gl4, float[] pos, Byte mDrawMethod, int [] mconnectArray, boolean museAntialias){
        super();
        drawMethod = mDrawMethod;
        connectMethod = CONNECT_ARRAY;
        connectArray = mconnectArray;
        useAntialias = museAntialias;
        initLineVisual(gl4, pos);
    }
    protected void initLineVisual(GL4 gl4, pmBasicArrowShape[] patterns){
        patternList = patterns;
        drawMethod = DRAWMETHOD_GL;
        connectMethod = CONNECT_PATTERN;
        useAntialias = true;
        gl4.glEnable(GL4.GL_LINE_SMOOTH);
    }
    protected  void initLineVisual(GL4 gl4, pmLineVisual line){
        plineList = new pmLineVisual[2];
        plineList[0] = line;
        plineList[1] = new pmLineVisual(gl4, line);
        plineList[1].setOrigin(new Vector3(line.origin.x, line.origin.y + 0.01f, line.origin.z));
        useAntialias = true;
        gl4.glEnable(GL4.GL_LINE_SMOOTH);
    }
    protected void initLineVisual(GL4 gl4, float[] pos){
        vertices = pos;
        if(drawMethod==DRAWMETHOD_GL)
            initLineVisualGL(gl4, pos);
        else
            initLineVisualAGG(gl4, pos);
    }

    private void initLineVisualGL(GL4 gl4, float[] pos){
        if(useAntialias)
            gl4.glEnable(GL4.GL_LINE_SMOOTH);
        else
            gl4.glDisable(GL4.GL_LINE_SMOOTH);

        numOfVertices = pos.length/3;
        if(connectMethod != CONNECT_ARRAY)
            initBuffer(gl4, pos);
        else{
            numOfIndices = connectArray.length;
            initBuffer(gl4, pos, connectArray);
        }
    }
    public void setLineWidth(GL4 gl4, float mwidth){
        width = mwidth;
        gl4.glLineWidth(width);
    }
    private void initLineVisualAGG(GL4 gl4, float[] pos){

    }
}