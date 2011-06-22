/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

/**
 *
 * @author Entokwaa
 */
public interface IGL {
    String extractGLType();
    String extractGLSubType();
    String extractDefaultFormula();
    String extractCounterPostAccountNumber();
    int extractGLRecordId();
    boolean isPosted();
    void setPosted(boolean posted);
    void save();
    
    boolean hardcodePosting();
    String extractChargeDepartment();
}
