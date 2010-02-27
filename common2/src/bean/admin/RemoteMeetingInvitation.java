/*
 * AclUser.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.*;
import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import util.DateUtil;
import util.NetworkUtil;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "RemoteMeetingInvitation")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"remoteMeetingId", "userid"})
@Displays({
        @Display(name="seq"),
        @Display(name="remoteMeetingId"),
        @Display(name="userid"),
        @Display(name="timeJoined", type="Time"),
        @Display(name="timeEnd", type="Time"),
        @Display(name="clientIp"),
        @Display(name="joined"),
        @Display(name="onGoing")
})
public class RemoteMeetingInvitation extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "remoteMeetingId", nullable = false)
    public int remoteMeetingId;
    @Column(name = "userid", nullable = false)
    public String userid;
    @Column(name = "timeJoined")
    public String timeJoined;
    @Column(name = "timeEnd")
    public String timeEnd;
    @Column(name = "clientIp")
    public String clientIp;
    @Column(name = "joined")
    public boolean joined;
    @Column(name = "onGoing")
    public boolean onGoing;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return userid + " [" + seq + "]";
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public java.lang.String getUserid() {
        return userid;
    }

    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }

    public java.lang.String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(java.lang.String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean getOnGoing() {
        return onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public int getRemoteMeetingId() {
        return remoteMeetingId;
    }

    public void setRemoteMeetingId(int remoteMeetingId) {
        this.remoteMeetingId = remoteMeetingId;
    }

    public boolean getJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public java.lang.String getTimeJoined() {
        return timeJoined;
    }

    public void setTimeJoined(java.lang.String timeJoined) {
        this.timeJoined = timeJoined;
    }

    public java.lang.String getClientIp() {
        return clientIp;
    }

    public void setClientIp(java.lang.String clientIp) {
        this.clientIp = clientIp;
    }

    public String getMeetingName() {
        if (this.remoteMeetingId == 0) {
            return "";
        }
        RemoteMeeting meet = (RemoteMeeting) AbstractIBean.firstRecord("SELECT a FROM RemoteMeeting a WHERE a.seq="+remoteMeetingId);
        return meet.getMeetingName();
    }

    public String getOrganizer() {
        if (this.remoteMeetingId == 0) {
            return "";
        }
        RemoteMeeting meet = (RemoteMeeting) AbstractIBean.firstRecord("SELECT a FROM RemoteMeeting a WHERE a.seq="+remoteMeetingId);
        String userId = meet.getUserid();
        Person p = (Person) this.selectFirstCache("SELECT a FROM Person a WHERE a.userid='",userId,"'");
        if (p == null) {
            return "Anonymous";
        }
        return p.getFormattedTitle();
    }

    public void joinMeeting() {
        this.setClientIp(NetworkUtil.getIpAddress());
        this.setJoined(true);
        this.setOnGoing(true);
        this.setTimeJoined(DateUtil.getTime());
        this.save();
    }

    public void leaveMeeting() {
        this.setOnGoing(false);
        this.setTimeEnd(DateUtil.getTime());
        this.save();
    }
}
