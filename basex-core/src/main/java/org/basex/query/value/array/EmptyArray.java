package org.basex.query.value.array;

import java.util.*;

import org.basex.query.*;
import org.basex.query.value.*;
import org.basex.util.*;

/**
 * The empty array.
 *
 * @author BaseX Team 2005-17, BSD License
 * @author Leo Woerteler
 */
final class EmptyArray extends Array {
  /** The empty array. */
  static final EmptyArray INSTANCE = new EmptyArray();

  /** Hidden constructor. */
  private EmptyArray() {
  }

  @Override
  public Array cons(final Value elem) {
    return new SmallArray(new Value[] { elem });
  }

  @Override
  public Array snoc(final Value elem) {
    return new SmallArray(new Value[] { elem });
  }

  @Override
  public Value get(final long index) {
    throw Util.notExpected();
  }

  @Override
  public Array put(final long pos, final Value val) {
    throw Util.notExpected();
  }

  @Override
  public long arraySize() {
    return 0;
  }

  @Override
  public Array concat(final Array seq) {
    return seq;
  }

  @Override
  public Value head() {
    throw Util.notExpected();
  }

  @Override
  public Value last() {
    throw Util.notExpected();
  }

  @Override
  public Array init() {
    throw Util.notExpected();
  }

  @Override
  public Array tail() {
    throw Util.notExpected();
  }

  @Override
  public Array subArray(final long pos, final long len, final QueryContext qc) {
    return this;
  }

  @Override
  public boolean isEmptyArray() {
    return true;
  }

  @Override
  public Array reverseArray(final QueryContext qc) {
    return this;
  }

  @Override
  public Array insertBefore(final long pos, final Value value, final QueryContext qc) {
    return new SmallArray(new Value[] { value });
  }

  @Override
  public Array remove(final long pos, final QueryContext qc) {
    throw Util.notExpected();
  }

  @Override
  public ListIterator<Value> iterator(final long size) {
    return Collections.emptyListIterator();
  }

  @Override
  void checkInvariants() {
    // nothing can go wrong
  }

  @Override
  Array consSmall(final Value[] values) {
    return new SmallArray(values);
  }
}
