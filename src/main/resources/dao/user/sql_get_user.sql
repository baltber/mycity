select
    *
from auth.usr u
left join core.organisation o on u.organisation_id = o.organisation_id
where u.login = :login;