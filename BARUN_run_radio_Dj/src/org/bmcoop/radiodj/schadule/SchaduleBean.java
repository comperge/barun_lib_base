package org.bmcoop.radiodj.schadule;

public class SchaduleBean {

	public static int PROGRAM_EMPTY = 0;
	public static int PROGRAM_PROCESSING = 1;
	public static int PROGRAM_OCCUPIED = 2;
	public static int PROGRAM_SUBMIT = 3;

	public String date;
	public int state;
	public String modifyDate;

	public SchaduleBean(String date, String modifyDate, int state) {
		this.date = date;
		this.state = state;
		this.modifyDate = modifyDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

}
