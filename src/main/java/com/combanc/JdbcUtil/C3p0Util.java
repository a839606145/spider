package com.combanc.JdbcUtil;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.combanc.util.ConfigManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Util {
	
	private static C3p0Util instance=null;
	private ComboPooledDataSource dataSource;
	private C3p0Util(){
		try {
			dataSource=new ComboPooledDataSource();
			dataSource.setDriverClass(ConfigManager.getProperty(JdbcConstant.DRIVER_NAME));
			dataSource.setJdbcUrl(ConfigManager.getProperty(JdbcConstant.JDBC_URL));
			dataSource.setUser(ConfigManager.getProperty(JdbcConstant.JDBC_USER));
			dataSource.setPassword(ConfigManager.getProperty(JdbcConstant.JDBC_PASSWORD));
			dataSource.setMaxPoolSize(ConfigManager.getPropertyInteger(JdbcConstant.JDBC_POOL_SIZE));
			dataSource.setInitialPoolSize(3);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}
	
	public static C3p0Util getInstance(){
		if(instance==null){
			synchronized(C3p0Util.class){
				if(instance==null){
					instance=new C3p0Util();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void releaseConnection(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//执行操作
		public int executeUpdate(String sql, Object[] params){
			int rtn=0;
			Connection conn=null;
			PreparedStatement pstmt=null;
			try{
				conn=getConnection();
				conn.setAutoCommit(false);
				pstmt=conn.prepareStatement(sql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						pstmt.setObject(i+1, params[i]);
					}
				}
				rtn=pstmt.executeUpdate();
				conn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				releaseConnection(conn);
			}
			return rtn;
		}
		
		public void executeQuery(String sql,Object[] params,QueryCallBack callback){
			 Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;

		        try {
		            conn = getConnection();
		            pstmt = conn.prepareStatement(sql);

		            if (params != null && params.length > 0) {
		                for (int i = 0; i < params.length; i++) {
		                    pstmt.setObject(i + 1, params[i]);
		                }
		            }

		            rs = pstmt.executeQuery();
		            if(callback!=null){
		            	callback.process(rs);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		        	if(rs!=null){
		        		try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        	if(pstmt!=null){
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		        	releaseConnection(conn);
		        }
		}
	
		 public static void main(String[] args) {
			 final String sql="select * from user";
			for(int i=0;i<20;i++){
				new Thread(new Runnable(){
					public void run() {
						C3p0Util.getInstance().executeQuery(sql, null, null);
						try {
							TimeUnit.SECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					
				}).start();
			}
			
		}
}
