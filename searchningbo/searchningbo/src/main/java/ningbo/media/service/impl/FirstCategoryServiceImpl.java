package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.FirstCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FirstCategoryDao;
import ningbo.media.service.FirstCategoryService;

@Service("firstCategoryService")
public class FirstCategoryServiceImpl extends BaseServiceImpl<FirstCategory, Integer> implements FirstCategoryService {

	@Autowired
	public FirstCategoryServiceImpl(@Qualifier("firstCategoryDao")FirstCategoryDao firstCategoryDao) {
		super(firstCategoryDao);
	}

}
