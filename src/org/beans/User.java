package org.beans;

import org.msf.db.annotation.Column;
import org.msf.db.annotation.Table;

@Table(dataSource = "mysql5", name = "act_id_user")
public class User {
	@Column(name = "ID_")
	private String uname;

	@Column(name = "PWD_")
	private String upwd;

	private String[] sex;

	private String f1;
	private String f2;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String[] getSex() {
		return sex;
	}

	public void setSex(String[] sex) {
		this.sex = sex;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	@Override
	public String toString() {
		return "User [uname=" + uname + ", upwd=" + upwd + ", sex=[" + sex[0]
				+ "," + sex[1] + "], f1=" + f1 + ", f2=" + f2 + "]";
	}

}
