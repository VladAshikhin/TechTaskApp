package com.app.repository;

import com.app.objects.CorporateStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateStyleRepository extends JpaRepository<CorporateStyle, String> {
}
