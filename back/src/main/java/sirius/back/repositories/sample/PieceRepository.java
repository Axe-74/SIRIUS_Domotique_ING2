package sirius.back.repositories.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sirius.back.models.sample.Piece;
import sirius.back.models.sample.Sample;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {
//    @Query(value="SELECT * FROM Sample AS s ORDER BY s.date_sample DESC LIMIT 1", nativeQuery = true)
//    Sample findLastSampleByDate();
}