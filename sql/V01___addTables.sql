CREATE TABLE author (
  id BIGINT,
  name VARCHAR(70),
  country VARCHAR(30),
  birthdate DATE,
  track_count BIGINT CONSTRAINT ch_count CHECK (track_count > 0)
);

ALTER TABLE author ADD CONSTRAINT author_pk
  PRIMARY KEY (id);
CREATE SEQUENCE MusicService_author
  INCREMENT BY 1
  CACHE 1000
  OWNED BY author.id;
ALTER TABLE author ALTER COLUMN id SET DEFAULT nextval('MusicService_author');

CREATE TABLE music_user (
  id BIGINT,
  name VARCHAR(70),
  country VARCHAR(30),
  birthdate DATE,
  login VARCHAR(50),
  email VARCHAR(70),
  hash_password VARCHAR(100),
  is_author BOOLEAN CONSTRAINT nn_is_author NOT NULL,
  author_id BIGINT
);

ALTER TABLE music_user ADD CONSTRAINT user_pk
  PRIMARY KEY (id);
CREATE SEQUENCE MusicService_user
  INCREMENT BY 1
  CACHE 1000
  OWNED BY music_user.id;
ALTER TABLE music_user ALTER COLUMN id SET DEFAULT nextval('MusicService_user');
ALTER TABLE music_user ADD CONSTRAINT user_fk
  FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE SET NULL ON UPDATE CASCADE;

CREATE TABLE track (
  id BIGINT,
  liked BOOLEAN,
  release_date DATE,
  genre VARCHAR(40),
  name VARCHAR(100),
  url TEXT,
  duration BIGINT CONSTRAINT ch_duration CHECK (duration > 0),
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
  FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE NO ACTION ON UPDATE CASCADE;

CREATE TABLE playlist (
  id BIGINT,
  name VARCHAR(70),
  duration BIGINT,
  release_date DATE,
  track_count BIGINT CONSTRAINT ch_count CHECK (track_count > 0),
  is_album BOOLEAN CONSTRAINT nn_is_album NOT NULL,
  user_id BIGINT,
  author_id BIGINT
);

ALTER TABLE playlist ADD CONSTRAINT playlist_pk
  PRIMARY KEY (id);
CREATE SEQUENCE MusicService_playlist
  INCREMENT BY 1
  CACHE 1000
  OWNED BY playlist.id;
ALTER TABLE playlist ALTER COLUMN id SET DEFAULT nextval('MusicService_playlist');
ALTER TABLE playlist ADD CONSTRAINT playlist_user_fk
  FOREIGN KEY (user_id) REFERENCES music_user(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE playlist ADD CONSTRAINT playlist_author_fk
  FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE NO ACTION ON UPDATE CASCADE;

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

CREATE TABLE music_event (
  id BIGINT,
  name VARCHAR(100),
  address VARCHAR(70),
  city VARCHAR(70),
  country VARCHAR(30),
  date TIMESTAMP,
  sale_site TEXT
);

ALTER TABLE music_event ADD CONSTRAINT event_pk
  PRIMARY KEY (id);
CREATE SEQUENCE MusicService_event
  INCREMENT BY 1
  CACHE 1000
  OWNED BY music_event.id;
ALTER TABLE music_event ALTER COLUMN id SET DEFAULT nextval('MusicService_event');

CREATE TABLE event_author (
  author_id BIGINT,
  event_id BIGINT
);

ALTER TABLE event_author ADD CONSTRAINT event_author_pk
  PRIMARY KEY (author_id, event_id);
ALTER TABLE event_author ADD CONSTRAINT ev_author_author_fk
  FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE event_author ADD CONSTRAINT ev_author_event_fk
  FOREIGN KEY (event_id) REFERENCES music_event(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE user_comment (
  id BIGINT,
  title VARCHAR(70),
  description TEXT,
  date TIMESTAMP,
  user_id BIGINT,
  role INT CONSTRAINT nn_role NOT NULL,
  track_id BIGINT,
  event_id BIGINT
);

ALTER TABLE user_comment ADD CONSTRAINT comment_pk
  PRIMARY KEY (id);
CREATE SEQUENCE MusicService_comment
  INCREMENT BY 1
  CACHE 1000
  OWNED BY user_comment.id;
ALTER TABLE user_comment ALTER COLUMN id SET DEFAULT nextval('MusicService_comment');
ALTER TABLE user_comment ADD CONSTRAINT comment_track_fk
  FOREIGN KEY (track_id) REFERENCES track(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_comment ADD CONSTRAINT comment_user_fk
  FOREIGN KEY (user_id) REFERENCES music_user(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_comment ADD CONSTRAINT comment_event_fk
  FOREIGN KEY (event_id) REFERENCES music_event(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE music_subscription (
  id BIGINT,
  name VARCHAR(70),
  author_id BIGINT,
  user_id BIGINT
);

ALTER TABLE music_subscription ADD CONSTRAINT subscription_pk
  PRIMARY KEY (id);
CREATE SEQUENCE MusicService_subscription
  INCREMENT BY 1
  CACHE 1000
  OWNED BY music_subscription.id;
ALTER TABLE track ALTER COLUMN id SET DEFAULT nextval('MusicService_subscription');
ALTER TABLE music_subscription ADD CONSTRAINT subscription_author_fk
  FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE music_subscription ADD CONSTRAINT subscription_user_fk
  FOREIGN KEY (user_id) REFERENCES music_user(id) ON DELETE CASCADE ON UPDATE CASCADE;