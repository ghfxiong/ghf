package com.ghf.core.dao.connections;

import java.sql.Connection;
import java.sql.SQLException;

import com.ghf.core.dao.interfaces.DbDao;
import com.ghf.utils.common.ServiceFactory;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*// �������ӳض���
		JdbcPoolProxy jdbcPoolProxy = new JdbcPoolProxy();

		// �����ӳ���ȡ������
		jdbcPoolProxy.getConnection();
		jdbcPoolProxy.getConnection();
		jdbcPoolProxy.getConnection();
		jdbcPoolProxy.getConnection();

		Connection conn = jdbcPoolProxy.getConnection();
		conn.close();

		jdbcPoolProxy.getConnection();
		jdbcPoolProxy.getConnection();*/
		for(int i=5;i>0;i--){
			DbDao dao = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
			dao.getCount("SELECT COUNT(*) count from kfinfo t");
		}
		
	}

}
