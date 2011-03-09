package javacommon.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author badqiu
 */
@Transactional
public abstract class BaseManager <E,PK extends Serializable>{
	
	protected Log log = LogFactory.getLog(getClass());

	protected abstract EntityDao getEntityDao();

	@Transactional(readOnly=true)
	public E getById(PK id) {
		return (E)getEntityDao().getById(id);
	}
	
	@Transactional(readOnly=true)
	public List<E> findAll() {
		return getEntityDao().findAll();
		
	}
	
	public void saveOrUpdate(E entity) {
		getEntityDao().saveOrUpdate(entity);
	}
	
	public void save(E entity) {
		getEntityDao().save(entity);
		
	}
	
	public void removeById(PK id) {
		getEntityDao().deleteById(id);
	}
	
	public void update(E entity) {
	
		getEntityDao().update(entity);
	}
	
	@Transactional(readOnly=true)
	public boolean isUnique(E entity, String uniquePropertyNames) {
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
	/**
	 * 查询表最新的ID值
	 */
	@Transactional(readOnly = true)
	public String getMaxId(String sql_statement) {
		Date date = new java.util.Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyMMdd");
		String dateStr = sd.format(date);
	    java.lang.String maxId = getEntityDao().getMaxId(sql_statement); 
	    if(javacommon.util.StringTool.isNull(maxId)==false){
	    	maxId ="101";
	    }else{
	    	if(!maxId.startsWith(dateStr)){
	    	  if(maxId.length()>3){
	    		maxId= maxId.substring(maxId.length()-3,maxId.length());
	    		maxId=  dateStr + maxId;
	    	  }else{
	    		  maxId= dateStr + maxId;
	    	  }
	    	}else{
	    		if(maxId.length()>3){
		    		maxId= maxId.substring(maxId.length()-3,maxId.length());
		    		maxId=  dateStr + maxId;
		    	  }else{
		    		  maxId= dateStr + maxId;
		    	  }
	    	}
	    }
		 
		int temp_id = Integer.parseInt(maxId);
		temp_id = temp_id+1;
		return String.valueOf(temp_id);
	}
}
