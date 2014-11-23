/**
 * Copyright © 2014 salesforce.com, inc. All rights reserved.
 */
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.subsnap.domain.Send;


@Entity
@Table(name = "projects", uniqueConstraints = { @UniqueConstraint(columnNames = "project_id") }, indexes = { @Index(columnList = "PROJECT_ID") })
public class Project extends ResourceSupport implements java.io.Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6221630110575114725L;

    @Id
    @GenericGenerator(name = "reservation_seq", strategy = "increment")
    @GeneratedValue(generator = "reservation_seq")
    @Column(name = "project_id", unique = true, nullable = false)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_date")
    private Date projectDate;

    private String star;
    
    private String director;

    private String writer;

    
    /*
     * JOIN TABLE VARIABLES
     */

    /*
     * TODO 1. Convert fetch to LAZY. Currently getting LazyInitializationException : No Session.
     */
    
    @ElementCollection(targetClass = Send.class)
    @OneToMany(mappedBy = "projectId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private Set<Send> Sends;

    protected Project() {
    	this.projectDate = new Date();
    }

    // getter and setter methods

    public Long getProjectId() {
        return projectId;
    }

    public synchronized void setUniqueId(Long unique_id) {
        this.projectId = unique_id;
    }

    public String getProjectName() {
        return projectName;
    }

    public synchronized void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    @JsonSerialize(using = DateSerializer.class)
    public Date getProjectDate() {
        return projectDate;
    }

    public synchronized void setProjectDate(Date date) {
        this.projectDate = date;
    }


    public Set<Send> getSends() {
		return Sends;
	}

	public void setSends(Set<Send> sends) {
		Sends = sends;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this, Send.class);

        return json;
    }
}
