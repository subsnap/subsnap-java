
package co.subsnap.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Entity
@Table(name = "send_emails", uniqueConstraints = { @UniqueConstraint(columnNames = {"send_email_id"}) }, indexes = { @Index(columnList = "SEND_EMAIL_ID")})
public class SendEmail extends ResourceSupport implements java.io.Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6221630110575114725L;

    @Id
    @GenericGenerator(name = "reservation_seq", strategy = "increment")
    @GeneratedValue(generator = "reservation_seq")
    @Column(name = "send_email_id", unique = true, nullable = false)
    private Long sendEmailId;
    
    @Column(name = "send_id")
    private Long sendId;

    @Column(name = "send_email_name")
    private String sendEmailName;

    @Column(name = "send_email_address")
    private String sendEmailAddress;
    
    @Type(type = "true_false")
    private boolean isWatermarked;
    
    @Column(name = "send_email_title")
    private String sendEmailTitle;
    
    @Column(name = "send_email_body")
    private String sendEmailBody;
    
	@JsonIgnore
	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_id", insertable = false, updatable = false)
	private Send send;

    public SendEmail() {

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
    
    public String getSendEmailAddress() {
        return sendEmailAddress;
    }

    public synchronized void setSendEmailAddress(String name) {
        this.sendEmailAddress = name;
    }
    
    
    public Long getSendEmailId() {
		return sendEmailId;
	}

	public void setSendEmailId(Long sendEmailId) {
		this.sendEmailId = sendEmailId;
	}

	public boolean isWatermarked() {
		return isWatermarked;
	}

	public void setWatermarked(boolean isWatermarked) {
		this.isWatermarked = isWatermarked;
	}

	public String getSendEmailTitle() {
		return sendEmailTitle;
	}

	public void setSendEmailTitle(String emailTitle) {
		this.sendEmailTitle = emailTitle;
	}

	public String getSendEmailBody() {
		return sendEmailBody;
	}

	public void setSendEmailBody(String emailBody) {
		this.sendEmailBody = emailBody;
	}

	@Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this, SendEmail.class);

        return json;
    }
}
