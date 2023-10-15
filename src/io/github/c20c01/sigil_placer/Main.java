package io.github.c20c01.sigil_placer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the width of the board:");
            int boardX = scanner.nextInt();
            System.out.println("Enter the height of the board:");
            int boardY = scanner.nextInt();
            System.out.println("""
                    Enter the number of I bricks:
                    ▌""");
            int numOfI = scanner.nextInt();
            System.out.println("""
                    Enter the number of O bricks:
                    ▇""");
            int numOfO = scanner.nextInt();
            System.out.println("""
                    Enter the number of LL bricks:
                    ▛▘""");
            int numOfLL = scanner.nextInt();
            System.out.println("""
                    Enter the number of LR bricks:
                    ▝▜""");
            int numOfLR = scanner.nextInt();
            System.out.println("""
                    Enter the number of ZL bricks:
                    ▜▙""");
            int numOfZL = scanner.nextInt();
            System.out.println("""
                    Enter the number of ZR bricks:
                    ▟▛""");
            int numOfZR = scanner.nextInt();
            System.out.println("""
                    Enter the number of T bricks:
                    ▜▘""");
            int numOfT = scanner.nextInt();

            Placer placer = new Placer.Builder(boardX, boardY)
                    .numOfI(numOfI)
                    .numOfO(numOfO)
                    .numOfLL(numOfLL)
                    .numOfLR(numOfLR)
                    .numOfZL(numOfZL)
                    .numOfZR(numOfZR)
                    .numOfT(numOfT)
                    .build();
            placer.run();
        }
    }

    @SuppressWarnings("unused")
    public static void example() {
        Placer placer = new Placer.Builder(5, 8)
                .numOfI(1)
                .numOfO(1)
                .numOfLL(4)
                .numOfLR(1)
                .numOfZL(1)
                .numOfT(2)
                .build();
        placer.run();
    }
}
