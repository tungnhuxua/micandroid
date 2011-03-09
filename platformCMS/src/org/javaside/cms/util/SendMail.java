package org.javaside.cms.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 发送激活邮件
 * 
 * @author LiuDingKun
 * @address dkun.liu@gmail.com
 */
public class SendMail {
	String host = "smtp.qq.com";
	String user = "113444884";
	String password = "198051logoman";

	public void setHost(String host) {
		this.host = host;
	}

	public void setAccount(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public void send(String from, String to, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);//指定SMTP服务器
		props.put("mail.smtp.auth", "true");//指定是否需要SMTP验证
		try {
			Session mailSession = Session.getDefaultInstance(props);

//			mailSession.setDebug(true);//是否在控制台显示debug信息

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));//发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//收件人
			//message.addRecipient(Message.RecipientType.CC,new InternetAddress("orion@haobin.com"));//收件人

			message.setSubject(subject);//邮件主题
			message.setText(content);//邮件内容   
			message.setSentDate(new java.util.Date());//发信日期
			message.saveChanges();

			BodyPart mdp = new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
			mdp.setContent(content, "text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
			Multipart mm = new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对象(事实上可以存放多个)
			mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			message.setContent(mm);//把mm作为消息对象的内容

			message.saveChanges();
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void main(String args[]) {
//		SendMail sm = new SendMail();
//		// sm.setHost("smtp.qq.com");//指定要使用的邮件服务器
//		// sm.setAccount("qq帐号","qq密码");//指定帐号和密码 必须登录QQ邮箱开能smtp服务
//		// sm.setAccount("65162125","DINGKUN226");//指定帐号和密码 必须登录QQ邮箱开能smtp服务
//		String content = "<div class='ii gt' id=':gi'>您好！<br/>非常感谢您通过<a target='_blank' href='http://www.ooowo.com'>www.ooowo.com</a>网站提交了注册申<wbr/>请。我们非常期待能为您提供有价值的专业信息服务。<br/>现在请您点击以下链接（如链接无法点击，<wbr/>建议您复制以下链接内容，并粘贴至浏览器地址栏中，点击运行），<wbr/>完成最后一步注册激活工作。<br/><br/><a target='_blank' style='color: red;' href='http://customer.ilib.cn/Login/Active.aspx?eMail=dkun.liu@gmail.com&amp;code=5a8ce975-bac1-4dbe-b7ae-257be5b4a6b0'>http://customer.ilib.cn/Login/<wbr/>Active.aspx?eMail=dkun.liu@<wbr/>gmail.com&code=5a8ce975-bac1-<wbr/>4dbe-b7ae-257be5b4a6b0</a><br/><br/>成功激活后，即刻开始您在设计圈网站的快乐之旅。<br/><br/>衷心祝福您工作愉快、生活幸福、学业有成！<br/><br/>";
//		//String content="<html><head><meta http-equiv='Content-Type' content='text/html; charset=gb2312' /><title>hello world</title></head><body><p>恭喜你！收到邮件了！</p><p><a href='http://www.3km.com.cn' target='_blank'>www.3km.com.cn</a> </p></body></html>";
//		/*
//		 * @param String 发件人的地址
//		 * @param String 收件人地址
//		 * @param String 邮件标题
//		 * @param String 邮件正文
//		 */
//		sm.send("65162125@qq.com", "dingkunk@163.com", "设计圈账户注册激活，欢迎您的加入。", content);
		
		SendMail sm=new SendMail();
		  sm.setHost("smtp.qq.com");//指定要使用的邮件服务器
//		  sm.setAccount("qq帐号","qq密码");//指定帐号和密码 必须登录QQ邮箱开能smtp服务
		  sm.setAccount("113444884","198051logoman");//指定帐号和密码 必须登录QQ邮箱开能smtp服务
		
		  /*
		   * @param String 发件人的地址
		   * @param String 收件人地址
		   * @param String 邮件标题
		   * @param String 邮件正文
		  */
		  sm.send("113444884@qq.com","65162125@qq.com","哈哈，这是标题","呵呵，好牛B啊。");
	}
}
