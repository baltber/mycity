select
   count(*)

from core.order o
         join core.order_stat os on o.order_stat_id = os.order_stat_id
