package org.basex.test.query;

import org.basex.core.BaseXException;
import org.basex.query.func.FunDef;
import org.basex.query.util.Err;
import org.junit.Test;

/**
 * This class tests the XQuery utility functions prefixed with "util".
 *
 * @author BaseX Team 2005-11, ISC License
 * @author Christian Gruen
 */
public final class FNUtilTest extends AdvancedQueryTest {
  /** Constructor. */
  public FNUtilTest() {
    super("util");
  }

  /**
   * Test method for the util:eval() functions.
   * @throws BaseXException database exception
   */
  @Test
  public void testEval() throws BaseXException {
    final String fun = check(FunDef.EVAL, String.class);
    query(fun + "('1')", "1");
    query(fun + "('1 + 2')", "3");
    error(fun + "('1+')", Err.INCOMPLETE);
    error("declare variable $a := 1; " + fun + "('$a')", Err.VARUNDEF);
    error("for $a in (1,2) return " + fun + "('$a')", Err.VARUNDEF);
  }

  /**
   * Test method for the util:run() functions.
   * @throws BaseXException database exception
   */
  @Test
  public void testRun() throws BaseXException {
    final String fun = check(FunDef.RUN, String.class);
    query(fun + "('etc/xml/input.xq')", "XML");
    error(fun + "('etc/xml/xxx.xq')", Err.UNDOC);
  }

  /**
   * Test method for the util:mb() functions.
   * @throws BaseXException database exception
   */
  @Test
  public void testMB() throws BaseXException {
    final String fun = check(FunDef.MB, (Class<?>) null, Boolean.class);
    query(fun + "(())");
    query(fun + "(1 to 1000, false())");
    query(fun + "(1 to 1000, true())");
  }

  /**
   * Test method for the util:ms() functions.
   * @throws BaseXException database exception
   */
  @Test
  public void testMS() throws BaseXException {
    final String fun = check(FunDef.MS, (Class<?>) null, Boolean.class);
    query(fun + "(())");
    query(fun + "(1 to 1000, false())");
    query(fun + "(1 to 1000, true())");
  }

  /**
   * Test method for the util:integer-to-base() functions.
   * @throws BaseXException database exception
   */
  @Test
  public void testToBase() throws BaseXException {
    final String fun = check(FunDef.TO_BASE, Integer.class, Integer.class);
    query(fun + "(4, 2)", "100");
    query(fun + "(65535, 2)", "1111111111111111");
    query(fun + "(65536, 2)", "10000000000000000");
    query(fun + "(4, 16)", "4");
    query(fun + "(65535, 16)", "ffff");
    query(fun + "(65536, 16)", "10000");
    query(fun + "(4, 10)", "4");
    query(fun + "(65535, 10)", "65535");
    query(fun + "(65536, 10)", "65536");
    error(fun + "(1, 1)", Err.INVBASE);
    error(fun + "(1, 100)", Err.INVBASE);
    error(fun + "(1, 100)", Err.INVBASE);
  }

  /**
   * Test method for the util:integer-from-base() functions.
   * @throws BaseXException database exception
   */
  @Test
  public void testFromBase() throws BaseXException {
    final String fun = check(FunDef.FRM_BASE, String.class, Integer.class);
    query(fun + "('100', 2)", "4");
    query(fun + "('1111111111111111', 2)", "65535");
    query(fun + "('10000000000000000', 2)", "65536");
    query(fun + "('4', 16)", "4");
    query(fun + "('ffff', 16)", "65535");
    query(fun + "('FFFF', 16)", "65535");
    query(fun + "('10000', 16)", "65536");
    query(fun + "('4', 10)", "4");
    query(fun + "('65535', 10)", "65535");
    query(fun + "('65536', 10)", "65536");
    error(fun + "('1', 1)", Err.INVBASE);
    error(fun + "('1', 100)", Err.INVBASE);
    error(fun + "('abc', 10)", Err.INVDIG);
    error(fun + "('012', 2)", Err.INVDIG);
  }
}
