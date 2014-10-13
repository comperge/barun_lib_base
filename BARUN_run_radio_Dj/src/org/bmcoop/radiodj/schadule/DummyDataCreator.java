package org.bmcoop.radiodj.schadule;

import java.util.ArrayList;

public class DummyDataCreator {

	public ArrayList<SchaduleBean> getData() {
		ArrayList<SchaduleBean> al = new ArrayList<SchaduleBean>();
		al.add(new SchaduleBean("2014-10-01", "2014-09-11", 3));
		al.add(new SchaduleBean("2014-10-07", "", 0));
		al.add(new SchaduleBean("2014-10-14", "2014-09-11", 1));
		al.add(new SchaduleBean("2014-10-21", "", 0));
		al.add(new SchaduleBean("2014-10-28", "2014-09-11", 2));
		al.add(new SchaduleBean("2014-11-03", "", 0));
		al.add(new SchaduleBean("2014-11-10", "2014-09-11", 2));
		al.add(new SchaduleBean("2014-11-17", "", 0));
		al.add(new SchaduleBean("2014-11-24", "2014-09-11", 1));
		al.add(new SchaduleBean("2014-12-01", "", 0));
		al.add(new SchaduleBean("2014-12-07", "", 0));
		al.add(new SchaduleBean("2014-12-14", "", 0));
		return al;
	}
}
