package entity.changed;

class LockTime {

    private long time;
    private boolean lock;

    private LockTime(long time) {
        this.time = time;
        lock = true;
    }


}