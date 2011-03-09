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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.Authenticator;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 
 * @author Jianmin Liu
 **/
public class EmailClient extends Authenticator
{
  public static final int SHOW_MESSAGES = 1;
  public static final int CLEAR_MESSAGES = 2;
  public static final int SHOW_AND_CLEAR = SHOW_MESSAGES + CLEAR_MESSAGES;
  public final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
  protected String fullName;
  protected String user;
  protected String userEmail;
  protected Session session;
  protected PasswordAuthentication authentication;
  
  public EmailClient(String email, String password, String incomingType,String incoming, String incomingPort,String incomingSSL,String outgoing,String outgoingPort,String outgoingSSL)
  { 
	this.user= email;
	if(email.indexOf("@") > 0){
		String domain = email.substring(email.indexOf("@") + 1);
		if(incoming.endsWith(domain) && outgoing.endsWith(domain))
			this.user = email.substring(0,email.indexOf("@"));
	}
	this.userEmail = email ;
	authentication = new PasswordAuthentication(this.user, password);
	Properties props = new Properties();
	props.put("mail.user", this.user);
	props.put("mail.smtp.host", outgoing);
	props.put("mail.smtp.port", outgoingPort);
	if("1".equals(outgoingSSL)){
	  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	  props.setProperty("mail.smtp.socketFactory.fallback", "false");
	  props.setProperty("mail.smtp.socketFactory.port", outgoingPort);
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.starttls.enable", "true");
	}
	props.put("mail.transport.protocol", "smtp");
	
	if("pop3".equals(incomingType)){
	    props.put("mail.pop3.host", incoming);
	    props.put("mail.store.protocol", "pop3");
	    props.setProperty("mail.pop3.port", incomingPort);
	    if("1".equals(incomingSSL)){
	    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		    props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		    props.setProperty("mail.pop3.socketFactory.fallback", "false");		    
		    props.setProperty("mail.pop3.socketFactory.port", incomingPort);
	    }
	}else{
		props.put("mail.imap.host", incoming);
	    props.put("mail.store.protocol", "imap");
	    props.setProperty("mail.imap.port", incomingPort);
	    if("1".equals(incomingSSL)){
	    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		    props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
		    props.setProperty("mail.imap.socketFactory.fallback", "false");		    
		    props.setProperty("mail.imap.socketFactory.port", incomingPort);
	    }
	}
	
	session = Session.getInstance(props, this);
  }
  public EmailClient(String fullName,String email, String password, String incomingType,String incoming, String incomingPort,String incomingSSL,String outgoing,String outgoingPort,String outgoingSSL)
  { 
	  this(email,password,incomingType,incoming,incomingPort,incomingSSL,outgoing,outgoingPort,outgoingSSL);
	  this.fullName = fullName;	
  }
  
  public PasswordAuthentication getPasswordAuthentication()
  {
    return authentication;
  }
  
  public void sendMessage(String to, String cc, String bcc, String subject, String content)
      throws MessagingException
  {
    MimeMessage msg = new MimeMessage(session);
    String from = userEmail;
    if(this.fullName != null){	
  	  if(this.fullName.indexOf(",") < 0) 
  		  from = this.fullName+"<"+this.userEmail+">";
  	  else
  		  from = "\""+this.fullName+"\"<"+this.userEmail+">";
    }
    msg.setFrom(new InternetAddress(from));
    msg.addRecipients(Message.RecipientType.TO, to);
    if(cc != null)
    {
	     StringTokenizer ccST = new StringTokenizer(cc, ",;");
	     while(ccST.hasMoreTokens())
	     {
		      Address addr = new InternetAddress(ccST.nextToken());
		      msg.addRecipient(Message.RecipientType.CC, addr);
	     }
    }

    if(bcc != null)
    {
	     StringTokenizer bccST = new StringTokenizer(bcc, ",;");
	     while(bccST.hasMoreTokens())
	     {
		      Address addr = new InternetAddress(bccST.nextToken());
		      msg.addRecipient(Message.RecipientType.BCC, addr);
	     }
    }
    msg.setSubject(subject);
    msg.setText(content);
    Transport.send(msg);
  }
  
  public List<MailBean> getMessage(int mode, int pageId,int row)
  throws Exception
{
  List<MailBean> mails = new ArrayList<MailBean>();
  boolean show = (mode & SHOW_MESSAGES) > 0;
  boolean clear = (mode & CLEAR_MESSAGES) > 0;
  String action =
    (show ? "Show" : "") +
    (show && clear ? " and " : "") +
    (clear ? "Clear" : "");
  Store store = session.getStore();
  store.connect();
  Folder root = store.getDefaultFolder();
  Folder inbox = root.getFolder("inbox");
  inbox.open(Folder.READ_ONLY);
  
  FetchProfile profile = new FetchProfile();
  profile.add(FetchProfile.Item.ENVELOPE);
  int unreadCount = inbox.getUnreadMessageCount();
  Message[] msgs = inbox.getMessages();
  inbox.fetch(msgs, profile);
  int start= msgs.length - (pageId - 1) * row;
  int end = msgs.length - pageId * row;
  if(end < 0) end = 0;  
  for (int i=start - 1; i >= end; i--)
  {
    MimeMessage msg = (MimeMessage)msgs[i];
    if (show)
    {     	        
      //      -- Get the message part (i.e. the message itself) --
//      StringBuffer contentBuffer = new StringBuffer();
    	String contentText = null;
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
//      
      int flag = 0;
      Flags flags = msg.getFlags();
      Flags.Flag[] sf = flags.getSystemFlags();
      if(sf.length == 0){
    	  flag= 1;
      }else{
	      for (int j = 0; j < sf.length; j++) {
	         if (sf[j] != Flags.Flag.SEEN){
	             flag= 1;	            
	         }
	         if(sf[j] == Flags.Flag.SEEN){
	             flag= 0;
	             break;
	         }
	      }
      }
      InternetAddress ia = new InternetAddress(msg.getFrom()[0].toString());
	  mails.add(new MailBean(ia.getPersonal(),ia.getAddress(),null,msg.getSubject(),contentText,msg.getSentDate(),flag,msgs.length,unreadCount,msg));
    }
    if (clear)
    {
      msg.setFlag(Flags.Flag.DELETED, true);
    }
  }
  inbox.close(true);
  store.close();
  return mails;
}
  
