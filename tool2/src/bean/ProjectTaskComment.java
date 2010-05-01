package bean;

import javax.persistence.Temporal;
import template.UITemplate;
import template.Displays;
import template.Display;
import template.screen.TemplateTabPage;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import service.util.AbstractIBean;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 3, 2009
 * Time: 6:10:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ProjectTaskComment")
@UITemplate(showChart=true, template= TemplateTabPage.class, gridCount = 4, showFiles = true,
        columnSearch = {"comment"}
)
@Displays({
    @Display(name="comment", gridFieldWidth = 3, width=-1),
    @Display(name="commentDate"),
    @Display(name="commentBy", type="PopSearch", linktoBean = Employee.class)
})
public class ProjectTaskComment extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "projectTaskId")
    public int projectTaskId;
    @Column(name = "comment", length = 4000)
    public String comment;
    @Column(name = "commentDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date commentDate;
    @Column(name = "commentBy")
    public int commentBy;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getProjectTaskId() {
        return projectTaskId;
    }

    public void setProjectTaskId(int projectTaskId) {
        this.projectTaskId = projectTaskId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(int commentBy) {
        this.commentBy = commentBy;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "comment");
	}
}
