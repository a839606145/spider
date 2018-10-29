package com.combanc.JdbcUtil;

import java.sql.ResultSet;

public interface QueryCallBack {
	void process(ResultSet rs) throws Exception;
}
