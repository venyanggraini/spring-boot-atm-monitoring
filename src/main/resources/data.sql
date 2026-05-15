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