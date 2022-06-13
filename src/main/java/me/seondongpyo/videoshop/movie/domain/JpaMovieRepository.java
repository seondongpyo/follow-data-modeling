package me.seondongpyo.videoshop.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaMovieRepository extends MovieRepository, JpaRepository<Movie, UUID> {

    @Override
    @Query("select m from Movie m join fetch m.starringActors where m.id = :id")
    Optional<Movie> findById(@Param("id") UUID id);
}
