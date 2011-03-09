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

package org.light.portlets.email;

import static org.light.portal.util.Constants._CHARSET_UTF;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 
 * @author Jianmin Liu
 **/
public class GmailClient extends Authenticator
{
  public static final int SHOW_MESSAGES = 1;
  public static final int CLEAR_MESSAGES = 2;
  public static final int SHOW_AND_CLEAR = SHOW_MESSAGES + CLEAR_MESSAGES;
  public final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
  protected String fullName;
  protected String userName;
  protected String userEmail;
  protected String password;
  protected Session session;
  protected PasswordAuthentication authentication;
  
  public GmailClient(String username, String password)
  {	
	if(username.indexOf("@") > 0){
		int index = username.indexOf("@");
		this.userName = username.substring(0,index); 
		this.userEmail = username ;
	}
	else{
		this.userName = username;
		this.userEmail = username+"@gmail.com";
	}
    this.password = password;
    authentication = new PasswordAuthentication(this.userName, password);
    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    Properties props = System.getProperties();
    props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.pop3.socketFactory.fallback", "false");
    props.setProperty("mail.pop3.port", "995");
    props.setProperty("mail.pop3.socketFactory.port", "995");

    session = Session.getDefaultInstance(props,this);
  }
  
  public GmailClient(String fullName,String username, String password)
  {
	this.fullName = fullName;
	if(username.indexOf("@") > 0){
		int index = username.indexOf("@");
		this.userName = username.substring(0,index); 
		this.userEmail = username ;
	}
	else{
		this.userName = username;
		this.userEmail = username+"@gmail.com";
	}
    this.password = password;
    authentication = new PasswordAuthentication(this.userName, password);
    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    Properties props = System.getProperties();
    props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.pop3.socketFactory.fallback", "false");
    props.setProperty("mail.pop3.port", "995");
    props.setProperty("mail.pop3.socketFactory.port", "995");

    session = Session.getDefaultInstance(props,this);
  }
  
  public PasswordAuthentication getPasswordAuthentication()
  {
    return authentication;
  }
  
  public void sendMessage(String toEmail, String cc, String bcc,String subject, String content)
  throws MessagingException
{ 
   Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
   Properties props = System.getProperties();
   props.put("mail.smtp.host", "smtp.gmail.com");
   props.put("mail.smtp.ssl", "true");
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.port", "465");
   props.put("mail.smtp.socketFactory.port", "465");
   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
   props.put("mail.smtp.socketFactory.fallback", "false");
   //props.put("mail.smtp.user", userName);
   //props.put("mail.smtp.passwd", password);
   session = Session.getDefaultInstance(props,this);
 
 
//sess.setDebug(true);
  MimeMessage mess = new MimeMessage(session);
  mess.setSubject(subject);
 
  StringTokenizer toST = new StringTokenizer(toEmail, ",;");
  while(toST.hasMoreTokens())
  {
   Address addr = new InternetAddress(toST.nextToken());
   mess.addRecipient(Message.RecipientType.TO, addr);
  }
  String from = userEmail;
  if(this.fullName != null){	
	  if(this.fullName.indexOf(",") < 0) 
		  from = this.fullName+"<"+this.userEmail+">";
	  else
		  from = "\""+this.fullName+"\"<"+this.userEmail+">";
  }
  mess.setFrom(new InternetAddress(from));
  
  if(cc != null)
  {
   StringTokenizer ccST = new StringTokenizer(cc, ",;");
   while(ccST.hasMoreTokens())
   {
    Address addr = new InternetAddress(ccST.nextToken());
    mess.addRecipient(Message.RecipientType.CC, addr);
   }
  }

  if(bcc != null)
  {
   StringTokenizer bccST = new StringTokenizer(bcc, ",;");
   while(bccST.hasMoreTokens())
   {
    Address addr = new InternetAddress(bccST.nextToken());
    mess.addRecipient(Message.RecipientType.BCC, addr);
   }
  }
 
 
  BodyPart messageBodyPart = new MimeBodyPart();
  Multipart multipart = new MimeMultipart();
  if(content != null)
  {
   messageBodyPart.setText(content);
   multipart.addBodyPart(messageBodyPart);
  }
//  String[] attachments = null;
//  if(attachments != null)
//  {
//   for(int i = 0; i < attachments.length; i++)
//   {
//    messageBodyPart = new MimeBodyPart();
//    DataSource source = new FileDataSource(attachments[i]);
//    messageBodyPart.setDataHandler(new DataHandler(source));
//    messageBodyPart.setFileName(attachments[i].getName());
//    multipart.addBodyPart(messageBodyPart);
//   }
//  }
 
  mess.setContent(multipart);

  Transport.send(mess);
  
 }
  public void sendMessage2(String toEmail, String subject, String content)
      throws MessagingException
  {
	  //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	  //Properties props = System.getProperties();
	  Properties props = new Properties();
	  props.put("mail.smtp.host", "smtp.gmail.com");
	  //props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	  //props.setProperty("mail.smtp.socketFactory.fallback", "false");
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.port", "587");
	  //props.setProperty("mail.smtp.socketFactory.port", "587");
	  //props.setProperty("mail.smtps.auth", "true");
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.ssl", "true");

	  Session session = Session.getDefaultInstance(props, new Authenticator(){
	      protected PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication(userName, password);
	      }});

