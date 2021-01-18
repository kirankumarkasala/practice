package com.excelra.glpg.utils;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelra.glpg.service.ProjectService;


public class Mail {
	  private Logger LOGGER = LoggerFactory.getLogger(Mail.class);
    /**
     * get the properties of the outlook and smtp configuration
     * according to the internatilization
     */

     ResourceBundle resourceBundle = ResourceBundle.getBundle("mails");

    String port = resourceBundle.getString("mail.port");
    String mailHost = resourceBundle.getString("mail.host");
    String from = resourceBundle.getString("mail.from");
    String SMTP_AUTH_USER = resourceBundle.getString("mail.from");
    String SMTP_AUTH_PWD = resourceBundle.getString("mail.auth.pwd");
    
     
    public void sendMail(String [] recipients,String [] cc_recipients, String subject,String body)
    {
    	
        boolean debug = false;

        LOGGER.info("mail called------------->");
        try{
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", mailHost);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getDefaultInstance(props, auth);

            session.setDebug(debug);

            // create a message

            Message msg = new MimeMessage(session);

            // set the from and to address
            // InternetAddress addressFrom = new InternetAddress(from);
            // msg.setFrom(addressFrom);
            msg.setContent(body, "text/HTML");
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++)
            {

                addressTo[i] = new InternetAddress(recipients[i]);
            }


            msg.setRecipients(Message.RecipientType.TO, addressTo);

            if(cc_recipients != null && cc_recipients.length>0) {
              //  String[] ccStr =  cc_recipients[0].split(",");

                InternetAddress[] addressCC = new InternetAddress[cc_recipients.length];
                for (int i = 0; i < cc_recipients.length; i++)
                {

                    if(cc_recipients[i]!=null)
                        addressCC[i] = new InternetAddress(cc_recipients[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, addressCC);
            }
	       /*for (int i = 0; i < cc_recipients.length; i++)
	       {

	    	   if(cc_recipients[i]!=null)
	    	   addressCC[i] = new InternetAddress(cc_recipients[i]);
	       }*/

            Transport tr = session.getTransport("smtp");

            //tr.connect();
            tr.connect(mailHost, SMTP_AUTH_USER, SMTP_AUTH_PWD);

            msg.saveChanges();
            msg.setFrom(new InternetAddress(from));// don't forget this
            msg.setSubject(subject);

            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();

            // Setting the Subject and Content Type
            //msg.setSubject(subject);
            //msg.setContent(message, "text/plain");
            //Transport.send(msg);
            LOGGER.info("****mail sent successfully****");
        }
        catch(SendFailedException se)
        {
            se.printStackTrace();
            String className = new Throwable().getStackTrace()[0].getClassName();
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
           // Constants.writeExceptionLog(className, methodName, se);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            String className = new Throwable().getStackTrace()[0].getClassName();
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
           // Constants.writeExceptionLog(className, methodName, ex);
        }
    }
    private class SMTPAuthenticator extends Authenticator
    {

        public javax.mail.PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
    
    public static void main(String argv[]) {
    	System.out.println("hi-----?");
    	final String[] emailList = {"shreekanth.gummadi@excelra.com"};
        final String[] emailListcc = {"harika.neelam@excelra.com"};
        String mailTo = "shreekanth.gummadi@excelra.com,harika.neelam@excelra.com";
        String[] recepients = mailTo.split(",");
        for(String rec : recepients) {
        	System.out.println("recepients---->"+rec);
        }
        
        //Mail mail = new Mail();
    	//mail.sendMail(emailList, emailListcc, "Test Subject", "Test Body");
    }
}