/*
 * SalesScriptQuestion.java
 *
 * Created on Apr 26, 2008, 12:20:42 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;

import service.util.AbstractIBean;

import javax.persistence.*;

/**
 *
 * @author ebhoy
 */
@Entity
@Table(name = "SalesScript")
public class SalesScript extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq")
    private Integer seq;
    @Column(name = "question", length = 4000)
    private String question;
    @Column(name = "possibleAnswer")
    private String possibleAnswer;

    public String getPossibleAnswer() {
        return possibleAnswer;
    }

    public void setPossibleAnswer(String possibleAnswer) {
        this.possibleAnswer = possibleAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "question");
	}
}
