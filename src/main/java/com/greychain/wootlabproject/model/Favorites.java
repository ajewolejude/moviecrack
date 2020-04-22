package com.greychain.wootlabproject.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@Table(name="favorites")
@ApiModel(description = "All details about the Favorites. ")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="movie_id")
    private Long movie_id;

    @Column(name="user_id")
    private Long user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Favorites{" +
				"id=" + id +
				", movie_id=" + movie_id +
				", user_id=" + user_id +
				'}';
	}

	public Favorites(Long id, Long movie_id, Long user_id) {
		this.id = id;
		this.movie_id = movie_id;
		this.user_id = user_id;
	}

	public Favorites() {
	}
}