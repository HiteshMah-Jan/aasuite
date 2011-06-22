/*
 * AttendanceLoginMonitor.java
 *
 * Created on Nov 18, 2007, 8:26:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.payroll;

import bean.person.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSearchOnly;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AttendanceLoginMonitor")
@UITemplate(template = TemplateSearchOnly.class,
                        gridCount = 3,
                        columnSearch = {"personAttendanceId","logTime"},
                        criteriaSearch = {"logTime"})
                       
@Displays({
        @Display(name="personAttendanceId"),
        @Display(name="logTime")
        })
public class AttendanceLoginMonitor extends PersonAttribute implements Serializable {
            public static void main(String[] args) {
        view(AttendanceLoginMonitor.class);
    }
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personAttendanceId")
    public int personAttendanceId;
    @Column(name = "logTime")
    public String logTime;

    
    @Override
      public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public int getPersonAttendanceId() {
        return personAttendanceId;
    }

    public void setPersonAttendanceId(int personAttendanceId) {
        this.personAttendanceId = personAttendanceId;
    }

    @Override
    public int getPersonId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    
    
   
}
