package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    int size = 0;
    for (E i : this) {// Note to self: Efficiently traverse whole list
      size++;
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    for (E i : this) {
      if (i.equals(o))
        return true;
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    for (E i : this) {
      if (!that.contains(i))
        return false;
    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    return that.isSubset(this);
  }

  @Override
  public Set<E> adjoin(E e) {
    if (this.contains(e))
      return this;
    else {
      LinkedNode<E> newHead = new LinkedNode<E>(e, head);
      LinkedSet<E> newSet = new LinkedSet<E>(newHead);
      return newSet;
    }
  }

  @Override
  public Set<E> union(Set<E> that) {
    LinkedSet<E> pointer = this;
    for (E i : that) {
      if (!this.contains(i))
        pointer = (LinkedSet<E>) pointer.adjoin(i);
    }
    return pointer;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    LinkedSet<E> other = new LinkedSet<E>();
    Iterator<E> curr = that.iterator();
    while (curr.hasNext()) {
      E holder = curr.next();
      if (this.contains(holder))
        other = (LinkedSet<E>) other.adjoin(holder);
    }
    return other;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    LinkedSet<E> newSet = new LinkedSet<E>();
    for (E i : this) {
      if (!that.contains(i))
        newSet = (LinkedSet<E>) newSet.adjoin(i);
    }
    return newSet;

  }

  @Override
  public Set<E> remove(E e) {
    LinkedNode<E> removed = null;
    for (E i : this) {
      if (!e.equals(i))
        removed = new LinkedNode<E>(i, removed);
    }
    head = removed;
    return this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (!(o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>) o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
