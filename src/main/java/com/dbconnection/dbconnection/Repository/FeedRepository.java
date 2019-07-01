package com.dbconnection.dbconnection.Repository;
import com.dbconnection.dbconnection.Feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Integer> {

}
