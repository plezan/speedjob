USE speedjob;

INSERT INTO business
  (id, activity_area, description, name, siret, validation_status, website_url)
  VALUES
  (1, 'Testeur d\'apis', 'Société qui a pour désir de tester des apis, voilà.', 'APIzTestz', '123456789', true, NULL),
  (2, 'Agriculture', 'Désireux d\'implanter la tech dans nos vaches.', 'I-Cow', '987654321', false, 'http://i-cow.fr'),
  (3, 'Société de services', 'On aime rendre service !', 'JINI', '123666789', true, 'https://www.jini.fr');

INSERT INTO agency_business
  (id, name, business_id, is_head_office)
  VALUES
  (1, 'Siège social API', 1, 1),
  (2, 'Bureau principal', 2, 1),
  (3, 'Siège du servicing', 3, 1);