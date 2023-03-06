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
}