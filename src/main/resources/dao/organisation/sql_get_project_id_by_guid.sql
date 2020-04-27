select
    o.project_id
from core.organisation o
where o.organisation_guid = :organisation_guid;