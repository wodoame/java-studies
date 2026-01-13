-- Database Setup Script for User Account Management
-- Smart E-Commerce System (SMECS)

-- Create Users table if it doesn't exist
CREATE TABLE IF NOT EXISTS Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(256) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT chk_role CHECK (role IN ('user', 'admin')),
    CONSTRAINT chk_username_length CHECK (LENGTH(username) >= 3),
    CONSTRAINT chk_email_format CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Create index on email for faster lookups
CREATE INDEX IF NOT EXISTS idx_users_email ON Users(email);

-- Create index on username for faster lookups
CREATE INDEX IF NOT EXISTS idx_users_username ON Users(username);

-- Insert a default admin user (password: admin123)
-- Password hash is SHA-256 of "admin123"
INSERT INTO Users (username, email, password_hash, role)
VALUES ('admin', 'admin@smecs.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'admin')
ON CONFLICT (username) DO NOTHING;

-- Insert a test user (password: test123)
-- Password hash is SHA-256 of "test123"
INSERT INTO Users (username, email, password_hash, role)
VALUES ('testuser', 'test@smecs.com', 'ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae', 'user')
ON CONFLICT (username) DO NOTHING;

-- Display success message
SELECT 'Users table created successfully!' AS status;
SELECT COUNT(*) AS total_users FROM Users;

