package org.basex.query.func.http;

import org.basex.query.*;
import org.basex.query.func.*;
import org.basex.query.iter.*;
import org.basex.query.value.*;
import org.basex.query.value.item.*;
import org.basex.query.value.node.*;
import org.basex.util.http.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-17, BSD License
 * @author Rositsa Shadura
 */
public final class HttpSendRequest extends StandardFunc {
  @Override
  public Iter iter(final QueryContext qc) throws QueryException {
    checkCreate(qc);

    // get request node
    final ANode request = toEmptyNode(exprs[0], qc);

    // get HTTP URI
    final byte[] href = exprs.length >= 2 ? toEmptyToken(exprs[1], qc) : null;
    // get payload
    final ValueBuilder vb = new ValueBuilder(qc);;
    if(exprs.length == 3) {
      final Iter iter = exprs[2].iter(qc);
      for(Item item; (item = qc.next(iter)) != null;) vb.add(item);
    }
    // send HTTP request
    return new HttpClient(info, qc.context.options).sendRequest(href, request, vb.value());
  }
}
