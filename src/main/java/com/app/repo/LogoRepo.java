package com.app.repo;

import com.app.objects.templatetypes.Logo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoRepo extends JpaRepository<Logo, String> {
}
