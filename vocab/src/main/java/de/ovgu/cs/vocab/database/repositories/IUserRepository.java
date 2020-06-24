package de.ovgu.cs.vocab.database.repositories;

import de.ovgu.cs.vocab.database.tables.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A JpaRepository which grants access to the DbUser table.
 */
public interface IUserRepository extends JpaRepository<DbUser, Long> {
    List<DbUser> findByUsername(String username);
    List<DbUser> findByApiKey(String apiKey);
}
