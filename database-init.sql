-- create new database
CREATE DATABASE grassroot_united;

-- Enum Type pi_body_size
CREATE TYPE body_size AS ENUM('XXS', 'XS', 'S', 'M', 'L', 'XL', 'XXL', 'XXXL');

-- player_information
CREATE TABLE player_information(
  pi_id SERIAL NOT NULL,
  pi_user_type INT,
  pi_customer_number VARCHAR(255),
  pi_first_name VARCHAR(255),
  pi_last_name VARCHAR(255),
  pi_email VARCHAR(255),
  pi_password VARCHAR(255),
  pi_mobile_number VARCHAR(255),
  pi_occupation INT,
  pi_date_of_birth DATE,
  pi_gender VARCHAR(255),
  pi_photo_profile VARCHAR(255),
  pi_address TEXT,
  pi_city VARCHAR(255),
  pi_postal_code VARCHAR(255),
  pi_body_size body_size,
  pi_activation_code VARCHAR(255),
  pi_email_status BOOLEAN,
  pi_verified_at TIMESTAMP,
  pi_created_at TIMESTAMP DEFAULT NOW(),
  pi_created_by INT,
  pi_updated_at TIMESTAMP,
  pi_updated_by INT,
  PRIMARY KEY (pi_id)
);

-- player_information comments
COMMENT ON COLUMN player_information.pi_occupation IS 'Reference to mr_occupation';

-- ====================================================================== --

-- preferred_position
CREATE TABLE preferred_position(
  pp_id SERIAL NOT NULL,
  pp_pi_id INT,
  pp_position INT,
  pp_created_at TIMESTAMP DEFAULT NOW(),
  pp_created_by INT,
  pp_updated_at TIMESTAMP,
  pp_updated_by INT,
  PRIMARY KEY (pp_id)
);

-- preferred_position comments
COMMENT ON COLUMN preferred_position.pp_position IS 'Reference to mr_position';

-- ====================================================================== --

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

-- ====================================================================== --

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
  PRIMARY KEY (gi_id)
);

-- ====================================================================== --

-- game_galleries
CREATE TABLE game_galleries(
  ggs_id SERIAL NOT NULL,
  ggs_gd_id INT,
  ggs_image_url TEXT,
  ggs_alt_image VARCHAR(255),
  ggs_created_at TIMESTAMP DEFAULT NOW(),
  ggs_created_by INT,
  ggs_updated_at TIMESTAMP,
  ggs_updated_by INT,
  PRIMARY KEY (ggs_id)
);

-- ====================================================================== --

-- game_costs
CREATE TABLE game_costs(
  gcs_id SERIAL NOT NULL,
  gcs_gd_id INT,
  gcs_description TEXT,
  gcs_cost DOUBLE PRECISION,
  gcs_created_at TIMESTAMP DEFAULT NOW(),
  gcs_created_by INT,
  gcs_updated_at TIMESTAMP,
  gcs_updated_by INT,
  PRIMARY KEY (gcs_id)
);

-- ====================================================================== --

-- game_registration
CREATE TABLE game_registration(
  gr_id SERIAL NOT NULL,
  gr_gd_id INT,
  gr_pi_id INT,
  gr_is_outfield BOOLEAN,
  gr_amount DOUBLE PRECISION,
  gr_transaction_number VARCHAR(255),
  gr_created_at TIMESTAMP DEFAULT NOW(),
  gr_created_by INT,
  gr_updated_at TIMESTAMP,
  gr_updated_by INT,
  PRIMARY KEY (gr_id)
);

-- ====================================================================== --

-- game_registered_player
CREATE TABLE game_registered_player(
  grp_id SERIAL NOT NULL,
  grp_gd_id INT,
  grp_pi_id INT,
  grp_is_outfield BOOLEAN,
  grp_amount_paid DOUBLE PRECISION,
  grp_paid_at TIMESTAMP,
  grp_transaction_number VARCHAR(255),
  grp_created_at TIMESTAMP DEFAULT NOW(),
  grp_created_by INT,
  grp_updated_at TIMESTAMP,
  grp_updated_by INT,
  PRIMARY KEY (grp_id)
);
