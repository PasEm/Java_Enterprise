------------------------------
--USER
CREATE TABLE user_entity (
  id BIGINT,
  first_name VARCHAR(70),
  surname varchar(70),
  country VARCHAR(30),
  birthdate DATE,
  login VARCHAR(50),
  email VARCHAR(70),
  hash_password VARCHAR(100),
  track_count BIGINT CONSTRAINT ch_count CHECK (track_count >= 0),
  avatar text,
  cookie text
);

----------------------------
--ALTER
alter table user_entity alter column track_count set default 0;
alter table user_entity add constraint uq_login unique (login);
--CONSTRAINT
ALTER TABLE user_entity ADD CONSTRAINT user_pk
PRIMARY KEY (id);
CREATE SEQUENCE MusicService_user
  INCREMENT BY 1
  CACHE 1000
  OWNED BY user_entity.id;
ALTER TABLE user_entity ALTER COLUMN id SET DEFAULT nextval('MusicService_user');
-----------------------------------------------------------
CREATE TABLE track (
  id BIGINT,
  release_date DATE,
  genre VARCHAR(40),
  name VARCHAR(100),
  url TEXT,
  duration BIGINT CONSTRAINT ch_duration CHECK (duration > 0),
  avatar text,
  author_id BIGINT
);

ALTER TABLE track ADD CONSTRAINT track_pk
PRIMARY KEY (id);
CREATE SEQUENCE MusicService_track
  INCREMENT BY 1
  CACHE 1000
  OWNED BY track.id;
ALTER TABLE track ALTER COLUMN id SET DEFAULT nextval('MusicService_track');
ALTER TABLE track ADD CONSTRAINT track_fk
FOREIGN KEY (author_id) REFERENCES user_entity(id) ON DELETE NO ACTION ON UPDATE CASCADE;
-----------------------------------------------------------

create table viewed_track (
  track_id bigint,
  user_id bigint,
  liked boolean,
  view_count int constraint view_count_ch check (view_count >= 1)
);
alter table viewed_track add constraint viewed_pk primary key (user_id, track_id);
alter table viewed_track alter column view_count set default 1;
alter table viewed_track add constraint view_track_fk
foreign key (track_id) references track(id) on DELETE cascade on update cascade;
alter table viewed_track add constraint view_user_fk
foreign key (user_id) references user_entity(id) on delete cascade on update cascade;
-----------------------------------------------------------

CREATE TABLE playlist (
  id BIGINT,
  name VARCHAR(70),
  duration BIGINT,
  release_date DATE,
  track_count BIGINT CONSTRAINT ch_count CHECK (track_count > 0),
  is_album BOOLEAN CONSTRAINT nn_is_album NOT NULL,
  user_entity_id BIGINT
);
alter table playlist drop column is_album;

ALTER TABLE playlist ADD CONSTRAINT playlist_pk
PRIMARY KEY (id);
CREATE SEQUENCE MusicService_playlist
  INCREMENT BY 1
  CACHE 1000
  OWNED BY playlist.id;
ALTER TABLE playlist ALTER COLUMN id SET DEFAULT nextval('MusicService_playlist');
ALTER TABLE playlist ADD CONSTRAINT playlist_user_fk
FOREIGN KEY (user_entity_id) REFERENCES user_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;
-----------------------------------------------------------

CREATE TABLE playlist_track(
  playlist_id BIGINT,
  track_id BIGINT
);

