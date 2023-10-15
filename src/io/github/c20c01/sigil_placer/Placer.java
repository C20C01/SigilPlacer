package io.github.c20c01.sigil_placer;

public class Placer {
    private final int BOARD_X, BOARD_Y;
    private final Brick[] BRICKS;
    private final int[] NUM_OF_BRICKS;
    private final Integer[][] RESULT;
    private final Boolean[][] BOARD;
    private boolean unfinished = true;
    private int index = 1;

    private Placer(int boardX, int boardY, Brick[] bricks, int[] numOfBricks) {
        BOARD_X = boardX;
        BOARD_Y = boardY;
        BRICKS = bricks;
        NUM_OF_BRICKS = numOfBricks;
        RESULT = new Integer[BOARD_Y][BOARD_X];
        BOARD = new Boolean[BOARD_Y][BOARD_X];
        for (int y = 0; y < BOARD_Y; y++) {
            for (int x = 0; x < BOARD_X; x++) {
                BOARD[y][x] = false;
            }
        }
    }

    public void run() {
        run(0, 0);
        if (unfinished) {
            System.out.println("No solution found.");
        } else {
            Printer.print(BOARD_X, BOARD_Y, RESULT);
        }
    }

    private void run(int x, int y) {
        if (y == BOARD_Y) {
            unfinished = false;
            return;
        }
        if (x == BOARD_X) {
            run(0, y + 1);
            return;
        }
        if (BOARD[y][x]) {
            run(x + 1, y);
            return;
        }

        for (Brick brick : BRICKS) {
            if (NUM_OF_BRICKS[brick.ordinal()] == 0) {
                continue;
            }
            for (int rotation = 0; rotation < brick.getNumOfShapes(); rotation++) {
                //System.out.println("Try " + brick + " at (" + x + ", " + y + ")" + " with rotation " + rotation);
                if (canPlace(brick, rotation, x, y)) {
                    place(brick, rotation, x, y);
                    run(x + 1, y);
                    if (unfinished) {
                        remove(brick, rotation, x, y);
                    } else {
                        set(RESULT, brick, rotation, x, y, index++);
                        return;
                    }
                }
            }
        }
    }

    private boolean canPlace(Brick brick, int rotation, int x, int y) {
        for (int[] pos : brick.getShape(rotation)) {
            try {
                if (BOARD[y + pos[0]][x + pos[1]]) {
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    private void place(Brick brick, int rotation, int x, int y) {
        //System.out.println("Set " + brick + " at (" + x + ", " + y + ")" + " with rotation " + rotation);
        set(BOARD, brick, rotation, x, y, true);
        NUM_OF_BRICKS[brick.ordinal()]--;
    }

    private void remove(Brick brick, int rotation, int x, int y) {
        //System.out.println("Remove " + brick + " at (" + x + ", " + y + ")" + " with rotation " + rotation);
        set(BOARD, brick, rotation, x, y, false);
        NUM_OF_BRICKS[brick.ordinal()]++;
    }

    private static <T> void set(T[][] board, Brick brick, int rotation, int x, int y, T value) {
        board[y][x] = value;
        for (int[] pos : brick.getShape(rotation)) {
            board[y + pos[0]][x + pos[1]] = value;
        }
    }

    @SuppressWarnings("unused")
    public static class Builder {
        private final int boardX, boardY;
        private int kindsOfBricks = 0;
        private int numOfLL = 0;
        private int numOfLR = 0;
        private int numOfZL = 0;
        private int numOfZR = 0;
        private int numOfI = 0;
        private int numOfO = 0;
        private int numOfT = 0;

        public Builder(int boardX, int boardY) {
            this.boardX = boardX;
            this.boardY = boardY;
        }

        public Builder numOfLL(int numOfLL) {
            if (numOfLL > 0) {
                kindsOfBricks++;
                this.numOfLL = numOfLL;
            }
            return this;
        }

        public Builder numOfLR(int numOfLR) {
            if (numOfLR > 0) {
                kindsOfBricks++;
                this.numOfLR = numOfLR;
            }
            return this;
        }

        public Builder numOfZL(int numOfZL) {
            if (numOfZL > 0) {
                kindsOfBricks++;
                this.numOfZL = numOfZL;
            }
            return this;
        }

        public Builder numOfZR(int numOfZR) {
            if (numOfZR > 0) {
                kindsOfBricks++;
                this.numOfZR = numOfZR;
            }
            return this;
        }

        public Builder numOfI(int numOfI) {
            if (numOfI > 0) {
                kindsOfBricks++;
                this.numOfI = numOfI;
            }
            return this;
        }

        public Builder numOfO(int numOfO) {
            if (numOfO > 0) {
                kindsOfBricks++;
                this.numOfO = numOfO;
            }
            return this;
        }

        public Builder numOfT(int numOfT) {
            if (numOfT > 0) {
                kindsOfBricks++;
                this.numOfT = numOfT;
            }
            return this;
        }

        public Placer build() {
            if (numOfLL + numOfLR + numOfZL + numOfZR + numOfI + numOfO + numOfT != boardX * boardY / 4) {
                System.err.println("Wrong number of bricks!");
                System.exit(1);
            }

            Brick[] bricks = new Brick[kindsOfBricks];
            int[] numOfBricks = new int[Brick.values().length];

            if (numOfT > 0) {
                bricks[--kindsOfBricks] = Brick.T;
                numOfBricks[Brick.T.ordinal()] = numOfT;
            }
            if (numOfO > 0) {
                bricks[--kindsOfBricks] = Brick.O;
                numOfBricks[Brick.O.ordinal()] = numOfO;
            }
            if (numOfI > 0) {
                bricks[--kindsOfBricks] = Brick.I;
                numOfBricks[Brick.I.ordinal()] = numOfI;
            }
            if (numOfZR > 0) {
                bricks[--kindsOfBricks] = Brick.ZR;
                numOfBricks[Brick.ZR.ordinal()] = numOfZR;
            }
            if (numOfZL > 0) {
                bricks[--kindsOfBricks] = Brick.ZL;
                numOfBricks[Brick.ZL.ordinal()] = numOfZL;
            }
            if (numOfLR > 0) {
                bricks[--kindsOfBricks] = Brick.LR;
                numOfBricks[Brick.LR.ordinal()] = numOfLR;
            }
            if (numOfLL > 0) {
                bricks[--kindsOfBricks] = Brick.LL;
                numOfBricks[Brick.LL.ordinal()] = numOfLL;
            }

            return new Placer(boardX, boardY, bricks, numOfBricks);
        }
    }
}
