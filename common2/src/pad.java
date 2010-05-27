
import util.DataUtil;
import util.Log;

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
        Log.out(DataUtil.padEnd("001", '0', 4));
        Log.out(DataUtil.padEnd("123", '0', 4));
        Log.out(DataUtil.padEnd("23421", '0', 4));
        Log.out(DataUtil.padEnd("12212", '0', 4));
        Log.out(DataUtil.padEnd("62311", '0', 4));
        Log.out(DataUtil.padEnd("67", '0', 4));
        Log.out(DataUtil.padEnd("3", '0', 4));
        
        Log.out("STAR");
        Log.out(DataUtil.padStart("001", '0', 4));
        Log.out(DataUtil.padStart("123", '0', 4));
        Log.out(DataUtil.padStart("23421", '0', 4));
        Log.out(DataUtil.padStart("12212", '0', 4));
        Log.out(DataUtil.padStart("62311", '0', 4));
        Log.out(DataUtil.padStart("67", '0', 4));
        Log.out(DataUtil.padStart("3", '0', 4));
    }
}
