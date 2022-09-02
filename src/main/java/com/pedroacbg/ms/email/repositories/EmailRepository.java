package com.pedroacbg.ms.email.repositories;

import com.pedroacbg.ms.email.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
