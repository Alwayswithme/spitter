INSERT INTO Author (username, password, favourite_section) VALUES ('jim','********','NEWS');
INSERT INTO Author (username, password, favourite_section) VALUES ('sally','********','VIDEOS');

INSERT INTO Blog (id,author_id,title) VALUES (1,1,'Jim Business');

INSERT INTO Post (id,blog_id,author_id,created_time,section,subject,draft) VALUES
  (1,1,1,'2007-12-05-00.00.00','NEWS','Corn nuts',1),
  (2,1,1,'2008-01-12-00.00.00','VIDEOS','Paul Hogan on Toy Dogs',0);
