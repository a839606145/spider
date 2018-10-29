package com.combanc.JdbcUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.combanc.util.ConfigManager;

public class JDBCUtil {

	static{
		try {
			Driver.class.forName(ConfigManager.getProperty(JdbcConstant.DRIVER_NAME));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//因为单例 只有一个线程执行
	private JDBCUtil(){
		int size=ConfigManager.getPropertyInteger(JdbcConstant.JDBC_POOL_SIZE);
		if(size>0){
			String url = null;
            String user = null;
            String password = null;

            url = ConfigManager.getProperty(JdbcConstant.JDBC_URL);
            user = ConfigManager.getProperty(JdbcConstant.JDBC_USER);
            password = ConfigManager.getProperty(JdbcConstant.JDBC_PASSWORD);
			for(int i=0;i<size;i++){
				try{
					Connection conn=DriverManager.getConnection(url, user, password);
					dataSource.push(conn);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public synchronized Connection getConnection(){
		while(dataSource.size()==0){
			try{
				Thread.sleep(10);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataSource.poll();
	}
	
	//双捡锁  单例模式
	private static JDBCUtil instance=null;
	
	private LinkedList<Connection> dataSource=new LinkedList<Connection>();;
	
	public static JDBCUtil getInstance(){
		if(instance==null){
			synchronized(JDBCUtil.class){
				if(instance==null){
					instance=new JDBCUtil();
				}
			}
		}
		return instance;
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
			if(conn!=null){
				dataSource.push(conn);
			}
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
	            while(rs!=null && rs.next()){
	            	System.out.println(rs.getString(1));
	            }
	            if(callback!=null){
	            	callback.process(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (conn != null) {
	                dataSource.push(conn);
	            }
	        }
	}
	
	 public int[] executeBatch(String sql, List<Object[]> paramsList) {
	        int[] rtn = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = getConnection();

	            // 第一步：使用Connection对象，取消自动提交
	            conn.setAutoCommit(false);

	            pstmt = conn.prepareStatement(sql);

	            // 第二步：使用PreparedStatement.addBatch()方法加入批量的SQL参数
	            if (paramsList != null && paramsList.size() > 0) {
	                for (Object[] params : paramsList) {
	                    for (int i = 0; i < params.length; i++) {
	                        pstmt.setObject(i + 1, params[i]);
	                    }
	                    pstmt.addBatch();
	                }
	            }

	            // 第三步：使用PreparedStatement.executeBatch()方法，执行批量的SQL语句
	            rtn = pstmt.executeBatch();

	            // 最后一步：使用Connection对象，提交批量的SQL语句
	            conn.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (conn != null) {
	                dataSource.push(conn);
	            }
	        }

	        return rtn;
	    }
	 
	 public static void main(String[] args) {
		 final String sql="select * from user";
		for(int i=0;i<20;i++){
			new Thread(new Runnable(){
				public void run() {
					JDBCUtil.getInstance().executeQuery(sql, null, null);
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
