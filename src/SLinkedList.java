import Interface_form.List;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E> {

    private Node<E> head;    // head : 노드의 첫 부분을 나타내는 Node<E> 타입의 변수
    private Node<E> tail;    // tail : 노드의 마지막 부분을 나타내는 Node<E> 타입의 변수
    private int size;    // size : 리스트의 크기를 나타내는 int 타입의 변수

    // 생성자는 객체가 생성될 때 자동으로 호출되며, 객체의 초기화를 담당
    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index) {
        // 매개변수로 받은 index 값이 리스트의 범위를 벗어나면 예외 던지기
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> x = head;    // head 노드부터 시작해서
        // 매개변수로 받은 index 만큼 x 노드의 다음 노드를 계속해서 탐색
        for (int i = 0; i < index; i++) {
            x = x.next;    // x노드의 다음 노드를 x에 저장한다
        }
        return x; //해당 위치에 있는 노드를 찾아서 반환
    }

    //즉, 이 메소드를 호출하면 리스트의 첫 부분에 새로운 노드가 추가되며,
    // 만약 리스트가 비어있다면 tail도 첫 번째 노드를 가리키게 된다.
    public void addFirst(E value) {

        Node<E> newNode = new Node<E>(value);    // 새로운 노드를 생성
        newNode.next = head;    // 새로운 노드의 다음 노드를 현재 head가 가리키는 노드로 설정
        head = newNode;    // head를 새로운 노드로 변경
        size++; // 리스트 크기를 1 증가

        //만약, head가 가리키는 노드의 다음 노드가 없다면 (리스트가 비어있다면), tail도 head와 같은 노드를 가리키게 한다.
        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value); // addLast 메소드를 호출
        return true; // 메소드 호출의 결과로 항상 true를 반환
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);    // 새로운 노드를 생성하고, 노드의 값을 매개변수로 받아 초기화
        // 연결 리스트가 비어있다면, addFirst 메소드를 호출하여 새로운 노드를 리스트의 첫번째 노드로 추가하고 메소드를 종료
        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode; // 현재 마지막 노드인 tail의 다음 노드로 새로운 노드를 추가
        tail = newNode; // tail을 새로운 노드로 갱신
        size++; // 리스트의 크기를 1 증가
    }

    @Override
    public void add(int index, E value) {

        // index가 리스트의 크기(size)보다 크거나 음수인 경우 IndexOutOfBoundsException을 던진다.
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // index가 0인 경우, 리스트의 가장 앞에 value를 추가하는 addFirst()를 호출하고 메소드를 종료
        if (index == 0) {
            addFirst(value);
            return;
        }
        // index가 size인 경우, 리스트의 가장 뒤에 value를 추가하는 addLast()를 호출하고 메소드를 종료
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드(prev_Node)
        Node<E> prev_Node = search(index - 1);

        // 추가하려는 위치의 다음 노드(next_Node)
        Node<E> next_Node = prev_Node.next;

        // 새로운 노드(newNode)를 생성
        Node<E> newNode = new Node<E>(value);

        prev_Node.next = null; // prev_Node가 가리키는 노드를 끊은 뒤
        prev_Node.next = newNode; // 새로운 노드를 이전 노드와 연결
        newNode.next = next_Node; // 새로운 노드를 다음 노드와 연결
        size++; // 리스트 크기(size)를 1 증가

    }

    public E remove() {

        Node<E> headNode = head; // headNode 변수에 head 노드를 할당

        if (headNode == null) // headNode가 null인 경우 NoSuchElementException 예외를 throw
            throw new NoSuchElementException();

        // element 변수에 headNode의 데이터를 할당
        E element = headNode.data;

        // nextNode 변수에 headNode의 다음 노드를 할당
        Node<E> nextNode = head.next;

        // headNode의 데이터와 다음 노드를 null로 설정
        head.data = null;
        head.next = null;

        // head 변수에 nextNode를 할당
        head = nextNode;
        size--; // 리스트 크기를 1 감소

        // 리스트 크기가 0인 경우 tail 노드를 null로 설정
        if (size == 0) {
            tail = null;
        }
        return element; // 삭제된 노드의 데이터를 반환
    }

    @Override
    public E remove(int index) {

        // 삭제하려는 노드가 첫 번째 노드인 경우 remove() 메소드를 호출하여 첫 번째 노드를 삭제
        if (index == 0) {
            return remove();
        }

        // 인덱스가 연결 리스트의 범위를 벗어나는 경우 IndexOutOfBoundsException 예외를 발생
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1);    // 삭제할 노드의 이전 노드(prevNode)
        Node<E> removedNode = prevNode.next;    // 삭제할 노드(removedNode)
        Node<E> nextNode = removedNode.next;    // 삭제할 노드의 다음 노드(nextNode)

        E element = removedNode.data;    // 삭제할 노드의 데이터(element)를 저장

        // 이전 노드의 next 레퍼런스를 삭제할 노드의 다음 노드로 변경
        prevNode.next = nextNode;

        // 만약 삭제된 노드가 마지막 노드인 경우 tail을 이전 노드로 변경
        if (prevNode.next == null) {
            tail = prevNode;
        }
        // 삭제된 노드의 next 레퍼런스와 데이터를 null로 변경하여 메모리 누수를 방지하고, 크기(size)를 감소
        removedNode.next = null;
        removedNode.data = null;
        size--;

        return element; // 삭제된 노드의 데이터를 반환
    }

    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head; // head 노드를 prevNode에 할당
        boolean hasValue = false; // hasValue 변수를 false로 초기화
        Node<E> x = head;    // x 노드를 head에 할당합니다. x는 나중에 제거될 노드

        // x를 리스트를 따라 이동하면서 value와 같은 값을 가진 노드를 찾습니다.
        // 값을 찾으면 hasValue를 true로 설정하고 반복문을 중단
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        // x가 null이면, value와 일치하는 노드가 없다는 것을 의미하므로 false를 반환
        if (x == null) {
            return false;
        }

        // x가 head와 같으면, remove() 메소드를 호출하여 head 노드를 제거하고 true를 반환
        if (x.equals(head)) {
            remove();
            return true;
        } else {
            // 이전 노드(prevNode)의 next를 x의 next로 설정하여 x 노드를 제거
            prevNode.next = x.next;

            // prevNode의 next가 null이면, tail 노드를 prevNode로 설정
            if (prevNode.next == null) {
                tail = prevNode;
            }
            x.data = null; // x의 data를 null로 설정
            x.next = null; // x의 next를 null로 설정하여 x 노드를 초기화
            size--; // 리스트의 크기(size)를 감소시키고, true를 반환
            return true;
        }
    }

    @Override
    // search(index) 메소드를 호출하여 주어진 인덱스에 해당하는 노드를 찾은 후, 해당 노드의 데이터를 반환
    public E get(int index) {
        // search(index) 메소드는 이 리스트에서 주어진 인덱스에 해당하는 노드의 데이터를 반환하는 메소드
        return search(index).data;
    }

    @Override
    //  search(index) 메소드를 호출하여 주어진 인덱스에 해당하는 노드를 찾은 후, 해당 노드의 데이터를 새로운 값으로 변경
    public void set(int index, E value) {
        // replaceNode 변수에 search(index) 메소드의 반환값인 해당 노드를 저장
        Node<E> replaceNode = search(index);
        // replaceNode의 데이터 필드를 null로 설정
        replaceNode.data = null;
        // 새로운 값을 할당
        replaceNode.data = value;
    }
    @Override
    // 매개변수로 전달된 값(value)과 같은 값을 가진 노드를 찾을 때까지 연결 리스트의 처음(head)부터 마지막 노드까지 반복문을 실행
    public int indexOf(Object value) {
        int index = 0;
        // 노드 x가 null이 될 때까지 루프를 실행
        for (Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) { // 데이터 값(x.data)과 매개변수로 전달된 값(value)이 같은 경우
                return index; // 현재 노드의 인덱스(index)를 반환
            }
            index++;
        }
        // 해당 값을 가진 노드를 찾지 못한 경우, -1을 반환
        return -1;
    }
    @Override
    //  boolean 타입의 값을 반환, 매개변수는 Object 타입의 "item"
    public boolean contains(Object item) {
        return indexOf(item) >= 0; // indexOf" 메소드를 호출하여 "item"이 리스트 내에 있는지 확인합니다.
        // 만약 "item"이 리스트 내에 존재하면 "indexOf"는 해당 요소의 인덱스를 반환하며, 그렇지 않으면 -1을 반환
    }
    @Override
    // 객체가 현재 가지고 있는 요소의 수를 반환
    public int size() {
        return size;
    }
    @Override
    // 객체 내부에서 유지되는 "size"라는 변수를 확인하여 해당 값이 0이면 true를 반환하고, 0이 아니면 false를 반환
    // 객체가 요소를 가지고 있지 않으면 true를 반환하며, 그렇지 않으면 false를 반환
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void clear() {
        // "head" 노드부터 시작하여 모든 노드를 순회하면서 각 노드의 "data"와 "next" 필드를 null로 설정하여 요소들을 제거
        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null; // "head"와 "tail" 노드를 모두 null로 설정하여 리스트의 끝을 표시
        size = 0; // "size" 변수를 0으로 설정하여 요소의 개수를 0으로 초기화
    }
}