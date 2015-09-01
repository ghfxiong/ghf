package com.ghf.core.dao.connections;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.dao.DbBean;
import com.ghf.core.dao.utils.ReflectHelper;

public class SpringJdbcUtil<T> implements DbConnUtil<T>{
	private static final Logger log = Logger.getLogger(SpringJdbcUtil.class);
	
	private static DataSource dataSource;
	
	private static JdbcTemplate jdbcTemplate;
	// �������Դ
	public void setDataSource(DataSource datasource) {
		//BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		//DataSource dataSource = beanFactory.getBean("dataSource", DataSource.class);
		dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate getJdbc(){
		if(null == jdbcTemplate){
			if(null == dataSource){
				BeanFactory factory = new ClassPathXmlApplicationContext("jdbc-context.xml"); 
				dataSource = factory.getBean("dataSource",DataSource.class);
			}
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
	
	@Override
	public void executeSql(String sql) {
		getJdbc().execute(sql);
	}

	@Override
	public void executeSql(String[] sqls) {
		long start = System.currentTimeMillis();
		log.debug(" lev1 start time :"+start+"   total sqls :"+sqls.length);
		if(sqls!=null&&sqls.length>0){
			for(String sql:sqls){
				getJdbc().execute(sql);
			}
		}
		long end = System.currentTimeMillis();
		log.debug("total time :" + (end-start));
	}

	@Override
	public void executeSql(String[] sqls, boolean flag) {
		long start = System.currentTimeMillis();
		log.debug(" lev1 start time :"+start+"   total sqls :"+sqls.length);
		if(sqls!=null&&sqls.length>0){
			getJdbc().batchUpdate(sqls);
		}
		long end = System.currentTimeMillis();
		log.debug("total time :" + (end-start));
	}

	@Override
	public String[] getValues(String sql) {
		String strs = sql.split("values")[0];
		String[] tmp = strs.split(",");
		String value = sql.split("values")[1];
		value = value.replace("(", "");
		value = value.replace(")", "");
		value = value.replace(";", "");
		value = value.replace("'", "");
		value = value.replace("'", "");
		return value.split(",");  //ע��ĩβΪ�����
	}

	@Override
	public String createPreSql(String[] sqls) {
		String strs = sqls[0].split("values")[0];
		String[] tmp = strs.split(",");
		String pre_sql = strs+" values (";
		for(int i=0;i<tmp.length;i++){
			pre_sql +="?,";
		}
		pre_sql = pre_sql.substring(0,pre_sql.length()-1);
		pre_sql +=")";
		return pre_sql;
	}

	public List<T> query(Class<T> clazz, String sql) {
		//List list = getJdbc().queryForList(sql, clazz);
		//return getJdbc().queryForList(sql, clazz);
		final List<T> list = new ArrayList<T>();
		final Class<T> cls = clazz;
		if(log.isDebugEnabled()){
			log.debug(clazz.getName()+"======="+sql);
		}
		getJdbc().query(sql, new ResultSetExtractor(){
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				ReflectHelper<T> helper = new ReflectHelper<T>();
				list.addAll(helper.getList(cls, rs));
				return null;
			}			
		});
		return list;
	}
	
	public List<T> getBeans(String sql, Class<T> clazz){
		List<T> list = getJdbc().queryForList(sql, clazz);
		return list;
	}
	
	public Map getBeanMap(String sql, Class clazz){
		Map map = getJdbc().queryForMap(sql);
		return map;
	}

	@Override
	public long getCount(String sql) {
		return getJdbc().queryForLong(sql);
	}
	
	public void batchInsert(final List<T> list,int flag)throws Exception{
		if(list.size()<1){
			return;
		}
		Class<? extends Object> clazz = list.get(0).getClass();
		Field f1= clazz.getDeclaredField("tableName");
		f1.setAccessible(true);
		StringBuilder psql = new StringBuilder("insert into ")
			.append(f1.get(clazz))
			.append(" (");
		StringBuilder sf = new StringBuilder();
		StringBuilder values = new StringBuilder();
		Field[] fields = clazz.getFields();//获得属性
		final DbBean bean = new DbBean();
		List<Map<String,Object>> plists = new ArrayList<Map<String,Object>>();
		List<String> clists = new ArrayList<String>();
		//String[] cols = new String[fields.length];
		for(int i=0,len=fields.length;i<len;i++){
			String col = fields[i].getName();
			PropertyDescriptor pd = new PropertyDescriptor(col,clazz);
			Method getMethod = pd.getReadMethod();//获得get方法
			Object o = getMethod.invoke(list.get(0));//执行get方法返回一个Object
			if(o!=null){
				sf.append(col).append(",");
				values.append("?,");
				//cols[i]=col;
				clists.add(col);
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(col, o);
				plists.add(map);
			}
		}
		bean.setCols(clists.toArray(new String[]{}));
		bean.setParams(plists);
		psql.append(sf.toString().substring(0, sf.toString().length()-1))
		   .append(") values (")
		   .append(values.toString().substring(0, values.toString().length()-1))
		   .append(")");
		
		if(log.isDebugEnabled()){
			log.debug(psql);
		}
		getJdbc().batchUpdate(psql.toString(), new BatchPreparedStatementSetter(){
			public int getBatchSize() {
				return list.size();
			}
			public void setValues(PreparedStatement paramPreparedStatement,
					int paramInt) throws SQLException {
				String[] cols = bean.getCols();
				List<Map<String,Object>> list = bean.getParams();
				for(int i=0,len=cols.length;i<len;i++){
					if(cols[i]!=null){
						paramPreparedStatement.setObject(i+1, list.get(i).get(cols[i]));
						if(log.isDebugEnabled()){
							log.debug(cols[i]+"---------"+list.get(i).get(cols[i]));
						}
					}
				}
			}
		});
	}
	public void batchInsert(final List<T> list)throws Exception{
		if(list.size()>0){
			String psql = this.buildPsql(list.get(0));
			final DbBean bean = this.setCols(psql);
			if(log.isDebugEnabled()){
				log.debug(psql);
			}
			
			final Map<String,Method> methods = this.getMethod(list.get(0));
			//List<Map<String,Object>> plists = new ArrayList<Map<String,Object>>();
			
			getJdbc().batchUpdate(psql.toString(), new BatchPreparedStatementSetter(){
				public int getBatchSize() {
					return list.size();
				}
				public void setValues(PreparedStatement paramPreparedStatement,
						int paramInt) throws SQLException {
					String[] cols = bean.getCols();
					for(int i=0,len=cols.length;i<len;i++){
						if(cols[i]!=null){
							Method getMethod = methods.get(cols[i]);
							Object val = null;
							try {
								val = getMethod.invoke(list.get(paramInt));
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(log.isDebugEnabled()){
								//log.debug(i+" num --------");
								//log.debug(cols[i]+"---------"+val);
							}
							paramPreparedStatement.setObject(i+1, val );
							
						}
					}
				}
			});
		}
	}
	
	private DbBean setCols(String psql){
		DbBean bean = new DbBean();
		String addCols = psql.substring(psql.indexOf("(")+1, psql.indexOf(")"));
		String[] sCols = addCols.split(",");
		String[] cols = new String[sCols.length];
		for(int i=0;i< sCols.length;i++){
			cols[i] = sCols[i].trim();
		}
		bean.setCols(cols);
		return bean;
	}
	
	private Map<String,Method> getMethod(Object obj)throws Exception{
		List<Map<String,Method>> list = new ArrayList<Map<String,Method>>();
		Class<? extends Object> clazz = obj.getClass();
		Field[] fields = clazz.getFields();//获得属性
		Map<String,Method> map = new HashMap<String,Method>();
		for(int i=0,len=fields.length;i<len;i++){
			String col = fields[i].getName();
			PropertyDescriptor pd = new PropertyDescriptor(col,clazz);
			Method getMethod = pd.getReadMethod();//获得get方法
			//Object o = getMethod.invoke(obj);//执行get方法返回一个Object
			map.put(col, getMethod);
			//list.add(map);
		}
		return map;
	}
	
	private String buildPsql(Object obj)throws Exception{
		Class<? extends Object> clazz = obj.getClass();
		Field f1= clazz.getDeclaredField("tableName");
		f1.setAccessible(true);
		StringBuilder psql = new StringBuilder("insert into ")
			.append(f1.get(clazz))
			.append(" (");
		StringBuilder sf = new StringBuilder();
		StringBuilder values = new StringBuilder();
		Field[] fields = clazz.getFields();//获得属性
		for(int i=0,len=fields.length;i<len;i++){
			String col = fields[i].getName();
			//PropertyDescriptor pd = new PropertyDescriptor(col,clazz);
			//Method getMethod = pd.getReadMethod();//获得get方法
			//Object o = getMethod.invoke(obj);//执行get方法返回一个Object
			//if(o!=null){
				sf.append(col).append(",");
				values.append("?,");
			//}
		}
		psql.append(sf.toString().substring(0, sf.toString().length()-1))
		   .append(") values (")
		   .append(values.toString().substring(0, values.toString().length()-1))
		   .append(")");
		
		if(log.isDebugEnabled()){
			//log.debug(psql);
		}
		return psql.toString();
	}
	
	public void insert(Object obj)throws Exception{
		Class<? extends Object> clazz = obj.getClass();
		Field f1= clazz.getDeclaredField("tableName");
		f1.setAccessible(true);
		StringBuilder psql = new StringBuilder("insert into ")
			.append(f1.get(clazz))
			.append(" (");
		StringBuilder sf = new StringBuilder();
		StringBuilder values = new StringBuilder();
		Field[] fields = clazz.getFields();//获得属性
		final DbBean bean = new DbBean();
		List<Map<String,Object>> plists = new ArrayList<Map<String,Object>>();
		List<String> clists = new ArrayList<String>();
		//String[] cols = new String[fields.length];
		for(int i=0,len=fields.length;i<len;i++){
			String col = fields[i].getName();
			PropertyDescriptor pd = new PropertyDescriptor(col,clazz);
			Method getMethod = pd.getReadMethod();//获得get方法
			Object o = getMethod.invoke(obj);//执行get方法返回一个Object
			if(o!=null){
				sf.append(col).append(",");
				values.append("?,");
				//cols[i]=col;
				clists.add(col);
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(col, o);
				plists.add(map);
			}
		}
		bean.setCols(clists.toArray(new String[]{}));
		bean.setParams(plists);
		psql.append(sf.toString().substring(0, sf.toString().length()-1))
		   .append(") values (")
		   .append(values.toString().substring(0, values.toString().length()-1))
		   .append(")");
		
		if(log.isDebugEnabled()){
			log.debug(psql);
		}
		getJdbc().batchUpdate(psql.toString(), new BatchPreparedStatementSetter(){
			public int getBatchSize() {
				return 1;
			}
			public void setValues(PreparedStatement paramPreparedStatement,
					int paramInt) throws SQLException {
				String[] cols = bean.getCols();
				List<Map<String,Object>> list = bean.getParams();
				for(int i=0,len=cols.length;i<len;i++){
					if(cols[i]!=null){
						paramPreparedStatement.setObject(i+1, list.get(i).get(cols[i]));
						if(log.isDebugEnabled()){
							log.debug(cols[i]+"---------"+list.get(i).get(cols[i]));
						}
					}
				}
			}
		});
	}
	
	
	public static void main(String[] args)throws Exception{
		/*SpringJdbcUtil<InterCfgBean> util = new SpringJdbcUtil<InterCfgBean>();
		List<InterCfgBean> list = util.getBeans("select intercode FROM intercfg",InterCfgBean.class);
		log.debug(list.size());
		//Map map = util.getBeanMap("select * FROM intercfg",InterCfgBean.class); 
*/	
		 String sql="INSERT INTO WEBSITE_MALL (NAME,HIGHT,MALL_INFO,OPRATION_BTIME ,OPRATION_ETIME,BELONG_TO,ADDRESS,VECTOR,FLOOR,FLOOR_IFNO,REMARK,UPDATE_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		 SpringJdbcUtil util = new SpringJdbcUtil();
		 DbBean bean = util.setCols(sql);
		 bean.getCols();
		 
	}
}
