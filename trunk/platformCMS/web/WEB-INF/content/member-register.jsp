<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<title>设计圈_用户注册</title>
		<link type="text/css" rel="stylesheet" href="css/tag.css" />
		<link type="text/css" href="css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/login.css" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="css/style2.css" /> 

		<!--[if lte IE 6]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE6.css" />
		<![endif]-->
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/navHankIE7.css" />
		<![endif]-->
       	<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/ui.core.js"></script>
		<script type="text/javascript" src="js/ui.dialog.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>
		<script type="text/javascript" src="js/userlogin.js"></script>

		<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
		<script>
		function submitForm(id){
			document.getElementById(id).submit();
		}
		$(document).ready(function(){
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
			rules: { 
				agree: "required",
				loginName: { 
       				required: true, 
       				email:"email",
       				remote: "checkloginname.action?orgLoginName="+encodeURIComponent('${loginName}')
   				},
    	 		name: {
  					  required : true,
  					  userName : true
   	    	 	},
    	  		password: {
    					required: true,
    					minlength:3
    			}, 
    			passwordConfirm: {
        			    required:true,
    					equalTo:"#password"
    			},
    			email:"email"
				},
				messages: {
					agree:"您还没有同意",
					name:"请填写用户名",
					loginName: {
						remote: "用户登录名已存在"
					},
					passwordConfirm: {
						equalTo: "输入与上面相同的密码"
					}
				}
			});
		});

		jQuery.validator.addMethod("userName", function(value, element) {    
			  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);    
		}, "用户名只能包括中文字、英文字母、数字和下划线");    
	</script>
	</head>
	<body>
	<%@ include file="/common/userlogin.jsp" %>
	<div class="container">
		<div id="top">
			<!-- 顶部的容器 -->
			<div class="headernb">
				<!-- 放logo的地方 -->
				<div class="jhlogo">
					<a href="${ctx}">
						<img src="images/logo.gif" alt="这里写公司的信息，方便搜索引擎搜索" />
					</a>
				</div>
			    <div class="dibunav">
			      <div class="riqi"><script type="text/javascript" src="js/time.js"></script></div>
			      <div class="topnav">
			        <ul>
			        	<li><a href="news"><span>_资讯</span></a></li>
						<li><a href="person"><span>_人物</span></a></li>
						<li><a href="${ctx}/show?showCategory.id=56&isNewsVote=all"><span>_秀场</span></a></li>
						<li><a href="special"><span>_专题</span></a></li>
						<li><a href="circle"><span>_圈网</span></a></li>
						<li><a href="download"><span>_下载</span></a></li>
						<li><a href="${ctx}/circle-web.action"><span>_江湖</span></a></li>
						<li><a href="#"><span>_商店</span></a></li>
						<li><a href="#"><span>_书店</span></a></li>	
			        </ul>
			      </div>
			    </div>
				<!-- 搜索容器 -->
				<div class="zuonav">
				 	<div class="youdh">
					<ul>
						
						<li><a href="#"><span>全站导航</span></a></li>
						<li id="linkreg" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>><a href="javascript:toregister();"><span>注册</span></a></li>
						<li id="logout_login" <s:if test='userName != "roleAnonymous" && userName != null'> style="display:none"</s:if>><a href="#" onclick="showLogin();"><span class="u_login">登录</span></a></li>
						<li id="site_home"><s:if test='userName != "roleAnonymous" && userName != null'><span id='blogcenter'><a href="blog/member-home.action">个人中心</a></span></s:if><s:else><a href="${ctx}"><span id="ooowoindex">设计圈首页</span></a></s:else></li>
						<s:if test='userName != "roleAnonymous" && userName != null'>
						<li id="logout">${userName}[<a href="#" style="margin-left:0px;" onclick="userlogout();">退出</a>]</li>
						</s:if>
					</ul>
					</div>
					<div class="ss">
						<form action="${ctx }/searcharticle.action" name="searchnews" method="post">
                        	<input type="text" name="newsContent"  class="indexss"/>
                        	<input name="biaoqian" type="button" class="bianqianan" onclick="searchNews()"/>
                       </form>
                    </div>
				</div>
			</div>
			<!-- 导航条 -->
			<div class="hd">
			    <div class="navbh">
			      <ul>
						<li><a href="${ctx}/newslist.action?showCategory.id=59"><span>_抢先看</span></a></li>
						<li><a href="${ctx}/personlist.action?showCategory.id=70"><span>_色女郎</span></a></li>	
						<li><a href="#"><span>_LOGO</span></a></li>	
						<li><a href="#"><span>_图库</span></a></li>	
						<li><a href="#"><span>_招聘</span></a></li>	
						<li><a href="${ctx}/speciallist.action?showCategory.id=76"><span>_杂志</span></a></li>	
						<li><a href="#"><span>_博客</span></a></li>	
						<li><a href="#"><span>_论坛</span></a></li>	
						<li><a href="#"><span>_群英会</span></a></li>	
						<li><a href="${ctx}/tag"><span>_标签TAG</span></a></li>	
						<li><a href="#"><span>_平面</span></a></li>	
						<li><a href="#"><span>_创意</span></a></li>	
						<li><a href="#"><span>_卡通</span></a></li>	
						<li class="lilaster"><a href="${ctx}/circlelist.action?showCategory.id=80"><span>_艺术</span></a></li>
			      </ul>
			    </div>
			  </div>
			<div class="clean"></div>
		</div>

		<div id="content">
		
			<div id="topBanner">
						<a href="" target="_blank">
						<img src="/ooowo" height="100px" width="960px" alt="top_banner" />
						</a>
			</div>
			
			<div id="postion">
				<p>
					<span>您的位置:首页&gt;注册</span>
				</p>
				<hr class="splitline"/>
			</div>
			<s:if test="isUidUsercode">
			<div style="width:830px; margin-top:40px;float:right;" >
                <div class="bqmainbh" align="left">
                    <div>
                    <span class="bigsize">Regisster new member_注册新用户</span><br />
                    <span class="bigsize">欢迎来到中国新锐设计师创意集会网站</span></div>
                </div>
                <div class="tianxieziliao">
                    <ul>
                        <li><a href="">1_填写个人完整资料</a></li>
                        <li><a href="">2_收邮件激活帐号</a></li>
                        <li><a href="">3_拥有你的设计圈</a></li>
                    </ul>
                </div>
                <div class="tishi" align="left">抱歉，ooowo(设计圈)目前需要邀请码才能注册，看看你身边的朋友谁有设计圈吧<br />注_目前ooowo(设计圈)处于内测期，我们会在近期内开放注册，请多关注www.ooowo.com		           </div>
                
                <div class="zhucebiao" align="left">
                <form action="member-reg-success.action" id="inputForm" method="post" name="inputForm">
                    <input type="hidden" name="mailActivtion.mailCode"/>
                    <table border="0" cellspacing="0">
                      <tr>
                        <td width="68" height="80" valign="top">你的邀请码&nbsp;</td>
                        <td width="388" valign="top" align="left"><input disabled="disabled" name="yqm" type="text" class="yaoqingma" value="${member.usercode}"/></td>
                        <td width="238" rowspan="14" valign="top">
                          <s:if test="memberinfo.headPortraitUri!=null && memberinfo.headPortraitUri!=''">
                              <img src="${ctx}${memberinfo.headPortraitUri}" width="100px" height="100px"/>
                          </s:if><s:else>
                       		 <img src="images/zhchepic.gif" width="100px" height="100px"/>
                       	  </s:else><br /> 欢迎你${member.name}的朋友！
                       	</td>
                      </tr>
                      <tr>
                        <td height="30" colspan="2" valign="top" align="left">填写注册信息</td>
                        </tr>
                      <tr>
                        <td height="20" valign="top" align="left">登陆邮箱</td>
                        <td height="20" valign="top" align="left"><input id="loginName"  name="loginName" type="text" class="yaoqingma"   /></td>
                      </tr>
                      <tr>
                        <td height="30" colspan="2" valign="top" align="left">该邮箱作为你在设计圈oowo.com的登陆帐号</td>
                      </tr>
                      <tr>
                        <td height="20" valign="top" align="left">登陆密码<br />					</td>
                        <td height="20" valign="top" align="left"><input id="password" name="password" type="password"  class="yaoqingma"/></td>
                      </tr>
                      <tr>
                        <td height="30" colspan="2" valign="top" align="left">密码区分大小写，长度不少于6位数</td>
                      </tr>
                      <tr>
                        <td height="30" valign="top" align="left">重输密码</td>
                        <td height="30" valign="top" align="left"><input id="password2" name="passwordConfirm" type="password"  class="yaoqingma"/></td>
                      </tr>
                      <tr>
                        <td height="19" valign="top" align="left">姓名									</td>
                        <td height="19" valign="top" align="left"><input id="name" name="name"  type="text" class="yaoqingma"   /></td>
                      </tr>
                      <tr>
                        <td height="30" colspan="2" valign="top" align="left">请用真实姓名作为你的用户名，以便于你的朋友在设计圈找到你！</td>
                      </tr>
                      <!-- 
                      <tr>
                        <td height="40" valign="top" align="left">验证码</td>
                        <td height="40" valign="top" align="left"><input name="yqm" type="text" class="yaoqingma"   />&nbsp;&nbsp;1234&nbsp;&nbsp;<a href="">看不清？单击验证码换一张</a></td>
                      </tr>
                       -->
                       <tr>
                        <td height="40" colspan="2" valign="top" align="left"><input name="agree" id="agree" type="checkbox" value="" />&nbsp;<span class="STYLE1">&nbsp;我已阅读并同意遵守</span></td>
                      </tr>
                      <tr>
                        <td height="50" colspan="2" valign="top"><input name="22" type="submit" value="JOIN 提交注册" /></td>
                      </tr>
                    </table>
                    <input type="hidden" name="mid" value="${member.id}"/>
                    </form>
                    <div class="xunzhao">
                        <div class="zzbiaoti" align="left">寻找你的朋友是否在设计圈</div>
                        <div class="zzsousuo">
                        <input name="yuming" type="text" class="yaoqingma"  class="bitiankuang" />
                        <input name="biaoqian" type="button" value="" class="bianqianan"/>
                        </div>
                    </div>
                </div>
            </div>
            </s:if><s:else>
			    <div style="width:830px; margin-top:40px; padding-left:120px;" align="left">
	               <div class="bqmainbh">
						<div class="xinyonghu">
						<span class="bigsize">Regisster new member_注册新用户</span><br />
						<span>欢迎来到中国新锐设计师创意集会网站</span>
						</div>
					</div>
					<div class="gongxi">对不起！注册失败,<br /><br />您的邀请链接是不合法的地址，请您核对后在继续操作，谢谢您的支持。
					</div>
	            </div>
			</s:else>
        <div class="clean"></div>
        <div id="bannerFour">
           
					
						<a href="" target="_blank">
						<img src="/ooowo" height="100px" width="960px" alt="footer" />
						</a>
					
			
        </div>
			<%@ include file="/common/foot.jsp"%>
		</div>
	</div>
	</body>
</html>