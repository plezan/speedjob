USE speedjob;

INSERT INTO business
  (id, activity_area, description, name, phone, siret, validation_status, website_url, profile_image_url)
  VALUES
    (1, 'Testeur d\'apis', 'Société qui a pour désir de tester des apis, voilà.', 'APIzTestz', '0231564587', '123456789', true, NULL, 0),
    (2, 'Agriculture', 'Désireux d\'implanter la tech dans nos vaches.', 'I-Cow', '0231123879', '987654321', false, 'http://i-cow.fr', 0),
    (3, 'Société de services', 'On aime rendre service !', 'JINI', '0246876213', '123666789', true, 'https://www.jini.fr', 0);