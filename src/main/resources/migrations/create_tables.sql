CREATE TABLE hotels (
                        id serial NOT NULL,
                        name varchar(255) NOT NULL,
                        country varchar(255) NOT NULL,
                        region varchar(255) NOT NULL,
                        city varchar(255) NOT NULL,
                        street varchar(255) NOT NULL,
                        house varchar(255),
                        description TEXT NOT NULL,
                        phone_num varchar(255),
                        email varchar(255),
                        web_site varchar(255) NOT NULL,

                        family DECIMAL NOT NULL,
                        children DECIMAL NOT NULL,
                        the_youth DECIMAL NOT NULL,
                        old_friends DECIMAL NOT NULL,
                        comfort DECIMAL NOT NULL,
                        distance DECIMAL NOT NULL,
                        price DECIMAL NOT NULL,
                        activity DECIMAL NOT NULL,
                        safety DECIMAL NOT NULL,
                        active_recreation_on_the_water DECIMAL NOT NULL,
                        fishing DECIMAL NOT NULL,
                        football DECIMAL NOT NULL,
                        volleyball DECIMAL NOT NULL,
                        table_tennis DECIMAL NOT NULL,
                        tennis DECIMAL NOT NULL,
                        cycling DECIMAL NOT NULL,
                        distance_from_Kazan DECIMAL NOT NULL,
                        cost_of_stay_per_day DECIMAL NOT NULL,


                        CONSTRAINT hotels_pk PRIMARY KEY (id)
);

CREATE TABLE photos (
                        id serial NOT NULL,
                        id_hotel integer NOT NULL,
                        link varchar(255) NOT NULL
);

CREATE TABLE users (
                       id serial NOT NULL,
                       first_name varchar(255) NOT NULL,
                       last_name varchar(255) NOT NULL,
                       otchestvo varchar(255),
                       email varchar(255) NOT NULL,
                       phone_num varchar(255),
                       password varchar(255) NOT NULL,
                       CONSTRAINT users_pk PRIMARY KEY (id)
);


CREATE TABLE residence_history (
                                   id serial NOT NULL,
                                   check_in_date DATE NOT NULL,
                                   check_out_date DATE NOT NULL,
                                   total_cost FLOAT NOT NULL,
                                   review TEXT,
                                   grade integer,
                                   id_user integer NOT NULL,
                                   id_hotel integer NOT NULL,
                                   CONSTRAINT residence_history_pk PRIMARY KEY (id)
);



ALTER TABLE residence_history ADD CONSTRAINT residence_history_fk0 FOREIGN KEY (id_user) REFERENCES users(id);
ALTER TABLE residence_history ADD CONSTRAINT residence_history_fk1 FOREIGN KEY (id_hotel) REFERENCES hotels(id);
ALTER TABLE photos ADD CONSTRAINT photos_fk0 FOREIGN KEY (id_hotel) REFERENCES hotels(id);






