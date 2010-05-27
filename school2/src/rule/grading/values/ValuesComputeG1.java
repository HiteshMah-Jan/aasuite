package rule.grading.values;

import rule.grading.AbstractGradeComputation;
import util.DBClient;
import bean.Student;
import bean.person.StudentValuesGrading;

public class ValuesComputeG1 extends AbstractGradeComputation {
	StudentValuesGrading val;
	
	public ValuesComputeG1(Student stud, String schoolYear) {
		super(stud, schoolYear);
		val = (StudentValuesGrading) DBClient.getFirstRecord("SELECT a FROM StudentValuesGrading a WHERE a.studentId=",stud.personId," AND a.schoolYear='",schoolYear,"'");
		val.conFinal = (val.con+val.con2+val.con3+val.con4)/4;
        val.motFinal = (val.mot+val.mot2+val.mot3+val.mot4)/4;
        val.effFinal = (val.eff+val.eff2+val.eff3+val.eff4)/4;
        val.resFinal = (val.res+val.res2+val.res3+val.res4)/4;
        val.iniFinal = (val.ini+val.ini2+val.ini3+val.ini4)/4;
        val.perFinal = (val.per+val.per2+val.per3+val.per4)/4;
        val.carFinal = (val.car+val.car2+val.car3+val.car4)/4;
        val.teaFinal = (val.tea+val.tea2+val.tea3+val.tea4)/4;
        val.comFinal = (val.com+val.com2+val.com3+val.com4)/4;
        val.proFinal = (val.pro+val.pro2+val.pro3+val.pro4)/4;
	}

	@Override
	public double computeQuarter1() {
		double total = 0;
		total += val.con+val.mot+val.eff+val.res+val.ini+val.per+val.car+val.tea+val.com+val.pro;
		if (total==0) return 0;
		return (int)(total/10);
	}

	@Override
	public double computeQuarter2() {
		double total = 0;
		total += val.con2+val.mot2+val.eff2+val.res2+val.ini2+val.per2+val.car2+val.tea2+val.com2+val.pro2;
		if (total==0) return 0;
		return (int)(total/10);
	}

	@Override
	public double computeQuarter3() {
		double total = 0;
		total += val.con3+val.mot3+val.eff3+val.res3+val.ini3+val.per3+val.car3+val.tea3+val.com3+val.pro3;
		if (total==0) return 0;
		return (int)(total/10);
	}

	@Override
	public double computeQuarter4() {
		double total = 0;
		total += val.con4+val.mot4+val.eff4+val.res4+val.ini4+val.per4+val.car4+val.tea4+val.com4+val.pro4;
		if (total==0) return 0;
		return (int)(total/10);
	}

	@Override
	public double computeFinalRating() {
		double total = 0;
		total += val.conFinal+val.motFinal+val.effFinal+val.resFinal+val.iniFinal+val.perFinal+val.carFinal+val.teaFinal+val.comFinal+val.proFinal;
		if (total==0) return 0;
		return (int)(total/10);
	}
}
