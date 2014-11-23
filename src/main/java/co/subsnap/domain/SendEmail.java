
package co.subsnap.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.ResourceSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Entity
@Table(name = "send_emails", uniqueConstraints = { @UniqueConstraint(columnNames = {"send_id", "send_email_address"}) }, indexes = { @Index(columnList = "SEND_ID"), @Index(columnList = "SEND_EMAIL_ADDRESS") })
public class SendEmail extends ResourceSupport implements java.io.Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6221630110575114725L;

    @Id
    @GenericGenerator(name = "reservation_seq", strategy = "increment")
    @GeneratedValue(generator = "reservation_seq")    @Column(name = "send_email_id", unique = true, nullable = false)
    private Long sendEmailId;
    
    @Column(name = "send_id", unique = true, nullable = false)
    private Long sendId;

    @Column(name = "send_email_name")
    private String sendEmailName;

    @Column(name = "send_email_address")
    private String sendEmailAddress;

    protected SendEmail() {

    }

    // getter and setter methods

    public Long getSendId() {
        return sendId;
    }

    public synchronized void setSendId(Long id) {
        this.sendId = id;
    }

    public String getSendEmailName() {
        return sendEmailName;
    }

    public synchronized void setSendEmailName(String name) {
        this.sendEmailName = name;
    }
    
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this, SendEmail.class);

        return json;
    }
}
