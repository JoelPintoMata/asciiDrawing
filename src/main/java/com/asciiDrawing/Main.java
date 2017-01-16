package com.asciiDrawing;

import java.util.Scanner;

/**
 * Created by guybrushtreepwood on 19/11/16.
 */
public class Main {

    public static void main(String[] args) {
        AsciiDrawing asciiDrawing = new AsciiDrawing();
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("enter command: ");
            asciiDrawing.draw(in.nextLine().split(" "));
        }
    }
}
