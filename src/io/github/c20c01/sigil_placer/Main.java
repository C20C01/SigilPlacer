package io.github.c20c01.sigil_placer;

public class Main {
    public static void main(String[] args) {
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
