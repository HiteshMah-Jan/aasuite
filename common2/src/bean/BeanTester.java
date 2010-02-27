/*
 * BeanTester.java
 * 
 * Created on Sep 25, 2007, 11:52:10 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import test.XMLTester;
import util.BeanUtil;
import util.DataUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class BeanTester {

    public static void testQuery() {
		EntityManager entityManager = javax.persistence.Persistence.createEntityManagerFactory("commonPU").createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT a FROM Awb a");
		List lst = query.getResultList();
//		System.out.println(lst.size());
		entityManager.getTransaction().commit();
		
		entityManager.close();
    }

    public static void displayFields() {
        StringBuffer sb = new StringBuffer();
        List<String> lst = XMLTester.getAllBeanNames();
        for (String string : lst) {
            try {
                sb.setLength(0);
                sb.append("#############   ").append(string).append("   #####################\n\n");
                sb.append("@Displays({\n");
                List<String> fieldNames = getFields(string);
                for (String string1 : fieldNames) {
                    if ("comboDisplay".equals(string1)) continue;
                    sb.append("\t@Display(name=\"").append(string1).append("\"),\n");
                }
                sb.deleteCharAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
                sb.append("\n})\n");
//                System.out.println(sb.toString());
                int c = System.in.read();
            } catch (Exception ex) {
                Logger.getLogger(BeanTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void displayFields(List<String> lst) {
        StringBuffer sb = new StringBuffer();
        for (String string : lst) {
            try {
                sb.setLength(0);
                sb.append("#############   ").append(string).append("   #####################\n\n");
                sb.append("@Entity\n");
                sb.append("@Table(name = \"").append(string).append("\")");
                sb.append("@UITemplate(gridCount = 4, columnSearch = {})\n");
                sb.append("@Displays({\n");
                List<String> fieldNames = getFields(string);
                for (String string1 : fieldNames) {
                    if ("comboDisplay".equals(string1)) continue;
                    sb.append("\t@Display(name=\"").append(string1).append("\"),\n");
                }
                sb.deleteCharAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
                sb.append("\n})\n");
//                System.out.println(sb.toString());
            } catch (Exception ex) {
                Logger.getLogger(BeanTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static List<String> getFields(String bean) {
        try {
            Class currentClass = PanelUtil.getBeanClass(bean);
            List lst = new ArrayList();
            Field[] fields = currentClass.getFields();
            for (Field field : fields) {
                lst.add(field.getName());
            }
            return lst;
        } catch (Exception ex) {
            Logger.getLogger(BeanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /** Creates a new instance of BeanTester */
    public BeanTester() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

}
