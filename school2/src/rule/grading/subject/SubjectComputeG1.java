package rule.grading.subject;

import bean.Student;
import rule.grading.AbstractGradeComputation;

public class SubjectComputeG1 extends AbstractGradeComputation {
	public SubjectComputeG1(Student stud, String schoolYear) {
		super(stud, schoolYear);
	}

	@Override
	public double computeFinalRating() {
		return 0;
	}

	@Override
	public double computeQuarter1() {
		
		return 0;
	}

	@Override
	public double computeQuarter2() {
		return 0;
	}

	@Override
	public double computeQuarter3() {
		return 0;
	}

	@Override
	public double computeQuarter4() {
		return 0;
	}
}
