package com.bank.design;

public class Heading {
    public static void disp() throws InterruptedException {
        String cyan = "\u001B[36m";
        String reset = "\u001B[0m";
        String border = "╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗";
        String title = "║                                                                    ✨ WELCOME TO CV 225 BANK ✨                                                                     ║";
        String line = "╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝";

        System.out.println(cyan + border + reset);
        Thread.sleep(200);
        System.out.println(cyan + title + reset);
        Thread.sleep(200);
        System.out.println(cyan + line + reset);
        Thread.sleep(200);
        System.out.println();
        System.out.println("Loading, please wait...\n");
        Thread.sleep(500);

        int total = 40;
        for (int i = 0; i <= total; i++) {
            int percent = (i * 100) / total;
            StringBuilder bar = new StringBuilder("[");
            for (int j = 0; j < total; j++) {
                if (j < i) bar.append("█");
                else bar.append("░");
            }
            bar.append("] ").append(percent).append("%");
            System.out.print("\r" + bar);
            Thread.sleep(100);
        }
        System.out.print("\r[████████████████████████████████████████] 100% ✅ Ready! Loading Complete.\n\n");
    }
}