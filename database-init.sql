-- create new database
CREATE DATABASE `grassroot_united`;

-- user_information
CREATE TABLE user_information(
  ui_id SERIAL NOT NULL,
  ui_user_type SMALLINT,
  ui_customer_number VARCHAR(255),
  ui_first_name VARCHAR(255),
  ui_last_name VARCHAR(255),
  ui_email VARCHAR(255),
  ui_password VARCHAR(255),
  ui_mobile_number VARCHAR(255),
  ui_occupation INT,
  ui_date_of_birth DATE,
  ui_gender INT,
  ui_photo_profile VARCHAR(255),
  ui_address TEXT,
  ui_city VARCHAR(255),
  ui_postal_code VARCHAR(255),
  ui_body_size INT,
  ui_activation_code VARCHAR(255),
  ui_email_status BOOLEAN,
  ui_verified_at TIMESTAMP,
  ui_created_at TIMESTAMP DEFAULT NOW(),
  ui_created_by INT,
  ui_updated_at TIMESTAMP,
  ui_updated_by INT,
  PRIMARY KEY (ui_id)
);

-- Comments
COMMENT ON COLUMN user_information.ui_occupation IS 'Reference to mr_occupation';
COMMENT ON COLUMN user_information.ui_body_size IS 'Reference to mr_body_size';
COMMENT ON COLUMN user_information.ui_gender IS 'Reference to mr_gender';

-- =============================================================================================

-- preferred_position
CREATE TABLE preferred_position(
  pp_id SERIAL NOT NULL,
  pp_ui_id INT,
  pp_position INT,
  pp_created_at TIMESTAMP DEFAULT NOW(),
  pp_created_by INT,
  pp_updated_at TIMESTAMP,
  pp_updated_by INT,
  PRIMARY KEY (pp_id),
  FOREIGN KEY (pp_ui_id) REFERENCES user_information(ui_id)
);

-- Comments
COMMENT ON COLUMN preferred_position.pp_position IS 'Reference to mr_position';

-- =============================================================================================

-- game_data
CREATE TABLE game_data(
  gd_id SERIAL NOT NULL,
  gd_game_number VARCHAR(255),
  gd_venue_name VARCHAR(255),
  gd_venue_address VARCHAR(255),
  gd_map_url VARCHAR(255),
  gd_time TIMESTAMP,
  gd_goalkeeper_quota INT,
  gd_outfield_quota INT,
  gd_goalkeeper_price DOUBLE PRECISION,
  gd_outfield_price DOUBLE PRECISION,
  gd_total_cost DOUBLE PRECISION,
  gd_notes TEXT,
  gd_status BOOLEAN,
  gd_created_at TIMESTAMP DEFAULT NOW(),
  gd_created_by INT,
  gd_updated_at TIMESTAMP,
  gd_updated_by INT,
  PRIMARY KEY (gd_id)
);

-- =============================================================================================

-- game_information
CREATE TABLE game_information(
  gi_id SERIAL NOT NULL,
  gi_gd_id INT,
  gi_type INT,
  gi_description TEXT,
  gi_created_at TIMESTAMP DEFAULT NOW(),
  gi_created_by INT,
  gi_updated_at TIMESTAMP,
  gi_updated_by INT,
  PRIMARY KEY (gi_id),
  FOREIGN KEY (gi_gd_id) REFERENCES game_data(gd_id)
);

COMMENT ON COLUMN game_information.gi_type IS 'Reference to mr_game_info';

-- =============================================================================================

-- game_galleries
CREATE TABLE game_gallery(
  gg_id SERIAL NOT NULL,
  gg_gd_id INT,
  gg_image_url TEXT,
  gg_alt_image VARCHAR(255),
  gg_created_at TIMESTAMP DEFAULT NOW(),
  gg_created_by INT,
  gg_updated_at TIMESTAMP,
  gg_updated_by INT,
  PRIMARY KEY (gg_id),
  FOREIGN KEY (gg_gd_id) REFERENCES game_data(gd_id)
);

-- =============================================================================================

-- game_costs
CREATE TABLE game_cost(
  gc_id SERIAL NOT NULL,
  gc_gd_id INT,
  gc_description TEXT,
  gc_cost DOUBLE PRECISION,
  gc_created_at TIMESTAMP DEFAULT NOW(),
  gc_created_by INT,
  gc_updated_at TIMESTAMP,
  gc_updated_by INT,
  PRIMARY KEY (gc_id),
  FOREIGN KEY (gc_gd_id) REFERENCES game_data(gd_id)
);

