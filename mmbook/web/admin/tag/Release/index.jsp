<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%
	String v_id = (null==request.getParameter("v")) ? "1" : request.getParameter("v");
	String u_id = (null==request.getParameter("u")) ? "1" : request.getParameter("u");
%>
<html>
<head>
<title>数据重载</title>

<script type="text/javascript">
Ext.namespace("Ext.ux.cmssystem");
Ext.ux.cmssystem.init<%=u_id%>=Ext.extend(function(){
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');

var field_init_page<%=u_id%> = new Ext.form.FieldSet({
    id:'field_init_page<%=u_id%>',
	title : '全局变量',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.3,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '10%'
			         },
			         items: [{
			            xtype:'textfield',
	                    fieldLabel: '全局变量<br/>重新生成页面全局变量',
	                    hidden: true
		             }]
            	},{
	                columnWidth:.6,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
	                buttonAlign : 'left',
                    buttons: [{
                        text: '更新',
  	                    handler:function(){
  	                        Ext.MessageBox.show({title: '请稍候...',msg: '正在更新全局变量...',width:240,wait:true,animEl:true});
                            setTimeout(function(){
  	                            Ext.Ajax.request({
				               	    url:'tag/Release/updatePage.do?versionId='+<%=v_id%>,
		                      	    method:'POST',
		                      	    success:function(response){
		                      	        Ext.MessageBox.hide();
		                     	        var data = Ext.util.JSON.decode(response.responseText);
		                     	        if (data.success == true){
		                      	            Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                      	        }else{
		                      	            Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                  	            };
		                  	        },scope:this
		                        });
		                     }, 300);
  	                    }
  	               }]
          }]
});

var field_init_templet<%=u_id%> = new Ext.form.FieldSet({
    id:'field_init_templet<%=u_id%>',
	title : '模板页面',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.3,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '10%'
			         },
			         items: [{
			            xtype:'textfield',
	                    fieldLabel: '模板页面<br/>重新生成模板页面',
	                    hidden: true
		             }]
            	},{
	                columnWidth:.6,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
	                buttonAlign : 'left',
                    buttons: [{
                        text: '更新',
  	                    handler:function(){
  	                        Ext.MessageBox.show({title: '请稍候...',msg: '正在更新模板页面...',width:240,wait:true,animEl:true});
                            setTimeout(function(){
  	                            Ext.Ajax.request({
				               	    url:'tag/Release/updateCmsTemplet.do?versionId='+<%=v_id%>,
		                      	    method:'POST',
		                      	    success:function(response){
		                      	        Ext.MessageBox.hide();
		                     	        var data = Ext.util.JSON.decode(response.responseText);
		                     	        if (data.success == true){
		                      	            Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                      	        }else{
		                      	            Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                  	            };
		                  	        },scope:this
		                        });
		                     }, 300);
  	                    }
  	               }]
          }]
});

var field_init_category<%=u_id%> = new Ext.form.FieldSet({
    id:'field_init_category<%=u_id%>',
	title : '栏目标签',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.3,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '10%'
			         },
			         items: [{
			            xtype:'textfield',
	                    fieldLabel: '栏目标签<br/>重新生成栏目标签',
	                    hidden: true
		             }]
            	},{
	                columnWidth:.6,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
	                buttonAlign : 'left',
                    buttons: [{
                        text: '更新',
  	                    handler:function(){
  	                        Ext.MessageBox.show({title: '请稍候...',msg: '正在更新栏目标签...',width:240,wait:true,animEl:true});
                            setTimeout(function(){
  	                            Ext.Ajax.request({
				               	    url:'tag/Release/updateCategory.do?versionId='+<%=v_id%>,
		                      	    method:'POST',
		                      	    success:function(response){
		                      	        Ext.MessageBox.hide();
		                     	        var data = Ext.util.JSON.decode(response.responseText);
		                     	        if (data.success == true){
		                      	            Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                      	        }else{
		                      	            Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                  	            };
		                  	        },scope:this
		                        });
		                     }, 300);
  	                    }
  	               }]
          }]
});

var field_init_content<%=u_id%> = new Ext.form.FieldSet({
    id:'field_init_content<%=u_id%>',
	title : '内容标签',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.3,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '10%'
			         },
			         items: [{
			            xtype:'textfield',
	                    fieldLabel: '内容标签<br/>重新生成内容标签',
	                    hidden: true
		             }]
            	},{
	                columnWidth:.6,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
	                buttonAlign : 'left',
                    buttons: [{
                        text: '更新',
  	                    handler:function(){
  	                        Ext.MessageBox.show({title: '请稍候...',msg: '正在更新内容标签...',width:240,wait:true,animEl:true});
                            setTimeout(function(){
  	                            Ext.Ajax.request({
				               	    url:'tag/Release/updateCmsTagContent.do?versionId='+<%=v_id%>,
		                      	    method:'POST',
		                      	    success:function(response){
		                      	        Ext.MessageBox.hide();
		                     	        var data = Ext.util.JSON.decode(response.responseText);
		                     	        if (data.success == true){
		                      	            Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                      	        }else{
		                      	            Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                  	            };
		                  	        },scope:this
		                        });
		                     }, 300);
  	                    }
  	               }]
          }]
});

