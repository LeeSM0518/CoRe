package io.wisoft.core.root.repository;

import io.wisoft.core.root.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByEmail(String email);

  Member findByName(String name);

}
