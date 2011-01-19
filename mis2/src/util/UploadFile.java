package util;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import bean.Branch;
import bean.Center;
import bean.Member;
import bean.MemberDividends;
import bean.MemberLoan;
import bean.MemberPassbook;
import bean.MemberRegister;

public class UploadFile {
	private static int center;
	private static String branch;

	public static void main(String[] args) {
		if (args == null || args.length==0) {
			springbean.AAAConfig.getServerInstance().setupDB(new JLabel());
		}
		else {
			springbean.AAAConfig.getInstance(args).setupDB(new JLabel());
		}
		List<List> lst = parseRecords("C:/filename.xls", true);
		for (List l : lst) {
			log(l);
			if (isLineAMember(l)) {
				int memberId = addMember(l);
				addLoan(l, memberId);
				addRegister(l, memberId);
				addPasbook(l, memberId);
				addDividends(l, memberId);
			} else {
				checkCenterOrBranch(l);
			}
		}
	}

	private static void log(List l) {
		if (l != null && !l.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < l.size(); i++) {
				sb.append(l.get(i)).append("\t");
			}
			Log.info(sb.toString());
		}
	}

	private static void checkCenterOrBranch(List l) {
		String s = (String) l.get(0);
		s = s.trim().toUpperCase();
		if (s.contains("CENTER")) {
			s = s.replace("CENTER", "").trim();
			center = Integer.parseInt(s);
		} else if (s.contains("BRANCH")) {
			s = s.replace("BRANCH", "");
			s = s.replace(":", "");
			branch = s.trim();
			Branch b = new Branch();
			b.name = branch;
			b.address = branch;
			b.save();
//			create 100 center
			for (int i=1; i<=100; i++) {
				Center c = new Center();
				c.name = i+"";
				c.branchName = branch;
				c.save();
			}
		}
		Log.info("Saving Branch and Center");
	}

	private static void addDividends(List l, int memberId) {
		String div = (String) l.get(15);

		if (isNotNullAndEmpty(div)) {
			MemberDividends pass = new MemberDividends();
			pass.setMemberId(memberId);
			pass.setAmount(getDouble(div));
			pass.save();
		}
	}

	private static void addPasbook(List l, int memberId) {
		String sbu = (String) l.get(11);
		String loan = (String) l.get(12);
		String ps = (String) l.get(13);

		if (isNotNullAndEmpty(sbu) && isNotNullAndEmpty(loan)
				&& isNotNullAndEmpty(ps)) {
			MemberPassbook pass = new MemberPassbook();
			pass.setMemberId(memberId);
			pass.setSbu(sbu);
			pass.setPs(getDouble(ps));
			pass.setLoan(getDouble(loan));
			pass.save();
		}
	}

	private static void addRegister(List l, int memberId) {
		String sbu = (String) l.get(8);
		String loan = (String) l.get(9);
		String ps = (String) l.get(10);

		if (isNotNullAndEmpty(sbu) && isNotNullAndEmpty(loan)
				&& isNotNullAndEmpty(ps)) {
			MemberRegister reg = new MemberRegister();
			reg.setMemberId(memberId);
			reg.setSbu(sbu);
			reg.setPs(getDouble(ps));
			reg.setLoan(getDouble(loan));
			reg.save();
		}
	}

	private static void addLoan(List l, int memberId) {
		// there are 2 loan records here
		// 2008
		String releaseDates = (String) l.get(2);
		String interests = (String) l.get(3);
		String amounts = (String) l.get(4);
		String totalInterests = (String) l.get(5);
		String[] arr = releaseDates.split(";");
		if (isNotNullAndEmpty(releaseDates)) {
			for (int i = 0; i < arr.length; i++) {
				Date d = getDate(releaseDates, i);
				double amount = getDouble(amounts, i);
				if (d != null || amount != 0) {
					MemberLoan loan = new MemberLoan();
					loan.setReleaseDate(d);
					loan.setMemberId(memberId);
					loan.setAmount(amount);
					loan.setInterest(getDouble(interests, i));
					loan.setInterestOnLoansPaid(getDouble(totalInterests, i));
					loan.save();
				}
			}
		}

		// 2009
		releaseDates = (String) l.get(6);
		amounts = (String) l.get(7);

		if (isNotNullAndEmpty(releaseDates)) {
			arr = releaseDates.split(";");
			for (int i = 0; i < arr.length; i++) {
				Date d = getDate(releaseDates, i);
				double amount = getDouble(amounts, i);
				if (d != null || amount != 0) {
					MemberLoan loan = new MemberLoan();
					loan.setReleaseDate(d);
					loan.setMemberId(memberId);
					loan.setAmount(amount);
					loan.save();
				}
			}
		}
	}

	private static boolean isLineAMember(List l) {
		String line = (String) l.get(0);
		try {
			if (Double.parseDouble(line) > 0) {
				String name = (String) l.get(1);
				if (name != null && !name.isEmpty() && !name.contains("Member")
						&& name.contains(" ")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static int addMember(List l) {
		String name = (String) l.get(1);
		String[] arr = name.split(" ");
		String firstName = arr[0];
		String middleInitial = "";
		String lastName = arr[1];
		if (lastName!=null) {
			if (lastName.contains(".") && lastName.length()==2) {
				middleInitial = lastName.replace(".", "");
			}
			else if (lastName.length()==1) {
				middleInitial = lastName.replace(".", "");
			}
		}
		if (arr.length==3) {
			lastName = arr[2];
		}
		else if (arr.length==4) {
			lastName = arr[2]+" "+arr[3];
		}
//		check for existing member
		Member m = (Member) DBClient.getFirstRecord("SELECT a FROM Member a WHERE a.firstName='",firstName.toUpperCase(),"' AND a.lastName='",lastName.toUpperCase(),"'");
		if (m==null || m.personId==null || m.personId==0) {
			m = new Member();
			m.setFirstName(firstName.toUpperCase());
			m.setMiddleInitial(middleInitial);
			m.setLastName(lastName.toUpperCase());
			m.branch = branch.toUpperCase();
			m.center = center;
			m.save();
		}
		return m.personId;
	}

	private static double getDouble(String s, int offset) {
		String[] arr = s.split(";");
		if (arr.length > offset && isNotNullAndEmpty(arr[offset])) {
			return Double.parseDouble(arr[offset]);
		}
		return 0;
	}

	private static Date getDate(String s, int offset) {
		SimpleDateFormat sdfYear = new SimpleDateFormat("mm/dd/yy");
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat sdfCheckYear = new SimpleDateFormat("yyyy");
		String[] arr = s.split(";");
		if (arr.length > offset) {
			try {
				Date d = sdf.parse(arr[offset]);
				if (sdfCheckYear.format(d).startsWith("00")) {
					d = sdfYear.parse(arr[offset]);
				}
				return d;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static boolean isNotNullAndEmpty(String s) {
		return s != null && !s.isEmpty();
	}

	private static double getDouble(String s) {
		if (isNotNullAndEmpty(s)) {
			return Double.parseDouble(s);
		}
		return 0;
	}

	private static String pad(Object o, String pad, int count) {
		StringBuffer sb = new StringBuffer(o.toString());
		for (int i=sb.length(); i<count; i++) {
			sb.insert(0, pad);
		}
		return sb.toString();
	}

	private static List<List> parseRecords(String filename, boolean removeHeader) {
		List<List> lstRow = new ArrayList<List>();
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					filename));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator();
			if (removeHeader) {
				rows.next(); // remove the header
			}

			for (int i = 0; rows.hasNext(); i++) {
				List lst = new ArrayList();
				lstRow.add(lst);
				HSSFRow row = (HSSFRow) rows.next();
				Log.info("Row #", row.getRowNum());

				for (int j = 0; j < 30; j++) {
					HSSFCell cell = (HSSFCell) row.getCell((short) j);
					if (cell == null) {
						lst.add("");
					}
					try {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							String year = DateUtil.formatDate(
									cell.getDateCellValue(), "yyyy");
							if (Integer.parseInt(year) > 2000) {
								lst.add(DateUtil.formatDate(
										cell.getDateCellValue(), "MM/dd/yyyy"));
							} else {
								lst.add(cell.getNumericCellValue() + "");
							}
						} else {
							lst.add(cell.getStringCellValue());
						}
					} catch (Exception e) {
						lst.add("");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lstRow;
	}
}
