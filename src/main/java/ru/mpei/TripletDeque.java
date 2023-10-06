package ru.mpei;

import java.util.*;

public class TripletDeque<T> implements Deque<T>, Containerable {
    private int queueSizeMax = 1000;
    private int queueSize = 0;
    private int size = 5;
    Container <T> last = null;
    Container <T> first = null;
    public TripletDeque(int queueSizeMax) {
        this.queueSizeMax = queueSizeMax;
    }

    public TripletDeque(int queueSizeMax, int size) {
        this.queueSizeMax = queueSizeMax;
        this.size = size;
    }
    public TripletDeque() {
    }

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        Container<T> firstCont = first;
        int index = 0;
        while (index < cIndex){
            firstCont = firstCont.next;
            if (firstCont == null){
               // throw new IndexOutOfBoundsException("Вышли за пределы коллекции")
                return null;
            }
            index++;
        }
        return firstCont.elements;
    }

    class Container<M> {
        Container<M> next = null;
        Container<M> prev = null;

        private Object[] elements = new Object[size];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Container<?> container = (Container<?>) o;
            return Objects.equals(next, container.next) && Objects.equals(prev, container.prev) && Arrays.equals(elements, container.elements);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(next, prev);
            result = 31 * result + Arrays.hashCode(elements);
            return result;
        }
    }


    @Override
    public void addFirst(T e) {
        if (e == null){
            throw new NullPointerException("Вы хотите добавить значение null");
        }

        if (this.first == null){
            this.first = new Container<T>();
            this.last = this.first;
        }
        if (queueSize + 1 > queueSizeMax) {
            throw new IllegalStateException("Выход за пределы максимально допустимой очереди");
        }
        if (first == last && first.elements[0] != null){

                this.first.prev = new Container<T>();
                this.first.prev.elements[4] = e;
                queueSize += 1;
                this.first.prev.next = this.first;
                this.first = this.first.prev;

        }else{
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.first.elements[i] == null) {

                    this.first.elements[i] = e;
                    i = 0;
                    queueSize += 1;

            } else if (i == 0) {

                    this.first.prev = new Container<T>();
                    this.first.prev.elements[4] = e;
                    queueSize += 1;
                    this.first.prev.next = this.first;
                    this.first = this.first.prev;

            }
        }
        }
    }

    @Override
    public void addLast(T e) {
        if (e == null){
            throw new NullPointerException("Вы хотите добавить значение null");
        }

        if (this.last == null){
            this.last = new Container<T>();
            this.first = this.last;
        }
        if (queueSize + 1 > queueSizeMax){
            throw new IllegalStateException("Выход за пределы максимально допустимой очереди");
        }
        if (first == last && last.elements[4] != null){

                this.last.next = new Container<T>();
                this.last.next.elements[0] = e;
                queueSize += 1;
                this.last.next.prev = this.last;
                this.last = this.last.next;

        }else{
        for (int i = 0; i < this.size; i++) {
            if (this.last.elements[i] == null) {

                    this.last.elements[i] = e;
                    queueSize += 1;
                    i = this.size;

            } else if (i == this.size - 1) {

                    this.last.next = new Container<T>();
                    this.last.next.elements[0] = e;
                    queueSize += 1;
                    this.last.next.prev = this.last;
                    this.last = this.last.next;

            }
        }
        }
    }

    @Override
    public boolean offerFirst(T e) {
        if (e == null){
            throw new NullPointerException("Вы хотите добавить значение null");
        }

        if (this.first == null){
            this.first = new Container<T>();
            this.last = this.first;
        }
        if (queueSize + 1 > queueSizeMax) {
            return false;
        }
        if (first == last && first.elements[0] != null){

            this.first.prev = new Container<T>();
            this.first.prev.elements[4] = e;
            queueSize += 1;
            this.first.prev.next = this.first;
            this.first = this.first.prev;

        }else{
            for (int i = this.size - 1; i >= 0; i--) {
                if (this.first.elements[i] == null) {

                    this.first.elements[i] = e;
                    i = 0;
                    queueSize += 1;

                } else if (i == 0) {

                    this.first.prev = new Container<T>();
                    this.first.prev.elements[4] = e;
                    queueSize += 1;
                    this.first.prev.next = this.first;
                    this.first = this.first.prev;

                }
            }
        }
        return true;
    }

    @Override
    public boolean offerLast(T e) {

        if (e == null){
            throw new NullPointerException("Вы хотите добавить значение null");
        }

        if (this.last == null){
            this.last = new Container<T>();
            this.first = this.last;
        }
        if (queueSize + 1 > queueSizeMax){
            return false;
        }
        if (first == last && last.elements[4] != null){

            this.last.next = new Container<T>();
            this.last.next.elements[0] = e;
            queueSize += 1;
            this.last.next.prev = this.last;
            this.last = this.last.next;

        }else{
            for (int i = 0; i < this.size; i++) {
                if (this.last.elements[i] == null) {

                    this.last.elements[i] = e;
                    queueSize += 1;
                    i = this.size;

                } else if (i == this.size - 1) {

                    this.last.next = new Container<T>();
                    this.last.next.elements[0] = e;
                    queueSize += 1;
                    this.last.next.prev = this.last;
                    this.last = this.last.next;

                }
            }
        }
        return true;
    }

    @Override
    public T removeFirst() {
        T removed = null;
        if (this.first == null){
            throw new NoSuchElementException("Контейнер пуст!");
        }else{
            for (int i = 0; i < this.first.elements.length; i++){
                if (this.first.elements[i] != null){
                    removed = (T) this.first.elements[i];
                    this.first.elements[i] = null;
                    queueSize--;
                    boolean flag = true;
                    if (this.first.elements[4] != null || queueSize == 0 || first == last){
                        flag = false;
                    }
                    if (flag){
                        this.first.next.prev = null;
                        Container<T> imag = this.first.next;
                        this.first.next = null;
                        this.first = imag;
                    }
                    i = this.first.elements.length;
                }
            }
        }
    return removed;
    }

    @Override
    public T removeLast() {
        T removed = null;
        if (this.last == null){
            throw new NoSuchElementException("Контейнер пуст!");
        }else{
            for (int i = this.last.elements.length - 1; i >= 0; i--){
                if (this.last.elements[i] != null){
                    removed = (T) this.last.elements[i];
                    this.last.elements[i] = null;
                    queueSize--;
                    boolean flag = true;
                    if (this.last.elements[0] != null || queueSize == 0 || first == last){
                        flag = false;
                    }
                    if (flag){
                        this.last.prev.next = null;
                        Container<T> imag = this.last.prev;
                        this.last.prev = null;
                        this.last = imag;
                    }
                    i = -1;
                }
            }
        }
        return removed;
    }

    @Override
    public T pollFirst() {
        T removed = null;
        if (this.first == null){
            return null;
        }else{
            for (int i = 0; i < this.first.elements.length; i++){
                if (this.first.elements[i] != null){
                    removed = (T) this.first.elements[i];
                    this.first.elements[i] = null;
                    queueSize--;
                    boolean flag = true;
                    if (this.first.elements[4] != null || queueSize == 0 || first == last){
                        flag = false;
                    }
                    if (flag){
                        this.first.next.prev = null;
                        Container<T> imag = this.first.next;
                        this.first.next = null;
                        this.first = imag;
                    }
                    i = this.first.elements.length;
                }
            }
        }
        return removed;
    }

    @Override
    public T pollLast() {
        T removed = null;
        if (this.last == null){
            return null;
        }else{
            for (int i = this.last.elements.length - 1; i >= 0; i--){
                if (this.last.elements[i] != null){
                    removed = (T) this.last.elements[i];
                    this.last.elements[i] = null;
                    queueSize--;
                    boolean flag = true;
                    if (this.last.elements[0] != null || queueSize == 0 || first == last){
                        flag = false;
                    }
                    if (flag){
                        this.last.prev.next = null;
                        Container<T> imag = this.last.prev;
                        this.last.prev = null;
                        this.last = imag;
                    }
                    i = -1;
                }
            }
        }
        return removed;
    }

    @Override
    public T getFirst() {
        T found = null;
        if (this.first == null){
            throw new NoSuchElementException("Контейнер пуст!");
        }else{
            for (int i = 0; i < this.first.elements.length; i++){
                if (this.first.elements[i] != null){
                    found = (T) this.first.elements[i];
                    i = this.first.elements.length;
                }
            }
        }
        return found;
    }

    @Override
    public T getLast() {
        T found = null;
        if (this.last == null){
            throw new NoSuchElementException("Контейнер пуст!");
        }else{
            for (int i = this.last.elements.length - 1; i >= 0; i--){
                if (this.last.elements[i] != null){
                    found = (T) this.last.elements[i];
                    i = -1;
                }
            }
        }
        return found;
    }

    @Override
    public T peekFirst() {
        T found = null;
        if (this.first == null){
            return null;
        }else{
            for (int i = 0; i < this.first.elements.length; i++){
                if (this.first.elements[i] != null){
                    found = (T) this.first.elements[i];
                    i = this.first.elements.length;
                }
            }
        }
        return found;
    }

    @Override
    public T peekLast() {
        T found = null;
        if (this.last == null){
            return null;
        }else{
            for (int i = this.last.elements.length - 1; i >= 0; i--){
                if (this.last.elements[i] != null){
                    found = (T) this.last.elements[i];
                    i = -1;
                }
            }
        }
        return found;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (o == null){
            throw new NullPointerException("Вы хотите удалить значение null");
        }
        Container<T> firstDuplicate = this.first;
        while(firstDuplicate != null){
            for (int i = 0; i < firstDuplicate.elements.length; i++){
                if (Objects.equals(o, firstDuplicate.elements[i])){
                    firstDuplicate.elements[i] = null;
                    Iterator iterator = this.iterator();
                    int j = 0;
                    while (j < this.first.elements.length){
                        if (this.first.elements[j] != null){
                            break;
                        }
                        j++;
                    }
                    Container<T> firstD = this.first;
                    while(iterator.hasNext()){
                        Object iter = iterator.next();
                        if (j == 5){
                            firstD = firstD.next;
                            j = 0;
                        }
                        if(iter != null){
                            firstD.elements[j] = iter;
                            j++;
                        }
                    }
                    if (j == 5){
                        firstD = firstD.next;
                        j = 0;
                    }
                    firstD.elements[j] = null;
                    queueSize--;
                    boolean flag = true;
                    if (this.last.elements[0] != null){
                        flag = false;
                    }
                    if (flag){
                        this.last.prev.next = null;
                        Container<T> imag = this.last.prev;
                        this.last.prev = null;
                        this.last = imag;
                    }
                    return true;
                }
            }
            firstDuplicate = firstDuplicate.next;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null){
            throw new NullPointerException("Вы хотите удалить значение null");
        }
        Container<T> lastDuplicate = this.last;
        while(lastDuplicate != null){
            for (int i = lastDuplicate.elements.length - 1; i >= 0; i--){
                if (Objects.equals(o, lastDuplicate.elements[i])){
                    lastDuplicate.elements[i] = null;
                    Iterator iterator = this.iterator();
                    int j = 0;
                    while (j < this.first.elements.length){
                        if (this.first.elements[j] != null){
                            break;
                        }
                        j++;
                    }
                    Container<T> firstD = this.first;
                    while(iterator.hasNext()){
                        Object iter = iterator.next();
                        if (j == 5){
                            firstD = firstD.next;
                            j = 0;
                        }
                        if(iter != null){
                            firstD.elements[j] = iter;
                            j++;
                        }
                    }
                    if (j == 5){
                        firstD = firstD.next;
                        j = 0;
                    }
                    firstD.elements[j] = null;
                    queueSize--;
                    boolean flag = true;
                    if (this.last.elements[0] != null){
                        flag = false;
                    }
                    if (flag){
                        this.last.prev.next = null;
                        Container<T> imag = this.last.prev;
                        this.last.prev = null;
                        this.last = imag;
                    }
                    queueSize--;
                    return true;
                }
            }
            lastDuplicate = lastDuplicate.prev;
        }
        return false;
    }

    @Override
    public boolean add(T e) {
        this.addLast(e);
        return true;
    }

    @Override
    public boolean offer(T e) {
        this.offerLast(e);
        return true;
    }

    @Override
    public T remove() {
        return this.removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
       return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator iteratorTest = c.iterator();
        while (iteratorTest.hasNext()){
            if (iteratorTest.next() == null){
                throw new NullPointerException();
            }
        }
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            addLast((T)iterator.next());
        }
        return true;
    }

    @Override
    public void clear() {
        if (first != last) {
            this.last = this.first;
            this.first.next = null;
            for (int i = 0; i < this.size; i++) {
                this.first.elements[i] = null;
            }
        }
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void push(T e) {
        addFirst(e);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null){
            throw new NullPointerException("Вы хотите добавить значение null");
        }
        Container<T> firstDuplicate = this.first;
        while(firstDuplicate != null){
            for (int i = 0; i < firstDuplicate.elements.length; i++){
                if (Objects.equals(o, firstDuplicate.elements[i])){
                    return true;
                }
            }
            firstDuplicate = firstDuplicate.next;
        }
        return false;
    }

    @Override
    public int size() {
        return this.queueSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator iterator() {
        return new TripDeqIterator();
    }

    private class TripDeqIterator implements Iterator<T> {

        Container<T> cursor;

        int number;

        int remaining = size();

        TripDeqIterator() { cursor = first; }


        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public T next() {
            if (remaining <= 0)
                throw new NoSuchElementException();
            if (remaining == size()){
                for (int i = 0; i < cursor.elements.length; i++){
                    if (cursor.elements[i] != null){
                        number = i;
                        remaining--;
                        return (T)cursor.elements[number];
                    }
                }
            } else {
                number++;
                if (number == 5) {
                    cursor = cursor.next;
                    number = 0;
                }
                remaining--;
                return (T) cursor.elements[number];

            }
            return null;
        }
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator descendingIterator() {
        throw new UnsupportedOperationException();
    }

}
