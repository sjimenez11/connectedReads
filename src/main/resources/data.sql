INSERT INTO book (isbn, title, author, price, genre, synopsis, stock, created_at, updated_at)
VALUES(8417854312, 'De sangre y cenizas', 'Jennifer L. Armentrout', 18.95, 'Fantasía romántica', 'UNA DONCELLA. Elegida desde su nacimiento para dar comienzo a una nueva era, la vida de Poppy nunca le ha pertenecido. La vida de la Doncella es solitaria. Jamás la tocarán. Jamás la mirarán. Jamás le hablarán. Jamás sentirá placer.', 33, NOW()-2, NOW()-1);

INSERT INTO book (isbn, title, author, price, genre, synopsis, stock, created_at, updated_at)
VALUES(8419266280, 'Babel', 'Rebecca F. Kuang', 20.90, 'Narrativa fantástica', '1828. El Instituto Real de Traducción de Oxford, también conocido como Babel, es la institución mágica más importante del mundo. La magia con plata capaz de revelar significados ocultos perdidos en la traducción que allí se practica...', 19, NOW()-2, NOW()-1);

INSERT INTO book (isbn, title, author, price, genre, synopsis, stock, created_at, updated_at)
VALUES(8419266906, 'La guerra de la amapola', 'Rebecca F. Kuang', 22.80, 'Narrativa fantástica', 'Que Rin superase el Keju (una prueba para encontrar a los jóvenes con más talento del imperio) sorprendió a todo el mundo: a los oficiales que realizaron la prueba, que no podían creer que una huérfana...', 45, NOW()-2, NOW()-1);

INSERT INTO reading_group (name, description, genre)
VALUES('FantasySupremacy', 'Adentrémonos en mundos fantásticos', 'Fantasia');