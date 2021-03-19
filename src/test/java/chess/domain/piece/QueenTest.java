package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    void create() {
        assertThatCode(() -> new Queen(Team.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("팀 확인하기")
    @Test
    void isSameTeam() {
        Queen queen = new Queen(Team.BLACK);
        assertThat(queen.isSameTeam(Team.BLACK)).isTrue();
    }

    @DisplayName("차례가 아닐 때 움직일 수 없다.")
    @Test
    void team() {
        Queen queen = new Queen(Team.BLACK);
        assertThatThrownBy(() -> queen.validateCurrentTurn(Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹인지 확인하기")
    @Test
    void isKing() {
        Queen queen = new Queen(Team.BLACK);
        assertThat(queen.isKing()).isFalse();
    }

    @DisplayName("폰인지 확인하기")
    @Test
    void isPawn() {
        Queen queen = new Queen(Team.BLACK);
        assertThat(queen.isPawn()).isFalse();
    }
}