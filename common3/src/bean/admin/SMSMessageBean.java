package bean.admin;

import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "SMSMessageBean")
@UITemplate(template = template.screen.TemplateTabPage.class, showChart = true, criteriaSearch = {"phoneNumber", "code1"}, columnSearch = {"phoneNumber", "code1"}, gridCount = 6)
@Displays({
    @Display(name = "recStatus"),
    @Display(name = "phoneNumber"),
    @Display(name = "code1"),
    @Display(name = "code2"),
    @Display(name = "code3"),
    @Display(name = "code4"),
    @Display(name = "message")
})
@Reports({
    @template.Report(reportFile = "SMSMessages", reportTitle = "SMS Messages")
})
public class SMSMessageBean extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "recordId")
    public int recordId;
    @Column(name = "recStatus")
    public String recStatus;
    @Column(name = "phoneNumber")
    public String phoneNumber;
    @Column(name = "message")
    public String message;
    @Column(name = "code1")
    public String code1;
    @Column(name = "code2")
    public String code2;
    @Column(name = "code3")
    public String code3;
    @Column(name = "code4")
    public String code4;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public static SMSMessageBean extractBean(String string) {
        String str1 = string.substring(0, string.indexOf("\n"));
        String str2 = string.substring(string.indexOf("\n"));
        SMSMessageBean bean = new SMSMessageBean();
        String[] arr = str1.split(",");
        try {
            bean.recStatus=clean(arr[1]);
            bean.phoneNumber=clean(arr[2]);
            bean.message=str2.trim();
            bean.recordId=Integer.parseInt(arr[0]);
        } catch (Exception exp) {
        }
        return bean;
    }

    private static String clean(String str) {
        return str.replace("\"", "");
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCode4() {
        return code4;
    }

    public void setCode4(String code4) {
        this.code4 = code4;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
