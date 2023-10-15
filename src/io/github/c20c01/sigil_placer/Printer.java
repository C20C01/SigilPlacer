package io.github.c20c01.sigil_placer;

public class Printer {
    private static Integer[][] RESULT;

    public static void print(int board_x, int board_y, Integer[][] result) {
        Point[][] BOARD = new Point[board_y + 1][board_x + 1];
        RESULT = result;
        for (int y = 0; y <= board_y; y++) {
            for (int x = 0; x <= board_x; x++) {
                int[] area = getArea(x - 1, y - 1);
                BOARD[y][x] = new Point(area[0], area[1], area[2], area[3]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Solution found:");
        for (Point[] row : BOARD) {
            sb.append("\n");
            for (Point point : row) {
                sb.append(point);
            }
        }
        System.out.println(sb);
    }

    private static int[] getArea(int x, int y) {
        int[] area = new int[4];
        area[0] = getID(x, y);
        area[1] = getID(x + 1, y);
        area[2] = getID(x + 1, y + 1);
        area[3] = getID(x, y + 1);
        return area;
    }

    private static int getID(int x, int y) {
        try {
            return RESULT[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public static class Point {
        private static final String[] CODE = new String[]{"╳", null, null, "┗", null, "┃", "┏", "┣", null, "┛", "━", "┻", "┓", "┫", "┳", "╋"};
        private final byte code;

        public Point(int ul, int ur, int dr, int dl) {
            int temp = 0;
            temp += ul == ur ? 0 : 1;
            temp += ur == dr ? 0 : 2;
            temp += dr == dl ? 0 : 4;
            temp += dl == ul ? 0 : 8;
            code = (byte) temp;
        }

        public String toString() {
            return CODE[code];
        }
    }
}
