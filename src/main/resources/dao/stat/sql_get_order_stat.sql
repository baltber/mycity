select
    os.order_price,
    os.delivery_price,
    os.total_price,
       o.order_date

from core.order o
         join core.order_stat os on o.order_stat_id = os.order_stat_id
