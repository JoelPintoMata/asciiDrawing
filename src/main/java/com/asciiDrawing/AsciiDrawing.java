package com.asciiDrawing;

/**
 * ascii canvas drawing implementation class
 *
 * this class draws inside a given canvas of a given w+2, h+2 size
 */
public class AsciiDrawing {

//    the canvas where to draw
    char[][] canvas;

    /**
     *
     * @param parameters the parameters (coordenates) of the operation
     */
    public void draw(String... parameters) {

        switch (parameters[0].charAt(0)) {
//            new canvas
            case 'C':
                if(parameters.length != 3){
                    System.out.println("Invalid number of parameters");
                } else {
                    drawCanvas(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
                    print();
                }
                break;
//            new line
            case 'L':
                if(canvas == null) {
                    System.out.println("Invalid canvas");
                } else if(parameters.length != 5) {
                    System.out.println("Invalid number of parameters");
                } else {
                    drawLine(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
                    print();
                }
                break;
//            new rectangle
            case 'R':
                if(canvas == null) {
                    System.out.println("Invalid canvas");
                } else if(parameters.length != 5) {
                    System.out.println("Invalid number of parameters");
                } else {
                    drawRectangle(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
                    print();
                }
                break;
//            new area color fill
            case 'B':
                if(canvas == null) {
                    System.out.println("Invalid canvas");
                } else if(parameters.length != 4) {
                    System.out.println("Invalid number of parameters");
                } else {
                    drawAreaFill(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), parameters[3].charAt(0));
                    print();
                }
                break;
//            quit
            case 'Q':  System.exit(0);

            default: System.out.println("Invalid operation");
                break;
        }
    }

    /**
     * Generates a new canvas
     * @param w the canvas weight
     * @param h the canvas height
     */
    private void drawCanvas(int w, int h) {
        int i=0;
        canvas = new char[h+2][w+2];
        for(; i<(w+2); i++){
            canvas[0][i] = '-';
            canvas[(h+2)-1][i] = '-';
        }
        i=1;
        for(; i<(h+2)-1; i++){
            canvas[i][0] = '|';
            for(int j=1; j<w+1; j++)
                canvas[i][j] = ' ';
            canvas[i][(w+2)-1] = '|';
        }
    }

    /**
     * Draws a line inside the canvas
     * @param x1 x axe line start point
     * @param y1 y ax line start point
     * @param x2 x axe line end point
     * @param y2 x axe line end point
     */
    private void drawRectangle(int x1, int y1, int x2, int y2) {
        if(x1 < 1 || x2 < 1 || y1 < 1 || y2 < 1)
            return;
        if(x1 > canvas[0].length-2 || x2 > canvas[0].length-2 || y1 > canvas[0].length-2 || y2 > canvas[0].length-2)
            return;
        drawHorizontalLine(x1, y1, x2, y1);
        drawHorizontalLine(x1, y2, x2, y2);
        drawVerticalLine(x1, y1, x1, y2);
        drawVerticalLine(x2, y1, x2, y2);
    }

    /**
     * Draws a line inside the canvas
     * @param x1 x axe start point
     * @param y1 y axe start point
     * @param x2 x axe end point
     * @param y2 x axe end point
     */
    private void drawLine(int x1, int y1, int x2, int y2) {
        if(x1 < 1 || x2 < 1 || y1 < 1 || y2 < 1)
            return;
        if(x1 > canvas[0].length-2 || x2 > canvas[0].length-2 || y1 > canvas[0].length-2 || y2 > canvas[0].length-2)
            return;
        if(y1 == y2)
            drawHorizontalLine(x1, y1, x2, y2);
        else
            if(x1 == x2)
                drawVerticalLine(x1, y1, x2, y2);
    }

    /**
     * Draws a horizontal line inside the canvas
     * @param x1 x axe start point
     * @param y1 y axe start point
     * @param x2 x axe end point
     * @param y2 x axe end point
     */
    private void drawHorizontalLine(int x1, int y1, int x2, int y2) {
        int aux;
        if(x2 < x1){
            aux = x1;
            x1 = x2;
            x2 = aux;
        }
        for(int i=x1; i <= x2; i++){
            canvas[y1][i] = 'x';
        }
    }

    /**
     * Draws a vertical line inside the canvas
     * @param x1 x axe start point
     * @param y1 y axe start point
     * @param x2 x axe end point
     * @param y2 x axe end point
     */
    private void drawVerticalLine(int x1, int y1, int x2, int y2) {
        int aux;
        if(y2 < y1){
            aux = y1;
            y1 = y2;
            y2 = aux;
        }
        for(int i=y1; i <= y2; i++){
            canvas[i][x1] = 'x';
        }
    }

    /**
     * Fills a area between two points with a given color
     * @param x axe start point
     * @param y axe start point
     * @param color
     */
    private void drawAreaFill(int x, int y, char color) {
//        we cannot start paint from a already invalid area
        if(canvas[y][x] == 'x')
            return;
        areaFill(x, y, color);
    }

    /**
     * Fills a area between two points with a given color
     * @param x axe start point
     * @param y axe start point
     * @param color the color with which to fill the area
     */
    private void areaFill(int x, int y, char color) {
//        we cannot paint over a line
        if(canvas[y][x] == color || canvas[y][x] == 'x')
            return;
//        we cannot paint outside the canvas
        if(x == 0 || y == 0 || x == canvas[0].length-1 || y == canvas.length-1)
            return;

//        set the color in the canvas
        canvas[y][x] = color;

        areaFill(x-1, y-1, color);
        areaFill(x-1, y, color);
        areaFill(x-1, y+1, color);

        areaFill(x, y-1, color);
        areaFill(x, y+1, color);

        areaFill(x+1, y-1, color);
        areaFill(x+1, y, color);
        areaFill(x+1, y+1, color);
        return;
    }

    /**
     * Prints this canvas
     */
    private void print(){
        for(int i=0; i<canvas.length; i++){
            System.out.println(canvas[i]);
        }
        System.out.println();
    }

    /**
     * Canvas getter
     * @return the canvas
     */
    public char[][] getCanvas() {
        return canvas;
    }
}
