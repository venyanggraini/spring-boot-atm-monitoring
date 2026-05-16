CREATE TABLE IF NOT EXISTS atm (
    atm_id VARCHAR(50) UNIQUE PRIMARY KEY,

    atm_status VARCHAR(50) NOT NULL,

    cash_remaining_status VARCHAR(50),

    card_reader_status VARCHAR(50),

    receipt_printer_status VARCHAR(50),

    last_updated TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,

    username VARCHAR(100) UNIQUE NOT NULL,

    email VARCHAR(255) NOT NULL,

    password VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    last_updated TIMESTAMP NOT NULL
);

INSERT INTO atm (
    atm_id,
    atm_status,
    card_reader_status,
    cash_remaining_status,
    receipt_printer_status,
    last_updated
) VALUES 
('ATM001', 'ONLINE', 'NORMAL', 'NORMAL', 'NORMAL', NOW()),

('ATM002', 'OFFLINE', 'ERROR', 'LOW', 'LOW', NOW()),

('ATM003', 'ONLINE', 'WARNING', 'FULL', 'EMPTY', NOW()),

('ATM004', 'OUT_OF_SERVICE', 'WARNING', 'NEAR_FULL', 'LOW', NOW())

ON CONFLICT (atm_id) DO NOTHING;

INSERT INTO users (
    user_id,
    username,
    email,
    password,
    created_at,
    last_updated
)
VALUES (
    1,
    'admin',
    'admin@mail.com',
    '$2a$10$lS7Mfvnau83CME2weeq8WOk99L49a7QbJInluPLEaDZ/XkDaYJ1yy',
    NOW(),
    NOW()
)
ON CONFLICT (user_id) DO NOTHING;