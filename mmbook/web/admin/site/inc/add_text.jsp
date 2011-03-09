<%@ page contentType="text/html; charset=UTF-8"%>
<%
System.out.println(request.getParameter("pageTitle"));
 %>
<script type="text/javascript"> 
var field_base_text = new Ext.form.FieldSet({
    id : 'field_add_text',
	title : '小说内容',
	autoHeight : true,
	layout: 'fit',
    layout:'column',
    items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [
		                    {
		                    	xtype:'textfield',
		                        fieldLabel: '章节号',
		                        name:'siteContent.title'
		                    }
	                        ,{
		                    	xtype:'textfield',
		                        fieldLabel: '章节名称',
		                        name:'siteContent.author'
		                     }
	                    ]
            	},{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [ 
		                       {
		                    	xtype:'textarea',
		                        fieldLabel: '章节内容',
		                        name:'siteContent.title'
		                        } 
                    ]
	 }]
});


</script>