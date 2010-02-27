
import util.DataUtil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Entokwaa
 */
public class pad {
    public static void main(String[] args) {
        System.out.println(DataUtil.padEnd("001", '0', 4));
        System.out.println(DataUtil.padEnd("123", '0', 4));
        System.out.println(DataUtil.padEnd("23421", '0', 4));
        System.out.println(DataUtil.padEnd("12212", '0', 4));
        System.out.println(DataUtil.padEnd("62311", '0', 4));
        System.out.println(DataUtil.padEnd("67", '0', 4));
        System.out.println(DataUtil.padEnd("3", '0', 4));
        
        System.out.println("STAR");
        System.out.println(DataUtil.padStart("001", '0', 4));
        System.out.println(DataUtil.padStart("123", '0', 4));
        System.out.println(DataUtil.padStart("23421", '0', 4));
        System.out.println(DataUtil.padStart("12212", '0', 4));
        System.out.println(DataUtil.padStart("62311", '0', 4));
        System.out.println(DataUtil.padStart("67", '0', 4));
        System.out.println(DataUtil.padStart("3", '0', 4));
    }
}
