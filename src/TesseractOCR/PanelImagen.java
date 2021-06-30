package TesseractOCR;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class PanelImagen extends JPanel {
  private Image imagen;
  
  public PanelImagen(){
  }
  
  public void setImagen(Image imagen){
    this.imagen = imagen;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(imagen, 0, 0, this);
  }

}
