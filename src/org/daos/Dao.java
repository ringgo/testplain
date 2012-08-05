package org.daos;

import java.util.List;

import org.beans.User;
import org.ext.dbutil.QH;

public class Dao {
	public static List<User> getUserList() throws Exception {
		return QH.queryList_slice_sqlkey(User.class,
				"sqlscript_mysql5_sqls0.selectAllUser", 1, 20, "1");
	}
}
