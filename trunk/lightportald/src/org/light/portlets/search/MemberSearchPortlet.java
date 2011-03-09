 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portlets.search;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.LinkedList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portal.search.model.SearchResultItem;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class MemberSearchPortlet extends LightGenericPortlet {
	 	     
	public void processAction (ActionRequest request, ActionResponse response) 
		throws PortletException, java.io.IOException 
	{		
		String action = request.getParameter("action");
		if("find".equals(action)){
		   String input = request.getParameter("input");
		   String keyword = request.getParameter("keyword");
		   if("0".equals(input)) keyword=null;
		   SearchCriteria criteria = new SearchCriteria(OrganizationThreadLocal.getOrganizationId());
		   criteria.setKeyword(keyword);
		   try{
			   SearchResult result = this.getUserService(request).searchUser(criteria);
			   request.getPortletSession().setAttribute("criteria",criteria,PortletSession.APPLICATION_SCOPE);	
			   request.getPortletSession().setAttribute("usResult",result,PortletSession.APPLICATION_SCOPE);	
		   }catch(Exception e){
			   
		   }
		}		
	  }
 
	protected void doView (RenderRequest request, RenderResponse response)
   		throws PortletException, java.io.IOException
	{	
		 String flowOff = request.getParameter("flowOff");
		 if(flowOff != null){
			 if("0".equals(flowOff))
				 request.getPortletSession().setAttribute("flowOff",true,PortletSession.APPLICATION_SCOPE);
			 else
				 request.getPortletSession().removeAttribute("flowOff",PortletSession.APPLICATION_SCOPE);
		 }
		 String viewType = request.getParameter("viewType");	
		 if(viewType != null && "2".equals(viewType))
			 doViewFlow(request,response);
		 else if(viewType != null && "1".equals(viewType))
			 doViewFlowPage(request,response);
		 else
			 doViewNormal(request,response);		
	}	
	private void doViewFlow (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
	{	
	 
	 SearchResult result = (SearchResult)request.getPortletSession().getAttribute("usResult",PortletSession.APPLICATION_SCOPE);		 
	 if(result != null){		 
		 int number = 0;
		 int total = result.getTotal();

		 if(request.getParameter("page") != null){
			 try{
				 number = Integer.parseInt(request.getParameter("page"));	
			 }catch(Exception e){}
		 }else if(request.getParameter("number") != null){
			 try{
				 number = Integer.parseInt(request.getParameter("number"));	
			 }catch(Exception e){}
		 }
		 
		 if(number >= total) number = total - 1;
		 if(number < 0) number = 0;
		 List<SearchResultItem> leftPage = new LinkedList<SearchResultItem>();
		 for(int i=number - 5;i<number;i++){
			 if(i < 0) continue;
			 if(i >= total) break;
			 leftPage.add(result.getItems().get(i));
		 }
		 List<SearchResultItem> rightPage = new LinkedList<SearchResultItem>();
		 for(int i=number + 1;i<=number + 5;i++){
			 if(i >= total) break;
			 rightPage.add(0,result.getItems().get(i));
		 }
		 request.setAttribute("leftPage", leftPage);
		 request.setAttribute("rightPage", rightPage);
		 request.setAttribute("pages", total);
		 if(number < total)
			 request.setAttribute("current", result.getItems().get(number));
		 
		 request.setAttribute("page", number);
	 }
		
	 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/directory/flowView.jsp").include(request,response);	 
	}
	private void doViewFlowPage (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
	{		 
	 SearchResult result = (SearchResult)request.getPortletSession().getAttribute("usResult",PortletSession.APPLICATION_SCOPE);		 
	 if(result != null){		 
		 int pageNumber = 5;
		 int pages = (result.getTotal() % pageNumber == 0) ? result.getTotal() / pageNumber : result.getTotal() / pageNumber + 1;
		 int page =0;
		 int number = 0;
		 int total = result.getTotal();
		 
		 if(request.getParameter("page") != null){
			 try{
				 page = Integer.parseInt(request.getParameter("page"));	
				 number = page * pageNumber;
			 }catch(Exception e){}
		 }else if(request.getParameter("number") != null){
			 try{
				 number = Integer.parseInt(request.getParameter("number"));	
				 page = number / pageNumber;
			 }catch(Exception e){}
		 }
		 
		 List<SearchResultItem> currentPage = new LinkedList<SearchResultItem>();
		 for(int i=page * pageNumber;i<result.getTotal();i++){
			 if(i>=(page + 1)* pageNumber) break;
			 currentPage.add(result.getItems().get(i));
		 }
		 request.setAttribute("members", currentPage);
		 request.setAttribute("pages", pages);
		 request.setAttribute("page", page);
	 }			
	 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/directory/slideView.jsp").include(request,response);
	}	
	private void doViewNormal (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
	{		 
		SearchCriteria criteria = (SearchCriteria)request.getPortletSession().getAttribute("criteria",PortletSession.APPLICATION_SCOPE);
		SearchResult result = (SearchResult)request.getPortletSession().getAttribute("usResult",PortletSession.APPLICATION_SCOPE);		 
		 if(result != null){		 
			 int pageNumber = 5;
			 int pages = (result.getTotal() % pageNumber == 0) ? result.getTotal() / pageNumber : result.getTotal() / pageNumber + 1;
			 int page =0;
			 int number = 0;
			 int total = result.getTotal();
			 
			 if(request.getParameter("page") != null){
				 try{
					 page = Integer.parseInt(request.getParameter("page"));	
					 number = page * pageNumber;
				 }catch(Exception e){}
			 }else if(request.getParameter("number") != null){
				 try{
					 number = Integer.parseInt(request.getParameter("number"));	
					 page = number / pageNumber;
				 }catch(Exception e){}
			 }
			 List<SearchResultItem> currentPage = new LinkedList<SearchResultItem>();
			 for(int i=page * pageNumber;i<result.getTotal();i++){
				 if(i>=(page + 1)* pageNumber) break;
				 currentPage.add(result.getItems().get(i));
			 }
			 request.setAttribute("members", currentPage);
			 request.setAttribute("pages", pages);
			 request.setAttribute("page", page);
			 request.setAttribute("start",criteria.getRowPerPage() * page);
			 request.setAttribute("end",(criteria.getRowPerPage() * (page + 1) < result.getTotal()) ? criteria.getRowPerPage() * (page + 1) : result.getTotal());
			 
			 //flow view	 		 
			 if(number >= total) number = total - 1;
			 if(number < 0) number = 0;
			 List<SearchResultItem> leftPage = new LinkedList<SearchResultItem>();
			 for(int i=number - 5;i<number;i++){
				 if(i < 0) continue;
				 if(i >= total) break;
				 leftPage.add(result.getItems().get(i));
			 }
			 List<SearchResultItem> rightPage = new LinkedList<SearchResultItem>();
			 for(int i=number + 1;i<=number + 5;i++){
				 if(i >= total) break;
				 rightPage.add(0,result.getItems().get(i));
			 }
			 request.setAttribute("leftPage", leftPage);
			 request.setAttribute("rightPage", rightPage);
			 if(number < total)
				 request.setAttribute("current", result.getItems().get(number));
	
		 }			
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/directory/view.jsp").include(request,response);
	}	
	
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/directory/edit.jsp").include(request,response);
	 }
	
}
