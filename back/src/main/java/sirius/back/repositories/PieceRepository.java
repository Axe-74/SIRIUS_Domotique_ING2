package sirius.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.Piece;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {}
