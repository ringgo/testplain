package org.daos;

import java.util.List;

import org.beans.User;
import org.ext.dbutil.QueryHelper;

public class Dao {

	public static List<User> getUserList() {
		return QueryHelper.query_slice("mysql5", User.class,
				"SELECT * FROM t_user WHERE 1=?",1,20, "1");
	}
}
