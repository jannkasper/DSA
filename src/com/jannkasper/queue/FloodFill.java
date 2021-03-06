package com.jannkasper.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Flood fill (also known as seed fill) is an algorithm that determines the area connected to a given node in a multi-dimensional array
 * Replace color from start node = (3,9) ("GREY") into "BLACK"
 */
public class FloodFill {

    // Below arrays details all 8 possible movements
    private static final int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
    private static final int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

    // check if it is possible to go to pixel (x, y) from
    // current pixel. The function returns false if the pixel
    // has different color or it is not a valid pixel
    public static boolean isSafe(char[][] matrix, int y, int x, char color) {
        if (y >= 0 && y < matrix.length &&
            x >= 0 && x < matrix[0].length &&
            matrix[y][x] == color ) {
            return true;
        }
        return false;
    }

    public static void BFS(char[][] matrix, int startY, int startX, char replacement) {
        char color = matrix[startY][startX];

        // create a queue and enqueue starting pixel
        // SPACE COMPLEXITY O(n^2)
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(startX,startY));

        // TIME COMPLEXITY O(n^2)
        while (!queue.isEmpty()) {
            // replace current pixel color with that of replacement
            Pair pair = queue.poll();
            matrix[pair.y][pair.x] = replacement;

            // TIME COMPLEXITY O(k)
            for (int i = 0; i< row.length; i++) {
                // if adjacent pixel at position (x + row[k], y + col[k]) is
                // a valid pixel and have same color as that of current pixel
                if (isSafe(matrix, pair.y + col[i], pair.x + row[i], color)) {
                    queue.add(new Pair(pair.x + row[i], pair.y + col[i]));
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] matrix =
                {
                        "YYYGGGGGGG".toCharArray(),
                        "YYYYYYGXXX".toCharArray(),
                        "GGGGGGGXXX".toCharArray(),
                        "WWWWWGGGGX".toCharArray(),
                        "WRRRRRGXXX".toCharArray(),
                        "WWWRRGGXXX".toCharArray(),
                        "WBWRRRRRRX".toCharArray(),
                        "WBBBBRRXXX".toCharArray(),
                        "WBBXBBBBXX".toCharArray(),
                        "WBBXXXXXXX".toCharArray()
                };

        // start node
        int x = 3, y = 9;

        // replacement color
        char replacement = 'C';

        // print the colors before replacement
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();

        BFS(matrix, y, x, replacement);

        // print the colors after replacement
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }


}
