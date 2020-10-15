package com.app.repo;

import com.app.objects.templatetypes.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepo extends JpaRepository<Presentation, String> {
}
