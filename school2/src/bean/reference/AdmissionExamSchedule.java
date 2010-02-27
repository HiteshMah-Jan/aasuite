package bean.reference;

import template.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import service.util.AbstractIBean;
import service.util.CallService;
import service.ReturnStruct;
import service.ParamStruct;
import util.DateUtil;
import util.DBClient;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 6, 2009
 * Time: 7:13:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "AdmissionExamSchedule")
@UITemplate(template=template.screen.TemplateSinglePage.class,
     columnSearch={"scheduleDate", "examineeCount", "allowedLevel"},
     gridCount=4)
@Displays({
//        @Display(name="schoolYear"),
        @Display(name="scheduleDate"),
        @Display(name="examineeCount", type="Label"),
        @Display(name="allowedLevel", gridFieldWidth = 3, width=-1)
})
@ActionButtons({
    @ActionButton(name = "btnGenerateForSchoolYear", label = "Generate Reference")
})
public class AdmissionExamSchedule extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
//    @Column(name = "schoolYear")
//    public String schoolYear;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "scheduleDate")
    public Date scheduleDate;
    @Column(name = "allowedLevel")
    public String allowedLevel;
    @Column(name = "room")
    public String room;
    @Column(name = "time")
    public String time;
    @Column(name = "examineeCount")
    public String examineeCount;

    public static void main(String[] args) {
        view(AdmissionExamSchedule.class);
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   
    
    public String getAllowedLevel() {
        return allowedLevel;
    }

    public void setAllowedLevel(String allowedLevel) {
        this.allowedLevel = allowedLevel;
    }

    public String getExamineeCount() {
        return examineeCount;
    }

    public void setExamineeCount(String examineeCount) {
        this.examineeCount = examineeCount;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear", "allowedLevel");
    }

    public static void generate(String sy, String dow) {
        Vector vec = new Vector();
        vec.add(sy);
        vec.add(dow);
        CallService.callService(vec, 1, AdmissionExamSchedule.class.getName());
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
        Vector<String> vec = (Vector) param.getData();
        String sy = vec.get(0);
        String dow = vec.get(1);
        List<Date> lst = DateUtil.getAllDates(dow);
        long now = constants.Constants.useDate.getTime();
        for (Date d : lst) {
            if (now > d.getTime()) continue;
//            check if exist
            boolean b = DBClient.exist("SELECT a FROM AdmissionExamSchedule a WHERE a.scheduleDate='"+DateUtil.formatDateToSql(d)+"'");
            if (b) continue;

            AdmissionExamSchedule sched = new AdmissionExamSchedule();
//            sched.schoolYear = sy;
            sched.scheduleDate = d;
            sched.save();
        }
        return null;
    }
}
