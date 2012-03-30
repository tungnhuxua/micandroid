package coffee.sqlite;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import coffee.sqlite.annotation.Column;
import coffee.sqlite.annotation.Transient;
import coffee.sqlite.base.TDbUtils;
import coffee.util.common.DateUtils;


/**
 * 数据库的通用工具类
 * 
 * @author coffee
 */
public class TSqliteUtils extends TDbUtils{

	/**
	 * 返回更新实体的命令语句
	 * @param <T>
	 * @param t
	 * @param token
	 */
	public static <T> String getUpdateSql(T t) throws Exception{
		StringBuffer sql = new StringBuffer("update ").append(TSqliteUtils.getTableName(t.getClass())).append(" set ");
		long id = 0;
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Transient nullMap = fields[i].getAnnotation(Transient.class);
			if(nullMap != null){
				continue;
			}
			String columnName = getColumnName(clazz, fields[i].getName());
			Object value = getValue(t, fields[i].getName());
			if(TSqliteUtils.isPrimaryKey(t.getClass(), fields[i].getName())){
				id = Long.valueOf(value.toString());
				continue;
			}else{
				if (value == null) {
					continue;// 忽略空值，如果想赋null值， 字符串写成 fieldName=""; 数值型 fieldName=0
				}
				switch(TypeUtils.getMappedType( fields[i].getType())){
					case Integer :
					case Long : 
					case Float:
					case Double:
						sql.append(columnName).append("=").append(value);
						break;
					case Date :
						value = DateUtils.format(value.toString());
						sql.append(columnName).append("='").append(value).append("'");
						break;
					case String :
						sql.append(columnName).append("='").append(value).append("'");
						break;
					default:
						continue;
				}
				if (value != null && fields.length > i) {
					sql.append(",");
				}
			}
		}
		if (sql.toString().endsWith(",")) {// 除去末尾的 ,
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(" where id = ").append(id);
		return sql.toString();
	}
	
	// 获取插入记录的sql语句
	public static <T> String getInsertSql(T t) throws Exception {
		StringBuffer sql = new StringBuffer("insert into ").append(
				TSqliteUtils.getTableName(t.getClass())).append(" ");
		
		Field[] fields = t.getClass().getDeclaredFields();
		//k-v 映射的column名字 : 属性  LinkedHashMap 按照插入的顺序排序
		Map<String,Field> propMap = new LinkedHashMap<String, Field>();
		sql.append("(");
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(isSupportType(field) == false){
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = field.getName();
			if (column != null && !"".equals(column.name())) {
				columnName = column.name();
			}
			sql.append(columnName);
			propMap.put(columnName, fields[i]);
			if (i + 1 < fields.length) {
				sql.append(",");
			}
		}
		sql.append(")values(");
		for (String column : propMap.keySet()) {
			Field field = propMap.get(column);
			if(isIdAuto(t.getClass(), field.getName())){
				sql.append("null");	
			}else{
				Object fieldValue = getValue(t, field.getName());
				switch(TypeUtils.getMappedType(field.getType())){
					case Integer :
					case Long :
					case Float:
					case Double:
						sql.append(fieldValue);
						break;
					case Date :
						fieldValue = DateUtils.format(fieldValue);
						sql.append(null == fieldValue ? "null" : "'" + fieldValue + "'");
						break;
					case String :
						sql.append(null == fieldValue ? "null" : "'" + fieldValue + "'");
						break;
					default :
						continue;
				}
			}
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);// 除去sql语句后面最后一个 逗号
		sql.append(")");
		return sql.toString();
	}
	
}
