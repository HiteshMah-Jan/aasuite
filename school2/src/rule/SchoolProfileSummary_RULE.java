package rule;

import java.util.List;

import javax.swing.JComponent;

import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.reference.SchoolProfileSummary;

public class SchoolProfileSummary_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		System.out.println("PROFILE");
		if ("btnExtractEnrollment".equals(comp.getName())) {
			SchoolProfileSummary sum = (SchoolProfileSummary) this.getBean();
			extractAgeProfile(sum);
			extractOtherStatus(sum);
			PanelUtil.showMessage(null, "Please save.");
		}
	}

	public void extractAgeProfile(SchoolProfileSummary sum) {
		String sql = "select gender, age, count(*) from person where persontype='STUDENT' and status='ENROLLED' and age>0 and gender is not null and gradeLevel in ('K1','K2','N1','N2','P1','P2') group by gender, age";
		List<List> lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String grade = "pre";
			String gender = l.get(0).toString();
			int age = DataUtil.getIntValue(l.get(1).toString());
			if (age<4) {
				age = 4;
			}
			int count = DataUtil.getIntValue(l.get(2).toString());
			if ("FEMALE".equals(gender)) {
				String f = "pre"+age+"Male";
				sum.changeValue(f, 0);
				sum.changeValue(f, 0);
			}
			else {
				String f = "pre"+age+"Female";
				sum.changeValue(f, 0);
				sum.changeValue(f, 0);
			}
		}

		lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String gender = l.get(0).toString();
			int age = DataUtil.getIntValue(l.get(1).toString());
			if (age<4) {
				age = 4;
			}
			int count = DataUtil.getIntValue(l.get(2).toString());
			if ("FEMALE".equals(gender)) {
				String f = "pre"+age+"Male";
				int val = DataUtil.getIntValue(BeanUtil.getPropertyValue(sum, f));
				sum.changeValue(f, count+val);
				sum.changeValue(f, count+val);
			}
			else {
				String f = "pre"+age+"Female";
				int val = DataUtil.getIntValue(BeanUtil.getPropertyValue(sum, f));
				sum.changeValue(f, count+val);
				sum.changeValue(f, count+val);
			}
		}

		sql = "select gradelevel, gender, age, count(*) from person where persontype='STUDENT' and status='ENROLLED' and age>0 and gender is not null and gradeLevel is not null group by gradelevel, gender, age";
		lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String grade = l.get(0).toString();
			String gender = l.get(1).toString();
			int age = DataUtil.getIntValue(l.get(2).toString());
			int count = DataUtil.getIntValue(l.get(3).toString());
			if ("|K1|N1|P1|K2|N2|P2|".contains(grade)) {
				continue;
			}
			if ("FEMALE".equals(gender)) {
				String f = grade.toLowerCase()+age+"Male";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
			else {
				String f = grade.toLowerCase()+age+"Female";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
		}

		sql = "select gender, count(*) from person where persontype='STUDENT' and status='ENROLLED' and age>0 and gender is not null and gradeLevel in ('N1','N2','K1','K2','P1','P2') group by gender";
		lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String gender = l.get(0).toString();
			int count = DataUtil.getIntValue(l.get(1).toString());
			if ("FEMALE".equals(gender)) {
				String f = "preMale";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
			else {
				String f = "preFemale";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
		}
		sql = "select gradelevel, gender, count(*) from person where persontype='STUDENT' and status='ENROLLED' and age>0 and gender is not null and gradeLevel is not null group by gradelevel, gender";
		lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String grade = l.get(0).toString();
			String gender = l.get(1).toString();
			int count = DataUtil.getIntValue(l.get(2).toString());
			if ("|K1|N1|P1|K2|N2|P2|".contains(grade)) {
				continue;
			}
			if ("FEMALE".equals(gender)) {
				String f = grade.toLowerCase()+"Male";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
			else {
				String f = grade.toLowerCase()+"Female";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
		}
	}
	
	public void extractOtherStatus(SchoolProfileSummary sum) {
		String sql = "select gender, addStatus, count(*) from person where persontype='STUDENT' and status='ENROLLED' and age>0 and gender is not null and gradeLevel in ('K1','K2','N1','N2','P1','P2') group by gender, addStatus";
		List<List> lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String grade = "pre";
			String gender = l.get(0).toString();
			String addStatus = l.get(1)+"";
			if (addStatus.toUpperCase().contains("TRANS")) {
				addStatus = "Transfer";
			}
			else if (addStatus.toUpperCase().contains("BALIK")) {
				addStatus = "Back";
			}
			else if (addStatus.toUpperCase().contains("SPED")) {
				addStatus = "Sped";
			}
			else if (addStatus.toUpperCase().contains("MUSL")) {
				addStatus = "Muslim";
			}
			else if (addStatus.toUpperCase().contains("REPE")) {
				addStatus = "Repeat";
			}
			else if (addStatus.toUpperCase().contains("INDI")) {
				addStatus = "Indigenous";
			}
			else {
				continue;
			}
			if ("FEMALE".equals(gender)) {
				String f = grade+addStatus+"Male";
				sum.changeValue(f, 0);
				sum.changeValue(f, 0);
			}
			else {
				String f = grade+addStatus+"Female";
				sum.changeValue(f, 0);
				sum.changeValue(f, 0);
			}
		}

		lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String grade = "pre";
			String gender = l.get(0).toString();
			String addStatus = l.get(1)+"";
			if (addStatus.toUpperCase().contains("TRANS")) {
				addStatus = "Transfer";
			}
			else if (addStatus.toUpperCase().contains("BALIK")) {
				addStatus = "Back";
			}
			else if (addStatus.toUpperCase().contains("SPED")) {
				addStatus = "Sped";
			}
			else if (addStatus.toUpperCase().contains("MUSL")) {
				addStatus = "Muslim";
			}
			else if (addStatus.toUpperCase().contains("REPE")) {
				addStatus = "Repeat";
			}
			else if (addStatus.toUpperCase().contains("INDI")) {
				addStatus = "Indigenous";
			}
			else {
				continue;
			}
			int count = DataUtil.getIntValue(l.get(2).toString());
			if ("FEMALE".equals(gender)) {
				String f = grade+addStatus+"Male";
				int val = DataUtil.getIntValue(BeanUtil.getPropertyValue(sum, f));
				sum.changeValue(f, count+val);
				sum.changeValue(f, count+val);
			}
			else {
				String f = grade+addStatus+"Female";
				int val = DataUtil.getIntValue(BeanUtil.getPropertyValue(sum, f));
				sum.changeValue(f, count+val);
				sum.changeValue(f, count+val);
			}
		}

		sql = "select gradelevel, gender, addStatus, count(*) from person where persontype='STUDENT' and status='ENROLLED' and age>0 and gender is not null and gradeLevel is not null group by gradelevel, gender, addStatus";
		lst = DBClient.getListNative(sql);
		for (List l:lst) {
			String grade = l.get(0).toString();
			String gender = l.get(1).toString();
			String addStatus = l.get(2)+"";
			if (addStatus.toUpperCase().contains("TRANS")) {
				addStatus = "Transfer";
			}
			else if (addStatus.toUpperCase().contains("BALIK")) {
				addStatus = "Back";
			}
			else if (addStatus.toUpperCase().contains("SPED")) {
				addStatus = "Sped";
			}
			else if (addStatus.toUpperCase().contains("MUSL")) {
				addStatus = "Muslim";
				String f = grade.toLowerCase()+addStatus+"Male";
				System.out.println("MUSLIM CALLED - "+f);
			}
			else if (addStatus.toUpperCase().contains("REPE")) {
				addStatus = "Repeat";
			}
			else if (addStatus.toUpperCase().contains("INDI")) {
				addStatus = "Indigenous";
			}
			else {
				continue;
			}
			int count = DataUtil.getIntValue(l.get(3).toString());
			if ("|K1|N1|P1|K2|N2|P2|".contains(grade)) {
				continue;
			}
			if ("FEMALE".equals(gender)) {
				String f = grade.toLowerCase()+addStatus+"Male";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
			else {
				String f = grade.toLowerCase()+addStatus+"Female";
				sum.changeValue(f, count);
				sum.changeValue(f, count);
			}
		}
	}
}
