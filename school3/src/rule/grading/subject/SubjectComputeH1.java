package rule.grading.subject;

import bean.Student;
import rule.grading.AbstractGradeComputation;

public class SubjectComputeH1 extends AbstractGradeComputation {
	public SubjectComputeH1(Student stud, String schoolYear) {
		super(stud, schoolYear);
	}

	@Override
	public double computeFinalRating() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double computeQuarter1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double computeQuarter2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double computeQuarter3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double computeQuarter4() {
		// TODO Auto-generated method stub
		return 0;
	}
}
