package io.wisoft.core.root.repository;

import io.wisoft.core.root.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

  @Query("select f from Feed f join f.relatedTags r where r.name in :hashtags order by f.createdDateTime desc")
  Page<Feed> findByHashtags(@Param("hashtags") List<String> hashtags, Pageable pageable);

}
