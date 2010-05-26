/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import util.BeanUtil;

/**
 *
 * @author Entokwaa
 */
public class DrawingUtil {
    public final static int RECTANGLE = 1;
    public final static int ELLIPSE = 2;
    public final static int LINE = 3;
    
    private int usedTool;
    DrawingBean bean;
    List<DrawingBean> drawingList = new ArrayList<DrawingBean>();
    List<JButton> lstBtn = new ArrayList<JButton>();
    JComponent drawComp;
    Color usedColor = Color.CYAN;
    boolean activeDraw = false;

    public void draw(Graphics g) {
        for (DrawingUtil.DrawingBean drawingBean : drawingList) {
            int width = drawingBean.origin.x - drawingBean.destination.x;
            int height = drawingBean.origin.y - drawingBean.destination.y;
            if (width<0) width = width*-1;
            if (height<0) height = height*-1;
            
            int origx = drawingBean.origin.x<drawingBean.destination.x?drawingBean.origin.x:drawingBean.destination.x;
            int origy = drawingBean.origin.y<drawingBean.destination.y?drawingBean.origin.y:drawingBean.destination.y;
            
            g.setColor(drawingBean.color);
            int tool = drawingBean.tool;
            switch (tool) {
            case RECTANGLE:
                g.drawRect(origx, origy, width, height);
                break;
            case ELLIPSE:
                g.drawOval(origx, origy, width, height);
                break;
            case LINE:
                g.drawLine(drawingBean.origin.x, drawingBean.origin.y, drawingBean.destination.x, drawingBean.destination.y);
                break;
            }
        }
    }
    
    public void clearDrawing() {
        drawingList.clear();
        drawComp.repaint();
    }
    
    public void setupTool(JButton btn, int usedTool) {
        lstBtn.add(btn);
        btn.setName(BeanUtil.concat(usedTool,""));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeDraw = true;
                JButton btn = (JButton) e.getSource();
                int usedTool = Integer.parseInt(btn.getName());
                setUsedTool(usedTool);                
                for (JButton mybtn : lstBtn) {
                    mybtn.setEnabled(true);
                }
                btn.setEnabled(false);
            }
        });
    }
    
    public DrawingUtil(JComponent comp, JButton btnClear, JButton btnColor, JButton... btn) {
        drawComp = comp;
        comp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseIsPressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseIsReleased(e);
                JComponent comp = (JComponent) e.getSource();
                comp.repaint();
            }
        });
        for (int i = 0; i < btn.length; i++) {
            JButton mybtn = btn[i];
            setupTool(mybtn, i+1);
        }
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearDrawing();
            }
        });
        btnColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usedColor = JColorChooser.showDialog((JComponent)e.getSource(), "Color Chooser", usedColor);
            }
        });
    }
    
    private void mouseIsPressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            for (JButton mybtn : lstBtn) {
                mybtn.setEnabled(true);
            }
            activeDraw = false;
        }
        bean = new DrawingBean();
        bean.color = usedColor;
        bean.tool = usedTool;
        bean.origin = e.getPoint();
    }
    
    private void mouseIsReleased(MouseEvent e) {
        bean.destination = e.getPoint();
        if (activeDraw) drawingList.add(bean);
    }

    public List<DrawingBean> getDrawingList() {
        return drawingList;
    }

    public int getUsedTool() {
        return usedTool;
    }

    public void setUsedTool(int usedTool) {
        this.usedTool = usedTool;
    }
    
    public static class DrawingBean {
        int tool;
        Point origin;
        Point destination;
        Color color;
    }
}
