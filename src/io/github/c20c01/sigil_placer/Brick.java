package io.github.c20c01.sigil_placer;

public enum Brick {
    LL(new int[][][]{
            {{1, 0}, {1, 1}, {1, 2}},
            {{0, 1}, {1, 0}, {2, 0}},
            {{0, 1}, {0, 2}, {1, 2}},
            {{1, 0}, {2, 0}, {2, -1}}
    }),

    LR(new int[][][]{
            {{1, 0}, {1, -1}, {1, -2}},
            {{1, 0}, {2, 0}, {2, 1}},
            {{0, 1}, {0, 2}, {1, 0}},
            {{0, 1}, {1, 1}, {2, 1}}
    }),

    ZL(new int[][][]{
            {{0, 1}, {1, 1}, {1, 2}},
            {{1, 0}, {1, -1}, {2, -1}}
    }),

    ZR(new int[][][]{
            {{0, 1}, {1, 0}, {1, -1}},
            {{1, 0}, {1, 1}, {2, 1}}
    }),

    I(new int[][][]{
            {{1, 0}, {2, 0}, {3, 0}},
            {{0, 1}, {0, 2}, {0, 3}}
    }),

    O(new int[][][]{
            {{1, 0}, {1, 1}, {0, 1}}
    }),

    T(new int[][][]{
            {{1, 0}, {1, 1}, {1, -1}},
            {{1, 0}, {2, 0}, {1, 1}},
            {{1, 0}, {1, -1}, {2, 0}},
            {{0, 1}, {0, 2}, {1, 1}}
    });

    // {y, x}, without {0, 0}
    private final int[][][] SHAPES;

    Brick(int[][][] shapes) {
        this.SHAPES = shapes;
    }

    public int getNumOfShapes() {
        return SHAPES.length;
    }

    public int[][] getShape(int rotation) {
        return SHAPES[rotation];
    }
}