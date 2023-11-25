INSERT INTO client (name) VALUES
  ('Taras'),
  ('Tarantino'),
  ('Silvestor'),
  ('Darunka'),
  ('Katerina'),
  ('Bender'),
  ('Ostap'),
  ('Wagen'),
  ('Nikael'),
  ('Sharick');

INSERT INTO planet (id, name) VALUES
  ('MERCURY1', 'MERCURY'),
  ('VENUS1', 'VENUS'),
  ('EARTH1', 'EARTH'),
  ('MARS1', 'MARS'),
  ('JUPITER1', 'JUPITER');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
  (CURRENT_TIMESTAMP, 1, 'MERCURY1', 'EARTH1'),
  (CURRENT_TIMESTAMP, 2, 'EARTH1', 'JUPITER1'),
  (CURRENT_TIMESTAMP, 3, 'JUPITER1', 'MARS1'),
  (CURRENT_TIMESTAMP, 4, 'MARS1', 'VENUS1'),
  (CURRENT_TIMESTAMP, 5, 'VENUS1', 'EARTH1'),
  (CURRENT_TIMESTAMP, 6, 'EARTH1', 'JUPITER1'),
  (CURRENT_TIMESTAMP, 7, 'JUPITER1', 'EARTH1'),
  (CURRENT_TIMESTAMP, 8, 'VENUS1', 'EARTH1'),
  (CURRENT_TIMESTAMP, 9, 'VENUS1', 'EARTH1'),
  (CURRENT_TIMESTAMP, 10, 'MERCURY1', 'JUPITER1');