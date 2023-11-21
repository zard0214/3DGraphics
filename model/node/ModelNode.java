package model.node;

import com.jogamp.opengl.GL3;
import model.basic.Model;

/**
 * @author Dr Steve Maddock
 * @version 1.0
 */
public class ModelNode extends SGNode {

  protected Model model;

  public ModelNode(String name, Model m) {
    super(name);
    model = m; 
  }

  public void draw(GL3 gl) {
    model.render(gl, worldTransform);
    for (int i=0; i<children.size(); i++) {
      children.get(i).draw(gl);
    }
  }

}