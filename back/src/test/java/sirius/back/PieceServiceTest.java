package sirius.back;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sirius.back.models.Piece;
import sirius.back.repositories.PieceRepository;
import sirius.back.services.PieceService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PieceServiceTest {

    @Mock
    private PieceRepository pieceRepository; // simule bdd

    @InjectMocks
    private PieceService pieceService; // service à tester

    @Test
    public void testFindAllPieces() {
        // GIVEN
        Piece p1 = new Piece();
        Piece p2 = new Piece();
        p1.setNom("Salon");
        p2.setNom("Cuisine");

        when(pieceRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // WHEN
        List<Piece> resultat = pieceService.findAllPiece();

        // THEN
        assertNotNull(resultat, "La liste des pièces ne doit pas etre nulle");
        assertEquals(2, resultat.size(), "On doit recuperer 2 pièces");
        assertEquals("Salon", resultat.get(0).getNom(), "La premiere piece doit etre le Salon");
    }

    @Test
    public void testFindPieceByIdInconnu() {
        // GIVEN : on dit que la piece 99 n'existe pas
        when(pieceRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN
        Piece resultat = pieceService.findPieceById(99L);

        // THEN
        assertNull(resultat, "Le service doit retourner null pour un ID qui n'existe pas");
    }

    @Test
    public void testUpdatePieceAppelRepo() {
        // GIVEN
        Piece pieceModifiee = new Piece();
        pieceModifiee.setId_piece(1L);
        pieceModifiee.setNom("Salon");

        when(pieceRepository.save(any(Piece.class))).thenReturn(pieceModifiee);

        // WHEN
        Piece resultat = pieceService.updatePiece(pieceModifiee);

        // THEN : on verifie que la sauvegarde est bien demandée
        assertNotNull(resultat);
        assertEquals("Salon", resultat.getNom());
        verify(pieceRepository, times(1)).save(pieceModifiee);
    }
}