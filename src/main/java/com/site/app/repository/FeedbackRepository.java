package com.site.app.repository;

import com.site.app.model.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    Feedback findById(long id);
    void delete(Feedback feedback);
}
