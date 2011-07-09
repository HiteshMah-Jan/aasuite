package rule.grading.values;

import rule.grading.AbstractGradeComputation;
import util.DBClient;
import bean.Student;
import bean.person.StudentValuesGrading;

public class ValuesComputeG5 extends AbstractGradeComputation {
	StudentValuesGrading val;
	
	public ValuesComputeG5(Student stud, String schoolYear) {
		super(stud, schoolYear);
		val = (StudentValuesGrading) DBClient.getFirstRecord("SELECT a FROM StudentValuesGrading a WHERE a.studentId=",stud.personId," AND a.schoolYear='",schoolYear,"'");

//		values promoted
		val.elsFinal = (val.els+val.els2+val.els3+val.els4)/4;
        val.wfrFinal = (val.wfr+val.wfr2+val.wfr3+val.wfr4)/4;
        val.apgwFinal = (val.apgw+val.apgw2+val.apgw3+val.apgw4)/4;
        val.spaaFinal = (val.spaa+val.spaa2+val.spaa3+val.spaa4)/4;
        val.ivaFinal = (val.iva+val.iva2+val.iva3+val.iva4)/4;
        val.islFinal = (val.isl+val.isl2+val.isl3+val.isl4)/4;
        val.aspviFinal = (val.aspvi+val.aspvi2+val.aspvi3+val.aspvi4)/4;
        val.hspdFinal = (val.hspd+val.hspd2+val.hspd3+val.hspd4)/4;
        val.sdFinal = (val.sd+val.sd2+val.sd3+val.sd4)/4;
        val.putFinal = (val.put+val.put2+val.put3+val.put4)/4;
        val.hlewFinal = (val.hlew+val.hlew2+val.hlew3+val.hlew4)/4;
        val.prsFinal = (val.prs+val.prs2+val.prs3+val.prs4)/4;
        val.crFinal = (val.cr+val.cr2+val.cr3+val.cr4)/4;
        val.cwsFinal = (val.cws+val.cws2+val.cws3+val.cws4)/4;
        
//        zl2
        
	}

	@Override
	public double computeQuarter1() {
		double total = 0;
		total += val.els+val.wfr+val.apgw+val.spaa+val.iva+val.isl+val.aspvi+val.hspd+val.sd+val.put+val.hlew+val.prs+val.cr+val.cws;
		return (int)(total/14);
	}

	@Override
	public double computeQuarter2() {
		double total = 0;
		total += val.els2+val.wfr2+val.apgw2+val.spaa2+val.iva2+val.isl2+val.aspvi2+val.hspd2+val.sd2+val.put2+val.hlew2+val.prs2+val.cr2+val.cws2;
		return (int)(total/14);
	}

	@Override
	public double computeQuarter3() {
		double total = 0;
		total += val.els3+val.wfr3+val.apgw3+val.spaa3+val.iva3+val.isl3+val.aspvi3+val.hspd3+val.sd3+val.put3+val.hlew3+val.prs3+val.cr3+val.cws3;
		return (int)(total/14);
	}

	@Override
	public double computeQuarter4() {
		double total = 0;
		total += val.els4+val.wfr4+val.apgw4+val.spaa4+val.iva4+val.isl4+val.aspvi4+val.hspd4+val.sd4+val.put4+val.hlew4+val.prs4+val.cr4+val.cws4;
		return (int)(total/14);
	}

	@Override
	public double computeFinalRating() {
		double total = 0;
		total += val.elsFinal+val.wfrFinal+val.apgwFinal+val.spaaFinal+val.ivaFinal+val.islFinal+val.aspviFinal+val.hspdFinal+val.sdFinal+val.putFinal+val.hlewFinal+val.prsFinal+val.crFinal+val.cwsFinal;
		return (int)(total/14);
	}
}
