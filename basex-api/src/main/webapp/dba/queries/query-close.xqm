(:~
 : Save query.
 :
 : @author Christian Grün, BaseX Team, 2014-17
 :)
module namespace dba = 'dba/queries';

import module namespace cons = 'dba/cons' at '../modules/cons.xqm';

(:~
 : Closes a query file.
 : @param  $name   name of query file
 :)
declare
  %rest:POST
  %rest:path("/dba/query-close")
  %rest:query-param("name", "{$name}")
function dba:query-save(
  $name   as xs:string
) as empty-sequence() {
  cons:check(),
  cons:save(map { $cons:K-QUERY: '' })
};
