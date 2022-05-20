package me.seondongpyo.videoshop.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMovieRepository extends MovieRepository, JpaRepository<Movie, UUID> {
}
