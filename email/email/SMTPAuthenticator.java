package email;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
 
public class SMTPAuthenticator extends Authenticator {
    public SMTPAuthenticator() {
        super();
    }
 
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "dgh4486";
        String password = "ehdrb1992";
        return new PasswordAuthentication(username, password);
    }
}

