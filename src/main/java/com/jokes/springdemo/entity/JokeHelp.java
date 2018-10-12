package com.jokes.springdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;



public class JokeHelp {

	private int id;

	@NotBlank(message = "sadržaj mora biti popunjen")
	private String content;

	private int likes;

	private int dislikes;

	@NotBlank(message = "kategorija mora biti popunjena")
	private String category;

	public JokeHelp() {

	}

	public JokeHelp(String content, int likes, int dislikes) {
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public JokeHelp(String content) {
		this.content = content;
		this.likes = 0;
		this.dislikes = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDifference() {
		return Math.abs(likes - dislikes);
	}

	@Override
	public String toString() {
		return "Joke [id=" + id + ", content=" + content + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}

}
