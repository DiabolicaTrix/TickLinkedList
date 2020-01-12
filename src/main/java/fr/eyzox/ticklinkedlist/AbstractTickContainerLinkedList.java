package fr.eyzox.ticklinkedlist;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractTickContainerLinkedList<T> {
    protected LinkedList<TickContainer<T>> list = new LinkedList<TickContainer<T>>();

    public T tick() {
        if (this.list.isEmpty())
            return null;
        TickContainer<T> tc = this.list.getFirst();
        tc.tick();
        if (tc.isExpired())
            return this.list.poll().getData();
        return null;
    }

    public void add(int tick, T data) {
        add(new TickContainer<T>(tick, data));
    }

    protected void add(TickContainer<T> tickContainerToAdd) {
        if (this.list.isEmpty()) {
            this.list.add(tickContainerToAdd);
        } else {
            for (ListIterator<TickContainer<T>> it = this.list.listIterator(); it.hasNext(); ) {
                TickContainer<T> current = it.next();
                int compareResult = current.compareTo(tickContainerToAdd);
                if (compareResult < 0) {
                    tickContainerToAdd.decrease(current);
                    continue;
                }
                if (compareResult == 0) {
                    current.setData(merge(tickContainerToAdd.getData(), current.getData()));
                    return;
                }
                it.set(tickContainerToAdd);
                current.decrease(tickContainerToAdd);
                it.add(current);
                return;
            }
            this.list.add(tickContainerToAdd);
        }
    }

    public List<T> clear() {
        List<T> resultList = new LinkedList<T>();
        if(!this.list.isEmpty()) {
            ListIterator<TickContainer<T>> it = this.list.listIterator();
            while(it.hasNext()) {
                TickContainer<T> tc = it.next();
                resultList.add(tc.getData());
                it.remove();
            }
        }
        return resultList;
    }

    protected abstract T merge(T paramT1, T paramT2);
}
