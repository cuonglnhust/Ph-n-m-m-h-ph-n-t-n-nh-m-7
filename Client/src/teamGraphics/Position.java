package teamGraphics;

class Position {
    private int firstPosition;
    private int lastPosition;
    private int firstHorizontalPosition;
    private int firstVerticalPosition;

    public Position(int firstPosition, int lastPosition, int firstHorizontalPosition, int firstVerticalPosition) {
        this.firstPosition = firstPosition;
        this.lastPosition = lastPosition;
        this.firstHorizontalPosition = firstHorizontalPosition;
        this.firstVerticalPosition = firstVerticalPosition;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public int getFirstHorizontalPosition() {
        return firstHorizontalPosition;
    }

    public int getFirstVerticalPosition() {
        return firstVerticalPosition;
    }
}
