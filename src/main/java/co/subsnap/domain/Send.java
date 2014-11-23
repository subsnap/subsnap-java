package co.subsnap.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.subsnap.domain.SendEmail;

@Entity
@Table(name = "sends", uniqueConstraints = { @UniqueConstraint(columnNames = "send_id") }, indexes = { @Index(columnList = "SEND_ID") })
public class Send extends ResourceSupport implements java.io.Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6221630110575114725L;

	@Id
	@GenericGenerator(name = "reservation_seq", strategy = "increment")
	@GeneratedValue(generator = "reservation_seq")
	@Column(name = "send_id")
	private Long sendId;

	@Column(name = "project_id")
	private Long projectId;

	/*
	 * JOIN TABLE VARIABLES
	 */

	@Transient
	@ElementCollection(targetClass = SendEmail.class)
	@OneToMany(mappedBy = "sendId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SendEmail> sendEmails = new ArrayList<SendEmail>();

	@JsonIgnore
	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", insertable = false, updatable = false)
	// @JsonBackReference
	private Project project;

	@Column(name = "send_date")
	private Date sendDate;

	public Send() {
		this.sendDate = new Date();
	}

	// getter and setter methods

	public Long getSendId() {
		return sendId;
	}

	public synchronized void setSendId(Long unique_id) {
		this.sendId = unique_id;
	}

	// Join Column
	public List<SendEmail> getSendMail() {

		return this.sendEmails;
	}

	// Join Column
	public void setSendEmail(List<SendEmail> emails) {

		this.sendEmails = emails;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getSendDate() {
		return sendDate;
	}

	public synchronized void setSendDate(Date date) {
		this.sendDate = date;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(this, Send.class);

		return json;
	}
}