-- =============================================================================================

-- game_registration
CREATE TABLE game_registration(
  gr_id SERIAL NOT NULL,
  gr_gd_id INT,
  gr_ui_id INT,
  gr_is_outfield BOOLEAN,
  gr_amount DOUBLE PRECISION,
  gr_transaction_number VARCHAR(255),
  gr_created_at TIMESTAMP DEFAULT NOW(),
  gr_created_by INT,
  gr_updated_at TIMESTAMP,
  gr_updated_by INT,
  PRIMARY KEY (gr_id),
  FOREIGN KEY (gr_gd_id) REFERENCES game_data(gd_id),
  FOREIGN KEY (gr_ui_id) REFERENCES user_information(ui_id)
);

-- =============================================================================================

-- game_registered_player
CREATE TABLE game_registered_player(
  grp_id SERIAL NOT NULL,
  grp_gd_id INT,
  grp_ui_id INT,
  grp_is_outfield BOOLEAN,
  grp_amount_paid DOUBLE PRECISION,
  grp_paid_at TIMESTAMP,
  grp_transaction_number VARCHAR(255),
  grp_created_at TIMESTAMP DEFAULT NOW(),
  grp_created_by INT,
  grp_updated_at TIMESTAMP,
  grp_updated_by INT,
  PRIMARY KEY (grp_id),
  FOREIGN KEY (grp_gd_id) REFERENCES game_data(gd_id),
  FOREIGN KEY (grp_ui_id) REFERENCES user_information(ui_id)
);

-- =============================================================================================

-- global_param
CREATE TABLE global_param(
  gp_id SERIAL NOT NULL,
  gp_code_id INT,
  gp_slug VARCHAR(255),
  gp_name VARCHAR(255),
  gp_description VARCHAR(255),
  gp_created_at TIMESTAMP DEFAULT NOW(),
  gp_updated_at TIMESTAMP,
  PRIMARY KEY (gp_id)
);

INSERT INTO global_param(gp_code_id, gp_slug, gp_name, gp_description)
VALUES
  (1, 'mr_game_info', 'Game Information Type', 'Peraturan'),
  (2, 'mr_game_info', 'Game Information Type', 'Ketentuan'),
  (3, 'mr_game_info', 'Game Information Type', 'Fasilitas'),
  (1, 'mr_occupation', 'Occupation', 'Pelajar'),
  (2, 'mr_occupation', 'Occupation', 'Mahasiswa'),
  (3, 'mr_occupation', 'Occupation', 'Karyawan'),
  (4, 'mr_occupation', 'Occupation', 'Belum Bekerja'),
  (1, 'mr_position', 'Player Position', 'Goalkeeper'),
  (2, 'mr_position', 'Player Position', 'Center Back'),
  (3, 'mr_position', 'Player Position', 'Right Back'),
  (4, 'mr_position', 'Player Position', 'Left Back'),
  (5, 'mr_position', 'Player Position', 'Defensive Midfielder'),
  (6, 'mr_position', 'Player Position', 'Center Midfielder'),
  (7, 'mr_position', 'Player Position', 'Attacking Midfielder'),
  (8, 'mr_position', 'Player Position', 'Right Midfielder'),
  (9, 'mr_position', 'Player Position', 'Left Midfielder'),
  (10, 'mr_position', 'Player Position', 'Striker'),
  (1, 'mr_gender', 'Gender', 'Male'),
  (2, 'mr_gender', 'Gender', 'Female'),
  (1, 'mr_body_size', 'Body Size', 'XS'),
  (2, 'mr_body_size', 'Body Size', 'S'),
  (3, 'mr_body_size', 'Body Size', 'M'),
  (4, 'mr_body_size', 'Body Size', 'L'),
  (5, 'mr_body_size', 'Body Size', 'XL'),
  (6, 'mr_body_size', 'Body Size', 'XXL'),
  (7, 'mr_body_size', 'Body Size', 'XXXL'),
  (1, 'mr_user_type', 'User Type', 'Back Office'),
  (2, 'mr_user_type', 'User Type', 'Front Office');

