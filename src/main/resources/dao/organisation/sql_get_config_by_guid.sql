select
    o.config
from core.organisation o
where o.organisation_guid = :organisation_guid;