  public MailBean getMessageDetail(Message message) throws Exception{
	  System.out.println("getMessageDetail");
	  MailBean mail = null;
	  Store store = session.getStore();
	  store.connect();
	  Folder root = store.getDefaultFolder();
	  Folder inbox = root.getFolder("inbox");
	  inbox.open(Folder.READ_WRITE);
	  int unreadCount = inbox.getUnreadMessageCount();
	  FetchProfile profile = new FetchProfile();
	  profile.add(FetchProfile.Item.ENVELOPE);
	  Message[] msgs = inbox.getMessages();
	  inbox.fetch(msgs, profile);
	  for (int i = msgs.length - 1; i >= 0; i--){
		  MimeMessage msg = (MimeMessage)msgs[i];
		  if(msg.getSentDate().equals(message.getSentDate()) && msg.getFrom()[0].toString().equals(message.getFrom()[0].toString())){
			  msg.setFlag(Flags.Flag.SEEN, true);
			  
			  StringBuffer contentBuffer = new StringBuffer();
		      String contentText = null;
		      Part messagePart=msg;
		      Object content=messagePart.getContent();      
		      // -- or its first body part if it is a multipart message --
		      if (content instanceof Multipart)
		      {
		    	System.out.println(((Multipart)content).getCount());
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
			            contentBuffer.append(thisLine);
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
			     	  	            contentBuffer.append(thisLine);
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
			     	  	            contentBuffer.append(thisLine);
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
		      InternetAddress ia = new InternetAddress(msg.getFrom()[0].toString());
			  mail = new MailBean(ia.getPersonal(),ia.getAddress(),null,msg.getSubject(),contentText,msg.getSentDate(),0,msgs.length,unreadCount,msg);
			  break;
		  }
	  }
	  
	  inbox.close(true);
	  store.close();
	  return mail;
  }
  public List<MailBean> checkInbox(int mode)
    throws Exception
  {
	List<MailBean> mails = new ArrayList<MailBean>();
    boolean show = (mode & SHOW_MESSAGES) > 0;
    boolean clear = (mode & CLEAR_MESSAGES) > 0;
    String action =
      (show ? "Show" : "") +
      (show && clear ? " and " : "") +
      (clear ? "Clear" : "");
    Store store = session.getStore();
    store.connect();
    Folder root = store.getDefaultFolder();
    Folder inbox = root.getFolder("inbox");
    inbox.open(Folder.READ_WRITE);
    int unreadCount = inbox.getUnreadMessageCount();
    FetchProfile profile = new FetchProfile();
    profile.add(FetchProfile.Item.ENVELOPE);
    Message[] msgs = inbox.getMessages();
    inbox.fetch(msgs, profile);
    for (int i = 0; i < msgs.length; i++)
    {
      MimeMessage msg = (MimeMessage)msgs[i];
      if (show)
      {     	        
        //      -- Get the message part (i.e. the message itself) --
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
	        String contentType=messagePart.getContentType();
	        // -- If the content is plain text, we can print it --        
	        if (contentType.toLowerCase().startsWith("text/plain")
	         || contentType.toLowerCase().startsWith("text/html"))
	        {
	          InputStream is = messagePart.getInputStream();
	          BufferedReader reader=new BufferedReader(new InputStreamReader(is));
	          String thisLine=reader.readLine();
	          while (thisLine!=null)
	          {
	            contentBuffer.append(thisLine);
	            thisLine=reader.readLine();
	          }
	        }
	        contentText = contentBuffer.toString();
        }
        
        int flag = 0;
        Flags flags = msg.getFlags();
        Flags.Flag[] sf = flags.getSystemFlags();
        for (int j = 0; j < sf.length; j++) {
           if (sf[j] == Flags.Flag.RECENT){
               flag= 1;               
           }
        }
        InternetAddress ia = new InternetAddress(msg.getFrom()[0].toString());
	    mails.add(0,new MailBean(ia.getPersonal(),ia.getAddress(),null,msg.getSubject(),contentText,msg.getSentDate(),flag,msgs.length,unreadCount,msg));
      }
      if (clear)
      {
        msg.setFlag(Flags.Flag.DELETED, true);
      }
    }
    inbox.close(true);
    store.close();
    return mails;
  }
  
  public void deleteMessage(Message message)
  throws Exception
{
  Store store = session.getStore();
  store.connect();
  Folder root = store.getDefaultFolder();
  Folder inbox = root.getFolder("inbox");
  inbox.open(Folder.READ_WRITE);
  Message[] msgs = inbox.getMessages();
  
  for (int i = msgs.length - 1; i >= 0; i--){
	  MimeMessage msg = (MimeMessage)msgs[i];
	  if(msg.getSentDate().equals(message.getSentDate()) && msg.getFrom()[0].toString().equals(message.getFrom()[0].toString())){
		  msg.setFlag(Flags.Flag.DELETED, true);
		  break;
	  }
  }
  
  inbox.close(true);
  store.close();
}

}
