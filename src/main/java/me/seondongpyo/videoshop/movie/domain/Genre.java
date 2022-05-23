package me.seondongpyo.videoshop.movie.domain;

import lombok.Getter;

@Getter
public enum Genre {
	COMEDY("코미디"),
	THRILLER("스릴러"),
	DRAMA("드라마"),
	ACTION("액션"),
	WAR("전쟁"),
	DOCUMENTARY("다큐멘터리"),
	;

	private final String description;

	Genre(String description) {
		this.description = description;
	}
}