	       // -- Create a new message --
	  Message msg = new MimeMessage(session);

	  // -- Set the FROM and TO fields --
	  msg.setFrom(new InternetAddress(userEmail));
	  msg.setRecipients(Message.RecipientType.TO,
	    InternetAddress.parse(toEmail,false));
	  msg.setSubject(subject);
	  msg.setText(content);
	  msg.setSentDate(new Date());
	  Transport.send(msg);
	  
  }
  
  public List<MailBean> checkInbox(int mode)
    throws Exception
  {
	List<MailBean> mails = new ArrayList<MailBean>();
	URLName urln = new URLName("pop3","pop.gmail.com",995,null,userEmail, password);
		  Store store = session.getStore(urln);
		  Folder inbox = null;
		   store.connect();
		   inbox = store.getFolder("INBOX");
		   inbox.open(Folder.READ_ONLY);
		   int unreadCount = inbox.getUnreadMessageCount();
//		   FetchProfile profile = new FetchProfile();
//		   profile.add(FetchProfile.Item.FLAGS);
		   Message[] messages = inbox.getMessages();
//		   inbox.fetch(messages, profile);		   
		   for (int i = 0; i < messages.length; i++) {
		   //for (int i = 0; i < 10; i++) {		    
		    String from = decodeText(messages[i].getFrom()[0].toString());
		    StringBuffer contentBuffer = new StringBuffer();
//	        Part messagePart=messages[i];
//	        Object content=messagePart.getContent();
//	        // -- or its first body part if it is a multipart message --
//	        if (content instanceof Multipart)
//	        {
//	          messagePart=((Multipart)content).getBodyPart(0);         
//	        }
//	        // -- Get the content type --
//	        String contentType=messagePart.getContentType();
//	        // -- If the content is plain text, we can print it --        
//	        if (contentType.startsWith("text/plain")
//	         || contentType.startsWith("text/html"))
//	        {
//	          InputStream is = messagePart.getInputStream();
//	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
//	          String thisLine=reader.readLine();
//	          while (thisLine!=null)
//	          {
//	            contentBuffer.append(thisLine);
//	            thisLine=reader.readLine();
//	          }
//	        }
	        
	        int flag = 0;
//	        Flags flags = messages[i].getFlags();
//	        Flags.Flag[] sf = flags.getSystemFlags();
//	        for (int j = 0; j < sf.length; j++) {
//	               if (sf[j] == Flags.Flag.RECENT)
//	                   flag= 1;
//	        }
	        InternetAddress ia = new InternetAddress(from);
		    mails.add(0,new MailBean(ia.getPersonal(),ia.getAddress(),null,messages[i].getSubject(),contentBuffer.toString(),messages[i].getSentDate(),flag,messages.length,unreadCount,messages[i]));
		    
		   }
    return mails;
  }
  
  public List<MailBean> getMessage(int mode, int pageId,int row)
  throws Exception
{
  List<MailBean> mails = new ArrayList<MailBean>();
  URLName urln = new URLName("pop3","pop.gmail.com",995,null,userEmail, password);
  Store store = session.getStore(urln);
  store.connect();
  Folder root = store.getDefaultFolder();
  Folder inbox = root.getFolder("inbox");
  inbox.open(Folder.READ_ONLY);
  
//  FetchProfile profile = new FetchProfile();
//  profile.add(FetchProfile.Item.ENVELOPE);
  int unreadCount = inbox.getUnreadMessageCount();
  Message[] msgs = inbox.getMessages();
//  inbox.fetch(msgs, profile);
  int start= msgs.length - (pageId - 1) * row;
  int end = msgs.length - pageId * row;
  if(end < 0) end = 0;  
  for (int i=start - 1; i >= end; i--)
  {
    MimeMessage msg = (MimeMessage)msgs[i]; 
    String contentText = null;
      //      -- Get the message part (i.e. the message itself) --
//      StringBuffer contentBuffer = new StringBuffer();
//      
//      Part messagePart=msg;
//      Object content=messagePart.getContent();      
//      // -- or its first body part if it is a multipart message --
//      if (content instanceof Multipart)
//      {
//    	System.out.println(((Multipart)content).getCount());
//        messagePart=((Multipart)content).getBodyPart(0);         
//      }
//      if(content instanceof String){
//      	contentText = (String)content;
//      }else{
//	        // -- Get the content type --
//	        //String contentType=messagePart.getContentType();
//	        // -- If the content is plain text, we can print it --    	        
//	        if (messagePart.isMimeType("text/plain")
//	         || messagePart.isMimeType("text/html"))
//	        {
//	          InputStream is = messagePart.getInputStream();
//	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
//	          String thisLine=reader.readLine();
//	          while (thisLine!=null)
//	          {
//	            contentBuffer.append(thisLine);
//	            thisLine=reader.readLine();
//	          }
//	        }
//	        if (messagePart.isMimeType("multipart/*")){
//	        	Multipart mp = (Multipart)messagePart.getContent();	     	    
//	     	    int count = mp.getCount();
//	     	    for (int j = 0; j < count; j++){
//	     	    	Part mpc = mp.getBodyPart(j);
//	     	    	if (mpc.isMimeType("text/plain")){
//	     	    	   if(contentBuffer.length() == 0){
//	     	  	          InputStream is = mpc.getInputStream();
//	     	  	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
//	     	  	          String thisLine=reader.readLine();
//	     	  	          while (thisLine!=null)
//	     	  	          {
//	     	  	            contentBuffer.append(thisLine);
//	     	  	            thisLine=reader.readLine();
//	     	  	          }
//	     	    	   }
//	     	  	    }
//	     	    	if (mpc.isMimeType("text/html")){
//	     	    		  contentBuffer = new StringBuffer();
//	     	    		  InputStream is = mpc.getInputStream();
//	     	  	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
//	     	  	          String thisLine=reader.readLine();
//	     	  	          while (thisLine!=null)
//	     	  	          {
//	     	  	            contentBuffer.append(thisLine);
//	     	  	            thisLine=reader.readLine();
//	     	  	          }
//	     	    	}
//	     	    }
//	     	   if(((Multipart)content).getCount() > 1){
//	     		   messagePart=((Multipart)content).getBodyPart(1); 
//	     		   String contentType=messagePart.getContentType();
//	     		  
//	     	   }
//	        }
//	        if (messagePart.isMimeType("message/rfc822")){
//	        	
//	        }
//	        contentText = contentBuffer.toString();
//      }
      
      int flag = 0;
//      Flags flags = msg.getFlags();
//      Flags.Flag[] sf = flags.getSystemFlags();
//      if(sf.length == 0){
//    	  flag= 1;
//      }else{
//	      for (int j = 0; j < sf.length; j++) {
//	//      	System.out.println(sf[j]);
//	//      	System.out.println(Flags.Flag.RECENT);
//	//      	System.out.println(Flags.Flag.SEEN);
//	         if (sf[j] != Flags.Flag.SEEN){
//	             flag= 1;
//	             System.out.println(msg.getFrom()[0].toString()+"--"+msg.getSubject());
//	         }
//	      }
//      }      
      Address [] address = msg.getFrom();
      String from ="(unknow sender)";
      String fromEmail = null;
      if(address != null && address.length > 0){    	    	  
    	  InternetAddress ia = new InternetAddress(address[0].toString());
    	  from = ia.getPersonal();
    	  fromEmail = ia.getAddress();
      }
      
	  mails.add(new MailBean(from,fromEmail,null,msg.getSubject(),contentText,msg.getSentDate(),flag,msgs.length,unreadCount,msg));
    }
  inbox.close(true);
  store.close();
  return mails;
}
  
  public MailBean getMessageDetail(Message message) throws Exception{
	  MailBean mail = null;
	  URLName urln = new URLName("pop3","pop.gmail.com",995,null,userEmail, password);
	  Store store = session.getStore(urln);
	  store.connect();
	  Folder root = store.getDefaultFolder();
	  Folder inbox = root.getFolder("inbox");
	  inbox.open(Folder.READ_ONLY);
	  int unreadCount = inbox.getUnreadMessageCount();
	  FetchProfile profile = new FetchProfile();
	  profile.add(FetchProfile.Item.ENVELOPE);
	  Message[] msgs = inbox.getMessages();
	  inbox.fetch(msgs, profile);
	  for (int i = msgs.length - 1; i >= 0; i--){
		  MimeMessage msg = (MimeMessage)msgs[i];
		  if(msg.getSentDate().equals(message.getSentDate()) && msg.getFrom()[0].toString().equals(message.getFrom()[0].toString())){		  
			  StringBuffer contentBuffer = new StringBuffer();
		      String contentText = null;
		      Part messagePart=msg;
		      Object content=messagePart.getContent();      
		      // -- or its first body part if it is a multipart message --
		      if (content instanceof Multipart)
		      {
		        messagePart=((Multipart)content).getBodyPart(0);         
		      }
		      if(content instanceof String){
		      	contentText = (String)content;
		      }else{
			        // -- Get the content type --
			        //String contentType=messagePart.getContentType();
			        // -- If the content is plain text, we can print it --    	        
			        if (messagePart.isMimeType("text/plain")
			         || messagePart.isMimeType("text/html"))
			        {
			          InputStream is = messagePart.getInputStream();
			          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
			          String thisLine=reader.readLine();
			          while (thisLine!=null)
			          {			        	  
			        	  if(thisLine.equals(""))
	     	  	            	contentBuffer.append("<br/>");
	     	  	            else
	     	  	            	contentBuffer.append(thisLine).append("<br/>");
			            thisLine=reader.readLine();
			          }
			        }
			        if (messagePart.isMimeType("multipart/*")){
			        	Multipart mp = (Multipart)messagePart.getContent();	     	    
			     	    int count = mp.getCount();
			     	    for (int j = 0; j < count; j++){
			     	    	Part mpc = mp.getBodyPart(j);
			     	    	if (mpc.isMimeType("text/plain")){
			     	    	   if(contentBuffer.length() == 0){
			     	  	          InputStream is = mpc.getInputStream();
			     	  	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
			     	  	          String thisLine=reader.readLine();
			     	  	          while (thisLine!=null)
			     	  	          {			     	  	        	
			     	  	            if(thisLine.equals(""))
			     	  	            	contentBuffer.append("<br/>");
			     	  	            else
			     	  	            	contentBuffer.append(thisLine).append("<br/>");
			     	  	            thisLine=reader.readLine();
			     	  	          }
			     	    	   }
			     	  	    }
			     	    	if (mpc.isMimeType("text/html")){
			     	    		  contentBuffer = new StringBuffer();
			     	    		  InputStream is = mpc.getInputStream();
			     	  	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
			     	  	          String thisLine=reader.readLine();
			     	  	          while (thisLine!=null)
			     	  	          {
			     	  	        	if(thisLine.equals(""))
			     	  	            	contentBuffer.append("<br/>");
			     	  	            else
			     	  	            	contentBuffer.append(thisLine).append("<br/>");
			     	  	            thisLine=reader.readLine();
			     	  	          }
			     	    	}
			     	    }
			     	   if(((Multipart)content).getCount() > 1){
			     		   messagePart=((Multipart)content).getBodyPart(1); 
			     		   String contentType=messagePart.getContentType();
			     		  
			     	   }
			        }
			        if (messagePart.isMimeType("message/rfc822")){
			        	
			        }
			        contentText = contentBuffer.toString();
		      }
		      if(contentText != null) contentText=URLDecoder.decode(contentText,_CHARSET_UTF);
		      Address [] address = msg.getFrom();
		      String from ="(unknow sender)";
		      String fromEmail = null;
		      if(address != null && address.length > 0){    	    	  
		    	  InternetAddress ia = new InternetAddress(address[0].toString());
		    	  from = ia.getPersonal();
		    	  fromEmail = ia.getAddress();
		      }		      
			  mail = new MailBean(from,fromEmail,null,msg.getSubject(),contentText,msg.getSentDate(),0,msgs.length,unreadCount,msg);
			  break;
		  }
	  }
	  
	  inbox.close(true);
	  store.close();
	  return mail;
  }
  
  public void deleteMessage(Message message)
  throws Exception
{  
  URLName urln = new URLName("pop3","pop.gmail.com",995,null,userEmail, password);
  Store store = session.getStore(urln);
  Folder inbox = null;
   store.connect();
   inbox = store.getFolder("INBOX");
   inbox.open(Folder.READ_WRITE);
   //FetchProfile profile = new FetchProfile();
   //profile.add(FetchProfile.Item.FLAGS);
   Message[] messages = inbox.getMessages();
   //inbox.fetch(messages, profile);   
   for (int i = 0; i < messages.length; i++) {
    //?????
    String from = decodeText(messages[i].getFrom()[0].toString());
	  MimeMessage msg = (MimeMessage)messages[i];
	  if(msg.getSentDate().equals(message.getSentDate()) && msg.getFrom()[0].toString().equals(message.getFrom()[0].toString())){
		  msg.setFlag(Flags.Flag.DELETED, true);
		  break;
	  }
  }
  
  inbox.close(true);
  store.close();
}
    
  
  protected static String decodeText(String text)
  throws UnsupportedEncodingException {
 if (text == null)
  return null;
 if (text.startsWith("=?GB") || text.startsWith("=?gb"))
  text = MimeUtility.decodeText(text);
 else
  text = new String(text.getBytes("ISO8859_1"));
 return text;
}
}
