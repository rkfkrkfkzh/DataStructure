import Interface_form.List;

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
}