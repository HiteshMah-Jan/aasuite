/*
 * EmailTemplate.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "EmailTemplate")
@UITemplate(template = TemplateTabPage.class, gridCount = 2, columnSearch = {"templateName", "subject"})
@Displays({
        @Display(name="templateName",width=-1,mandatory=true),
        @Display(name="subject",width=-1,upCase=false),
        @Display(name="attachment",width=-1,type="Text",upCase=false),
        @Display(name="content",type="TextArea",width=650,height=350,upCase=false)
})
public class EmailTemplate extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "templateName", length=100)
    public String templateName;
    @Column(name = "subject", length = 250)
    public String subject;   
    @Column(name = "content", length=4000)
    public String content;
    @Column(name = "attachment", length = 500)
    public String attachment;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