ALTER TABLE playlist_track ADD CONSTRAINT playlist_track_pk
PRIMARY KEY (playlist_id, track_id);
ALTER TABLE playlist_track ADD CONSTRAINT pl_track_playlist_fk
FOREIGN KEY (playlist_id) REFERENCES playlist(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE playlist_track ADD CONSTRAINT pl_track_track_fk
FOREIGN KEY (track_id) REFERENCES track(id) ON DELETE CASCADE ON UPDATE CASCADE;
-----------------------------------------------------------

CREATE TABLE music_event (
  id BIGINT,
  name VARCHAR(100),
  address VARCHAR(70),
  city VARCHAR(70),
  country VARCHAR(30),
  date TIMESTAMP,
  avatar text,
  sale_site TEXT
);

ALTER TABLE music_event ADD CONSTRAINT event_pk
PRIMARY KEY (id);
CREATE SEQUENCE MusicService_event
  INCREMENT BY 1
  CACHE 1000
  OWNED BY music_event.id;
ALTER TABLE music_event ALTER COLUMN id SET DEFAULT nextval('MusicService_event');
-----------------------------------------------------------

CREATE TABLE event_participant (
  participant_id BIGINT,
  event_id BIGINT
);

ALTER TABLE event_participant ADD CONSTRAINT event_participant_pk
PRIMARY KEY (participant_id, event_id);
ALTER TABLE event_participant ADD CONSTRAINT ev_participant_fk
FOREIGN KEY (participant_id) REFERENCES user_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE event_participant ADD CONSTRAINT ev_event_fk
FOREIGN KEY (event_id) REFERENCES music_event(id) ON DELETE CASCADE ON UPDATE CASCADE;
-----------------------------------------------------------

CREATE TABLE user_comment (
  id BIGINT,
  title VARCHAR(70),
  description TEXT,
  date TIMESTAMP,
  user_id BIGINT,
  track_id BIGINT,
  event_id BIGINT
);

ALTER TABLE user_comment ADD CONSTRAINT comment_pk
PRIMARY KEY (id);
CREATE SEQUENCE MusicService_comment
  INCREMENT BY 1
  OWNED BY user_comment.id;
ALTER TABLE user_comment ALTER COLUMN id SET DEFAULT nextval('MusicService_comment');
ALTER TABLE user_comment ADD CONSTRAINT comment_track_fk
FOREIGN KEY (track_id) REFERENCES track(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_comment ADD CONSTRAINT comment_user_fk
FOREIGN KEY (user_id) REFERENCES user_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_comment ADD CONSTRAINT comment_event_fk
FOREIGN KEY (event_id) REFERENCES music_event(id) ON DELETE CASCADE ON UPDATE CASCADE;
-----------------------------------------------------------

CREATE TABLE music_subscription (
  id BIGINT,
  name VARCHAR(70),
  user_id BIGINT,
  subscriber_id BIGINT
);

ALTER TABLE music_subscription ADD CONSTRAINT subscription_pk
PRIMARY KEY (id);
CREATE SEQUENCE MusicService_subscription
  INCREMENT BY 1
  CACHE 1000
  OWNED BY music_subscription.id;
ALTER TABLE track ALTER COLUMN id SET DEFAULT nextval('MusicService_subscription');
ALTER TABLE music_subscription ADD CONSTRAINT sub_user_fk
FOREIGN KEY (user_id) REFERENCES user_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE music_subscription ADD CONSTRAINT sub_subscriber_fk
FOREIGN KEY (subscriber_id) REFERENCES user_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;
-----------------------------------------------------------

create table participant_basket (
  id text,
  participant_id bigint
);

alter table participant_basket add CONSTRAINT basket_part_fk
foreign key (participant_id) references user_entity(id) on delete cascade  on update  cascade ;

------------------------------------------------------------
--DROP
drop table auth;
DROP TABLE user_entity;
DROP TABLE track;
DROP TABLE playlist;
DROP TABLE playlist_track;
DROP TABLE music_event;
DROP TABLE event_participant;
DROP TABLE user_comment;
DROP TABLE music_subscription;
--TRUNCATE
truncate table music_event cascade;
truncate table event_participant;
----------------------------------------------

--------------------------------------------------
--INSERT
do $$
declare
  chars char[] := '{a,b,c,d,e,f,g,h,u,i,o,p,k,l,m,n,z,x,w}';
  country text[] := '{Russia, Italy, USA, Japan, Spain, UK, Greece}';
  city1 text :='';
  address1 text :='';
  name1 text :='';
  part bigint;
  event bigint;
begin
  for i in 1 .. 25 loop
    for j in 1.. 10 loop
      city1 = city1 || chars[floor(random() * cardinality(chars) + 1)];
      address1 = address1|| chars[floor(random() * cardinality(chars) + 1)];
      name1 = name1 || chars[floor(random() * cardinality(chars) + 1)];
    end loop;
    insert into music_event(name, address, city, country, date, sale_site)
    values (name1, address1, city1, country[floor(random() * cardinality(country))], '2017-01-05', '123c');
    address1 = '';
    city1 = '';
    name1 = '';
  end loop;
  for i in 1 .. 20 loop
    event := (
             select id from music_event
             offset floor(random() * (select count(id) from music_event))
             limit 1
             );
    part := (
            select id from user_entity
            offset floor(random() * (select count(id) from user_entity))
            limit 1
            );
    insert into event_participant(participant_id, event_id) values (part, event);
  end loop;
end;
$$;
-------------------------------------------------------