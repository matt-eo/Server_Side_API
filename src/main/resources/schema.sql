create table if not exists customer
(
    id           BIGSERIAL PRIMARY KEY,
    recovery_key uuid               NOT NULL,
    name         VARCHAR(50)        NOT NULL,
    password     VARCHAR(50)        NOT NULL,
    email        VARCHAR(50) UNIQUE NOT NULL,
    address      VARCHAR(100)       NOT NULL
);

/*create table if not exists customer_order
(
    id           BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(100) UNIQUE         NOT NULL,
    price        DECIMAL(10, 2)              NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    customer_id uuid,
    FOREIGN KEY (customer_id) REFERENCES customer(id)

);*/
