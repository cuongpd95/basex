package org.basex.query.func.fn;

import org.basex.query.*;
import org.basex.query.expr.*;
import org.basex.query.func.*;
import org.basex.query.value.*;
import org.basex.query.value.item.*;
import org.basex.query.value.type.*;
import org.basex.util.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-17, BSD License
 * @author Christian Gruen
 */
public final class FnHead extends StandardFunc {
  @Override
  public Item item(final QueryContext qc, final InputInfo ii) throws QueryException {
    return exprs[0].iter(qc).next();
  }

  @Override
  protected Expr opt(final CompileContext cc) throws QueryException {
    final Expr expr = exprs[0];
    final SeqType st = expr.seqType();
    if(st.zeroOrOne()) return expr;
    if(expr instanceof Value) return ((Value) expr).itemAt(0);

    exprType.assign(st.type, st.oneOrMore() ? Occ.ONE : Occ.ZERO_ONE);

    return expr instanceof FnReverse ?
      cc.function(Function._UTIL_LAST_FROM, info, ((Arr) expr).exprs) : this;
  }
}
