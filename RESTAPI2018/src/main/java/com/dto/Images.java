package com.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Images {
	@Id@GeneratedValue
    private int id;
	
	private String demo;
	private String imageName;
	public Images() {
		super();
	}
	public Images(int id, String demo, String imageName) {
		super();
		this.id = id;
		this.demo = demo;
		this.imageName = imageName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "Images [id=" + id + ", desc=" + demo + ", imageName=" + imageName + "]";
	}
	
	
	

}
