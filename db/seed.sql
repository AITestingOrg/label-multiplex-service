CREATE TABLE out_label (
    id                  SERIAL PRIMARY KEY,
    created_at          timestamp NOT NULL,
    updated_at          timestamp NOT NULL,
    name                varchar(255) NOT NULL,
    description         varchar(255)
);

CREATE TABLE in_label (
    id                  SERIAL PRIMARY KEY,
    created_at          timestamp NOT NULL,
    updated_at          timestamp NOT NULL,
    name                varchar(255) NOT NULL,
    out_label_id        bigint REFERENCES out_label(id)
);


insert INTO out_label VALUES
    (1, current_timestamp, current_timestamp, 'FIRST_NAME', 'Unified label for first name'),
    (2, current_timestamp, current_timestamp, 'LAST_NAME', 'Unified label for last name'),
    (3, current_timestamp, current_timestamp, 'DATE', 'Unified label for dates'),
    (4, current_timestamp, current_timestamp, 'EMAIL', 'Unified label for mail addresses'),
    (5, current_timestamp, current_timestamp, 'ADDRESS', 'Unified label for addresses'),
    (6, current_timestamp, current_timestamp, 'GENDER', 'Unified label for genders'),
    (7, current_timestamp, current_timestamp, 'ZIPCODE', 'Unified label for zipcodes'),
    (8, current_timestamp, current_timestamp, 'STATE', 'Unified label for states'),
    (9, current_timestamp, current_timestamp, 'ABR_STATE', 'Unified label for states` abbreviations'),
    (10, current_timestamp, current_timestamp, 'PHONE_NUMBER', 'Unified label for states` abbreviations')
;

insert INTO in_label(created_at, updated_at , name, out_label_id) VALUES
    (current_timestamp, current_timestamp, 'FIRST NAME', 1),
    (current_timestamp, current_timestamp, 'FIRST NAME:', 1),
    (current_timestamp, current_timestamp, 'FIRST NAME*', 1),
    (current_timestamp, current_timestamp, 'first name', 1),
    (current_timestamp, current_timestamp, 'first name:', 1),
    (current_timestamp, current_timestamp, 'first name*', 1),
    (current_timestamp, current_timestamp, 'name', 1),
    (current_timestamp, current_timestamp, 'common name', 1)
;
