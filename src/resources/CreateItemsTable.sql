create table items(
  id            bigserial  primary key not null,
  name          text not null,
  length        double precision not null,
  breadth       double precision not null,
  height        double precision not null,
  weight        double precision not null,
  is_fragile    boolean not null,
  this_side_up  boolean not null
);

create table vehicles(
  id            bigserial primary key not null,
  name          text not null,
  length        double precision not null,
  breadth       double precision not null,
  height        double precision not null,
  capacity      double precision not null
);

create table orders(
  id            bigserial primary key not null,
  item_id       bigint not null references items(id),
  item_qty      bigint not null,
  vehicle_id    bigint not null references vehicles(id)
);

insert into items (name, length, breadth, height, weight, is_fragile, this_side_up) values
  ('TV',80,20,60,5000,false,false),
  ('Fridge',80,80,200,35000,false,true),
  ('Mobile',25,15,15,850,true,false),
  ('Washing Machine',100,100,150,20000,false,true);


insert into vehicles (name, length, breadth, height, capacity) values
  ('Big truck',975,244,244,15000000),
  ('Medium Truck',533,229,229,9000000),
  ('Small Truck',427,183,168,3000000);