var field_init_cmstagrmdation<%=u_id%> = new Ext.form.FieldSet({
    id:'field_init_cmstagrmdation<%=u_id%>',
	title : '内容推荐标签',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.3,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '10%'
			         },
			         items: [{
			            xtype:'textfield',
	                    fieldLabel: '内容推荐标签<br/>重新生成内容推荐标签',
	                    hidden: true
		             }]
            	},{
	                columnWidth:.6,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
	                buttonAlign : 'left',
                    buttons: [{
                        text: '更新',
  	                    handler:function(){
  	                        Ext.MessageBox.show({title: '请稍候...',msg: '正在更新内容推荐标签...',width:240,wait:true,animEl:true});
                            setTimeout(function(){
  	                            Ext.Ajax.request({
				               	    url:'tag/Release/updateCmsTagRmdation.do?versionId='+<%=v_id%>,
		                      	    method:'POST',
		                      	    success:function(response){
		                      	        Ext.MessageBox.hide();
		                     	        var data = Ext.util.JSON.decode(response.responseText);
		                     	        if (data.success == true){
		                      	            Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                      	        }else{
		                      	            Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                  	            };
		                  	        },scope:this
		                        });
		                     }, 300);
  	                    }
  	               }]
          }]
});

var frm_init_cmssystem<%=u_id%> = new Ext.FormPanel({
        id:'frm_init_cmssystem<%=u_id%>',
        layout: 'fit',
        labelAlign: 'right',
        frame:true,
        title: '数据重载',
        margins : '8',
        region:'center',
        labelWidth : 210,
        autoScroll : true,
        items:[field_init_page<%=u_id%>,field_init_templet<%=u_id%>,field_init_category<%=u_id%>,field_init_content<%=u_id%>,field_init_cmstagrmdation<%=u_id%>],
        buttonAlign : 'center',
        buttons: [{
                        text: '重新发布',
  	                    handler:function(){
  	                    Ext.MessageBox.confirm('确认操作','确定重新发布?',function(btn){
  	                    if (btn == 'yes'){
  	                        Ext.MessageBox.show({title: '请稍候...',msg: '正在重新发布...',width:240,wait:true,animEl:true});
                            setTimeout(function(){
  	                            Ext.Ajax.request({
				               	    url:'tag/Release/redeploy.do',
				               	    params:{
					                    versionId: <%=v_id%>
				                    },
		                      	    method:'POST',
		                      	    success:function(response){
		                      	        Ext.MessageBox.hide();
		                     	        var data = Ext.util.JSON.decode(response.responseText);
		                     	        if (data.success == true){
		                      	            Ext.Msg.show({title: '提示', msg: data.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK});
		                      	        }else{
		                      	            Ext.Msg.show({title: '错误', msg: data.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
		                  	            };
		                  	        },scope:this
		                        });
		                     }, 300)}
  	  		               },this);  
  	                    }
  	            },{
  	            text:'关闭',
  	            style : 'margin-left: 8px',
  	   		    handler:function(){
  	              Ext.MessageBox.confirm('确认操作','确定关闭栏目标签页面?',function(btn){
  	                if (btn == 'yes'){
  				       panel<%=u_id%>.remove(panel<%=u_id%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }] 
});

function fun_field_fit() {
	Ext.getCmp("field_init_page<%=u_id%>").setWidth(Ext.get("field_init_page<%=u_id%>").getWidth() - 22);
	Ext.getCmp("field_init_templet<%=u_id%>").setWidth(Ext.get("field_init_page<%=u_id%>").getWidth());
    Ext.getCmp("field_init_category<%=u_id%>").setWidth(Ext.get("field_init_page<%=u_id%>").getWidth());
	Ext.getCmp("field_init_content<%=u_id%>").setWidth(Ext.get("field_init_page<%=u_id%>").getWidth());
	Ext.getCmp("field_init_cmstagrmdation<%=u_id%>").setWidth(Ext.get("field_init_page<%=u_id%>").getWidth());
};

var panel_init_cmssystem<%=u_id%> = new Ext.Panel({
		renderTo:'init_cmssystemdiv<%=u_id%>',
		width:Ext.get("init_cmssystemdiv<%=u_id%>").getWidth(),
		height:Ext.get("init_cmssystemdiv<%=u_id%>").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_init_cmssystem<%=u_id%>]
});
fun_field_fit();
panel<%=u_id%>.on('beforeremove', function(tab, item) {
		if(item.id=='<%=u_id%>'){
		    if(Ext.getCmp(item.id)){
		        Ext.getCmp(item.id).destroy();
		        panel_init_cmssystem<%=u_id%>.destroy();
		    }
		}
});

panel<%=u_id%>.on('resize',function(){
    if(Ext.get("init_cmssystemdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('<%=u_id%>');
             panel_init_cmssystem<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_init_cmssystem<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_init_cmssystem<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_init_cmssystem<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmssystem.init<%=u_id%>();
</script>
</head>
<body>
<div id="init_cmssystemdiv<%=u_id%>" style="width:100%;height:100%;"></div>
</body>
</html>