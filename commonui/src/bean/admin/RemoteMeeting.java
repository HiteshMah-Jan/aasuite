/*
 * AclUser.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.admin.AclUser;
import constants.UserInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import util.BeanUtil;
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
@Table(name = "RemoteMeeting")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"meetingdate", "meetingName", "timeStart"})
@Displays({
        @Display(name="seq"),
        @Display(name="userid"),
        @Display(name="meetingName"),
        @Display(name="meetingdate"),
        @Display(name="timeStart", type="Time"),
        @Display(name="timeEnd", type="Time"),
        @Display(name="servingIp"),
        @Display(name="onGoing")
})
public class RemoteMeeting extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "userid", nullable = false, length = 30)
    public String userid;
    @Column(name = "meetingName", nullable = false)
    public String meetingName;
    @Column(name = "meetingdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date meetingdate;
    @Column(name = "timeStart")
    public String timeStart;
    @Column(name = "timeEnd")
    public String timeEnd;
    @Column(name = "servingIp")
    public String servingIp;
    @Column(name = "onGoing")
    public boolean onGoing;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return BeanUtil.concat(userid," [",seq,"]");
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

    public java.lang.String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(java.lang.String meetingName) {
        this.meetingName = meetingName;
    }

    public java.lang.String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(java.lang.String timeStart) {
        this.timeStart = timeStart;
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

    public String getServingIp() {
        return servingIp;
    }

    public void setServingIp(String servingIp) {
        this.servingIp = servingIp;
    }

    public static void addMeetingRequest(String meetingName, List<AclUser> users) {
        RemoteMeeting meeting = new RemoteMeeting();
        meeting.setMeetingName(meetingName);
        meeting.setUserid(UserInfo.loginUser.getUser().getUserid());
        meeting.setServingIp(NetworkUtil.getIpAddress());
        meeting.setOnGoing(true);
        meeting.setTimeStart(DateUtil.getTime());
        meeting.setMeetingdate(constants.Constants.useDate);

        meeting.save();

        List invitations = new ArrayList();
        for (AclUser user : users) {
            RemoteMeetingInvitation invite = new RemoteMeetingInvitation();
            invite.setOnGoing(false);
            invite.setRemoteMeetingId(meeting.getSeq());
            invite.setUserid(user.getUserid());

            invitations.add(invite);
        }
        meeting.save(invitations);
    }

    public static void endMeeting(int seq) {
        RemoteMeeting meeting = (RemoteMeeting) AbstractIBean.firstRecord("SELECT a FROM RemoteMeeting a WHERE a.seq=",seq);
        meeting.setOnGoing(false);
        meeting.setTimeEnd(DateUtil.getTime());

        meeting.save();
    }

    public java.util.Date getMeetingdate() {
        return meetingdate;
    }

    public void setMeetingdate(java.util.Date meetingdate) {
        this.meetingdate = meetingdate;
    }
}
