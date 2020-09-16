package io.wisoft.core.root.repository;

import io.wisoft.core.root.entity.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

  @Query("select h from Hashtag h where h.name in :names")
  List<Hashtag> findByNames(@Param("names") List<String> names);

  Hashtag findByName(String name);

  Page<Hashtag> findByNameStartsWith(String name, Pageable pageable);

}
