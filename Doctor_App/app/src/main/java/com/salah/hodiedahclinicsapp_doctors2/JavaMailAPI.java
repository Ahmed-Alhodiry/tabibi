package com.salah.hodiedahclinicsapp_doctors2;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void>  {


    //Variables
    private Context mContext;
    private Session mSession;

    androidx.cardview.widget.CardView card;
    LottieAnimationView lottie;
    Dialog loadingDialog;



    private String mEmail;
    private String mSubject;
    private String mMessage;


    //Constructor
    public JavaMailAPI(Context mContext, String mEmail, String mSubject, String mMessage) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress dialog while sending email


        loadingDialog = new Dialog(mContext);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismiss progress dialog when message successfully send

        lottie = loadingDialog.findViewById(R.id.lottie_img2);
        card = loadingDialog.findViewById(R.id.card_loading_dialog);
        card.setVisibility(View.GONE);
        lottie.setVisibility(View.VISIBLE);


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    loadingDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        //Show success toast
        Toast.makeText(mContext,"Message Sent",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        mSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(mSession);

            //Setting sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
            //Adding subject
            mm.setSubject(mSubject);
            //Adding message
            mm.setText(mMessage);
            //Sending email
            Transport.send(mm);

//            BodyPart messageBodyPart = new MimeBodyPart();
//
//            messageBodyPart.setText(message);
//
//            Multipart multipart = new MimeMultipart();
//
//            multipart.addBodyPart(messageBodyPart);
//
//            messageBodyPart = new MimeBodyPart();
//
//            DataSource source = new FileDataSource(filePath);
//
//            messageBodyPart.setDataHandler(new DataHandler(source));
//
//            messageBodyPart.setFileName(filePath);
//
//            multipart.addBodyPart(messageBodyPart);

//            mm.setContent(multipart);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
