package com.codesquad.commit.repository;

import com.codesquad.commit.domain.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository <Commit, String>{
}
