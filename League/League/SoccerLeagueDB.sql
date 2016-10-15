-- demos database creation script
-- This script was created for use with Derby
--
-- This is the Database Schema for the Sport League system.
--

-- Clear out the old table, if they existed at all.
DROP TABLE Registration;
DROP TABLE Player;
DROP TABLE League;
DROP TABLE AdminUser;

--
-- This table represents the League objects
--
CREATE TABLE League (
-- PRIMARY KEY --
   lid             INTEGER        PRIMARY KEY,   -- League's object ID
-- DATA FIELDS --
   lyear           INTEGER        NOT NULL,      -- year of league
   season          VARCHAR(10)    NOT NULL,      -- season: Spring/Summer/Fall
   title           VARCHAR(30)    NOT NULL       -- the title of the league
);


--
-- This table represents the Player objects
--
CREATE TABLE Player (
-- PRIMARY KEY --
   pid             INTEGER        PRIMARY KEY,   -- Player's object ID
-- DATA FIELDS --
   name            VARCHAR(30)    NOT NULL,      -- the player's full name
   address         VARCHAR(30)    NOT NULL,      -- the player's street addr
   city            VARCHAR(20)    NOT NULL,      -- the player's city
   email           VARCHAR(40)    NOT NULL       -- the player's email addr
);


--
-- This table represents the relationship between leagues and players.
--
CREATE TABLE Registration (
-- PRIMARY KEY FIDLDS--
   lid             INTEGER        NOT NULL,
   pid             INTEGER        NOT NULL,
-- DATA FIELDS --
   division        VARCHAR(20)    NOT NULL, 
-- ASSOCIASION FIELDS --
   LEAGUE_LID      INTEGER        NOT NULL,
   PLAYER_PID      INTEGER        NOT NULL,
-- PRIMARY KEY --
   PRIMARY KEY (LID, PID)
);


--
-- This table represents the the AdminUser objects.
--
CREATE TABLE AdminUser (
-- PRIMARY KEY FIDLDS--
   uid             INTEGER        PRIMARY KEY,
-- DATA FIELDS --
   username        VARCHAR(20)    NOT NULL, 
   password        VARCHAR(20)    NOT NULL
);

--
-- This script populates the Sport League database.
--

-- Create the initial set of leagues.
INSERT INTO League (lid, lyear, season, title)
  VALUES (1, 2008, 'Spring', 'Soccer League (Spring ''08)');
INSERT INTO League (lid, lyear, season, title)
  VALUES (2, 2008, 'Summer', 'Summer Soccer Fest 2008');
INSERT INTO League (lid, lyear, season, title)
  VALUES (3, 2008, 'Fall', 'Fall Soccer League (2008)');
INSERT INTO League (lid, lyear, season, title)
  VALUES (4, 2009, 'Spring', 'Soccer League (Spring ''09)');
INSERT INTO League (lid, lyear, season, title)
  VALUES (5, 2009, 'Summer', 'The Summer of Soccer Love 2009');
INSERT INTO League (lid, lyear, season, title)
  VALUES (6, 2009, 'Fall', 'Fall Soccer League (2009)');

-- Insert the basic AdminUser.
INSERT INTO AdminUser (uid, username, password)
  VALUES (100, 'admin', 'admin');
INSERT INTO AdminUser (uid, username, password)
  VALUES (101, 'jack', 'admin');

-- COMMIT;
