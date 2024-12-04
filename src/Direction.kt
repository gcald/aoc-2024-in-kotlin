enum class Direction(val relativeCoords: Pair<Int, Int>) {
    // Row, Col
    N(Pair(-1, 0)),
    NE(Pair(-1, 1)),
    E(Pair(0, 1)),
    SE(Pair(1, 1)),
    S(Pair(1, 0)),
    SW(Pair(1, -1)),
    W(Pair(0, -1)),
    NW(Pair(-1, -1))
}