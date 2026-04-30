INSERT INTO organizers (id, name, description) VALUES (101, 'Globomantics', 'Globomantics Technology Corporation');
INSERT INTO organizers (id, name, description) VALUES (102, 'Carved Rock', 'Carved Rock Sports Equipment');
INSERT INTO organizers (id, name, description) VALUES (103, 'Adventure Seekers', 'Adventure Seekers Tours and Activities');
INSERT INTO organizers (id, name, description) VALUES (104, 'Tech Innovators', 'Leading the way in tech innovation');
INSERT INTO organizers (id, name, description) VALUES (105, 'Health & Wellness Co', 'Promoting health and wellness through events');
INSERT INTO organizers (id, name, description) VALUES (106, 'Green Earth', 'Organizing events for a sustainable future');
INSERT INTO organizers (id, name, description) VALUES (107, 'Foodies United', 'Bringing food lovers together through events');
INSERT INTO organizers (id, name, description) VALUES (108, 'Music Mania', 'Creating unforgettable music events');
INSERT INTO organizers (id, name, description) VALUES (109, 'Artistic Expressions', 'Celebrating art and creativity through events');
INSERT INTO organizers (id, name, description) VALUES (110, 'Sports Central', 'Organizing exciting sports events for all ages');
INSERT INTO organizers (id, name, description) VALUES (111, 'Travel Enthusiasts', 'Planning unforgettable travel experiences through events');
INSERT INTO organizers (id, name, description) VALUES (112, 'Film Fanatics', 'Bringing film lovers together through screenings and festivals');
INSERT INTO organizers (id, name, description) VALUES (113, 'Literary Legends', 'Celebrating literature and storytelling through events');
INSERT INTO organizers (id, name, description) VALUES (114, 'Tech Startups', 'Supporting and showcasing tech startups through events');
INSERT INTO organizers (id, name, description) VALUES (115, 'Fitness Fanatics', 'Organizing fitness events and challenges for all levels');


INSERT INTO venues (id, name, street, city, country) VALUES (201, 'Globomatics Main Office', 'Test Street 325', 'New York', 'USA');
INSERT INTO venues (id, name, street, city, country) VALUES (202, 'Sea View Hotel', 'Beach Boulevard 863', 'Los Angeles', 'USA');

INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (501, 'Globomantics Tech Conference', 101, 201, '2023-10-02', '2023-10-04');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (502, 'Globomantics Developer Day', 101, 201, '2024-01-10', '2024-01-10');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (503, 'Carved Rock New Products Day', 102, 202, '2024-02-29', '2024-02-29');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (504, 'Globomantics AI Summit', 101, 201, '2024-05-15', '2024-05-17');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (505, 'Carved Rock Outdoor Expo', 102, 202, '2024-06-20', '2024-06-22');    
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (506, 'Globomantics Product Launch', 101, 201, '2024-07-10', '2024-07-12');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (507, 'Carved Rock Fitness Seminar', 102, 202, '2024-08-15', '2024-08-15');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (508, 'Globomantics Sales Conference', 101, 201, '2024-09-20', '2024-09-22');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (509, 'Carved Rock Camping Trip', 102, 202, '2024-10-05', '2024-10-07');
INSERT INTO events (id, name, organizer_id, venue_id, start_date, end_date) VALUES (510, 'Globomantics Training Workshop', 101, 201, '2024-11-15', '2024-11-15');


INSERT INTO products (id, event_id, name, description, price) VALUES (801, 501, 'Standard', 'Standard Conference Ticket', 499.00);
INSERT INTO products (id, event_id, name, description, price) VALUES (802, 501, 'Premium', 'Premium Conference Ticket', 649.00);
INSERT INTO products (id, event_id, name, description, price) VALUES (803, 502, 'Standard', 'Developer Day Ticket', 195.50);
INSERT INTO products (id, event_id, name, description, price) VALUES (804, 503, 'Regular', 'Regular Entrance', 35.00);
INSERT INTO products (id, event_id, name, description, price) VALUES (805, 503, 'VIP', 'VIP Bonus Entrance', 65.00);
