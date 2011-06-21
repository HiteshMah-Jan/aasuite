package bean.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import util.DBClient;

@Entity
@Table(name = "TempTbl")
public class TempTbl extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "g1")
    public String g1;
    @Column(name = "g2")
    public String g2;
    @Column(name = "g3")
    public String g3;
    @Column(name = "g4")
    public String g4;
    @Column(name = "g5")
    public String g5;
    @Column(name = "g6")
    public String g6;
    @Column(name = "g7")
    public String g7;
    @Column(name = "g8")
    public String g8;
    @Column(name = "g9")
    public String g9;
    @Column(name = "g10")
    public String g10;

    @Column(name = "f1")
    public double f1;
    @Column(name = "f2")
    public double f2;
    @Column(name = "f3")
    public double f3;
    @Column(name = "f4")
    public double f4;
    @Column(name = "f5")
    public double f5;
    @Column(name = "f6")
    public double f6;
    @Column(name = "f7")
    public double f7;
    @Column(name = "f8")
    public double f8;
    @Column(name = "f9")
    public double f9;
    @Column(name = "f10")
    public double f10;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getG1() {
		return g1;
	}

	public void setG1(String g1) {
		this.g1 = g1;
	}

	public String getG2() {
		return g2;
	}

	public void setG2(String g2) {
		this.g2 = g2;
	}

	public String getG3() {
		return g3;
	}

	public void setG3(String g3) {
		this.g3 = g3;
	}

	public String getG4() {
		return g4;
	}

	public void setG4(String g4) {
		this.g4 = g4;
	}

	public String getG5() {
		return g5;
	}

	public void setG5(String g5) {
		this.g5 = g5;
	}

	public String getG6() {
		return g6;
	}

	public void setG6(String g6) {
		this.g6 = g6;
	}

	public String getG7() {
		return g7;
	}

	public void setG7(String g7) {
		this.g7 = g7;
	}

	public String getG8() {
		return g8;
	}

	public void setG8(String g8) {
		this.g8 = g8;
	}

	public String getG9() {
		return g9;
	}

	public void setG9(String g9) {
		this.g9 = g9;
	}

	public String getG10() {
		return g10;
	}

	public void setG10(String g10) {
		this.g10 = g10;
	}

	public double getF1() {
		return f1;
	}

	public void setF1(double f1) {
		this.f1 = f1;
	}

	public double getF2() {
		return f2;
	}

	public void setF2(double f2) {
		this.f2 = f2;
	}

	public double getF3() {
		return f3;
	}

	public void setF3(double f3) {
		this.f3 = f3;
	}

	public double getF4() {
		return f4;
	}

	public void setF4(double f4) {
		this.f4 = f4;
	}

	public double getF5() {
		return f5;
	}

	public void setF5(double f5) {
		this.f5 = f5;
	}

	public double getF6() {
		return f6;
	}

	public void setF6(double f6) {
		this.f6 = f6;
	}

	public double getF7() {
		return f7;
	}

	public void setF7(double f7) {
		this.f7 = f7;
	}

	public double getF8() {
		return f8;
	}

	public void setF8(double f8) {
		this.f8 = f8;
	}

	public double getF9() {
		return f9;
	}

	public void setF9(double f9) {
		this.f9 = f9;
	}

	public double getF10() {
		return f10;
	}

	public void setF10(double f10) {
		this.f10 = f10;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setupIndex() {
		runIndex(1, "g1");
		runIndex(2, "g1","g2");
		runIndex(3, "g1","g2","g3");
		runIndex(4, "g1","g2","g3","g4");
	}
}
