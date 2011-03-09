package cn.mmbook.freemark.generator;

import java.util.List;
import java.util.Map;

public interface Generator {

	public void generate(String templateFileName, Map data,String fileName) ;

}
