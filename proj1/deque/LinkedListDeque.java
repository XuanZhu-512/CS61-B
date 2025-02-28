package deque;

/* 使用T是为了实现泛型 */
public class LinkedListDeque<T> {
    /* 为了实现常量的时间复杂度，用变量size来维护队列大小 */
    private int size;

    /* 哨兵的使用避免了队列为空时多余的讨论，且某种意义上将整个队列连接成了一个环 */
    private Node<T> sentinel;

    /* 定义节点，
        由于是双端队列，item负责存放数据，prev和next分别指向前面和后面
     */
    public class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        /* 构造函数，初始化Node */
        public Node(T i, Node<T> p, Node<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* 嵌套类，构建一个空队列并定义了哨兵
        哨兵初始时prev和next都指向自己(保证之后插入元素构成环)
     */
    public LinkedListDeque() {
        sentinel = new Node<> (null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /* 返回队列大小 */
    public int size() {
        return size;
    }

    /* 判断队列是否为空 */
    public boolean isEmpty() {
        return size == 0;
    }

    /* 在队列顶端插入元素
        注意：1. 哨兵默认在顶部元素之前
             2. 插入元素后需要更新哨兵和原先顶部元素指向状态
             3. 原本是空队列的话，插入第一个元素后哨兵的前后指向就已全部更新
     */
    public void addFirst(T item) {
        Node<T> newNode = new Node<> (item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /* 在队列末尾插入元素 */
    public void addLast(T item) {
        Node<T> newNode = new Node<> (item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /* 删除并返回队列首位元素 */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> removeNode = sentinel.next;
        sentinel.next = removeNode.next;
        removeNode.next.prev = sentinel;
        size--;
        return removeNode.item;
    }

    /* 删除并返回队列末尾元素 */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> removeNode = sentinel.prev;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        size--;
        return removeNode.item;
    }

    /* 打印整个链表并换行 */
    public void printDeque() {
        Node<T> currentNode = sentinel.next;
        while (currentNode != sentinel.prev) {
            System.out.print(currentNode.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println(currentNode);
    }

    /* 找到索引为index的元素 */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> currentNode = sentinel.next;
        int i = 0;
        while (i != index) {
            currentNode = currentNode.next;
            i++;
        }
        return currentNode.item;
    }

    /* 递归实现get函数的辅助函数(递归需要引入节点的变量) */
    private T getRecursiveHelper(Node<T> currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    /* 递归实现get函数 */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public boolean equalsTwoItems(T i, T j) {
        return i == j;
    }

    /* 判断一个对象是否等于o链表 (not finished yet) */
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<?> referenceObject = (LinkedListDeque<?>) o;
        if (size != referenceObject.size()) {
            return false;
        }

        Node<T> currentNode1 = sentinel.next;
        Node<?> currentNode2 = (Node<T>) referenceObject.sentinel.next;

        while (currentNode1 != sentinel) {
            if (!equalsTwoItems(currentNode1.item, (T) currentNode2.item)) {
                return false;
            }
            currentNode1 = currentNode1.next;
            currentNode2 = currentNode2.next;
        }
        return true;
    }
}
