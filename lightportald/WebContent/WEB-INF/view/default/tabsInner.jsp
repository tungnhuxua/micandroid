<%@ page import="java.util.*,org.light.portal.util.*,org.json.*" %>
<ul id="tabList">
	<%
		List<LabelBean> list = (List<LabelBean>)request.getAttribute(Constants._PORTAL_INIT_LIST);
		if(list != null){
			int maxTabs = 10;
			for(LabelBean bean : list){	
				if(bean.getId() == "portalJSON"){
					JSONObject json = JSONUtil.parse(bean.getDesc());
					maxTabs = json.getInt("maxShowTabs");
				}
				
				if(bean.getId() == "tabsJSON"){
					JSONArray jarray =  JSONUtil.parseArray(bean.getDesc());
					if(jarray != null){
						int len = (jarray.length() < maxTabs) ? jarray.length() : maxTabs;
						for(int i=0;i<len;i++){
							JSONObject json = jarray.getJSONObject(i);
	%>
							<li id='tab_<%= json.get("id") %>' label='<%= json.get("label") %>' <% if(i==0){ %>class="current"<%}%> >
								<span id='tabHeader_<%= json.get("id") %>' <% if(json.getBoolean("defaulted")){ %>class="current"<%}%>>
									<div id='tabTitle_<%= json.get("id") %>' class="portal-tab-handle">
										<%= json.get("label") %>
									</div>
								</span>
							</li>					
	<%
						}
					}
	
				}
			}
		}
	%>
</ul>