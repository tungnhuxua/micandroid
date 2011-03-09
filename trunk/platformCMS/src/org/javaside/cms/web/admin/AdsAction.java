package org.javaside.cms.web.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Ads;
import org.javaside.cms.entity.AdsLocation;
import org.javaside.cms.entity.AdsType;
import org.javaside.cms.service.AdsLocationManager;
import org.javaside.cms.service.AdsManager;
import org.javaside.cms.service.AdsTypeManager;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "ads.action?page.pageRequest=${page.pageRequest}&location.id=${location.id}", type = "redirect") })
public class AdsAction extends CRUDActionSupport<Ads> {

	@Autowired
	private AdsManager adsManager;
	@Autowired
	private AdsTypeManager typeManager;
	@Autowired
	private AdsLocationManager locationManager;

	private Long id;
	private Ads entity;
	private Page<Ads> page = new Page<Ads>(10);
	private AdsLocation location; //广告位
	private List<AdsType> adsTypes;
	private Long[] ids; //广告ID集合

	@Override
	public String input() throws Exception {
		if (location != null && location.getId() != null) {
			location = locationManager.get(location.getId());
		}
		adsTypes = typeManager.getAll();
		//新增广告时设置广告类型的默认值
		if (entity.getType() == null && adsTypes.size() > 0) {
			AdsType tmp = new AdsType();
			tmp.setId(adsTypes.get(0).getId());
			entity.setType(tmp);
		}
		return INPUT;
	}

	@Override
	public String delete() throws Exception {
		adsManager.delete(id);
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		adsManager.deleteBatch(ids);
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		if (location != null && location.getId() != null) {
			location = locationManager.get(location.getId());
		} else {
			location = null;
		}
		adsManager.getAdsByLocation(location, page);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = adsManager.get(id);
		} else {
			entity = new Ads();
		}
	}

	/**
	 * 保存方法的预装载
	 */
	@Override
	public void prepareSave() throws Exception {
		if (id != null) {
			entity = adsManager.get(id);
			entity.setType(null); //设置为NULL防止与页面的TYPE产生冲突
		} else {
			entity = new Ads();
		}
	}

	@Override
	public String save() throws Exception {
		if (location != null && location.getId() != null) {
			location = locationManager.get(location.getId());
			entity.getLocations().add(location);
		}
		//		if(entity!=null&&entity.getType()!=null&&entity.getType().getId()!=null)
		//			entity.setType(typeManager.get(entity.getType().getId()));
		adsManager.save(entity);
		return RELOAD;
	}

	public Ads getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ads getEntity() {
		return entity;
	}

	public void setEntity(Ads entity) {
		this.entity = entity;
	}

	public Page<Ads> getPage() {
		return page;
	}

	public void setPage(Page<Ads> page) {
		this.page = page;
	}

	public AdsLocation getLocation() {
		return location;
	}

	public void setLocation(AdsLocation location) {
		this.location = location;
	}

	public List<AdsType> getAdsTypes() {
		return adsTypes;
	}

	public void setAdsTypes(List<AdsType> adsTypes) {
		this.adsTypes = adsTypes;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
}
