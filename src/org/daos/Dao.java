package org.daos;

import java.util.List;

import org.beans.User;
import org.ext.dbutil.QueryHelper;

public class Dao {
	enum SQL {
		sqlscript_mysql5_sqls0
	}

	public static List<User> getUserList() throws Exception {
		return QueryHelper.queryList_slice_sqlkey(User.class,
				SQL.sqlscript_mysql5_sqls0 + ".selectAllUser", 1, 20, "1");
	}
}
