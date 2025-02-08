INSERT INTO authors (name, country, dob) VALUES
('J.K. Rowling', 'United Kingdom', '1965-07-31'),
('George Orwell', 'United Kingdom', '1973-06-25'),
('J.R.R. Tolkien', 'United Kingdom', '1892-01-03'),
('Agatha Christie', 'United Kingdom', '1940-09-15'),
('Mark Twain', 'United States', '1835-11-30');

INSERT INTO books (title, authorID, genre, publishedYear, copiesAvailable) VALUES
('The Silent Patient', 1, 'Thriller', 2019, 5),
('Atomic Habits', 2, 'Self-Help', 2018, 3),
('To Kill a Mockingbird', 3, 'Fiction', 1960, 4),
('The Alchemist', 4, 'Fiction', 1888, 2),
('The Hobbit', 5, 'Fantasy', 1937, 6);

INSERT INTO members (Name, MembershipDate, email) VALUES
('John Doe', '2024-01-15', 'johndoe@example.com'),
('Alice Smith', '2023-09-10', 'alice.smith@email.com'),
('Robert Johnson', '2024-02-05', 'robert.j@email.com'),
('Emily Davis', '2023-11-20', 'emily.davis@example.org'),
('Michael Brown', '2024-03-01', 'michael.brown@email.net');

INSERT INTO borrowingRecords (Member_ID, Book_ID, BorrowDate, ReturnDate) VALUES
-- Older records (Before August 2024)
(1, 101, '2024-05-10', '2024-05-20'),
(2, 102, '2024-06-15', '2024-06-25'),
(3, 103, '2024-07-01', NULL),  -- Still borrowed
(4, 104, '2024-06-20', '2024-07-05'),
(5, 105, '2024-11-10', '2025-01-01'),  -- Not returned

-- Borrowed within the last 6 months (Aug 2024 - Feb 2025)
(1, 102, '2024-09-05', '2024-09-15'),
(2, 103, '2024-10-08', NULL),  -- Not returned
(3, 104, '2024-11-12', '2024-11-22'),
(4, 105, '2024-12-15', NULL),  -- Still borrowed
(5, 101, '2025-01-05', '2025-01-15'),
(1, 103, '2025-01-20', NULL),  -- Recently borrowed, not returned
(2, 104, '2025-02-01', NULL),  -- Borrowed this month

-- Additional varied records  
(3, 105, '2024-08-10', '2024-08-20'),
(4, 101, '2024-09-15', NULL),  -- Still borrowed
(5, 102, '2024-12-25', NULL);  -- Borrowed on Christmas, not returned

truncate table borrowingrecords cascade;
select * from borrowingrecords;