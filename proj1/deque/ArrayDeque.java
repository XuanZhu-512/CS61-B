package deque;

import java.util.Iterator;

public class ArrayDeque<T> {
    private T[] items;
    private int size;

    /* 双端队列前后指针，frontNext指向首位元素前一个位置，backNext指向末尾元素后一个位置 */
    private int frontNext;
    private int backNext;

    /* 初始化一个空的双端队列, 大小为8
       注意：由于双端队列的两端都能执行插入与删除操作，需尽量将元素放置在 Array 的中间
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        frontNext = 3;
        backNext = 4;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /* 缩小 Array 的大小，避免内存的过度占用 (Not finished yet!!!)
    private void shrinkSize() {

    }
     */

    /* 改变当前双端队列的大小至 newSize， 且保证所有元素均在数列中间 */
    private void resize(int newSize) {
        T[]  newArray = (T[]) new Object[newSize];
        int startPos = Math.abs(newSize - size) / 2;
        System.arraycopy(items, frontNext + 1, newArray, startPos, size);
        items = newArray;

        frontNext = startPos - 1;
        backNext = startPos + size;
    }

    public void addFirst(T item) {
        items[frontNext] = item;
        size += 1;
        frontNext -= 1;
        if (frontNext == -1) {
            resize(size * 2);
        }
    }

    public void addLast(T item) {
        items[backNext] = item;
        size += 1;
        backNext += 1;
        if (backNext == items.length) {
            resize(size * 2);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        frontNext += 1;
        T item = items[frontNext];
        items[frontNext] = null;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        backNext -= 1;
        T item = items[backNext];
        items[backNext] = null;
        size -= 1;
        return item;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.print("null");
            return;
        }
        for (int i = frontNext + 1; i < backNext; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T get(int index) {
        if (index <= frontNext || index >= backNext) {
            return null;
        }
        else {
            index = frontNext + index + 1;
            return items[index];
        }
    }

    /*
    public boolean equals(Object o) {

    }
     */

    /*
    public Iterator<T> iterator() {

    }
     */
}
