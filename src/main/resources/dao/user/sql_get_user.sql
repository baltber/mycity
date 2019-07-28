select
    u.username,
    u.password,
    u.role
from auth.usr u
where u.username = :username;