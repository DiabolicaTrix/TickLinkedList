package fr.eyzox.ticklinkedlist;

public class Tick implements Comparable<Tick> {
    private int tick;

    public Tick() {}

    public Tick(int tick) {
        this.tick = tick;
    }

    public int compareTo(Tick o) {
        return this.tick - o.tick;
    }

    public int compareTo(int tick) {
        return this.tick - tick;
    }

    public void tick() {
        this.tick--;
    }

    public boolean isExpired() {
        return (this.tick <= 0);
    }

    public void decrease(Tick o) {
        if (o != null)
            this.tick -= o.tick;
    }

    public int getTick() {
        return this.tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }
}
