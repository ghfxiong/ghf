package com.iamghf.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ghf.utils.common.ComConst;

public class TableSql {
	private static final transient Logger log = Logger.getLogger(TableSql.class);
	private List<String> cols;
	private String tableName;
	private Map<String,String[]> exp = new HashMap<String,String[]>();
	private Map<String,String> condition = new HashMap<String,String>();
	private static String DEFAULT_COLS = "*";
	
	public List<String> getCols() {
		return cols;
	}

	public void setCols(List<String> cols) {
		this.cols = cols;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String[]> getExp() {
		return exp;
	}

	public void setExp(Map<String, String[]> exp) {
		this.exp = exp;
	}

	public Map<String, String> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, String> condition) {
		this.condition = condition;
	}

	public String buildSql(){
		StringBuilder sb = new StringBuilder(1024);
		sb.append("SELECT ").append(list2String(cols))
			.append(" FROM ").append(tableName)
			.append(" WHERE 1=1 ");
		if(!exp.isEmpty()){
			for(Map.Entry<String,String[]> entry : exp.entrySet()){
				String[] exps = entry.getValue();
				String key = entry.getKey();
				for(String ex :exps){
					sb.append(" AND ").append(key).append(" ").append(ex);
				}
			}
		}
		String sql = sb.toString();
		if(!condition.isEmpty()){
			for(Map.Entry<String,String> entry : condition.entrySet()){
				sql = StringUtils.replace(sql, "{"+entry.getKey()+"}", entry.getValue());
			}
		}
		return sql;
	}
	
	private String list2String(List<String> cols){
		if(null == cols || cols.isEmpty()){
			return DEFAULT_COLS;
		}
		StringBuilder sb = new StringBuilder(1024);
		for(int index=0,len=cols.size();index<len;index++){
			if(index<len-1){
				//sb.append(" '").append(cols.get(index)).append("',");
				sb.append(" ").append(cols.get(index)).append(",");
			}else{
				sb.append(" ").append(cols.get(index)).append("");
			}
		}
		return sb.toString();
	}
	
	public static String buildPageSql(String tableName,String pkId, String condtion, Map param){
		StringBuilder sb = new StringBuilder(1024);
		int curPage = param.get(ComConst.DB_CONST.CURRENT_PAGE)==null || "".equals(param.get(ComConst.DB_CONST.CURRENT_PAGE))?1:Integer.parseInt(String.valueOf(param.get(ComConst.DB_CONST.CURRENT_PAGE)));
		if(curPage<1000){
			/*sb.append("SELECT * FROM ").append(tableName).append(" TT ");
			sb.append(" WHERE TT.ID >= (")
				.append(condtion).append(" LIMIT ").append(param.get(ComConst.DB_CONST.BEGIN_INDEX)).append(",1 )");
			sb.append(" ORDER BY TT.ID LIMIT ").append(param.get(ComConst.DB_CONST.PAGE_SIZE));*/
			sb.append("SELECT * FROM ").append(tableName);
			sb.append(condtion.toString()).append(" LIMIT ")
				.append(param.get(ComConst.DB_CONST.BEGIN_INDEX))
				.append(",")
				.append(param.get(ComConst.DB_CONST.PAGE_SIZE));
			
		}else{
			//TODO 待优化
			sb.append("SELECT * FROM ").append(tableName).append(" TT1 ")
				.append(" JOIN ")
				.append(" ( SELECT ").append(pkId).append(" FROM ").append(tableName).append(" SS ")
				.append(condtion)
				.append(" LIMIT ").append(param.get(ComConst.DB_CONST.BEGIN_INDEX)).append(",")
				.append(param.get(ComConst.DB_CONST.PAGE_SIZE))
				.append(" ) TT2")
			.append(" WHERE TT1.").append(pkId).append(" = TT2.").append(pkId).append(" ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		TableSql tSql = new TableSql();
		tSql.setTableName("intercfg");
		List<String> cols = new ArrayList<String>();
		cols.add("interfaceid");
		cols.add("intercode");
		//tSql.setCols(cols);
		Map<String,String[]> exp = new HashMap<String,String[]>();
		exp.put("interfaceid", new String[]{"= {id} "});
		tSql.setExp(exp);
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("id", "3");
		tSql.setCondition(condition);
		
		log.info(tSql.buildSql());
	}
}
