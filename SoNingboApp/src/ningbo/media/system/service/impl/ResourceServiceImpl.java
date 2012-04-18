package ningbo.media.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.entity.Resource;
import ningbo.media.system.dao.ResourceDao;
import ningbo.media.system.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService{

	@Autowired
	public ResourceServiceImpl(@Qualifier("resourceDao")ResourceDao resourceDao){
		super(resourceDao) ;
	}
}
