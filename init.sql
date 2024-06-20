drop table if exists accounts;

create table accounts (
    id serial primary key,
    owner text not null,
    alias text,
    balance numeric not null default 0,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint valid_owner check (owner <> ''),
    constraint valid_alias check (alias <> ''),
    constraint unique_alias unique (alias),
    constraint positive_balance check (balance >= 0)
);
create or replace function lowercase_alias() returns trigger as $$ begin new.alias = lower(new.alias);
return new;
end;
$$ language plpgsql;
create trigger lowercase_alias_trigger before
insert
    or
update on accounts for each row execute procedure lowercase_alias();
insert into accounts (owner, alias, balance)
values ('Alice Smith', 'alice', 1000),
    ('Bob Brown', 'bob', 2000),
    ('Charlie White', 'charlie', 3000),
    ('David Black', 'david', 4000),
    ('Eve Green', 'eve', 5000),
    ('Frank Blue', 'frank', 6000),
    ('Grace Red', 'grace', 7000),
    ('Hank Yellow', 'hank', 8000),
    ('Ivy Purple', 'ivy', 9000),
    ('Jack Orange', 'jack', 10000);