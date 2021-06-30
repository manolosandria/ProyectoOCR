package TesseractOCR;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class VentanaOCR extends javax.swing.JFrame {

  private PanelImagen panel;

  public VentanaOCR() {
    initComponents();
    panel = new PanelImagen();
    add(panel, BorderLayout.CENTER);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelTexto = new javax.swing.JPanel();
    scrollTexto = new javax.swing.JScrollPane();
    areaTexto = new javax.swing.JTextArea();
    panelBoton = new javax.swing.JPanel();
    botonAbrir = new javax.swing.JButton();
    panelImagen = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    areaTexto.setColumns(20);
    areaTexto.setRows(5);
    scrollTexto.setViewportView(areaTexto);

    panelTexto.add(scrollTexto);

    getContentPane().add(panelTexto, java.awt.BorderLayout.SOUTH);

    botonAbrir.setText("Abrir imagen");
    botonAbrir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonAbrirActionPerformed(evt);
      }
    });
    panelBoton.add(botonAbrir);

    getContentPane().add(panelBoton, java.awt.BorderLayout.NORTH);

    panelImagen.setLayout(new java.awt.BorderLayout());
    getContentPane().add(panelImagen, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
    JFileChooser seleccion = new JFileChooser();
    int opcion = seleccion.showOpenDialog(this);
    if (opcion == JFileChooser.APPROVE_OPTION) {
      try {
        BufferedImage imagen = ImageIO.read(seleccion.getSelectedFile());
        double d = imagen.getRGB(imagen.getTileWidth() / 2, imagen.getTileHeight() / 2);

        // comparing the values and setting new scaling values that are later on used by RescaleOP
        if (d >= -1.4211511E7 && d < -7254228) {
          procesarImagen(imagen, 3f, -10f);
        } else if (d >= -7254228 && d < -2171170) {
          procesarImagen(imagen, 1.455f, -47f);
        } else if (d >= -2171170 && d < -1907998) {
          procesarImagen(imagen, 1.35f, -10f);
        } else if (d >= -1907998 && d < -257) {
          procesarImagen(imagen, 1.19f, 0.5f);
        } else if (d >= -257 && d < -1) {
          procesarImagen(imagen, 1f, 0.5f);
        } else if (d >= -1 && d < 2) {
          procesarImagen(imagen, 1f, 0.35f);
        }
        panel.setImagen(imagen);
        panel.repaint();
      } catch (IOException ex) {
        System.out.println("Error: "+ex);
      } catch (TesseractException ex) {
        System.out.println("Error de Tesseract: "+ex);
      }
    }
  }//GEN-LAST:event_botonAbrirActionPerformed

    public void procesarImagen(BufferedImage ipimage, float scaleFactor, float offset)throws IOException, TesseractException {
    BufferedImage opimage = new BufferedImage(1050, 1024, ipimage.getType());

    Graphics2D graphic = opimage.createGraphics();

    graphic.drawImage(ipimage, 0, 0, 1050, 1024, null);
    graphic.dispose();

    RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);

    BufferedImage fopimage = rescale.filter(opimage, null);
    ImageIO.write(fopimage, "jpg", new File("D:\\Escuela\\Proyectos\\Graficacion\\Proyectos\\Tess4J\\Testing and learning\\output.png"));

    Tesseract it = new Tesseract();

    it.setDatapath("D:\\Escuela\\Proyectos\\Graficacion\\Proyectos\\Tess4J\\tessdata");

    String str = it.doOCR(fopimage);
    areaTexto.setText(str);
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextArea areaTexto;
  private javax.swing.JButton botonAbrir;
  private javax.swing.JPanel panelBoton;
  private javax.swing.JPanel panelImagen;
  private javax.swing.JPanel panelTexto;
  private javax.swing.JScrollPane scrollTexto;
  // End of variables declaration//GEN-END:variables
}
