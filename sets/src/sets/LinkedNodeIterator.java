package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
  private LinkedNode<E> curr;

  public LinkedNodeIterator(LinkedNode<E> head) {
    this.curr = head;
  }

  @Override
  public boolean hasNext() {
    return !(curr == null);
  }

  @Override
  public E next() {
    if (hasNext() == false)
      throw new NoSuchElementException();
    E temp = curr.getData();
    curr = curr.getNext();
    return temp;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
