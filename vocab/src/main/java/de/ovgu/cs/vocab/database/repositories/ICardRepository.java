package de.ovgu.cs.vocab.database.repositories;

import de.ovgu.cs.vocab.database.tables.DbCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<DbCard, Long> {
}
