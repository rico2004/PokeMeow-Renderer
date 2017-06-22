package main.java.org.cytoscape.pokemeow.internal.nodeshape;

import main.java.org.cytoscape.pokemeow.internal.algebra.Matrix4;
import main.java.org.cytoscape.pokemeow.internal.algebra.Vector3;
import main.java.org.cytoscape.pokemeow.internal.rendering.pmSthForDraw;

/**
 * This is the basic stuff to draw nodes, you can set origin, scale and viewMatirx for a single node
 *
 * Created by ZhangMenghe on 2017/6/21.
 *
 */
public class pmBasicNodeShape {
    public pmSthForDraw gsthForDraw;//stuff to draw using gl4,VAO&VBO included
    public Vector3 origin;//origin of node
    public Vector3 scale;//scale of node
    public Matrix4 modelMatrix;//translation*scale
    public Matrix4 viewMattrix;

    public pmBasicNodeShape(){
        origin = new Vector3(.0f,.0f,.0f);
        scale = new Vector3(1.0f,1.0f,1.0f);
        modelMatrix = Matrix4.mult(Matrix4.scale((scale)),Matrix4.translation(origin));
        viewMattrix = Matrix4.identity();
        gsthForDraw = new pmSthForDraw();
    }

    public void setScale(Vector3 new_scale){
        scale = new_scale;
        modelMatrix = Matrix4.mult(Matrix4.scale((scale)),Matrix4.translation(origin));
    }

    public void setScale(float s_scale){
        scale = new Vector3(s_scale,s_scale,s_scale);
        modelMatrix = Matrix4.mult(Matrix4.scale((scale)),Matrix4.translation(origin));
    }

    public void setOrigin(Vector3 new_origin){
        origin = new_origin;
        modelMatrix = Matrix4.mult(Matrix4.scale((scale)),Matrix4.translation(origin));
    }

    public void setViewMattrix(Matrix4 new_viewMatrix){
        viewMattrix = new_viewMatrix;
    }
}