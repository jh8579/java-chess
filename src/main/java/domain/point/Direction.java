package domain.point;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {
	N(-1, 0, DirectionPredicates::isN),
	NE(-1, 1, DirectionPredicates::isNe),
	E(0, 1, DirectionPredicates::isE),
	SE(1, 1, DirectionPredicates::isSe),
	S(1, 0, DirectionPredicates::isS),
	SW(1, -1, DirectionPredicates::isSw),
	W(0, -1, DirectionPredicates::isW),
	NW(-1, -1, DirectionPredicates::isNw),
	KNIGHT(0, 0, DirectionPredicates::isKnight),
	ELSE(0, 0, DirectionPredicates::isElse);

	private final int rowIndex;
	private final int columnIndex;
	private final BiPredicate<Integer, Integer> biPredicate;

	Direction(int rowIndex, int columnIndex, BiPredicate<Integer, Integer> biPredicate) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.biPredicate = biPredicate;
	}

	public static Direction of(Point from, Point to) {
		int rowDifference = getRowDifference(from, to);
		int columnDifference = getColumnDifference(from, to);

		return Arrays.stream(values())
				.filter(direction -> direction.biPredicate.test(rowDifference, columnDifference))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}

	private static int getRowDifference(Point from, Point to) {
		return to.getRowIndex() - from.getRowIndex();
	}

	private static int getColumnDifference(Point from, Point to) {
		return to.getColumnIndex() - from.getColumnIndex();
	}

	public boolean isNotStraight() {
		return !Arrays.asList(E, W, S, N).contains(this);
	}

	public boolean isNotDiagonalUp() {
		return !isDiagonalUp();
	}

	private boolean isDiagonalUp() {
		return this == NW || this == NE;
	}

	public boolean isNotDiagonalDown() {
		return !isDiagonalDown();
	}

	private boolean isDiagonalDown() {
		return this == SW || this == SE;
	}

	public boolean isNotDiagonal() {
		return isNotDiagonalUp() && isNotDiagonalDown();
	}

	public boolean isLinearDirection() {
		return !isNotLinearDirection();
	}

	public boolean isNotLinearDirection() {
		return isNotStraight() && isNotDiagonal();
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}
}