package team1.videoplay.user.servlet;

import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


class Mail {
    public static void main(String[] args) {
        try {

            System.out.println("发简单邮件");
            Properties props = new Properties();//构造属性
            props.put("mail.transport.protocol", "smtp");//设置发送邮件协议为smtp,默认可省
            props.put("mail.smtp.port", "25");//设置发送邮件端口为25,默认可省
            props.put("mail.smtp.host", "172.18.53.53");//设置邮件服务器
            Session session = Session.getInstance(props);
            MimeMessage mimeMessage = new MimeMessage(session);  //封闭邮件
            mimeMessage.setFrom(new InternetAddress("qq@t60.com"));   //设置发件人
                    mimeMessage.setRecipients(Message.RecipientType.TO,
                                      InternetAddress.parse("ww@t60.com"));//设置收件人
            mimeMessage.setSentDate(new Date());//设置发送日期
            mimeMessage.setSubject("aa");//设置邮件标题
            mimeMessage.setContent("bb", "text/html");////设置邮件内容
            Transport.send(mimeMessage);//发送邮件

            System.out.println("收简单邮件");
            Store store = session.getStore("pop3");    //指定收邮件协议为pop3
            store.connect("172.18.53.53", "ww@t60.com", "ww");  //登录
             Folder folder = store.getFolder("INBOX");   //获取收件箱
            folder.open(Folder.READ_WRITE);  //以读写方式打开
            Message msgs[] = folder.getMessages();  //获取所有邮件
            for (int i = 0; i < msgs.length; i++) {
                System.out.println("发件人：" + msgs[i].getFrom()[0]);
                System.out.println("标题：" + msgs[i].getSubject());
                System.out.println("内容：" + msgs[i].getContent());
              //  msgs[i].setFlag(Flags.Flag.DELETED, true);    //删除邮件
            }
            folder.close(true);   //关闭收件箱
//
//            System.out.println("发带附件邮件");
//            Properties props1 = new Properties();
//            props1.put("mail.smtp.host", "127.0.0.1");
//            Session session1 = Session.getInstance(props1);
//            MimeMessage mimeMessage1 = new MimeMessage(session1);
//
//            mimeMessage1.setFrom(new InternetAddress("qq@t60.com"));
//            mimeMessage1.setRecipients(Message.RecipientType.TO,
//                                       InternetAddress.parse("ww@t60.com"));
//            mimeMessage1.setSentDate(new Date());
//            mimeMessage1.setSubject("aa");
//
//            MimeMultipart mimeMultipart = new MimeMultipart();
//            MimeBodyPart mimeBodyPart = new MimeBodyPart();
//
//            mimeBodyPart.setContent("bb", "text/html");
//            mimeMultipart.addBodyPart(mimeBodyPart);
//
//            mimeBodyPart = new MimeBodyPart();
//            FileDataSource fileDataSource = new FileDataSource("c:/aa.txt");
//            DataHandler dataHandler = new DataHandler(fileDataSource);
//            mimeBodyPart.setDataHandler(dataHandler);
//            mimeBodyPart.setFileName("aa.txt");
//            mimeMultipart.addBodyPart(mimeBodyPart);
//
//            mimeMessage1.setContent(mimeMultipart);
//
//            Transport.send(mimeMessage1);
            
            
////            System.out.println("收带附件邮件");
//            Store store1 = session1.getStore("pop3");
//            store1.connect("127.0.0.1", "ww@t60.com", "ww");
//            Folder folder1 = store1.getFolder("INBOX");
//            folder1.open(Folder.READ_WRITE);
//            Message[] msgs1 = folder1.getMessages();
//            for (int i = 0; i < msgs1.length; i++) {
//
//                System.out.println("第" + (i + 1) + "封邮件信息如下：");
//                System.out.println("邮件标题：" + msgs1[i].getSubject());
//                mimeMultipart = (MimeMultipart) msgs1[i].
//                                getContent();
//                mimeBodyPart = (MimeBodyPart) mimeMultipart.
//                               getBodyPart(0);
//                System.out.println("邮件正文：" + mimeBodyPart.getContent());
//
//                mimeBodyPart = (MimeBodyPart)
//                               mimeMultipart.getBodyPart(1);
//                System.out.println("附件名：" + mimeBodyPart.getFileName());
//                InputStream is = mimeBodyPart.getInputStream();
//                byte b[] = new byte[is.available()];
//                is.read(b);
//                System.out.println("附件内容：\n" + new String(b));
//                msgs1[i].setFlag(Flags.Flag.DELETED, true);
//
//            }
//            folder1.close(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

