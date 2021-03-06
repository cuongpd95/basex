package org.basex.query.value.item;

import static org.basex.query.QueryError.*;

import org.basex.core.*;
import org.basex.query.*;
import org.basex.query.value.type.*;
import org.basex.util.*;

/**
 * String item ({@code xs:string}, {@code xs:normalizedString}, {@code xs:language}, etc.).
 *
 * @author BaseX Team 2005-17, BSD License
 * @author Christian Gruen
 */
public final class Str extends AStr {
  /** Wildcard string. */
  public static final Str WC = new Str(new byte[] { '*' });
  /** Zero-length string. */
  public static final Str ZERO = new Str(Token.EMPTY);

  /**
   * Constructor.
   * @param value value
   */
  private Str(final byte[] value) {
    this(value, AtomType.STR);
  }

  /**
   * Constructor.
   * @param value value
   * @param type item type
   */
  public Str(final byte[] value, final AtomType type) {
    super(type, value);
  }

  /**
   * Returns an instance of this class.
   * @param value value
   * @return instance
   */
  public static Str get(final byte[] value) {
    return value.length == 0 ? ZERO : new Str(value);
  }

  /**
   * Returns an instance of this class.
   * @param value string
   * @return instance
   */
  public static Str get(final String value) {
    return get(Token.token(value));
  }

  /**
   * Returns a string representation of the specified value.
   * @param value object (will be converted to token)
   * @param qc query context
   * @param inf input info
   * @return instance
   * @throws QueryException query exception
   */
  public static Str get(final Object value, final QueryContext qc, final InputInfo inf)
      throws QueryException {

    final boolean validate = qc.context.options.get(MainOptions.CHECKSTRINGS);
    final byte[] bytes = Token.token(value.toString());
    final int bl = bytes.length;
    TokenBuilder tb = null;
    for(int c = 0; c < bl; c += Token.cl(bytes, c)) {
      int cp = Token.cp(bytes, c);
      if(!XMLToken.valid(cp)) {
        if(validate) throw INVCODE_X.get(inf, Integer.toHexString(cp));
        cp = Token.REPLACEMENT;
        if(tb == null) {
          tb = new TokenBuilder(bl);
          for(int b = 0; b < c; b++) tb.addByte(bytes[b]);
        }
      }
      if(tb != null) tb.add(cp);
    }
    return get(tb == null ? bytes : tb.finish());
  }

  @Override
  public byte[] string(final InputInfo info) {
    return value;
  }

  /**
   * Returns the string value.
   * @return string value
   */
  public byte[] string() {
    return value;
  }

  @Override
  public String toJava() {
    return Token.string(value);
  }

  @Override
  public String toString() {
    return toString(value);
  }
}
